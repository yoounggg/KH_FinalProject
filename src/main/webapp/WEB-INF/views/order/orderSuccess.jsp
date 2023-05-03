<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MOYAMOGA</title>
    
 	<link rel="stylesheet" href="/resources/css/order/orderSuccess.css">

    <link rel="shortcut icon" href="/resources/ico/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/ico/favicon.ico" type="image/x-icon">
	
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>

</head>
<body>

	<%@include file= "../common/header.jsp" %>
	

    <div class="wrapper">

        <div class="주문완료">주문완료</div>

        <div class="line"></div>

        <div class="인사">
            주문해주셔서 감사합니다
            <br>
            주문 내역은 마이페이지에서 확인 가능합니다.
        </div>

        <div class="주문내역">주문내역</div>

        <div class="line2"> </div>

        <div class="주문내역박스">

            <div class="left-box">
                <div class="input-box">
                    <label for="name">이름</label>
                    <input type="text" id="name" name="name" value="${name}" readonly>
                    <!-- <label for="name">이름</label>
                    <input type="text" id="name" name="name"> -->
                </div>

                <div class="input-box">
                    <label for="address">주소</label>
                    <input type="text" id="address1" name="address" value="${address1 }" readonly>
                </div>

                <div>
                    <input type="text" id="address2" name="address" value="${address2}" readonly>
                </div>

                <div class="input-box">
                    <label for="phone">연락처</label>
                    <input type="tel" id="phone" name="phone" value="${tel}" readonly>
                </div>

                <div class="input-box">
                    <label for="delivery">배송요청사항</label>
                    <textarea id="delivery" name="delivery" readonly>${delivery_comment}</textarea>
                </div>

            </div><!-- left-box -->

            <div class="right-box">
                <div class="price-box">
                    <div class="price-label">총상품가격</div>
                    <div class="totalPrice_span">${totalPrice}원</div>
                </div>

                <div class="price-box">
                    <div class="price-label">할인금액</div>
                    <div class="salePrice_span">${salePrice}원</div>
                </div>

                <div class="price-box">
                    <div class="price-label">배송비</div>
                    <div class="delivery_price_span" >${delivery_cost }원</div>
                </div>
                
                <div class="price-box total">
                    <div class="price-label">총결제금액</div>
                    <div class="finalTotalPrice_span">${price}원</div>
                </div>
            </div> <!-- right-box -->
        </div> <!-- line2 -->

        <input type="submit" class="쇼핑계속하기" value="쇼핑 계속하기">

    </div>

	<%@include file= "../common/footer.jsp" %>

</body>


</html>