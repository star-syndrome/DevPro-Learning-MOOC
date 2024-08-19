$(document).ready(() => {
	// Data Table Courses
	$("#tabel-kursus").DataTable({
		ajax: {
			url: "/api/admin/course",
			dataSrc: "",
		},
		columnDefs: [
			{
				className: "text-center",
				targets: "_all",
				searchable: true,
				orderable: true,
			},
		],
		order: [[1, "asc"]],
		columns: [
			{ data: "id" },
			{ data: "title" },
			{ data: "mentor" },
			{ data: "level" },
			{ data: "price" },
			{
				data: null,
				render: (data) => {
					return /*html*/ `
                    <div class="d-flex m-auto gap-4 justify-content-center">
                        <button
                            type="button"
                            class="btn btn-primary btn-sm"
                            data-toggle="modal"
                            data-target="#details"
                            title="Details ${data.title}"
                            onclick="findCourseById(${data.id})">
                            <span class="material-symbols-rounded"> info </span>
                        </button> 
                        <button
                            type="button"
                            class="btn btn-warning d-flex align-items-center"
                            data-bs-toggle="modal"
                            data-bs-target="#update"
                            title="Update ${data.title}"
                            onclick="beforeUpdateCourse(${data.id})">
                            <span class="material-symbols-rounded"> sync </span>
                        </button>
                        <button
                            type="button"
                            class="btn btn-danger d-flex align-items-center"
                            title="Delete ${data.title}"
                            onclick="deleteCourse(${data.id})">
                            <span class="material-symbols-rounded"> delete </span>
                        </button>   
                    </div>`;
				},
			},
		],
	});

	// Data Table Modules
	$("#tabel-modul").DataTable({
		ajax: {
			url: "/api/admin/module",
			dataSrc: "",
		},
		columnDefs: [
			{
				className: "text-center",
				targets: "_all",
				searchable: true,
				orderable: true,
			},
		],
		order: [[1, "asc"]],
		columns: [
			{ data: "id" },
			{ data: "name" },
			{ data: "content" },
			{ data: "duration" },
			{
				data: null,
				render: (data) => {
					return /*html*/ `
                    <div class="flex m-auto gap-4 justify-center">
                    	<button
                            type="button"
                            class="btn btn-primary btn-sm"
                            data-toggle="modal"
                            data-target="#details-module"
                            title="Details ${data.name}"
                            onclick="findModuleById(${data.id})">
                            <span class="material-symbols-rounded"> info </span>
                        </button>
						<button
                            type="button"
                            class="btn btn-warning d-flex align-items-center"
                            data-bs-toggle="modal"
                            data-bs-target="#update-module"
                            title="Update ${data.name}"
                            onclick="beforeUpdateModule(${data.id})">
                            <span class="material-symbols-rounded"> sync </span>
                        </button>
                        <button
                            type="button"
                            class="btn btn-danger d-flex align-items-center"
                            title="Delete ${data.name}"
                            onclick="deleteModule(${data.id})">
                            <span class="material-symbols-rounded"> delete </span>
                        </button>   
                    </div>`;
				},
			},
		],
	});

	$("#tabel-kursus").on("draw.dt", function () {
		$("#tabel-kursus")
			.DataTable()
			.column(0, { search: "applied", order: "applied" })
			.nodes()
			.each(function (cell, i) {
				cell.innerHTML = i + 1;
			});
	});

	$("#tabel-modul").on("draw.dt", function () {
		$("#tabel-modul")
			.DataTable()
			.column(0, { search: "applied", order: "applied" })
			.nodes()
			.each(function (cell, i) {
				cell.innerHTML = i + 1;
			});
	});

	$.ajax({
		url: "/api/admin/total-courses",
		type: "GET",
		success: function (data) {
			$("#total-kursus").text(data);
		},
		error: function (xhr, status, error) {
			console.error("Error fetching data:", error);
		},
	});

	$.ajax({
		url: "/api/admin/total-premium-courses",
		type: "GET",
		success: function (data) {
			$("#total-kursus-premium").text(data);
		},
		error: function (xhr, status, error) {
			console.error("Error fetching data:", error);
		},
	});

	$.ajax({
		url: "/api/admin/total-users",
		type: "GET",
		success: function (data) {
			$("#total-pengguna").text(data);
		},
		error: function (xhr, status, error) {
			console.error("Error fetching data:", error);
		},
	});
});

