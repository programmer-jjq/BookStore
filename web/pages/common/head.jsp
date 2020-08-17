<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 存放公共的 base标签 CSS样式 jQery文件等--%>
<!-- base标签，永远固定相对路径跳转的结果-->
<%
    String basePath = request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";
    pageContext.setAttribute("basePath",basePath);
%>
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
