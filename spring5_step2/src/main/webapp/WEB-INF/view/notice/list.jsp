<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" >
<title>Insert title here</title>
</head>
<body>
<header>	
	<h1>뉴렉처 온라인</h1>
	
	<section>
		<h1>헤더</h1>
		
		<nav>
			<h1>메인메뉴</h1>
			<ul>
				<li><a href="">학습가이드</a></li>
				<li><a href="">강좌선택</a></li>
				<li><a href="">AnswerIs</a></li>
			</ul>
		</nav>
		
		<section>
			<h1>과정검색 폼</h1>
			<form>
			과정검색
			</form>
		</section>
		
		<nav>
			<h1>회원메뉴</h1>
			<ul>
				<li>HOME</li>
				<li>로그인</li>
				<li>회원가입</li>
			</ul>
		</nav>
		
		<nav>
			<h1>자주사용하는메뉴</h1>
			<ul>
				<li>마이페이지</li>
				<li><a href="/notice/list">고객센터</a></li>
			</ul>
		</nav>
	</section>
	
</header>
<!-- ============================================== -->
<aside>
	<h1>고객센터</h1>	
	<h2>고객을 위한 서비스</h2>
	
	<section>
		<h1>고객센터메뉴</h1>
		<ul>
			<li>공지사항</li>
			<li>자주하는 질문</li>
			<li>수강문의</li>
			<li>이벤트</li>
		</ul>
	</section>
	
	<section>
		<h1>협력업체</h1>
		<ul>
			<li>노트펍스</li>
			<li>나무랩연구소</li>
			<li>한빛미디어</li>
		</ul>
	</section>
</aside>
<!-- ========main=================================== -->
<main>
	<section>
		<h1>공지사항</h1>
		
		<section>
			<h1>경로</h1>
			<ol>
				<li>home</li>
				<li>고객센터</li>
				<li>공지사항</li>
			</ol>
		</section>
	
		<section>
			<h1>공지사항목록</h1>		
			<table border="1">
				<thead>
					<tr>
						<th>순번</th>
						<th>번호</th>
						<td>제목</td>
						<td>작성자</td>
						<td>작성일</td>
						<td>조회수</td>
					</tr>
				</thead>
				<tbody>
				
				<%-- <%
					List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
					for(Map<String, Object> n : list){ 
					pageContext.setAttribute("n", n);
				%> --%>
				<c:forEach var="n" items="${list}" begin="0" end="4" step="2" varStatus="status">
					<tr>
						<th>${status.index}</th>
						<th>${n.id}</th>
						<td><a href="detail?id=${n.id}">${n.title}</a></td>
						<td>${n.writerId}</td>
						<td><fmt:formatDate value="${n.regdate}" pattern="yyyy년 MM월 dd일 HH시 mm분 ss초"/> </td>
						<td>
							<c:set var="price" value="12345678901.23" />
							${price} / <fmt:formatNumber value="${price }" type="number" />원							
						
						</td>
					</tr>
				</c:forEach>
				<%-- <%} %> --%>
				
				</tbody>
			</table>
		</section>
		
		
		<div>
			1 / 1 pages
		</div>
		
		<c:set var="page" value="${(empty param.p)?0:param.p}" />		
		<c:set var="startNum" value="${page - (page-1)%5}" />
		<div>startNum: ${startNum }</div>
		
		<c:choose>
			<c:when test="${startNum == 1}">
			<a href="javascript:alert('못가');">이전</a>
			</c:when>
			<c:otherwise>
			<a href="list?p=${startNum-1}">이전</a>
			</c:otherwise>
		</c:choose>
		
		<!-- --------------------------------- -->
		<div>
		<c:if test="${startNum == 1}">
		<a href="javascript:alert('못가');">이전</a>
		</c:if>
		<c:if test="${startNum > 5 }">
		<a href="list?p=${startNum-1}">이전</a>
		</c:if>
		</div>
		
		<ul>
			<c:forEach var="idx" begin="0" end="4" varStatus="s">
			<li><a href="list?p=${startNum+idx}">${startNum+idx}</a></li>
			</c:forEach>
		</ul>
		<div>	
		<a href="list?p=${startNum+5}">다음</a>
		</div>
	</section>
</main>
<!-- ========footer=================================== -->
<footer>
	<section>
		<h1>소유자 정보</h1>
	
		<section>
			<h1>회사정보</h1>
			<dl>
				<dt>주소:</dt>
				<dd>서울특별시 마포구 토정로35길 11, 인우빌딩 5층 266호</dd>
				<dt>관리자메일:</dt>
				<dd>admin@newlecture.com</dd>
				<dt>사업자 등록번호:</dt>
				<dd>132-18-46763</dd>
				<dt>통신 판매업:</dt>
				<dd>신고제 2013-서울강동-0969 호</dd>
				<dt>상호:</dt>
				<dd>뉴렉처</dd>
				<dt>대표:</dt>
				<dd>박용우</dd>
				<dt>전화번호:</dt>
				<dd>070-4206-4084</dd>
			</dl>
		</section>
		
		<h3>저작권 정보</h3>
		<div>
		Copyright ⓒ newlecture.com 2012-2014 All Right Reserved. Contact admin@newlecture.com for more information
		</div>
	</section>
</footer>
</body>
</html> 





