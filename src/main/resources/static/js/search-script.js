const locationlisturl = "http://localhost:8080/search/api/locationlist";
const myLocationSelect = document.getElementById("locationSelect");
const myServiceTypeSelect = document.getElementById("serviceTypeSelect");

const fillLocationSelect = (data) => {
  Object.keys(data).forEach((key) => {
    let newOption = document.createElement("option");
    // console.log(data[key].name);

    newOption.text = data[key].name;
    newOption.setAttribute('value', data[key].id);

    myLocationSelect.appendChild(newOption);
  })
}

fetch(locationlisturl)
  .then((resp) => (resp.json()))
  .then(fillLocationSelect);

myLocationSelect.onchange=changeLocationSelectEventHandler;

function changeLocationSelectEventHandler(event) {
  console.log(123);
  console.log(event);
  myServiceTypeSelect.style.display = "block";
}

// .addEventListener('change', () => {
//   console.log(123);
//   myServiceTypeSelect.style.display = "block";
// });