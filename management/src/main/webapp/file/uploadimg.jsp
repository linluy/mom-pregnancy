<%@ page import="com.wyyuand.utils.UploadImage" %><%--
  Created by IntelliJ IDEA.
  User: 沐沐
  Date: 2019/7/11
  Time: 9:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    String imgEncodedStr = request.getParameter("image");
    String fileName = request.getParameter("filename");
    System.out.println("Filename: "+ fileName);
    if(imgEncodedStr != null){
        UploadImage.convertStringtoImage(imgEncodedStr, fileName);
        System.out.print("Image upload complete, Please check your directory");
    } else{
        System.out.print("Image is empty");
    }
%>

</body>
</html>
