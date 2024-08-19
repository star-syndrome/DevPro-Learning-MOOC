$(document).ready(() => {
	$("#tabel-kategori").DataTable({
		ajax: {
			url: "/api/category",
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
			{ data: "linkPhoto" },
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
                            title="Details ${data.name}"
                            onclick="findCategoryById(${data.id})">
                            <span class="material-symbols-rounded"> info </span>
                        </button> 
                        <button
                            type="button"
                            class="btn btn-warning d-flex align-items-center"
                            data-bs-toggle="modal"
                            data-bs-target="#update"
                            title="Update ${data.name}"
                            onclick="beforeUpdateCategory(${data.id})">
                            <span class="material-symbols-rounded"> sync </span>
                        </button>
                        <button
                            type="button"
                            class="btn btn-danger d-flex align-items-center"
                            title="Delete ${data.name}"
                            onclick="deleteCategory(${data.id})">
                            <span class="material-symbols-rounded"> delete </span>
                        </button>   
                    </div>`;
				},
			},
		],
	});
	$("#tabel-kategori").on("draw.dt", function () {
		$("#tabel-kategori")
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

	$("#addCategoryForm").on("submit", function (event) {
		event.preventDefault();

		// Collect form data
		let formData = {
			name: $("#categoryName").val(),
		};

		// Perform AJAX request
		$.ajax({
			url: "/api/admin/category",
			type: "POST",
			contentType: "application/json",
			data: JSON.stringify(formData),
			beforeSend: addCSRFToken(),
			success: function (response) {
				Swal.fire({
					position: "center",
					icon: "success",
					title: "SUCCESS!",
					text: "Category added successfully!",
					showConfirmButton: false,
					timer: 1500,
				});
				$("#tabel-kategori").DataTable().ajax.reload();
				$("#create").modal("hide"); // Close the modal
				$("#addCategoryForm")[0].reset(); // Reset the form
			},
			error: function (xhr, status, error) {
				console.log(error);
				Swal.fire({
					position: "center",
					icon: "error",
					title: "ERROR!",
					text: "Failed to add category!",
					showConfirmButton: false,
					timer: 1500,
				});
			},
		});
	});

	$("#uploadImageForm").on("submit", function (event) {
		event.preventDefault();

		// Create a FormData object to hold the form data
		let formData = new FormData(this);
		let categoryId = $("#categoryId").val();

		// Perform AJAX request
		$.ajax({
			url: `/api/admin/category/upload-image/${categoryId}`,
			type: "POST",
			data: formData,
			processData: false,
			contentType: false,
			beforeSend: addCSRFToken(),
			success: function (response) {
				Swal.fire({
					position: "center",
					icon: "success",
					title: "SUCCESS!",
					text: "Uploaded image to category successful!",
					showConfirmButton: false,
					timer: 1500,
				});
				$("#tabel-kategori").DataTable().ajax.reload();
				$("#uploadImage").modal("hide"); // Close the modal
				$("#uploadImageForm")[0].reset(); // Reset the form
			},
			error: function (xhr, status, error) {
				console.log(error);
				Swal.fire({
					position: "center",
					icon: "error",
					title: "Failed to upload image!",
					text: "Status: " + status,
					showConfirmButton: false,
					timer: 1500,
				});
			},
		});
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

// Get Category By ID
function findCategoryById(id) {
	$.ajax({
		url: `/api/category/${id}`,
		type: "GET",
		contentType: "application/json",
		success: function (response) {
			const categoryDetails = `
                <p><strong>ID:</strong> ${response.id}</p>
                <p><strong>Name:</strong> ${response.name}</p>
                <p><strong>Link Photo:</strong> <img src="${response.linkPhoto}" alt="Category Image" class="img-fluid"></p>
            `;
			$("#categoryDetailsContent").html(categoryDetails);
			$("#details").modal("show");
		},
		error: function (xhr, status, error) {
			console.log(error);
			Swal.fire({
				position: "center",
				icon: "error",
				title: "ERROR!",
				text: "Failed to fetch category details!",
				showConfirmButton: false,
				timer: 1500,
			});
		},
	});
}

function beforeUpdateCategory(id) {
	$.ajax({
		url: `/api/category/${id}`,
		type: "GET",
		contentType: "application/json",
		success: function (response) {
			$("#categoryId-update").val(response.id);
			$("#categoryName-update").val(response.name);
			$("#update").modal("show");
		},
		error: function (xhr, status, error) {
			console.log(error);
			Swal.fire({
				position: "center",
				icon: "error",
				title: "ERROR!",
				text: "Failed to fetch category details!",
				showConfirmButton: false,
				timer: 1500,
			});
		},
	});
}

// Update Category
$("#updateCategoryForm").on("submit", function (event) {
	event.preventDefault();

	let categoryId = $("#categoryId-update").val();
	let categoryName = $("#categoryName-update").val();

	$.ajax({
		url: `/api/admin/category/${categoryId}`,
		type: "PUT",
		contentType: "application/json",
		beforeSend: addCSRFToken(),
		dataType: "JSON",
		data: JSON.stringify({
			name: categoryName,
		}),
		success: function (response) {
			Swal.fire({
				position: "center",
				icon: "success",
				title: "SUCCESS!",
				text: "Category updated successfully!",
				showConfirmButton: false,
				timer: 1500,
			});
			$("#update").modal("hide");
			$("#tabel-kategori").DataTable().ajax.reload();
		},
		error: function (xhr, status, error) {
			console.log(error);
			Swal.fire({
				position: "center",
				icon: "error",
				title: "ERROR!",
				text: "Failed to update category!",
				showConfirmButton: false,
				timer: 1500,
			});
		},
	});
});

// Delete Category By ID
function deleteCategory(id) {
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
				url: `/api/admin/category/${id}`,
				dataType: "JSON",
				beforeSend: addCSRFToken(),
				contentType: "application/json",
				success: (response) => {
					$("#tabel-kategori").DataTable().ajax.reload();
					$("#tabel-modul").DataTable().ajax.reload();
					$("#tabel-kursus").DataTable().ajax.reload();
				},
				error: (error) => {
					console.log(error);
				},
			});
			Swal.fire({
				title: "Deleted!",
				text: "Category has been deleted.",
				icon: "success",
			});
		}
	});
}
