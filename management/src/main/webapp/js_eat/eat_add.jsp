<%@ page import="com.wyyuand.domain.Food" %><%--
  Created by IntelliJ IDEA.
  User: 沐沐
  Date: 2019/6/11
  Time: 9:15
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/asserts/utf8-jsp/ueditor.config.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/asserts/utf8-jsp/ueditor.all.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <style type="text/css">

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
            border: 3px solid #9CA274;
            padding: 10px 15px;
            margin-left: 10px;
        }

        hr{
            margin-top: 20px;
            margin-bottom: 40px;
            color: gainsboro;
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
                var ue1 = UE.getEditor('container1');
                var ue2 = UE.getEditor('container2');
                var ue3 = UE.getEditor('container3');
                var ue4 = UE.getEditor('container4');
                var ue5 = UE.getEditor('container5');
                var ue6 = UE.getEditor('container6');
                var ue7 = UE.getEditor('container7');
            }
        })


        function getType(){
            var type = document.getElementById( "ifeat1").value;    //得到的是类型改变后的值
            document.getElementById("type").value = type;
        }
        function getUsuallyEat(){
            var eat = document.getElementById( "ifeat2").value;//得到列表改变后的值
            document.getElementById("pwUsuallyEat").value = eat;
        }
        function getParturientJ(){
            var eat = document.getElementById( "ifeat3").value   ;//得到列表改变后的值
            document.getElementById("pwJ").value=eat;
        }
        function getBfpJ(){
            var eat = document.getElementById( "ifeat4").value;//得到列表改变后的值
            document.getElementById("bfpJ").value=eat;
        }
        function getBabyJ(){
            var eat = document.getElementById( "ifeat5").value   ;//得到列表改变后的值
            document.getElementById("babyJ").value=eat;
        }

    </script>
</head>
<body>


<%
    Object type = session.getAttribute("type");
%>
<li class="link" style="font-size: 30px;margin-left: 40px;margin-top: 40px"><a href="${pageContext.request.contextPath}/food/listByPage?type=<%=type%>" target="content" >返回</a></li>

<fieldset  style="width: 70%;margin-left: 200px;margin-top: 60px" >
    <div class="" style="color:#9CA274;font-weight: bold;color: #455B06;margin-bottom: 50px;">
        <h1><li>食物添加</li></h1>
    </div>
