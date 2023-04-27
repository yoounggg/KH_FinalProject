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

/* ==========================결제======================================= */


/* ==============================배송지=========================================== */
    
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
            }, function (rsp) { 
                if (rsp.success) {
                    console.log(rsp);
                } else {
                    console.log(rsp);
                }
            });
        }
  
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {

                var roadAddr = data.roadAddress; 
                var extraRoadAddr = ''; 

                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }

                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }

                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                document.getElementById('address_input_r').value = data.zonecode; 
                document.getElementById("address_input2_r").value = roadAddr; 
                document.getElementById("address_input3_r").value = data.jibunAddress; 

                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
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
    

      function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('address_input_r').value = data.zonecode;
                document.getElementById("address_input2_r").value = roadAddr;
                document.getElementById("address_input3_r").value = data.jibunAddress;

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
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


