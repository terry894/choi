<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<html>

<head>
    <title>코딩 전문가를 만들기 위한 온라인 강의 시스템</title>
    <meta charset="UTF-8">
    <title>공지사항목록</title>

    <link href="/css/customer/layout.css" type="text/css" rel="stylesheet" />
    <style>
        #visual .content-container {
            height: inherit;
            display: flex;
            align-items: center;

            background: url("/images/mypage/visual.png") no-repeat center;
        }
    </style>
</head>

<body>
    <!-- header 부분 -->

    <jsp:include page="../../../inc/header.jsp"/>
    
    <!-- --------------------------- <visual> --------------------------------------- -->
    <!-- visual 부분 -->

    <div id="visual">
        <div class="content-container"></div>
    </div>
    <!-- --------------------------- <body> --------------------------------------- -->
    <div id="body">
        <div class="content-container clearfix">

            <!-- --------------------------- aside --------------------------------------- -->
            <!-- aside 부분 -->


                     <jsp:include page="../../inc/aside.jsp"/>
            <!-- --------------------------- main --------------------------------------- -->




            <main>
                <h2 class="main title">공지사항</h2>

                <div class="breadcrumb">
                    <h3 class="hidden">breadlet</h3>
                    <ul>
                        <li>home</li>
                        <li>고객센터</li>
                        <li>공지사항</li>
                    </ul>
                </div>

                <div class="margin-top first">
                    <h3 class="hidden">공지사항 내용</h3>
                    <table class="table">
                        <tbody>
                            <tr>
                                <th>제목</th>
                                <td class="text-align-left text-indent text-strong text-orange" colspan="3">${n.title}</td>
                            </tr>
                            <tr>
                                <th>작성일</th>
                                <td class="text-align-left text-indent" colspan="3">${n.regdate}</td>
                            </tr>
                            <tr>
                                <th>작성자</th>
                                <td>newlec</td>
                                <th>조회수</th>
                                <td>148</td>
                            </tr>
                            <tr>
                                <th>첨부파일</th>
                                <td class="text-align-left text-indent" colspan="3">
                                <c:forTokens var="fileName" items="${n.files}" delims=",">
                                <a href="/upload/${fileName}" download >${fileName}</a>
                                </c:forTokens>
                               <!-- 쉼표로 구분되어 잇는 항목 만큼 반복  -->
                                </td>
                            </tr>
                            <tr class="content">
                                <td colspan="4">${n.content}<div><br></div>
                                    <div><br></div>
                                    <div><a href="http://www.newlecture.com/resource/spring2.zip"><b><u>
                                                    <font size="5" color="#dd8a00">예제 다운로드하기</font>
                                                </u></b></a></div>
                                    <div><br></div>
                                    <div><br></div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div class="margin-top text-align-center">
                    <a class="btn-text btn-cancel" href="list">목록</a>
                    <a class="btn-text btn-default" href="edit.html">수정</a>
                    <a class="btn-text btn-default" href="del.html">삭제</a>
                </div>

                <div class="margin-top">
                    <table class="table border-top-default">
                        <tbody>
                            <tr>
                                <th>다음글</th>
                                <td colspan="3" class="text-align-left text-indent">다음글이 없습니다.</td>
                            </tr>
                            <tr>
                                <th>이전글</th>
                                <td colspan="3" class="text-align-left text-indent"><a class="text-blue text-strong"
                                        href="">스프링 DI 예제 코드</a></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </main>

        </div>
    </div>

    <!-- ------------------- <footer> --------------------------------------- -->
   <jsp:include page="../../../inc/footer.jsp"/>
</body>

</html>