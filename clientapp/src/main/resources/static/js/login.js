function showPassword() {
	let showPW = document.getElementById("current-password");

	if (showPW.type === "password") {
		showPW.type = "text";
	} else {
		showPW.type = "password";
	}
}
