<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		// 相当于 页面加载完成
		$(function () {

			// 给删除的a标签绑定单击事件，用于删除的确认提示操作
			$("a.deleteClass").click(function () {

				// 在事件的 function函数中，有一个 this对象，是当前正在响应事件的dom对象
				/*
                * confirm 是确认提示框函数
                * 参数是它的提示内容
                * 两个按钮，一个确认，一个取消
                * 	返回 true，表示点击确认 返回 false,表示点击取消
                */
				return confirm("您确定要删除【"+ $(this).parent().parent().find("td:first").text() +"】?");

				// return false; // 阻止元素的默认行为 == 不提交请求
			});
		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
			<%-- 静态包含 manager管理模块的菜单 --%>
			<%@include file="/pages/common/manager_menu.jsp"%>
	</div>

	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<%-- 使用 JSTL标签库的 forEach标签循环遍历 --%>
			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<td><a class="deleteClass" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>
		</table>
		<%-- 静态包含分页条 --%>
		<%@include file="/pages/common/page_nav.jsp"%>
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>