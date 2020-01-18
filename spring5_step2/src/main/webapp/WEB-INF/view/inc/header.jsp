<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<%-- <c:set var="root" value="${pageContext.request.contextPath}" /> --%>
<c:url var="root" value="/" />

	<header id="header">

		<div class="content-container">
			<!-- ---------------------------<header>--------------------------------------- -->

			<h1 id="logo">
				<a href="/index.html">
					<img src="${root}resource/images/logo.png" alt="뉴렉처 온라인" />

				</a>
			</h1>

			<section>
				<h1 class="hidden">헤더</h1>

				<nav id="main-menu">
					<h1>메인메뉴</h1>
					<ul>
						<li><a href="/guide">학습가이드</a></li>

						<li><a href="/course">강좌선택</a></li>
						<li><a href="/answeris/index">AnswerIs</a></li>
					</ul>
				</nav>

				<div class="sub-menu">

					<section id="search-form">
						<h1>강좌검색 폼</h1>
						<form action="/course">
							<fieldset>
								<legend>과정검색필드</legend>
								<label>과정검색</label>
								<input type="text" name="q" value="" />
								<input type="submit" value="검색" />
							</fieldset>
						</form>
					</section>

					<nav id="acount-menu">
						<h1 class="hidden">회원메뉴</h1>
						<ul>
							<li><a href="/index">HOME</a></li>
							<%-- <c:if test="${empty sessionScope.userName}"> --%>
							<s:authorize access="isAnonymous()">
                            <li><a href="/member/login">로그인</a></li>
                            </s:authorize>
                            <%-- </c:if>
                            <c:if test="${not empty sessionScope.userName}"> --%>
                            <s:authorize access="isAuthenticated()">
                            <li><a href="/member/logout">로그아웃</a></li>
                            </s:authorize>
                            <%-- </c:if> --%>
							<li><a href="/member/agree">회원가입</a></li>
						</ul>
					</nav>

					<nav id="member-menu" class="linear-layout">
						<h1 class="hidden">고객메뉴</h1>
						<ul class="linear-layout">
							<li><a href="/member/home"><img src="/resource/images/txt-mypage.png" alt="마이페이지" /></a></li>
							<li><a href="/notice/list"><img src="/resource/images/txt-customer.png" alt="고객센터" /></a></li>
						</ul>
					</nav>

				</div>
			</section>

		</div>

	</header>