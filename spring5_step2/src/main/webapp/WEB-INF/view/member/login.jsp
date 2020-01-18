<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
			<main>
                <h2 class="main title">로그인</h2>
        
                <div class="breadcrumb">
                    <h3 class="hidden">breadlet</h3>
                    <ul>
                        <li>home</li>
                        <li>회원</li>
                        <li>로그인</li>
                    </ul>
                </div>
               	
               	<c:if test="${not empty param.error}">
        		<div style="color:red;font-weight: bold;">
        			아이디 또는 비밀번호가 올바르지 않습니다.
        		</div>
        		</c:if>
        		
                <div class="margin-top first">
                    <h3 class="hidden">로그인 정보 입력</h3>
                    <form class="login-form" action="login" method="post"> 
                        <fieldset>
                            <legend class="hidden">로그인 폼</legend>
                            <h4>
                                <img src="../images/member/txt-title.png" />
                            </h4>
                            <ul class="login-box">
                                <li>
                                    <label for="uid">아이디</label>
                                    <input type="text" name="username" placeholder="아이디" /></li>
                                <li>
                                    <label for="pwd">비밀번호</label>
                                    <input type="password" 	name="password" placeholder="비밀번호"/></li>
                            </ul>
                            
                            <div class="login-btn-box">
                                <%-- <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token}" /> --%>
                                <input type="submit" class="btn login-btn"/>
                            </div>
                            
                            <ul class="login-option">
                                <li>
                                    <span>아이디 또는 비밀번호를 분실하셨나요?</span>
                                    <a href="find-id">
                                        <img alt="ID/PWD 찾기" src="../images/member/btn-find.png" />
                                    </a>
                                </li>
                                <li class="margin-top">
                                    <span>아이디가 없으신 분은 회원가입을 해주세요.</span>
                                    <a href="agree">
                                        <img alt="회원가입" src="../images/member/btn-join.png" />
                                    </a>
                                </li>
                            </ul>
                        </fieldset>
                    </form>
                </div>
        
            </main>