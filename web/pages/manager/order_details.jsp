<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <%@include file="/pages/common/head.jsp"%>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">订单详情</span>
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
        <c:forEach items="${requestScope.orderItems}" var="orderItem">
            <tr>
                <td>${orderItem.name}</td>
                <td>${orderItem.count}</td>
                <td>${orderItem.price}</td>
                <td>${orderItem.totalPrice}</td>
            </tr>
        </c:forEach>
    </table>

    <div class="details_info">
        <c:if test="${requestScope.status==0}">
            <span><a href="orderServlet?action=sendOrder&orderId=${requestScope.orderId}">订单号为：${requestScope.orderId},立即发货</a></span>
        </c:if>
        <c:if test="${requestScope.status==1}">
            <span><a href="orderServlet?action=showAllOrders&status=1">此订单已经发货，点此返回</a></span>
        </c:if>
        <c:if test="${requestScope.status==2}">
            <span><a href="orderServlet?action=showAllOrders&status=1">此订单已经被用户签收，点此返回</a></span>
        </c:if>
    </div>
</div>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>