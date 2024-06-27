$(document).ready(function () {
	$("#createOrderButton").on("click", function () {
		let paymentId = $("#payment-method").val();
		let courseTitle = $("#course-title").val();

		// Create JSON object to send in the request body
		let orderData = {
			paymentId: paymentId,
			title: courseTitle,
		};

		$.ajax({
			url: "/api/order",
			type: "POST",
			beforeSend: addCSRFToken(),
			dataType: "JSON",
			contentType: "application/json",
			data: JSON.stringify(orderData),
			success: function (response) {
				// Handle the response data
				console.log(response);

				// Redirect to payment page
				window.location.href = "/payment-success";
			},
			error: function (xhr, status, error) {
				// Handle errors
				console.error(error);
				Swal.fire({
					position: "center",
					icon: "error",
					title: "You already purchased this course!",
					text: "Status: " + status,
					showConfirmButton: false,
					timer: 1500,
				});
			},
		});
	});
});

// Get All Payment Method
$.ajax({
	type: "GET",
	contentType: "application/json; charset=utf-8",
	dataType: "JSON",
	url: "/api/payment",
	success: function (response) {
		$.each(response, function (index, payment) {
			$("#payment-method").append(
				$("<option>").text(payment.name).val(payment.id)
			);
		});
	},
	error: function (error) {
		console.log(error);
	},
});
