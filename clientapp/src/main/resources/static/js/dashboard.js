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
		url: "/api/course",
		type: "GET",
		contentType: "application/json",
		success: function (response) {
			const courses = JSON.parse(JSON.stringify(response));
			courses.forEach((course) => {
				const cardHtml = /*html*/ `
                    <div class="col-md-4">
                        <div class="card course-card">
                            <img src="${course.linkPhoto}" class="card-img-top" alt="${course.categoryName}">
                            <div class="card-body">
                                <h5 class="card-title">${course.title}</h5>
                                <p class="card-text"><strong>Category:</strong> ${course.category}</p>
                                <p class="card-text"><strong>Price:</strong> ${course.price}</p>
                                <p class="card-text"><strong>Mentor:</strong> ${course.mentor}</p>
                                <p class="card-text"><strong>Level:</strong> ${course.level}</p>
                                <p class="card-text"><strong>Total Duration:</strong> ${course.totalDuration} minutes</p>
                                <p class="card-text"><strong>Status:</strong> ${course.isPremium}</p>
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
