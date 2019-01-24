const locationlisturl = "http://localhost:8080/search/api/locationlist";
const 

const myLocationSelect = document.getElementById("locationSelect");
const myServiceTypeSelect = document.getElementById("serviceTypeSelect");
const mySelectedInformation = document.getElementById("selectedInformation");

const locationList = [];

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

fetch(locationlisturl)
  .then((resp) => (resp.json()))
  .then(fillLocationSelect);

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

  locationList.forEach(location => myLocationSelect.options[this.selectedIndex].innerText == location.name ? locationInformationFiller(location.address) : 0);

  
}

