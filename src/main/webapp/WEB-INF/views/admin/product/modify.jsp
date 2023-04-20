<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상품 목록</title>
    <link rel="stylesheet" href="/resources/css/admin/common.css">
	<script src="https://cdn.ckeditor.com/ckeditor5/37.0.1/classic/ckeditor.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>
    <style>
        
	span, li {
            font-size: 16px;
        }


        .content {
            font-size: 13px;
            font-weight: 600;
            font-family: "맑은 고딕",AppleGothic,Dotum,"돋움",sans-serif;
            /* color: #000; */
            padding: 0;
            width : 650px;
            height: 900px;
            margin : auto;
        }
        .content h1 {
            padding-top : 20px;
        }

        .write {
            margin-bottom: 20px;
        }

        h3 {
            height: 30px;
            font-size: 22px;
        }

        .product_detail {
            height: 500px;
            font-size: 20px;
            font-weight: 700;
        }

        form {
            width: 650px;
            margin-top: 40px;
        }

        button {
            float: right;
            margin-left : 5px;
            margin-bottom : 100px;
            font-weight: 800;
            width: 75px;
            height: 35px;
            background-color: rgb(175, 124, 213);
            border: 1px solid #ceced2;
            font-size: 16px;
        }
        
        .product_button {
     		margin-top : 20px;
     	}

        .reg_date {
            float: right;
        }


/* --------------------- 농가 상세 페이지 --------------------------------- */

        .product {
            background-color: #f7f7f7;
            padding: 30px;

        }


        .box1 {
            border: 1px solid rgb(195, 195, 195);
            padding: 0;
            width: 590px;
            height:120px;
            /* display : flex; */
            /* justify-content: center; */
            /* align-items: center; */
            /* vertical-align: middle; */
        }

        .box2 {
            border-bottom: 1px solid rgb(195, 195, 195);
        }

        .box3 {
            padding-top: 15px;
        }
        
        .box4 {
            border: 1px solid rgb(195, 195, 195);
            padding: 0;
            width: 590px;
            height:255px;
        }
        


        p {
            padding-left: 20px;
            font-size: 16;
        }


        #info {
        	padding:0px;
            width: 550px;
            height: 30px;
            font-size: 20px;
            margin-left: 20px;
        }
        
        
        #price, #discount, #discount_price{
        	padding:0px;
            width: 550px;
            height: 30px;
            font-size: 20px;
            margin-left: 20px;
        }

        select {
            width: 150px;
            height: 30px;
            margin-left: 20px;
        }
        
        #discount_price {
        	margin : 0;
        	margin-left : 20px;
        	padding: 0;
        	width: 550;
        	height: 30px;
        	border : 1px solid #767676;
        	background-color: white;
        }
        
        
        /* 위지윅 에디터 높이*/
		.ck-content {					

		    height: 170px;
		}
        
	  
	 a:link {color:black; text-decoration: none;}
	 a:visited {color:black; text-decoration: none;}
/* 	 a:hover {color:black; text-decoration: underline;} */


/* --------------------- 이미지 크기 조정 및 삭제 --------------------------------- */
	
	#result_card img {
		max-width: 200px;
		height:auto;
		display:block;
		padding:5px;
		margin-top:10px;
		margin:auto;
	}
	
	#result_card {
		position : relative;
	}
	
	.imgDeleteBtn {
		position : absolute;
		top: 0;
		right:5%;
	    background-color: #ef7d7d;
	    color: wheat;
	    font-weight: 900;
	    width: 30px;
	    height: 30px;
	    border-radius: 50%;
	    line-height: 26px;
	    text-align: center;
	    border: none;
	    display: block;
	    cursor: pointer;
	}
	
	#result_card img{
		max-width : 100%;
		height : auto;
		display : block;
		padding: 5px;
		margin-top: 10px;
		margin : auto;
	}
        

    </style>
    
</head>

<body>

