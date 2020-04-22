<%--
  Created by IntelliJ IDEA.
  User: 沐沐
  Date: 2019/6/17
  Time: 9:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .navigation {
            width: 100%;
            position: relative;
            height: 60px;
            font-size: 20px;
            color: #FFFFFF;
            background: -webkit-gradient(linear,0 0,100% 0,from(#ff937c),to(#ff6565));
            padding: 30px 15px;
            color:#FFFFFF;
        }
        a{
            color:#FFFFFF;
            text-decoration:none;
            padding-left: 100px;
        }
    </style>
</head>
<body>

    <div class="navigation">
        <a href="${pageContext.request.contextPath}/login.jsp" target="_blank">登录</a>
    </div>

</body>
</html>
