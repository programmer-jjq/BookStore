<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 分页条的开始	--%>
<div id="page_nav">
<%-- 当前页码 pageNo >1 时，显示 上一页的超链接--%>
<c:if test="${requestScope.page.pageNo > 1}">
    <a href="${ requestScope.page.url }&pageNo=1">首页</a>
    <a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageNo-1}">上一页</a>
</c:if>

<%-- 页码输出的开始 --%>
<c:choose>
    <%--情况 1：如果总页码小于等于 5 的情况，页码的范围是：1-总页码--%>
    <c:when test="${requestScope.page.pageTotal <= 5 }">
        <c:set var="begin" value="1"/>
        <c:set var="end" value="${requestScope.page.pageTotal}"/>
    </c:when>
    <%--情况 2 ：总页码大于5 的情况。假设一共10页--%>
    <c:when test="${requestScope.page.pageTotal > 5}">
        <c:choose>
            <%-- 小情况 1：当前页码为前面 3 个：1，2，3 的情况，页码范围是：1-5 --%>
            <c:when test="${requestScope.page.pageNo <= 3}">
                <c:set var="begin" value="1"/>
                <c:set var="end" value="5"/>
            </c:when>
            <%-- 小情况 2 ：当前页码为最后三个，8，9，10，页码范围：总页码减4 - 总页码--%>
            <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
                <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                <c:set var="end" value="${requestScope.page.pageTotal}"/>
            </c:when>
            <%-- 小情况 3 ：4，5，6，7，页码范围是：当前页码-2   -   当前页码+2 --%>
            <c:otherwise>
                <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                <c:set var="end" value="${requestScope.page.pageNo+2}"/>
            </c:otherwise>
        </c:choose>
    </c:when>
</c:choose>
<%-- 可以将共同的 forEach标签提取出来进行优化 --%>
<c:forEach begin="${begin}" end="${end}" var="i">
    <c:if test="${i == requestScope.page.pageNo}">
        【${i}】
    </c:if>
    <c:if test="${i != requestScope.page.pageNo}">
        <a href="${ requestScope.page.url }&pageNo=${i}">${i}</a>
    </c:if>
</c:forEach>
<%-- 页码输出的结束 --%>

<%-- 当前页码 pageNo < pageTotal 时，显示下一页和末页的超链接 --%>
<c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal }">
    <a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageNo+1}">下一页</a>
    <a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageTotal}">末页</a>
</c:if>
共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalcount}条记录
到第<input name="pn" id="pn_input" value="${param.pageNo}"/>页
<input id="searchPageBtn" type="button" value="确定">
    <script type="text/javascript">
        // 跳到指定的页码
        $(function () {
            // 为 searchPageBtn 按钮绑定单击事件
            $("#searchPageBtn").click(function () {
                var pageNo = $("#pn_input").val();
                // JavaScript提供了一个 location地址栏对象的 href属性可读可写
                location.href= "${pageScope.basePath}${ requestScope.page.url }&pageNo="+pageNo;
            });
        });
    </script>
</div>
<%-- 分页条的结束	--%>
