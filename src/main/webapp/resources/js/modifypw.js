//상단 nav 메뉴 고정

$(document).ready(function(){
   var menuOffset = $('.menu').offset();
   $(window).scroll(function(){
      if($(document).scrollTop() > menuOffset.top){
         $('.menu').addClass('fixedmenu').css('z-index', '999');
      } else{
         $('.menu').removeClass('fixedmenu').css('z-index', '');
         }
      });
});

//top banner
$(document).ready(function(){
    $(".topBannerBtn").on("click", function(){ // 버튼 클릭하면
      $(".topBanner").slideUp(); // 탑배너 사라지게 함
      });
    });
    
//**비밀번호 변경**
//1. 기존 비밀번호 일치 여부 확인 -> 해시암호화 했기 때문에 이 방법은 사용할 수 없음!
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
 function newPwforDetails(){
 	const newPw = document.querySelector('#newPw');
	
 	const isOk = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,16}$/;    //대소문자 구분 숫자8~16
	
 	if(isOk.test(newPw.value)){
 		alert("사용할 수 있는 비밀번호 입니다")
 	} else {
 		alert("비밀번호를 조건에 맞게 다시 입력해주세요")
 	} // if else
 }; // newPwforDetails

// //3. 변경한 비밀번호 다시 입력해서 일치하는지 확인
 function confirmNewPw(){
 	const newPw = document.querySelector('#newPw');
 	const checkNewPw = document.querySelector('#checkNewPw');
	
 	if(newPw.value == checkNewPw.value ){
 		alert("비밀번호가 일치합니다")
 	}else{
 		alert("비밀번호가 불일치합니다")
 	} // if-else
 }; //  confirmNewPw
 
 // 현재 비밀번호 칸에 입력을 안하고 신규 비밀번호 칸으로 넘어갈 수 없게 하기
 function checkInput(){
	let firstinput = document.getElementById("password1");
	if(firstinput.value === ""){
		alert("현재 비밀번호 확인을 먼저 해주세요.");
		firstinput.focus();
	}// if

} // checkInput


