<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>decorator.jsp</title>

<jsp:include page="head.jsp" flush = "false" />
<decorator:head />
<jsp:include page="top.jsp" />
</head>
<body>
	<center>
		<table class="mainTable">
			<tr>
				<td colspan = "4"><jsp:include page="menu.jsp" /></td>
			</tr>
			<tr>
				<td colspan = "3" width = "1100px" height = "500px"><decorator:body /></td>
			</tr>
			<tr>
				<td colspan = "4"><jsp:include page = "bottom.jsp" /></td>
			</tr>
		</table>
	</center>
</body>
</html>






