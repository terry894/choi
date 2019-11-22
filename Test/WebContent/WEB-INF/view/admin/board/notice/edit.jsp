<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
                <h2 class="main title">공지사항 수정</h2>

                <div class="breadcrumb">
                    <h3 class="hidden">breadlet</h3>
                    <ul>
                        <li>home</li>
                        <li>게시글 관리</li>
                        <li>공지사항</li>
                    </ul>
                </div>

                <form action="edit" method="post" >
                    <div class="margin-top first">
                        <h3 class="hidden">공지사항 입력</h3>
                        <table class="table">
                            <tbody>
                                <tr>
                                    <th>제목</th>
                                    <td class="text-align-left text-indent text-strong text-orange" colspan="3">
                                        <input type="text" name="title" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>첨부파일</th>
                                    <td colspan="3" class="text-align-left text-indent"><input type="file"
                                            name="file" /> </td>
                                </tr>
                                <tr class="content">
                                    <td colspan="4"><textarea class="content" name="content"></textarea></td>
                                </tr>
                                <tr>
                                    <td colspan="4" class="text-align-right"><input class="vertical-align" type="checkbox" id="open" name="open" value="true"><label for="open" class="margin-left">바로공개</label> </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="margin-top text-align-center">
                        <input class="btn-text btn-default" type="submit" value="등록" />
                        <a class="btn-text btn-cancel" href="list.html">취소</a>
                    </div>
                </form>

            </main>
        </div>
    </div>

    <!-- ------------------- <footer> --------------------------------------- -->



   <jsp:include page="../../../inc/footer.jsp"/>
</body>

</html>