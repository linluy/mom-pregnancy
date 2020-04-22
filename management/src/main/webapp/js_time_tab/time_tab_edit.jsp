<%--
  Created by IntelliJ IDEA.
  User: 沐沐
  Date: 2019/6/11
  Time: 9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>孕周管理更新</title>

    <script type="text/javascript" src="${pageContext.request.contextPath}/asserts/utf8-jsp/ueditor.config.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/asserts/utf8-jsp/ueditor.all.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <style type="text/css">

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

        fieldset{
            border: 0;
        }
        .forminfo li{
            margin-top: 20px;
            color: #597190;
            font-weight:bold;
        }
        input{
            border-radius: 3px;
            padding-left:5px;
            border: 1px solid #ff937c;
            padding: 10px 15px;
            margin-left: 10px;
        }

        hr{
            margin-top: 20px;
            margin-bottom: 40px;
            color: gainsboro;
        }
        #sub{
            background: #597190;
            border-color: black;
            color: #FFFFFF;

        }
        option{
            color: red;
        }
    </style>
    <script>
        $(function()
        {
            {
                var ue1 = UE.getEditor('container1');
            }
        })

    </script>
</head>

<body>

<li class="link" style="font-size: 30px;margin-left: 40px;margin-top: 40px"><a href="${pageContext.request.contextPath}/pretime/listByPage?" target="content">返回</a></li>

<%--	` check_id `, ` check_pre `, ` check_second `, ` check_usu `, ` check_esp `, ` STATUS `       --%>
<fieldset  style="width: 70%;margin-left: 200px;margin-top: 100px" >

    <form action="${pageContext.request.contextPath}/pretime/saveEdit" method="post" enctype="multipart/form-data">

        <input type="hidden" name="checkId" value="${timeTab.checkId}" />
        <input type="hidden" name="checkPre" value="${timeTab.checkPre}" />
        <input type="hidden" name="checkSecond" value="${timeTab.checkSecond}" />
        <ul class="forminfo">

            <li>

                <lable >怀孕周期：</lable>第${timeTab.checkPre}周
                <%--<input type="text" name="checkPre"   value="${timeTab.checkPre}">--%>


                &nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;
                <lable>第几次产检：</lable>第${timeTab.checkSecond}次
                <%--<input type="text" name="checkSecond"   value="${timeTab.checkSecond}">--%>

            </li>

            <hr/>

            <li>
                <lable >重点项目</lable>
                <input type="text" name="checkKey"   value="${timeTab.checkKey}">

            </li>


            <li >
                <lable >产检项目</lable>
                <script id="container1" name="checkUsu" type="text/plain" >${timeTab.checkUsu}</script>
            </li>

            <hr/>
            <%--设置下拉选2--%>
            <div  align="middle" >
                <input id="sub" type="submit" value="更新" style="width: 200px">
            </div>

    </form>

</fieldset>

</form>
</body>
</html>
