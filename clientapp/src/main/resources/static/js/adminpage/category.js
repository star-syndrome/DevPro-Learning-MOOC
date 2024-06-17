// Trigger Add Category Modal
const addCategoryButton = document.getElementById("addCategoryButton");
const addCategoryModal = document.getElementById("addCategoryModal");
const closeAddCategoryModal = document.getElementById("closeAddCategoryModal");
addCategoryButton.addEventListener("click", function () {
  addCategoryModal.classList.remove("hidden");
  addCategoryModal.classList.add("bg-black/60");
});
closeAddCategoryModal.addEventListener("click", function () {
	addCategoryModal.classList.add("hidden");
	addCategoryModal.classList.remove("bg-black/60");
});