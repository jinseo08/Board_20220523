<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2022-05-23
  Time: 오후 1:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
</head>
<body>
<jsp:include page="./layout/header.jsp" flush="false"></jsp:include>
<table class="table">
    <tr>
        <th>글번호</th>
        <th>글제목</th>
        <th>작성자</th>
        <th>조회수</th>
        <th>작성시간</th>

    </tr>
    <tr>
        <td>${boardDetail.b_id}</td>
        <td>${boardDetail.boardTitle}</td>
        <td>${boardDetail.boardWriter}</td>
        <td>${boardDetail.boardHits}</td>
        <td>${boardDetail.boardCreatedDate}</td>
    </tr>
    <tr>
        <th>내용</th>
        <th>사진</th>
    </tr>
    <tr>
        <td>${boardDetail.boardContents}</td>
        <td><img src="${pageContext.request.contextPath}/upload/${boardDetail.boardFileName}"
                 alt="" height="350" width="350"></td>
    </tr>
</table>
<button onclick="pwCheck()">글수정</button>
<button onclick="pwCheck()">글삭제</button>
<button onclick="paging()">페이징목록</button>

<div class="container">
    <div id="comment-write">
        <input type="text" id="commentWriter" placeholder="작성자">
        <input type="text" id="commentContents" placeholder="내용">
        <button id="comment-write-btn">댓글작성</button>
    </div>
    <div id="comment-List">
        <table class="table">
            <tr>
                <th>댓글번호</th>
                <th>작성자</th>
                <th>내용</th>
                <th>작성시간</th>
            </tr>
            <c:forEach items="${commentList}" var="comment">
                <tr>
                    <td>${comment.id}</td>
                    <td>${comment.commentWriter}</td>
                    <td>${comment.commentContents}</td>
                    <td>${comment.commentCreatedDate}</td>
                </tr>
            </c:forEach>

        </table>
    </div>
</div>

</body>
<script>
    $("#comment-write-btn").click(function (){
       //alert("버튼클릭")
        //댓글작성자, 내용을 가져오고
        // ajax 문법을 활용하여 /comment/save 주소로 post 방식으로 작성자, 내용, 글번호 이렇게 3개의 값을 보내는 코드 작성
        let commentWriter = document.getElementById("commentWriter").value;
        let commentContents = document.getElementById("commentContents").value;
        let boardId = '${boardDetail.b_id}';
        $.ajax({
            type : "post",
            url : "/comment/save",
            data : {
                "commentWriter" : commentWriter,
                "commentContents":commentContents,
                "boardId": boardId
            },
            dataType : "json",
            success : function (result) {
                let output = "<table class='table'>";
                output += "<tr><th>댓글번호</th>";
                output += "<th>작성자</th>";
                output += "<th>내용</th>";
                output += "<th>작성시간</th></tr>";
                for(let i in result){
                    output += "<tr>";
                    output += "<td>"+result[i].id+"</td>";
                    output += "<td>"+result[i].commentWriter+"</td>";
                    output += "<td>"+result[i].commentContents+"</td>";
                    output += "<td>"+result[i].commentCreatedDate+"</td>";
                    output += "</tr>";
                }
                output += "</table>";
                document.getElementById('comment-List').innerHTML = output;
                document.getElementById('commentWriter').value='';
                document.getElementById('commentContents').value='';
            },
            error : function (){
                alert("어디가 틀렸을까")
            }
        })
    });

    function pwCheck(){
        location.href = "/board/pwCheck?b_id=${boardDetail.b_id}";
    }

    function paging(){
        location.href = "/board/paging?page=${page}"; // 직전에 있었던 페이지 값을 컨트롤러로 요청
    }
</script>
</html>
