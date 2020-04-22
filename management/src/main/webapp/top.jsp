<%--
  Created by IntelliJ IDEA.
  User: 沐沐
  Date: 2019/7/14
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link href="asserts/css/style.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" src="js/jquery-1.12.4.min.js"></script>

  <%--  <script type="text/javascript">
        $(function(){
            //顶部导航切换
            $(".nav li a").click(function(){
                $(".nav li a.selected").removeClass("selected")
                $(this).addClass("selected");
            })
        })
    </script>--%>

</head>

<body style="background:url(asserts/images/topbg.jpg) repeat-x;">

    <div class="topleft" >
        <%--<a href="main.html" target="_parent"><img src="asserts/images/logo.png" title="系统首页" /></a>--%>
        <a href="index.html" target="_parent" style="align: center;font-size: 20px;color: white "><img src="asserts/images/topleft.png" title="系统首页" style="margin-right: 10px" />好妈妈孕期管理</a>
    </div>

    <div class="topright">

        <%--<img src="asserts/images/topbg.jpg">--%>
        <%--管理员的信息--%>
        <div class="user" style="color: #FFFFFF">
            <%--<span>${SEESION_LOGIN_DOMAIN.displayName}</span>--%>
            <span><a  href="admin/adminInfo?adminId=${sessionScope.adminId}" target="content">${sessionScope.LoginUser}</a></span>
            <%--<img src="asserts/images/goodMoom.png" align="absmiddle">--%>
                <span><b><a href="login.jsp" target="_parent">【退出】</a></b></span>
        </div>

    </div>
</body>

</html>
