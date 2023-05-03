$(document).ready(function () {

    // 휴대폰 인증 버튼을 클릭했을 때
    $("#find_id_p_btn").click(function () {
		// 버튼 스타일 및 폼의 display 변경
        $(this).addClass("find_id_active");
        $("#find_id_e_btn").removeClass("find_id_active");
        $("#find_id_p_form").css("display", "block");
        $("#find_id_e_form").css("display", "none");
    });

    // 이메일 인증 버튼을 클릭했을 때
    $("#find_id_e_btn").click(function () {
		// 버튼 스타일 및 폼의 display 변경
        $(this).addClass("find_id_active");
        $("#find_id_p_btn").removeClass("find_id_active");
        $("#find_id_e_form").css("display", "block");
        $("#find_id_p_form").css("display", "none");
    });

// -----------------------------------------------------

    /* 아이디 찾기 - 휴대폰 인증 ajax 구현 */

    $(".findid_button_p").click(function (event) {
		
		// 페이지 새로고침 방지
        event.preventDefault();

		// 사용자 입력값 받기 및 입력값 확인
        var name = $("#find_id_p_form input[type=text]").val();
        var tel = $("#find_id_p_form input[type=tel]").val();
	
		// 사용자가 입력값을 모두 입력할 수 있도록, 입력값이 하나라도 없는 경우에는 알람
        if (!name || !tel) {
            alert("입력값을 모두 입력해주세요.");
            
            return;
        } // if

		// DB에 회원 정보가 존재하는지 확인하는 ajax 요청!
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
                    alert("회원정보를 확인했습니다. \n인증번호 발송 버튼을 눌러, 휴대폰 번호 인증을 진행해주세요.")
                    $(".findid_button_p").hide();
                    $(".send_verification_button_p").show();
                } else {
                    alert("회원정보를 찾지 못했습니다. 다시 확인해주세요.");
                }
            },
            error: function (request, status, error) {
                console.log("code = " + request.status + " message = " + request.responseText + " error = " + error);
            }
        }); // ajax
    }); // // 아이디 찾기 버튼 클릭


	// 인증 번호 발송 버튼 클릭 이벤트 - 휴대폰
	var val_num = ""; // 인증번호를 저장할 변수
    $(".send_verification_button_p").click(function () {
        alert("인증번호를 인증번호 입력창에 입력해주세요.");
        
        // 인증 번호 발송 버튼 클릭 이후 인증번호 입력창, 확인 버튼을 표시하도록 변경
        $(".p_verification_input").css("display", "block");
        $(".p_verify_button").css("display", "block");
		
		var phone = $("#tel").val();
		
		// 인증 번호 발송을 위한 ajax 요청
        $.ajax({
            type: 'GET',
            url:'/login/findid/telCheck' + phone,
            data: {
				tel: phone
			},
            cache: false,
            success:function(data){
                if(data == 'error'){
                    alert("휴대폰 번호가 올바르지 않습니다.")
                    $('#p_verification_input').attr("autofocus", true);
                } else {
                    $('#p_verification_input').attr("readonly", true);
                    val_num = data;
                } // if-else
            } // success:fn()
        }); // ajax
    }); // 인증 번호 발송 버튼 클릭 fn()
	
    // 휴대폰 인증번호 대조
    $('.p_verify_button').click(function(){
        if($('.p_verification_input').val() == val_num){
            alert("인증번호가 일치합니다.")
            $(".p_verify_button").hide();
            $(".p_verify_button_result").show();
        } else {
            alert("인증번호가 일치하지 않습니다. 인증 절차를 다시 시도해주세요.")
        } // if-else
    }); // 휴대폰 인증 번호 대조 fn()
    
    // 확인 버튼 클릭
	$('.p_verify_button_result').click(function(){
	    var name = $("#find_id_p_form input[type=text]").val();
	    var tel = $("#find_id_p_form input[type=tel]").val();
	    
	    // 사용자 이름과 휴대폰 번호를 이용해 찾은 아이디를 표시하는 ajax 요청
	    $.ajax({
	        url: '/login/findid/result',
	        type: 'POST',
	        data: {
	            name: name, // 찾을 이름 값
	            tel: tel // 찾을 전화번호 값
	        },
	        dataType: 'text',
	        success: function(idResult) {
	            // 결과 값을 보여줄 영역에 결과 값을 설정 (예: 알림창, 팝업, 모달 등)
	            alert("찾은 아이디: " + idResult);
	            $(".p_verify_button_result").hide();
	            // 기존 아이디 찾기 폼 다 숨기고 로그인 하러 가기 버튼 만들기
	            location.href = "/main";
	        },
	        error: function(xhr, status, error) {
	            console.error(error);
	        }
	    });
	    
	}); // 확인 버튼 클릭 fn()
	
