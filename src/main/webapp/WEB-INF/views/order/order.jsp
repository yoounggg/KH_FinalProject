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
    
    <script src="/resources/js/order/order.js"></script>
    
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	

    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
    <script>
        var IMP = window.IMP; 
        IMP.init("imp35647012"); 
    
        function requestPay() {
            IMP.request_pay({
                pg : 'kcp.{INIBillTst}',
                pay_method : 'card',
                merchant_uid: "57008833-33004", 
                name : '당근 10kg',
                amount : 1004,
                buyer_email : 'Iamport@chai.finance',
                buyer_name : '포트원 기술지원팀',
                buyer_tel : '010-1234-5678',
                buyer_addr : '서울특별시 강남구 삼성동',
                buyer_postcode : '123-456'
            }, function (rsp) { // callback
                if (rsp.success) {
                    console.log(rsp);
                } else {
                    console.log(rsp);
                }
            });
        }
    </script>
	    
</head>
<body>

	<%@include file= "../common/header.jsp" %>
	
<div class="wrapper">

        <div class="주문결제">
            주문결제
        </div>

       
        <div class="주문상품">

            <hr class="separator">

            <div class="orderProducts_div">
				
				<div class="products_kind_div">
					주문상품 <span class="products_kind_div_kind"></span>종<span class="products_kind_div_count"></span>개
				</div>
				
				<table class="products_subject_table">
					<colgroup>
						<col width="15%">
						<col width="45%">
						<col width="40%">
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
						<col width="15%">
						<col width="45%">
						<col width="40%">
					</colgroup>
					<tbody>
						<c:if test="${not empty orderList}">
							<c:forEach items="${orderList}" var="ol">
								<tr>
									<td></td>
									<td class="상품정보">${ol.name}</td>
									<td class="products_table_price_td">
										<fmt:formatNumber value="${ol.price}" pattern="#,### 원" /> | 수량 ${ol.productCount}개
										<br><fmt:formatNumber value="${ol.totalPrice}" pattern="#,### 원" />
										<input type="hidden" class="individual_productPrice_input" value="${ol.price}">
										<input type="hidden" class="individual_salePrice_input" value="${ol.discount}">
										<input type="hidden" class="individual_productCount_input" value="${ol.productCount}">
										<input type="hidden" class="individual_totalPrice_input" value="${ol.discount * ol.productCount}"> 
										<input type="hidden" class="individual_productId_input" value="${ol.productId}">
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
                    <input type="text" id="name_input" name="name" value="${memberInfo.name}">
                    <button class="edit_button">수정</button>
                </div>

                <div class="input_row">
                    <label for="phone_title">핸드폰 번호</label>
                    <input type="tel" id="phone_input" name="phone" value="${memberInfo.tel}">
                    <button class="edit_button">수정</button>
                </div>

                <div class="input_row">
                    <label for="email_title">이메일</label>
                    <input type="email" id="email_input" name="email" value="${memberInfo.email}">
                </div>

            </div>
        </div>

        <div class="받는사람 정보">

            <div class="receiver_info">받는사람 정보</div>

            <hr class="separator">

            <div class="locate">

                <div class="input_row2">
                    <label for="name_title_r">이름</label>
                    <input type="text" id="name_input_r" name="name" placeholder="이름을 입력해주세요">
                </div>

                <div class="input_row2">
                    <label for="address_title_r">주소</label>

					<input type="text" id="address_input_r" placeholder="우편번호">
                    <input type="button" onclick="sample4_execDaumPostcode()" value="주소입력" class="address_button" ><br>
                    <div></div>
                    <input type="text" id="address_input2_r" placeholder="도로명주소">

                  	<div></div>
                  	<input type="text" id="address_input3_r"" placeholder="지번주소">
                    <div></div>
                  <input type="text" id="address_input3_r" placeholder="상세주소">

                </div>

                <div class="input_row2">
                    <label for="phone_title_r">연락처</label>
                    <input type="phone" id="phone_input_r" name="phone" placeholder="연락처를 입력해주세요">
                </div>


                <div class="input_row2">
                    <label for="request_title_r">배송 요청사항</label>

                    <select name='selbox' id="배송요청사항" onchange="selectMemo(this)">
                        <option value='' selected id="선택">--------------------- 선택 ---------------------</option>
                        <option value='부재시, 연락 바랍니다.'>부재시, 연락 바랍니다.</option>
                        <option value='부재시, 무인 택배함 보관 후 연락바랍니다.'>부재시, 무인 택배함 보관 후 연락바랍니다.</option>
                        <option value='부재시, 경비실에 맡겨주세요.'>부재시, 경비실에 맡겨주세요.</option>
                        <option value="직접입력">직접입력</option>
                    </select>
                    <br>

                <input id="selboxDirect" type="text" placeholder="직접 입력하세요" style="display: none;">     
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
            			<span id="총상품가격">100000</span>원
            		</li>
            	
            		<li>
            			<span id="정보">할인금액</span>
            			<span id="할인금액">1000</span>원
            		</li>
            	
            		<li>
            			<span id="정보">배송비</span>
            			<span id="배송비1">1000</span>원
            		</li>
            	
            		<li class="pricae_total_lo">
            			<strong id="price_span_label total_price_label]">총결제금액</strong>
            			<strong id="총결제금액">
            				<span id="total_rpice_red finalTotalPrice_span"></span>
            						98000
            			</strong>원
            		</li>
            	</ul>
            </div>
        </div> 

		 <button onclick="requestPay()" class="결제팝업" >결제하기</button>
    </div>



    <aside>
        <div class="container7">
            <div id="choikun1">
                <div id="choikun2">
                    <div>최근 본 상품</div>
                        <img src="imgs/sample.jpg" alt="최근1">
                        <img src="imgs/sample.jpg" alt="최근2">
                        <img src="imgs/sample.jpg" alt="최근3">
                </div>    
                <input id="topbtn" type="button" name="TOP" value="TOP">
            </div> 
        </div>
    </aside>


	<%@include file= "../common/footer.jsp" %>

</body>

</html>
