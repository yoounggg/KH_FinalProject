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

    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">

    <script src="https://kit.fontawesome.com/1fe7ba446e.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>

    <style>

        /* -----정렬------ */
        main{
            padding: 100px 0 150px 0;
            width: 60%;
            margin: 0 auto;
            display: flex;
            flex-flow: row nowrap;
            justify-content: space-evenly;
        }

        /* ---------aside------- */
        .userinfoAside{
            display: flex;
            flex-flow: column wrap;
            margin: 0 auto; 
            position: relative;
            left:-100px;
            /* 왜 밑으로 안가지..? */
            z-index: 97;
        }
        .userinfoAside>ul{
            /*margin: 0;*/
            /*padding: 0;*/
            width: 150px;
            display: inline-block;
            /* border: 1px solid black; */
            text-align: center;
        }
        .userinfoAside>ul>li>a{
            color: black;
        }
        .userinfoAside>ul>li>a:hover{
            text-decoration: underline;
        }
        .box1{
            height: 160px;
            padding-top: 10px;
            border: 1px solid black;
            font-size: 11px;
            background-color: #EFEFEF;
        }
        .box1>img{
            /* padding-top: 10px; */
            width: 100px;
            height: 100px;
            object-fit: cover;
            object-position: top;
            border-radius: 50%;
        }
        .small1{
            padding: 1px;
        }
        .box2{
            height: 210px;
            font-size: 14px;
        }
        .small2{
            /*border: 1px solid black; */
            padding: 25px;
        }
        .small3{
            border-bottom: 1px solid black;
        }
        .small4{
            border-right: 1px solid black;
            border-left: 1px solid black;
        }
        .small5{
            border-right: 1px solid black;
            border-left: 1px solid black;
            border-bottom: 1px solid black;
        }






        .t1 {
            width: 79%;
        }

        h2 {
            margin: 0 0 50px;
        }

        h2 > span {
            color: #11BC0D;
        }

        .orderDate {
            display: none;
        }

        .deliImg {
            width: 800px;
            height: 130px;

            border: 0px solid black;
        }

        .deliImg > li {
            display: inline-block;

            width: 105px;
            height: 115px;

            margin: 0 26px;

            border: 0px solid black;
            text-align: center;
        }

        .deliImg > li > img {
            width: 80px;
            height: 80px;
            /* filter: opacity(0.5) drop-shadow(0 0 0 #11BC0D); */
        }

        .deliImg > li > div {
            font-size: 15px;
        }

        .deliTxt {
            width: 500px;
            height: 500px;
            
            margin: 20px;

            border: 0px solid black;
        }

        .deliTxt > li {
            width: 700px;

            border-bottom: 1px solid black;
            list-style: square;
            padding: 15px 10px;
        }

        .deliTxt > li > img {
            width: 20px;
            height: 20px;
            
        }

        .deliTxt span {
            margin: 0 20px;
        }

        .deliDate1, .deliDate2, .deliDate3, .deliDate4, .deliDate5 {
            padding: 0 40px;
        }
    </style>

</head>
<body>
	<!-- header -->
	<%@include file= "/WEB-INF/views/common/header.jsp" %>

<main>
    <aside class="userinfoAside">
        <ul class="box1">
            <img src="/resources/imgs/profileimg.jpg" alt="프로필 사진">
            <li class="small1">${details.name} 님</li>
            <li class="small1"><a href="/mypage/userInfo/${member.id}"><i class="fab fa-whmcs"></i>회원정보관리</a></li>
        </ul>

        <ul class="box2">
            <li class="small2 small3 small4"><a href="/mypage/userInfo/${member.id}">정보수정</a></li>
            <li class="small2 small3 small4"><a href="/mypage/userInfo/${member.id}/changePw">비밀번호 변경</a></li>
            <li class="small2 small3 small4"><a href="/mypage/orderList/${member.id}">주문내역</a></li>
            <li class="small2 small5"><a href="/mypage/deliveryConfirm/${member.id}">배송현황</a></li>
        </ul>
    </aside>
    
    <div class="t1">
        <c:forEach var="order" items="${__OrderList__}">
            <div class="orderDate">${order.order_date} </div>
            <h2>주문 상품 : <span>${order.name}</span></h2>
            <ul class="deliImg">
                <li class="img1"><img src="/resources/imgs/delipro.png"><div>상품준비</div></li>
                <li class="img2"><img src="/resources/imgs/delicar.png"><div>상품이동중</div></li>
                <li class="img3"><img src="/resources/imgs/delicen.png"><div>배송터미널도착</div></li>
                <li class="img4"><img src="/resources/imgs/delicar1.png"><div>배송출발</div></li>
                <li class="img5"><img src="/resources/imgs/delicon.png"><div>배송완료</div></li>
            </ul>

            <ul class="deliTxt">
                <li><img src="/resources/imgs/delipro.png"><span class="deliTxt1"></span><p class="deliDate1"></p><p class="deliDateConf1"></p></li>
                <li><img src="/resources/imgs/delicar.png"><span class="deliTxt2"></span><p class="deliDate2"></p><p class="deliDateConf2"></p></li>
                <li><img src="/resources/imgs/delicen.png"><span class="deliTxt3"></span><p class="deliDate3"></p><p class="deliDateConf3"></p></li>
                <li><img src="/resources/imgs/delicar1.png"><span class="deliTxt4"></span><p class="deliDate4"></p><p class="deliDateConf4"></p></li>
                <li><img src="/resources/imgs/delicon.png"><span class="deliTxt5"></span><p class="deliDate5"></p><p class="deliDateConf5"></p></li>
            </ul>
            
        </c:forEach>
    </div>

