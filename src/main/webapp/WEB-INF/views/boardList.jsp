<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>게시판</title>
    <style>
        body{
            margin: 5px 25px;
            padding-top: 70px;
            padding-bottom: 30px;
        }
    </style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
</head>
<body>
<article>
    <div class="container">
        <div class="table-responsive">
            <h2>게시판</h2>
            <table class="table table-striped table-sm">
                <colgroup>
                    <col style="width:5%;"/>
                    <col style="width:auto;"/>
                    <col style="width:15%;"/>
                    <col style="width:10%;"/>
                    <col style="width:10%;"/>
                </colgroup>
                <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>등록일</th>
                    <th>파일</th>
                    <th>조회</th>
                </tr>
                </thead>
                </tbody>
                <tr>
                    <c:forEach items="${boardList}" var="list" varStatus="status">
                    <td><a href="/board/boardDetail?boardNo=${list.boardNo}">${fn:length(boardList)-status.index}</a></td>
                    <td><a href="/board/boardDetail?boardNo=${list.boardNo}">${list.boardTitle}</a></td>
                    <td>${list.boardWriter}</td>
                    <td>${list.boardRegdate}</td>
                    <td>
                        <c:if test="${list.fileNo ne null}"><img src="../image/free-icon-attachments-304690.png"></c:if>
                    <td>${list.boardViewcount}</td>
                </tr>
                </c:forEach>
                </tbody>
                <div>
                    <button type="button" class="btn btn-outline-info" onclick="location.href='boardWrite'">등록</button>
                </div>
            </table>
        </div>
    </div>
</article>
</body>
</html>