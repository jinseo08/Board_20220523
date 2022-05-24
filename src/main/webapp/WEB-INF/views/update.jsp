<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2022-05-24
  Time: 오전 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
수정페이지
<form action="/board/update?b_id=${boardDetail.b_id}" method="post">
    <input type="text" name="boardWriter" value="${boardDetail.boardWriter}" readonly><br>
    <input type="text" name="boardTitle" value="${boardDetail.boardTitle}"><br>
    <input type="password" name="boardPassword" value="${boardDetail.boardPassword}" readonly><br>
    <textarea name="boardContents" rows="5" cols="10" >${boardDetail.boardContents}</textarea><br>
    <input type="submit" value="글수정">
</form>
</body>
</html>
