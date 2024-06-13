// Register User
$("#register-button").click(function (e) {
	e.preventDefault();

	let valueName = $("#name").val();
	let valueEmail = $("#email").val();
	let valuePhone = $("#phone").val();
	let valueUsername = $("#username").val();
	let valuePassword = $("#password").val();

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
				html: '<a href="/auth/login"><b>Log in here!</b></a>',
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
});

// Show/Hide Password
function showPassword() {
	let showPW = document.getElementById("password");
	if (showPW.type === "password") {
		showPW.type = "text";
	} else {
		showPW.type = "password";
	}
}
