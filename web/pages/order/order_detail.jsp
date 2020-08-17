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
			<span class="wel_word">订单管理系统</span>
			<%-- 静态包含 manager管理模块的菜单 --%>
			<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>商品编号</td>
				<td>名称</td>
				<td>数量</td>
				<td>价格</td>
				<td>总价</td>
				<td>订单编号</td>
			</tr>
			<%-- 使用 forEach遍历 Request域中的 orderItems 订单项--%>
			<c:forEach items="${requestScope.orderItems}" var="orderItem">
				<tr>
					<td>${orderItem.id}</td>
					<td>${orderItem.name}</td>
					<td>${orderItem.count}</td>
					<td>${orderItem.price}</td>
					<td>${orderItem.totalPrice}</td>
					<td>${orderItem.orderId}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<%-- 静态包含 页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>