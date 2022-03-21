<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex">
	<nav class="adminNav">
		<ul class="nav flex-column">
			<li class="nav-item adminNavFont p-2"><a href="#" class="nav-link">회원정보</a></li>
			<li class="nav-item adminNavFont p-2">
				<div class="dropdown">
					<a href="#" class="dropbtn nav-link">메인 페이지 관리</a>
					<div class="dropdown-content">
					    <a href="#">메인 배너</a>
					    <a href="#">다음 경기</a>
					    <a href="#">팀성적</a>
					</div>
				</div>
			</li>
			<li class="nav-item adminNavFont p-2">
				<div class="dropdown">
					<a href="#" class="dropbtn nav-link">구단 페이지 관리</a>
					<div class="dropdown-content">
					    <a href="#">구단소개</a>
					    <a href="#">대표 선수</a>
					    <a href="#">로고 및 마스코트</a>
					    <a href="#">구장</a>
					</div>
				</div>
			</li>
			<li class="nav-item adminNavFont p-2"><a href="#" class="nav-link">선수단 페이지 관리</a></li>
			<li class="nav-item adminNavFont p-2"><a href="#" class="nav-link">경기정보 페이지 관리</a></li>
			<li class="nav-item adminNavFont p-2"><a href="#" class="nav-link">팬 페이지 관리</a></li>
		</ul>
	</nav>
	<section class="adminSection p-3">
		<table id="userList">
			<thead>
				<tr>
					<th>loginId</th>
					<th>name</th>
					<th>email</th>
					<th>createdAt</th>
				</tr>
			</thead>
		</table>
	</section>
</div>
<script>
	$(document).ready(function(){
		// 이전에 만든 것 없애고 다시 그리기 위한 기능
		$("#userList").DataTable().destroy();
		$("#userList").DataTable({
			responsive: false,  //반응형 설정
	          pageLength: 10,     //페이지 당 글 개수 설정
	          autoWidth: false,
	          destroy: true,
	          processing: true,
	          serverSide: false,
	          searching: false,    //검색란 표시 설정
	          ordering: true,      //글 순서 설정
	          paging: true,        //페이징 표시 설정
	          dom: "Blfrtip",      //버튼 dom 설정 l을 추가하면 pagelength 드롭다운 표시
	          buttons: [
	            {
	              extend: "excel",
	              text: "엑셀 다운로드",
	              filename: function () {
	                if ($("#file1").val() === "a1") {
	                  return "_"+$("#file1").val();
	                } else {
	                  return "file2";
	                }
	              },

	              customize: function (xlsx) {
	              //엑셀 셀 커스텀
	                var sheet = xlsx.xl.worksheets["sheet1.xml"];
	                $("c[r=B2] t", sheet).text("custom text1");
	                $("c[r=C2] t", sheet).text("custom text2");
	 
	              },
	            },
	          ],
	          language: {
	            emptyTable: "데이터가 없습니다.",
	            lengthMenu: "페이지당 _MENU_ 개씩 보기",
	            info: "현재 _START_ - _END_ / _TOTAL_건",
	            infoEmpty: "데이터 없음",
	            infoFiltered: "( _MAX_건의 데이터에서 필터링됨 )",
	            search: "",
	            zeroRecords: "일치하는 데이터가 없습니다.",
	            loadingRecords: "로딩중...",
	            processing: "잠시만 기다려 주세요.",
	            paginate: {
	              next: "다음",
	              previous: "이전",
	            },
	          },
			
	        ajax:{
	        	url:"/user/get_all_user",
	        	type:"GET",
	        	dataSrc :''
	        },
	        columns:[
	        	{data:"loginId"},
	        	{data:"name"},
	        	{data:"email"},
	        	{data:"createdAt"}
	        ]
		});
	});
</script>