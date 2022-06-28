const selectCity = document.querySelector("#selectCity");
const selectDistrict = document.querySelector("#selectDistrict");
const cityValue = document.querySelector("#cityName");
console.log(cityValue.value);

selectCity.addEventListener("change", (e) => {
  e.preventDefault();
  const value = e.target.value;
  location.href = "/hotList/" + value;
});

selectDistrict.addEventListener("change", (e) => {
  e.preventDefault();
  const value = e.target.value;

  location.href = "/hotList/" + cityValue.value + "/" + value;
});
