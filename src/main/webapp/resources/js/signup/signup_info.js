// 아이디 유효성 검사
function id_btn() {
    document.getElementById("id_confirm").style.backgroundColor = "#10bc0d";
    document.querySelector("#id_confirm").disabled = false; // 배경, 버튼 활성화

    const input_id = document.getElementById("input_id");
    const value = input_id.value.toLowerCase(); // 입력된 값 소문자로 변환

    input_id.value = value; // 소문자로 변환된 값으로 변경

    const isOk = /^(?=.*[a-z])[a-z0-9]{6,12}$/; // 영문 or 영문숫자 6~12, 소문자만 입력 가능

    if (isOk.test(value)) {
        document.getElementById("id_input_con").style.display = "none";
    } else {
        document.getElementById("id_input_con").style.display = "block";
    }
};

// 아이디 중복 확인
function checkId() {
    var id = $("#input_id").val();    // 아이디 입력값
    const isOk = /^(?=.*[a-zA-Z])[a-zA-Z0-9]{6,12}$/;                   //영문 or 영문숫자 6~12

    if (isOk.test(id)) {

        $.ajax({
            url: "/signup/infoa",
            type: "post",
            data: { id: id },
            dataType: 'json',
            success: function (cntId) {
                if (cntId == 0) {          // 0이면 사용가능 
                    popup_on2();
                    // document.getElementById("input_id").readOnly = "true";            // 아이디 입력창 읽기전용
                    document.getElementById("id_confirm").disabled = "true";          // 중복확인 버튼 비활성화
                    document.getElementById("id_confirm").style.backgroundColor = "#b1b1b1";	// 배경 변경


                } else if (cntId == 1) {                // 1이면 중복
                    popup_on3();
                    $("#input_id").val("");  //중복시 초기화

                }
            },
            error: function (xhr, status, error) { // 에러 콜백 함수
                console.error('AJAX 요청 실패:', status, error);
            }
        });



    } else {
        popup_on1();

    }
};

// 비밀번호 유효성 검사
function pw_input_con() {
    const passwordInput = document.querySelector('#input_pw');
    const passwordCondition = document.querySelector('#pw_input_con');

    const isOk = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,16}$/;    //대소문자 구분 숫자8~16


    if (isOk.test(passwordInput.value)) {
        passwordCondition.style.display = 'none'; // 조건 만족 시 안 보이도록 함
    } else {
        passwordCondition.style.display = 'block';
    }
};

// 비밀번호 확인 유효성 검사 
function confirm_pw_input_con() {
    const input_pw = document.getElementById("input_pw");
    const input_confirm_pw = document.getElementById("input_confirm_pw");
    const confirm_pw_input_con = document.getElementById("confirm_pw_input_con");

    if (input_pw.value != input_confirm_pw.value) {
        confirm_pw_input_con.style.display = "block";
    } else {
        confirm_pw_input_con.style.display = "none";
    }

};

// 이름 유효성 검사
function name_input_con() {
    const nameInput = document.querySelector('#input_name');
    const nameCondition = document.querySelector('#name_input_con');

    const isOk = /^[a-zA-Z가-힣]{2,20}$/;       //한글 영어 2~20자


    if (isOk.test(nameInput.value)) {
        nameCondition.style.display = 'none'; // 조건 만족 시 안 보이도록 함
    } else {
        nameCondition.style.display = 'block';
    }
};


//이메일 유효성 검사
function email_btn() {
    document.getElementById("email_confirm").style.backgroundColor = "#10bc0d";
    document.querySelector("#email_confirm").disabled = false; // 배경, 버튼 활성화

    const emailInput = document.querySelector('#input_email');
    const value = emailInput.value.toLowerCase(); // 입력된 값 소문자로 변환

    emailInput.value = value; // 소문자로 변환된 값으로 변경

    const emailCondition = document.querySelector('#email_input_con');
    var isOk = /^[a-z0-9._-]+@[a-z0-9]+\.[a-z]{2,}$/; // 이메일 형식, 소문자만 입력 가능
	
    if (isOk.test(value)) {
        emailCondition.style.display = 'none'; // 조건 만족 시 안 보이도록 함
    } else {
        emailCondition.style.display = 'block';
    }
};