function addModule() {
	const moduleContainer = document.getElementById("modules");
	const moduleIndex = moduleContainer.children.length;
	const moduleTemplate = /*html*/ `
		<div class="module-container fw-bold">
			<label for="modules[${moduleIndex}].name">Module Name:</label>
			<input type="text" name="modules[${moduleIndex}].name" class="form-control" required><br>
			
			<label for="modules[${moduleIndex}].description">Module Description:</label>
			<textarea name="modules[${moduleIndex}].description" class="form-control" required></textarea><br>
			
			<label for="modules[${moduleIndex}].content">Module Content:</label>
			<textarea name="modules[${moduleIndex}].content" class="form-control" required></textarea><br>
			
			<label for="modules[${moduleIndex}].duration">Module Duration (minutes):</label>
			<input type="number" name="modules[${moduleIndex}].duration" class="form-control" required><br>
			
			<button type="button" class="btn btn-danger" onclick="removeModule(this)">Remove Module</button>
		</div>
	`;
	moduleContainer.insertAdjacentHTML("beforeend", moduleTemplate);
}

// Button Remove Module
function removeModule(button) {
	button.parentElement.remove();
}

$("#courseForm").on("submit", function (event) {
	event.preventDefault();

	const formData = $(this).serializeArray();
	let courseData = {};
	let modules = [];

	formData.forEach((field) => {
		if (field.name.startsWith("modules[")) {
			const moduleIndex = field.name.match(/\d+/)[0];
			const moduleField = field.name.split("]")[1].slice(1);
			modules[moduleIndex] = modules[moduleIndex] || {};
			modules[moduleIndex][moduleField] = field.value;
		} else {
			courseData[field.name] = field.value;
		}
	});

	courseData.moduleRequests = modules.length > 0 ? modules : [];

	$.ajax({
		url: "/api/admin/course",
		type: "POST",
		contentType: "application/json",
		dataType: "JSON",
		beforeSend: addCSRFToken(),
		data: JSON.stringify(courseData),
		success: function (response) {
			Swal.fire({
				position: "center",
				icon: "success",
				title: "SUCCESS!",
				text: "Course added successfully!",
				showConfirmButton: false,
				timer: 2500,
			});
			$("#create").modal("hide");
			$("#courseForm")[0].reset();
			$("#modules").empty();
			$("#tabel-kursus").DataTable().ajax.reload();
			$("#tabel-modul").DataTable().ajax.reload();
		},
		error: function (xhr, status, error) {
			console.log(error);
			Swal.fire({
				position: "center",
				icon: "error",
				title: "ERROR!",
				text: "Failed to add course!",
				showConfirmButton: false,
				timer: 2500,
			});
		},
	});
});

// Get All Categories
$.ajax({
	type: "GET",
	contentType: "application/json; charset=utf-8",
	dataType: "JSON",
	url: "/api/category",
	success: function (response) {
		$.each(response, function (index, category) {
			$("#categoryId").append(
				$("<option>").text(category.name).val(category.id)
			);
		});
	},
	error: function (error) {
		console.log(error);
	},
});

// Get Module By ID
function findModuleById(id) {
	$.ajax({
		url: `/api/admin/module/${id}`,
		type: "GET",
		contentType: "application/json",
		dataType: "JSON",
		success: function (response) {
			const moduleDetails = /*html*/ `
				<p><strong>ID:</strong> ${response.id}</p>
				<p><strong>Name:</strong> ${response.name}</p>
				<p><strong>Description:</strong> ${response.description}</p>
				<p><strong>Content:</strong> ${response.content}</p>
				<p><strong>Duration:</strong> ${response.duration} minutes</p>
				<p><strong>Course:</strong> ${response.course}</p>
			`;
			$("#moduleDetails").html(moduleDetails);
			$("#details-module").modal("show");
		},
		error: function (xhr, status, error) {
			console.log(error);
			Swal.fire({
				position: "center",
				icon: "error",
				title: "ERROR!",
				text: "Failed to fetch module details!",
				showConfirmButton: false,
				timer: 2500,
			});
		},
	});
}

// Get Course By ID
function findCourseById(id) {
	$.ajax({
		url: `/api/admin/course/${id}`,
		type: "GET",
		contentType: "application/json",
		dataType: "JSON",
		success: function (response) {
			// Populate modal with course details
			const courseDetails = /*html*/ `
				<p><strong>ID:</strong> ${response.id}</p>
				<p><strong>Title:</strong> ${response.title}</p>
				<p><strong>Category:</strong> ${response.category}</p>
				<p><strong>About:</strong> ${response.about}</p>
				<p><strong>Is Premium:</strong> ${response.isPremium}</p>
				<p><strong>Price:</strong> ${response.price}</p>
				<p><strong>Level:</strong> ${response.level}</p>
				<p><strong>Mentor:</strong> ${response.mentor}</p>
				<p><strong>Total Duration:</strong> ${response.totalDuration} minutes</p>
				<br>
				<h5 class="fw-bold fs-3 mb-3">Modules</h5>
				<ul>
                    ${
											Array.isArray(response.moduleAdminResponses) &&
											response.moduleAdminResponses.length > 0
												? response.moduleAdminResponses
														.map(
															(module) => /*html*/ `
                            <li>
                                <p><strong>ID:</strong> ${module.id}</p>
                                <p><strong>Name:</strong> ${module.name}</p>
                                <p><strong>Description:</strong> ${module.description}</p>
                                <p><strong>Content:</strong> ${module.content}</p>
                                <p><strong>Duration:</strong> ${module.duration} minutes</p>
								<br>
                            </li>
                        `
														)
														.join("")
												: "<p>No modules available.</p>"
										}
                </ul>
			`;
			$("#courseDetails").html(courseDetails);
			$("#details").modal("show");
		},
		error: function (xhr, status, error) {
			console.log(error);
			Swal.fire({
				position: "center",
				icon: "error",
				title: "ERROR!",
				text: "Failed to fetch course details!",
				showConfirmButton: false,
				timer: 2500,
			});
		},
	});
}