<!-- 헤더 - 로그인/로그아웃 로고, 관리자페이지 메인글씨 -->
<header>
    <div class="state">
        <span class="login">admin님 로그인하였습니다</span>
        <span class="login">Ι</span>
        <span class="login"><a href="/main">홈페이지</a></span>
        <span class="login">Ι</span>
        <span class="login"><a href="#">로그아웃</a></span>
    </div>
    <div>
        <h1><a href="/admin/main"><img src="/resources/css/admin/logo.png" id="logo" width="200"><a/></h1> 
    </div>
    <h2 class="admin">관리자 페이지</h2>
    
</header>

<!-- 메인 - 보라색 가로줄(메인페이지글자), 왼쪽 메뉴 -->
<main>
    <div class="sub_content">
        <nav> 메인페이지 </nav>
        <div class="menu1">
            <ul id="menu2">
                    <li><a href="/admin/product/register">상품등록</a></li>
                    <li><a href="/admin/product/list">상품목록</a></li>
                    <li><a href="/admin/notice/list">공지사항</a></li>
                    <li><a href="/admin/faq/list">FAQ</a></li>
                    <li><a href="/admin/member/list">회원관리</a></li>
                    <li><a href="/admin/farm/list">거래처관리</a></li>
            </ul>
        </div>   
        
<!-- 메인 - 공지사항 글 목록 테이블, 이동페이지, 수정, 삭제 -->            
                
        <div class="content">
            <h2>상품등록 <span class="test">테스트</span></h2>
            <div class="write">
                <form action="/admin/product/register" method="POST">
                    <div class="product">

	                    <div class="box1">
	                        <div class="box2">
	                            <p>대분류</p>
	                        </div>
	                        <div class="box3">
	                            <select class="category1">
	                                <option selected value="none">선택하기</option>
	                            </select>
	                        </div>                      
	                    </div>
                    	<br>


	                    <div class="box1">
	                        <div class="box2">
	                            <p>중분류</p>
	                        </div>
	                        <div class="box3">
	                            <select name="category" class="category2">
	                                <option selected value="none">선택하기</option>
	    
	                            </select>
	                        </div>                      
	                    </div>
	                    <br>
	                    
	                    
	                    <div class="box1">
	                        <div class="box2">
	                            <p>농장 업체</p>
	                        </div>
	                        <div class="box3">
	                            <input type="text"  name="farm_no" value="1" id="info" readonly>
	                        </div>                      
	                    </div>
	                    <br>
	
	
	                    <div class="box1">
	                        <div class="box2">
	                            <p>상품이름</p>
	                        </div>
	                        <div class="box3">
	                            <input type="text"  name="name" id="info">
	                        </div>                      
	                    </div>
	                    <br>
	
	                    <div class="box1">
	                        <div class="box2">
	                            <p>상품 가격</p>
	                        </div>
	                        <div class="box3">
	                            <input type="text"  name="price"  id="price">
	                        </div>         
	                    </div>
	                    <br>
	
	                    <div class="box1">
	                        <div class="box2">
	                            <p>할인(%)</p>
	                        </div>
	                        <div class="box3">
	                            <input type="text"  name="discount" maxlength="2" id="discount" value="0">
	                            <input type="hidden" name="calc"  value="0">
	                        </div>        
	                    </div>
	                    <br>
	
	                    <div class="box1">
	                        <div class="box2">
	                            <p>할인된 가격</p>
	                        </div>
	                        <div class="box3">
	                           <!--  <input type="text" name="discount_price"  id="discount_price" class="discount_price"> -->
								<p name="discount_price" id="discount_price" class="discount_price"></p>
								<input type="hidden" name="discount_price"  id="discount_price" value="" class="discount_price">
	                        </div>         
	                    </div>
	                    <br>
	
	                    <div class="box1">
	                        <div class="box2">
	                            <p>중량</p>
	                        </div>
	                        <div class="box3">
	                            <input type="text"  name="weight"  id="info">
	                        </div>         
	                    </div>
	                    <br>
	
	                    <div class="box1">
	                        <div class="box2">
	                            <p>원산지</p>
	                        </div>
	                        <div class="box3">
	                            <input type="text"  name="origin"  id="info">
	                        </div>         
	                    </div>
	                    <br>
	
	                    <div class="box1">
	                        <div class="box2">
	                            <p>재고수량</p>
	                        </div>
	                        <div class="box3">
	                            <input type="text"  name="stock"  id="info">
	                        </div>         
	                    </div>
	                    <br>
	
	                    <!-- <p>농가업체</p> -->
	                    <input type="hidden"  name="farm_no"  id="info">
	
	                    <div class="box1">
	                        <div class="box2">
	                            <p>메인 이미지</p>
	                        </div>
	                        <div class="box3">
	                            <input type="file" name="main_image"  id="main_image" style="height:30px";>
	                            <div id="uploadResult">
