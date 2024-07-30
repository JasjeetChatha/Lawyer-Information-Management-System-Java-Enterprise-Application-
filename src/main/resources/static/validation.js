// validation.js

function validateAgeInput() {
  var ageInput = document.getElementById('age');
  var ageValue = ageInput.value;

  // Check if the age is less than or equal to 3
  if (parseInt(ageValue) <=0) {
    alert('Invalid Age');
    ageInput.value = ''; // Clear the input
  }
}

