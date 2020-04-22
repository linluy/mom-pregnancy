<%--
  Created by IntelliJ IDEA.
  User: 沐沐
  Date: 2019/7/16
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
                var ue2 = UE.getEditor('container2');
            }
        })
    </script>
</head>
<body>
<li class="link" style="font-size: 30px;margin-left: 40px;margin-top: 40px"><a href="${pageContext.request.contextPath}/prestatus/listByPage" target="content" >返回</a></li>

<div class="formbody" style="float: left;margin-top: 50px;">
    <fieldset  style="width: 70%;margin-left: 200px;"  >
        <div class="" style="color:#9CA274;font-weight: bold;color: #455B06;margin-bottom: 50px;text-align: center;font-size: 30px">
            孕周管理修改
        </div>
        <form action="${pageContext.request.contextPath}/prestatus/saveEdit" method="post" enctype="multipart/form-data">

            <ul class="forminfo">
                <input type="hidden" name="id" value="${prestatus.id}" />

                <li>
                    <lable >周期：</lable>
                    <input type="text" name="period" value="${prestatus.period}" >
                </li>

                <li>
                    <lable >体重：</lable>
                    <input type="text" name="weight"   value="${prestatus.weight}">
                </li>
                <li>
                    <lable >身长：</lable>
                    <input type="text" name="len" value="${prestatus.len}" >
                </li>
                <hr/>
              <%--  <li>
                    <lable >宝宝：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</lable>
                    <input type="text" name="babySay"   value="${prestatus.babySay}">
                </li>
                <li>
                    <lable >妈妈：&nbsp;&nbsp;</lable>
                    <input type="text" name="momSay" value="${prestatus.momSay}" >
                </li>
                <hr/>--%>

                <li>
                    <lable >原图片名: &nbsp;&nbsp;&nbsp;&nbsp;</lable>${prestatus.picture}
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
                    <lable >宝宝</lable>
                    <script id="container" name="babySay" type="text/plain" width="200px" heigh="300px">${prestatus.babySay}</script>
                </li>
                <li>
                    <lable >妈妈</lable>
                    <script id="container2" name="momSay" type="text/plain" width="200px" heigh="300px">${prestatus.momSay}</script>
                </li>

             <%--   <li><lable >&nbsp;&nbsp;</lable>
                    <input type="submit" value="更新孕周管理">
                </li>--%>
            </ul>

            <div  align="middle" >
                <input id="sub" type="submit" value="更新" style="width: 200px">
            </div>

        </form>
    </fieldset>
</div>
</body>
</html>
