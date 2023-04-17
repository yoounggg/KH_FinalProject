<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../resources/css/signup/signup_agreement.css">
    <%@include file="/WEB-INF/views/common/favicon.jsp" %>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>
    <script src="../resources/js/signup/signup_agreement.js"></script>
    
    <title>MOYAMOGA</title>



</head>

<body>
    <div id="entire">
<%@include file= "../common/header.jsp" %>
   

    <section>

        <div class='body-wrapper'>


            <div class="탑로고">
                <img id="logo" src="../resources/imgs/logo.png">
            </div>
            <div class="가입단계">
                <div id="가입단계1">약관동의</div>
                <div class="triangle"></div>
                <div id="가입단계2">회원 정보 입력</div>
                <div class="triangle"></div>
                <div id="가입단계3">가입 완료</div>
            </div>

            <div class="메인">


                <div class="이용약관">
                    <div class='이용약관제목'>이용약관(필수)</div>
                    <div class='이용약관틀'>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quaerat aspernatur
                        provident
                        eaque cumque dignissimos molestias! Natus, quibusdam accusantium sequi praesentium molestiae
                        ipsam
                        quos distinctio. Quia dignissimos iste atque ipsum, assumenda sunt modi natus, laborum neque
                        corrupti consequatur numquam voluptatum vero maiores ex velit incidunt quaerat fugiat
                        consequuntur
                        id sapiente, ab recusandae. Illum animi ducimus, aut deleniti alias, aliquam eaque numquam atque
                        placeat sit tempora obcaecati modi expedita sapiente! Ratione officiis veritatis doloremque
                        facere,
                        rem neque? Quod voluptatem fugit atque expedita vel nemo amet totam enim officia, corporis,
                        dolore,
                        omnis saepe pariatur molestias perferendis animi nihil debitis asperiores facilis cumque
                        aperiam!
                        Hic dolore perspiciatis tempore, repellat tenetur quo corporis odit magnam vero ratione veniam
                        voluptas nesciunt. Accusamus eius qui sapiente praesentium veritatis perspiciatis velit est
                        atque
                        molestiae! Nam cumque eligendi ad facere fuga nesciunt quis, maiores ipsa, optio voluptates
                        vitae.
                        Illo ab sed quod, nam nostrum iusto ratione ea consectetur unde, suscipit omnis. Quidem quod sit
                        officiis aliquid laudantium ad ipsa nihil expedita accusantium labore? Eum repellat sunt libero,
                        consectetur praesentium maiores tempore ducimus? Delectus quibusdam perspiciatis inventore alias
                        rerum atque enim magni, sequi, molestias, reiciendis maxime. Quam voluptatum, iure architecto
                        eum
                        quod rerum blanditiis nesciunt dignissimos, repudiandae consequuntur amet accusantium.</div>

                    <form class="이용약관동의체크">
                        <input type="checkbox" id ="체크1" name="agreement" class="check" onclick='checkSelectAll()'>이용약관 동의
                    </form>
                </div>

                <div class="개인정보수집">
                    <div class='개인정보수집및이용동의'>개인정보 수집 및 이용동의(필수)</div>
                    <div class='개인정보수집틀'>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Iste sit facilis
                        amet,
                        totam corrupti nostrum iure. Explicabo quaerat aliquid soluta quo praesentium maxime rerum
                        dolore
                        sunt magni? Porro officia impedit reiciendis sit id quidem magnam fuga molestias nam et harum
                        neque
                        vitae nulla, atque consequuntur doloribus natus accusantium, maiores vero reprehenderit nisi
                        sapiente cumque beatae iusto? Hic quas ullam libero numquam dolorum dicta veritatis reiciendis a
                        est
                        laudantium voluptatibus natus consequuntur vitae quibusdam possimus perspiciatis earum expedita
                        quia
                        atque pariatur ad dolores, at saepe nisi. At incidunt voluptate perferendis natus aliquid quae
                        qui,
                        rerum eius repudiandae dicta facilis molestiae officiis voluptatibus saepe corporis quis quasi.
                        Ducimus repudiandae voluptates sunt vitae, aperiam eos quos! Nam reiciendis beatae sunt a ipsa
                        aperiam temporibus reprehenderit praesentium dolor, inventore commodi provident deleniti, error
                        veritatis assumenda odio dolore, voluptate pariatur. Alias tempore perferendis voluptas
                        aspernatur
                        rem error optio? Odio iure, minima voluptas quos molestias, saepe iste, velit unde distinctio
                        quod
                        quia quaerat blanditiis adipisci ad quibusdam ipsa recusandae quo assumenda repellendus.
                        Veritatis
                        eum assumenda veniam nesciunt unde dicta dolorum. Labore voluptas magnam porro dolores quis rem
                        dolorem ipsam aspernatur maiores autem, ut, doloribus veniam vitae consequatur. In eius totam ex
                        voluptatibus esse. Quibusdam, perferendis cupiditate?</div>

                    <form class="개인정보수집동의체크">
                        <input type="checkbox" id ="체크2" name="agreement" class="check" onclick='checkSelectAll()'>개인정보수집 동의
                    </form>
                </div>

                <form class="전체동의체크">
                    <input type="checkbox" name="selectall" class="check" onclick='selectAll(this)'>전체 동의
                </form>
                
                 <!-- <a id="다음단계" onclick="a()" href="signup/info"><span class="step" disabled>다음단계</span></a> -->
                 
                 <input type="button" onclick="a()" id='다음단계' class='다음단계' value="다음단계">
                <!-- <input type="button" id='이전단계' class='이전단계' value="이전단계">  -->
                 <a id="이전단계"  href="main"><span class="step">이전단계</span></a>
            </div>
        </div>

    </section>

    <%@include file= "../common/footer.jsp" %>
</div>

<div class="popmenu1">
    <p>약관에 동의해주세요</p>
    <input type="button" class="exit1" onclick="exit1()" value="확인">
</div>

</body>

</html>