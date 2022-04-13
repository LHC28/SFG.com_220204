<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SFG.com</title>
<%-- bootstrap, jquery --%>
<link rel="stylesheet" href="/static/bootstrap-4.3.1-dist/css/bootstrap.css">
<script type="text/javascript" src="/static/jquery/jquery-3.6.0.js"></script>
<script type="text/javascript" src="/static/bootstrap-4.3.1-dist/js/bootstrap.js"></script>

<%-- jqGrid --%>
<script src="/static/jqGrid-4.7.1/js/jquery.jqGrid.js"></script>
<script src="/static/jqGrid-4.7.1/js/i18n/grid.locale-kr.js"></script>
<link rel="stylesheet" href="/static/jqGrid-4.7.1/css/ui.jqgrid.css">

<%-- moment CDN --%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.0/moment.min.js"></script>

<%-- font awesome CDN --%>
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<%-- 외부 스타일 시트 --%>
<link rel="stylesheet" href="/static/css/mainPage.css">

</head>
<body>
	<div class="container">
		<c:if test="${loginId eq 'admin' }">
		<jsp:include page="../include/adminHeader.jsp"></jsp:include>
		</c:if>
		<c:if test="${loginId ne 'admin' }">
		<jsp:include page="../include/header.jsp"></jsp:include>
		</c:if>
		<jsp:include page="../${viewName }.jsp"></jsp:include>
		<jsp:include page="../include/footer.jsp"></jsp:include>
	</div>
</body>
</html>