<!-- 	                            <div id="result_card">
		                            	<div class="imgDeleteBtn">x</div>
		                            	<img src ="/product/display?fileName=test.jpg">
	                            	</div> -->
	                            </div>
	                        </div>         
	                    </div>
	                    <br>
	
	                    <div class="box1">
	                        <div class="box2">
	                            <p>서브 이미지1</p>
	                        </div>
	                        <div class="box3">
	                            <input type="text"  name="sub_image1"  id="info">
	                        </div>         
	                    </div>
	                    <br>
	                
	                    <div class="box1">
	                        <div class="box2">
	                            <p>서브 이미지2</p>
	                        </div>
	                        <div class="box3">
	                            <input type="text"  name="sub_image2"  id="info">
	                        </div>         
	                    </div>
	                    <br>
	                    
	                    <div class="box1">
	                        <div class="box2">
	                            <p>서브 이미지3</p>
	                        </div>
	                        <div class="box3">
	                            <input type="text"  name="sub_image3"  id="info">
	                        </div>         
	                    </div>
	                    <br>
	
	                    <div class="box1">
	                        <div class="box2">
	                            <p>서브 이미지4</p>
	                        </div>
	                        <div class="box3">
	                            <input type="text"  name="sub_image4"  id="info">
	                        </div>         
	                    </div>
	                    <br>
	
	                    <div class="box4">
	                        <div class="box2">
	                            <p>상세정보내용</p>
	                        </div>
	                        <div class="box41">
	                            <textarea name="content" id="info_textarea"></textarea>
	                        </div>         
	                    </div>
	                    <br>
	
	                    <div class="form_section">
	                        <div class="form_section_title">
	                            <p>상세정보내용 이미지</p>
	                        </div>
	                        <div class="form_section_image">
	                            <input type="file" id="content_image" name="content_image" style="height : 30px;">
	                            <div id="uploadResult">
	                            		
	                            </div>
	                        </div>         
	                    </div>
	                    <br>


	                    <div class="product_button">
	                        <button type="submit" id="modifyBtn">등록</button>
	                        <button type="button" id="listBtn">목록</button>
	                    </div>
                    </div>
                 </form>
            </div>
            
        </div>
        

    </div>
</main>

    
</body>




<script>	
//  ================== 1. 버튼을 클릭하면 이동함. =============================

	

        
</script>
<script>
//================== 2. 위지윅 편집기 적용 =============================

/* 상세 정보 내용 */
 	ClassicEditor
	.create(document.querySelector('#info_textarea'))
	.catch(error=>{
		console.error(error);
	});

	
</script>

<script>
//================== 3. 상품 할인율 설정 =============================

