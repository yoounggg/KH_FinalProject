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
            width : 700px;
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

        p {
            padding-left: 20px;
            font-size: 16;
        }


        #info {
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
	  
	 a:link {color:black; text-decoration: none;}
	 a:visited {color:black; text-decoration: none;}
/* 	 a:hover {color:black; text-decoration: underline;} */
        

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
                            <p>카테고리1</p>
                        </div>
                        <div class="box3">
                            <select>
                                <option value="">선택하기</option>
                                <option value="${product.category1}">농가</option>
                            </select>
                        </div>                      
                    </div>
                    <br>


                    <div class="box1">
                        <div class="box2">
                            <p>카테고리2</p>
                        </div>
                        <div class="box3">
                            <select>
                                <option value="">선택하기</option>
                                <option value="${product.category2}">오늘의과일채소</option>
                                <option value="${product.category2}">국내외과일</option>
                                <option value="${product.category2}">친환경유기농채소</option>
                                <option value="${product.category2}">우리땅채소</option>
                                <option value="${product.category2}">채소/샐러드</option>
                                <option value="${product.category2}">주곡/잡곡</option>
                                <option value="${product.category2}">오늘의특가</option>
                                <option value="${product.category2}">신상품</option>
    
                            </select>
                        </div>                      
                    </div>
                    <br>


                    <div class="box1">
                        <div class="box2">
                            <p>상품이름</p>
                        </div>
                        <div class="box3">
                            <input type="text"  id="info">
                        </div>                      
                    </div>
                    <br>

                    <div class="box1">
                        <div class="box2">
                            <p>상품 가격</p>
                        </div>
                        <div class="box3">
                            <input type="text"  id="info">
                        </div>         
                    </div>
                    <br>

                    <div class="box1">
                        <div class="box2">
                            <p>할인(%)</p>
                        </div>
                        <div class="box3">
                            <input type="text"  id="info">
                        </div>         
                    </div>
                    <br>

                    <div class="box1">
                        <div class="box2">
                            <p>할인된 가격</p>
                        </div>
                        <div class="box3">
                            <input type="text"  id="info">
                        </div>         
                    </div>
                    <br>

                    <div class="box1">
                        <div class="box2">
                            <p>중량</p>
                        </div>
                        <div class="box3">
                            <input type="text"  id="info">
                        </div>         
                    </div>
                    <br>

                    <div class="box1">
                        <div class="box2">
                            <p>원산지</p>
                        </div>
                        <div class="box3">
                            <input type="text"  id="info">
                        </div>         
                    </div>
                    <br>

                    <div class="box1">
                        <div class="box2">
                            <p>재고수량</p>
                        </div>
                        <div class="box3">
                            <input type="text"  id="info">
                        </div>         
                    </div>
                    <br>

                    <!-- <p>농가업체</p> -->
                    <input type="hidden"  id="info">

                    <div class="box1">
                        <div class="box2">
                            <p>메인 이미지</p>
                        </div>
                        <div class="box3">
                            <input type="text"  id="info">
                        </div>         
                    </div>
                    <br>

                    <div class="box1">
                        <div class="box2">
                            <p>서브 이미지1</p>
                        </div>
                        <div class="box3">
                            <input type="text"  id="info">
                        </div>         
                    </div>
                    <br>
                
                    <div class="box1">
                        <div class="box2">
                            <p>서브 이미지2</p>
                        </div>
                        <div class="box3">
                            <input type="text"  id="info">
                        </div>         
                    </div>
                    <br>
                    
                    <div class="box1">
                        <div class="box2">
                            <p>서브 이미지3</p>
                        </div>
                        <div class="box3">
                            <input type="text"  id="info">
                        </div>         
                    </div>
                    <br>

                    <div class="box1">
                        <div class="box2">
                            <p>서브 이미지4</p>
                        </div>
                        <div class="box3">
                            <input type="text"  id="info">
                        </div>         
                    </div>
                    <br>

                    <div class="box1">
                        <div class="box2">
                            <p>상세정보내용</p>
                        </div>
                        <div class="box3">
                            <input type="text"  id="info">
                        </div>         
                    </div>
                    <br>

                    <div class="box1">
                        <div class="box2">
                            <p>상세정보내용 이미지</p>
                        </div>
                        <div class="box3">
                            <input type="text"  id="info">
                        </div>         
                    </div>
                    <br>


                    <div class="product_button">
                        <button type="button" id="modifyBtn">등록</button>
                        <button type="button" id="listBtn">목록</button>
                    </div>
                 </form>
            </div>
            
            
			<div class="pageInfo_wrap" >
			  <form id=moveForm method="get">
			    <div class="pageInfo_area">
			    	<ul id="pageInfo" class="pageInfo">
			    	
			    		<!-- 이전페이지 버튼 -->
			            <c:if test="${pageMaker.prev}">
			                <li class="pageInfo_btn previous"><a href="${pageMaker.startPage-1}">Previous</a></li>
			            </c:if>
			            
		            	<!-- 각 번호 페이지 버튼 -->
		                <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
		                    <li class="pageInfo_btn "><a href="/admin/farm/list?currPage=${num}&amount=${pageMaker.cri.amount}">${num}</a></li>
		                </c:forEach>
			                
			            <!-- 다음페이지 버튼 -->
			            <c:if test="${pageMaker.next}">
			                <li class="pageInfo_btn next"><a href="${pageMaker.endPage + 1 }">Next</a></li>
			            </c:if>  
		             </ul>
		            
            	</div>
            
            
	            <div class="button1">
	 				<button type="button" id="registerBtn">등록</button>
	                <button type="button" id="removeBtn">삭제</button>
	            </div>

			        <input type="hidden" name="currPage" value="${pageMaker.cri.currPage }">
			        <input type="hidden" name="amount" value="${pageMaker.cri.amount }">
		        
            </form>
           </div>
        </div>
        

    </div>
