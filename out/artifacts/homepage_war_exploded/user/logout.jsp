<%--
  Created by IntelliJ IDEA.
  User: heosangbeom
  Date: 2024. 7. 14.
  Time: 오전 1:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
session.invalidate();
%>
<script>
    location.href='board.do'
</script>
</body>
</html>
