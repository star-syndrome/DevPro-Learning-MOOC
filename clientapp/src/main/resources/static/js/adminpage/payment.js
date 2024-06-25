$(document).ready(() => {
	$("#tabel-pembayaran").DataTable({
		ajax: {
			url: "/api/payment",
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
                            onclick="findPaymentById(${data.id})">
                            <span class="material-symbols-rounded"> info </span>
                        </button> 
                        <button
                            type="button"
                            class="btn btn-warning d-flex align-items-center"
                            data-bs-toggle="modal"
                            data-bs-target="#update"
                            title="Update ${data.name}"
                            onclick="beforeUpdatePayment(${data.id})">
                            <span class="material-symbols-rounded"> sync </span>
                        </button>
                        <button
                            type="button"
                            class="btn btn-danger d-flex align-items-center"
                            title="Delete ${data.name}"
                            onclick="deletePayment(${data.id})">
                            <span class="material-symbols-rounded"> delete </span>
                        </button>   
                    </div>`;
				},
			},
		],
	});
	$("#tabel-pembayaran").on("draw.dt", function () {
		$("#tabel-pembayaran")
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

// Add Payment Method
$("#addPaymentMethodForm").on("submit", function (event) {
	event.preventDefault();

	let formData = {
		name: $("#paymentMethodName").val(),
	};

	$.ajax({
		url: "/api/admin/payment",
		type: "POST",
		contentType: "application/json",
		beforeSend: addCSRFToken(),
		data: JSON.stringify(formData),
		success: function (response) {
			Swal.fire({
				position: "center",
				icon: "success",
				title: "Payment Method added successfully!",
				showConfirmButton: false,
				timer: 1500,
			});
			$("#create").modal("hide");
			$("#addPaymentMethodForm")[0].reset();
			$("#tabel-pembayaran").DataTable().ajax.reload();
			$("#tabel-riwayat-pembayaran").DataTable().ajax.reload();
		},
		error: function (xhr, status, error) {
			console.log(error);
			Swal.fire({
				position: "center",
				icon: "error",
				title: "Failed to add payment method!",
				text: "Status: " + status,
				showConfirmButton: false,
				timer: 1500,
			});
		},
	});
});

// Get Payment Method By ID
function findPaymentById(id) {
	$.ajax({
		url: `/api/payment/${id}`,
		type: "GET",
		contentType: "application/json",
		success: function (response) {
			$("#paymentMethodId").text(response.id);
			$("#paymentMethodName-details").text(response.name);
			$("#details").modal("show");
		},
		error: function (xhr, status, error) {
			console.log(error);
			Swal.fire({
				position: "center",
				icon: "error",
				title: "Failed to fetch payment method details!",
				text: "Status: " + status,
				showConfirmButton: false,
				timer: 1500,
			});
		},
	});
}

function beforeUpdatePayment(id) {
	$.ajax({
		url: `/api/payment/${id}`,
		type: "GET",
		contentType: "application/json",
		success: function (response) {
			$("#paymentMethodId-update").val(response.id);
			$("#paymentMethodName-update").val(response.name);
			$("#update").modal("show");
		},
		error: function (xhr, status, error) {
			console.log(error);
			Swal.fire({
				position: "center",
				icon: "error",
				title: "Failed to fetch payment method details!",
				text: "Status: " + status,
				showConfirmButton: false,
				timer: 1500,
			});
		},
	});
}

// Update Payment Method
$("#updatePaymentMethodForm").on("submit", function (event) {
	event.preventDefault();

	const paymentMethodData = {
		id: $("#paymentMethodId-update").val(),
		name: $("#paymentMethodName-update").val(),
	};

	$.ajax({
		url: `/api/admin/payment/${paymentMethodData.id}`,
		type: "PUT",
		contentType: "application/json",
		beforeSend: addCSRFToken(),
		data: JSON.stringify(paymentMethodData),
		success: function (response) {
			Swal.fire({
				position: "center",
				icon: "success",
				title: "Payment Method updated successfully!",
				showConfirmButton: false,
				timer: 1500,
			});
			$("#update").modal("hide");
			$("#tabel-pembayaran").DataTable().ajax.reload();
			$("#tabel-riwayat-pembayaran").DataTable().ajax.reload();
		},
		error: function (xhr, status, error) {
			console.log(error);
			Swal.fire({
				position: "center",
				icon: "error",
				title: "Failed to update payment method!",
				text: "Status: " + status,
				showConfirmButton: false,
				timer: 1500,
			});
		},
	});
});

// Delete Payment Method By ID
function deletePayment(id) {
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
				url: `/api/admin/payment/${id}`,
				dataType: "JSON",
				beforeSend: addCSRFToken(),
				contentType: "application/json",
				success: (response) => {
					$("#tabel-pembayaran").DataTable().ajax.reload();
				},
				error: (error) => {
					console.log(error);
				},
			});
			Swal.fire({
				title: "Deleted!",
				text: "Payment Method has been deleted.",
				icon: "success",
			});
		}
	});
}
