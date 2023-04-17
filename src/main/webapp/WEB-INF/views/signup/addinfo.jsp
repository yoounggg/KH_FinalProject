<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../resources/css/signup/signup_addinfo.css">
     <%@include file="/WEB-INF/views/common/favicon.jsp" %>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>
    <script src="../resources/js/signup/js/signup_addinfo.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

    <title>MOYAMOGA</title>

</head>

<body>
    
<div id="entire">
	<%@include file= "../common/header.jsp" %>
  

    <section>
        <div class='wrapper'>
            <div class="탑로고">
                <img id="logo" src="../resources/imgs/logo.png">
            </div>

            <h1>추가정보 입력</h1>

            <div class="wrapper-input">
                <div class='line'></div>

                <div class="정보">


                    <div id="addr_form">
                        <label class="라벨">주소</label>
                        <input form="addr_form" type="submit" class='회원정보' id="addr" value="주소검색">
                    </div>

					 <div id="postal_code">
                            <label class="라벨">우편번호</label>
                            <input form="addr_form" type="submit" class='회원정보' id="post_num" >
                     </div>
                        
                    <div id="addr_form2">
                        <label class="라벨">주소</label>
                        <input form="addr_form2" type="submit" class='회원정보' id="addr2" readonly>
                        <input form="id_form" type="submit"  id="addr_btn" value="재검색">
                    </div>

                    <div id="detail_addr_form">
                        <label class="라벨">상세주소</label>
                        <input type="text" class='회원정보' id="detail_addr" placeholder="상세주소를 입력해주세요" >
                    </div>



                    <div id="gender_form" class="성별">
                        <label class="라벨">성별</label>
                        <input type="radio" name="gender" id="남자">남자
                        <input type="radio" name="gender" id="여자">여자
                        <input type="radio" name="gender" id="선택안함" checked>선택안함
                    </div>

                    <div id="birth_form" class="생년월일">
                        <label class="라벨">생년월일</label>
                        <span>
                            <input type="text" class='YYYY' placeholder="YYYY" onblur="validateYYYY(this)">
                            <input type="text" class='MM' placeholder="MM" onblur="validateMM(this)">
                            <input type="text" class='DD' placeholder="DD" onblur="validateDD(this)">
                        </span>
                    </div>
                    
                     <div class="하단버튼">

                            <div id="가입">
                                <a id="가입하기" href="socialComplete"><span class="bottombtn">가입하기</span></a>
                            </div>

                            <div id="다음">
                                <a id="다음에" href="socialComplete"><span class="bottombtn">다음에 입력</span></a>
                            </div>
                        </div>
                </div>
            </div>
        </div>
    </section>

   
 <%@include file= "../common/footer.jsp" %>
</div>

    <div class="popmenu3">
        <p>날짜 형식에 맞게 입력해주세요</p>
        <input type="button" class="exit3" onclick="exit3()" value="확인">
    </div>


</body>

</html>