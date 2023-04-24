//상단 nav 메뉴 고정
$(document).ready(function(){
    var menuOffset = $('.menu').offset();
    $(window).scroll(function(){
      if($(document).scrollTop() > menuOffset.top){
        $('.menu').addClass('fixedmenu');
      } else{
        $('.menu').removeClass('fixedmenu');
      }
    });
  });


//top banner
$(document).ready(function(){
    $(".topBannerBtn").on("click", function(){ // 버튼 클릭하면
      $(".topBanner").slideUp(); // 탑배너 사라지게 함
      });
    });
    
// 카카오 주소 api  
function kakaoAdress(){
	new daum.Postcode({
		oncomplete: function(data) {
	           
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 주소변수 문자열과 참고항복 문자열 합침
                    addr += extraAddr;
                
                } else {
                     addr += '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
		}
	}).open();
}

//**비밀번호 변경**
//1. 기존 비밀번호 일치 여부 확인
// function checkPw(){
// 	const pwInput = document.querySelector('#originPw');
// 	const pwHiddenInput = document.querySelector('#checkOringinPw');
	
// 	if(pwInput.value == pwHiddenInput.dataset.password ){
// 		alert("비밀번호가 일치합니다")
// 	} else{
// 		alert("비밀번호가 불일치합니다")
// 	} // if-else
// }; // 

// //2. 변경할 비밀번호 유효성 검사
// function newPwforDetails(){
// 	const newPw = document.querySelector('#newPw');
	
// 	const isOk = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,16}$/;    //대소문자 구분 숫자8~16
	
// 	if(isOk.test(newPw.value)){
// 		alert("사용할 수 있는 비밀번호 입니다")
// 	} else {
// 		alert("비밀번호를 조건에 맞게 다시 입력해주세요")
// 	} // if else
// }; // newPwforDetails

// //3. 변경한 비밀번호 다시 입력해서 일치하는지 확인
// function confirmNewPw(){
// 	const newPw = document.querySelector('#newPw');
// 	const checkNewPw = document.querySelector('#checkNewPw');
	
// 	if(newPw.value == checkNewPw.value ){
// 		alert("비밀번호가 일치합니다")
// 	}else{
// 		alert("비밀번호가 불일치합니다")
// 	} // if-else
// }; //  confirmNewPw


//회원 정보 수정 form-> submit
function goform(){
	$('#userUpdateForm').attr("action",'/mypage/userInfo/update').submit();
}; // goform

//휴대폰 번호 인증
// var code2 = "";
// $('#telCk').click(function({id}){
// 	alert("인증번호가 발송되었습니다. \n휴대폰에서 인증번호를 확인해주세요.");
// 	var phone = $('#tel').val();
// 	$.ajax({
// 		type: 'GET',
// 		url:'/mypage/userInfo/'+'${id}'+'/phoneCheck?tel='+'${tel}',
// 		cache: false,
// 		success:function(data){
// 			if(data == 'error'){
// 				alert("휴대폰 번호가 올바르지 않습니다.")
// 				$('.successTelCk').text("유효한 번호를 입력해주세요.");
// 				$('.successTelCk').css("color", "red");
// 				$('#tel').attr("autofocus", true);
// 			} else {
// 				$('#tel2').attr("disabled", false);
// 				$('#tel2Chk').css("display", "inline-block");
// 				$('.successTelCk').text("인증번호를 입력한 뒤 본인인증을 눌러주세요.");
// 				$('.successTelCk').css("color", "green");
// 				$('#tel').attr("readonly", true);
// 				code2 = data;
// 			} // else
// 		} // function
// 	}); // ajax
// }); // function

// //휴대폰 인증번호 대조
// $('#tel2Chk').click(function(){
// 	if($('#tel2').val() == code2){
// 		$('.successTelCk').text("인증번호가 일치합니다.");
// 		$('.successTelCk').css("color", "green");
// 		$('#telDoubleChk').val("true");
// 		$('tel2').attr("disabled", true);
// 	} else{
// 		$('.successTelCk').text("인증번호가 일치하지 않습니다. 확인해주시기 바랍니다");
// 		$('.successTelCk').css("color", "red");
// 		$('#telDoubleChk').val("false");
// 		$(this).attr("autofocus", true);
// 	}
// });
      