</main>

    
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>

<script>

//  ================= 공지사항 목록 전체선택 / 전체해제 / 체크 삭제 ========================= 
//  ================== 1. 전체선택, 전체해제 체크박스 =============================

    function selectAll(selectAll)  {
        const checkboxes 
        = document.getElementsByName("item");

        checkboxes.forEach((checkbox) => {
            checkbox.checked = selectAll.checked;
        })

    }

//  ================== 2. 하나라도 선택해제 될 경우 전체선택 해제 =============================

    function checkSelectAll()   {
        // 전체 체크박스
        const checkboxes 
            = document.querySelectorAll('input[name="item"]');
        // 선택된 체크박스
        const checked 
            = document.querySelectorAll('input[name="item"]:checked');
        // select all 체크박스
        const selectAll 
            = document.querySelector('input[name="selectall"]');

        // 만약 체크박스가 하나라도 선택해제되면 전체선택 해제 
        if(checkboxes.length === checked.length)  {
            selectAll.checked = true;
        }else {
            selectAll.checked = false;
        }

    }

</script>

<script>	
//  ================== 5. 버튼을 클릭하면 이동함. =============================

	
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
        
 </script>
 <script>      
     
 	// 페이지 이동 번호가 동작!
 	
        /*  let moveForm = ${"#moveForm"}; 
        
         $(".move").on("click", function(e) {
        	e.preventDefault();
        	
        	moveForm.append("<input type='hidden' name='no' value='"+(this).attr("href")"'>");
/*         	moveForm.attr("action", "/notice/get");
        	moveForm.submit(); */
        	
/*         	moveForm.setAttribute('method', 'POST');
        	moveForm.setAttribute('action', '/notice/get');
        	moveForm.submit();      	
        	
        });  */
        

        /*$(".pageInfo a").on("click", function(e){
        	 
            e.preventDefault();
            moveForm.find("input[name='currPage']").val($(this).attr("href"));
            moveForm.attr("action", "/notice/list");
            moveForm.submit();
            
        }); */
        
        

</script>

</html>