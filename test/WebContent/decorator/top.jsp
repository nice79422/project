 <%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <img id="logo" src="${pageContext.request.contextPath}/img/Mark.png"
	style="width: 950px; height: auto" /> --%>
<body>
<script>
$(document).ready(function(){
	$("#logOutBtn").click(function(){
		location.href="${pageContext.request.contextPath}/base/login.do?method=logout"
	});
	$("#log").css({"text-align" : "right" });
});
</script>

<div></div>
<span class="title" onclick="location.href='${pageContext.request.contextPath}/emp/login.html';" >SY<font color="#19848a">석재</font></span>
<div id="log">
<c:if test="${sessionScope.name!=null}">
${sessionScope.name}님 환영합니다.
<input type="button" value="LOGOUT" id="logOutBtn">
</c:if>
</div>



