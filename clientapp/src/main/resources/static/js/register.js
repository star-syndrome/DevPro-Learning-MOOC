// Register User
$("#register-button").click(function (e) {
	e.preventDefault();

	let valueName = $("#name").val();
	let valueEmail = $("#email").val();
	let valuePhone = $("#phone").val();
	let valueUsername = $("#username").val();
	let valuePassword = $("#password").val();

	let validatePassword = validationPassword(valuePassword);

	if (validatePassword !== "LFG!") {
		Swal.fire({
			position: "center",
			icon: "error",
			title: validatePassword,
			showConfirmButton: false,
			timer: 1500,
		});
	} else {
		$.ajax({
			type: "POST",
			contentType: "application/json",
			beforeSend: addCSRFToken(),
			dataType: "JSON",
			url: "http://localhost:8080/api/auth/registration",
			data: JSON.stringify({
				name: valueName,
				email: valueEmail,
				phone: valuePhone,
				username: valueUsername,
				password: valuePassword,
			}),
			success: function (response) {
				Swal.fire({
					position: "center",
					icon: "success",
					title: "Registration Successful!",
					html: '<p>Please <a href="/auth/login"><b>log in here</b></a>!</p>',
					showConfirmButton: false,
				});
				$("#name").val("");
				$("#email").val("");
				$("#phone").val("");
				$("#username").val("");
				$("#password").val("");
			},
			error: function (error) {
				console.log(error);
				Swal.fire({
					position: "center",
					icon: "error",
					title: "Registration Failed!",
					showConfirmButton: false,
					timer: 1500,
				});
			},
		});
	}
});

function validationPassword(password) {
	if (password.length < 8) {
		return "Password length must be at least 8 characters";
	} else {
		return "LFG!";
	}
}

// Show/Hide Password
function showPassword() {
	let showPW = document.getElementById("password");
	if (showPW.type === "password") {
		showPW.type = "text";
	} else {
		showPW.type = "password";
	}
}
