'''
By Kellen Dorchen and Javin White
September 30, 2018
api-test.py
'''

import sys
import argparse
import json
import urllib.request

class Testing_APIs:

	def get_all():
		pass

	def get_region(search_for):
		base_url = 'https://restcountries.eu/rest/v2/region/{0}'
		url = base_url.format(search_for)
		data_from_server = urllib.request.urlopen(url).read()
		string_from_server = data_from_server.decode('utf-8')
		region_landmass_list = json.loads(string_from_server)
		for entry in region_landmass_list:
			print("\n Here is this place...\n")
			for key in entry:
				print("{} = {}".format(key, entry[key]))


	def get_country_name(search_for, search_key):
		base_url = 'https://restcountries.eu/rest/v2/name/{0}'
		url = base_url.format(search_for)
		data_from_server = urllib.request.urlopen(url).read()
		string_from_server = data_from_server.decode('utf-8')
		country_detail_list = json.loads(string_from_server)
		if args.filter == 'no':
			for key in country_detail_list[0]:
				print("{} = {}".format(key, country_detail_list[0][key]))
		else:
			print("For %s," % country_detail_list[0]['name'], "here are the %s: " %search_key, country_detail_list[0][search_key])


	def get_filtered(service, search_for):
		base_url = 'https://restcountries.eu/rest/v2/all?fields={0};{1}'
		url = base_url.format(service, search_for)
		data_from_server = urllib.request.urlopen(url).read()
		string_from_server = data_from_server.decode('utf-8')
		filtered_list = json.loads(string_from_server)
		for entry in filtered_list:
			print("\n Here is this place...")
			for key in entry:
				print("{} = {}".format(key, entry[key]))


def main(args):

	if args.service == 'all':
		massive_list = Testing_APIs.get_all()

	if args.service == 'name' and (args.filter == 'no' or args.filter == 'key'):
		country_detail_list = Testing_APIs.get_country_name(args.search_for, args.search_key)

	if args.service == 'region' and args.filter == 'no':
		region_list = Testing_APIs.get_region(args.search_for)

	if args.filter == 'yes':
		filter_list = Testing_APIs.get_filtered(args.service, args.search_for)



if __name__ == '__main__':

	parser = argparse.ArgumentParser(description='Get Country info from the restcountries API')

	parser.add_argument('service',
                        metavar='service',
                        help='what you want to search by:',
                        choices=['region', 'name', 'all'])

	parser.add_argument('filter',
						metavar='filter',
						help='filter search? Can be done by key',
						choices=['yes', 'no', 'key'])

	parser.add_argument('search_for', help='the area in question to display')
						#Region can be Africa, Americas, Asia, Europe, Oceania
						#Name can be partial country name or native name but necessarily full
						#For filtered search region is an option
	#to filter: service, maybe consider one particular field instead of multiple
	#https://restcountries.eu/rest/v2/{service}?fields={field};{field};{field}

	parser.add_argument('search_key',
						metavar='search_key',
						help='search for specific item in particular service; \n if none enter none')


	args = parser.parse_args()
	main(args)
