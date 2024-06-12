document.addEventListener("DOMContentLoaded", function() {
    const otpInputs = document.querySelectorAll(".otp-input");
    const submitButton = document.querySelector(".submit-button");
    const successMessage = document.getElementById("success-message");
    const timerElement = document.getElementById("timer");

    let timer = 60;
    const interval = setInterval(() => {
        timer--;
        timerElement.textContent = timer;
        if (timer === 0) {
            clearInterval(interval);
        }
    }, 1000);

    otpInputs.forEach((input, index) => {
        input.addEventListener("input", () => {
            if (input.value.length === 1 && index < otpInputs.length - 1) {
                otpInputs[index + 1].focus();
            }
        });

        input.addEventListener("keydown", (e) => {
            if (e.key === "Backspace" && index > 0) {
                otpInputs[index - 1].focus();
            }
        });
    });

    submitButton.addEventListener("click", () => {
        const otpValue = Array.from(otpInputs).map(input => input.value).join("");
        if (otpValue.length === otpInputs.length) {
            successMessage.style.display = "block";
        }
    });
});
