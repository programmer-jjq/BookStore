<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			// 给【删除】绑定单击响应事件
			$(".deletItem").click(function () {
				return confirm("您确定要删除【" + $(this).parent().parent().find("td:first").text() +"】吗?");
			});

			// 给【清空购物车】绑定单击响应事件
			$("#clearCart").click(function () {
				return confirm("您确定要清空购物车吗?");
			});

			// 给输入框绑定onchange内容发生改变事件
			$(".updateCount").change(function () {
				// 获取商品名称
				let name = $(this).parent().parent().find("td:first").text();
				// 获取商品数量
				var count = this.value;
				// 获取商品Id
				var bookId = $(this).attr("bookId");
				if (confirm("您确定要将【"+ name +"】商品修改数量为："+count+"吗？")){
					// 发起请求，给服务器保存数量
					location.href = "http://localhost:8080/BookStore/cartServlet?action=updateCount&count="+count+"&id="+bookId;
				} else{
					// defaultValue属性是表单项Dom对象的属性，表示默认的value值
					this.value = this.defaultValue;
				}
			});

		});
	</script>
</head>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<%-- 对 Session域中的 cart购物车是否为空判断--%>
			<c:if test="${empty sessionScope.cart.items}">
				<%-- 购物车为空，显示提示信息,不显示 cart_info信息--%>
				<tr>
					<td colspan="5"><a href="index.jsp">亲，当前购物车为空!快和小伙伴们去浏览商品吧!!</a></td>
				</tr>
			</c:if>
			<c:if test="${not empty sessionScope.cart.items}">
				<%-- 购物车不为空，循环显示购物车信息--%>
				<c:forEach items="${sessionScope.cart.items}" var="entry">
					<%-- items是 Map集合，entry是集合中每个键值对，entry.value就是每个 CartItem商品项对象--%>
					<tr>
						<td>${entry.value.name}</td>
						<td>
							<input class="updateCount" style="width: 80px"
								   bookId="${entry.value.id}" type="text" value="${entry.value.count}">
						</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a class="deletItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<%-- 购物车不为空，显示 cart_info信息--%>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="client/orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>
	</div>
	<%-- 静态包含 页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>