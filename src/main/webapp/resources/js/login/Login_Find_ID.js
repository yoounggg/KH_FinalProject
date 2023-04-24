// 아이디 찾기 ajax

// 휴대폰 인증 (아이디 찾기 버튼) 클릭 이벤트 - 이름, 전화번호로 아이디 중복 검사 실행
// 아이디 존재하는 경우에 인증이 가능함.
$(".findid_button_p").click(function(event) {
	
	event.preventDefault(); // Add this lin
	
    var name = $("#find_id_p_form input[type=text]").val();
    var tel = $("#find_id_p_form input[type=tel]").val();

    // 사용자가 입력창에 값을 모두 입력해야만 함!
    if (!name || !tel) {
        alert("입력값을 모두 입력해주세요.");
        return;
    } // if
	
    // 입력값이 존재하면 ajax로 서버에 전송
    $.ajax ({
        url: "/login/findid/idCheck", // 서버의 경로
        type: "POST", // POST 방식으로 전송
        data: {
			name: name,
        	tel: tel
        },
        dataType: 'json',
        success: function(cntIdCheck) {
            console.log(cntIdCheck);
            
			cntIdCheck = parseInt(cntIdCheck); // 숫자형으로 변환
			
            if (cntIdCheck === 1) {	// 이름, 전화번호 존재! -> MemberMapper.xml의 idCheck()를 통해 1 반환함 = 아이디가 존재함
                // 성공했을 경우
                $(".p_verification").css("display", "block");
                alert("회원정보를 확인했습니다. 휴대폰 번호 인증을 진행해주세요.")
            } else {				// 일치하는 회원정보 X
                // 실패했을 경우
                alert("일치하는 회원 정보가 없습니다.");
            } // if-else
        },
        error: function(xhr, status, error) {
            console.log(xhr);
            console.log(status);
            console.log(error);
        }
    });
});

// 이메일 인증 버튼(인증 번호 발송) 클릭 이벤트
$(".findid_button_e").click(function() {
    var name = $("#find_id_e_form input[type=text]").val();
    var email = $("#find_id_e_form input[type=email]").val();

	// 사용자가 입력창에 값을 모두 입력해야만 함!
    if (!name || !email) {
        alert("입력값을 모두 입력해주세요.");
        return;
    }

    // 입력값이 존재하면 ajax로 서버에 전송
    $.ajax({
        url: "/send_verification_number", // 서버의 경로
        type: "POST", // POST 방식으로 전송
        data: {
            name: name,
            email: email,
            type: "e" // 인증 방식을 식별하는 type 파라미터 추가
        },
        success: function(response) {
            console.log(response);
            if (response.status === "success") {
                // 성공했을 경우
                $(".e_verification").css("display", "block");
            } else {
                // 실패했을 경우
                alert(response.message);
            }
        },
        error: function(xhr, status, error) {
            console.log(xhr);
            console.log(status);
            console.log(error);
        }
    });
});




