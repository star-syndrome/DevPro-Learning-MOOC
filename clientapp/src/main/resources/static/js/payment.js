// Get All Payment Method
$.ajax({
	type: "GET",
	contentType: "application/json; charset=utf-8",
	dataType: "JSON",
	url: "/api/payment",
	success: function (response) {
		$.each(response, function (index, payment) {
			$("#paymentMethod").append(
				$("<option>").text(payment.name).val(payment.id)
			);
		});
	},
	error: function (error) {
		console.log(error);
	},
});