</main>

   <%@include file= "/WEB-INF/views/common/footer.jsp" %>
</body>
</html>

<script>
    // let time = Math.floor(Math.random() * 6 + 5) * 60;   // 5~10 랜덤 숫자
    // let time = Math.floor(Math.random() * 2 + 1) * 60;   // 5~10 랜덤 숫자
    const orderDate = document.querySelector(".orderDate").innerHTML;
    const date = new Date(orderDate);
    let count = 1;
    for(let i=1; i<=5; i++){
        //let rNum = 1; //Math.floor(Math.random() * 2 + 1); // 테스트로 시간은 1~2분 사이로 설정
        //let time = rNum * 60;
        let rNum = 10; 
        let time = 10;
        let min = "";
        let sec = "";

        let x = setInterval( () => { 

            min = parseInt(time / 60, 10);
            sec = time % 60;

           
            if(i == count) {
                switch(count) {
                    case 1: document.querySelector(".deliTxt"+i).innerHTML = "상품이 준비중 입니다. (준비 시간 : " + min + " 분 " + sec + " 초)";
                            document.querySelector(".deliDate"+i).innerHTML = "상품 주문 시간 : " + orderDate;
                            document.querySelector(".img"+i+"> img").style.filter = "opacity(0.5) drop-shadow(0 0 0 #11BC0D)";
                            document.querySelector(".img"+i+"> div").style.color = "#11BC0D";
                            time--;
                            break;
                    case 2: document.querySelector(".deliTxt"+i).innerHTML = "상품이 배송터미널로 출발하였습니다. (도착 시간 까지 " + min + " 분 " + sec + " 초 남았습니다.)";
                            document.querySelector(".img"+i+"> img").style.filter = "opacity(0.5) drop-shadow(0 0 0 #11BC0D)";
                            document.querySelector(".img"+i+"> div").style.color = "#11BC0D";
                            time--;
                            break;
                    case 3: document.querySelector(".deliTxt"+i).innerHTML = "상품이 배송터미널에 도착하였습니다. 상품을 분류중 입니다. (준비 시간 " + min + " 분 " + sec + " 초)";
                            document.querySelector(".img"+i+"> img").style.filter = "opacity(0.5) drop-shadow(0 0 0 #11BC0D)";
                            document.querySelector(".img"+i+"> div").style.color = "#11BC0D";
                            time--;
                            break;
                    case 4: document.querySelector(".deliTxt"+i).innerHTML = "상품이 고객의 주소지로 출발하였습니다. (도착 시간 까지 " + min + " 분 " + sec + " 초 남았습니다.)";
                            document.querySelector(".img"+i+"> img").style.filter = "opacity(0.5) drop-shadow(0 0 0 #11BC0D)";
                            document.querySelector(".img"+i+"> div").style.color = "#11BC0D";
                            time--;
                            break;
                } // switch

                if(count == 5){
                    document.querySelector(".img5 > img").style.filter = "opacity(0.5) drop-shadow(0 0 0 #11BC0D)";
                    document.querySelector(".deliTxt5").innerHTML = "상품이 고객의 주소지에 도착하였습니다.";
                    document.querySelector(".deliDate5").innerHTML = "도착 시간 : " +
                    date.toLocaleDateString().substring(date.toLocaleDateString().length-1,'').replaceAll('. ','-') + " " + date.toLocaleTimeString();
                    document.querySelector(".img"+i+"> div").style.color = "#11BC0D";
                    count++;    
                } //if

            } //if

            if(time < 0){
                clearInterval(x);
                document.querySelector(".img"+i+"> img").style.filter="";
                document.querySelector(".img"+i+"> div").style.color="#000000";
                
                switch(count) {
                    case 1: date.setSeconds(date.getSeconds() + rNum);
                            document.querySelector(".deliTxt"+i).innerHTML = "상품 준비가 완료되었습니다."; 
                            document.querySelector(".deliDate2").innerHTML = "출발 시간 : " +
                                date.toLocaleDateString().substring(date.toLocaleDateString().length-1,'').replaceAll('. ','-') + " " 
                                + date.toLocaleTimeString();
                            count++;
                            break;
                    case 2: date.setSeconds(date.getSeconds() + rNum);
                            document.querySelector(".deliTxt"+i).innerHTML = "상품이 배송터미널로 출발하였습니다."; 
                            document.querySelector(".deliDate3").innerHTML = "도착 시간 : " +
                                date.toLocaleDateString().substring(date.toLocaleDateString().length-1,'').replaceAll('. ','-') + " " 
                                + date.toLocaleTimeString();
                            count++;
                            break;
                    case 3: date.setSeconds(date.getSeconds() + rNum);
                            document.querySelector(".deliTxt"+i).innerHTML = "상품이 배송터미널에 도착 하였습니다.";
                            document.querySelector(".deliDate4").innerHTML = "출발 시간 : " +
                                date.toLocaleDateString().substring(date.toLocaleDateString().length-1,'').replaceAll('. ','-') + " " 
                                + date.toLocaleTimeString();
                            count++;
                            break;
                    case 4: date.setSeconds(date.getSeconds() + rNum);
                    		document.querySelector(".deliTxt"+i).innerHTML = "상품이 고객의 주소지로 출발하였습니다.";
                            count++;
                            break;
                } //switch
                
            } // if
        }, 1000);

    }

</script>