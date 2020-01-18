<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="page" value="${(empty param.p)?1:param.p}" />	
<c:set var="startNum" value="${page - (page-1)%5}" />
<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(listCount/10), '.')}" />

			<main class="main">
				<h2 class="main title">공지사항</h2>

				<div class="breadcrumb">
					<h3 class="hidden">경로</h3>
					<ul>
						<li>home</li>
						<li>고객센터</li>
						<li>공지사항</li>
					</ul>
				</div>

				<div class="search-form margin-top first align-right">
					<h3 class="hidden">공지사항 검색폼</h3>
					<form action="list" method="get" class="table-form">
						<fieldset>
							<legend class="hidden">공지사항 검색 필드</legend>
							<label class="hidden">검색분류</label>
							<select name="f">
								<option value="title">제목</option>
								<option value="writerId">작성자</option>
							</select>
							<label class="hidden">검색어</label>
							<input type="text" name="q" value="${param.q}" >
							<input type="hidden" name="p" value="${param.p}">							
							<input class="btn btn-search" type="submit" value="검색" />
						</fieldset>
					</form>
				</div>
<!-- ==================================== -->
				<form action="list" method="post">
				<div class="notice margin-top">
					<h3 class="hidden">공지사항 목록</h3>
					<table class="table">
						<thead>
							<tr>
								<th class="w60">번호</th>
								<th class="expand">제목</th>
								<th class="w100">작성자</th>
								<th class="w100">작성일</th>
								<th class="w60">조회수</th>
								<th class="w40">공개</th>
								<th class="w40">삭제</th>
							</tr>
						</thead>
						<tbody>
											
						
						<c:forEach var="n" items="${list}">
							<tr>
								<td>${n.id}</td>
								<td class="title indent text-align-left"><a href="${n.id}">${n.title}</a></td>
								<td>${n.writerId}</td>
								<td>
									${n.regdate}
								</td>
								<td>${n.hit}</td>
								<td><input type="checkbox" name="open"></td>
								<td><input type="checkbox" name="del-id" value="${n.id}"></td>
							</tr>
						</c:forEach>
							

						</tbody>
					</table>
				</div>

				<div class="indexer margin-top align-right">
					<h3 class="hidden">현재 페이지</h3>
					<div><span class="text-orange text-strong">${page}</span> / ${lastNum } pages</div>
				</div>

				<div class="text-align-right margin-top">
					<input type="submit" class="btn-text btn-default" name="cmd" value="일괄공개">
					<input type="submit" class="btn-text btn-default" name="cmd" value="일괄삭제">
					<a class="btn-text btn-default" href="reg">글쓰기</a>				
				</div>
				</form>
<!-- ======================================= -->
				<div class="margin-top align-center pager">

					<div>

					<c:if test="${startNum <= 5 }">
						<span class="btn btn-prev" onclick="alert('이전 페이지가 없습니다.');">이전</span>
					</c:if>
					<c:if test="${startNum > 5 }">
						<a class="btn btn-prev" href="list?p=${startNum-1}&f=${param.f}&q=${param.q}">${startNum+i}>이전</a>
					</c:if>
					</div>
					<ul class="-list- center">
					
					<c:forEach var="i" begin="0" end="4">
					<c:if test="${startNum+i <= lastNum }">
						<li>
							<c:if test="${startNum+i == page}">
								<c:set var="currentStyle" value="orange bold" />
							</c:if>
							<c:if test="${startNum+i != page}">
								<c:set var="currentStyle" value="" />
							</c:if>
							<a class="-text- ${currentStyle}" href="list?p=${startNum+i}&f=${param.f}&q=${param.q}">${startNum+i}</a>
						</li>
					</c:if>
					</c:forEach>

					</ul>
					<div>
					<c:if test="${startNum+5 > lastNum }">
						<span class="btn btn-next" onclick="alert('다음 페이지가 없습니다.');">다음</span>
					</c:if>
					<c:if test="${startNum+5 <= lastNum }">
						<a class="btn btn-next" href="list?p=${startNum+5}&f=${param.f}&q=${param.q}">다음</a>
					</c:if>
					</div>

				</div>
			</main>