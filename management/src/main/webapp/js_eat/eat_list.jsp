<%@ page import="com.wyyuand.domain.Food" %>
<%@ page import="com.wyyuand.domain.Food" %><%--
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
    <title>能不能吃</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js "></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/asserts/bootstrap-3.3.7/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/asserts/bootstrap-3.3.7/js/bootstrap.js"></script>
<%--ajax删除--%>
    <style>


        .pagea{
            color: black;
        }

        table tr td{
            /*border:none;*/
            margin-top: 20px;
            padding-top: 30px;
            padding-bottom: 30px;
            margin-left: 20px;
            margin-right: 20px;
            height: 50px;
            text-align: center;
        }
        #tabTil td{
            height: 10px;
            background:#9CA274;
        }

    </style>
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
            $('.del').click(function () {
            // var sure = confirm(确认删除？)
                var id = $(this).attr('idVal');


                if(confirm("你确定要删除"+id+"的信息吗？")){
                    $.ajax('${pageContext.request.contextPath}/food/ajaxDel?id='+id,{
                        type:'POST',
                        success:function (data) {
                            if(data && data.success) {
                                alert('删除成功');
                                window.location.href='${pageContext.request.contextPath}/food/listByPage?type=${type}';
                            }else{
                                alert('删除失败');
                            }
                        }
                    })
                }
             });

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
                            $.ajax('${pageContext.request.contextPath}/food/deleteAll',{
                                type:'POST',
                                data:{
                                    ids:ids
                                },
                                dataType:"json",
                                success:function (data) {
                                    if(data && data.success) {
                                        alert('删除成功');
                                        window.location.href='${pageContext.request.contextPath}/food/listByPage?type=${type}';
                                    }else{
                                        alert('删除失败');
                                    }
                                }
                            })
                        }

                    } else{
                                confirm("请选择要删除的用户")
                     }
                //将获取的值存入数组
          })
        })
        $(function(){
            $("#toPageBtn").click(function () {
                var pageNum = $("#pageNumInp").val();
                // alert(pageNum);
                if(pageNum<=0 || pageNum>parseInt($("#pageCount").val())){
                    alert("没有"+pageNum+"页");
                    return;
                }
                window.location.href = "${pageContext.request.contextPath}/food/listByPage?type=${type}&pageNumStr="+pageNum;
            })

        })
        <%-- 添加   ${pageContext.request.contextPath}--%>
        function toAdd() {
            // alert("跳转到管理添加页.....");
            window.location.href = "${pageContext.request.contextPath}/js_eat/eat_add.jsp";
        }
    </script>

</head>

<body>




    <div style="float:left; width: 100%;height: 100px;margin-top: 30px" >
        <span style="margin-left: 150px;font-size: 50px;">${foodName}</span>
        <img src="${pageContext.request.contextPath}/asserts/images/add.png" onclick="toAdd()" style="margin-left: 20px">
    </div>


    <div  class="demoTable"  style="height: 20px;float:right;margin-top: 50px;margin-right: 100px">

        <form action="${pageContext.request.contextPath}/food/queryByKeyWord?type=${type}" method="post">
            <div class="layui-inline" style="float: left">
                食物名称： <input class="layui-input" name="keyword" id="keyword">
            </div>
            <button class="layui-btn layui-btn-sm">搜索</button>
            <a href="food/listByPage?type=${type}">
                <button>取消</button>
            </a>
        </form>
    </div>

    <div style="float:left;background: lightgray;height: 30px;width: 100%;margin-top: 30px"></div>

    <div  style="float:left;margin-left: 120px;padding-top: 80px">
        <input type="button" name="delete" id="delete" value="批量删除"/>
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <c:choose>
                    <c:when test="${pageNum==1}" >
                       上一页
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/food/listByPage?type=${type}&pageNumStr=${pageNum-1}" class="pagea"  style="color: black">上一页</a>
                    </c:otherwise>
                </c:choose>

                &nbsp;&nbsp;
                <c:choose>
                    <c:when test="${pageNum==pageCount}" >
                        下一页
                    </c:when>

                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/food/listByPage?type=${type}&pageNumStr=${pageNum+1}" class="pagea"  style="color: black">下一页</a>
                    </c:otherwise>
                </c:choose>

                当前第${pageNum}页，一共${pageCount}页，共${rowCount}条 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跳转到第【<input type="text" name="" id="pageNumInp" value="${pageNum}" size="3">】页
                <input type="button" id="toPageBtn" value="跳转">
    </div>

    <div style="float:left; width: 100%;height: 100%;margin-top: 10px;" >

            <input type="hidden" value="${pageCount}" id="pageCount" name="pageCount">

            <table rules = rows id="tbl" >
                <tr id="tabTil">
                    <td><input type="checkbox" id="ckAll" />全选/反选</td>
                    <td>序号</td>
                    <td>图片</td>
                    <td>名称</td>
                    <td>id</td>
                    <td>类型</td>
                    <td>分类</td>
                    <td>更新时间</td>
                    <td>状态</td>
                    <td>操作</td>
                </tr>

                    <c:forEach items="${foodslList}"  var="food" varStatus="stats">

                        <tr id="data_${stats.count}" class="tr_value">
                        <td width="10%"><input type="checkbox" name="ckSingle" class="ckSingle"  value="${food.id}"/></td>

                         <td width="5%">${stats.count}</td>

                                <td width="10%" >
                                <%
                                Food food = (Food) pageContext.getAttribute("food");
//                                String images = food.getPicture();
                                session.setAttribute("type",food.getType());
                                %>
                                   <%--<img src="${pageContext.request.contextPath}/food/download?fileName=<%=URLEncoder.encode(images,"UTF-8")%>" style="width: 300px;height: 160px"/>--%>
                                   <img src="${pageContext.request.contextPath}/food/download?id=${food.id}" style="width: 90px;height: 40px"/>
                                </td>

                         <td width="10%">${food.variety}</td>
                         <td width="5%">${food.id}</td>
                         <td width="5%">${food.type}</td>
                         <td width="20%">${food.alias}</td>
                         <td width="10%"><fmt:formatDate value="${food.updatetime}"/></td>
                         <td width="5%">${food.status}</td>
                                <%--删除图标--%>
                            <td width="10%">

                                <a href="${pageContext.request.contextPath}/food/toEdit?id=${food.id}"  class="upd" style="color: black">
                                    <%--<img  class="myImg" src="${pageContext.request.contextPath}/asserts/images/edit.png" />--%>
                                    <BUTTON style="background: #d98c1d">编辑</BUTTON>
                                </a>

                                &nbsp;&nbsp;
                                    <%--onclick="readyDel(${food.id})"--%>
                                <a  href="javascript:void(0)" class="del"  name="mydel"   idVal="${food.id}" style="color: black">
                                    <%--<img  class="myImg" src="${pageContext.request.contextPath}/asserts/images/del.png"/>&nbsp;&nbsp;--%>
                                    <BUTTON style="background:#b8ceda;">删除</BUTTON>
                                </a>
                            </td>
                    </tr>

                    </c:forEach>

            </table>

    </div>
</body>

</html>