// 이메일 중복 확인
function checkEmail() {
    var email = $("#input_email").val();       // 이메일 입력값
    var isOk = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9]+\.[a-zA-Z]{2,}$/;         //이메일 형식

    if (isOk.test(email)) {

        $.ajax({
            url: "/signup/infob",
            type: "post",
            data: { email: email },
            dataType: 'json',
            success: function (cntEmail) {
                if (cntEmail == 0) {                   				 // 0이면 사용 가능
                    popup_on4();
                    document.getElementById("input_email").readOnly = "true";            // 이메일 입력창 읽기전용
                    document.getElementById("email_confirm").disabled = "true";          // 중복확인 버튼 비활성화
                    document.getElementById("email_confirm").style.backgroundColor = "#b1b1b1";
                    document.getElementById("input_email").addEventListener("keydown", function (event) {
                        event.preventDefault();     // 키보드 제한  //안먹힘...
                    });
                } else if (cntEmail == 1) {                          // 1이면 이메일 중복
                    popup_on5();
                    $("#input_email").val("");  // 중복시 이메일 입력창 초기화
                }
            },
            error: function (xhr, status, error) { // 에러 콜백 함수
                console.log("AJAX 요청 실패", status, error)
            }
        });
    } else {
        popup_on1();
    }
};


// 휴대폰 번호 유효성 검사
function hp_btn() {
    document.getElementById("hp_confirm").style.backgroundColor = "#10bc0d"
    // document.getElementById("hp_input_con").style.display = "block";
    document.querySelector("#hp_confirm").disabled = false;

    const input_hp = document.getElementById("input_hp");
    const hp_input_con = document.getElementById("hp_input_con");

    const isOk = /^01([016789])(\d{3,4})(\d{4})$/;

    if (isOk.test(input_hp.value)) {
        hp_input_con.style.display = "none";
        document.getElementById("hp_confirm").disabled = false;
    } else {
        hp_input_con.style.display = "block";
    }
};


// 휴대폰 번호 중복 검사 및 인증
var random_num = "";

function checkHp() {
    var tel = $("#input_hp").val();
    const isOk = /^01([016789])(\d{3,4})(\d{4})$/;

    if (!isOk.test(tel)) {
        popup_on25();
    } else {

        $.ajax({
            url: "/signup/infoc",
            type: "post",
            data: { tel: tel },
            dataType: 'json',
            success: function (cntTel) {
                if (cntTel == 0) {            // 0이면 사용 가능한 번호
                    popup_on6();                // 인증번호가 전송되었습니다. 팝업
                    console.log(cntTel);
                    smsSend();                  // 문자 전송함수

                } else if (cntTel == 1) {                  // 1이면 중복된 번호
                    console.log(cntTel);
                    popup_on7();
                    $("#input_hp").val(""); // 휴대폰 번호 창 초기화
                }
            },
            error: function (xhr, status, error) { // 에러 콜백 함수
                console.log("AJAX 요청 실패", status, error)
            }
        });
    };
};
var timer;

function smsSend() {
    var tel = $("#input_hp").val();
    // 문자 전송
    $.ajax({
        type: "get",
        url: "/signup/info/telCheck?tel=" + tel,
        cache: false,
        success: function (randomNumber) {
            if (randomNumber == "error") {
                //에러 떴을때
                ;;
            } else {
                console.log("문자전송")
                document.querySelector("#num_form").style.display = "block";    // 인증번호 창 생성
                document.querySelector("#input_hp").readOnly = "true";          // 휴대폰 번호 입력창 읽기전용
                document.querySelector("#hp_confirm").disabled = "true";         // 인증하기 버튼 비활성화
                document.querySelector("#hp_confirm").style.backgroundColor = "#b1b1b1";  //  배경 변경
                random_num = randomNumber;                                      // 난수 저장

                console.log("인증번호 : " + random_num);

                // 타이머 시작
                var count = 180;  // 180초

                timer = setInterval(function () {
                    count--;
                    var minutes = Math.floor(count / 60);        // 분 계산
                    var seconds = count % 60;                    // 초 계산
                    document.querySelector("#countdown").textContent = minutes + ":" + (seconds < 10 ? "0" : "") + seconds;  // 카운트 다운 업데이트
                    if (count == 0) {  // 타이머 0일때
                        clearInterval(timer);  // 타이머 종료
                        document.querySelector("#input_hp").readOnly = false;     // 휴대폰 번호 입력창 읽기전용 풀기
                        document.querySelector("#hp_confirm").disabled = false;  // 인증하기 버튼 활성화
                        document.querySelector("#hp_confirm").style.backgroundColor = "#10bc0d";
                        popup_on9();  // 인증시간이 만료되었습니다.
                        document.getElementById("num_form").style.display = "none";  // 인증번호 입력창 숨김
                        document.getElementById("input_num").value = "";             // 인증번호 값 초기화
                    }
                }, 1000);  // 1초마다 타이머 작동
            }
        }
    });
};


