<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员登录页面</title>
	<%@include file="/pages/common/head.jsp"%>
	<style>
		.login_form {
			height: 310px;
		}
	</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>

			<div class="login_banner">
				<div id="l_content">
					<span class="login_word">欢迎登录</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>尚硅谷会员</h1>
								<a href="pages/user/regist.jsp">立即注册</a>
							</div>
							<div class="msg_cont">
								<b></b>
								<span class="errorMsg">
									<%-- 从 Request域中取出错误信息 msg
										当显示信息为 Null时,还没有传回错误信息,显示 请输入用户名和密码
										否则显示 错误信息 msg
										--%>
							<%--    <%= request.getAttribute("msg")==null?"请输入用户名和密码":request.getAttribute("msg")%>  --%>
									<%--  使用 EL表达式替换表达式脚本，输出错误信息 --%>
									${ empty requestScope.msg ? "请输入用户名和密码":requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<%-- 创建一个隐藏表单，传递 action参数值为 login--%>
									<input type="hidden" name="action" value="login">
									<label>用户名称：</label>
									<%-- 从 Request域中取出回显的信息 username
										 当显示信息为 Null时,还没有回显信息,显示空串
										否则显示 回显信息 username
									--%>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username"
											<%-- 使用 EL表达式输出回显信息	   --%>
										   value="${requestScope.username}"
   									 />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<input type="submit" value="登录" id="sub_btn" />
								</form>
								</div>

								</div>
								</div>
								</div>
								</div>
								<%-- 静态包含 页脚内容--%>
									<%@include file="/pages/common/footer.jsp"%>
</body>
</html>