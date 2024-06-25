$(document).ready(() => {
	$.ajax({
		url: "http://localhost:8080/api/category",
		method: "GET",
		dataType: "json",
		success: function (response) {
			response.forEach(function (category) {
				const categoryCard = `
                    <div class="col-md-4">
                        <div class="card m-2 category-card">
                            <img src="${category.linkPhoto}" class="card-img-top category-img" alt="${category.name}">
                            <div class="card-body">
                                <h5 class="card-title">${category.name}</h5>
                            </div>
                        </div>
                    </div>
                `;
				$("#category-list").append(categoryCard);
			});
		},
		error: function (xhr, status, error) {
			console.error("Failed to fetch categories:", error);
		},
	});

	$.ajax({
		url: "http://localhost:8080/api/course/before-login", // Adjust the URL to your API endpoint
		method: "GET",
		dataType: "json",
		success: function (response) {
			response.forEach(function (course) {
				const courseCard = `
                    <div class="col-md-4">
                        <div class="card course-card">
                            <img src="${course.linkPhoto}" class="card-img-top course-img" alt="${course.categoryName}">
                            <div class="card-body">
                                <h5 class="card-title">${course.title}</h5>
                                <p class="card-text"><strong>Kategori:</strong> ${course.category}</p>
                                <p class="card-text"><strong>Mentor:</strong> ${course.mentor}</p>
                                <p class="card-text"><strong>Level:</strong> ${course.level}</p>
                                <p class="card-text"><strong>Total Durasi:</strong> ${course.totalDuration} menit</p>
                                <p class="card-text"><strong>Harga:</strong> Rp ${course.price}</p>
                                <p class="card-text"><strong>${course.isPremium}</strong></p>
                            </div>
                        </div>
                    </div>
                `;
				$("#course-list").append(courseCard);
			});
		},
		error: function (xhr, status, error) {
			console.error("Failed to fetch courses:", error);
		},
	});
});

document.addEventListener("DOMContentLoaded", () => {
	const filters = document.querySelectorAll(".filters button");
	const searchBar = document.querySelector(".search-bar input");
	const searchButton = document.querySelector(".search-bar button");
	const courses = document.querySelectorAll(".course");

	// Add event listener to filters
	filters.forEach((filter) => {
		filter.addEventListener("click", (e) => {
			filters.forEach((f) => f.classList.remove("active"));
			e.target.classList.add("active");
			const filterText = e.target.textContent;
			filterCourses(filterText);
		});
	});

	// Add event listener to search button
	searchButton.addEventListener("click", () => {
		const query = searchBar.value.toLowerCase();
		searchCourses(query);
	});

	// Function to filter courses based on category
	function filterCourses(category) {
		courses.forEach((course) => {
			if (category === "All" || course.textContent.includes(category)) {
				course.style.display = "block";
			} else {
				course.style.display = "none";
			}
		});
	}

	// Function to search courses based on query
	function searchCourses(query) {
		courses.forEach((course) => {
			if (course.textContent.toLowerCase().includes(query)) {
				course.style.display = "block";
			} else {
				course.style.display = "none";
			}
		});
	}
});
