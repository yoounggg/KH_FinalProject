

	// 1. 파일 이미지  업로드
	$("input[type='file']").on("change", function(e) {
		
		let formData = new FormData();
		let fileInput = $('input[name="main_image"]');
		let fileList = fileInput[0].files;
		let fileObj = fileList[0];
		
		if(!fileCheck(fileObj.name, fileObj.size)) {
			return false;
		}
			
		formData.append("uploadFile", fileObj);
		
/* 		for(let i = 0; i<fileList.length; i++) {
			formData.append("uploadFile", fileList[i]);
		} */
		
		// 준비된 데이터를 서버에 전송하는 코드 ajax
		$.ajax({
			url: '/admin/product/uploadAjaxAction',
			processData : false,
			contentType : false,
			data : formData,
			type : 'POST',
			dataType : 'json';
		});
		
		
	});
	
	
	
	
	
	
	
	// 2. 파일 사이즈, 종류 제한
	let regex = new RegExp("(.*?)\.(jpg|png|jpeg)$");
	let maxSize = 1048576;	// 1MB
	
	function fileCheck (fileName, fileSize) {
		
		// 파일 사이즈 제한
		if(fileSize >= maxSize) {
			alert("파일 사이즈 초과");
			return false;
		} // if
		
		// 파일 종류 제한
		if(!regex.test(fileName)) {
			alert("해당 종류의 파일은 업로드 할 수 없습니다.");
			return false;
		}
		
		return true;
		
	} //  fileCheck
		
		
// 상품 가격-할인 순으로 입력했을 때
$("#discount").on("propertychange change keyup paste input", function(){
	
	let userInput = $("#discount"); 10
	let discountInput = $("input[name='calc']"); 10
	
	let discountRate = userInput.val();					// 사용자가 입력할 할인값
	let sendDiscountRate = discountRate / 100;					// 서버에 전송할 할인값
	let goodsPrice = $("input[name='price']").val();			// 원가
	let discountPrice = goodsPrice * (1 - sendDiscountRate);		// 할인가격
    
	$(".discount_price").html(discountPrice);
	discountInput.val(sendDiscountRate);	
	
});

// 입력 후 상품 가격을 수정했을 때
$("input[name='price']").on("change", function(){
	
	let userInput = $("#discount");
	let discountInput = $("input[name='calc']");
	
	let discountRate = userInput.val();					// 사용자가 입력한 할인값
	let sendDiscountRate = discountRate / 100;			// 서버에 전송할 할인값
	let goodsPrice = $("input[name='price']").val();			// 원가
	let discountPrice = goodsPrice * (1 - sendDiscountRate);		// 할인가격
	
	$(".discount_price").html(discountPrice);
	
});



/* 상세 정보 내용 */
 	ClassicEditor
	.create(document.querySelector('#info_textarea'))
	.catch(error=>{
		console.error(error);
	});


	
		// 글 작성!!
        var registerBtn = document.querySelector('#registerBtn');

        registerBtn.addEventListener('click', function () {
            location = '/admin/farm/register';
        }); // registerBtn

      //만약 결과값에 어떤 값이든 들어왔다면(null이 아니라면) -> 결과값을 alert창으로 띄운다.
      
        var result = "${param.result}";
        if(result != null && result != "") {        
            alert('result: ' + result);
        } // if
        
        
        
		// 글 삭제!!
        removeBtn.addEventListener('click', function(){
            console.log('removeBtn clicked ㅇ_<');

            //form 태그를 조작해서 삭제요청을 전송! 
            var form = document.querySelector('form');
            console.log(form.constructor.prototype);


            form.setAttribute('method', 'POST');
            form.setAttribute('action', '/admin/farm/remove');
            form.submit();

        }); // removeBtn
