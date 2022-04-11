<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex">
<%-- 관리자 페이지 nav --%>
	<jsp:include page="../include/adminNav.jsp"></jsp:include>
	<%-- table 라이브러리 넣었던 곳... --%>
	<section class="adminSection p-3">
		<div class="tableWrap">
			<table id="mainGrid"></table>
			<div id="pager"></div>		
		</div>
	</section>
</div>
<script>
var searchResultColNames = ['게시글관리번호', '번호', '제목', '작성자', '날짜', '조회수'];
ar searchResultColModel = [ 
	{name:'bbsMgtNo', index:'bbsMgtNo', align:'center', hidden:true},
	{name:'bbsNum', index:'bbsNum', align:'left', width:'12%'},
	{name:'bbsTitle', index:'bbsTitle', align:'center', width:'50%'},
	{name:'bbsWriter', index:'bbsWriter', align:'center', width:'14%'},
	{name:'bbsDate', index:'bbsDate', align:'center', width:'12%'},
	{name:'bbsHit', index:'bbsHit', align:'center', width:'12%'}
	];


$(document).ready(function(){
	$("#mainGrid").jqGrid({
	height: 261,
	width: 1019,
	colNames: searchResultColNames,
	colModel: searchResultColModel,
	rowNum : 10,
	pager: "pager"
	});
	
	
	function searchData(mode) {
		var postData = objConvertJson($("#fieldSurvForm")); //form 데이터 json으로 변경
		$("#mainGrid").jqGrid({
			url : "/user/get_all_user", // ajax처럼 데이터를 주고받을 서버 url주소
			datatype : "JSON", // 데이터의 타입. ajax처럼 사용하면 됨.
			postData : postData, // 넘겨줄 데이터
			mtype : "POST", // post or get
			colNames: searchResultColNames, // 그리드 헤더의 제목 배열
			colModel: searchResultColModel, // 그리드 행에 보여줄 데이터로 데이터 컬럼과 매칭시켜주어야 한다.
			rowNum : 10, // 보여줄 행의 갯수
			pager: "#pager", // 페이징을 하기 위해 선언하며 거의 필수
			height: 261, // 높이
			width: 1019, // 너비
			caption : "게시글 목록" // 타이틀, 제목
			});
		}

});
</script>