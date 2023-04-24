document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("change_pw_p_btn").addEventListener("click", function() {
        switchForm("change_pw_p_form", "change_pw_e_form", this);
    });

    document.getElementById("change_pw_e_btn").addEventListener("click", function() {
        switchForm("change_pw_e_form", "change_pw_p_form", this);
    });

    document.querySelectorAll(".change_pw_form .val_button").forEach(function(btn) {
        btn.addEventListener("click", function(e) {
            e.preventDefault();
            var form = this.closest("form");

            var verificationInput = document.createElement("input");
            verificationInput.setAttribute("type", "text");
            verificationInput.setAttribute("placeholder", "인증번호를 입력해주세요.");
            verificationInput.setAttribute("required", "");
            verificationInput.classList.add("verification-input");

            var countDown = document.createElement("div");
            countDown.classList.add("countdown");

            var countdownWrapper = document.createElement("div");
            countdownWrapper.classList.add("countdown-wrapper");
            countdownWrapper.appendChild(verificationInput);
            countdownWrapper.appendChild(countDown);

            form.insertBefore(countdownWrapper, this);

            var confirmButton = document.createElement("button");
            confirmButton.setAttribute("type", "submit");
            confirmButton.textContent = "확인";
            confirmButton.classList.add("val_button");

            form.insertBefore(confirmButton, this);

            this.remove();

            var timeLeft = 180;
            var timer = setInterval(function() {
                if (timeLeft <= 0) {
                    clearInterval(timer);
                    countDown.textContent = "인증번호의 유효 시간이 만료되었습니다.";
                    confirmButton.classList.remove("re_val_button");
                    confirmButton.classList.add("val_button");
                    confirmButton.textContent = "인증번호 재발송";
                } else {
                    countDown.textContent = "남은 시간: " + timeLeft + "초";
                    timeLeft--;
                }
            }, 1000);
        });
    });
});

function switchForm(showFormId, hideFormId, activeBtn) {
    document.getElementById(showFormId).style.display = "block";
    document.getElementById(hideFormId).style.display = "none";

    activeBtn.classList.add("change_pw_active");
    if (activeBtn.nextElementSibling) {
        activeBtn.nextElementSibling.classList.remove("change_pw_active");
    } else {
        activeBtn.previousElementSibling.classList.remove("change_pw_active");
    }
}

// 이메일 유효성 검사
function validateEmail(input) {
    var regex = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    if (regex.test(input.value)) {
        input.setCustomValidity("");
    } else {
        input.setCustomValidity("올바른 이메일 주소 형식을 입력해주세요.");
    }
}