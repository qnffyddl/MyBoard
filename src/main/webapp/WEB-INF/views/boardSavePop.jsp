<%--
  Created by IntelliJ IDEA.
  User: Blucean
  Date: 2022-09-21
  Time: 오후 3:05
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src='//cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
    <title>비밀번호 확인</title>
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
<form name=form class="center" method="post">
    <h2>비밀번호를 입력해주세요!</h2>
    <div class="form-group">
        <input id="password" type="text" class="form-control" name="password" placeholder="비밀번호 입력" style="width:150px;"/>
    </div>

    <button id="btn_enter" type="button" class="btn btn-outline-info">확인</button>
    <button type="button" class="btn btn-outline-info"><a href="/board/boardUpdate?boardNo=<%=request.getParameter("boardNo")%>">취소</a></button>
</form>
</body>
<script type="text/javascript">

    //button 등록
    $(document).on('click', '#btn_enter', function(e) {

        //validation
        let pwVal = document.getElementById("password").value;
        let boardNo = <%=request.getParameter("boardNo")%>;

        if (pwVal.value == "") {
            alert("비밀번호를 입력하세요.");
            pwVal.focus();
            return false;
        }

        const password = $("#password").val().trim();

        //ajax 통신을 사용해 서버에 데이터를 전송하기 위해
        //폼데이터 객체를 생성함
        //jquery의 append를 통해서 프로퍼티에 바인딩이 가능하도록 세팅한다..append()선택된 요소의 마지막에 새로운 요소나 콘텐츠를 추가한다.
        let formData = new FormData();
        formData.append("boardNo", boardNo);

        let UpdateData = new FormData();
        UpdateData.append("boardNo", boardNo);
        UpdateData.append("boardTitle", "${boardTitle}");
        UpdateData.append("boardContent", "${boardContent}");
        UpdateData.append("boardPw", "${boardPw}");
        UpdateData.append("boardWriter", "${boardWriter}");
        //ajax로 파일전송 폼데이터를 보내기위해
        //enctype, processData, contentType 이 세가지를 반드시 세팅해야한다.

        $.ajax({
            type: 'POST',
            enctype: 'multipart/form-data',
            url : "./boardPwAjax",
            data : formData,
            processData : false,
            contentType : false,
            cache : false,
            success : function(res) {
                 if(res.data.boardPw == pwVal){
                    $.ajax({
                        type: 'POST',
                        enctype: 'multipart/form-data',
                        url : "./boardUpdateAjax",
                        data : UpdateData,
                        processData : false,
                        contentType : false,
                        cache : false,
                        success : function(res) {
                            alert("저장되었습니다!");
                            window.close();
                        },
                        error :  function(res) {
                            alert('업데이트 실패');
                        }
                    });
                }else{
                    alert("비밀번호 확인 실패");
                }

            },
            error :  function(res) {
                alert('통신 실패');
            }
        });
    });
</script>
</html>
