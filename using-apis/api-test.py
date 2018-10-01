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

	def get_region(landmass):
		base_url = 'https://restcountries.eu/rest/v2/region/{0}'
		url = base_url.format(landmass)
		data_from_server = urllib.request.urlopen(url).read()
		string_from_server = data_from_server.decode('utf-8')
		region_landmass_list = json.loads(string_from_server)
		print(region_landmass_list)


	def get_country_name(name):
		base_url = 'https://restcountries.eu/rest/v2/name/{0}'
		url = base_url.format(name)
		data_from_server = urllib.request.urlopen(url).read()
		string_from_server = data_from_server.decode('utf-8')
		country_detail_list = json.loads(string_from_server)
		print(country_detail_list)


def main(args):
	if args.action == 'name':
		country_detail_list = Testing_APIs.get_country_name(args.country_name)
		for detail in country_detail_list:
			detail = ['name']
			print('{0} [{1}]'.format(detail))


	if args.action == 'region':
		region_list = Testing_APIs.get_region(args.country_name)
		for region in region_list:
			region = ['region']
			#part_of_speech = root_word['partofspeech']
			print('{0} [{1}]'.format(region))



if __name__ == '__main__':

	parser = argparse.ArgumentParser(description='Get Country info from the restcountries API')

	parser.add_argument('action',
                        metavar='action',
                        help='all you can do is region as of now',
                        choices=['region', 'name'])

	parser.add_argument('landArea',
						metavar='landArea',
						help='pick a way to id body of land',
						choices=['landmass', 'country'])
						#landmass can be Africa, Americas, Asia, Europe, Oceania

	parser.add_argument('country_name', help='the country to display')

	args = parser.parse_args()
	main(args)
