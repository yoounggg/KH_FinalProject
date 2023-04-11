

          // YYYY 유효성 검사 (올해까지)
          function validateYYYY(input) {
            const regex = /^(19|20)\d{2}$/;
            const isValid = regex.test(input.value) && parseInt(input.value) <= new Date().getFullYear();
            input.setCustomValidity(isValid ? '' : 'YYYY 형식이 올바르지 않습니다.');
            if (!isValid) {
                document.querySelector('.popmenu3').style.display = 'block';
                $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
            }
        }

        // MM 유효성 검사
        function validateMM(input) {
            const regex = /^(0?[1-9]|1[0-2])$/;
            const isValid = regex.test(input.value);
            input.setCustomValidity(isValid ? '' : 'MM 형식이 올바르지 않습니다.');
            if (!isValid) {
                document.querySelector('.popmenu3').style.display = 'block';
                $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
            }
        }

        // DD 유효성 검사
        function validateDD(input) {
            const yyyy = document.querySelector('.YYYY').value;
            const mm = document.querySelector('.MM').value;
            const lastDay = new Date(yyyy, mm, 0).getDate();
            const regex = /^(0?[1-9]|[1-2][0-9]|3[0-1])$/;
            const isValid = regex.test(input.value) && parseInt(input.value) <= lastDay;
            input.setCustomValidity(isValid ? '' : 'DD 형식이 올바르지 않습니다.');
            if (!isValid) {
                document.querySelector('.popmenu3').style.display = 'block';
                $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
            }
        }


        // 팝업 3
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


        window.onload = function() {
            var addrElem = document.getElementById("addr");
            var addrBtnElem = document.getElementById("addr_btn");
            
            addrElem.addEventListener("click", openKakaoPostcode);
            addrBtnElem.addEventListener("click", openKakaoPostcode);
          };
          
          function openKakaoPostcode() {
            //카카오 지도 발생
            new daum.Postcode({
              oncomplete: function(data) { //선택시 입력값 세팅
                document.getElementById("addr_form").style.display = "none";
                document.getElementById("addr_form2").style.display = "block";
                document.getElementById("addr2").value = data.address; // 주소 넣기
                document.getElementById("detail_addr_form").style.display = "block";
                document.querySelector("#detail_addr").focus(); //상세입력 포커싱
                document.getElementById('postal_code').style.display= "block";
            	document.getElementById('post_num').value = data.zonecode;
              }
            }).open();
          }
          
          
    