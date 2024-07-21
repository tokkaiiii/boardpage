<%@ page import="user.mvc.model.UserService" %>
<%@ page import="mvc.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: heosangbeom
  Date: 2024. 7. 13.
  Time: 오전 1:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String id = null;
    if (session.getAttribute("id") == null) {
        id = (String) session.getAttribute("id");
    }
    if (id != null) {
%>
<script>
    alert('이미 로그인 돼있습니다')
    location.href='board.do';
</script>
<%
    }
%>

<script>
  const signupValid = <%=(int)request.getAttribute("signupValid")%>;
  if (signupValid === 1) {
    <%
    int signupValid = (int) request.getAttribute("signupValid");
    User user = (User) request.getAttribute("user");
    if (signupValid == 1) {
        session.setAttribute("id", user.getId());
    }
    %>
    alert('로그인 성공');
    location.href = '/board.do';
  } else if (signupValid === 0) {
    alert('비밀번호가 틀렸습니다');
    location.href = 'index.jsp';
  } else if (signupValid === -1) {
    alert("해당 id는 없습니다")
    location.href = 'index.jsp';
  } else {
    alert('다시 한번 확인해주세요')
    location.href = 'index.jsp';
  }
</script>
</html>
