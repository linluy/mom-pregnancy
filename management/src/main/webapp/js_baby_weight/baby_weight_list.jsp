<%@ page import="java.net.URLEncoder" %>
<%@ page import="com.wyyuand.domain.Knowledge" %><%--
  Created by IntelliJ IDEA.
  User: 沐沐
  Date: 2019/6/10
  Time: 16:43
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
    <%--<script src="${pageContext.request.contextPath}/js/jquery.min.1.10.1.js"></script>--%>
    <%--ajax删除--%>
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
            $('.del').click(function () {
                //ajax删除
                var id = $(this).attr('idVal');
                // alert(id);
                if(confirm("你确定要删除"+id+"的信息吗？")){
                    $.ajax('${pageContext.request.contextPath}/babyWeight/ajaxDel?id='+id,{
                        type:'POST',
                        success:function (data) {
                            if(data && data.success) {
                                alert('删除成功');
                                window.location.href='${pageContext.request.contextPath}/babyWeight/listByPage';
                            }else{
                                alert('删除失败');
                            }
                        }
                    })
                }

            })
        })
    </script>
    <script>
        $(document).ready(function() {

            $("input[name='ckSingle']").click(function () {

                if(!this.checked){

                    $("#ckAll").prop('checked', false);
                }
            })
            $("tr").mousemove(function(){
                $(this).css("background","#F0F0F0");  //鼠标经过背景颜色变为灰色

            })

            $("tr").mouseout(function(){
                $(this).css("background","#fff");  //离开后背景颜色回复白色
            })

            <%--${pageContext.request.contextPath}--%>
            //删除
        });
        $(function(){
            /** 获取所有的单选框 */
            var boxs  = $("input[type='checkbox'][id^='box_']");
            $("#ckAll").click(function () {

                if (this.checked) {
                    $("input[name='ckSingle']").prop('checked', true);

                } else {
                    $("input[name='ckSingle']").prop('checked', false);
                }
            })

            <%--${pageContext.request.contextPath}--%>

            /** 删除绑定点击事件 */
            $("#delete").click(function(){
                var ids = "";
                $("input[name='ckSingle']:checked").each(function(){
                    ids += ","+$(this).val();
                });
                ids = ids.substring(1);
                if(ids!=""){
                    if(confirm("你确定要删除"+ids+"的信息吗？")) {

                        //执行ajax删除
                        $.ajax('${pageContext.request.contextPath}/babyWeight/deleteAll',{
                            type:'POST',
                            data:{
                                ids:ids
                            },
                            dataType:"json",
                            success:function (data) {
                                if(data && data.success) {
                                    alert('删除成功');
                                    window.location.href='${pageContext.request.contextPath}/babyWeight/listByPage';
                                }else{
                                    alert('删除失败');
                                }
                            }
                        })
                    }
                } else{
                    confirm("请选择要删除的知识")
                }
            })
        })
    </script>

    <script>
        function toAdd()
        {
            alert("跳转到管理添加页.....");
            window.location.href = "${pageContext.request.contextPath}/js_knowledge/knowledge_add.jsp";
        }
    </script>
    <%--页数--%>
    <script>
        $(function(){
            $("#toPageBtn").click(function () {
                var pageNum = $("#pageNumInp").val();
                // alert(pageNum);
                if(pageNum<=0 || pageNum>parseInt($("#pageCount").val())){
                    alert("没有"+pageNum+"页");
                    return;
                }
                window.location.href = "${pageContext.request.contextPath}/babyWeight/listByPage?pageNumStr="+pageNum;
            })
        })
    </script>
</head>

<body>
<%--<img src="d:\\upload\\img1.jpg">--%>

    <div style="float: left;text-align: center;margin-left: 50px;margin-top: 50px">
        <span style="font-size: 40px">宝宝体重</span>
        <%--<input type="button" value="添加" onclick="toAdd()" style="margin-left: 30px;background:#9CA274;color: white;width: 70px;font-size: 20px"/>--%>
    </div>

<div  class="demoTable"  style="height: 20px;float:right;margin-top: 120px;margin-right: 100px">

    <form action="${pageContext.request.contextPath}/babyWeight/queryByKeyWord" method="post">
        <div class="layui-inline" style="float: left">
            用户ID： <input class="layui-input" name="keyword" id="keyword">
        </div>
        <button class="layui-btn layui-btn-sm">搜索</button>
        <a href="babyWeight/listByPage">
            <button>取消</button>
        </a>
    </form>
</div>




    <div  style="float:left;padding-top: 80px; padding-left:50px;width: 100%;">

        <input type="button" name="delete" id="delete" value="批量删除" style="margin-left: 30px;background:#b8ceda;height: 40px"/>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <c:choose>
            <c:when test="${pageNum==1}" >
                上一页
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/babyWeight/listByPage?pageNumStr=${pageNum-1}" class="pagea"  style="color: black">上一页</a>
            </c:otherwise>
        </c:choose>
        &nbsp;&nbsp;
        <c:choose>
            <c:when test="${pageNum==pageCount}" >
                下一页
            </c:when>

            <c:otherwise>
                <a href="${pageContext.request.contextPath}/babyWeight/listByPage?pageNumStr=${pageNum+1}" class="pagea"  style="color: black">下一页</a>
            </c:otherwise>
        </c:choose>
            当前第${pageNum}页，一共${pageCount}页，共${rowCount}条 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跳转到第【<input type="text" name="" id="pageNumInp" value="${pageNum}" size="3">】页
            <input type="button" id="toPageBtn" value="跳转">
        </div>

    <div style="float:left; width: 100%;height: 100%;margin-top: 10px;" >
        <input type="hidden" value="${pageCount}" id="pageCount" name="pageCount">
        <table width="100%" border="1"  cellspacing="0" cellpadding="0">

        <tr class="tabTil">
            <td><input type="checkbox" id="ckAll" />全选/反选</td>
            <td>序号</td>
            <td>估重ID</td>
            <td>估重日期</td>
            <td>用户ID</td>
            <td>双径顶（BDP）</td>
            <td>腹围（AC）</td>
            <td>股骨长（FL）</td>
            <td>估重</td>
            <td>操作</td>
        </tr>
<%--
  `weight_id`, `user_id`,  `weight_data`, `bdp`, `ac`, `fl`, `weight_value`
--%>
        <c:forEach items="${babyWeightList}" var="babyWeight" varStatus="stats">
            <tr>
                <td width="10%"><input type="checkbox" name="ckSingle" class="ckSingle"  value="${babyWeight.weightId}"/></td>

                <td width="5%">${stats.count}</td>
                <td width="8%">${babyWeight.weightId}</td>

                <td width="8%"><fmt:formatDate value="${babyWeight.weightData}"/></td>

                <td width="8%">${babyWeight.userId}</td>

                <td width="8%">${babyWeight.bdp}</td>
                <td width="5%">${babyWeight.ac}</td>
                <td width="8%">${babyWeight.fl}</td>
                <td width="8%">${babyWeight.weightValue}</td>

                <td width="10%">
                    <a href="javascript:void(0)" class="del" idVal="${babyWeight.weightId}">
                       <BUTTON style="background:#b8ceda;">删除</BUTTON>
                    </a>
                </td>
            </tr>

        </c:forEach>
    </table>
    </div>

</body>

</html>
