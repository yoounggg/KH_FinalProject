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
    <link rel="stylesheet" href="/resources/css/admin/get.css">
    <link rel="stylesheet" href="/resources/css/admin/product_get.css">
    <!-- include favicon -->
	<%@include file="/WEB-INF/views/common/favicon.jsp" %>
   	<script src="https://cdn.ckeditor.com/ckeditor5/37.0.1/classic/ckeditor.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>
</head>
<style>
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
</style>
<body>

<!-- 헤더 - 로그인/로그아웃 로고, 관리자페이지 메인글씨 -->
<%@include file= "/WEB-INF/views/admin/common/header.jsp" %> 

<!-- 메인 - 보라색 가로줄(메인페이지글자), 왼쪽 메뉴 -->
<main>
    <div class="sub_content">        
<!-- 메인 - 공지사항 글 목록 테이블, 이동페이지, 수정, 삭제 -->            
                
        <div class="content">
            <h2>상품상세</h2>
            <div class="write">
            	<form>
            	<div class="product">
          		<input type="hidden" name="no" value="${product.no}">
                       <div class="box1">
                           <div class="box2">
                               <p>카테고리</p>
                           </div>
                           <div class="box3">
								<p name="category2" id="category2">${product.category}</p>
                           </div>                      
                       </div>
                       <br>
                       
                       
                       <div class="box1">
                           <div class="box2">
                               <p>농장 업체</p>
                           </div>
                           <div class="box3">
                               <input type="text"  name="product_no" value="1" id="info" readonly>
                           </div>                      
                       </div>
                       <br>
   
   
                       <div class="box1">
                           <div class="box2">
                               <p>상품이름</p>
                           </div>
                           <div class="box3">
                               <input type="text"  name="name" id="info" value="${product.name}" readonly>
                           </div>                      
                       </div>
                       <br>
                       
                       <div class="box1">
                           <div class="box2">
                               <p>타이틀-레시피('사과, 오렌지' 처럼 과일 명만 적기)</p>
                           </div>
                           <div class="box3">
                               <input type="text"  name="title" id="info" value="${product.title}" readonly>
                           </div>                      
                       </div>
                       <br>
                       
                       
   
                       <div class="box1">
                           <div class="box2">
                               <p>상품 가격</p>
                           </div>
                           <div class="box3">
                               <input type="text"  name="price"  id="price" value="${product.price}" readonly>
                           </div>         
                       </div>
                       <br>
   
                       <div class="box1">
                           <div class="box2">
                               <p>할인(%)</p>
                           </div>
                           <div class="box3">
                               <input type="text"  name="discount" maxlength="2" id="discount" value="${product.discount}" readonly>
                               <input type="hidden" name="calc"  value="${product.discount}">
                           </div>        
                       </div>
                       <br>
   
                       <div class="box1">
                           <div class="box2">
                               <p>할인된 가격</p>
                           </div>
                           <div class="box3">
                              <!--  <input type="text" name="discount_price"  id="discount_price" class="discount_price"> -->
                        <p name="discount_price" id="discount_price" class="discount_price" >${product.discount_price}</p>
                        <input type="hidden" name="discount_price"  id="discount_price" value="${product.discount_price}" class="discount_price">
                           </div>         
                       </div>
                       <br>
   
                       <div class="box1">
                           <div class="box2">
                               <p>중량</p>
                           </div>
                           <div class="box3">
                               <input type="text"  name="weight"  id="info" value="${product.weight}" readonly>
                           </div>         
                       </div>
                       <br>
   
                       <div class="box1">
                           <div class="box2">
                               <p>원산지</p>
                           </div>
                           <div class="box3">
                               <input type="text"  name="origin"  id="info" value="${product.origin}" readonly>
                           </div>         
                       </div>
                       <br>
   
                       <div class="box1">
                           <div class="box2">
                               <p>재고수량</p>
                           </div>
                           <div class="box3">
                               <input type="text"  name="stock"  id="info" value="${product.stock}" readonly>
                           </div>         
                       </div>
                       <br>
   
                       <!-- <p>농가업체</p> -->
                       <input type="hidden"  name="product_no"  id="info" >
                       
                       
                       	<div class="box1">
                       	 	<div class="box2">
                               <p>메인 이미지</p>
		                    </div>
                           <div class="box3">
	                        <div class="select_image">
	                        	<img src="/resources/product/${product.main_image}" id="main_image" style="width:200px" /></div>
							</div>
						</div>
                     	<br>
                     
   
                       <div class="box1">
                           <div class="box2">
                               <p>서브 이미지1</p>
                           </div>
                           <div class="box3">
                        	<div class="select_image">
	                        	<img src="/resources/product/${product.sub_image1}" id="sub_image1" style="width:200px"/>
                        	</div>
                           </div>
                       </div>
                       <br>
                   
                   
                       <div class="box1">
                           <div class="box2">
                               <p>서브 이미지2</p>
                           </div>
                           <div class="box3">
                        		<div class="select_image">
                        			<img src="/resources/product/${product.sub_image2}" id="sub_image2" style="width:200px"/>
                        		</div>
                             </div>             
                       </div>
                       <br>
                       
                       
                       <div class="box1">
                           <div class="box2">
                               <p>서브 이미지3</p>
                           <div class="box3">
		                        <div class="select_image">
		                        	<img src="/resources/product/${product.sub_image3}" id="sub_image3" style="width:200px" />
		                        </div>
							</div>           
                       </div>
                       </div>
                       <br>
   
   
                       <div class="box1">
							<div class="box2">
								<p>서브 이미지4</p>
							</div>
							<div class="box3">
	                        	<div class="select_image">
	                        		<img src="/resources/product/${product.sub_image4}" id="sub_image4" style="width:200px"/>
	                        	</div>
							</div>
                       </div>
                       <br>
   
   
                       <div class="box4">
                           <div class="box2">
                               <p>상세정보내용</p>
                           </div>
                           <div class="box41">
                               <textarea name="content" id="info_textarea"  readonly>${product.content}</textarea>
                           </div>         
                       </div>
                       <br>
   
                       <div class="box1">
                           <div class="box2">
                               <p>상세정보내용 이미지</p>
                           </div>
							<div class="box3">
	                        	<div class="select_image">
	                        		<img src="/resources/product/${product.content_image}" id="content_image" style="width:500px"/>
	                        	</div>
							</div>
                       </div>
                       <br>
                    </div>
					<div class="button">
                        <button type="button" id="modifyBtn">수정</button>
                        <button type="button" id="removeBtn">삭제</button>
                        <button type="button" id="listBtn">목록</button>
                    </div>
                 </form>
            </div>
            
        </div>
        

    </div>
</main>

    
</body>
<script>
	var listBtn = document.querySelector('#listBtn');
	var modifyBtn = document.querySelector('#modifyBtn');
	var removeBtn = document.querySelector('#removeBtn');
	
	//목록 버튼을 눌러 목록으로 돌아가기
	listBtn.addEventListener('click', function() {
		console.log('listBtn clicked');
		location.href='/admin/product/list';
	});
	
	// 수정 버튼을 눌러 수정페이지로 가기
	modifyBtn.addEventListener('click', function() {
		console.log('modifyBtn clicked');
		self.location="/admin/product/modify?no=${product.no}";
	});
	
	
	// 삭제 버튼을 눌러 목록으로 돌아가기
    removeBtn.addEventListener('click', function(){
        console.log('removeBtn clicked ㅇ_<');

        //form 태그를 조작해서 삭제요청을 전송! 
        var form = document.querySelector('form');
        console.log(form.constructor.prototype);


        form.setAttribute('method', 'POST');
        form.setAttribute('action', '/admin/product/remove');
        form.submit();


});
    
    
</script>
</html>