<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>
	<div class="d-flex align-items-center justify-content-between">
		<div class="d-flex align-items-center">
			<%-- 구단 로고 박스 --%>
			<div id="mainLogo" class="ml-5 d-flex align-items-center justify-content-center">
				<a href="#">
					<img src="/static/images/sfglogo.png" alt="메인로고" width="90px" height="90px">
				</a>
			</div>
			<%-- 네비게이션바 --%>
			<nav class="navBox">
				<ul class="nav nav-fill mt-1">
					<li class="nav-item"><a href="#" class="nav-link">구단</a></li>
					<li class="nav-item"><a href="#" class="nav-link">선수단</a></li>
					<li class="nav-item"><a href="#" class="nav-link">경기정보</a></li>
					<li class="nav-item"><a href="#" class="nav-link">히스토리</a></li>
					<li class="nav-item"><a href="#" class="nav-link">팬</a></li>
				</ul>
			</nav>
		</div>
		<%-- 회원가입 및 로그인 박스 --%>
		<%-- 로그인 전 --%>
		<div class="buttonBox1 mr-3 bg-success d-flex align-items-center justify-content-between">
			<div class="loginBox d-flex align-items-center justify-content-center">
				<a href="/user/sign_in_view">로그인</a>
			</div>
			<div class="loginBox d-flex align-items-center justify-content-center">
				<a href="">회원가입</a>
			</div>
		</div>
		<div class="buttonBox2 mr-3 bg-success d-flex align-items-center justify-content-between">
			<div class="ml-3 loginBoxId"><span class="loginBoxIdFont mr-1">abcd</span>님 환영합니다.</div>
			<div class="loginBox d-flex align-items-center justify-content-center">
				<button class="btn signOutBtn">로그아웃</button>
			</div>
		</div>
	</div>
</header>
