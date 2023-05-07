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
    
 	<link rel="stylesheet" href="/resources/css/order/order.css">	

    <link rel="shortcut icon" href="/resources/ico/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/ico/favicon.ico" type="image/x-icon">
    
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
    <script src="https://cdn.iamport.kr/v1/iamport.js"></script>
    
    	<script src="/resources/js/order/order.js"></script>

</head>

<body>

	<%@include file= "../common/header.jsp" %>
	
<div class="wrapper">

        <div class="주문결제">
            주문결제
        </div>

       
        <div class="OrderProducts">

            <hr class="separator">

            <div class="orderProducts_div">
				
				<div class="products_kind_div">
					주문상품
				</div>
				
				<table class="products_subject_table">
					<colgroup>
						<col width="15%">
						<col width="45%">
						<col width="30%">
					</colgroup>
					<tbody>
						<tr>
							<th>이미지</th>
							<th>상품 정보</th> 
							<th>판매가</th>
						</tr>
					</tbody>
				</table>
				<table class="products_table">
					<colgroup>
						<col width="25%">
						<col width="22%">
						<col width="30%">
					</colgroup>
					<tbody>
						<c:if test="${not empty orderList}">
							<c:forEach items="${orderList}" var="ol">
								<tr>
									<td class="products_image_wrap">
									<img src="/resources/product/${ol.main_image}" alt="상품 이미지">
									</td>
									<td class="상품정보">${ol.name}</td>
									<td class="products_table_price_td">
										<fmt:formatNumber value="${ol.price}" pattern="#,### 원" /> | 수량 ${ol.productCount}개
										<br><fmt:formatNumber value="${ol.totalPrice}" pattern="#,### 원" />
										<input type="hidden" class="individual_productName_input" name="name" value="${ol.name}">
										<input type="hidden" class="individual_productPrice_input" name="price" value="${ol.price}">		
										<input type="hidden" class="individual_productCount_input"  name="Count" value="${ol.productCount}">
										<input type="hidden" class="individual_totalPrice_input" value="${ol.productCount * ol.price}">
										<input type="hidden" class="individual_salePrice_input" name="Discount" value="${ol.salePrice}"> 
										<input type="hidden" class="individual_productId_input" name="productId" value="${ol.productId}">
									</td>
								</tr>			
							</c:forEach>
						</c:if>
						
						<c:if test="${empty orderList}">
							<tr>
								<td colspan="3">상품이 없습니다.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				
            </div>

        </div>
        
        <div class="구매자정보">

            <div class="buyer_info">구매자 정보</div>

            <hr class="separator">

            <div class="locate">

                <div class="input_row">
                    <label for="name_title">이름</label>  
                    <input type="text" id="name_input" name="name" value="${memberInfo.name}" readonly>
                    <button class="edit_button">수정</button>
                </div>

                <div class="input_row">
                    <label for="phone_title">핸드폰 번호</label>
                    <input type="tel" id="phone_input" name="phone" value="${memberInfo.tel}" readonly>
                </div>

                <div class="input_row">
                    <label for="email_title">이메일</label>
                    <input type="email" id="email_input" name="email" value="${memberInfo.email}" readonly/>
                </div>

            </div>
        </div>

        <div class="받는사람 정보">

            <div class="receiver_info">받는사람 정보
                
	            <div class="addressInfo_button_div">
				  <button class="address_btn_address_btn_1" onclick="showAddress('1')">직접 입력</button>
				  <button class="address_btn_address_btn_2" onclick="showAddress('2')">기존 주소</button>
				</div>
            </div>

            <hr class="separator">

           <!-- <div class="locate">  --> 
           <div class="locate_addressInfo">
				<div class="locate address_btn address_btn_1">

					<div class="input_row2">
	                    <label for="name_title_r">이름</label>
	                    <input type="text" class="name_input_r" name="receiver_name" placeholder="이름을 입력하세요">
	                </div>
	
	                <div class="input_row2">
	                    <label for="address_title_r">주소</label>
						<input class="selectAdressee" value="T" type="hidden">
						<input type="text" id="address_input_r"  placeholder="우편번호" readonly>
	                    <input type="button" onclick="sample4_execDaumPostcode()" value="주소입력" class="address_button" ><br>
	                    <div></div>
	                    <input type="text" id="address_input2_r" placeholder="도로명주소" readonly>
	
	                  	<div></div>
	                  	<input type="text" id="address_input3_r" placeholder="상세주소">
	                    <div></div>
	                  	<!-- <input  type="text" id="address_input4_r" value="상세주소">   -->
	                </div>
	
	                <div class="input_row2">
	                    <label for="phone_title_r">연락처</label>
	                    <input type="phone" id="phone_input_r" name="receiver_tel" placeholder="000-0000-0000">
	                </div>    
	                
				</div>
				
				<div class="address_btn_2_wrap" style="display: none;">
				
					<div class="locate address_btn address_btn_2" >
						<div class="input_row2">
		                    <label for="name_title_r">이름</label>
		                    <input type="text" class="name_input2_r" name="receiver_name" value="${memberInfo.name}" readonly>
		                </div>
		
		                <div class="input_row2">
		                    <label for="address_title_r">주소</label>
							<input class="selectAdressee" value="F" type="hidden">
							<input type="text" class="address_input4_r" value="${memberInfo.address1}" readonly>
		                    <input type="button" onclick="sample4_execDaumPostcode()" value="주소입력" class="address_button" ><br>
		                    <div></div>
		                    <input type="text" class="address_input5_r" value="${memberInfo.address2}" readonly>
		
		                  	<div></div>
		                  	<input type="text" class="address_input6_r" value="${memberInfo.address3}" readonly>
		                    <div></div>
		                  	<!-- <input  type="text" id="address_input4_r" value="상세주소">   -->
		                </div>
		
		                <div class="input_row2">
		                    <label for="phone_title_r">연락처</label>
		                    <input type="phone" id="phone_input2_r" name="receiver_tel" value="${memberInfo.tel}">
		                </div>   
		                      
					</div>
				
				</div>

		            <div class="input_row3">
						<label for="request_title_r">배송 요청사항</label>
						<select name='selbox' id="배송요청사항" onchange="selectMemo(this)">
							<option value='' selected id="선택">--------------------------- 선택 ---------------------------</option>
							<option value='부재시, 연락 바랍니다.'>부재시, 연락 바랍니다.</option>
							<option value='부재시, 무인 택배함 보관 후 연락바랍니다.'>부재시, 무인 택배함 보관 후 연락바랍니다.</option>
							<option value='부재시, 경비실에 맡겨주세요.'>부재시, 경비실에 맡겨주세요.</option>
							<option value="직접입력">직접입력</option>
						</select>
						<br>
						<input id="selboxDirect" type="text" placeholder="직접 입력하세요" style="display: none;">
						<br>
					</div>

			</div> 
            
        </div>

        <div class="결제금액정보">

            <div class="pay_info">결제 금액</div>

            <hr class="separator">

            <div class="locate">
            	<ul>
            		<li>
            			<span id="정보">총 상품 가격</span>
            			<span class="totalPrice_span"></span>원
            		</li>         		
            		<li>
            			<span id="정보">할인금액</span>
						<span class="salePrice_span"></span>원
            		</li>
            		<li>
            			<span id="정보">배송비</span>
            			<span class="delivery_price_span"></span>원 
            		</li>
            	
            		<li class="pricae_total_lo">
            			<strong class="price_span_label total_price_label">총결제금액</strong>
            			<strong class="strong_red">
            				<span class="finalTotalPrice_span"></span>
            			</strong>원
            		</li>
            	</ul>
            </div>
        </div>
        

		 <!-- order_btn 클릭시 주문정보를 같이 전송하기 위함(db에 들어가도록) -->
         <form class="order_form" action="/order" method="post">
  
        	<input name="member_id" value="${memberInfo.id}" type="hidden">
       
			<input name="receiver_name" type="hidden" value="">
			<input name="receiver_address1" type="hidden" value="">
			<input name="receiver_address2" type="hidden" value="">
			<input name="receiver_address3" type="hidden" value="">
			<input name="receiver_tel" type="hidden" value="">
			<input name="delivery_comment" type="hidden" value="">
				
         </form>

	 	 <button onclick="requestPay()" class="order_btn" >결제하기</button>
		 
    </div>

	<%@include file= "../common/footer.jsp" %>
</body>
		
		<script>
		
		$(document).ready(function() {
			
			 setTotalInfo();
			 
		});
		
		</script>

</html>