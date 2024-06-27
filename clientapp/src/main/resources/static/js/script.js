$(document).ready(function () {
	$.ajax({
		type: "GET",
		contentType: "application/json; charset=utf-8",
		dataType: "JSON",
		url: "/api/profile",
		success: function (response) {
			$("#name").val(response.name);
			$("#email").val(response.email);
			$("#phone").val(response.phone);
			$("#city").val(response.city);
			$("#country").val(response.country);
			$("#username").val(response.username);
		},
		error: function (error) {
			console.log(error);
		},
	});
});

// Update User
$("#update-profile").click(function (e) {
	e.preventDefault();

	let valueName = $("#name").val();
	let valueEmail = $("#email").val();
	let valuePhone = $("#phone").val();
	let valueCity = $("#city").val();
	let valueCountry = $("#country").val();

	let validateProfile = ValidationsProfile(valueEmail, valuePhone, valueName);

	if (validateProfile !== "LFG!") {
		Swal.fire({
			position: "center",
			icon: "error",
			title: validateProfile,
			showConfirmButton: false,
			timer: 1500,
		});
	} else {
		$.ajax({
			type: "PUT",
			contentType: "application/json; charset=utf-8",
			dataType: "JSON",
			beforeSend: addCSRFToken(),
			url: "/api/profile",
			data: JSON.stringify({
				name: valueName,
				email: valueEmail,
				phone: valuePhone,
				city: valueCity,
				country: valueCountry,
			}),
			success: function (response) {
				Swal.fire({
					position: "center",
					icon: "success",
					title: "User Successfully Updated!",
					showConfirmButton: false,
					timer: 1500,
				});
			},
			error: function (error) {
				console.log(error);
				Swal.fire({
					position: "center",
					icon: "error",
					title: "Email or Phone Already Exists!",
					showConfirmButton: false,
					timer: 1500,
				});
			},
		});
	}
});

// Update Password
$("#update-password").click(function (e) {
	e.preventDefault();

	let valueCurrentPassword = $("#current-password").val();
	let valueNewPassword = $("#new-password").val();
	let valueConfirmationPassword = $("#confirm-new-password").val();

	let validatePassword = validationsPassword(
		valueCurrentPassword,
		valueNewPassword,
		valueConfirmationPassword
	);
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
			type: "PUT",
			contentType: "application/json; charset=utf-8",
			dataType: "JSON",
			beforeSend: addCSRFToken(),
			url: "/api/profile/change-password",
			data: JSON.stringify({
				currentPassword: valueCurrentPassword,
				newPassword: valueNewPassword,
				confirmationPassword: valueConfirmationPassword,
			}),
			success: function (response) {
				Swal.fire({
					position: "center",
					icon: "success",
					title: "Password Successfully Updated!",
					text: "Please log out and re-login :)",
					showConfirmButton: true,
				});
				$("#current-password").val("");
				$("#new-password").val("");
				$("#confirm-new-password").val("");
			},
			error: function (error) {
				console.log(error);
				Swal.fire({
					position: "center",
					icon: "error",
					title: "Update Password Failed!",
					showConfirmButton: false,
					timer: 1500,
				});
			},
		});
	}
});

// Validations Password
function validationsPassword(
	currentPassword,
	newPassword,
	confirmationPassword
) {
	if (currentPassword === "") {
		return "Password Must Not Blank!";
	} else if (newPassword === "" || confirmationPassword === "") {
		return "New Password Must Not Blank!";
	} else if (newPassword !== confirmationPassword) {
		return "The Passwords Do Not Match!";
	} else {
		return "LFG!";
	}
}

function showCurrentPassword() {
	let showPW = document.getElementById("current-password");
	if (showPW.type === "password") {
		showPW.type = "text";
	} else {
		showPW.type = "password";
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
	let showPW = document.getElementById("confirm-new-password");
	if (showPW.type === "password") {
		showPW.type = "text";
	} else {
		showPW.type = "password";
	}
}

function ValidationsProfile(email, phone, name) {
	if (email === "") {
		return "Email Must Not Blank!";
	} else if (phone === "") {
		return "Phone Must Not Blank!";
	} else if (name === "") {
		return "Name Must Not Blank!";
	} else {
		return "LFG!";
	}
}

function getYouTubeEmbedLink(videoLink) {
	let videoId = "";
	const regex =
		/^.*(?:youtu\.be\/|v\/|u\/\w\/|embed\/|watch\?v=|\&v=)([^#\&\?]*).*/;
	const match = videoLink.match(regex);
	if (match && match[1].length === 11) {
		videoId = match[1];
	}
	return "https://www.youtube.com/embed/" + videoId;
}

$("#videoModal").on("show.bs.modal", function (event) {
	var button = $(event.relatedTarget);
	var videoLink = button.data("video-link");
	var embedLink = getYouTubeEmbedLink(videoLink);
	var modal = $(this);
	modal.find(".modal-body #courseVideo").attr("src", embedLink);
});

$("#videoModal").on("hidden.bs.modal", function () {
	var modal = $(this);
	modal.find(".modal-body #courseVideo").attr("src", "");
});

// Handle Order Now button click
$("#orderButton").on("click", function () {
	var courseTitle = $(this).data("course-title");

	// Redirect to payment page
	window.location.href = "/order/" + courseTitle;
});
