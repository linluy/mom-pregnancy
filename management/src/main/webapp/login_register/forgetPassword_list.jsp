<%--
  Created by IntelliJ IDEA.
  User: 沐沐
  Date: 2019/5/7
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%--D:/IdeaProjects/大三下/graduationproject/management-of-pregnancy--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>找回密码</title>
</head>


<frameset rows="100,*,50" cols="*" frameborder="no" border="0" framespacing="0">
    <!--1-->
    <frame src="${pageContext.request.contextPath}/login_register/forget_top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
    <!--2-->
    <frame src="${pageContext.request.contextPath}/login_register/item_forgetPassword.jsp" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame" />
    <!--底部-->
    <%--3.0--%>
    <frame src="${pageContext.request.contextPath}/footer.html" name="bottomFrame" scrolling="No" noresize="noresize" id="bottomFrame" title="bottomFrame" />
</frameset>
<%--
<body>
    <h1>忘记密码</h1>
</body>--%>
</html>
