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

// ======================================================
    // 아이디 찾기 - 휴대폰 인증 ajax
    
    // 1) 아이디 중복 검사
    // 휴대폰 인증 (아이디 찾기 버튼) 클릭 이벤트 - 이름, 전화번호로 아이디 중복 검사 실행
    // 아이디 존재하는 경우에 인증이 가능함.
    $(".findid_button_p").click(function (event) {

        event.preventDefault();

        var name = $("#find_id_p_form input[type=text]").val();
        var tel = $("#find_id_p_form input[type=tel]").val();

        // 사용자가 입력창에 값을 모두 입력해야만 함!
        if (!name || !tel) {
            alert("입력값을 모두 입력해주세요.");
            return;
        } // if

        // 입력값이 존재하면 ajax로 서버에 전송
        $.ajax({
            url: "/login/findid/idCheck", // 서버의 경로
            type: "POST", // POST 방식으로 전송
            data: {
                name: name,
                tel: tel
            },
            dataType: 'json',
            success: function (cntIdCheck) {
                console.log(cntIdCheck);

                cntIdCheck = parseInt(cntIdCheck); // 숫자형으로 변환

                if (cntIdCheck === 1) {    // 이름, 전화번호 존재! -> MemberMapper.xml의 idCheck()를 통해 1 반환함 = 아이디가 존재함
                    // 성공했을 경우
                    $(".p_verification").css("display", "block");
                    alert("회원정보를 확인했습니다. 휴대폰 번호 인증을 진행해주세요.")
                    $(".findid_button_p").hide();
                    $(".send_verification_button_p").show();
                } else {
                    // 실패했을 경우
                    alert("회원정보를 찾지 못했습니다. 다시 확인해주세요.");
                } // else
            }, // success
            error: function (request, status, error) {
                console.log("code = " + request.status + " message = " + request.responseText + " error = " + error);
            } // error
        }); // ajax
    }); // click
    
    // 2) 해당 전화번호로 인증 번호 발송 및 번호 비교
    
	// 인증번호 발송 버튼 클릭 이벤트
	var validate_num = "";
	$(".send_verification_button_p").click(function () {
	
	    alert("인증번호가 발송되었습니다. \n인증번호를 확인해주세요.");

	    smsSend();                  // 인증 번호 전송함수 실행
	
	    var phone = $('#tel').val();
	
	    $.ajax({
	        type: "GET",
	        url: "/login/findid/telCheck?tel=" + tel,
	        cache: false,
	        success: function (randomNumber) {
	            if (randomNumber == "error") {
	                //에러 떴을때
	                ;;
	            } else {
	                console.log("문자전송")
	                random_num = randomNumber;                                      // 난수 저장
	                console.log("인증번호 : " + random_num);
	
	                // 타이머 시작
	                var timer = 180;
	                var countdown = setInterval(function () {
	                    if (timer > 0) {
	                        $("#timer").html(timer + "초");
	                        timer--;
	                    } else {
	                        clearInterval(countdown);
	                        $("#timer").html("인증시간이 초과되었습니다. 다시 시도해주세요.");
	                        $(".send_verification_button_p").prop('disabled', false);
	                        validate_num = "";
	                    }
	                }, 1000);
	                // 타이머 끝
	            } // else
	        } // function
	    });
	
	});
	
	// 휴대폰 인증 - 확인 버튼 클릭 이벤트
	$(".p_verify_button").on("click", function() {
	  num_compare();
	});

// ======================================================

    // 이메일 인증 (아이디 찾기 버튼) 클릭 이벤트 - 이름, 이메일로 아이디 중복 검사 실행
    // 아이디 존재하는 경우에 인증이 가능함.
	$(".findid_button_e").click(function (event) {

		event.preventDefault();

		var name = $("#find_id_e_form input[type=text]").val();
		var email = $("#find_id_e_form input[type=email]").val();

		// 사용자가 입력창에 값을 모두 입력해야만 함!
		if (!name || !email) {
			alert("입력값을 모두 입력해주세요.");
		return;
		} // if

		// 입력값이 존재하면 ajax로 서버에 전송
		$.ajax({
			url: "/login/findid/idCheck", // 서버의 경로
			type: "POST", // POST 방식으로 전송
			data: {
				name: name,
				email: email
			},
			dataType: 'json',
			success: function (cntIdCheck) {
				console.log(cntIdCheck);

				cntIdCheck = parseInt(cntIdCheck); // 숫자형으로 변환

				if (cntIdCheck === 1) {    // 이름, 이메일 존재! -> MemberMapper.xml의 idCheck()를 통해 1 반환함 = 아이디가 존재함
				// 성공했을 경우
 					$(".e_verification").css("display", "block");
					alert("회원정보를 확인했습니다. 이메일 인증을 진행해주세요.");
				} else {
				// 실패했을 경우
					alert("회원정보를 찾지 못했습니다. 다시 확인해주세요.");
 				} // else
			}, // success
			error: function (request, status, error) {
				console.log("code = " + request.status + " message = " + request.responseText + " error = " + error);
			} // error
		}); // ajax
	}); // click

	// 인증번호 발송 버튼 클릭 이벤트
	$(".send_verification_button_e").click(function () {
		// 이메일 인증 발송 버튼 클릭 이벤트를 여기에 작성하시면 됩니다.
	});

	// 이메일 인증 - 확인 버튼 클릭 이벤트
	$(".e_verify_button").click(function () {
		// 이메일 인증 확인 버튼 클릭 이벤트를 여기에 작성하시면 됩니다.
	});
});

var timer;

function smsSend() {
  var tel = $("#tel").val();

  // 문자 전송
  $.ajax({
    type: "get",
    url: "/login/findid/telCheck?tel=" + tel,
    cache: false,
    success: function (randomNumber) {
      if (randomNumber == "error") {
        //에러 떴을때
        ;;
      } else {
        console.log("문자전송")

        var random_num = randomNumber; // 지역 변수로 변경

        console.log("인증번호 : " + random_num);

        // 타이머 시작
        var count = 180; // 180초

        timer = setInterval(function () {
          count--;
          var minutes = Math.floor(count / 60); // 분 계산
          var seconds = count % 60; // 초 계산
          document.querySelector("#countdown").textContent = minutes + ":" + (seconds < 10 ? "0" : "") + seconds; // 카운트 다운 업데이트
          if (count == 0) {
            // 타이머 0일때
            clearInterval(timer); // 타이머 종료
            document.querySelector("#input_hp").readOnly = false; // 휴대폰 번호 입력창 읽기전용 풀기
            document.querySelector("#hp_confirm").disabled = false; // 인증하기 버튼 활성화
            document.querySelector("#hp_confirm").style.backgroundColor = "#10bc0d";
            popup_on9(); // 인증시간이 만료되었습니다.
            document.getElementById("num_form").style.display = "none"; // 인증번호 입력창 숨김
            document.getElementById("input_num").value = ""; // 인증번호 값 초기화
          }
        }, 1000); // 1초마다 타이머 작동

        return random_num; // 난수 반환
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