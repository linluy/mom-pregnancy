<%--
  Created by IntelliJ IDEA.
  User: 沐沐
  Date: 2019/7/16
  Time: 10:47
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
            window.location.href = "${pageContext.request.contextPath}/prestatus/listByPage?pageNumStr="+pageNum;
            })
        })
    </script>

</head>
<body>

<div style="float: left;text-align: center;margin-left: 50px;margin-top: 50px">
    <span style="font-size: 40px">孕周管理</span>
    <%--<input type="button" value="添加" onclick="toAdd()" style="margin-left: 30px;background:#9CA274;color: white;width: 70px;font-size: 20px"/>--%>
</div>


<div  class="demoTable"  style="height: 20px;float:right;margin-top: 120px;margin-right: 100px">

    <form action="${pageContext.request.contextPath}/prestatus/queryByKeyWord" method="post">
        <div class="layui-inline" style="float: left">
            周期： <input class="layui-input" name="keyword" id="keyword">
        </div>
        <button class="layui-btn layui-btn-sm">搜索</button>
        <a href="prestatus/listByPage">
            <button>取消</button>
        </a>
    </form>
</div>
<div  style="float:left;padding-top: 80px; padding-left:50px;width: 100%;">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <c:choose>
        <c:when test="${pageNum==1}" >
            上一页
        </c:when>
        <c:otherwise>
            <a href="${pageContext.request.contextPath}/prestatus/listByPage?pageNumStr=${pageNum-1}" class="pagea"  style="color: black">上一页</a>
        </c:otherwise>
    </c:choose>
    &nbsp;&nbsp;
    <c:choose>
        <c:when test="${pageNum==pageCount}" >
            下一页
        </c:when>

        <c:otherwise>
            <a href="${pageContext.request.contextPath}/prestatus/listByPage?pageNumStr=${pageNum+1}" class="pagea"  style="color: black">下一页</a>
        </c:otherwise>
    </c:choose>
    当前第${pageNum}页，一共${pageCount}页，共${rowCount}条 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跳转到第【<input type="text" name="" id="pageNumInp" value="${pageNum}" size="3">】页
    <input type="button" id="toPageBtn" value="跳转">
</div>

<div style="float:left; width: 100%;height: 100%;margin-top: 10px;" >

    <input type="hidden" value="${pageCount}" id="pageCount" name="pageCount">

    <table width="100%" border="1"  cellspacing="0" cellpadding="0">
        <tr class="tabTil">
            <td>序号</td>
            <td>图片</td>
            <td>id</td>
            <td>周期</td>
            <td>体重</td>
            <td>身长</td>
            <td>宝宝</td>
            <td>妈妈</td>
            <td>操作</td>
        </tr>

        <c:forEach items="${prestatuses}" var="prestatuse" varStatus="stats">
            <tr>
                <td width="5%">${stats.count}</td>
                <td width="10%">
                    <img src="${pageContext.request.contextPath}/prestatus/download?id=${prestatuse.id}" width="80dp" height="50dp"/>
                </td>
                <td width="5%">${prestatuse.id}</td>
                <td width="10%">第${prestatuse.period}周</td>
                <td width="8%">${prestatuse.weight}</td>

                <%--<td width="50%">${knowledge.content}</td>--%>

                <td width="8%">${prestatuse.len}</td>
                <td width="20%">${prestatuse.babySay}</td>
                <td width="20%">${prestatuse.momSay}</td>

                <td width="10%">

                    <a href="${pageContext.request.contextPath}/prestatus/toEdit?id=${prestatuse.id}" >
                        <BUTTON style="background: #d98c1d;color: black">编辑</BUTTON>
                    </a>

                </td>

            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
