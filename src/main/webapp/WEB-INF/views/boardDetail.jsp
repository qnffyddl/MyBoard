<%--
  Created by IntelliJ IDEA.
  User: Blucean
  Date: 2022-09-19
  Time: 오후 3:19
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
    <title>게시글 상세화면</title>
</head>
<body>
<form name="boardForm" class="center" method="post" enctype="multipart/form-data">
    <h2>게시글 상세화면</h2>
    <input id ="boardNo" type="hidden" class="form-control" name="boardNo" value="${boardDetail.boardNo}"/>
    <div class="form-group">
        <label>제목</label>
        <input id ="boardTitle" type="text" class="form-control" name="boardTitle" value="${boardDetail.boardTitle}"/>
    </div>
    <div class="form-group">
        <label>내용</label>
        <input id ="boardContent" type="text" class="form-control" name="boardContent" value="${boardDetail.boardContent}"/>
    </div>
    <div class="form-group">
        <label>작성자</label>
        <input id ="boardWriter" type="text" class="form-control" name="boardWriter" value="${boardDetail.boardWriter}" readonly="true" />
    </div>
    <div class="form-group">
        <label>등록일자</label>
        <input id ="boardRegdate" type="text" class="form-control" name="boardRegdate" value="${boardDetail.boardRegdate}" disabled="true"/>
    </div>
    <div class="form-group">
        <c:forEach items="${boardDetail.fileList}" var="boardDto" varStatus="status">
            <c:if test="${status.index == 0}"> <%--쉼표 안붙이고--%>
                <c:set var="fileNameTextBox" value="${fileNameTextBox} ${boardDto.fileName}"/>
            </c:if>
            <c:if test="${status.index != 0}"> <%--쉼표 붙이고--%>
                <c:set var="fileNameTextBox" value="${fileNameTextBox}, ${boardDto.fileName}"/>
            </c:if>
        </c:forEach>
        <label>파일</label>
        <div class=class="form-control">
            <input class="form-control" id="boardfile" name="boardfile" type="file"  multiple="multiple"/>
        </div><br>
        <label>업로드된 파일</label>
        <div>
            ${fileNameTextBox}
        </div>
    </div>
    <div class="form-group">
        <input id ="boardPw" type="hidden" class="form-control" name="boardPw" value="${boardDetail.boardPw}"/>
    </div>

    <input id ="PageGubun" type="hidden" class="form-control" name="PageGubun"/>


    <button id="btn_save" type="button" class="btn btn-outline-info" onclick="boardPwPop('U')">저장</button>
    <button id="btn_delete" type="button" class="btn btn-outline-info" onclick="boardPwPop('D')">삭제</button>
    <button type="button" class="btn btn-outline-info"><a href="/board/boardList">돌아가기</a></button>
</form>
<script>
    //리스트로 이동
    function goToList() {
        location.href = "./boardList";
    }

    //저장 button
    function boardPwPop(Gubun){
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

        let targetTitle = "new_win";
        let popupWidth = 500;
        let popupHeight = 300;

        // 듀얼 모니터 기준
        let left = (screen.availWidth - popupWidth) / 2;
        if( window.screenLeft < 0){
            left += window.screen.width*-1;
        }
        else if ( window.screenLeft > window.screen.width ){
            left += window.screen.width;
        }

        let top = (screen.availHeight - popupHeight) / 2 - 10;

        let url = "/board/boardPwPop";
        let options = 'resizable=no,left=' + left + ',top=' + top +', width=' + popupWidth+ ',height=' + popupHeight +',menubar=no, status=no, toolbar=no, location=no, scrollbars=yes';

        $("#PageGubun").val("");//초기화

        if(Gubun == "U") {
            $("#PageGubun").val("U");
        }
        else if(Gubun == "D"){
            $("#PageGubun").val("D");
        }
        boardPwPopUp = window.open(url,targetTitle,options);
        boardForm.action = "/board/boardPwPop";
        boardForm.target = "new_win";
        boardForm.submit();
    }
</script>
</body>
</html>
