<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
            <h2 style="text-align: center";>게시판</h2>
            <div class="container">
                <div class="row" style="float: left;">
                    <form method="post" name="search" action="">
                        <table class="pull-right">
                            <tr>
                                <td><select class="form-control" name="searchField">
                                    <option value="">선택</option>
                                    <option value="title">제목</option>
                                    <option value="writer">작성자</option>
                                </select></td>
                                <td><input type="text" class="form-control"
                                           placeholder="검색어 입력" name="searchText" id="searchText" maxlength="100"></td>
                                <td><button type="button" class="btn btn-success" onclick="pageListNum('1')">검색</button></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
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

                <c:choose>
                    <c:when test="${fn:length(boardList) == 0}">
                        <tr>
                            <td colspan="6" style="text-align: center">등록된 게시물이 없습니다.</td>
                        </tr>
                    </c:when>
                <c:otherwise>
                    <c:forEach items="${boardList}" var="list" varStatus="status">
                        <tr>
                            <td><a href="/board/boardDetail?boardNo=${list.boardNo}">${list.rowNum}</a></td>
                            <td><a href="/board/boardDetail?boardNo=${list.boardNo}">${list.boardTitle}</a></td>
                            <td>${list.boardWriter}</td>
                            <td>${list.boardRegdate}</td>
                            <td>
                                <c:if test="${list.fileNo ne null}"><img src="../image/free-icon-attachments-304690.png"></c:if>
                            </td>
                            <td>${list.boardViewcount}</td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
                </c:choose>
                </tbody>

            </table>
            <div>
                <button type="button" class="btn btn-outline-info" onclick="location.href='boardWrite'" style="float: right;">등록</button>
            </div>
            <div style="text-align: center; font-size: x-large;">
                <c:set var="nowPageRange" value="${Math.ceil(1 / 10) * 10}"/><%--현재페이지NUM--%>
                <c:set var="startPageNum" value="${nowPageRange - 9}"/> <%--시작페이지NUM--%>
                <c:set var="totalPageNum" value="${Math.ceil(boardList[0].totalCount / 10)}"/><%--총페이지NUM--%>
                <c:set var="endPageNum" value="${nowPageRange > totalPageNum ? totalPageNum : nowPageRange}"/> <%--끝페이지NUM--%>

                <span id ="paging">
                    <c:forEach begin="${startPageNum}" end="${endPageNum}" var="num">
                        <%--선택시 강조처리--%>
                        <c:if test="${num eq '1'}">
                            <a onclick="pageListNum(${num})" style="font-weight: bold">${num}</a>
                        </c:if>
                        <c:if test="${num ne '1'}">
                            <a onclick="pageListNum(${num})">${num}</a>
                        </c:if>
                    </c:forEach>
                    <a onclick="pageListNum('2')"><img src="../image/page_next.png"> </a>
                    <a onclick="pageListNum(${totalPageNum})"><img src="../image/page_nnext.png"></a>
                    </span>
            </div>
        </div>
    </div>
</article>
</body>

<script type="text/javascript">
    function pageListNum(num) { // num 총 페이지 수
        let searchField = $("select[name=searchField]").val();
        let searchText = $("#searchText").val();
        console.log(searchField);

        var formData = new FormData();
        formData.append("page", num); //DTO를 타고 맵핑이 되고 컨트롤러에서 받는다.
        formData.append("searchField", searchField);
        formData.append("searchText", searchText);


        $.ajax({
            enctype : 'multipart/form-data',
            processData : false,
            contentType : false,
            cache : false,
            url : "./boardPageAjaxList",
            data : formData,
            type : "POST",
            success : function(res) { //꼭 res로 안해도 됨. 결과 값을 다시 받고
                //리스트 내용 생성
                let list = res.data|| []; // 있으면 값을 사용하고 아님 빈 객체로 사용
                let pageSize = 10; //리스트 ROW 출력 행수
                //console.log(list);
                //console.log(res.data);
                $("#boardList").empty();//비워줌

                let tbody = fn_getContentsHtml(list,num, res.data[0].totalCount);
                $("#boardList").append(tbody);
                //location.href = "./boardList";

                //페이지 버튼 생성
                //페이지의 모든 그림을 다시 출력하기 위해
                let pervPageNum = num-1; //이전 버튼 값
                let nextPageNum = num+1; //다음 버튼 값
                let pageTotal = Math.ceil(res.data[0].totalCount / pageSize); //전체 데이터 수에서 화면호출행수를 나눈거 마지막 페이지 번호가 된다.
                let nowPageRange = Math.ceil(num / 10) * 10 //클릭한 페이지를 기준으로 범위를 잡아줘 11-20 21-30
                let startPageNum =  nowPageRange - 9; //범위 정한거에 20-9 = 11 이
                let endPageNum;


                if (nowPageRange > pageTotal) {
                    endPageNum = pageTotal;
                } else {
                    endPageNum = nowPageRange;
                }

                $("#paging").empty();//비워줌

                if(num >1){ //클릭해서 받아온 페이지 값이 1이면 <이전>이 없음
                    $("#paging").prepend('<a onclick="pageListNum(1)"> <img src="../image/page_pprev.png"></a>');//제일 처음 버튼

                    $("#paging").append('<a onclick="pageListNum('+pervPageNum+')"> <img src="../image/page_prev.png"></a>'); //이전버튼

                }

                //번호를 다시 찍어주는 부분
                for(let i = startPageNum; i < endPageNum+ 1 ; i++){
                    //console.log("반복"+i);
                    //선택시 강조처리
                    if(i == num){
                        $("#paging").append('<a onclick="pageListNum('+i+')" style="font-weight: bold"> '+i+'</a>');
                    }else {
                        $("#paging").append('<a onclick="pageListNum(' + i + ')"> ' + i + '</a>'); //페이지 버튼
                    }
                }

                 if(num < res.data[0].totalCount /10 ){ //클릭한 num 값이 전체 totalCount(총데이터 수 ) / pagesize(출력행수) 한게 작을때 나와야한다.
                     $("#paging").append('<a onclick="pageListNum('+nextPageNum+')"> <img src="../image/page_next.png"> </a>'); //다음버튼
                     $("#paging").append('<a onclick="pageListNum('+pageTotal+')"> <img src="../image/page_nnext.png"></a>'); //제일 끝으로 버튼
                 }



                console.log("토탈"+pageTotal);
                console.log("시작페이지"+startPageNum);
                console.log("끝페이지"+endPageNum);
            },
            error :  function(res) {
                alert('게시글 등록 실패');
            }
        });
    }

    function fn_getContentsHtml(list,num, totalCount) {
        let contents = "";
        let imgYn = "";
        let pageRangeLastNum= (num) * 10;

        if(totalCount < pageRangeLastNum){ //205 < 210
            pageRangeLastNum = totalCount;
        }

        list.forEach((item,index) => {//item 별칭을 줘서 리스트 값을 뽑아올수있다. 리스트로 반복문을 태워서 /한행정보가 반복문 타서

           if(item.fileNo != null){
               imgYn = '<img src="../image/free-icon-attachments-304690.png">';
           }else{
               imgYn = '';
            }

           contents +=
                `<tr>
                <td class="num"><a href="/board/boardDetail?boardNo=\${item.boardNo}">\${item.rowNum}</td>
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