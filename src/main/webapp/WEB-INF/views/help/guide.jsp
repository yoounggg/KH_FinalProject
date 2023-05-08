<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MOYAMOGA</title>
    
	<!-- include favicon -->
    <%@include file="/WEB-INF/views/common/favicon.jsp" %>
    
    <link rel="stylesheet" href="/resources/css/help/guide.css">

    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,100,1,-25" />

    <script src="https://kit.fontawesome.com/1fe7ba446e.js" crossorigin="anonymous"></script>

    <!-- slick: cdn 방식으로 css, js 가져오기 -->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
	<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>			

</head>
<script type="text/javascript">
function showDelivery() {
	document.getElementById('delivery-info').style.display = 'block';
	document.getElementById('refund-info').style.display = 'none';

	document.getElementById('delivery-btn').style.backgroundColor = '#11BC0D';
	document.getElementById('delivery-btn').style.color = '#F9F9F4';
	document.getElementById('refund-btn').style.backgroundColor = '#F9F9F4';
	document.getElementById('refund-btn').style.color = 'black';
}

function showRefund() {
	document.getElementById('refund-info').style.display = 'block';
	document.getElementById('delivery-info').style.display = 'none';

	document.getElementById('refund-btn').style.backgroundColor = '#11BC0D';
	document.getElementById('refund-btn').style.color = '#F9F9F4';
	document.getElementById('delivery-btn').style.backgroundColor = '#F9F9F4';
	document.getElementById('delivery-btn').style.color = 'black';
}
</script>
<body onload="showDelivery()">
	<!-- header -->
	<%@include file= "/WEB-INF/views/common/header.jsp" %>
	
    <main>
        <div class="help_wrap">


            <!-- 상단 고객센터, 검색창 -->
            <div class="help_top">
                <div class="help_center">
                    <p class="help_center_text">고객센터</p>
                </div>
                <div class="help_search">
                    <ul class="help_search_ul">
                        <div class="help_search_input_a">

                        </div>
                    </ul>
                </div>
            </div>

            <!-- 중간 - notice 사이드 메뉴/ 게시물 -->
            <div class="help_content_wrap">

                <!-- 중간 - notice 사이드 메뉴 -->
                <div class="help_side_menu">
                    <ul class="sm_ul">
                        <li id="sm_li"><a href="/help/faq">FAQ</a></li>
                        <li id="sm_li"><a href="/help/notice">공지사항</a></li>
                        <li id="sm_li"><a href="/help/guide">이용안내</a></li>
                        <%-- 로그인이 되어 있는 경우 --%>
						<c:if test="${not empty sessionScope.member}">
							<li id="sm_li"><a href="/help/question">1:1문의</a></li>
						</c:if>

						<%-- 로그인이 되어 있지 않은 경우 --%>
						<c:if test="${empty sessionScope.member}">
							<li id="sm_li"><a href="/login/main">1:1문의</a></li>
						</c:if>
                    </ul>

                </div>
                
	            <div class="content_wrap">
	            <h2>이용 안내</h2>
		            <div class="content_list">
	
						<button id="delivery-btn" onclick="showDelivery()">배송 정보</button>
						<button id="refund-btn" onclick="showRefund()">환불 정보</button>
						<div id="delivery-info">
							<br>
							<br>

						
							<h3>모야모과 주간 배송</h3>
                            <p>주간배송은 물류편으로 매장에 보내, 매장에서 보내드리거나<br>
                            별도로 배송기사가 배송해 드리는 방법입니다.<br>
                            고객께서 대형마트등에서 사용하는 자율포장으로 배송 된다고 생각하시면 됩니다.</p>
                            <br>
                            <table>
                                <colgroup>
                                    <col style="width:20%">
                                    <col style="width:80%;">
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <th>
                                        배송시간표
                                        </th>
                                        <td>                                
                                          
                                          <colgrop>
                                                  </colgrop><table class="otable table_lineHB">
                                              <caption>*주문 마감시간은 결제 완료 시간 기준입니다.</caption>
                                              <colgroup><col>
                                                  <col>
                                                  <col>
                                                  <col>
                                              </colgroup>
                                              <thead>
                                                  <tr>
                                                      <th scope="col">차수</th><th scope="col">주문 마감 시간</th><th scope="col">배송 시간대</th>
                                                  </tr>
                                              </thead>
                                              <tbody>
                                                  <tr>
                                                    <th scope="row" style="background-color:white">1차</th><td>전일 12:00 ~ 08:00</td><td>당일 오후 2시까지 배송</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row" style="background-color:white">2차</th><td>08:00 ~ 12:00</td><td>당일 오후 6시까지 배송</td>
                                                </tr>
                                              </tbody>
                                          </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                        배송비
                                        </th>
                                        <td>
                                        배송비 3,000원 부과
                                    </td></tr>
                                </tbody>
                            </table>
                            <br>
                            <br>
                            <br>
                            <h3>모야모과 일반 배송</h3>
                            <p>별도 배송 요청이 있을 경우 별도 요청건을 우선 배송합니다.<br>
                                명절, 공휴일 등 택배사의 사정에 따라 다소 지연될 수 있습니다.</p>
                            <br>
                            <div class="info_boxs">
                                <table>
                                    <colgroup>
                                        <col style="width:20%">
                                        <col style="width:80%;">
                                    </colgroup>
                                    <tbody>
                                        <tr>
                                            <th>
                                            배송가능지역
                                            </th>
                                            <td>
                                            전지역 가능
                                            </td>
                                            </tr>
                                        <tr>
                                            <th>
                                            배송가능시간
                                            </th>
                                            <td>
                                            8시까지 주문한 상품은 당일 택배 발송
                                            </td>
                                        </tr>       
                                        <tr>
                                            <th>
                                            배송비
                                            </th>
                                            <td>
                                            배송비 3,000원 부과
                                        </td></tr>
                                    </tbody>
                                </table>
                            </div>
                            <br>
                            <br>
                            <br>
                            <h3>모야모과 산지직송 배송</h3>
                            <p>산지에서 직접배송하며, 생산지의 배송 정책에 따라 다르게 부과됩니다.</p>
                            <br>
                            <div class="info_boxs">
                                <table>
                                    <colgroup>
                                        <col style="width:20%">
                                        <col style="width:80%;">
                                    </colgroup>
                                    <tbody>
                                        <tr>
                                            <th>
                                            배송가능지역
                                            </th>
                                            <td>
                                            전지역 가능
                                            </td>
                                            </tr>
                                        <tr>
                                            <th>
                                            수령시간
                                            </th>
                                            <td>
                                            주문완료 후 1~2일 소요
                                            </td>
                                        </tr>       
                                        <tr>
                                            <th>
                                            배송비
                                            </th>
                                            <td>
                                            생산지의 배송 정책에 따라 다르게 부과됩니다.
                                        </td></tr>
                                    </tbody>
                                </table>
                            </div>
							

						</div>
						<div id="refund-info" style="display:none">
							<br>
							<br>

							<h3>교환, 반품, 환불 가능기간</h3>
                            <p>상품 수령일로부터 7일 이내에 이내에 가능합니다.<br>
                                - 채소/과일류, 유제품, 양곡 등은 상품 수령일로부터 다음날까지(영업일 기준) 접수해 주세요.<br>
                                - 건강기능식품은 상품 수령일로부터 7일 이내에 접수해 주세요.</p>
                            

                            <br>
                            <br>
                            <br>
                            <h3>교환, 반품, 환불 가능 안내</h3>
                            <p>주말에는 제품의 신선도를 위해 배송을 하지 않습니다.<br>
                               별도 배송 요청이 있을 경우 별도 요청건을 우선 배송합니다.<br>
                               명절, 공휴일 등 택배사의 사정에 따라 다소 지연될 수 있습니다.</p>

                            <br>
                            <div class="info_boxs">
                                <table>
                                    <colgroup>
                                        <col style="width:20%">
                                        <col style="width:80%;">
                                    </colgroup>
                                    <tbody>
                                        <tr>
                                            <th>
                                                불량 및 오배송
                                            </th>
                                            <td>
                                                1. 상품에 하자가 있거나 불량인 경우 변질, 불량, 파손, 표기오류, 이물혼입, 중량미달 등<br>
                                                2. 주문한 내역과 다른 상품이 배송 된 경우
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>
                                                단순변심
                                            </th>
                                            <td>
                                                1. 일반 가공식품 및 생활용품만 반품가능<br>
                                                2. 포장 및 구성품이 훼손되지 않은 경우 (단, 반송 시 발생하는 비용은 고객부담)<br>
                                                ※ 고객부담 배송비 : (반품) 택배-3,000원, (교환) 택배-6,000원
                                            </td>
                                        </tr>     
                                    </tbody>
                                </table>
                            </div>
                            <br>
                            <br>
                            <br>
                            <h3>교환, 반품, 환불 불가 안내</h3>
                            <p>1. 접수기간이 지난 경우<br>
                               2. 본인의 과실로 상품이 없어지거나 훼손 된 경우<br>
                               3. 개봉하여 이미 사용한 경우<br>
                               4. 단순 기호차이에 의한 경우 (맛, 향, 색등)<br>
                               5. 시간이 경과되어 상품의 가치가 현저히 떨어진 경우<br>
                               6. 예약상품: 취소 및 반품이 어렵습니다. 단, 예약마감 전 고객센터로 연락하시면 취소 가능<br> 
                            </p>

                        </div>
							
							
							
							
							
							
							
							
						</div>
	                </div>
                </div>

			</div>
</div>
</main>
    <!-- ==========================footer========================= -->
   <%@include file= "/WEB-INF/views/common/footer.jsp" %>
</body>
</html>