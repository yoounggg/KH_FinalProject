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
    <title>공지사항 목록</title>
    <!-- include favicon -->
	<%@include file="/WEB-INF/views/common/favicon.jsp" %>
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
            <table id="list">
                <h2>공지사항</h2>
                <thead>
                <form action="/admin/notice/search" id="searchForm" method="get">
                	<div class="search1">
	                	<input type="text" id="keyword" name="keyword" >
	                	<button id="search">검색</button>
                	</div>
	            </form>
	                <tr>
	                    <th><input type="checkbox" name="selectall" value="selectall" onclick="selectAll(this)"></th>
	                    <th>번호</th>
	                    <th>제목</th>
	                    <th>작성날짜</th>
	                    <th>수정날짜</th>
	                    <th></th>
	                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list}" var="NoticeDTO">
                        <tr>
                        	<td><input type="checkbox" name="item" onclick="checkSelectAll()" /></td>
                            <td>${NoticeDTO.no}</td>
                            <td><a href="/admin/notice/get?no=${NoticeDTO.no}">${NoticeDTO.title}</a></td>
                            <td><fmt:formatDate pattern="yyyy/MM/dd" value="${NoticeDTO.reg_date}"/></td>
                            <td><fmt:formatDate pattern="yyyy/MM/dd" value="${NoticeDTO.update_date}"/></td>
                            <td><input type="hidden" name="no" value="${NoticeDTO.no}"></td>
                        </tr>       
                    </c:forEach>    
                </tbody>
            </table>
            
            
		 				<form>
		 		<div class="pageInfo_wrap" >
					    <div class="pageInfo_area">
					    	<ul id="pageInfo" class="pageInfo">
					    		
					    		
					    		<!-- 이전페이지 버튼 -->
					            <c:if test="${pageMaker.prev}" >
					                <li class="pageInfo_btn previous"><a href="/admin/notice/list?currPage=${pageMaker.startPage-1}">Previous</a></li>
					            </c:if>
					            
				            	<!-- 각 번호 페이지 버튼 -->
				                <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
				                    <li class="pageInfo_btn "><a href="/admin/notice/list?currPage=${num}&amount=${pageMaker.cri.amount}">${num}</a></li>
				                </c:forEach>
				             
					            <!-- 다음페이지 버튼 -->
					            <c:if test="${pageMaker.next}" >
					                <li class="pageInfo_btn next"><a href="/admin/notice/list?currPage=${pageMaker.endPage + 1}&amount=${pageMaker.cri.amount}">Next</a></li>
					            </c:if>  
				             </ul>
								<div class="button1">
									<button type="button" id="registerBtn">등록</button>
									<button type="button" id="removeBtn">삭제</button>
								</div>
		            	</div>
						<input type="hidden" name="currPage" value="${pageMaker.cri.currPage}">
						<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
						<input type="hidden" name="pagePerPage">
						<input type="hidden" name="type">
						<input type="hidden" name="keyworkd">
	           
	           
	           </div>
	    </form>
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
            location = '/admin/notice/register';
        }); // registerBtn
       
       

        // 글 삭제!! - 체크박스
    	document.querySelector('#removeBtn').addEventListener('click', function () {
    	    const checkedItems = document.querySelectorAll('input[name="item"]:checked');
    	    
    	    // 체크된 상품의 번호를 배열로 저장
    	    const checkedItemNos = Array.from(checkedItems).filter((item) => item.checked).map((item) => item.parentNode.parentNode.querySelector('input[name="no"]').value);
    	    
    	    if (checkedItems.length === 0) {
    	        alert("삭제할 게시글을 선택해주세요.");
    	        return;
    	    }
    	    
    	    if (confirm('선택한 게시글을 삭제하시겠습니까?')) {
    	        const form = document.createElement('form');
    	        form.setAttribute('method', 'post');
    	        form.setAttribute('action', '/admin/notice/remove');
    	        checkedItemNos.forEach(checkedItemNo => {
    	            const input = document.createElement('input');
    	            input.setAttribute('type', 'hidden');
    	            input.setAttribute('name', 'no');
    	            input.setAttribute('value', checkedItemNo);
    	            form.appendChild(input);
    	            
    	        });
    	        document.body.appendChild(form);
    	        form.submit();
    	        alert("삭제되었습니다.");
    	    }
    	});
        
 </script>
</html>