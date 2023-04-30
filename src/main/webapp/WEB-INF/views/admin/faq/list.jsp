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
    <title>FAQ 목록</title>
    <link rel="stylesheet" href="/resources/css/admin/common.css">
	<link rel="stylesheet" href="/resources/css/admin/list.css">
</head>

<body>
	<!-- 헤더 - 로그인/로그아웃 로고, 관리자페이지 메인글씨 -->
    <%@include file= "/WEB-INF/views/admin/common/header.jsp" %>      
<!-- 메인 - 보라색 가로줄(메인페이지글자), 왼쪽 메뉴 -->
<main>
    <div class="sub_content"> 
<!-- 메인 - 공지사항 글 목록 테이블, 이동페이지, 수정, 삭제 -->            
                
        <div class="content">
        <form>
            <table>
                <h2>FAQ <span class="test">테스트</span></h2>
                <thead>
	                <tr>
	                    <th><input type="checkbox" name="selectall" value="selectall" onclick="selectAll(this)"></th>
	                    <th>번호</th>
	                    <th>제목</th>
	                    <th>작성날짜</th>
	                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${faqList}" var="FaqVO">
                        <tr>
                        	<td><input type="checkbox" name="item" onclick="checkSelectAll()" /></td>
                            <td>${FaqVO.no}</td>
                            <td><a href="/admin/faq/get?no=${FaqVO.no}">${FaqVO.title}</a></td>
                            <td>${FaqVO.reg_date}</td>
                        </tr>       
                    </c:forEach>    
                </tbody>
            </table>
        </form>    
            
		 		<div class="pageInfo_wrap" >
					    <div class="pageInfo_area">
					    	<ul id="pageInfo" class="pageInfo">
					    	
					    		<!-- 이전페이지 버튼 -->
					            <c:if test="${pageMaker.prev}">
					                <li class="pageInfo_btn previous"><a href="${pageMaker.startPage-1}">Previous</a></li>
					            </c:if>
					            
				            	<!-- 각 번호 페이지 버튼 -->
				                <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
				                    <li class="pageInfo_btn "><a href="/admin/faq/list?currPage=${num}&amount=${pageMaker.cri.amount}">${num}</a></li>
				                </c:forEach>
				             
					            <!-- 다음페이지 버튼 -->
					            <c:if test="${pageMaker.next}">
					                <li class="pageInfo_btn next"><a href="${pageMaker.endPage + 1 }">Next</a></li>
					            </c:if>  
				             </ul>
								<div class="button1">
									<button type="button" id="registerBtn">등록</button>
									<button type="button" id="removeBtn">삭제</button>
								</div>
								
		            	</div>
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
            location = '/admin/faq/register';
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
            form.setAttribute('action', '/admin/faq/remove');
            form.submit();

        }); // removeBtn
        
 </script>


</html>