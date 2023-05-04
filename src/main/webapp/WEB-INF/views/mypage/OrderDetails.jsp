<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MOYAMOGA</title>

	<!-- 파비콘 -->
    <link rel="shortcut icon" href="/resources/ico/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/ico/favicon.ico" type="image/x-icon">
    
	<!-- 주문 내역 상세 css -->
    <link rel="stylesheet" href="/resources/css/mypage/OrderDetails.css">
    
	<!-- 주문 내역 상세 js -->
    <!-- <script src="/resources/js/mypage/derDetails.js"></script>  -->

</head>

<body>

	<!-- header -->
	<%@include file= "../common/header.jsp" %>

    <main>
	    <!-- side bar 메뉴 -->
	    <aside class="userinfoAside">
	        <ul class="box1">
	            <img src="/resources/imgs/profileimg.jpg" alt="프로필 사진">
	            <li class="small1">${details.name} 님</li>
	            <li class="small1"><a href="/mypage/userInfo/${member.id}"><i class="fab fa-whmcs"></i>회원정보관리</a></li>
	        </ul>
	
	        <ul class="box2">
	            <li class="small2 small3 small4"><a href="/mypage/userInfo/${member.id}">정보수정</a></li>
	            <li class="small2 small3 small4"><a href="/mypage/userInfo/${member.id}/changePw">비밀번호 변경</a></li>
	            <li class="small2 small3 small4"><a href="/mypage/?">주문내역</a></li>
	            <li class="small2 small5"><a href="/mypage/?">배송현황</a></li>
	        </ul>
	    </aside>
	    
    <!-- 주문 내역 상세 컨텐츠 전체 wrapper -->
    <div class="OrderDetailsWrapper">

        <!-- 상세주문내역 -->
        <div class="OrderDetailsContainer1">

            <!-- 상세주문내역타이틀 -->
            <div class="OrderDetailsTitle1">상세주문내역</div>
            <!-- 구분선 -->
            <div class="line1"></div>

            <!-- 상세주문내역 contents 조절용 -->
            <div class="order_details1">

                <!-- 주문 상품 사진 -->
                <div class="product_photo">
                    <img src="imgs/상품이미지.jpg" alt="주문상품사진" class="주문상품사진">
                </div>

                <!-- 해당 상품에 대한 정보 -->
                <div class="product_info">
                    <div class="product_name"> 상품명</div>   
                    <div class="option"> 옵션</div> 
                    <div class="price"> 가격</div> 
                    <div class="payment_date"> 결제일</div> 
                </div>

            </div> 

        </div>


        <!-- 배송 정보 -->
        <div class="OrderDetailsContainer2">
            
            <!-- 배송 정보 타이틀 -->
            <div class="OrderDetailsTitle2">배송 정보</div>
            <!-- 구분선 -->
            <div class="line2"></div>

            <!-- 배송 정보 contents 조절용 -->
            <div class="order_details2">

                <div class="receiver_info">
                    <div id="delivery-title1">수령인</div>
                    <div id="수령인이름">셍나</div>
                </div>

                <div class="연락처">
                    <div id="delivery-title2">연락처</div>
                    <div id="휴대폰번호">010-01010-01010</div>
                </div>

                <div class="배송지">
                    <div id="delivery-title3">배송지</div>
                    <div id="주소123">
                        <div id="주소1">우편번호</div>
                        <div id="주소2">주소</div>
                        <div id="주소3">상세주소</div>
                    </div>
                </div>

                <div class="배송메모">
                    <div id="delivery-title4">배송메모</div>
                    <div id="배송메모데이터">배송메모데이터불러오기</div>
                </div>

            </div> 

        </div>


        <!-- 결제 정보 -->
        <div class="OrderDetailsContainer3">

            <!-- 결제 정보 타이틀 -->
            <div class="OrderDetailsTitle3">결제 정보</div>
            <!-- 구분선 -->
            <div class="line3"></div>

            <!-- 결제 정보 contents 조절용 -->
            <div class="order_details3">

                <div class="최초주문금액">
                    <div id="order-title1">최초 주문금액</div>
                    <div id="주문금액">100,000원</div>
                </div>
                <div class="상품금액">
                    <div id="order-title2">상품금액</div>
                    <div id="order-title2-1">98,500원</div>
                </div>
                <div class="배송비">
                    <div id="order-title3">배송비</div>
                    <div id="order-title3-1">+2,500원</div>
                </div>
                <div class="상품할인">
                    <div id="order-title4">상품 할인</div>
                    <div id="order-title4-1">-1,000원</div>
                </div>

            </div>
            
        </div>


        <!-- 결제 상세 -->
        <div class="OrderDetailsContainer4">

            <!-- 결제 상세 타이틀 -->
            <div class="OrderDetailsTitle4">결제 상세</div>
            <div class="line4"></div>

            <!-- 결제 상세 contents 조절용 -->
            <div class="order_details4">

                <div class="결제상세1">
                    <div class="order-detail-title1">카드 간편결제</div>
                    <div class="금액"> 100,000원</div> <!--위 최초 주문금액 가져와야할듯-->
                </div>

                <div class="결제상세2">
                    <div class="order-detail-title2">비씨(5555-****-****-****)</div>
                    <div class="결제방법">일시불</div>
                </div>

                <div class="카드사정책">
                    <div> · 카드에 BC 카드가 없는 경우 무이자 할부 적용 불가</div>
                    <div> · 무이자 적용 여부는 카드사로 문의하시면 정확하게 확인할 수 있습니다. </div>
                </div>
            </div>

        </div>
        
    </div>
    
    </main>
    
    <!-- footer -->
    <%@include file= "../common/footer.jsp" %>

	<!-- DOM이 완전히 로드되지 않은 상태에서 스크립트가 실행되는 것을 막기 위해 아래에다가 위치시키기! -->
    <!-- 주문 내역 상세 js -->
    <script src="/resources/js/mypage/OrderDetails.js"></script>
</body>

</html>