// 인증번호 입력 함수
function num_btn() {    // 입력시 인증하기 버튼 활성화
    document.getElementById("num_confirm").style.backgroundColor = "#10bc0d";
    document.getElementById("num_confirm").disabled = false;
};

// 인증번호비교
function num_compare() {
    if ($("#input_num").val() == random_num) {           // 입력된 값과 난수 값 같다면
        popup_on10();                                   // 인증되었습니다.
        clearInterval(timer);                           // 타이머 멈춤
        document.getElementById("num_form").style.display = "none";  // 인증번호 입력창 숨김
        document.getElementById("num_confirm").disabled = true;		 // 인증번호 버튼 비활성화
    } else if ($("#input_num").val() != random_num) {   // 다르다면
        popup_on8();                                    // 인증번호가 틀렸습니다.

    }
};

// 주소 검색 
window.onload = function () {
    var addrElem = document.getElementById("addr");
    var addrBtnElem = document.getElementById("addr_btn");

    addrElem.addEventListener("click", openKakaoPostcode);
    addrBtnElem.addEventListener("click", openKakaoPostcode);
};

function openKakaoPostcode() {
    //카카오 주소
    new daum.Postcode({
        oncomplete: function (data) { //선택시 입력값 세팅
            document.getElementById("addr_form").style.display = "none";	// 주소 검색창 안보이게
            document.getElementById("addr_form2").style.display = "block";
            document.getElementById("addr2").value = data.address; // 주소 넣기
            document.getElementById("detail_addr_form").style.display = "block";
            document.querySelector("#detail_addr").focus(); //상세입력 포커싱
            document.getElementById('postal_code').style.display = "block";
            document.getElementById('post_num').value = data.zonecode;  // 우편번호 가져오기
        }
    }).open();
}


// YYYY 유효성 검사 (올해까지)
function validateYYYY(input) {
    const isOk = /^(19|20)\d{2}$/;
    const isValid = input.value === '' || (isOk.test(input.value) && parseInt(input.value) <= new Date().getFullYear());

    if (isValid === true) {

    } else {
        document.querySelector('.popmenu11').style.display = 'block';
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
    }
}

// MM 유효성 검사
function validateMM(input) {
    const isOk = /^(0?[1-9]|1[0-2])$/;
    const isValid = input.value === '' || isOk.test(input.value);

    if (!isValid) {
        document.querySelector('.popmenu11').style.display = 'block';
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
    }
}

// DD 유효성 검사
function validateDD(input) {
    const yyyy = document.querySelector('.YYYY').value;
    const mm = document.querySelector('.MM').value;
    const lastDay = new Date(yyyy, mm, 0).getDate();
    const isOk = /^(0?[1-9]|[1-2][0-9]|3[0-1])$/;
    const isValid = input.value === '' || isOk.test(input.value) && parseInt(input.value) <= lastDay;

    if (!isValid) {
        document.querySelector('.popmenu11').style.display = 'block';
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
    }
}

// 회원가입 기능

