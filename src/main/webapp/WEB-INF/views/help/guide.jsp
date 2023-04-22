<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <main>
        <div class="helf_wrap">


            <!-- 상단 고객센터, 검색창 -->
            <div class="helf_top">
                <div class="helf_center">
                    <p class="helf_center_text">고객센터</p>
                </div>
                <div class="helf_search">
                    <ul class="helf_search_ul">
                        <p>공지사항 검색</p>
                        <div class="helf_search_input_a">
                            <input type="text" id="noticeKeyword" value="" placeholder="자주 찾는 질문을 검색해 보세요! ">
                            <a href="#" onclick="noticeSearch(); return false;" style="color: #fff;">검색</a>
                        </div>
                    </ul>
                </div>
            </div>

            <!-- 중간 - notice 사이드 메뉴/ 게시물 -->
            <div class="helf_content_wrap">

                <!-- 중간 - notice 사이드 메뉴 -->
                <div class="helf_side_menu">
                    <ul class="sm_ul">
                        <li id="sm_li"><a href="/help/faq">FAQ</a></li>
                        <li id="sm_li"><a href="/help/notice">공지사항</a></li>
                        <li id="sm_li"><a href="/help/guide">이용안내</a></li>
                    </ul>

                </div>
                

                <!-- 중간 - 게시물 -->
                <div class="content_wrap">
                        <h2>BEST10 <span class="test">가장 자주하시는 질문과 답변을 확인하세요</span></h2>
                        <form name="flist" method="post">
                        <!-- <a href="javascript:all_del()">전체선택 / 전체해제</a> -->
                        



                        <div class="content_list">
                            <c:forEach items="${list}" var="FaqVO">
                                <input type="radio" name="accodion" id="answer01">
                                <label for="answer01">컨텐츠 제목1<em></em></label>
                                <div>
                                    <p>
                                        자주묻는질문 답변1<br>
                                        자주묻는질문 답변1<br>
                                        자주묻는질문 답변1<br>
                                        자주묻는질문 답변1<br>
                                        자주묻는질문 답변1<br>
                                        자주묻는질문 답변1<br>
                                        자주묻는질문 답변1<br>
                                        자주묻는질문 답변10<br>
                                        자주묻는질문 답변1<br>
                                        자주묻는질문 답변1<br>
                                        자주묻는질문 답변1<br>
                                        자주묻는질문 답변1<br>
                                        자주묻는질문 답변21<br>
                                       
                                    </p>
    
                                </div> 
                            </c:forEach>

                            <input type="radio" name="accodion" id="answer01">
                            <label for="answer01">컨텐츠 제목2<em></em></label>
                            <div>
                                <p>
                                    자주묻는질문 답변1<br>
                                    자주묻는질문 답변1<br>
                                    자주묻는질문 답변1<br>
                                    자주묻는질문 답변1<br>
                                    자주묻는질문 답변1<br>
                                    자주묻는질문 답변1<br>
                                    자주묻는질문 답변1<br>
                                    자주묻는질문 답변10<br>
                                    자주묻는질문 답변1<br>
                                    자주묻는질문 답변1<br>
                                    자주묻는질문 답변1<br>
                                    자주묻는질문 답변1<br>
                                    자주묻는질문 답변1<br>
                                    자주묻는질문 답변1<br>
                                    자주묻는질문 답변1<br>
                                    자주묻는질문 답변1<br>
                                    자주묻는질문 답변1<br>
                                    자주묻는질문 답변1<br>
                                    자주묻는질문 답변1<br>
                                    자주묻는질문 답변1<br>
                                    자주묻는질문 답변1<br>
                                    자주묻는질문 답변1<br>
                                    자주묻는질문 답변1<br>
                                    자주묻는질문 답변21<br>
    
                                    
                                </p>
    
    
                            </div>
    
                            <input type="radio" name="accodion" id="answer02">
                            <label for="answer02">컨텐츠 제목3<em></em></label>
                            <div>
                                <p>자주묻는질문 답변2</p>
                            </div>
    
                            <input type="radio" name="accodion" id="answer03">
                            <label for="answer03">컨텐츠 제목3<em></em></label>
                            <div>
                                <p>자주묻는질문 답변3</p>
                            </div>
    
                            <input type="radio" name="accodion" id="answer04">
                            <label for="answer04">컨텐츠 제목4<em></em></label>
                            <div>
                                <p>자주묻는질문 답변4</p>
                            </div>
    
                            <input type="radio" name="accodion" id="answer05">
                            <label for="answer05">컨텐츠 제목5<em></em></label>
                            <div>
                                <p>자주묻는질문 답변5</p>
                            </div>
    
                            <input type="radio" name="accodion" id="answer06">
                            <label for="answer06">컨텐츠 제목6<em></em></label>
                            <div>
                                <p>자주묻는질문 답변6</p>
                            </div>
    
                            <input type="radio" name="accodion" id="answer07">
                            <label for="answer07">컨텐츠 제목7<em></em></label>
                            <div>
                                <p>자주묻는질문 답변7</p>
                            </div>
    
                            <input type="radio" name="accodion" id="answer08">
                            <label for="answer08">컨텐츠 제목8<em></em></label>
                            <div>
                                <p>자주묻는질문 답변8</p>
                            </div>
    
    
                        </div>
    
    
                    </div>
        
            </div>
        
            </div>
      
            
        </div>
    </main>
</body>
</html>