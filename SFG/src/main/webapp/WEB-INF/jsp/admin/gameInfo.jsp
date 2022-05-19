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
			<c:forEach var="num" begin="1" end="12" varStatus="status">
			<c:if test="${curMonth eq status.count }" >
			<option data-month-id="${status.count }" selected>${status.count }월</option>
			</c:if>
			<c:if test="${curMonth ne status.count }" >
			<option data-month-id="${status.count }">${status.count }월</option>
			</c:if>
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

	$(document).ready(function(){
		// 현재월 가져오기
		var now = new Date();
		var month = now.getMonth()+1;
		
		// select에서 월 바뀔시
		$('#playerSelectBox').on('change',function(){
			month = $('#playerSelectBox option:selected').data('month-id')
			
			// select값이 바뀌면 jqGrid도 바뀌도록
			jQuery("#jqGrid").jqGrid("setGridParam",
					{
						url: "/match/get_matchSchedule?month="+month
						,mtype: "get"
					}
			).trigger("reloadGrid");
		});
		
		$('#jqGrid').jqGrid({
			url: "/match/get_matchSchedule?month="+month
			,mtype: "get"
			,datatype: "json"
			,colNames: ['id','날짜','홈팅 id','원정팀 id', '홈팀 점수', '원정팀 점수', '결과','시간']
			,colModel: [
				{label: 'id', name: 'id', width: 60, key: true, editable: false, editrules: {required: true}, align: "center"}
				,{label: 'yyyymmdd', name: 'yyyymmdd', width: 150, editable: true, editrules: {required: true}, align: "center", formatter: "date", formatoptions: {newformat: "Y-m-d"}}
				,{label: 'homeTeamId', name: 'homeTeamId', width: 150, editable: true, edittype:'select',
					editoptions: {value:{'1':'Atlanta Braves','2':'Miami Marlins','3':'New York Mets','4':'Philadelphia Phillies','5':'Washington Nationals'
						,'6':'Chicago Cubs','7':'Cincinnati Reds','8':'Milwaukee Brewers','9':'Pittsburgh Pirates','10':'St.Louis Cardinals'
						,'11':'Arizona Diamondbacks','12':'Colorado Rockies','13':'Los Angeles Dodgers','14':'San Diego Padres','15':'San Francisco Giants'
						,'16':'Baltimore Orioles','17':'Boston Red Sox','18':'New York Yankees','19':'Tampa Bay Rays','20':'Toronto Blue Jays'
						,'21':'Chicago White Sox','22':'Cleveland Guardians','23':'Detroit Tigers','24':'Kansas City Royals','25':'Minnesota Twins'
						,'26':'Houston Astros','27':'Los Angeles Angels','28':'Oakland Athletics','29':'Seattle Mariners','30':'Texas Rangers'}}
					,editrules: {required: true}, align: "center"}
				,{label: 'awayTeamId', name: 'awayTeamId', width: 150, editable: true, edittype:'select',
					editoptions: {value:{'1':'Atlanta Braves','2':'Miami Marlins','3':'New York Mets','4':'Philadelphia Phillies','5':'Washington Nationals'
						,'6':'Chicago Cubs','7':'Cincinnati Reds','8':'Milwaukee Brewers','9':'Pittsburgh Pirates','10':'St.Louis Cardinals'
						,'11':'Arizona Diamondbacks','12':'Colorado Rockies','13':'Los Angeles Dodgers','14':'San Diego Padres','15':'San Francisco Giants'
						,'16':'Baltimore Orioles','17':'Boston Red Sox','18':'New York Yankees','19':'Tampa Bay Rays','20':'Toronto Blue Jays'
						,'21':'Chicago White Sox','22':'Cleveland Guardians','23':'Detroit Tigers','24':'Kansas City Royals','25':'Minnesota Twins'
						,'26':'Houston Astros','27':'Los Angeles Angels','28':'Oakland Athletics','29':'Seattle Mariners','30':'Texas Rangers'}}
					,editrules: {required: true}, align: "center"}
				,{label: 'homeScore', name: 'homeScore', width: 150, editable: true, editrules: {required: true}, align: "center"}
				,{label: 'awayScore', name: 'awayScore', width: 150, editable: true, editrules: {required: true}, align: "center"}
				,{label: 'result', name: 'result', width: 150, editable: true, editrules: {required: true}, align: "center"}
				,{label: 'time', name: 'time', width: 150, editable: true, editrules: {required: true}, align: "center"}
			]
			,height: 300
			,width: 850
			,rowNum: 30
			,pager: '#jqGridPager'
			,viewrecords: true // 페이지 네비게이터 우측 영역 표시 여부
			,loadonce: false // 로딩 중 화면 표시
			,rownumbers: true // 각 row의 맨 앞줄 각 행의 번호가 자동으로 부여되도록 설정
			,caption: "경기 정보"
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
	            { edit: true, add: true, del: true, search: false, refresh: false, view: false, position: "left", cloneToTop: false },
	            // options for the Edit Dialog
	            {
	            	mtype: "post"
	               	,url: "/match/edit_matchSchedule"
	               	,editData: {
	               		account: function(){
	               			var id = $( "#jqGrid" ).jqGrid('getGridParam', "selarrrow" );
	               			var yyyymmdd = $("#yyyymmdd").val();
		               		var homeTeamId = $("#homeTeamId").val();
		               		var awayTeamId = $("#awayTeamId").val();
		               		var homeScore = $("#homeScore").val();
		               		var awayScore = $("#awayScore").val();
		               		var result = $('#result').val();
		               		var time = $('#time').val();
		               		return id;
	               		}
	               	}
	                ,editCaption: "The Edit Dialog",
	                recreateForm: true,
	                onclickSubmit: function(data){
	                	// 업데이트시 새로고침
	                	jQuery("#jqGrid").trigger("reloadGrid");
	                	// 위 코드가 먹히기 위해서는 loadonce: false여야 한다.
	                }
					,checkOnUpdate : true,
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
		            ,url: "/match/add_matchSchedule"
		            ,addData: {
		            	account: function(){
		            		var yyyymmdd = $("#yyyymmdd").val();
		               		var homeTeamId = $("#homeTeamId").val();
		               		var awayTeamId = $("#awayTeamId").val();
		               		var homeScore = $("#homeScore").val();
		               		var awayScore = $("#awayScore").val();
		               		var result = $('#result').val();
		               		var time = $('#time').val();
		            	}
		            },
	            	closeAfterAdd: true,
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
	            	,url: '/match/delete_matchSchedule'
	            	,delData:{
	            		account:function(){
	            			var id = $( "#jqGrid" ).jqGrid('getGridParam', "selarrrow" );
	            			return id;
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