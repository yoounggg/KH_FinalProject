
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
    
    
    function setTotalInfo() {
    	
    	let totalPrcie = 0;			
    	let totalCount = 0;			
    	let totalKind = 0;			
    	let delivery = 0;			
    	let finalTotalPrice = 0;	
    
    	$(".procuts_table_price_td").each(function(index, element) {

    		totalPrice += parseInt($(element).find(".individual_totalPrice_input").val)

    		totalCount += parseInt($(element).find(".individual_productCount_input").val)

    		totalKind += 1;
    		

    		if(totalPrice >= 30000) {
    			delivery = 0;			
    		} else if (totalPrcie == 0) {
    			delivery = 0;			
    		} else {
    			delivery = 3000;
    		}
    		
    		finalTotalPrice = totalPrcie + delivery;   
    		
    		$(".총상품가격").text(totalPrice.toLocaleString());
    	
    		$(".products_kind_div_count").text(totalCount);
    	
    		$(".products_kind_div_kind").text(totalKind);
    	
    		$(".배송비1").text(delivery.toLocaleString());
    
    		$(".finalTotalPrice_span").text(finalTotalPrice.toLocaleString());
    		
    	}); 
    }
