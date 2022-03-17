<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
	<div class="d-flex align-items-center justify-content-between">
		<div class="d-flex align-items-center">
			<%-- 구단 로고 박스 --%>
			<div id="mainLogo" class="ml-5 d-flex align-items-center justify-content-center">
				<a href="/main/main_page">
					<img src="/static/images/sfglogo.png" alt="메인로고" width="90px" height="90px">
				</a>
			</div>
			<%-- 네비게이션바 --%>
			<nav class="navBox">
				<ul class="nav nav-fill mt-1">
					<li class="nav-item"><a href="/team/introduce_view" class="nav-link">구단</a>
						<ul class="subMenu">
							<li><a href="/team/introduce_view" class="m-3">구단소개</a></li>
							<li><a href="/team/represent_player_view" class="m-3">대표 선수</a></li>
							<li><a href="/team/logo_view" class="m-3">로고 및 마스코트</a></li>
							<li><a href="/team/stadium_view" class="m-3">구장</a></li>
						</ul>
					</li>
					<li class="nav-item"><a href="/player/coach_view" class="nav-link">선수단</a>
						<ul class="subMenu">
							<li><a href="/player/coach_view" class="m-3">코칭스태프</a></li>
							<li><a href="/player/pitcher_view" class="m-3">투수</a></li>
							<li><a href="/player/in_fielder_view" class="m-3">내야수</a></li>
							<li><a href="/player/out_fielder_view" class="m-3">외야수</a></li>
						</ul>
					</li>
					<li class="nav-item"><a href="/match/match_result_view" class="nav-link">경기정보</a>
						<ul class="subMenu">
							<li><a href="/match/match_result_view" class="m-3">경기일정</a></li>
						</ul>
					</li>
					<li class="nav-item"><a href="#" class="nav-link">히스토리</a>
						<ul class="subMenu">
							<li><a href="#" class="m-3">submenu</a></li>
						</ul>
					</li>
					<li class="nav-item"><a href="/board/notice_view" class="nav-link">팬</a>
						<%-- 마지막 메뉴바 왼쪽으로 땡기기 --%>
						<ul class="subMenu subMenuLast">
							<li><a href="/board/notice_view" class="m-3">공지사항</a></li>
							<li><a href="/board/news_view" class="m-3">구단뉴스</a></li>
							<li><a href="/board/fan_view" class="m-3">팬 게시판</a></li>
							<li><a href="/board/picture_view" class="m-3">사진 게시판</a></li>
							<li><a href="/board/suggest_view" class="m-3">건의사항</a></li>
						</ul>
					</li>
				</ul>
			</nav>
		</div>
		<%-- 회원가입 및 로그인 박스 --%>
		<%-- 로그인 전 --%>
		<c:if test="${empty loginId}">
			<div class="buttonBox1 mr-3 d-flex align-items-center justify-content-between">
				<div class="loginBox d-flex align-items-center justify-content-center">
					<a href="/user/sign_in_view">로그인</a>
				</div>
				<div class="loginBox d-flex align-items-center justify-content-center">
					<a href="/user/sign_up_view">회원가입</a>
				</div>
			</div>
		</c:if>
		<%-- 로그인 후 --%>
		<c:if test="${not empty loginId }">
			<div class="buttonBox2 mr-3 d-flex align-items-center justify-content-between">
				<div class="ml-3 loginBoxId"><span class="loginBoxIdFont mr-1">abcd</span><span class="text-white">님 환영합니다.</span></div>
				<div class="loginBox d-flex align-items-center justify-content-center">
					<a href="/user/sign_out"><button class="btn signOutBtn">로그아웃</button></a>
				</div>
			</div>
		</c:if>
	</div>
</header>