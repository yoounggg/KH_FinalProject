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
	
		// 사용자가 입력값을 모두 입력할 수 있도록, 입력값이 하나라도 없는 경우에는 알람
        if (!name || !tel) {
            alert("입력값을 모두 입력해주세요.");
            return;
        }

		// DB에 회원 정보가 존재하는지 확인
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

	// 인증 번호 발송 버튼 클릭 이벤트
	var val_num = "";
    $(".send_verification_button_p").click(function () {
        alert("인증번호를 인증번호 입력창에 입력해주세요.");

        // 인증 번호 발송 버튼 클릭 이후 인증번호 입력창, 확인 버튼을 표시하도록 변경
        $(".p_verification_input").css("display", "block");
        $(".p_verify_button").css("display", "block");
		
		var phone = $('#p_verification_input[type=text]').val();
		
		$.ajax({
		type: 'GET',
		url:'/login/findid/telCheck/' +'${tel}',
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
	
	//휴대폰 인증번호 대조
	$('.p_verify_button').click(function(){
		if($('.send_verification_button_p').val() == val_num){
			alert("인증번호가 일치합니다.")
		} else {
			alert("인증번호가 일치하지 않습니다.")
		}
	});
    
});
