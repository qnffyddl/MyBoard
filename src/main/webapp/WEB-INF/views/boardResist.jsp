<%--
  Created by IntelliJ IDEA.
  User: Blucean
  Date: 2022-09-19
  Time: 오후 2:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        .center{
            margin: 5px 25px;
            padding-top: 70px;
            padding-bottom: 30px;
        }
    </style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <meta charset="UTF-8">
    <title>게시글 등록</title>
</head>
<body>
<form class="center" method="post">
    <h2>게시글 등록</h2>
    <div class="form-group">
        <label>제목</label>
        <input type="text" class="form-control" name="title" placeholder="게시글 제목 입력">
    </div>
    <div class="form-group">
        <label>내용</label>
        <input type="text" class="form-control" name="content" placeholder="게시글 내용 입력">
    </div>
    <div class="form-group">
        <label>비밀번호</label>
        <input class="form-control" name="password" placeholder="비밀번호 입력">
    </div>
    <div class="form-group">
        <label>작성자</label>
        <input class="form-control" name="writer" placeholder="작성자 입력">
    </div>

    <button type="submit" class="btn btn-outline-info">등록</button>
    <button type="button" class="btn btn-outline-info"><a href="/board/boardList">돌아가기</a></button>
</form>
</body>
</html>