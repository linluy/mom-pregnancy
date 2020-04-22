<%--
  Created by IntelliJ IDEA.
  User: 沐沐
  Date: 2019/6/11
  Time: 9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js "></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/asserts/bootstrap-3.3.7/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/asserts/bootstrap-3.3.7/js/bootstrap.js"></script>

    <style type="text/css">

        table tr td{
            /*border:none;*/
            margin-top: 10px;
            padding-top: 10px;
            padding-bottom: 10px;
            margin-left: 20px;
            margin-right: 20px;
            height: 50px;
            text-align: center;
            font-size: medium;
            font-weight: bold;
        }
        #inSub{
            background: #597190;
            border-color: black;
            color: #FFFFFF;

        }
    </style>

    <style type="text/css">
        td input{
            border: none;
            overflow: hidden;
            height: 100%;
            width: 100%;
            text-align: center
        }
    </style>

    <script>
        $(function(){
            $("#inSub").click(function () {
                var url = "${pageContext.request.contextPath}/admin/updateAdm";
                /*alert(url);*/
                $.ajax(url,{
                    type:'post',
                    data:{
                        admId:$("#admId").val(),
                        admName:$("#admName").val(),
                        admDisplayName:$("#admDisplayName").val(),
                        admSex:$("input[name='admSex']").val(),
                        admPassword:$("#admPassword").val(),
                        admEmail:$("#admEmail").val(),
                        admStatus:$("#admStatus").val(),
                        admExpDate:$("#admExpDate").val()
                    },
                    success:function (data) {
                        if(data && data.success){
                            alert("恭喜你！更新成功");
                            window.location.href = "${pageContext.request.contextPath}/content.html";
                        }else{
                            alert(data.msg);
                        }
                    }
                })
            });
        })
    </script>
</head>
<body>

<li class="link" style="font-size: 30px;margin-left: 80px;margin-top: 40px"><a href="${pageContext.request.contextPath}/knowledge/listByPage" target="content" >返回</a></li>

<%-- adm_id,adm_name,adm_display_name,adm_sex,adm_password,adm_create_time,adm_exp_date,adm_email,adm_status --%>
<div style="float:left; width: 80%;height: 100%;margin-top: 10px;" >

    <input type="hidden" id="admStatus" value="${timeTab.admStatus}" />
    <input type="hidden" id="admId" value="${admin.admId}" />
    <input type="hidden" id="admExpDate" value="${admin.admExpDate}" />

    <table width="50%" border="1"  cellspacing="0" cellpadding="0" align="center">
        <col style="width: 15%" />
        <col style="width: 85%" />
        <tr>
            <td>用户ID</td>
            <td>${admin.admId}</td>
        </tr>
        <tr>
            <td>用户名</td>
            <td><input  id="admName"  type="text" value="${admin.admName}"></td>
        </tr>
        <tr>
            <td>显示名</td>
            <td ><input  id="admDisplayName"  type="text" value="${admin.admDisplayName}"></td>
        </tr>

        <tr>
            <td>邮箱</td>
            <td><input  id="admEmail"  type="text" value="${admin.admEmail}"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input  id="admPassword"  type="text" value="${admin.admPassword}"></td>
        </tr>
        <tr>
            <td>性别</td>
            <td>
                <input name="admSex" type="radio" value="男" type="text" style="height: auto;width: auto"  <c:if test="${admin.admSex eq '男'}"> checked="checked"</c:if> />男
                <input name="admSex" type="radio" value="女" type="text" style="height: auto;width: auto" <c:if test="${admin.admSex eq '女'}"> checked="checked"</c:if> />女
            </td>

        </tr>


        <tr>
            <td>修改时间</td>
            <td><fmt:formatDate value="${admin.admCreateTime}"/></td>
        </tr>
     </table>
    <div  align="middle" style="margin-top: 40px;" >
        <BUTTON  id="inSub" name="submit" >更新</BUTTON>
    </div>
</div>



</body>
</html>
