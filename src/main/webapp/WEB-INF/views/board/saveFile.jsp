<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2022-05-24
  Time: 오후 3:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../layout/header.jsp" flush="false"></jsp:include>
글작성페이지
<form action="/board/saveFile" method="post" enctype="multipart/form-data">
    <input type="text" name="boardWriter" placeholder="작성자"><br>
    <input type="text" name="boardTitle" placeholder="제목"><br>
    <input type="password" name="boardPassword" placeholder="비밀번호"><br>
    <textarea name="boardContents" rows="10" cols="20"></textarea><br> <!-- 장문의 글 쓸때는 textarea 많이 씀 -->
    <input type="file" name="boardFile">
    <input type="submit" value="글작성">
</form>
</html>
