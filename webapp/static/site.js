//site.js

initialize();

function initialize() {
	
}

function getBaseURL() {
	var baseURL = window.location.protocol + '//' + window.location.hostname + ':' + api_port;
	return baseURL;
}

function onHomeTabClicked() {
	var url = getBaseURL();

}

function onNameTabClicked() {
	var url = getBaseURL() + '/name';
}

function onAgeTabClicked() {
	var url = getBaseURL() + '/age';

}

function onRaceTabClicked() {
	var url = getBaseURL() + '/race';

}

function onDateTabClicked() {
	var url = getBaseURL() + '/date';
}

function onCityTabClicked() {
	var url = getBaseURL() + '/city';
}

function onStateTabClicked() {
	var url = getBaseURL() + '/state';
}

function onDonationsTabClicked() {
	var url = getBaseURL() + '/donations';
}