$(document).ready(function () {

    const id_btn = document.getElementById("id_confirm");             //아이디 중복체크
    const email_btn = document.getElementById("email_confirm");       //이메일 중복체크
    const tel_btn = document.getElementById("hp_confirm");            //휴대폰 번호 인증
    const num_btn = document.getElementById("num_confirm");            //인증번호 인증

    $("#signup_btn").click(function () {

        const id = document.getElementById("input_id").value;             //아이디
        const pw = document.getElementById("input_pw").value;             //비밀번호
        const pw2 = document.getElementById("input_confirm_pw").value;    //비밀번호 확인
        const name = document.getElementById("input_name").value;         //이름
        const email = document.getElementById("input_email").value;       //이메일
        const tel = document.getElementById("input_hp").value;            //휴대폰 번호
        const num = document.getElementById("input_num").value;           //인증번호
        const addr2 = document.getElementById("addr2").value;             //주소
        const addr3 = document.getElementById("detail_addr").value;       //상세주소

        const isOkPw = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,16}$/;    //대소문자 구분 숫자8~16     
        const isOkName = /^[a-zA-Z가-힣]{2,20}$/;        //한글 영어 2~20자

        if (id.trim() === "") {                         // 아이디 체크
            popup_on12();

        } else if (!id_btn.disabled) {                  // 아이디 중복확인 체크
            popup_on21();

        } else if (pw.trim() === "") {                  // 비밀번호 체크
            popup_on13();

        } else if (!isOkPw.test(pw)) {                  // 비밀번호 유효성 체크
            popup_on1();
            
        } else if (pw2.trim() === "") {                   // 비밀번호 확인 체크 
            popup_on14();

        } else if (pw2 != pw) {                         // 비밀번호 확인 체크 
            popup_on24();

        } else if (name.trim() === "") {                // 이름 체크
            popup_on15();

        } else if (!isOkName.test(name)) {              // 이름 유효성 체크
            popup_on1();

        } else if (email.trim() === "") {               // 이메일 체크
            popup_on16();

        } else if (!email_btn.disabled) {               // 이메일 중복확인 체크
            popup_on22();

        } else if (tel.trim() === "") {                 // 휴대폰 번호 체크
            popup_on17();

        } else if (!tel_btn.disabled) {                 // 휴대폰 인증 체크
            popup_on23();

        } else if (num.trim() === "") {                 // 인증번호 체크
            popup_on18();

        } else if (!num_btn.disabled) {                 // 인증번호 인증 체크
            popup_on26();

        } else if (addr2.trim() === "") {               // 주소 체크
            popup_on19();

        } else if (addr3.trim() === "") {               // 상세 주소 체크
            popup_on20();

        } else {
            $("#signup").attr("action", "/signup/info");        //가입 성공
            $("#signup").submit();

        }
    });
});



// 팝업 1 조건에 맞게 입력해주세요
function popup_on1() {
    $(".popmenu1").show();
    popup_blur1(true);
};

function exit1() {
    $(".popmenu1").hide();
    popup_blur1(false);
};

function popup_blur1(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};



// 팝업 2 사용하실 수 있는 아이디입니다
function popup_on2() {
    $(".popmenu2").show();
    popup_blur2(true);
};

function exit2() {
    $(".popmenu2").hide();
    popup_blur2(false);
};

function popup_blur2(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};

// 팝업 3 중복된 아이디입니다
function popup_on3() {
    $(".popmenu3").show();
    popup_blur3(true);
};

function exit3() {
    $(".popmenu3").hide();
    popup_blur3(false);
};

function popup_blur3(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};

// 팝업 4 사용하실 수 있는 이메일입니다
function popup_on4() {
    $(".popmenu4").show();
    popup_blur4(true);
};

function exit4() {
    $(".popmenu4").hide();
    popup_blur4(false);
};

function popup_blur4(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};

// 팝업 5 중복된 이메일입니다
function popup_on5() {
    $(".popmenu5").show();
    popup_blur5(true);
};

function exit5() {
    $(".popmenu5").hide();
    popup_blur5(false);
};

function popup_blur5(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};

// 팝업 6 인증번호가 전송되었습니다
function popup_on6() {
    $(".popmenu6").show();
    popup_blur6(true);
};

function exit6() {
    $(".popmenu6").hide();
    popup_blur6(false);
};

function popup_blur6(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};

// 팝업 7 중복된 번호입니다
function popup_on7() {
    $(".popmenu7").show();
    popup_blur7(true);
};

function exit7() {
    $(".popmenu7").hide();
    popup_blur7(false);
};

function popup_blur7(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};

// 팝업 8 인증번호가 틀렸습니다
function popup_on8() {
    $(".popmenu8").show();
    popup_blur8(true);
};

function exit8() {
    $(".popmenu8").hide();
    popup_blur8(false);
};

function popup_blur8(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};

// 팝업 9 인증시간이 만료되었습니다
function popup_on9() {
    $(".popmenu9").show();
    popup_blur8(true);
};

function exit9() {
    $(".popmenu9").hide();
    popup_blur9(false);
};

function popup_blur9(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};


// 팝업 10 인증되었습니다
function popup_on10() {
    $(".popmenu10").show();
    popup_blur10(true);
};

function exit10() {
    $(".popmenu10").hide();
    popup_blur10(false);
};

function popup_blur10(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};

// 팝업 11 날짜형식에 맞게 입력해주세요
function popup_on11() {
    $(".popmenu11").show();
    popup_blur11(true);
};

function exit11() {
    $(".popmenu11").hide();
    popup_blur11(false);
};

function popup_blur11(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};



