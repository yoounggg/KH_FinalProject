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

new daum.Postcode({
	oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
	}
}).open();

      