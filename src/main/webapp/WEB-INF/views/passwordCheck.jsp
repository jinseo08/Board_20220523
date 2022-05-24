<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2022-05-23
  Time: 오후 1:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <input type="password" id=pw placeholder="비밀번호를 입력해주세요">
    <input type="button" onclick="delete1()" value="글삭제">
    <input type="button" onclick="updateCk()" value="글수정">
</body>
<script>
    function delete1(){
        const pw = document.getElementById("pw").value;
        const pwDB = "${boardDetail.boardPassword}"
        console.log(pwDB)
        if(pw == pwDB) {
            location.href = "/board/delete?b_id=${boardDetail.b_id}";
        }else{
            <%--일치하지 않으면 상세조회 화면으로--%>
            <%--location.href = "/board/detail?b_id=${boardDetail.b_id}"--%>
           alert("비밀번호가 틀립니다.");
        }
    }

    function updateCk(){
        const pw = document.getElementById("pw").value;
        const pwDB = "${boardDetail.boardPassword}"
        if(pw == pwDB) {
            location.href = "/board/updateCk?b_id=${boardDetail.b_id}";
        }else{
            alert("비밀번호가 틀립니다.");
        }
    }

</script>
</html>
