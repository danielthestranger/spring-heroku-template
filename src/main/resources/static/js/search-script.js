const locationlisturl = "http://localhost:8080/search/api/locationlist";
const servicetypelisturl = "http://localhost:8080/search/api/servicetypesoflocation";

const myLocationSelect = document.getElementById("locationSelect");
const myServiceTypeSelect = document.getElementById("serviceTypeSelect");
const mySelectedInformation = document.getElementById("selectedInformation");

const locationList = [];
let selectedLocationServiceTypes = [];

const fillLocationSelect = (data) => {
  Object.keys(data).forEach((key) => {
    let newOption = document.createElement("option");
    // console.log(data[key].name);

    newOption.text = data[key].name;
    newOption.setAttribute('value', data[key].id);

    locationList.push(data[key]);

    myLocationSelect.appendChild(newOption);
  })
}
window.onload = () => {
  fetch(locationlisturl)
    .then((resp) => (resp.json()))
    .then(fillLocationSelect);
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

myLocationSelect.onchange = changeLocationSelectEventHandler;

function changeLocationSelectEventHandler() {
  myServiceTypeSelect.style.display = "block";
  mySelectedLocation = myLocationSelect.options[this.selectedIndex];
  let mySelectedLocationCalendarIDs = [];

  while (myServiceTypeSelect.firstChild) {
    myServiceTypeSelect.removeChild(myServiceTypeSelect.firstChild);
  }
  let newOption = document.createElement('option');
  newOption.innerText = 'Select a service …';
  myServiceTypeSelect.appendChild(newOption);

  locationList.forEach(location => {
    if (mySelectedLocation.innerText == location.name) {
      locationInformationFiller(location.address);
      mySelectedLocationCalendarIDs = location.atariCalendarIds;
    }
  });

  const fillServiceTypeSelect = (data) => {
    let newOption = document.createElement('option');

    newOption.text = data.name;
    newOption.setAttribute('value', data.id);

    myServiceTypeSelect.appendChild(newOption);

    selectedLocationServiceTypes.push(data);
  }

  mySelectedLocationCalendarIDs.forEach(calendarId => {
    fetch(`${servicetypelisturl}?calendarid=${calendarId}`)
      .then((resp) => (resp.json()))
      .then(fillServiceTypeSelect);
  })
}

function changeServiceTypeSelectEventHandler() {
  
}