// 팝업 12 아이디를 입력해주세요
function popup_on12() {
    $(".popmenu12").show();
    popup_blur12(true);
};

function exit12() {
    $(".popmenu12").hide();
    popup_blur12(false);
    document.getElementById("input_id").focus();
};

function popup_blur12(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};

// 팝업 13 비밀번호를 입력해주세요
function popup_on13() {
    $(".popmenu13").show();
    popup_blur13(true);
};

function exit13() {
    $(".popmenu13").hide();
    popup_blur13(false);
    document.getElementById("input_pw").focus();
};

function popup_blur13(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};

// 팝업 14 비밀번호를 한 번 더 입력해주세요
function popup_on14() {
    $(".popmenu14").show();
    popup_blur14(true);
};

function exit14() {
    $(".popmenu14").hide();
    popup_blur14(false);
    document.getElementById("input_confirm_pw").focus();
};

function popup_blur14(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};

// 팝업 15 이름을 입력해주세요
function popup_on15() {
    $(".popmenu15").show();
    popup_blur15(true);
};

function exit15() {
    $(".popmenu15").hide();
    popup_blur15(false);
    document.getElementById("input_name").focus();
};

function popup_blur15(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};

// 팝업 16 이메일을 입력해주세요
function popup_on16() {
    $(".popmenu16").show();
    popup_blur16(true);
};

function exit16() {
    $(".popmenu16").hide();
    popup_blur16(false);
    document.getElementById("input_email").focus();
};

function popup_blur16(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};

// 팝업 17 휴대폰 번호를 입력해주세요
function popup_on17() {
    $(".popmenu17").show();
    popup_blur17(true);
};

function exit17() {
    $(".popmenu17").hide();
    popup_blur17(false);
    document.getElementById("input_hp").focus();
};

function popup_blur17(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};

// 팝업18 인증번호를 입력해주세요
function popup_on18() {
    $(".popmenu18").show();
    popup_blur18(true);
};

function exit18() {
    $(".popmenu18").hide();
    popup_blur18(false);
    document.getElementById("input_num").focus();
};

function popup_blur18(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};

// 팝업 19 주소를 입력해주세요
function popup_on19() {
    $(".popmenu19").show();
    popup_blur18(true);
};

function exit19() {
    $(".popmenu19").hide();
    popup_blur19(false);
    document.getElementById("addr2").focus();
};

function popup_blur19(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};


// 팝업 20 상세 주소를 입력해주세요
function popup_on20() {
    $(".popmenu20").show();
    popup_blur20(true);
};

function exit20() {
    $(".popmenu20").hide();
    popup_blur20(false);
    document.getElementById("detail_addr").focus();
};

function popup_blur20(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};

// 팝업 21 아이디 중복확인을 해주세요
function popup_on21() {
    $(".popmenu21").show();
    popup_blur21(true);
};

function exit21() {
    $(".popmenu21").hide();
    popup_blur21(false);
    document.getElementById("id_confirm").focus();
};

function popup_blur21(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};

// 팝업 22 이메일 중복확인을 해주세요
function popup_on22() {
    $(".popmenu22").show();
    popup_blur22(true);
};

function exit22() {
    $(".popmenu22").hide();
    popup_blur22(false);
    document.getElementById("email_confirm").focus();
};

function popup_blur22(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};

// 팝업 23 휴대폰 인증을 해주세요
function popup_on23() {
    $(".popmenu23").show();
    popup_blur23(true);
};

function exit23() {
    $(".popmenu23").hide();
    popup_blur23(false);
    document.getElementById("hp_confirm").focus();
};

function popup_blur23(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};

// 팝업 24 비밀번호가 서로 다릅니다
function popup_on24() {
    $(".popmenu24").show();
    popup_blur24(true);
};

function exit24() {
    $(".popmenu24").hide();
    popup_blur24(false);
    document.getElementById("input_confirm_pw").focus();
};

function popup_blur24(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};

// 팝업 25 휴대폰 번호 형식에 맞게 입력해주세요
function popup_on25() {
    $(".popmenu25").show();
    popup_blur25(true);
};

function exit25() {
    $(".popmenu25").hide();
    popup_blur25(false);
};

function popup_blur25(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};


// 팝업 26 인증하기 버튼을 눌러주세요
function popup_on26() {
    $(".popmenu26").show();
    popup_blur26(true);
};

function exit26() {
    $(".popmenu26").hide();
    popup_blur26(false);
};

function popup_blur26(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};