// 상품 가격-할인 순으로 입력했을 때
$("#discount").on("propertychange change keyup paste input", function(){
	
	let userInput = $("#discount"); 
	let discountInput = $("input[name='calc']"); 
	
	let discountRate = userInput.val();					// 사용자가 입력할 할인값
	let sendDiscountRate = discountRate / 100;					// 서버에 전송할 할인값
	let goodsPrice = $("input[name='price']").val();			// 원가
	let discountPrice = goodsPrice * (1 - sendDiscountRate);		// 할인가격
    
	$(".discount_price").html(discountPrice);
	discountInput.val(sendDiscountRate);
	
	$("input[name='discount_price']").attr('value',discountPrice);
	
});



// 입력 후 상품 가격을 수정했을 때
$("input[name='price']").on("change", function(){
	
	let userInput = $("#discount");
	let discountInput = $("input[name='calc']");
	
	let discountRate = userInput.val();							// 사용자가 입력한 할인값
	let sendDiscountRate = discountRate / 100;					// 서버에 전송할 할인값
	let goodsPrice = $("input[name='price']").val();			// 원가
	let discountPrice = goodsPrice * (1 - sendDiscountRate);	// 할인가격
	
	$(".discount_price").html(discountPrice);						// 값이 보여짐(실제 Value값에 할당되진 않음)
	$("input[name='discount_price']").attr('value',discountPrice);	// 실제로는 여기로 값이 들어감.
	
});
	
</script>
<script>	
	
	
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
</script>
<script>
	
	/* 이미지 출력 */
	function showUploadImage(uploadResultArr){
	
		/* 전달받은 데이터 검증 */
		if(!uploadResultArr || uploadResultArr.length == 0){return}
		
		let uploadResult = $("#uploadResult");
		
		let obj = uploadResultArr[0];
		
		let str = "";
		
		let fileCallPath = encodeURIComponent(obj.uploadPath.replace(/\\/g, '/') + "/s_" + obj.uuid + "_" + obj.fileName);
		
		str += "<div id='result_card'>";
		str += "<img src='/product/display?fileName=" + fileCallPath +"'>";
		str += "<div class='imgDeleteBtn' data-file='"+ fileCallPath+"'>x</div>";
		str += "<input type='hidden' name='imageList[0].fileName' value='"+ obj.fileName+"'>";
		str += "<input type='hidden' name='imageList[0].uuid' value='"+ obj.uuid+"'>";
		str += "<input type='hidden' name='imageList[0].uploadPath' value='"+ obj.uploadPath+"'>";
		str += "</div>";		
		
		uploadResult.append(str);
	
	}
		
</script>
<script>
//================== 4. 이미지 업로드 =============================
 	
	/* 1. 이미지  업로드 */
	$("input[type='file']").on("change", function(e) {
		
		/* 이미지 존재 시 삭제 */
		if($(".imgDeleteBtn").length > 0) {
			deleteFile();
		}
		
		
		let formData = new FormData();
		let fileInput = $('input[name="main_image"]');
		let fileList = fileInput[0].files;
		let fileObj = fileList[0];
		
 		/*if(!fileCheck(fileObj.name, fileObj.size)) {
			return false;
		} */
			
		formData.append("main_image", fileObj);
		
		/* 만약 사용자가 multiple 속성을 부여하여 여러개의 파일을 선택할 땐 아래와 같이 해주면 됨*/
		
		/* 	for(let i = 0; i<fileList.length; i++) {
			formData.append("uploadFile", fileList[i]);
		} */

		// 준비된 데이터를 서버에 전송하는 코드 ajax
		
		$.ajax({
			url: '/admin/uploadAjaxAction',
			processData : false,
			contentType : false,
			data : formData,
			type : 'POST',
			dataType : 'json',
			success : function(result) {
				console.log(result);
				showUploadImage(result);
			},
			error : function(result) {
				alert("이미지 파일이 아닙니다.");
			}
			
		});
		
		
		
		
	});
