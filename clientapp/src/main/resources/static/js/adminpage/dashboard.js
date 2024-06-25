$(document).ready(() => {
	$("#tabel-riwayat-pembayaran").DataTable({
		ajax: {
			url: "/api/admin/order/payment-history",
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
			{ data: "category" },
			{ data: "course" },
			{ data: "username" },
			{ data: "isPaid" },
			{ data: "payment" },
			{ data: "time" },
		],
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