// -----------------------------------------------------
    
    /* 아이디 찾기 - 이메일 인증 ajax 구현 */
    
    $(".findid_button_e").click(function (event) {
		
		// 페이지 새로고침 방지
        event.preventDefault();

		// 사용자 입력값 받기 및 입력값 확인
        var name = $("#find_id_e_form input[type=text]").val();
        var email = $("#find_id_e_form input[type=email]").val();
	
		// 사용자가 입력값을 모두 입력할 수 있도록, 입력값이 하나라도 없는 경우에는 알람
        if (!name || !email) {
            alert("입력값을 모두 입력해주세요.");
            return;
        }

		// DB에 회원 정보가 존재하는지 확인하는 ajax 요청!
        $.ajax({
            url: "/login/findid/idCheck_e",
            type: "POST",
            data: {
                name: name,
                email: email
            },
            dataType: 'json',
            success: function (cntIdCheck_e) {
                cntIdCheck_e = parseInt(cntIdCheck_e);

                if (cntIdCheck_e === 1) {
                    $(".e_verification").css("display", "block");
                    alert("회원정보를 확인했습니다. \n인증번호 발송 버튼을 눌러, 이메일 인증을 진행해주세요.")
                    $(".findid_button_e").hide();
                    $(".send_verification_button_e").show();
                } else {
                    alert("회원정보를 찾지 못했습니다. 다시 확인해주세요.");
                } // if-else
            }, 
            error: function (request, status, error) {
                console.log("code = " + request.status + " message = " + request.responseText + " error = " + error);
            } // 회원 정보 확인 fn()
        }); // ajax
    }); // 아이디 찾기 버튼 클릭 fn()
    
    // 인증 번호 발송 버튼 클릭 이벤트
	var val_num = ""; // 인증번호를 저장할 변수
    $(".send_verification_button_e").click(function () {
        alert("인증번호를 인증번호 입력창에 입력해주세요.");
        
        // 인증 번호 발송 버튼 클릭 이후 인증번호 입력창, 확인 버튼을 표시하도록 변경
        $(".e_verification_input").css("display", "block");
        $(".e_verify_button").css("display", "block");
		
		var email = $("#find_id_e_form input[type=email]").val();
		
		// 인증 번호 발송을 위한 ajax 요청
        $.ajax({
            type: 'GET',
            url:'/login/findid/sendMail',
            data: {
				email: email
			},
            cache: false,
            success:function(data){
                if(data == 'error'){
                    alert("이메일 주소가 올바르지 않습니다.")
                    $('#e_verification_input').attr("autofocus", true);
                } else {
                    $('#e_verification_input').attr("readonly", true);
                    val_num = data;
                    console.log
                } // if-else
            } // success:fn()
        }); // ajax
    }); // 인증 번호 발송 버튼 클릭 fn()
    
        // 이메일 인증번호 대조
    $('.e_verify_button').click(function(){
        if($('.e_verification_input').val() == val_num){
            alert("인증번호가 일치합니다.")
            $(".e_verify_button").hide();
            $(".e_verify_button_result").show();
        } else {
            alert("인증번호가 일치하지 않습니다. 인증 절차를 다시 시도해주세요.")
        } // if-else
    }); // 이메일 인증 번호 대조 fn()
    
    // 확인 버튼 클릭
	$('.e_verify_button_result').click(function(){
	    var name = $("#find_id_e_form input[type=text]").val();
	    var email = $("#find_id_e_form input[type=email]").val();
	    
	    // 사용자 이름과 이메일 주소를 이용해 찾은 아이디를 표시하는 ajax 요청
	    $.ajax({
	        url: '/login/findid/result_e',
	        type: 'POST',
	        data: {
	            name: name, // 찾을 이름 값
	            email: email // 찾을 전화번호 값
	        },
	        dataType: 'text',
	        success: function(idResult_e) {
	            // 결과 값을 보여줄 영역에 결과 값을 설정 (예: 알림창, 팝업, 모달 등)
	            alert("찾은 아이디: " + idResult_e);
	            
	            $(".e_verify_button_result").hide();
	            // 기존 아이디 찾기 폼 다 숨기고 로그인 하러 가기 버튼 만들기
	            location.href = "/main";
	        },
	        error: function(xhr, status, error) {
	            console.error(error);
	        }
	    });
	    
	}); // 확인 버튼 클릭 fn()
    
}); // (document).ready(function () {})
