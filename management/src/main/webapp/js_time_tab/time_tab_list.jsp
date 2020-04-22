<%@ page import="com.wyyuand.domain.MusicChun" %><%--
  Created by IntelliJ IDEA.
  User: 沐沐
  Date: 2019/6/11
  Time: 9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js "></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/asserts/bootstrap-3.3.7/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/asserts/bootstrap-3.3.7/js/bootstrap.js"></script>

    <style>

        table tr td{
            /*border:none;*/
            margin-top: 10px;
            padding-top: 10px;
            padding-bottom: 10px;
            margin-left: 20px;
            margin-right: 20px;
            height: 50px;
            text-align: center;
        }
        .tabTil td{
            height: 10px;
            background:#9CA274;
        }
    </style>

    <script>
        $(function(){
            $("#toPageBtn").click(function () {
                var pageNum = $("#pageNumInp").val();
                // alert(pageNum);
                if(pageNum<=0 || pageNum>parseInt($("#pageCount").val())){
                    alert("没有"+pageNum+"页");
                    return;
                }
                window.location.href = "${pageContext.request.contextPath}/pretime/listByPage?pageNumStr="+pageNum;
            })
        })
    </script>

</head>
<body>


<div style="float:left; width: 100%;height: 100px;margin-top: 30px" >
    <span style="margin-left: 150px;font-size: 50px;">产检时间表</span>
</div>

<div  class="demoTable"  style="height: 20px;float:right;margin-top: 50px;margin-right: 100px">

    <form action="${pageContext.request.contextPath}/pretime/queryByKeyWord" method="post">
        <div class="layui-inline" style="float: left">
            第几次产检： <input class="layui-input" name="keyword" id="keyword">
        </div>
        <button class="layui-btn layui-btn-sm">搜索</button>
        <a href="pretime/listByPage">
            <button>取消</button>
        </a>
    </form>
</div>

<div style="float:left;background: lightgray;height: 30px;width: 100%;margin-top: 10px;"></div>


<div  style="float:left;padding-top: 80px; padding-left:50px;width: 100%;">

    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <c:choose>
        <c:when test="${pageNum==1}" >
            上一页
        </c:when>
        <c:otherwise>
            <a href="${pageContext.request.contextPath}/pretime/listByPage?pageNumStr=${pageNum-1}" class="pagea"  style="color: black">上一页</a>
        </c:otherwise>
    </c:choose>
    &nbsp;&nbsp;
    <c:choose>
        <c:when test="${pageNum==pageCount}" >
            下一页
        </c:when>

        <c:otherwise>
            <a href="${pageContext.request.contextPath}/pretime/listByPage?pageNumStr=${pageNum+1}" class="pagea"  style="color: black">下一页</a>
        </c:otherwise>
    </c:choose>
    当前第${pageNum}页，一共${pageCount}页，共${rowCount}条 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跳转到第【<input type="text" name="" id="pageNumInp" value="${pageNum}" size="3">】页
    <input type="button" id="toPageBtn" value="跳转">
</div>

<div style="float:left; width: 100%;height: 100%;margin-top: 10px;">

    <input type="hidden" value="${pageCount}" id="pageCount" name="pageCount">

    <table border="1"  cellspacing="0" cellpadding="0" id="tbl" style="margin-top: 50px">
    <tr class="tabTil" >
        <td>序号</td>
        <td>怀孕周期</td>
        <td>第几次检查</td>
        <%--<td>操作</td>--%>
        <td>产检项目</td>
        <%--<td>温馨提示</td>--%>
        <td>状态</td>
        <td>操作</td>
    </tr>


    <c:forEach items="${timeTabs}"  var="timeTab" varStatus="stats">

        <tr id="data_${stats.count}">
            <td width="8%">${stats.count}</td>
            <td width="6%">第${timeTab.checkPre}周</td>
            <td width="10%">第${timeTab.checkSecond}次</td>
            <td width="50%">${timeTab.checkUsu}</td>
            <%--<td width="25%">${timeTab.checkEsp}</td>--%>
            <td width="8%">${timeTab.status}</td>
                <%--删除图标--%>
            <td width="10%">

                    <%--修改--%>
                <a href="${pageContext.request.contextPath}/pretime/toEdit?id=${timeTab.checkId}"  class="upd" style="color: black">
                    <BUTTON style="background: #d98c1d">编辑</BUTTON>
                </a>

            </td>
        </tr>

    </c:forEach>

</table>
</div>
</body>
</html>
