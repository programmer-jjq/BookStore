<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<%-- 静态包含， base标签 CSS样式 jQery文件	--%>
<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		// 页面加载完成之后
		$(function () {

			// 给表单用户名绑定失去焦点事件
			$("#username").blur(function () {
				// 1、获取用户名 username
				var username = this.value;
				// 2、调用 jQuery的 $.getJSON(URL,Paramters,callback); 方法，发送 Ajax请求
				$.getJSON("http://localhost:8080/BookStore/userServlet","action=ajaxExistsUsername&username="+username,function (data) {
					if (data.existsUsername){
						// data的 existsUsername属性值 == true,证明数据库中存在该用户，提示信息为 用户名已存在
						$("span.errorMsg").text("用户名已存在!");
					}else {
						// data的 existsUsername属性值 == false,证明数据库中不存在该用户，提示信息为 用户名可用
						$("span.errorMsg").text("用户名可用!");
					}
				});
			});

			// 给验证码的图片绑定单击事件
			$("#code_img").click(function () {
				// 验证码刷新
				// 在事件响应的function函数中有一个 this对象，是正在响应事件的 Dom对象
				// src属性表示验证码img标签的图片路径，可读可写
				// 后边加 new Date() 是为了兼容火狐和IE等其他浏览器，跳过浏览器的缓存
				this.src = "${basePath}kaptcha.jpg?d="+new Date();
			});

			// 给注册按钮绑定单击事件
			$("#sub_btn").click(function () {
				// 验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
				//1、获取用户名输入框内容
				var usernameText = $("#username").val();
				//2、创建正则表达式对象
				var usernamePatt = /^\w{5,12}$/;
				//3、使用test方法验证
				if (!usernamePatt.test(usernameText)){
					//4、提示验证结果
					// 获取 errorMsg 提示用户名不合法的错误信息
					$("span.errorMsg").text("用户名不合法!");
					return false;
				}

				// 验证密码：必须由字母，数字下划线组成，并且长度为 5 到 12 位验证
				var passwordText = $("#password").val();
				var passwordPatt = /^\w{5,12}$/;

				if (!passwordPatt.test(passwordText)){
					$("span.errorMsg").text("密码不合法!");
					return false;
				}

				//确认密码：和密码相同
				// 1、获取 确认密码内容
				var repwdText = $("#repwd").val();
				// 2、与密码相比较
				if (repwdText != passwordText){
					// 3、提示用戶
					$("span.errorMsg").text("确认密码和密码不一致！");
					return false;
				}

				// 邮箱验证：xxxxx@xxx.com
				var emailText = $("#email").val();
				var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
				if (!emailPatt.test(emailText)){
					// 3、提示用戶
					$("span.errorMsg").text("邮箱不合法!");
					return false;
				}
				// 验证码：现在只需要验证用户已输入。因为还没讲到服务器的验证码生成。
				var codeText = $("#code").val();

				// 防止验证码空格的恶意输入，去掉验证码前后的空格
				// alert("去空格前:["+codeText+"]");
				codeText = $.trim(codeText);
				// alert("去空格后:["+codeText+"]");

				if (codeText == null || codeText ==""){
					$("span.errorMsg").text("验证码不能为空!");
					return false;
				}
					// 获取 errorMsg 合法时，错误内容清空
					$("span.errorMsg").text("");
			});
		});

	</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
			<div class="login_banner">
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">
									<%-- 从 Request域中取出错误信息 msg
										当显示信息为 Null时,还没有传回错误信息,显示空串
										否则显示 错误信息 msg
										--%>
								<%--	<%= request.getAttribute("msg")==null?" ":request.getAttribute("msg")%>  --%>
								<%--     使用 EL表达式替换表达式脚本    --%>
										${requestScope.msg}
								</span>
							</div>
							<div class="form">
								<!-- 由于已经配置了 base标签,直接到 BookStore/registServlet -->
								<form action="userServlet" method="post">
									<%-- 创建一个隐藏表单，传递 action参数值为 regist--%>
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username" id="username"
										   value="${ requestScope.username }"
									/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   autocomplete="off" tabindex="1" name="email" id="email"
										   value="${ requestScope.email }"
									/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 80px;" id="code" name="code"/>
									<img id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px;width: 150px;height: 40px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
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