<%--
  Created by IntelliJ IDEA.
  User: 沐沐
  Date: 2019/5/5
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%--http:127.0.0.1:8080//manage/WEB-INF/register/register_list.jsp--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
    <title>管理员注册界面</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.min.1.10.1.js"></script>

    <style >
          .mybody {
              background: url(../asserts/images/back.jpg) no-repeat;
              background-size: 100% 100%;
              font-family: 微软雅黑;
          }
        input{
            height:30px;
            width:250px;
            padding-right:50px;
            background:white;
        }
        #inSub{
            background-color: #FD7F07;
            border: none;
            width:250px ;
            height:30px;
        }
          a{
              color:black;
              text-decoration:none;
              padding:100px ;
              font-size: 30px;
          }

    </style>
    <%--js/jquery-1.7.2.min.js--%>
    <script src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js" ></script>

    <script>
        $(function(){
            $("#inSub").click(function () {
                var url = "${pageContext.request.contextPath}/admin/save";
                /*alert(url);*/
                $.ajax(url,{
                    type:'post',
                    data:{
                        admName:$("#admName").val(),
                        admDisplayName:$("#admDisplayName").val(),
                        admSex:$("input[name='admSex']").val(),
                        admPassword:$("#admPassword").val(),
                        admExpDate:$("#admExpDate").val(),
                        admEmail:$("#admEmail").val()
                    },
                    success:function (data) {
                        if(data && data.success){
                            alert("恭喜你！注册成功");
                            window.location.href = "${pageContext.request.contextPath}/login.jsp";
                        }else{
                            alert(data.msg);
                        }
                    }
                })
            });
        })
    </script>

</head>

<body class="mybody"   >
<div class="navigation">
    <a href="${pageContext.request.contextPath}/login.jsp" target="_blank">登录</a>
</div>
        <div style="margin-bottom: 100px;">
            <h1 style="text-align: center; margin-top: 100px;color: white; text-shadow: 0px 1px 0px #999, 0px 2px 0px #888, 0px 3px 0px #777, 0px 4px 0px #666, 0px 5px 0px #555, 0px 6px 0px #444, 0px 7px 0px #333, 0px 8px 7px #001135;" ></h1>

<%--margin: auto; m--%>
        <div style="width: 30em;height: 650px; argin-bottom:40px;margin-top: 40px;margin: auto;;
               background:rgba(255,255,255,0.3);" >
          <br/><br/><br/>

            <div style="margin:auto;margin-left: 120px">
                    <div>
                        <h1>管理员注册</h1>
                    </div>
                <%--1--%>
                    <div>
                        <span class="p">*</span>
                        <label>管理员名称:</label>
                        <div style="margin-top: 5px;margin-bottom: 15px">
                            <input id="admName"  name="admName" type="text" />
                            <span style="position:absolute;right:18px;top:2px;height:16px;width:16px;display:inline-block;" ></span>
                        </div>
                    </div>
                    <%--2--%>
                    <div>
                        <span class="p">*</span>
                        <label>管理员显示名称:</label>
                        <div style="margin-top: 5px;margin-bottom: 15px">
                            <input id="admDisplayName" name="admDisplayName" type="text"/>
                            <span style="position:absolute;right:18px;top:2px;height:16px;width:16px;display:inline-block;" ></span>
                        </div>
                    </div>
                        <%--3--%>
                    <div style="margin-top: 5px">
                        <span class="p">*</span>
                        <%--<label>性别:</label>--%>
                        性别：
                            <input name="admSex" type="radio" value="男"  style="height: auto;width: auto"/>男
                            <input name="admSex" type="radio" value="女"  style="height: auto;width: auto"/>女
                            <span style="position:absolute;right:18px;top:2px;height:16px;width:16px;display:inline-block;" ></span>
                    </div>
                    <%--4--%>
                    <div style="margin-top: 10px;">
                        <span class="p">*</span>
                        <label>密码:</label>
                        <div style="margin-top: 5px;margin-bottom: 15px">
                            <input id="admPassword" type="text">
                            <span style="position:absolute;right:18px;top:2px;height:16px;width:16px;display:inline-block;" ></span>
                        </div>
                    </div>
                <%--5--%>
                <div style="margin-top: 10px;">
                    <span class="p">*</span>
                    <label>邮箱:</label>
                    <div style="margin-top: 5px;margin-bottom: 15px">
                        <input id="admEmail" type="text">
                        <span style="position:absolute;right:18px;top:2px;height:16px;width:16px;display:inline-block;" ></span>
                    </div>
                </div>
                    <%--6--%>
                    <div>
                        <span class="p">*</span>
                        <label >过期时间:</label>
                        <div style="margin-top: 5px;margin-bottom: 15px">
                            <input  id="admExpDate" name="admExpDate" type="text" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy/MM/dd HH:mm:ss'})"/>
                            <span style="position:absolute;right:18px;top:2px;height:16px;width:16px;display:inline-block;" ></span>
                        </div>
                    </div>

                    <div>
                        <br/>
                        <button id="inSub" name="submit"  type="submit" >注册</button>
                    </div>
        </div>
        </div>
    </div>
</body>
</html>
