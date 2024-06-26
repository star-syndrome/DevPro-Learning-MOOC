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
		url: "/api/course",
		type: "GET",
		contentType: "application/json",
		success: function (response) {
			const courses = JSON.parse(JSON.stringify(response));
			courses.forEach((course) => {
				const cardHtml = /*html*/ `
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
});
