$(document).ready(() => {
	$.ajax({
		url: "http://localhost:8080/api/category",
		method: "GET",
		dataType: "json",
		success: function (response) {
			response.forEach(function (category) {
				const categoryCard = /*html*/ `
					<div class="col-sm-2">
						<div class="card category-card">
							<img src="${category.linkPhoto}" class="img-thumbnail category-img" alt="${category.name}">
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
				const courseCard = /*html*/ `
                    <div class="col-md-4">
                        <div class="course-card">
                            <img src="${course.linkPhoto}" alt="Course Image">
                            <div class="course-card-body">
                                <h5 class="course-title">${course.category}</h5>
                                <p>${course.title}</p>
                                <p class="text-muted">by ${course.mentor}</p>
                                <p>
                                    <span class="course-level text-success">${course.level}</span>
                                    <span class="course-isPremium text-danger">${course.isPremium}</span>
                                    <span class="course-duration text-primary">${course.totalDuration} Minutes</span>
                                </p>
								<a href="/auth/login" class="btn btn-primary">Rp ${course.price}</a>
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

function displayCategories(categories) {
	var categoryListHTML = "";

	for (var i = 0; i < categories.length; i++) {
		var category = categories[i];
		var categoryItemHTML = `
			<div class="category-item" data-category-id="${category.id}">
				${category.name}
			</div>
		`;
		categoryListHTML += categoryItemHTML;
	}

	$("#category-list").html(categoryListHTML);

	// Attach click event to category items
	$(".category-item").click(function () {
		var categoryId = $(this).data("categoryId");
		// Handle category click event (e.g., filter products, load subcategories)
		console.log("Category clicked:", categoryId);
	});
}

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