<form action="${pageContext.request.contextPath}/food/save"  method="post" enctype="multipart/form-data">

    <ul class="forminfo">

        <li>
            <lable >名字</lable>
            <%--<input type="text" name="variety"   value="${food.variety}">--%>
            <input type="text" name="variety"  >

            &nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;
            <lable >分类</lable>
            <%--<input type="text" name="alias"   value="${food.alias}">--%>
            <input type="text" name="alias"  >
        </li>
        <hr/>

        <li>
            <lable >类型</lable>
            <%--<input type="text" name="type"   value="${food.type}">--%>
            <%--<input type="text" name="type"  >--%>
            <select name="ifeat" onchange="getType()" id="ifeat1" >
                <option selected="selected"></option>
                <option value="1">1-五谷杂粮</option>
                <option value="2">2-饮品饮料</option>
                <option value="3">3-水果</option>
                <option value="4">4-肉禽类</option>
                <option value="5">5-奶制品</option>
                <option value="6">6-水产海鲜</option>
                <option value="7">7-零食小吃</option>
                <option value="8">8-蔬菜菌类</option>
                <input type="hidden" name="type" id="type" value="${food.type}">
                <%--
                    <input type="hidden" id="tempString" name="tempString" />
                --%>
            </select>
            &nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;

            <lable >哪周可以使用</lable>
           <%-- <select name="ifeat" onchange="getUsuallyEat()" id="ifeat2" >
                <option selected="selected"></option>
                <option value="0-不经常使用">0-不经常使用</option>
                <option value="1-经常使用">1-经常使用</option>
                <input type="hidden" name="pwUsuallyEat" id="pwUsuallyEat">
            </select>--%>
            <select name="ifeat" onchange="getUsuallyEat()" id="ifeat2" >
                <option selected="selected"></option>
                <option value="1">1-第一周</option>
                <option value="2">2-第二周</option>
                <option value="3">3-第三周</option>
                <option value="4">4-第四周</option>
                <option value="5">5-第五周</option>
                <option value="6">6-第六周</option>
                <option value="7">7-第七周</option>
                <option value="8">8-第八周</option>
                <option value="9">9-第九周</option>
                <option value="10">10-第十周</option>
                <option value="11">11-第十一周</option>
                <option value="12">12-第十二周</option>
                <option value="13">13-第十三周</option>
                <option value="14">14-第十四周</option>
                <option value="15">15-第十五周</option>
                <option value="16">16-第十六周</option>
                <option value="17">17-第十七周</option>
                <option value="18">18-第十八周</option>
                <option value="19">19-第十九周</option>
                <option value="20">20-第二十周</option>
                <option value="21">21-第二十一周</option>
                <option value="22">22-第二十二周</option>
                <option value="23">23-第二十三周</option>
                <option value="24">24-第二十四周</option>
                <option value="25">25-第二十五周</option>
                <option value="26">26-第二十六周</option>
                <option value="27">27-第二十七周</option>
                <option value="28">28-第二十八周</option>
                <option value="29">29-第二十九周</option>
                <option value="30">30-第三十周</option>
                <option value="31">31-第三十一</option>
                <option value="32">32-第三十二周</option>
                <option value="33">33-第三十三周</option>
                <option value="34">34-第三十四周</option>
                <option value="35">35-第三十五周</option>
                <option value="36">36-第三十六周</option>
                <option value="37">37-第三十七周</option>
                <option value="38">38-第三十八周</option>
                <option value="39">39-第三十九周</option>
                <option value="40">40-第四十周</option>
                <option value="41">41-第四十一周</option>
                <input type="hidden" name="pwUsuallyEat" id="pwUsuallyEat">
            </select>

        </li>
        <hr/>

        <li>
            <lable >图片: </lable>
            <input type="file" name="pics"/><br/><br/>
        </li>
        <hr/>
        <%--设置下拉选1--%>

        <li>
            <lable >孕妇能不能吃</lable>
            <%--
                            <input type="text" name="pwJ"   value="${food.pwJ}">
            --%>
            <select name="ifeat" onchange="getParturientJ()" id="ifeat3" >
                <option selected="selected">${food.parturientJ}</option>
                <option value="0-不能吃">0-不能吃</option>
                <option value="1-慎吃">1-慎吃</option>
                <option value="2-能吃">2-能吃</option>
                <input type="hidden" name="pwJ" id="pwJ" value="${food.pwJ}">
                <%--
                    <input type="hidden" id="tempString" name="tempString" />
                --%>
            </select>
        </li>

        <li >
            <lable >怎么吃</lable>
            <script id="container1" name="pw" type="text/plain" ></script>
        </li>
        <hr/>

        <li>
            <lable >哺乳期能不能吃</lable>
            <%--
                            <input type="text" name="bfpJ"   value="${food.bfpJ}">
            --%>
            <select name="ifeat" onchange="getBfpJ()" id="ifeat4" >
                <option selected="selected">${food.bfpJ}</option>
                <option value="0-不能吃">0-不能吃</option>
                <option value="1-慎吃">1-慎吃</option>
                <option value="2-能吃">2-能吃</option>
                <input type="hidden" name="bfpJ" id="bfpJ">
            <%--
                    <input type="hidden" id="tempString" name="tempString" />
                --%>
            </select>

        </li>
        <li  >
            <lable >怎么吃</lable>
            <script id="container3" name="bfp" type="text/plain" >${food.bfp}</script>
        </li>
        <hr/>
        <%--设置下拉选4--%>
        <li>
            <lable >宝宝能不能吃</lable>
              <%--<input type="text" name="babyJ"   value="${food.babyJ}">--%>
            <select name="ifeat" onchange="getBabyJ()" id="ifeat5" >
                <option selected="selected">${food.babyJ}</option>
                <option value="0-不能吃">0-不能吃</option>
                <option value="1-慎吃">1-慎吃</option>
                <option value="2-能吃">2-能吃</option>
                <input type="hidden" name="babyJ" id="babyJ">

            <%--
                    <input type="hidden" id="tempString" name="tempString" />
                --%>
            </select>
        </li>

        <li>
            <lable >怎么吃</lable>
            <script id="container4" name="baby" type="text/plain" ></script>
        </li>
        <hr />

        <li>
            <lable >特效与功能</lable>
            <script id="container5" name="efficacy" type="text/plain" ></script>
        </li>

        <hr />

        <li>
            <lable >选购攻略</lable>
            <script id="container6" name="buy" type="text/plain" ></script>
        </li>

        <hr/>

        <li>
            <lable >食用技巧</lable>
            <script id="container7" name="eating" type="text/plain"></script>
        </li>

        <%-- <li><lable >&nbsp;&nbsp;</lable>

         </li>--%>
    </ul>
    <div  align="middle" >
        <input id="sub" type="submit" value="确认添加" style="width: 200px">
    </div>
</form>

</fieldset>
</body>
</html>
