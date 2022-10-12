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
    <script src='//cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
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
<form id="form" name="form" class="center" method="post" enctype="multipart/form-data">
    <h2>게시글 등록</h2>
    <div class="form-group">
        <label>제목</label>
        <input id ="boardTitle" type="text" class="form-control" name="boardTitle" placeholder="게시글 제목 입력">
    </div>
    <div class="form-group">
        <label>내용</label>
        <input id ="boardContent" type="text" class="form-control" name="boardContent" placeholder="게시글 내용 입력">
    </div>
    <div class="form-group">
        <label>비밀번호</label>
        <input id ="boardPw" type="text" class="form-control" name="boardPw" placeholder="비밀번호 입력">
    </div>
    <div class="form-group">
        <label>작성자</label>
        <input id ="boardWriter" type="text" class="form-control" name="boardWriter" placeholder="작성자 입력">
    </div>
    <div class="form-group">
        <label>파일</label>
        <div class=class="form-control">
            <input id="boardFile" type="file" class="form-control"  name="boardFile" multiple="multiple"/>
        </div>
    </div>

    <button id="btn_register" type="button" class="btn btn-outline-info">등록</button>
    <button type="button" class="btn btn-outline-info"><a href="/board/boardList">돌아가기</a></button>
</form>
</body>

<script type="text/javascript">


    //button 등록
    $(document).on('click', '#btn_register', function(e) {
        //validation
        let titleVal = document.getElementById("boardTitle");
        if (titleVal.value == "") {
            alert("제목을 입력하세요.");
            titleVal.focus();
            return false;
        }

        let contentVal = document.getElementById("boardContent");
        if (contentVal.value == "") {
            alert("내용을 입력하세요.");
            contentVal.focus();
            return false;
        }

        let pwVal = document.getElementById("boardPw");
        if (pwVal.value == "") {
            alert("비밀번호를 입력하세요.");
            pwVal.focus();
            return false;
        }

        let writerVal = document.getElementById("boardWriter");
        if (writerVal.value == "") {
            alert("작성자를 입력하세요.");
            writerVal.focus();
            return false;
        }
        //데이터를 담아내는 부분 상수 const로
        //jquery val() : Form Element 의 값을 받아오는데 쓰인다. (주로 input 이나 textarea 정도?)- 주의해야할 점은 Form Element 이외의 값은 받아오질 못한다는 점.
        //문자열 좌우에서 공백을 제거하는 함수가 trim() 함수 입니다.

        const boardFile = $("#form")[0];
        console.log( $("#form")[0].elements[4].value);

        //ajax 통신을 사용해 서버에 데이터를 전송하기 위해
        //폼데이터 객체를 생성함
        //jquery의 append를 통해서 프로퍼티에 바인딩이 가능하도록 세팅한다..append()선택된 요소의 마지막에 새로운 요소나 콘텐츠를 추가한다.
        var formData = new FormData(boardFile);

        //ajax로 파일전송 폼데이터를 보내기위해
        //enctype, processData, contentType 이 세가지를 반드시 세팅해야한다.
        $.ajax({
            enctype : 'multipart/form-data',
            processData : false,
            contentType : false,
            cache : false,
            url : "./boardWriteAjax",
            data : formData,
            type : "POST",
            success : function(res) {
                alert('게시글 등록 완료');
                //location.href = "./boardList";
            },
            error :  function(res) {
                alert('게시글 등록 실패');
            }
        });
    });
</script>
</html>

