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
		,mtype: "post"
		,datatype: "json"
		,colNames: ['id','아이디','이름','이메일','생성일']
		,colModel: [
			{label: 'id', name: 'id', width: 150, key: true, editable: false, editrules: {required: true}, align: "center"}
			,{label: 'loginId', name: 'loginId', width: 150, editable: true, editrules: {required: true}, align: "center"}
			,{label: 'name', name: 'name', width: 150, editable: true, editrules: {required: true}, align: "center"}
			,{label: 'email', name: 'email', width: 150, editable: true, editrules: {required: true}, align: "center"}
			,{label: 'createdAt', name: 'createdAt', width: 150, editable: true, editrules: {required: true}, align: "center", formatter: "date", formatoptions: {newformat: "Y.m.d"}}
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
		,sortname: 'id' // 처음 그리드를 불러올 때 정렬에 사용할 기준 컬럼
		,sortorder: 'desc' // 정렬 기준 - 먹히지 않는 것으로 보임
		,multiselect: true // 선택박스 추가
		,emptyrecode: "정보가 없습니다." // 데이터가 없는 경우 보여줄 문자열
		// rowData 가져오기
		,onSelectRow: function(rowId, status, e){
			// 같은 기능
			//$("#jqGrid").jqGrid("getRowDate",rowId);
			//$('#jqGrid').getRowData(rowId);
		}
	});
	
	$('#jqGrid').navGrid('#jqGridPager',
            // the buttons to appear on the toolbar of the grid
            { edit: true, add: false, del: true, search: false, refresh: false, view: false, position: "left", cloneToTop: false },
            // options for the Edit Dialog
            {
            	mtype: "post"
               	,url: ""
               	,editData: {
               		account: function(){
               			var id = $( "#jqGrid" ).jqGrid('getGridParam', "selarrrow" );
               			var rowval = $("#jqGrid").jqGrid('getRowData',id);
               		}
               	}
                ,editCaption: "The Edit Dialog",
                recreateForm: true,
				checkOnUpdate : true,
				checkOnSubmit : true,
                closeAfterEdit: true,
                errorTextFormat: function (data) {
                    return 'Error: ' + data.responseText
                },
                reloadAfterSubmit: true
            },
            // options for the Add Dialog
            {
            	closeAfterAdd: true,
                recreateForm: true,
                // 추가할 일이 있는 경우 활용
                onclickSubmit: function(data){
                	
                },
                errorTextFormat: function (data) {
                    return 'Error: ' + data.responseText
                },
                reloadAfterSubmit: true
            },
            // options for the Delete Dailog
            {
            	mtype:"post"
            	,closeAfterDel: true
            	,reloadAfterSubmit: true
            	,url: '/user/delete_user'
            	,delData:{
            		account:function(){
            			var id = $( "#jqGrid" ).jqGrid('getGridParam', "selarrrow" );
    					//var rowval = $("#jqGrid").jqGrid('getRowData',sel);
    					//var id = rowval.id;
            		}
            	}
                ,errorTextFormat: function (data) {
                    return 'Error: ' + data.responseText
                },
				onclickSubmit: function(data){ // 확인 클릭시 이벤트
					
                },
                reloadAfterSubmit: true
			});
	
	
});
</script>