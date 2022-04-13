<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex">
<%-- 관리자 페이지 nav --%>
	<jsp:include page="../include/adminNav.jsp"></jsp:include>
	<%-- table 라이브러리 넣었던 곳... --%>
	<section class="adminSection p-3">
		<div class="tableWrap d-flex justify-content-center">
			<div>
				<table id="jqGrid"></table>
				<div id="jqGridPager"></div>
			</div>
		</div>
	</section>
</div>
<script>

$(document).ready(function(){
	$('#jqGrid').jqGrid({
		url: "/user/get_all_user"
		,datatype: "json"
		,colNames: ['id','아이디','이름','이메일','생성일']
		,colModel: [
			{name: 'id', index: 'id', width: 150, align: "center"}
			,{name: 'loginId', index: 'loginId', width: 150, align: "center"}
			,{name: 'name', index: 'name', width: 150, align: "center"}
			,{name: 'email', index: 'email', width: 150, align: "center"}
			,{name: 'createdAt', index: 'createdAt', width: 150, align: "center", formatter: "date", formatoptions: {newformat: "Y.m.d"}}
		]
		,height: 300
		,width: 600
		,rowNum: 10
		,pager: '#jqGridPager'
		,viewrecords: true // 페이지 네비게이터 우측 영역 표시 여부
		,loadonce: true // 로딩 중 화면 표시
		,rownumbers: true // 각 row의 맨 앞줄 각 행의 번호가 자동으로 부여되도록 설정
		,caption: "유저 정보"
		// 다시 확인
		,sortname: "id" // 처음 그리드를 불러올 때 정렬에 사용할 기준 컬럼
		,sortorder: "desc" // 정렬 기준
		,multiselect: true // 선택박스 추가
		,emptyrecode: "정보가 없습니다." // 데이터가 없는 경우 보여줄 문자열
		
		// rowData 가져오기
		,onSelectRow: function(rowId, status, e){
			// 같은 기능
			//$("#jqGrid").jqGrid("getRowDate",rowId);
			//$('#jqGrid').getRowData(rowId);
		}
	});
	
	
});
</script>