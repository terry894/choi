<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<main class="main">
	<h2 class="main title">newlec 님 환영합니다.</h2>
	
	<div class="breadcrumb">
		<h3 class="hidden">경로</h3>
		<ul>
			<li>Home</li>
			<li>마이페이지</li>
			<li>수강생홈</li>
		</ul>
	</div>
	
	<div class="-list- vertical margin-top first padding-bottom">
	
		<h3 class="hidden">안녕하세요. 뉴렉처입니다.</h3>
		<div>
			<h4 class="-border- all green radius -text- center -padding- -text- bold">뉴렉처 이벤트 소식</h4>
			
			
			
			<div class="-padding- -border- bottom b-green -text- center underline">
				<a href="../customer/event/1" class="-symbol-">회원정보 수정 이벤트</a>
			</div>
			
			
		</div>
		
		<div class="align-center margin-top">
		
			<span style="display:inline-block; background:url('../images/profile.png') no-repeat center; background-size:cover ;vertical-align:middle; width: 120px;height: 120px;border: 1px solid #595959; border-radius:60px; box-shadow: 0 4px 15px 0 rgba(0, 0, 0, 0.15);"></span>
		
		
		</div>
		
		<div class="-margin- top">
			<table class="table -border- top orange">
				<tr>
					<th colspan="2" class="-text- bold orange">
						내 수강 요약정보
					</th>
				</tr>
				<tr>
					<td class="-border- right -reset- line-height -padding-">
						<div>현재 수강중인 강좌</div>
						<div><span class="-text- orange">1</span> 개</div>
						<div class="-margin- top"><a class="-text- underline" href="course/taking">내 강의장으로 가기</a></div>
					</td>
					<td class="-border- -reset- line-height -padding-">
						<div>최근 1주일 1:1 문의 결과</div>
						<div><span class="-text- orange">0</span> / 0</div>
						<div class="-margin- top"><a class="-text- underline" href="helpdesk/question">문의 게시판으로 가기</a></div>
					</td>
				</tr>
				<!-- <tr>
					<th colspan="3" class="-text- bold">
						총 누적자산 : 포인트 <span class="-text- orange">21,200</span> 원, 쿠폰 <span class="-text- orange">2</span>장
					</th>
				</tr> -->
			</table>
		</div>
		<!-- ----- 강의 플레이어 버튼 ----------------------------------------------------------------------- -->
		<div class="-margin- top -inline- center">
			<div class="player">
				<a target="_blank" href="course/player">강의 플레이어 실행</a>
			</div>
		</div>
		<!-- -------------------------------------------------------------------------------------------------------------------------- -->
		
		<div class="-margin- top t40">
			<table class="table">
				<tr>
					<th colspan="2" class="-text- bold">
						내 자산 요약정보
					</th>
				</tr>
				<tr>					
					<td class="-border- right -reset- line-height -padding-">
						<div class="margin-top">내 포인트</div>
						<div><span class="-text- orange">20,800</span> P</div>
						<div class="-margin- top"><a class="-text- underline" href="payment/point">포인트 기록 보기</a></div>
						<div class="-margin- top">(매일 로그인만 해도 포인트가 쑥쑥!)<br />&nbsp;</div>
					</td>
					
					<td class="-reset- line-height -padding-">
						<div>쿠폰</div>
						<div><span class="text-strong color-warning">0</span> / <span class="text-strong color-strong">5</span></div>
						<div class="-margin- top"><a class="-text- underline" href="payment/coupon">쿠폰함 보기</a></div>
						<div class="-margin- top">(뉴렉처 이벤트로 쌓이는 쿠폰)<br />&nbsp;</div>
					</td>
				</tr>
				<tr>
					<th colspan="2" class="-text- bold">
						현재 총 자산 : 포인트 <span class="-text- orange">20,800</span> P, 쿠폰 <span class="-text- orange">0</span> 장
					</th>
				</tr>
			</table>
		</div>
	</div>
	
</main>