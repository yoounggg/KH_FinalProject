// 페이지 로드 완료 후 이벤트 리스너 실행 -> find_id_p_btn과 find_id_e_form에 클릭 이벤트 리스터 추가됨!
document.addEventListener("DOMContentLoaded", function() {
    
    // "find_id_p_btn" 버튼 클릭 이벤트 리스너 추가
    document.getElementById("find_id_p_btn").addEventListener("click", function() {
        switchForm("find_id_p_form", "find_id_e_form", this);
    });

    // "find_id_e_btn" 버튼 클릭 이벤트 리스너 추가
    document.getElementById("find_id_e_btn").addEventListener("click", function() {
        switchForm("find_id_e_form", "find_id_p_form", this);
    });

    // ".find_id_form .val_button" 클래스를 가진 요소에 클릭 이벤트 리스너 추가
    document.querySelectorAll(".find_id_form .val_button").forEach(function(btn) {
        btn.addEventListener("click", function(e) {
            
		// 기본 클릭 이벤트 차단
		e.preventDefault();

        // 현재 버튼이 속한 양식 찾기
        var form = this.closest(".find_id_form");
        
            // 인증번호 입력 필드 생성
            var verificationInput = document.createElement("input");
            verificationInput.setAttribute("type", "text");
            verificationInput.setAttribute("placeholder", "인증번호를 입력해주세요.");
            verificationInput.setAttribute("required", "");
            verificationInput.classList.add("verification-input");

            // 카운트다운 요소 생성
            var countDown = document.createElement("div");
            countDown.classList.add("countdown");

            // 인증번호 입력 필드와 카운트다운 요소를 포함하는 요소 생성
            var countdownWrapper = document.createElement("div");
            countdownWrapper.classList.add("countdown-wrapper");
            countdownWrapper.appendChild(verificationInput);
            countdownWrapper.appendChild(countDown);

            // 인증번호 입력 필드와 카운트다운 요소를 양식에 추가
            form.insertBefore(countdownWrapper, this);

            // "확인" 버튼 생성 및 양식에 추가
            var confirmButton = document.createElement("button");
            confirmButton.setAttribute("type", "submit");
            confirmButton.textContent = "확인";
            confirmButton.classList.add("val_button");

            form.insertBefore(confirmButton, this);

            // 기존 버튼 제거
            this.remove();

            // 카운트다운 로직
            var timeLeft = 180;	// 제한시간 3분
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

// 두 개의 양식 사이를 전환하는 함수
function switchForm(showFormId, hideFormId, activeBtn) {
    // 전달받은 ID를 가진 요소를 보이게 하거나 숨김
    document.getElementById(showFormId).style.display = "block";
    document.getElementById(hideFormId).style.display = "none";

    // 클릭한 버튼에 "find_id_active" 클래스 추가 및 인접한 다른 버튼에서 "find_id_active" 클래스 제거
    activeBtn.classList.add("find_id_active");
    if (activeBtn.nextElementSibling) {
        activeBtn.nextElementSibling.classList.remove("find_id_active");
    } else {
        activeBtn.previousElementSibling.classList.remove("find_id_active");
    }
}

