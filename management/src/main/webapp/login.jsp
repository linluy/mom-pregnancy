<%@ page import="com.wyyuand.domain.Admin" %><%--
  Created by IntelliJ IDEA.
  User: 沐沐
  Date: 2019/5/1
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%--http://127.0.0.1:8080/manage/login.jsp--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>好妈妈孕期管理后台登录界面</title>

    <style type="text/css">
        .mybody{
            background: url(asserts/images/mysplash_2.jpg) no-repeat;
            background-size: 100% 100%;
        }
        #idBtn{
            background:#F6A93D;
            border: none;
            color: black;
        }
    </style>
    <%--登陆界面的样式--%>
    <link
            href="asserts/bootstrap-3.3.7/css/bootstrap.min.css"
            rel="stylesheet" />
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.1.10.1.js"></script>
    <script  type="text/javascript" src="../js/index.js"></script>

    <script>
        $(function(){
            $("#idBtn").click(function () {
                var url = "${pageContext.request.contextPath}/admin/login";
                /*alert(url);*/
                $.ajax(url,{
                    type:'post',
                    data:{
                        admEmail:$("#admEmail").val(),
                        password:$("#password").val(),
                        checkcode:$("#checkcode").val()
                    },
                    success:function (data) {
                        if(data && data.success){
                            //alert("登陆成功");
                            window.location.href = "index.html";
                        }else{
                            alert(data.msg);
                        }
                    }
                })
            });
        })
    </script>

</head>

<body class="mybody">
        <div >
            <h1 style="text-align: center; margin-top: 250px;color: white; text-shadow: 0px 1px 0px #999, 0px 2px 0px #888, 0px 3px 0px #777, 0px 4px 0px #666, 0px 5px 0px #555, 0px 6px 0px #444, 0px 7px 0px #333, 0px 8px 7px #001135;" >欢迎进入好妈妈孕期管理APP后台管理系统</h1>
        </div>
        <form id="form1" class="well" method="post" action="${pageContext.request.contextPath}/admin/login"
              style="width: 30em;height: 350px; margin: auto; margin-bottom:70px;margin-top: 60px;
               background: linear-gradient(#EEF6E0,#F89614);
              ">
            <%--background-color:#BAD586;--%>
            <h3>管理员登录</h3>
            <%--管理员姓名 模块--%>
            <div class=" input-group input-group-md">
                          <span class="input-group-addon" id="sizing-addon1">
                              <i class="glyphicon glyphicon-user" ></i>
                          </span>
                <input id="admEmail" type="text" class="form-control" placeholder="请输入邮箱"/>
            </div>
            <%--管理员密码模块--%>
            <br />
            <div class="input-group input-group-md">
                          <span class="input-group-addon" id="sizing-addon2">
                              <i class="glyphicon glyphicon-lock"></i>
                          </span>
                <input type="password" id="password" class="form-control" placeholder="请输入密码"/>
            </div>
            <br/>
                <%--验证码--%>
            <div class="input-group input-group-md">
                          <span class="input-group-addon" id="sizing-addon3"><i
                                  class="glyphicon glyphicon-lock"></i> </span>
                <input type="text" id="checkcode" class="form-control" name="yzm" style="width: 50%" />&nbsp;&nbsp;&nbsp;&nbsp;
                <img src="${pageContext.request.contextPath}/vc/show"/>
            </div>
                <%--login_register--%>

            <br/>
            <button type="button" id="idBtn" class="btn btn-success btn-block"><b>登&nbsp;&nbsp;&nbsp;&nbsp;录</b></button>
   <%--Admin/register/register_list.jsp--%>
            <%--style="text-align: left;--%>
                <%--login_register/forgetPassword_list.jsp--%>
            <div  style="width:100%">
                <div><a class="btn btn-sm btn-white btn-block"  style="width:75%;float:left;"  th:href="@{register}" href="${pageContext.request.contextPath}/login_register/register_list.jsp"><h6>还没有账户?前往注册</h6></a></div>
                <div><a class="btn btn-sm btn-white btn-block"  style="width:25%;float:left;" th:href="@{forgetPassword}" href="${pageContext.request.contextPath}/login_register/forgetPassword_list.jsp"><h6>忘记密码？</h6></a></div>
            </div>

          </form>
        <div style="text-align: center; background:#FA8904;line-height:30px;color: white" >
            <span>哈学院计算机科学与技术1班——好妈妈孕期管理系统APP后台管理系统</span> &nbsp; &nbsp; &nbsp; &nbsp;
            <i>版权所有 &copy; 2019</i>  &nbsp; &nbsp; &nbsp; &nbsp;<i>联系电话：15704605626</i>
        </div>


</body>

</html>
