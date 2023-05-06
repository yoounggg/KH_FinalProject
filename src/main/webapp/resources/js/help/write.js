
// $(document).ready(function () {

//   $("#submit").click(function () {
//     const titleInput = document.getElementById('ttle');
//     const titleLength = titleInput.value.trim().length;
   
    
//       const con = document.getElementById("con")            //내용
//       const conLength = con.value.trim().length;


//       if (titleLength > 100) {                         // 제목
//           popup_on1();

//       } else if (conLength > 2000) {                  // 내용 
//           popup_on2();

//       } else {
//           $("#submit").attr("action", "/help/question");        
//           $("#submit").submit();
//       }
//   });
// });

// // 팝업 1 제목 길이 확인
// function popup_on1() {
//   $(".popmenu1").show();
//   popup_blur1(true);
// };

// function exit1() {
//   $(".popmenu1").hide();
//   popup_blur1(false);
// };

// function popup_blur1(chk) {
//   if (chk === false)
//       $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
//   else
//       $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
// };



// // 팝업 2 내용 확인
// function popup_on2() {
//   $(".popmenu2").show();
//   popup_blur2(true);
// };

// function exit2() {
//   $(".popmenu2").hide();
//   popup_blur2(false);
// };

// function popup_blur2(chk) {
//   if (chk === false)
//       $('#entire').css({ "opacity": "1", "pointer-events": "auto" });
//   else
//       $('#entire').css({ "opacity": "0.3", "pointer-events": "none" });
// };