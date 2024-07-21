<%@ page import="mvc.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: heosangbeom
  Date: 2024. 7. 13.
  Time: 오후 4:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script>
  const joinValid = <%=request.getAttribute("joinValid")%>;
  if (joinValid === -1) {
    alert('다시 입력해주세요')
    location.href = 'user/join.jsp'
  } else {
    <%
    User user = (User)request.getAttribute("user");
      session.setAttribute("id", user.getId());
    %>
    alert('가입이 성공했습니다')
    location.href = 'board.do'
  }
</script>
</html>
