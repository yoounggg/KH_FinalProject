<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MOYAMOGA</title>
    

    <%@include file="/WEB-INF/views/common/favicon.jsp" %>

    <link rel="stylesheet" href="/resources/css/mypage/modifypw.css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">

    <script src="https://kit.fontawesome.com/1fe7ba446e.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>
    <script src="/resources/js/modifypw.js"></script>
</head>
<body>
	<!-- header -->
	<%@include file= "/WEB-INF/views/common/header.jsp" %>

<!-- --------------위에는 헤더----------- -->

<main>
    <aside class="userinfoAside">
        <ul class="box1">
            <img src="/resources/imgs/profileimg.jpg" alt="프로필 사진">
            <li class="small1">${member.name} 님</li>
            <li class="small1"><a href="/mypage/userInfo/${member.id}"><i class="fab fa-whmcs"></i>회원정보관리</a></li>
        </ul>

        <ul class="box2">
            <li class="small2 small3 small4"><a href="/mypage/userInfo/${member.id}">정보수정</a></li>
            <li class="small2 small3 small4"><a href="/mypage/userInfo/${member.id}/changePw">비밀번호 변경</a></li>
            <li class="small2 small3 small4"><a href="/mypage/?">주문내역</a></li>
            <li class="small2 small5"><a href="/mypage/deliveryConfirm/${member.id}">배송현황</a></li>
        </ul>
    </aside>
    
    
    <div class="huiwon">
        <div class="sujeong1">
            <h2 id="sujeong2">비밀번호 변경</h2>
            <h6><span class="red">*</span>필수 입력</h6>
        </div>
        
        <!-- form 태그 -->
        <form>
            <table>
                <tr>
                    <th>&nbsp;현재 비밀번호<span class="red">*</span></th> 
                    <td><input type="password" id="password1" name ="password" required></td>                  
                    <td><span id="pwChk" class="btntr">확인</span></td>
                </tr>
                
                <tr>
                    <th>&nbsp;신규 비밀번호<span class="red">*</span></th>
                    <td><input type="password" id="newPw" onchange="newPwforDetails()" onclick="checkInput()"></td>
                    <td class="sletter" colspan="2">&nbsp;*대/소문자 구분, 숫자 8~16자리로 입력해주세요.</td>
                </tr>
                
                <tr>
                    <th>&nbsp;신규 비밀번호 확인<span class="red">*</span></th>
                    <td><input type="password" id="checkNewPw" name="checkedNewPw" onchange="confirmNewPw()" ></td>                  
                </tr> 
            </table>
				
	        <div class="btnset">
	        	<input type="button" id="newpwChk" class="userbtn1" value="변경하기" >
	            <input class="userbtn2" type="button" value="메인으로" onClick="location.href='/main'">
	        </div>     
		</form>               
    </div>    

</main>
    <!-- ---------------footer---------------- -->
   <%@include file= "/WEB-INF/views/common/footer.jsp" %>
</body>

<script>
// 현재 비밀번호 확인
$('#pwChk').click(function({id}) {
    $.ajax({
        type: 'POST',
        url: '/mypage/userInfo/' + '${id}' + '/checkPw',
        data: JSON.stringify({password: $('input[name=password]').val()}),
        contentType: 'application/json',
        headers: { 
        	'X-HTTP-Method-Override' : 'POST'
        	},
        dataType: 'json',
        success: function(result) {
            console.log(result);
            if (result == true ) {
                alert("비밀번호가 일치합니다");
            } else if(result == false){
                alert("비밀번호가 불일치합니다. \n다시 입력해주세요.");
            } // else-if
        }, // success
        error: function(error) {
            console.log("error: " + error);
        }
    });
}); // function

//새로운 비밀번호로 변경
$('#newpwChk').click(function({id}){
	//빈칸으로 두면 버튼 작동할 수 없게!
    var password = $('input[name=password]').val();
    var newPw = $('#newPw').val();
    var checkNewPw = $('input[name=checkedNewPw]').val();
    
    if (password == '' || newPw == '' || checkNewPw == '') {
        alert("빈 칸을 모두 채워주세요.");
        return;
    }    
	$.ajax({
		type: 'POST',
		url: '/mypage/userInfo/' + '${id}' + '/changedPw',
        contentType: 'application/json',
        headers: { 
        	'X-HTTP-Method-Override' : 'POST'
        	},
        data: JSON.stringify({password: $('input[name=checkedNewPw]').val()}),
        dataType: 'json',
        success:function(success){
        console.log(success);
	        if(success == true){
	       	alert("비밀번호가 변경되었습니다.");
	       	location.href = '/main';
	        } else if(success == false){
	        alert("비밀번호 입력을 다시 시도하세요.");
	        location.href = '/main';
	        } 
        }, // success		
        error: function(error) {
            console.log("error: " + error);
        }
	});
});

   
</script>

</html>