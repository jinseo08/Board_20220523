<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2022-05-23
  Time: 오후 1:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <style>
    </style>
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
</body>
<script>
    function pwCheck(){
        location.href = "/board/pwCheck?b_id=${boardDetail.b_id}";
    }
</script>
</html>
