// Forgot Password
$("#forgot-password").click(function (e) {
	e.preventDefault();

	let valueUsername = $("#username").val();
	let valueNewPassword = $("#new-password").val();
	let valueRepeatNewPassword = $("#repeat-new-password").val();

	let validatePassword = validationsPassword(
		valueNewPassword,
		valueRepeatNewPassword
	);
	if (validatePassword !== "LFG!") {
		Swal.fire({
			position: "center",
			icon: "error",
			title: "ERROR!",
			text: validatePassword,
			showConfirmButton: false,
			timer: 2500,
		});
	} else {
		$.ajax({
			type: "POST",
			contentType: "application/json",
			beforeSend: addCSRFToken(),
			dataType: "JSON",
			url: "http://localhost:8080/api/auth/forgot-password",
			data: JSON.stringify({
				username: valueUsername,
				newPassword: valueNewPassword,
				repeatNewPassword: valueRepeatNewPassword,
			}),
			success: function (response) {
				Swal.fire({
					position: "center",
					icon: "success",
					title: "Reset Password Successful!",
					html: '<a href="/auth/login"><b>Log in here!</b></a>',
					showConfirmButton: false,
				});
				$("#username").val("");
				$("#new-password").val("");
				$("#repeat-new-password").val("");
			},
			error: function (error) {
				console.log(error);
				Swal.fire({
					position: "center",
					icon: "error",
					title: "ERROR!",
					text: "Failed to Reset Password!",
					showConfirmButton: false,
					timer: 2500,
				});
			},
		});
	}
});

// Validation Password
function validationsPassword(newPassword, repeatNewPassword) {
	if (newPassword === "" || repeatNewPassword === "") {
		return "Password Must Not Blank!";
	} else if (newPassword !== repeatNewPassword) {
		return "The Passwords Do Not Match!";
	} else {
		return "LFG!";
	}
}

function showNewPassword() {
	let showPW = document.getElementById("new-password");
	if (showPW.type === "password") {
		showPW.type = "text";
	} else {
		showPW.type = "password";
	}
}

function showRepeatNewPassword() {
	let showPW = document.getElementById("repeat-new-password");
	if (showPW.type === "password") {
		showPW.type = "text";
	} else {
		showPW.type = "password";
	}
}