</script>
<script>
	
	/* 이미지 삭제 버튼 동작 */
	$("#uploadResult").on("click", ".imgDeleteBtn", function(e){
		
		deleteFile();
		
	});
	

	/* 파일 삭제 메서드 */
	function deleteFile(){
		
		let targetFile = $(".imgDeleteBtn").data("file");
		
		let targetDiv = $("#result_card");
		
		$.ajax({
			url: '/admin/deleteFile',
			data : {fileName : targetFile},
			dataType : 'text',
			type : 'POST',
			success : function(result){
				console.log(result);
				
				targetDiv.remove();
				$("input[type='file']").val("");
				
			},
			error : function(result){
				console.log(result);
				
				alert("파일을 삭제하지 못하였습니다.")
			}
		});
	}
	


	//==================  상세 이미지 - 기존 이미지 출력 =============================

		/* 기존 이미지 출력 */
		let no = '<c:out value="${product.no}"/>';
		let uploadResult = $("#uploadResult");
		
		$.getJSON("/getAttachList", {no : no}, function(arr) {
				
			console.log(arr);
			if(arr.length === 0) {
				
				let str = "";
				str += "<div id='result_card'>";
				str += "<img src='/resources/imgs/potato.jpg'>";
				str += "</div>";
				
				uploadResult.html(str);
				return;
			}
			
			let str = "";
			let obj = arr[0];
			
			let fileCallPath = encodeURIComponent(obj.uploadPath+"/s"+obj.uuid+"_"+obj.fileName);
			str += "<div id='result_card'";
			str += "data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data- fileName='"+obj.fileName+"'";
			str += ">";
			str += "<img src='/display?fileName="+fileCallPath+"'>";
			str += "<div class='imgDeleteBtn' data-file='" + fileCallPath + "'>x</div>";
			str += "<input type='hidden' name='imageList[0].fileName' value='"+ obj.fileName +"'>";
			str += "<input type='hidden' name='imageList[0].uuid' value='"+ obj.uuid +"'>";
			str += "<input type='hidden' name='imageList[0].uploadPath' value='"+ obj.uploadPath +"'>";				
			str += "</div>";
			
			uploadResult.html(str);		
			
		}); // GetJSON	
	
</script>
<script>
	/* 카테고리 구현 */
/* 	$(document).ready(function() {
		console.log('${cateList}');
		
	}); */
	
	/* 카테고리 */
	let cateList = JSON.parse('${cateList}');
	
	let cate1Array = new Array();
	let cate2Array = new Array();
	let cate1Obj = new Object();
	let cate2Obj = new Object();
	
	let cateSelect1 = $(".category1");
	let cateSelect2 = $(".category2");
	
	/* 카테고리 배열 초기화 메서드 */
	function makeCateArray(obj, array, cateList, tier) {
		
		for(let i = 0; i <cateList.length; i++) {
			if(cateList[i].tier === tier) {
				obj = new Object();
				
				obj.name = cateList[i].name;
				obj.code = cateList[i].code;
				obj.parent = cateList[i].parent;
				
				array.push(obj);
				
			} //if
			
		}// for
		
	} // end function
	
	/* 배열 초기화 */
	makeCateArray(cate1Obj, cate1Array, cateList, 1);
	makeCateArray(cate2Obj, cate2Array, cateList, 2);
	
/* 	$(document).ready(function() {
		
		console.log(cate1Array);
		console.log(cate2Array);
	}); */
	
	/* 대분류 태그 */
	for(let i = 0; i < cate1Array.length; i++){
		cateSelect1.append("<option value='"+cate1Array[i].code+"'>" + cate1Array[i].name + "</option>");
	}
	
	
	/* 중분류 <option> 태그 */
	$(cateSelect1).on("change",function(){
		
		let selectVal1 = $(this).find("option:selected").val();	
		
		cateSelect2.children().remove();
		
		cateSelect2.append("<option value='none'>선택하기</option>");
		
		for(let i = 0; i < cate2Array.length; i++){
			if(selectVal1 === cate2Array[i].parent){
				cateSelect2.append("<option value='"+cate2Array[i].code+"'>" + cate2Array[i].name + "</option>");	
			}
		}// for
		
	});

</script>
</html>