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
                            <img src="${category.linkPhoto}" class="img-thumbnail" alt="${category.name}">
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
		url: "/api/course",
		type: "GET",
		contentType: "application/json",
		success: function (response) {
			const courses = JSON.parse(JSON.stringify(response));
			console.log(courses);
			courses.forEach((course) => {
				const cardHtml = /*html*/ `
					<div class="col-md-4">
						<div class="card course-card">
							<img src="${course.linkPhoto}" alt="Course Image">
							<div class="course-card-body">
								<h5 class="course-category">${course.category}</h5>
								<p>${course.title}</p>
								<p class="text-muted">by ${course.mentor}</p>
								<p>
									<span class="course-level text-success">${course.level}</span>
									<span class="course-isPremium text-danger">${course.isPremium}</span>
									<span class="course-duration text-primary">${course.totalDuration} Minutes</span>
								</p>
                                <a href="/course/details/${course.title}" class="btn btn-primary">Details</a>
                            </div>
                        </div>
                    </div>
                `;
				$("#course-list").append(cardHtml);
			});
		},
		error: function (xhr, status, error) {
			console.error("Failed to fetch courses:", error);
		},
	});

	$.ajax({
		url: "/api/course/my-course",
		type: "GET",
		contentType: "application/json",
		success: function (response) {
			const courses = JSON.parse(JSON.stringify(response));
			console.log(courses);
			courses.forEach((course) => {
				const cardHtml = /*html*/ `
					<div class="col-md-4">
						<div class="card course-card">
							<img src="${course.linkPhoto}" alt="Course Image">
							<div class="course-card-body">
								<h5 class="course-category">${course.category}</h5>
								<p>${course.title}</p>
								<p class="text-muted">by ${course.mentor}</p>
								<p>
									<span class="course-level text-success">${course.level}</span>
									<span class="course-isPremium text-danger">${course.isPremium}</span>
									<span class="course-duration text-primary">${course.totalDuration} Minutes</span>
								</p>
                                <a href="/course/details/${course.title}" class="btn btn-primary">Details</a>
                            </div>
                        </div>
                    </div>
                `;
				$("#course-premium").append(cardHtml);
			});
		},
		error: function (xhr, status, error) {
			console.error("Failed to fetch courses:", error);
		},
	});
});

function searchCoursesAndCategories(search) {
	$(".card").each(function () {
		let courseTitle = $(this).find("p").text().toLowerCase();
		let courseCategory = $(this).find("h5").text().toLowerCase();
		if (
			courseTitle.includes(search.toLowerCase()) ||
			courseCategory.includes(search.toLowerCase())
		) {
			$(this).show();
		} else {
			$(this).hide();
		}
	});
}

$("#search").on("input", function () {
	let search = $(this).val().trim();
	searchCoursesAndCategories(search);
});