function addModuleForUpdate(module = {}) {
	const moduleContainer = document.getElementById("modules-update");
	const moduleIndex = moduleContainer.children.length;
	const moduleTemplate = /*html*/ `
		<div class="module-container fw-bold">
			<h6 class="mb-3">Module ${moduleIndex + 1}</h6>
			<input type="hidden" name="modules[${moduleIndex}].id" value="${
		module.id || ""
	}">
			<div class="form-group mb-3">
				<label for="modules[${moduleIndex}].name">Name:</label>
				<input type="text" class="form-control" name="modules[${moduleIndex}].name" value="${
		module.name || ""
	}" required>
			</div>
			<div class="form-group mb-3">
				<label for="modules[${moduleIndex}].description">Description:</label>
				<textarea class="form-control" name="modules[${moduleIndex}].description" required>${
		module.description || ""
	}</textarea>
			</div>
			<div class="form-group mb-3">
				<label for="modules[${moduleIndex}].content">Content:</label>
				<textarea class="form-control" name="modules[${moduleIndex}].content" required>${
		module.content || ""
	}</textarea>
			</div>
			<div class="form-group mb-3">
				<label for="modules[${moduleIndex}].duration">Duration (minutes):</label>
				<input type="number" class="form-control" name="modules[${moduleIndex}].duration" value="${
		module.duration || ""
	}" required>
			</div>
			<button type="button" class="btn btn-danger" onclick="removeModule(this)">Remove Module</button>
		</div>
	`;
	moduleContainer.insertAdjacentHTML("beforeend", moduleTemplate);
}

// Get All Categories for Update Course
$.ajax({
	type: "GET",
	contentType: "application/json; charset=utf-8",
	dataType: "JSON",
	url: "/api/category",
	success: function (response) {
		$.each(response, function (index, category) {
			$("#categoryId-update").append(
				$("<option>").text(category.name).val(category.id)
			);
		});
	},
	error: function (error) {
		console.log(error);
	},
});

function beforeUpdateCourse(id) {
	$.ajax({
		url: `/api/admin/course/${id}`,
		type: "GET",
		contentType: "application/json",
		dataType: "JSON",
		success: function (response) {
			$("#courseId").val(response.id);
			$("#title-update").val(response.title);
			$("#isPremium-update").val(response.isPremium ? "Yes" : "No");
			$("#price-update").val(response.price);
			$("#level-update").val(response.level);
			$("#mentor-update").val(response.mentor);
			$("#about-update").val(response.about);
			$("#categoryId-update").val(response.categoryId);

			// Clear existing modules
			$("#modules-update").empty();

			// Add modules
			response.moduleAdminResponses.forEach((module) =>
				addModuleForUpdate(module)
			);

			// Show the modal
			$("#update").modal("show");
		},
		error: function (xhr, status, error) {
			Swal.fire({
				position: "center",
				icon: "error",
				title: "ERROR!",
				text: "Failed to fetch details course!",
				showConfirmButton: false,
				timer: 2500,
			});
		},
	});
}

// Update Course
$("#updateCourseForm").on("submit", function (event) {
	event.preventDefault();

	const formData = $(this).serializeArray();
	let courseData = {};
	let modules = [];

	formData.forEach((field) => {
		if (field.name.startsWith("modules[")) {
			const moduleIndex = field.name.match(/\d+/)[0];
			const moduleField = field.name.split("]")[1].slice(1);
			modules[moduleIndex] = modules[moduleIndex] || {};
			modules[moduleIndex][moduleField] = field.value;
		} else {
			courseData[field.name] = field.value;
		}
	});

	courseData.moduleRequests = modules.length > 0 ? modules : [];

	$.ajax({
		url: `/api/admin/course/${courseData.id}`,
		type: "PUT",
		contentType: "application/json",
		dataType: "JSON",
		beforeSend: addCSRFToken(),
		data: JSON.stringify(courseData),
		success: function (response) {
			Swal.fire({
				position: "center",
				icon: "success",
				title: "SUCCESS!",
				text: "Course successfully updated!",
				showConfirmButton: false,
				timer: 2500,
			});
			$("#update").modal("hide");
			$("#updateCourseForm")[0].reset();
			$("#modules-update").empty();
			$("#tabel-kursus").DataTable().ajax.reload();
			$("#tabel-modul").DataTable().ajax.reload();
		},
		error: function (xhr, status, error) {
			console.log(error);
			Swal.fire({
				position: "center",
				icon: "error",
				title: "ERROR!",
				text: "Failed to update course!",
				showConfirmButton: false,
				timer: 2500,
			});
		},
	});
});

