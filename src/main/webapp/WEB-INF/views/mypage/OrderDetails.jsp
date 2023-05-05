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
            <li class="small2 small3 small4"><a href="/mypage/orderList/${member.id}">주문내역</a></li>
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
                    <!-- DB에서 상품 이미지 불러오기 -> 어떻게 하쥐,, -->
                    <img src="/resources/imgs/profileimg.jpg" alt="주문상품사진" class="product_photo2">
                </div>

                <!-- 해당 상품에 대한 정보 -->
                <div class="product_info_container">

                    <div class="product_info">
                        <div id="product_title">상품명:</div>
                        <div id="product_name">* 상품명 * DB에서 불러오기</div>
                    </div>

                    <!-- 옵션 없어짐
                    <div class="product_info">
                        <div id="product_title">옵션:</div>
                        <div id="product_option">DB에서 불러오기</div>
                    </div>
                    -->

                    <div class="product_info">
                        <div id="product_title">가격:</div>
                        <div id="product_price">* 상품 가격 * DB에서 불러오기</div>
                    </div> 

                    <div class="product_info">
                        <div id="product_title">결제일:</div>
                        <div id="product_payment_date">* 결제일자 * DB에서 불러오기</div>
                    </div> 

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

                <!-- 수령인 -->
                <div class="delivery_info1">
                    <div id="delivery_title1">수령인</div>
                    <div id="receiver_name">* 수령인 이름 * DB에서 불러오기</div>
                </div>

                <!-- 연락처 -->
                <!-- 수령인 연락처인지 주문한 사람 연락처인지 모르겠음.. -->
                <div class="delivery_info2">
                    <div id="delivery_title2">연락처</div>
                    <div id="receiver_tel">* 수령인 연락처 * DB에서 불러오기</div>
                </div>

                <!-- 배송지 -->
                <div class="delivery_info3">
                    <div id="delivery_title3">배송지</div>
                    <div id="receiver_address">
                        <div id="receiver_address1">* 우편번호 * DB에서 불러오기</div>
                        <div id="receiver_address2">* 주소 * DB에서 불러오기</div>
                        <div id="receiver_address3">* 상세 주소 * DB에서 불러오기</div>
                    </div>
                </div>

                <!-- 배송메모 -->
                <div class="delivery_info4">
                    <div id="delivery_title4">배송메모</div>
                    <div id="delivery_memo">* 배송메모 * DB에서 불러오기</div>
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

                <div class="payment_amount_info">
                    <!-- 할인 안들어간 원래 상품 금액 -->
                    <div id="order_title">총 상품 가격</div>
                    <div id="total_product_amount">* 총 상품 가격 * DB에서 불러오기</div>
                </div>

                <div class="payment_amount_info">
                    <!-- 할인 얼마나 됐는지 금액 -->
                    <div id="order_title">할인 금액</div>
                    <div id="discount_amount">* 할인 금액 * DB에서 불러오기</div>
                </div>

                <div class="payment_amount_info">
                    <!-- 배송비 -->
                    <div id="order_title">배송비</div>
                    <div id="delivery_charge">* 배송비 * DB에서 불러오기</div>
                </div>

                <div class="payment_amount_info">
                    <!-- 총 상품 가격 - 할인 금액 + 배송비 결과 -->
                    <div id="order_title">총 결제 금액</div>
                    <div id="total_payment_amount">* 총 결제 금액 * DB에서 불러오기</div>
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

                <!-- DB에서 끌고 오기 -->
                <div class="payment_details">
                    <!-- 결제 방식 -->
                    <div class="payment_title"> * 결제 방식 (ex: 카드 간편 결제) * DB에서 불러오기</div>
                    <!-- 총 결제 금액 불러오기 -->
                    <div class="amount_price"> * 총 결제 금액 * DB에서 불러오기</div>
                </div>

                <!-- DB에서 끌고 오기 -->
                <div class="payment_details2">
                    <!-- 결제 방식 관련 정보 -->
                    <div class="card_number"> * 카드 번호 (ex: 비씨 (5555-****-****-****)) * DB에서 불러오기</div>
                    <div class="payment_installments"> * 할부 방식 * DB에서 불러오기</div>
                </div>

                <!-- 카드사 정책 구현이 가능한지 모르겠음....?? -->
                <div class="company_policy">
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