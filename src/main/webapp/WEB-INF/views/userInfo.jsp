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
    
    <!-- include favicon -->
    <%@include file="/WEB-INF/views/common/favicon.jsp" %>

    <link rel="stylesheet" href="/resources/css/userInfo.css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">

    <script src="https://kit.fontawesome.com/1fe7ba446e.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>

	<!-- 카카오 주소 api -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

    <script src="/resources/js/userInfo.js"></script>
</head>
<body>
     <div class="topBanner">
        <div class="topbox">
            <div class="topcontent">
                    모두의 야채, 모두의 과일<br>
                    싱싱한 농산물을 합리적인 가격에 제공하는 직거래 유통 마켓
            </div>
            <button type="button" class="topBannerBtn">X</button>
        </div>
    </div>

    <header>    
            <ul class="container2">
            
            	<!--로그인 x--><!--로그인 성공하면 세션에 사용자 정보를 저장하는 model상자의 이름 뭐지? 우선 member로 기재-->
                <c:if test = "${member == null}">
                    <li><a href="/user/login">로그인</a></li>
                    <li><a href="/signup/main">회원가입</a></li>
                    <li><a href=/cart/main>장바구니</a></li>
                    <!-- <li><a href="/cart/${member.member_id}">장바구니</a></li> -->
                </c:if> 
                
                <!--로그인 O -->
                <c:if test = "${member != null}">

                        <!-- 관리자 계정 -->
                        <c:if test="${member.adminCk = 1}">
                            <li><a href="/admin/main">관리자 페이지</a></li>
                        </c:if> 
                        
               		<li>${member.name}님 환영합니다.</li>
                    <!-- <li><a href="/logout">로그아웃</a></li> -->
                    <!-- => 비동기 방식 로그아웃으로 변경 -->
                    <li><a id="logout_button">로그아웃</a></li>
                    <li><a href="/mypage">마이페이지</a></li>
                    <li><a href="/cart/main">장바구니</a></li>
                    <!-- 로그인이 되야 아래가 적용될듯? -->
                    <!-- <li><a href="/cart/${member.member_id}">장바구니</a></li> -->
                    
                </c:if>       
            </ul>

            <div class="container3">
                <a href="/main"><img id="logo" src="/resources/imgs/logo.png" alt="로고"></a>
        
                <form class="search1" action="#" method="GET">
                    <input class="search-txt" type="text" placeholder=" 검색어를 입력해주세요!">    
                    <button class="search-btn" type="submit"><img id="btn" src="/resources/imgs/search.png" alt="메인검색버튼"></button>
                </form>   
                 
            </div>
    </header>

    <nav> 
        <div class="menu">
            <ul class="container4">
                <li><a href="#"><i class="fas fa-bars"> 전체 카테고리</i></a>
                    <ul class="menu2">
                        <li><a href="#">농산 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; > </a>
                            <ul class="menu3">
                                <li><a href="#">전체보기</a></li>
                                <li><a href="#">오늘과일채소</a></li>
                                <li><a href="#">국내외과일</a></li>
                                <li><a href="#">친환경유기농채소</a></li>
                                <li><a href="#">우리땅채소</a></li>
                                <li><a href="#">채소/샐러드</a></li>
                                <li><a href="#">주곡/잡곡</a></li>
                                <li><a href="#">오늘의 특가</a></li>
                                <li><a href="#">신상품</a></li>
                            </ul>
                        </li>
                    </ul>
                <li><a href="#">오늘의 특가</a></li>
                <li><a href="#">신상품</a></li>
                <li><a href="#">고객센터</a></li>
            </ul>
        </div>
    </nav>

<!-- --------------위에는 헤더----------- -->

