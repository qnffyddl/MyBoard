<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <script src='//cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
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
                <tbody id="boardList">
                <c:forEach items="${boardList}" var="list" varStatus="status">
                <tr>
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
                    <c:set var="nowPageRange" value="${Math.ceil(1 / 10) * 10}"/><%--현재페이지NUM--%>
                    <c:set var="startPageNum" value="${nowPageRange - 9}"/> <%--시작페이지NUM--%>
                    <c:set var="totalPageNum" value="${Math.ceil(boardList[0].totalCount / 10)}"/><%--총페이지NUM--%>
                    <c:set var="endPageNum" value="${nowPageRange > totalPageNum ? totalPageNum : nowPageRange}"/> <%--끝페이지NUM--%>

                    <span id ="paging">
                    <c:forEach begin="${startPageNum}" end="${endPageNum}" var="num">
                                <a onclick="pageListNum(${num})">${num}</a>
                    </c:forEach>
                    </span>
                </div>
                <div>
                    <button type="button" class="btn btn-outline-info" onclick="location.href='boardWrite'">등록</button>
                </div>
            </table>
        </div>
    </div>
</article>
</body>

<script type="text/javascript">
    function pageListNum(num) { // num 총 페이지 수

        var formData = new FormData();
        formData.append("page", num);
        console.log(num);

        $.ajax({
            enctype : 'multipart/form-data',
            processData : false,
            contentType : false,
            cache : false,
            url : "./boardPageAjaxList",
            data : formData,
            type : "POST",
            success : function(res) {
                let list = res.data|| [];
                let pageSize = 10;
                console.log(list);
                console.log(res.data);
                $("#boardList").empty();//비워줌

                let tbody = fn_getContentsHtml(list,num);
                $("#boardList").append(tbody);
                //location.href = "./boardList";

                let pervPageNum = res.data[0].page-1;
                let pageTotal = Math.ceil(res.data[0].totalCount / pageSize);
                let nowPageRange = Math.ceil(res.data[0].page / 10) * 10
                let startPageNum =  nowPageRange - 9;
                let endPageNum;

                if (nowPageRange > pageTotal) {
                    endPageNum = pageTotal;
                } else {
                    endPageNum = nowPageRange;
                }

                $("#paging").empty();//비워줌

                for(let i = startPageNum; i < endPageNum+ 1 ; i++){
                    console.log("반복"+i);
                    $("#paging").append('<a onclick="pageListNum('+i+')"> '+i+'</a>'); //페이지 버튼
                }

                if(res.data[0].page >1){
                    $("#paging").prepend('<a onclick="pageListNum('+pervPageNum+')">◀</a>'); //이전버튼
                }
                // if(res.data[0].page != res.data[0].totalCount /10 ){}

                console.log("토탈"+pageTotal);
                console.log("시작페이지"+startPageNum);
                console.log("끝페이지"+endPageNum);
            },
            error :  function(res) {
                alert('게시글 등록 실패');
            }
        });
    }

    function fn_getContentsHtml(list,num) {
        let contents = "";
        let imgYn = "";
        list.forEach((item,index) => {
           let rowNum= (num) * 10 - (index);
           if(item.fileNo != null){
               imgYn = '<img src="../image/free-icon-attachments-304690.png">';
           }else{
               imgYn = '';
            }

           contents +=
                `<tr>
                <td class="num"><a href="/board/boardDetail?boardNo=\${item.boardNo}">\${rowNum}</td>
                <td class="center"><a href="/board/boardDetail?boardNo=\${item.boardNo}">\${item.boardTitle}</a></td>
                <td class="center">\${item.boardWriter}</td>
                <td class="center">\${item.boardRegdate}</td>
                <td class="center">\${imgYn}</td>
                <td class="center">\${(item.boardViewcount)}</td>
            </tr>`;
        });

        console.log(contents);
        return contents;
    }
</script>
</html>