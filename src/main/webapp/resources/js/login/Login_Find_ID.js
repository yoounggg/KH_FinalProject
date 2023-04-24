$(document).ready(function () {

    // 휴대폰 인증 버튼을 클릭했을 때
    $("#find_id_p_btn").click(function () {
        $(this).addClass("find_id_active");
        $("#find_id_e_btn").removeClass("find_id_active");
        $("#find_id_p_form").css("display", "block");
        $("#find_id_e_form").css("display", "none");
    });

    // 이메일 인증 버튼을 클릭했을 때
    $("#find_id_e_btn").click(function () {
        $(this).addClass("find_id_active");
        $("#find_id_p_btn").removeClass("find_id_active");
        $("#find_id_e_form").css("display", "block");
        $("#find_id_p_form").css("display", "none");
    });

    // 아이디 찾기 - 휴대폰 인증 ajax
    $(".findid_button_p").click(function (event) {

        event.preventDefault();

        var name = $("#find_id_p_form input[type=text]").val();
        var tel = $("#find_id_p_form input[type=tel]").val();

        if (!name || !tel) {
            alert("입력값을 모두 입력해주세요.");
            return;
        }

        $.ajax({
            url: "/login/findid/idCheck",
            type: "POST",
            data: {
                name: name,
                tel: tel
            },
            dataType: 'json',
            success: function (cntIdCheck) {
                cntIdCheck = parseInt(cntIdCheck);

                if (cntIdCheck === 1) {
                    $(".p_verification").css("display", "block");
                    alert("회원정보를 확인했습니다. 휴대폰 번호 인증을 진행해주세요.")
                    $(".findid_button_p").hide();
                    $(".send_verification_button_p").show();
                } else {
                    alert("회원정보를 찾지 못했습니다. 다시 확인해주세요.");
                }
            },
            error: function (request, status, error) {
                console.log("code = " + request.status + " message = " + request.responseText + " error = " + error);
            }
        });
    });

    var random_num = "";

    $(".send_verification_button_p").click(function () {
        alert("인증번호를 인증번호 입력창에 입력해주세요.");

        // 인증번호 입력창을 표시하도록 변경
        $(".p_verification").css("display", "block");

        clearInterval(timerInterval);
        timeLeft = 180;

        // 아래에서 smsSend() 함수 호출을 추가
        smsSend();

        timerInterval = setInterval(function() {
            timeLeft--;

            var minutes = Math.floor(timeLeft / 60);
            var seconds = timeLeft % 60;

            var timerElement = document.createElement('div');
            timerElement.classList.add('timer');

            timerElement.innerHTML = minutes + "분 " + seconds + "초";

            var verificationElement = document.querySelector('.p_verification');
            if (verificationElement) {
                if (verificationElement.querySelector('.timer')) {
                    verificationElement.removeChild(verificationElement.querySelector('.timer'));
                }
                verificationElement.appendChild(timerElement);
            }

            if (timeLeft <= 0) {
                clearInterval(timerInterval);
            }
        }, 1000);
    });


    function smsSend() {
        var tel = $("#tel").val();

        $.ajax({
            type: "get",
            url: "/login/findid/telCheck?tel=" + tel,
            cache: false,
            success: function (randomNumber) {
                if (randomNumber == "error") {
                } else {
                    console.log("문자전송")

                    random_num = randomNumber;

                    console.log("인증번호 : " + random_num);

                    return random_num;
                }
            },
        });
    }

    function num_compare() {
        if ($("#p_verification_input").val() == random_num) {           
            alert("인증번호가 일치합니다.");                                   
            clearInterval(timer);                           
        } else if ($("#p_verification_input").val() != random_num) {   
            alert("인증번호가 일치하지 않습니다.");                                      
        }
    };

    // 휴대폰 인증 - 확인 버튼 클릭 이벤트
    $(".p_verify_button").on("click", function() {
        num_compare();
    });

});
