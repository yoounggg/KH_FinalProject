    		
        IMP.init("imp11157675"); // 예: imp00000000a

        function requestPay() {
	
	    var productName = $(".individual_productName_input").val();
	    var price = parseFloat($(".finalTotalPrice_span").text().replace(',', ''));
	    var email = $("#email_input").val();
	    var name = $("#name_input").val();
	    var tel = $("#phone_input").val();
		var addr = $(".memberAddress2").val() + " " +  $(".memberAddress3").val();
		var postcode = $(".memberAddress1").val();
		var merchant_uid = new Date().getTime().toString();

            IMP.request_pay({
                pg : 'html5_inicis',
                pay_method : 'card',
                merchant_uid: merchant_uid, 
                name : productName,
                amount : 101,
                buyer_email : email,
                buyer_name : name,
                buyer_tel : tel,
                buyer_addr : addr,
                buyer_postcode : postcode
            }, function (rsp) { // callback
                if(rsp.success) {
                    console.log(rsp);
					sendOrderInfo();
                } else {
                    console.log(rsp);
                }
            });
        }

$(".order_btn").on("click", function() {
          
         requestPay();      
    }); 
    
        function sendOrderInfo() {
        // 배송 요청사항 select 태그에서 선택한 option 값 받아오기
        var deliveryRequest = $("#배송요청사항 option:selected").val();
        // 만약 "직접입력"을 선택했다면 해당 input 태그의 값을 deliveryRequest 변수에 저장하기
        if (deliveryRequest == "직접입력") {
          deliveryRequest = $("#selboxDirect").val();
        }
        // form 태그의 hidden input 요소에 값을 할당하기
        $("input[name='delivery_comment']").val(deliveryRequest);
        
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
        }


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
	var self = this;
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
	        

	        if (addressInputR) {
	            addressInputR.value = data.zonecode;
	          }
	          if (addressInput2R) {
	            addressInput2R.value = roadAddr;
	          }
	          if (addressInput3R) {
	            addressInput3R.value = data.jibunAddress;
	          }
	          
	      // 팝업 창 닫기
	      self.close();
	      
		}
	}).open();
}
      
/*   */

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
   