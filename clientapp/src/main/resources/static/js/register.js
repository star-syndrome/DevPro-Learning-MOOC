// Register User
$("#register-button").click(function (e) {
	e.preventDefault();

	let valueName = $("#name").val();
	let valueEmail = $("#email").val();
	let valuePhone = $("#phone").val();
	let valueUsername = $("#username").val();
	let valuePassword = $("#password").val();

	let validateRegistration = validationRegistration(
		valuePassword,
		valueName,
		valueEmail,
		valuePhone,
		valueUsername
	);

	if (validateRegistration !== "LFG!") {
		Swal.fire({
			position: "center",
			icon: "error",
			title: "ERROR!",
			text: validateRegistration,
			showConfirmButton: false,
			timer: 2500,
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
					title: "ERROR!",
					text: "Registration Failed!",
					showConfirmButton: false,
					timer: 2500,
				});
			},
		});
	}
});

function validationRegistration(password, name, email, phone, username) {
	if (password.length < 8) {
		return "Password length must be at least 8 characters";
	} else if (name === "") {
		return "Name must not be empty!";
	} else if (email === "") {
		return "Email must not be empty!";
	} else if (phone === "") {
		return "Phone must not be empty!";
	} else if (username === "") {
		return "Username must not be empty!";
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
