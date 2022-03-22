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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<%-- datatable CDN --%>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.23/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css" />

<%-- datatable library --%>
<link rel="stylesheet" type="text/css" href="/static/css/datatableEditor/editor.bootstrap.css">
 
<script type="text/javascript" src="/static/js/datatableEditor/dataTables.editor.js"></script>
<script type="text/javascript" src="/static/js/datatableEditor/editor.bootstrap.js"></script>

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