// Get All Courses for Update Module
$.ajax({
	type: "GET",
	contentType: "application/json; charset=utf-8",
	dataType: "JSON",
	url: "/api/admin/course",
	success: function (response) {
		$.each(response, function (index, course) {
			$("#moduleCourse").append(
				$("<option>").text(course.title).val(course.id)
			);
		});
	},
	error: function (error) {
		console.log(error);
	},
});

function beforeUpdateModule(id) {
	$.ajax({
		url: `/api/admin/module/${id}`,
		type: "GET",
		contentType: "application/json",
		dataType: "JSON",
		success: function (response) {
			// Populate the form with module details
			$("#moduleId").val(response.id);
			$("#moduleName").val(response.name);
			$("#moduleDescription").val(response.description);
			$("#moduleContent").val(response.content);
			$("#moduleDuration").val(response.duration);
			$("#moduleCourse").val(response.course);
			// Show the modal
			$("#update-module").modal("show");
		},
		error: function (xhr, status, error) {
			console.log(error);
			Swal.fire({
				position: "center",
				icon: "error",
				title: "ERROR!",
				text: "Failed to fetch module details!",
				showConfirmButton: false,
				timer: 2500,
			});
		},
	});
}

// Update Module
function updateModule() {
	const moduleData = {
		id: $("#moduleId").val(),
		name: $("#moduleName").val(),
		description: $("#moduleDescription").val(),
		content: $("#moduleContent").val(),
		duration: $("#moduleDuration").val(),
		courseId: $("#moduleCourse").val(),
	};

	$.ajax({
		url: `/api/admin/module/${moduleData.id}`,
		type: "PUT",
		contentType: "application/json",
		dataType: "JSON",
		beforeSend: addCSRFToken(),
		data: JSON.stringify(moduleData),
		success: function (response) {
			Swal.fire({
				position: "center",
				icon: "success",
				title: "SUCCESS!",
				text: "Module successfully updated!",
				showConfirmButton: false,
				timer: 2500,
			});
			$("#update-module").modal("hide");
			$("#updateModuleForm")[0].reset();
			$("#tabel-modul").DataTable().ajax.reload();
			$("tabel-kursus").DataTable().ajax.reload();
		},
		error: function (xhr, status, error) {
			console.log(error);
			Swal.fire({
				position: "center",
				icon: "error",
				title: "ERROR!",
				text: "Module successfully updated!",
				showConfirmButton: false,
				timer: 2500,
			});
		},
	});
}

// Delete Course By ID
function deleteCourse(id) {
	Swal.fire({
		title: "Are you sure?",
		text: "You won't be able to revert this!",
		icon: "warning",
		showCancelButton: true,
		confirmButtonColor: "#3085d6",
		cancelButtonColor: "#d33",
		confirmButtonText: "Yes, delete it!",
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				type: "DELETE",
				url: `/api/admin/course/${id}`,
				dataType: "JSON",
				beforeSend: addCSRFToken(),
				contentType: "application/json",
				success: (response) => {
					$("#tabel-kursus").DataTable().ajax.reload();
					$("#tabel-modul").DataTable().ajax.reload();
				},
				error: (error) => {
					console.log(error);
				},
			});
			Swal.fire({
				title: "Deleted!",
				text: "Course has been deleted.",
				icon: "success",
			});
		}
	});
}

// Delete Module By ID
function deleteModule(id) {
	Swal.fire({
		title: "Are you sure?",
		text: "You won't be able to revert this!",
		icon: "warning",
		showCancelButton: true,
		confirmButtonColor: "#3085d6",
		cancelButtonColor: "#d33",
		confirmButtonText: "Yes, delete it!",
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				type: "DELETE",
				url: `/api/admin/module/${id}`,
				dataType: "JSON",
				beforeSend: addCSRFToken(),
				contentType: "application/json",
				success: (response) => {
					$("#tabel-kursus").DataTable().ajax.reload();
					$("#tabel-modul").DataTable().ajax.reload();
				},
				error: (error) => {
					console.log(error);
				},
			});
			Swal.fire({
				title: "Deleted!",
				text: "Module has been deleted.",
				icon: "success",
			});
		}
	});
}
