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
    

    <%@include file="/WEB-INF/views/common/favicon.jsp" %>

    <link rel="stylesheet" href="/resources/css/order/orderDetail.css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">

    <script src="https://kit.fontawesome.com/1fe7ba446e.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>

</head>
<body>

	<%@include file= "../common/header.jsp" %>

<main>
    <aside class="userinfoAside">
        <ul class="box1">
            <img src="/resources/imgs/profileimg.jpg" alt="프로필 사진">
            <li class="small1">${member.name} 님</li>
            <li class="small1"><a href="/mypage/userInfo/${member.id}"><i class="fab fa-whmcs"></i>회원정보관리</a></li>
        </ul>

        <ul class="box2">
            <li class="small2 small3 small4"><a href="/mypage/userInfo/${member.id}">정보수정</a></li>
            <li class="small2 small3 small4"><a href="/mypage/userInfo/${member.id}/changePw">비밀번호 변경</a></li>
            <li class="small2 small3 small4"><a href="/mypage/orderList/${member.id}">주문내역</a></li>
            <li class="small2 small5"><a href="/mypage/deliveryConfirm/${member.id}">배송현황</a></li>
        </ul>
    </aside>
    
    <div class="wrapper">

        <h2>상세주문내역</h2>
        <div class="line"></div>

        <div class="order-details">

            <div class="product-info">
                <div class="상품사진">
                    <img src="imgs/상품이미지.jpg" alt="주문상품사진" class="주문상품사진">
                </div>

                <div class="상품정보">
                    <div class="product-name"> 상품명</div>   
                    <div class="option"> 옵션</div> 
                    <div class="price"> 가격</div> 
                    <div class="payment-date"> 결제일</div> 
                </div>

            </div>

        </div> <!-- ========== 상세 주문 내역1 ============ -->

        <div class="order-details2">

            <div class="product-info2">
                <div class="상품사진">
                    <img src="imgs/상품이미지.jpg" alt="주문상품사진" class="주문상품사진2">
                </div>

                <div class="상품정보2">
                    <div class="product-name2"> 상품명</div>   
                    <div class="option2"> 옵션</div> 
                    <div class="price2"> 가격</div> 
                    <div class="payment-date2"> 결제일</div> 
                </div>

            </div>

        </div> <!-- ============== 상세 주문 내역2 ============ -->

        <div class="line2"></div>

        <h2>배송 정보</h2>

        <div class="delivery-info">
            <div class="수령인">
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
        </div> <!-- ============ 배송정보 ==========  -->

        <div class="line3"></div>

        <div class="order-info">
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
        </div> <!-- ========== 결제정보 ============ -->

        <div class="line4"></div>

        <div class="order-detail-info">
            
            <h2>결제상세</h2>
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

    </div> <!-- ============ wrapper ==========  -->
    

</main>

  	<%@include file= "../common/footer.jsp" %>
</body>

</html>