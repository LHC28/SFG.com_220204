<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>
	<div class="d-flex align-items-center justify-content-between">
		<div class="d-flex align-items-center">
			<%-- 구단 로고 박스 --%>
			<div id="mainLogo" class="bg-danger ml-5"></div>
			<%-- 네비게이션바 --%>
			<div class="navBox bg-info"></div>
		</div>
		<%-- 회원가입 및 로그인 박스 --%>
		<%-- 로그인 전 --%>
		<div class="buttonBox mr-3 bg-success d-flex align-items-center justify-content-between">
			<div class="loginBox d-flex align-items-center justify-content-center">
				<a href="/user/sign_in_view">로그인</a>
			</div>
			<div class="loginBox d-flex align-items-center justify-content-center">
				<a href="">회원가입</a>
			</div>
		</div>
	</div>
	
</header>