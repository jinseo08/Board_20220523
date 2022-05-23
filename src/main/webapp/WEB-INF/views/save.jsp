<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2022-05-23
  Time: 오전 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
글작성페이지
<form action="/save" method="post">
    <input type="text" name="boardWriter" placeholder="작성자"><br>
    <input type="text" name="boardTitle" placeholder="제목"><br>
    <input type="text" name="boardPassword" placeholder="비밀번호"><br>
    <input type="text" name="boardContents" placeholder="내용"><br>
    <input type="submit" value="글작성">
</form>
</body>
</html>
