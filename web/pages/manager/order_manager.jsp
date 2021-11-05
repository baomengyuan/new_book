<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
		<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>
			</tr>
			<c:forEach items="${requestScope.orders}" var="Order">
				<tr>
					<td>${Order.creatTime}</td>
					<td>${Order.price}</td>
					<c:if test="${Order.status == 0}">
						<td>未发货</td>
					</c:if>
					<c:if test="${Order.status == 1}">
						<td>已发货</td>
					</c:if>
					<c:if test="${Order.status == 2}">
						<td>已签收</td>
					</c:if>
					<td><a href="orderServlet?action=showOrderDetail&orderId=${Order.orderId}&status=${Order.status}">查看详情</a></td>
				</tr>
			</c:forEach>

		</table>
	</div>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>