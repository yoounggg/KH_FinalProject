<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MOYAMOGA</title>

    <%@include file="/WEB-INF/views/common/favicon.jsp" %>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

    <link rel="stylesheet" href="../resources/css/help/write.css">

    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,100,1,-25" />

    <script src="https://kit.fontawesome.com/1fe7ba446e.js" crossorigin="anonymous"></script>

    <!-- slick: cdn 방식으로 css, js 가져오기 -->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
    <script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>

    <script src="../resources/js/help/write.js"></script>
</head>

<body>
<div id="entire">
<!-- header -->
<%@include file="/WEB-INF/views/common/header.jsp" %>

    <main>
        <div class="help_wrap">
            <!-- 상단 고객센터 -->
            <div class="help_top">
                <div class="help_center">
                    <p class="help_center_text">고객센터</p>
                </div>
                <div class="help"></div>
            </div>

            <!-- 중간 - notice 사이드 메뉴/ 게시물 -->
            <div class="help_content_wrap">

                <!-- 중간 - notice 사이드 메뉴 -->
                <div class="help_side_menu">
                    <ul class="sm_ul">
                        <li id="sm_li"><a href="/help/faq">FAQ</a></li>
                        <li id="sm_li"><a href="/help/notice">공지사항</a></li>
                        <li id="sm_li"><a href="/help/guide">이용안내</a></li>
                        <%-- 로그인이 되어 있는 경우 --%>
						<c:if test="${not empty sessionScope.member}">
							<li id="sm_li"><a href="/help/question">1:1문의</a></li>
						</c:if>

						<%-- 로그인이 되어 있지 않은 경우 --%>
						<c:if test="${empty sessionScope.member}">
							<li id="sm_li"><a href="/login/main">1:1문의</a></li>
						</c:if>
                    </ul>

                </div>
                <form action="#" method="post" id="form">

                    <!-- 중간 - 게시물 -->
                    <div class="content_wrap">
                        <h2>1:1문의<span class="test">문의하신 내용은 영업일 기준 5일이내에 답변해드립니다</span></h2>
                        
                            <select id="box" name="type">
                                <option value="선택안함">문의유형</option>
                                <option value="배송"> 배송관련 문의 </option>
                                <option value="상품"> 상품관련 문의 </option>
                                <option value="개인정보"> 개인정보관련 문의 </option>
                                <option value="교환/환불"> 교환 및 환불관련 문의 </option>
                                <option value="기타"> 기타 </option>
                            </select>

                              <input id="ttle" type="text" placeholder="제목을 입력해주세요 최대 100자" name="title" required>
                              
                              <br>

                              <textarea id="con" placeholder="내용을 입력해주세요" name="content" minlength="10" required></textarea><br>

                            <div class="btm_btn">
                                <button type="button" id="enroll" class="write">문의하기</button>
                    
                                <div>
                                    <a href="/help/question""><span class="back">뒤로가기</span></a>
                                </div>
                                
                            </div>
                    </div>
                  
                </form>
            </div>
    </main>

    
    <!-- ==========================footer========================= -->
    <%@include file="/WEB-INF/views/common/footer.jsp" %>
</div>
    
    <div class="popmenu1" id="common_pop">
        <p>제목은 100자 이내로 입력해주세요</p>
        <input type="button" class="exit1" id="common_btn" onclick="exit1()" value="확인">
    </div>

    <div class="popmenu2" id="common_pop">
        <p>내용은 2000자 이내로 입력해주세요</p>
        <input type="button" class="exit2" id="common_btn" onclick="exit2()" value="확인">
    </div>
</body>

<script>

$(document).ready(function () {

$("#enroll").click(function () {
  const titleInput = document.getElementById('ttle');
  const titleLength = titleInput.value.trim().length;
 
  
    const con = document.getElementById("con")            //내용
    const conLength = con.value.trim().length;


    if (titleLength > 100) {                         // 제목
        popup_on1();
        
    } else if (conLength > 2000) {                  // 내용 
        popup_on2();
       
    } else {
    	console.log("else");
    	 var form = $('form');
        $("#form").attr("action", "/help/write");        
        $("#form").submit();
    }
});
});

// 팝업 1 제목 길이 확인
function popup_on1() {
$(".popmenu1").show();
popup_blur1(true);
};

function exit1() {
$(".popmenu1").hide();
popup_blur1(false);
};

function popup_blur1(chk) {
if (chk === false)
    $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
else
    $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};



// 팝업 2 내용 확인
function popup_on2() {
$(".popmenu2").show();
popup_blur2(true);
};

function exit2() {
$(".popmenu2").hide();
popup_blur2(false);
};

function popup_blur2(chk) {
if (chk === false)
    $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
else
    $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};

</script>

</html>