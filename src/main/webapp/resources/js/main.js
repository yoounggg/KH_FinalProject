// 비동기식 로그아웃!
$("#gnb_logout").click(function() {
	// alert("로그아웃 버튼 활성화")
		$.ajax({
    		type:"POST",
    		url:"/login/logout",
    		success:function(data){
    			// alert("비동기식 로그아웃 성공");
    			document.location.reload();
    	}
    }); // ajax
});
    
//=====================================================================

//top banner
$(document).ready(function(){
    $(".topBannerBtn").on("click", function(){ // 버튼 클릭하면
      $(".topBanner").slideUp(); // 탑배너 사라지게 함
      });
    });
    
    
//=====================================================================
    
// 메인 슬라이드 배너 slick js
$(document).ready(function(){// 'html문서가 준비되면 할 일' 이라는 의미임 !
    $('.slides').slick({
          //slick에서 제공하는 화살표 끄기
          arrows:false,
    
          centerMode: true,
          centerPadding: '60px',
          slidesToShow: 3,
          // autoplay        
          slidesToScroll: 1,
          autoplay: true,
          autoplaySpeed: 2000,
    
          responsive: [
              {
              breakpoint: 768,
              settings: {
                  arrows: false,
                  centerMode: true,
                  centerPadding: '40px',
                  slidesToShow: 3
              }
              },
              {
              breakpoint: 480,
              settings: {
                  arrows: false,
                  centerMode: true,
                  centerPadding: '40px',
                  slidesToShow: 1
              }
              }
          ]
      });
    
// Manually refresh positioning of slick
//화살표를 눌렀을 때 이전, 다음으로 넘어가게!!
// 제이쿼리에서 요소를 선택할 때는-> $(css선택자)
// 여기서 선택한거에 click이라는 이벤트를 연결함
// -> 이 이벤트가 생기면 할 일을 function에 집어넣음
$('.prev-btn').click(function(){
	$('.slides').slick('slickPrev');
});
    
$('.pause-btn').click(function(){
	$('.slides').slick('slickPause');
});
    
$('.play-btn').click(function(){
	$('.slides').slick('slickPlay');
});
    
$('.next-btn').click(function(){
	$('.slides').slick('slickNext');
});
    
});
    
// ==========================================
    
//상단 nav 메뉴 고정
    
// $(document).ready(function(){
// 	var menuOffset = $('.menu').offset();
// 	$(window).scroll(function(){
// 		if($(document).scrollTop() > menuOffset.top){
// 			$('.menu').addClass('fixedmenu');
// 		} else{
// 			$('.menu').removeClass('fixedmenu');
// 			}
// 		});
// });
    
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
//==================================================
    
//로그인 하지 않으면 장바구니 이용불가능함 -> 알람창    
$('.nullcart').click(function(){
	alert('로그인 후 이용바랍니다.');
});
    
//==================================================

//top버튼
$(window).scroll(function(){
    $("#maintopbtn").css("margin-top",Math.max(50,0-$(this).scrollTop()));
});
    $(function() {
        $(window).scroll(function() {
            if ($(this).scrollTop() > 500) {
                $('#maintopbtn').fadeIn();
            } else {
                $('#maintopbtn').fadeOut();
            }
        });
        
        $("#maintopbtn").click(function() {
            $('html, body').animate({
                scrollTop : 0
            }, 400);
            return false;
        });
    });