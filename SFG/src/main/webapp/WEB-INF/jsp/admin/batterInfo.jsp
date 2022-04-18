<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<div class="d-flex">
<%-- 관리자 페이지 nav --%>
	<jsp:include page="../include/adminNav.jsp"></jsp:include>
	<%-- table 라이브러리 넣었던 곳... --%>
	<section class="adminSection p-3">
		<select id="playerSelectBox" class="mb-2">
			<c:forEach var="batter" items="${batterList }" varStatus="status">
			<option data-batter-id="${batter.id }">${batter.name} : ${batter.id}</option>
			</c:forEach>
		</select>
		<div class="tableWrap d-flex justify-content-center">
			<div>
				<table id="jqGrid"></table>
				<div id="jqGridPager"></div>
			</div>
		</div>
	</section>
</div>
<script>
	// 전역변수로 활용하기 위함.
	var playerId = 3;
	
	$(document).ready(function(){
		// select 값이 바뀌는 경우
		$("#playerSelectBox").on('change', function(){
			playerId = $('#playerSelectBox option:selected').data('batter-id');
			
			// select의 값이 변하면 jqGrid의 값도 변경되도록 하기 위함.
			jQuery("#jqGrid").jqGrid("setGridParam",
					{
						url: "/player/get_batter_stat?playerId="+playerId
						,mtype: "get"
					}
			).trigger("reloadGrid");
		});
		
		$('#jqGrid').jqGrid({
			url: "/player/get_batter_stat?playerId="+playerId
			,mtype: "get"
			,datatype: "json"
			,colNames: ['id','선수id','연도','팀','경기수','타석','득점','안타','2루타','3루타','홈런','타점','볼넷','삼진','도루','사구','희플']
			,colModel: [
				{label: 'id', name: 'id', width: 60, key: true, editable: false, editrules: {required: true}, align: "center"}
				,{label: 'playerId', name: 'playerId', width: 60, editable: true, editrules: {required: true}, align: "center"}
				,{label: 'year', name: 'year', width: 60, editable: true, editrules: {required: true}, align: "center"}
				,{label: 'team', name: 'team', width: 60, editable: true, editrules: {required: true}, align: "center"}
				,{label: 'games', name: 'games', width: 60, editable: true, editrules: {required: true}, align: "center"}
				,{label: 'at_bats', name: 'at_bats', width: 60, editable: true, editrules: {required: true}, align: "center"}
				,{label: 'runs', name: 'runs', width: 60, editable: true, editrules: {required: true}, align: "center"}
				,{label: 'hits', name: 'hits', width: 60, editable: true, editrules: {required: true}, align: "center"}
				,{label: 'doubles', name: 'doubles', width: 60, editable: true, editrules: {required: true}, align: "center"}
				,{label: 'triples', name: 'triples', width: 60, editable: true, editrules: {required: true}, align: "center"}
				,{label: 'homerun', name: 'homerun', width: 60, editable: true, editrules: {required: true}, align: "center"}
				,{label: 'runs_batted_in', name: 'runs_batted_in', width: 60, editable: true, editrules: {required: true}, align: "center"}
				,{label: 'bases_on_balls', name: 'bases_on_balls', width: 60, editable: true, editrules: {required: true}, align: "center"}
				,{label: 'strikeouts', name: 'strikeouts', width: 60, editable: true, editrules: {required: true}, align: "center"}
				,{label: 'stolen_bases', name: 'stolen_bases', width: 60, editable: true, editrules: {required: true}, align: "center"}
				,{label: 'hit_by_pitch', name: 'hit_by_pitch', width: 60, editable: true, editrules: {required: true}, align: "center"}
				,{label: 'sacrifice_flys', name: 'sacrifice_flys', width: 60, editable: true, editrules: {required: true}, align: "center"}
			]
			,height: 300
			,width: 850
			,rowNum: 10
			,pager: '#jqGridPager'
			,viewrecords: true // 페이지 네비게이터 우측 영역 표시 여부
			,loadonce: false // 로딩 중 화면 표시
			,rownumbers: true // 각 row의 맨 앞줄 각 행의 번호가 자동으로 부여되도록 설정
			,caption: "타자 기록 정보"
			// 다시 확인
			,sortname: 'year' // 처음 그리드를 불러올 때 정렬에 사용할 기준 컬럼
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
	            { edit: true, add: true, del: true, search: false, refresh: false, view: false, position: "left", cloneToTop: false },
	            // options for the Edit Dialog
	            {
	            	mtype: "post"
	               	,url: "/player/edit_batter_stat"
	               	,editData: {
	               		account: function(){
	               			var playerId = $("playerId").val();
		               		var year = $("#year").val();
		               		var team = $("#team").val();
		               		var games = $("#games").val();
		               		var at_bats = $("#at_bats").val();
		               		var runs = $("#runs").val();
		               		var hits = $("#hits").val();
		               		var doubles = $("#doubles").val();
		               		var triples = $("#triples").val();
		               		var homerun = $("#homerun").val();
		               		var runs_batted_in = $("#runs_batted_in").val();
		               		var bases_on_balls = $("#bases_on_balls").val();
		               		var strikeouts = $("#strikeouts").val();
		               		var stolen_bases = $("#stolen_bases").val();
		               		var hit_by_pitch = $("#hit_by_pitch").val();
		               		var sacrifice_flys = $("#sacrifice_flys").val();
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
	            	mtype: "post"
		            ,url: "/player/add_batter_stat"
		            ,addData: {
		             	account: function(){
		             		// 입력칸에 입력하는 값이 아니면 넘어가지 않는 이슈가 있는듯. 계속 null값이 나오는 것으로 보임. 따라서 select에서 고른 값을 가져오는 것은 불가능한 것으로 보여
		             		// 직접 입력하는 것으로 결정 - select에서 이름 옆에 id값이 보이도록 변경
		             		var playerId = $("playerId").val();
		               		var year = $("#year").val();
		               		var team = $("#team").val();
		               		var games = $("#games").val();
		               		var at_bats = $("#at_bats").val();
		               		var runs = $("#runs").val();
		               		var hits = $("#hits").val();
		               		var doubles = $("#doubles").val();
		               		var triples = $("#triples").val();
		               		var homerun = $("#homerun").val();
		               		var runs_batted_in = $("#runs_batted_in").val();
		               		var bases_on_balls = $("#bases_on_balls").val();
		               		var strikeouts = $("#strikeouts").val();
		               		var stolen_bases = $("#stolen_bases").val();
		               		var hit_by_pitch = $("#hit_by_pitch").val();
		               		var sacrifice_flys = $("#sacrifice_flys").val();
		               	}
		            }
	            	,closeAfterAdd: true,
	                recreateForm: true,
	                // 추가할 일이 있는 경우 활용
	                onclickSubmit: function(data){
	                	// 추가시 새로고침
	                	jQuery("#jqGrid").trigger("reloadGrid");
	                	// 위 코드가 먹히기 위해서는 loadonce: false여야 한다.
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
	            	,url: '/player/delete_batter_stat'
	            	,delData:{
	            		account:function(){
	            			var statId = $( "#jqGrid" ).jqGrid('getGridParam', "selarrrow" );
	            			return statId
	            		},
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