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
 	    <script src="/resources/js/order/order.js"></script>

    <link rel="shortcut icon" href="/resources/ico/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/ico/favicon.ico" type="image/x-icon">
    
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
    <script src="https://cdn.iamport.kr/v1/iamport.js"></script>
    
 <!--<script>
 function requestPay() {
	  const IMP = window.IMP;
	  IMP.init("imp11157675");

	  var productId = $(".individual_productId_input").val();
	  var productName = $(".individual_productName_input").val();
	  var price = parseFloat($(".finalTotalPrice_span").text().replace(',', ''));
	  var email = $("#email_input").val();
	  var name = $("#name_input").val();
	  var tel = $("#phone_input").val();
	  var addr = $(".memberAddress2").val() + " " +  $(".memberAddress3").val();
	  var postcode = $(".memberAddress1").val();
	  var merchant_uid = new Date().getTime().toString();
	  var uid = '';

	  IMP.request_pay({
	    pg: 'html5_inicis',
	    pay_method: 'card',
	    merchant_uid: merchant_uid,
	    name: productName,
	    amount: price,
	    buyer_email: email,
	    buyer_name: name,
	    buyer_tel: tel,
	    buyer_addr: addr,
	    buyer_postcode: postcode
	  }, function(rsp) { // callback
	    if (rsp.success) {
	      console.log(rsp);
	      var dto = {
	        productId: productId,
	        productName: productName,
	        price: price,
	        email: email,
	        name: name,
	        tel: tel,
	        addr: addr,
	        postcode: postcode,
	        merchantUid: merchant_uid,
	        uid: uid
	      };
	      $.ajax({
	        url: '/order',
	        method: 'POST',
	        data: dto,
	        success: function(data) {
	          console.log(data);
	          window.location.href = "/order/orderSuccess";
	        },
	        error: function(xhr, status, error) {
	          console.log(xhr);
	        }
	      });
	    } else {
	      console.log(rsp);
	    }
	  });
	}
</script>  -->


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
						<col width="25%">
						<col width="30%">
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
	                
	               <div class="input_row2">
						<label for="request_title_r">배송 요청사항</label>
						<select name='selbox'
							id="배송요청사항" onchange="selectMemo(this)">
							<option value='' selected id="선택">---------------------선택 ---------------------</option>
							<option value='부재시, 연락 바랍니다.'>부재시, 연락 바랍니다.</option>
							<option value='부재시, 무인 택배함 보관 후 연락바랍니다.'>부재시, 무인 택배함 보관 후 연락바랍니다.</option>
							<option value='부재시, 경비실에 맡겨주세요.'>부재시, 경비실에 맡겨주세요.</option>
							<option value="직접입력">직접입력</option>
						</select> <br> <input id="selboxDirect" type="text"
							placeholder="직접 입력하세요" style="display: none;">
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
		                
		            <div class="input_row2">
						<label for="request_title_r">배송 요청사항</label>
						<select name='selbox'
							id="배송요청사항" onchange="selectMemo(this)">
							<option value='' selected id="선택">---------------------선택 ---------------------</option>
							<option value='부재시, 연락 바랍니다.'>부재시, 연락 바랍니다.</option>
							<option value='부재시, 무인 택배함 보관 후 연락바랍니다.'>부재시, 무인 택배함 보관 후 연락바랍니다.</option>
							<option value='부재시, 경비실에 맡겨주세요.'>부재시, 경비실에 맡겨주세요.</option>
							<option value="직접입력">직접입력</option>
						</select> <br> <input id="selboxDirect" type="text"
							placeholder="직접 입력하세요" style="display: none;">
					</div>
		                      
					</div>
				
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
        

         <form class="order_form" action="/order" method="post">
  
        	<input name="member_id" value="${memberInfo.id}" type="hidden">
       
			<input name="receiver_name" type="hidden" value="">
			<input name="receiver_address1" type="hidden" value="">
			<input name="receiver_address2" type="hidden" value="">
			<input name="receiver_address3" type="hidden" value="">
			<input name="receiver_tel" type="hidden" value="">
			<input name="delivery_comment" type="hidden" value="">
				
         </form>

		 <button class="order_btn" >결제하기</button>
    </div>

	<%@include file= "../common/footer.jsp" %>

</body>

<script>

