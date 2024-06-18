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
                    <div class="flex m-auto gap-4 justify-center">
                        <button
                            type="button"
                            class="btn btn-warning d-flex align-items-center"
                            data-bs-toggle="modal"
                            data-bs-target="#update"
                            title="Update ${data.name}"
                            onclick="beforeUpdateCourse(${data.id})">
                            <span class="material-symbols-rounded"> sync </span>
                        </button>
                        <button
                            type="button"
                            class="btn btn-danger d-flex align-items-center"
                            title="Delete ${data.name}"
                            onclick="deleteCourse(${data.id})">
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
});

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
