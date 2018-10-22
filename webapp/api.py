#api.py

'''
	Kellen Dorchen
	Javin White
	October 21, 2018
'''

import sys
import flask
import json
import csv

app = flask.Flask(__name__)

people = []
ages = []
cities = []
states = []
dates = []
raceeths = []
with open('person_file.csv', 'r', encoding='utf-8') as person_data:
	person_data = csv.reader(person_data)
	for person in person_data:
		person_dict = {
			'name': person[0],
			'age': person[1],
			'gender': person[2],
			'raceethnicity': person[3],
			'month': person[4],
			'day': person[5],
			'year': person[6],
			'streetaddress': person[7],
			'city': person[8],
			'state': person[9],
			'latitude': person[10],
			'longitude': person[11],
			'lawenforcementagency': person[12],
			'cause': person[13],
			'armed': person[14],
			}
		people.append(person_dict)
with open('age_file.csv', 'r', encoding='utf=8') as age_data:
	age_data = csv.reader(age_data)
	for person in age_data: 
		age_dict = {
			'age': person[0],
			'name': person[1],
			'gender': person[2],
			'raceethnicity': person[3],
			'month': person[4],
			'day': person[5],
			'year': person[6],
			'streetaddress': person[7],
			'city': person[8],
			'state': person[9],
			'latitude': person[10],
			'longitude': person[11],				
			'lawenforcementagency': person[12],
			'cause': person[13],
			'armed': person[14],
			}	
		ages.append(age_dict)
with open('city_file.csv', 'r', encoding='utf=8') as city_data:
	city_data = csv.reader(city_data)
	for person in city_data: 
		city_dict = {
			'city': person[0],
			'name': person[1],
			'age': person[2],
			'gender': person[3],
			'raceethnicity': person[4],
			'month': person[5],
			'day': person[6],
			'year': person[7],
			'streetaddress': person[8],
			'state': person[9],
			'latitude': person[10],
			'longitude': person[11],
			'lawenforcementagency': person[12],
			'cause': person[13],
			'armed': person[14],
			}
		cities.append(city_dict)
with open('state_file.csv', 'r', encoding='utf=8') as state_data:
	state_data = csv.reader(state_data)
	for person in state_data: 
		state_dict = {
			'state': person[0],
			'name': person[1],
			'age': person[2],
			'gender': person[3],
			'raceethnicity': person[4],
			'month': person[5],
			'day': person[6],
			'year': person[7],
			'streetaddress': person[8],
			'city': person[9],
			'latitude': person[10],
			'longitude': person[11],
			'lawenforcementagency': person[12],
			'cause': person[13],
			'armed': person[14],
			}
		states.append(state_dict)
with open('date_file.csv', 'r', encoding='utf=8') as date_data:
	date_data = csv.reader(date_data)
	for person in date_data: 		
		date_dict = {
			'month': person[0],
			'day': person[1],
			'name': person[2],
			'age': person[3],
			'gender': person[4],
			'raceethnicity': person[5],
			'year': person[6],
			'streetaddress': person[7],
			'city': person[8],
			'state': person[9],
			'latitude': person[10],
			'longitude': person[11],				
			'lawenforcementagency': person[12],
			'cause': person[13],
			'armed': person[14],
			}
		dates.append(date_dict)
with open('raceeth_file.csv', 'r', encoding='utf=8') as raceeth_data:
	raceeth_data = csv.reader(raceeth_data)
	for person in raceeth_data: 
		raceeth_dict = {
			'raceethnicity': person[0],
			'name': person[1],
			'age': person[2],
			'gender': person[3],
			'month': person[4],
			'day': person[5],
			'year': person[6],
			'streetaddress': person[7],
			'city': person[8],
			'state': person[9],
			'latitude': person[10],
			'longitude': person[11],
			'lawenforcementagency': person[12],
			'cause': person[13],
			'armed': person[14],
			}
		raceeths.append(raceeth_dict)

@app.route('/')
def hello():
    return 'Welcome to our website.'

@app.route('/people/<name>/')	
def get_person(name):
	person_list = []
	lower_case_name = name.lower()
	for person in people:
		if person['name'].lower().startswith(lower_case_name):
			person_list.append(person)
	return json.dumps(person_list) 

@app.route('/ages/<age>/')		
def get_age(age):
	age_list = []
	for person in ages:
		if person['age'].startswith(age):
			age_list.append(person)
	return json.dumps(age_list)
	
@app.route('/cities/<city>/')		
def get_city(city):
	city_list = []
	lower_case_city = city.lower()
	for person in cities:
		if person['city'].lower().startswith(lower_case_city):
			city_list.append(person)
	return json.dumps(city_list)

@app.route('/states/<state>/')		
def get_state(state):
	state_list = []
	lower_case_state = state.lower()
	for person in states:
		if person['state'].lower().startswith(lower_case_state):
			state_list.append(person)
	return json.dumps(state_list)
	
@app.route('/dates/<month>/<day>/')		
def get_date(month, day):
	date_list = []
	lower_case_month = month.lower()
	for person in dates:
		if person['month'].lower().startswith(lower_case_month) and person['day'].startswith(day):
			date_list.append(person)
	return json.dumps(date_list)
	
@app.route('/raceeths/<raceeth>/')		
def get_raceeth(raceeth):
	raceeth_list = []
	lower_case_race = raceeth.lower()
	for person in raceeths:
		if person['raceethnicity'].lower().startswith(lower_case_race):
			raceeth_list.append(person)
	return json.dumps(raceeth_list)

if __name__ == '__main__':
    if len(sys.argv) != 3:
        print('Usage: {0} host port'.format(sys.argv[0]))
        print('  Example: {0} perlman.mathcs.carleton.edu 5101'.format(sys.argv[0]))
        exit()
    
    host = sys.argv[1]
    port = int(sys.argv[2])
    app.run(host=host, port=port, debug=True)
