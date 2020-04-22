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

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <style type="text/css">

       /* fieldset{
            border: 0;
        }*/
        input{
            border-radius: 3px;
            padding-left:5px;
            border: 3px solid #9CA274;
            padding: 10px 15px;
            margin-left: 10px;
            margin-top: 10px;
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
    </style>

    <script type="text/javascript" src="${pageContext.request.contextPath}/asserts/utf8-jsp/ueditor.config.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/asserts/utf8-jsp/ueditor.all.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
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
            <div class="" style="color:#9CA274;font-weight: bold;color: #455B06;margin-bottom: 50px;text-align: center">
                孕期知识添加
            </div>
        <form action="${pageContext.request.contextPath}/knowledge/save"  method="post" enctype="multipart/form-data">


            <ul class="forminfo">
                <li>
                    <lable >标题:&nbsp;&nbsp;&nbsp;&nbsp;</lable>
                    <input type="text" name="title"  >
                </li>
                <li>
                    <lable >发布者:</lable>
                    <input type="text" name="author"  >
                </li>

                <li>
                    <lable >图片:&nbsp;&nbsp;&nbsp;&nbsp;</lable>
                    <input type="file" name="pics"/><br/><br/>
                </li>

                <li>
                    <lable >正文</lable>
                    <script id="container" name="content" type="text/plain" width="200px" heigh="300px"></script>
                </li>

               <div  align="center" >
                   <input type="submit" value="确认添加" style="background: #303E03;border-color:#303E03;color: #FFFFFF;text-align: center">
               </div>


            </ul>
             </form>
        </fieldset>
    </div>
</body>
</html>