<main>
    <aside>
        <ul class="box1">
            <img src="/resources/imgs/profileimg.jpg" alt="프로필 사진">
            <li class="small1">"$"{member.name} 님</li>
            <li class="small1"><a href="/mypage/userInfo"><i class="fab fa-whmcs"></i>회원정보관리</a></li>
        </ul>

        <ul class="box2">
            <li class="small2 small3"><a href="/mypage/userInfo">정보수정</a></li>
            <li class="small2 small3"><a href="#">주문내역</a></li>
            <li class="small2"><a href="#">배송현황</a></li>
        </ul>
    </aside>
    
    
    <div class="huiwon">
        <div class="sujeong1">
            <h2 id="sujeong2">회원정보수정</h2>
            <h6 id="sujeong3"><span class="red">*</span>필수 입력</h6>
        </div>
       
        <form action="update" method="post">
        <input type = "hidden" name="id" value="{member.id}" />
        <table>
            <tr>
                <th>&nbsp;아이디</th>
                <td><input type="text" value="{member.id}" readonly>"$"{member.id}</td>
            </tr>
            <tr>
                <th>&nbsp;현재 비밀번호</th>
                <td><input type="password" value=""></td>
                <td class="sletter">&nbsp;*비밀번호를 입력해 주세요.</td>
            </tr>
            <tr>
                <th>&nbsp;신규 비밀번호<span class="red">*</span></th>
                <td><input type="password" value=""></td>
                <td class="sletter" colspan="2">&nbsp;*영문, 숫자 조합 6~15자리로 비밀번호를 입력해주세요.</td>
            </tr>
            <tr>
                <th>&nbsp;신규 비밀번호 확인<span class="red">*</span></th>
                <td><input type="password" value=""></td>
                <td class="sletter" colspan="2">&nbsp;*비밀번호 확인을 위해 한 번 더 입력해 주세요.</td>
            </tr>
            <tr>
                <th>&nbsp;성명<span class="red">*</span></th>
                <td><input type="text" value="{member.name}" readonly>"$"{member.name}</td>
            </tr>
            <tr class="specialtr">
                <th class="th_height" rowspan="2">&nbsp;휴대전화<span class="red">*</span></th>
                <td><input type="text"></td>
                <td><input type="button" value="휴대폰 인증" class="btntr">&nbsp;<input type="button" value="재발송" class="btntr"></td>
            </tr>
            <tr>
                <td><input type="text"></td>
                <td><input type="button" value="확인" class="btntr"></td>
            </tr>
            <tr class="specialtr">
                <th class="th_height2" rowspan="3">&nbsp;주소<span class="red">*</span></th>
                <td><input type="text" class="addrInput1 addrtr" readonly = "readonly" id="sample6_postcode"></td>
                <td><input type="button" value="우편번호 찾기" class="btntr" onclick="kakaoAdress()"></td>
            </tr>
 			<tr class="addr_tr">
                <td colspan="2"><input type="text" id="sample6_address" class="addrwidth"></td>
            </tr>
            <tr>
                <td><input type="text" class="addrInput2" id="sample6_detailAddress"></td>
            </tr>
            <tr>
                <th>&nbsp;이메일<span class="red">*</span></th>
                <td><input type="text" value="{member.email}" readonly>"$"{member.email}</td>
            </tr>
            <tr>
                <!-- 성별 변경 불가능 -->
                <!-- <form action="#" method="get">-->
                <th>&nbsp;성별</form></th>
                <td class="radio">
                    <input type="radio" name="성별" value="남자" ${member.gender eq '남자' ? 'checked' : ''} />남자&nbsp;
                    <input type="radio" name="성별" value="여자" ${member.gender eq '여자' ? 'checked' : ''} />여자&nbsp;
                    <input type="radio" name="성별" value="선택안함" ${member.gender eq '선택안함' ? 'checked' : ''} checked>선택안함
                </td>
                </form>
            </tr>
            <tr>
                <!-- 생년월일 변경 불가능 -->
                <th>&nbsp;생년원일</th>
                <td>"$"{member.birth_date}</td>
            </tr>
        </table>

        <div class="btnset">
            <input class="userbtn1" type="submit" value="정보수정">
            <input class="userbtn2" type="button" value="메인으로" onClick="location.href='/main'">
            <button class="userbtn2" type="button" onClick="removeMember();">회원탈퇴</button>
        </div>    
        
    </div>    
</main>

    <!-- ---------------footer---------------- -->
    <footer>
        <div class="yakgwan1">
            <div class="yakgwan2">
                <li><a href="#">고객센터</a></li>
                <li><a href="#">약관 및 정책</a></li>
                <li><a id="gaein" href="#">개인정보 취급방침</a></li>
            </div>
        </div>

        <div class="bottom1">
            <div class="bottom2">
                <div id="com_address">
                    <p id="jusik">(주)모야모과</p>
                    <p class="gray">강남지원 1관 : 서울특별시 강남구 테헤란로14길 6 남도빌딩<br>
                        상호: 주식회사 모야모과 대표자:요셉킴 사업자등록번호: 123-45-67891<br>
                        통신판매업신고번호: 제2023-서울강남-0208</p>
                    <p></p>
                    <p class="gray">Copyright ⓒ MOYAMOGA Corp. All Rights Reserved.</p>
                </div>
                <div id="custom_notice">
                    <p id="gogaek">고객센터 <span id="phnum">1588-1234</span></p>
                    <p></p>
                    <p class="gray">평일 07:00~18:00 / 토, 일요일 09:00~12:00<br>
                        점심시간 12:00~13:00</p>
                    <a href="#">고객센터 바로가기 ></a>
                </div>
            </div>
        </div>
    </footer>
</body>
</html>