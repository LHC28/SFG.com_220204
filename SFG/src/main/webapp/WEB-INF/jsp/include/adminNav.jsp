<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<li class="nav-item adminNavFont p-2">
				<div class="dropdown">
					<a href="/admin/player_info" class="nav-link">선수단 페이지 관리</a>
					<div class="dropdown-content">
						<a href="/admin/player_info">선수 및 코칭스태프 등록</a>
						<a href="/admin/batter_info">선수 타자 기록 등록</a>
						<a href="#">선수 투수 기록 등록</a>
					</div>
				</div>
			</li>
			<li class="nav-item adminNavFont p-2"><a href="#" class="nav-link">경기정보 페이지 관리</a></li>
			<li class="nav-item adminNavFont p-2"><a href="#" class="nav-link">팬 페이지 관리</a></li>
		</ul>
	</nav>