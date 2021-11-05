<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2021-10-28
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>500页面</title>
    <%@include file="/pages/common/head.jsp"%>
    <style type="text/css">
        h1{
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">500错误</span>
    <%--静态包含，登录成功之后的菜单--%>
    <%@ include file="/pages/common/login_success_menu.jsp"%>
</div>

<div id="main">
    <h1><a href="index.jsp">网页出错，程序员正在紧急为您抢修！！！</a></h1>

</div>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
