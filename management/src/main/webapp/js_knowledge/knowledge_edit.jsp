<%--
  Created by IntelliJ IDEA.
  User: 沐沐
  Date: 2019/6/10
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>

    <script type="text/javascript" src="${pageContext.request.contextPath}/asserts/utf8-jsp/ueditor.config.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/asserts/utf8-jsp/ueditor.all.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <style type="text/css">
        input{
            width: 300px;
            border-radius: 3px;
            padding-left:5px;
            border: 3px solid #9CA274;
            padding: 5px 5px;
            margin-left: 10px;
            margin-bottom: 15px;
        }

        a{
            color:black;
        }
        a:link {
            color:#455B06;
            text-decoration:none;
        }
        a:hover {
            color:orange;
            text-decoration:none;
        }
        #sub{
            background: #597190;
            border-color: black;
            color: #FFFFFF;

        }
    </style>
    <script>
        $(function()
        {
            {
                var ue = UE.getEditor('container');
            }
        })
    </script>
</head>
<body>
<li class="link" style="font-size: 30px;margin-left: 40px;margin-top: 40px"><a href="${pageContext.request.contextPath}/knowledge/listByPage" target="content" >返回</a></li>

<div class="formbody" style="float: left;margin-top: 50px;">
    <fieldset  style="width: 70%;margin-left: 200px;"  >
        <div class="" style="color:#9CA274;font-weight: bold;color: #455B06;margin-bottom: 50px;text-align: center;font-size: 30px">
            孕期知识修改
        </div>
        <form action="${pageContext.request.contextPath}/knowledge/saveEdit" method="post" enctype="multipart/form-data">

            <ul class="forminfo">
                <input type="hidden" name="id" value="${knowledge.id}" />
                <li>
                    <lable >标题：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</lable>
                    <input type="text" name="title"   value="${knowledge.title}">
                </li>
                <li>
                    <lable >发布者：&nbsp;&nbsp;</lable>
                    <input type="text" name="author" value="${knowledge.author}" >
                </li>

                <li>
                    <lable >原图片名: &nbsp;&nbsp;&nbsp;&nbsp;</lable>${knowledge.images}
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;
                </li>
                <li>
                    <lable >修改图片：</lable>
                    <input type="file" name="pics"/><br/><br/>
                </li>
                <hr/>

                <li>
                    <lable >正文</lable>
                    <script id="container" name="content" type="text/plain" width="200px" heigh="300px">${knowledge.content}</script>
                </li>

               <%-- <li><lable >&nbsp;&nbsp;</lable>
                    <input type="submit" value="更新孕期知识">
                </li>--%>
                <%--设置下拉选2--%>
                <div  align="middle" >
                    <input id="sub" type="submit" value="更新孕期知识" style="width: 200px">
                </div>

            </ul>
        </form>
    </fieldset>
</div>
</body>
</html>