/* ============================== 배송요청사항 직접입력 =========================================== */
	function selectMemo(select) {       // 배송요청사항 직접입력
	    var TTT1 = document.getElementById("selboxDirect");
	
	    if(select.value === "직접입력") {
	        TTT1.style.display = "inline";
	        TTT1.required = true;
	    } else {
	        TTT1.style.display = "none";
	        TTT1.required = false;
	    }
	}
	
	var productPrice = 10000;
	var deliveryFee= 3000;
	var discount = 3000;
	
	function calculatetotal() {
	    var total = productPrice + deliveryFee - discount;
	
	    var totalElement = document.getElementById("total_price");
	    totalElement.textContent = total.toLocaleString() + "원";
	}


/* ============================== 주소 입력란 버튼 동작 (숨김, 동작) =========================================== */
	function showAddress(className) {
		// 모든 .addressInfo_input_div를 숨긴다
		$(".addressInfo_input_div").hide();
    	// 클래스 이름에 해당하는 .addressInfo_input_div를 보여준다
		$(".addressInfo_input_div_" + className).show();
		if (className === "1") {
		// 직접 입력 버튼을 클릭한 경우
		// .address_btn_2_wrap를 숨긴다
		$(".address_btn_2_wrap").hide();
		// .locate.address_btn.address_btn_1를 보여준다
		$(".locate.address_btn.address_btn_1").show();
		// 기존 주소 버튼을 보여준다
		$(".locate.address_btn.address_btn_2").show();
		//$(".address_btn_2_wrap").show();
	} else if (className === "2") {
	    // 기존 주소 버튼을 클릭한 경우
	    // .address_btn_2_wrap를 보여준다
	    $(".address_btn_2_wrap").show();
	    // .locate.address_btn.address_btn_1를 숨긴다
	    $(".locate.address_btn.address_btn_1").hide();
	}
	    // 모든 .selectAdressee를 false로 설정
	    $(".selectAdressee").val("F");
	     // 해당하는 .addressInfo_input_div의 .selectAdressee를 true로 설정
	    $(".addressInfo_input_div_" + className).find(".selectAdressee").val("T");
	}
    
	/* ==============================배송지=========================================== */
	  
	function sample4_execDaumPostcode() {
		new daum.Postcode({
			
			
			
		    oncomplete: function(data) {
		    	var roadAddr = data.roadAddress; 
		        var extraRoadAddr = ''; 
		        var themeObj = {
		        		   searchBgColor: "#0B65C8", //검색창 배경색
		        		   queryTextColor: "#FFFFFF" //검색창 글자색
		        		};
		        if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		        	extraRoadAddr += data.bname;
		        }
	
		        if(data.buildingName !== '' && data.apartment === 'Y'){
		        	extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		        }
	
		        if(extraRoadAddr !== ''){
		        	extraRoadAddr = ' (' + extraRoadAddr + ')';
		        }
	
		        var addressInputR = document.getElementById('address_input_r');
		        var addressInput2R = document.getElementById('address_input2_r');
		        var addressInput3R = document.getElementById('address_input3_r');
		        var sample4ExtraAddress = document.getElementById('sample4_extraAddress');
	
		        if (addressInputR) {
		            addressInputR.value = data.zonecode;
		          }
		          if (addressInput2R) {
		            addressInput2R.value = roadAddr;
		          }
		          if (addressInput3R) {
		            addressInput3R.value = data.jibunAddress;
		          }
		          if (sample4ExtraAddress) {
		            sample4ExtraAddress.value = extraRoadAddr !== '' ? extraRoadAddr : '';
		          }
	
		        var guideTextBox = document.getElementById("guide");
	
		        if(data.autoRoadAddress) {
		        	var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
		            guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
		            guideTextBox.style.display = 'block';
	
		        } else if(data.autoJibunAddress) {
		        	var expJibunAddr = data.autoJibunAddress;
		        	guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
		       		guideTextBox.style.display = 'block';
		        } else {
		        	guideTextBox.innerHTML = '';
		        	guideTextBox.style.display = 'none';
		        }
			}
		}).open();
	} 
	      
	
	$(document).ready(function() {
    	/* 주문 조합정보란 최신화 */
		setTotalInfo();
    	
		$(".order_btn").on("click", function() {
			  $(".locate_addressInfo").each(function(i, obj) {
			    if ($(obj).find(".selectAdressee").val() == 'T') {
					$("input[name='receiver_name']").val($(obj).find(".name_input_r").val());
			      	$("input[name='receiver_address1']").val($(obj).find("#address_input_r").val());
			      	$("input[name='receiver_address2']").val($(obj).find("#address_input2_r").val());
			      	$("input[name='receiver_address3']").val($(obj).find("#address_input3_r").val());
			      	$("input[name='receiver_tel']").val($(obj).find("#phone_input_r").val());
			    } else if  ($(obj).find(".selectAdressee").val() == 'F') {
				    $("input[name='receiver_name']").val($(obj).find(".name_input2_r").val());
					$("input[name='receiver_address1']").val($(obj).find(".address_input4_r").val());
				    $("input[name='receiver_address2']").val($(obj).find(".address_input5_r").val());
				    $("input[name='receiver_address3']").val($(obj).find(".address_input6_r").val());
				    $("input[name='receiver_tel']").val($(obj).find("#phone_input2_r").val());
			    }
				// 배송 요청사항 select 태그에서 선택한 option 값 받아오기
				var deliveryRequest = $("#배송요청사항 option:selected").val();
			    // 만약 "직접입력"을 선택했다면 해당 input 태그의 값을 deliveryRequest 변수에 저장하기
				if (deliveryRequest == "직접입력") {
	            deliveryRequest = $("#selboxDirect").val();
			  	}
				$("input[name='delivery_memo']").val(deliveryRequest);
			});

		/* 상품정보 */
			let form_contents = '';
			$(".products_table_price_td").each(function(index, element) {

		  		let product_no = $(element).find(".individual_productId_input").val();
		  		let count = $(element).find(".individual_productCount_input").val();
		  		let price = $(element).find(".individual_productPrice_input").val();
		  		//totalPrice_span

		  		let product_no_input = "<input name='orders[" + index + "].product_no' type='hidden' value='" + product_no + "'>";
		  		form_contents += product_no_input;
		  		let productCount_input = "<input name='orders[" + index + "].count' type='hidden' value='" + count + "'>";
		  		form_contents += productCount_input;
		  		let price_input = "<input name='orders[" + index + "].price' type='hidden' value='" + price + "'>";
		  		form_contents += price_input;
			});
			$(".order_form").append(form_contents);
		
		  	/* 주문 양식 제출 */
		  	$(".order_form").submit();        
		}); 
	});	
	
	
    /* ==============================총 주문 정보=========================================== */
    
	 function setTotalInfo() {
	    
	    let totalPrice = 0;          // 총 가격
	    let totalCount = 0;          // 총 갯수
	    let totalKind = 0;           // 총 종류
	    let delivery = 0;            // 배송비
	    let totalDiscount = 0;		 // 할인금액
	    let finalTotalPrice = 0;     // 최종 가격 (총 가격 + 배송비)
	    

	    $(".products_table_price_td").each(function(index, element) {
	        // 총 가격
	        totalPrice += parseInt($(element).find(".individual_totalPrice_input").val());
	        // 총 갯수
	        totalCount += parseInt($(element).find(".individual_productCount_input").val());
	        // 할인금액
	        // totalDiscount += parseInt($(element).find(".individual_salePrice_input").val());
	        totalDiscount += parseInt($(element).find(".individual_salePrice_input").val());

	        if(totalPrice >= 30000) {           // 총 가격이 3만원 이상이면 배송비 무료
	            delivery = 0;           
	        } else if (totalPrice == 0) {       
	           delivery = 0;           
	        } else {                            // 아니면 3000원 배송비 부담
	            delivery = 3000;
	        }
	        

	        finalTotalPrice = totalPrice + delivery - totalDiscount;    // 최종 가격 : 총 가격 + 배송비 - 할인가격

	    		
	    		/* 값 삽입 */
	    		// 총 가격
	    		$(".totalPrice_span").text(totalPrice.toLocaleString());
	    		// 총 갯수
	    		$("#products_kind_div_count").text(totalCount);
	    		// 할인 금액
	    		$(".salePrice_span").text(totalDiscount.toLocaleString());
	    		// 배송비
	    		$(".delivery_price_span").text(delivery.toLocaleString());
	    		// 최종 가격
	    		$(".finalTotalPrice_span").text(finalTotalPrice.toLocaleString());
	    		
	    	}); 
	    }
	
</script>
	    

</html>