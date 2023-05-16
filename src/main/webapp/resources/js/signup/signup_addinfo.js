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

    $("#signup_btn").click(function () {

        const name = document.getElementById("input_name").value;         //이름
        const tel = document.getElementById("input_hp").value;            //휴대폰 번호
        const num = document.getElementById("input_num").value;           //인증번호
        const addr2 = document.getElementById("addr2").value;             //주소
        const addr3 = document.getElementById("detail_addr").value;       //상세주소
   
        const isOkName = /^[a-zA-Z가-힣]{2,20}$/;        //한글 영어 2~20자

      if (name.trim() === "") {                // 이름 체크
            popup_on15();

        } else if (!isOkName.test(name)) {              // 이름 유효성 체크
            popup_on1();

        } else if (tel.trim() === "") {                 // 휴대폰 번호 체크
            popup_on17();

        } else if (!tel_btn.disabled) {                 // 휴대폰 인증 체크
            popup_on23();

        } else if (num.trim() === "") {                 // 인증번호 체크
            popup_on18();

        } else if (addr2.trim() === "") {               // 주소 체크
            popup_on19();

        } else if (addr3.trim() === "") {               // 상세 주소 체크
            popup_on20();

        } else {
            $("#signup").attr("action", "/signup/addinfo");        //가입 성공
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

// 팝업 25 휴대폰 번호 형식에 맞게 입력해주세요
function popup_on25() {
    $(".popmenu25").show();
    popup_blur25(true);
};

function exit25() {
    $(".popmenu25").hide();
    popup_blur24(false);
};

function popup_blur25(chk) {
    if (chk === false)
        $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
    else
        $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
};