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

        <div class="orderSuccess">주문완료</div>

        <div class="line"></div>

        <div class="order_announce">
            주문해주셔서 감사합니다
            <br>
            주문 내역은 마이페이지에서 확인 가능합니다.
        </div>

        <div class="order_detail">주문내역</div>

        <div class="line2"> </div>

        <div class="order_detail_box">

            <div class="left-box">
                 <table>
		            <tr>
		                <td>
		                    <label for="name">이름</label>
		                </td>
		                <td>
		                    <span>${name}</span>
		                </td>
		            </tr>
		            <tr>
		                <td>
		                    <label for="address">주소</label>
		                </td>
		                <td>
		                    <span class="address1_span">${address1}</span>
		                    <span class="address2_span">${address2}</span>
		                    <span class="address3_span">${address3}</span>
		                </td>
		            </tr>
		            <tr>
		                <td>
		                    <label for="phone">연락처</label>
		                </td>
		                <td>
		                    <span>${tel}</span>
		                </td>
		            </tr>
		            <tr>
		                <td>
		                    <label for="delivery">배송요청사항</label>
		                </td>
		                <td>
		                    <span>${delivery_comment}</span>
		                </td>
		            </tr>
		        </table>

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
                    <div class="delivery_price_span" >${delivery_cost}원</div>
                </div>
                
                <div class="price-box total">
                    <div class="price-label">총결제금액</div>
                    <div class="finalTotalPrice_span">${price}원</div>
                </div>
            </div> <!-- right-box -->
        </div> <!-- line2 -->

		<form action="/main">
			<button class="orderSuccess_button">쇼핑 계속하기</button>
		</form>
        <!--<input type="submit" class="쇼핑계속하기" value="쇼핑 계속하기">-->

    </div>

	<%@include file= "../common/footer.jsp" %>

</body>


</html>