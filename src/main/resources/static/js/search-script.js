// URLs for fetching data
const locationListUrl = "http://localhost:8080/search/api/locationlist";
const serviceTypeListUrl = "http://localhost:8080/search/api/servicetypesoflocation";
const serviceProviderListUrl = "http://localhost:8080/search/api/serviceproviderssoflocation";

// Document Elements
const myLocationSelect = document.getElementById("locationSelect");
const myServiceTypeSelect = document.getElementById("serviceTypeSelect");
const myServiceProviderSelect = document.getElementById("serviceProviderSelect");
const mySubmitBtn = document.getElementById("submitButton");
const mySelectedInformation = document.getElementById("selectedInformation");

// Arrays for fetched data
const locationList = [];
const selectedIds = [];
let mySelectedLocationCalendarIDs = [];
let mySelectedServiceType = null;
let mySelectedLocation = null;
let mySelectedServiceProvider = null;

// Select fillers
const fillLocationSelect = (data) => {
  Object.keys(data).forEach((key) => {
    let newOption = document.createElement('option');

    newOption.text = data[key].name;
    // newOption.setAttribute('name', 'locationId');
    myLocationSelect.setAttribute('value', data[key].id);

    locationList.push(data[key]);

    myLocationSelect.appendChild(newOption);
  })
}

const fillServiceTypeSelect = (data) => {
  let newOption = document.createElement('option');

  newOption.text = data.name;
  // newOption.setAttribute('name', 'serviceTypeId');
  myServiceTypeSelect.setAttribute('value', data.id);
  newOption.setAttribute('value', data.id);

  myServiceTypeSelect.appendChild(newOption);
}

const fillServiceProviderSelect = (data) => {
  let newOption = document.createElement('option');

  newOption.text = data.description;
  // newOption.setAttribute('name', 'serviceProviderId');
  myServiceProviderSelect.setAttribute('value', data.id);

  myServiceProviderSelect.appendChild(newOption);
}

// Onchange Handler #1
myLocationSelect.onchange = changeLocationSelectEventHandler;
function changeLocationSelectEventHandler() {
  myServiceTypeSelect.style.display = 'block';
  myServiceProviderSelect.style.display = 'none';

  mySelectedLocation = myLocationSelect.options[this.selectedIndex];
  selectedIdsFiller(0, mySelectedLocation);

  addDefaultSelectOption(myServiceTypeSelect, 'service');

  locationList.forEach(location => {
    if (mySelectedLocation.innerText == location.name) {
      locationInformationFiller(location.address);
      mySelectedLocationCalendarIDs = location.atariCalendarIds;
    }
  });

  fetcherFromCalendarIDs(`${serviceTypeListUrl}?`, fillServiceTypeSelect);
}

// Onchange Handler #2
myServiceTypeSelect.onchange = changeServiceTypeSelectEventHandler;
function changeServiceTypeSelectEventHandler() {
  myServiceProviderSelect.style.display = 'block';
  
  mySelectedServiceType = myServiceTypeSelect.options[this.selectedIndex];
  selectedIdsFiller(1, mySelectedServiceType);

  addDefaultSelectOption(myServiceProviderSelect, 'provider');

  fetcherFromCalendarIDs(`${serviceProviderListUrl}?servicetypeid=${mySelectedServiceType.value}&`, fillServiceProviderSelect);
}

// Onchange Handler #3
myServiceProviderSelect.onchange = changeSubmitButtonSelectEventHandler;
function changeSubmitButtonSelectEventHandler() {
  mySubmitBtn.style.display = 'block';

  mySelectedServiceProvider = myServiceProviderSelect.options[this.selectedIndex];
  selectedIdsFiller(2, mySelectedServiceProvider);

  //JSON post fetching with DTO(locId, servTypeId, servProvId) => selectedIds[]
}

// Fetchers
window.onload = () => {
  fetch(locationListUrl)
    .then((resp) => (resp.json()))
    .then(fillLocationSelect);
}

function fetcherFromCalendarIDs(url, select) {
  mySelectedLocationCalendarIDs.forEach(calendarId => {
  fetch(`${url}calendarid=${calendarId}`)
    .then((resp) => (resp.json()))
    .then(select);
  })
}

// Additional functions
function addDefaultSelectOption(element, type) {
  while (element.firstChild) {
    element.removeChild(element.firstChild);
  }

  let newOption = document.createElement('option');
  newOption.innerText = `Select a ${type} …`;
  element.appendChild(newOption);
}

function locationInformationFiller(address) {
  while (mySelectedInformation.firstChild) {
    mySelectedInformation.removeChild(mySelectedInformation.firstChild);
  }

  let newDivLocationAddress = document.createElement('div');
  let newH3AddressInfo = document.createElement('h3');
  let newH4AddressInfo = document.createElement('h4');

  newH3AddressInfo.textContent = `Location Information: `;
  newH4AddressInfo.textContent = `${address.city}, ${address.street} ${address.houseNumber}, ${address.zipCode}`;

  newDivLocationAddress.appendChild(newH3AddressInfo);
  newDivLocationAddress.appendChild(newH4AddressInfo);

  mySelectedInformation.appendChild(newDivLocationAddress);
}

function selectedIdsFiller(number, selecteditem) {
    if (selectedIds.length == number) {
        selectedIds.push(selecteditem.value)
    } else {
        selectedIds.splice(0, selectedIds.length + number)
        selectedIds.push(selecteditem.value)
    }
}