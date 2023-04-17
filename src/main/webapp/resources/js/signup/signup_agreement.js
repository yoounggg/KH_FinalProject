

function checkSelectAll() {
    // 전체 체크박스
    const checkboxes
        = document.querySelectorAll('input[class="check"]');

    // 선택된 체크박스
    const checked
        = document.querySelectorAll('input[class="check"]:checked');

    // select all 체크박스
    const selectall
        = document.querySelector('input[name="selectall"]');

    if (checkboxes.length === checked.length) {
        selectall.checked = true;
    } else {
        selectall.checked = false;
    }

}



function selectAll(selectAll) {
    const checkboxes
        = document.querySelectorAll('input[type="checkbox"]');

    checkboxes.forEach((checkbox) => {
        checkbox.checked = selectAll.checked
    })
}



        // 팝업 1 약관에 동의해주세요
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



        function a() {
            const chk1 = document.querySelector("#체크1");
            const chk2 = document.querySelector("#체크2");
            if(!chk1.checked || !chk2.checked){
                popup_on1();
            } else {
                window.location.href = "info";
            }
        }