<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2021-10-29
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>我的订单详情</title>
  <%@include file="/pages/common/head.jsp"%>
</head>
<body>

<div id="header">
  <img class="logo_img" alt="" src="static/img/logo.gif" >
  <span class="wel_word">我的订单详情</span>
  <%@include file="/pages/common/manager_menu.jsp"%>
</div>

<div id="main">
  <table>
    <tr>
      <td>书名</td>
      <td>数量</td>
      <td>单价</td>
      <td>总价</td>
    </tr>
    <c:forEach items="${requestScope.MyOrderItems}" var="orderItem">
      <tr>
        <td>${orderItem.name}</td>
        <td>${orderItem.count}</td>
        <td>${orderItem.price}</td>
        <td>${orderItem.totalPrice}</td>
      </tr>
    </c:forEach>
  </table>
  <div class="details_info">
    <c:if test="${requestScope.MyStatus==0}">
      <span><a href="orderServlet?action=showMyOrders">您的订单还未发出请耐心等待！</a></span>
    </c:if>
    <c:if test="${requestScope.MyStatus==1}">
      <span><a href="orderServlet?action=receiveOrder&orderId=${requestScope.MyOrderId}">点此签收订单！！！</a></span>
    </c:if>
    <c:if test="${requestScope.MyStatus==2}">
      <span><a href="orderServlet?action=showMyOrders">您已经签收订单！！！</a></span>
    </c:if>
  </div>
</div>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
