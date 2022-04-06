<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="content">
	<div id="slideBanner">
		<img id="banner" src="/static/images/ace.jpg" alt="" width="1110px;" height="450px;">
		<div id="bannerContentBox">
			<div id="bannerTitle" class="d-flex align-items-center ml-2">1.<br> 제목 부분</div>
			<div id="bannerContent" class="d-flex align-items-center ml-2 mb-2">1. 내용 부분</div>
			<a href="#" id="bannerBtn">Read more</a>
		</div>
	</div>
	<div id="matchSchedule">
		<img id="matchImage" src="/static/images/mainpage/matchImage.png" alt="경기일정 배경사진" width="1110px" height="450px">
		<div id="matchScheduleContent">
			<div id="matchScheduleContentBox">
				<div id="matchScheduleContentTitle">
					[메이저리그 네셔널리그 서부지구]<br>다음 경기
				</div>
				<div id="matchScheduleContentTeam" class="d-flex justify-content-center align-items-center">
					<c:set var="name" value="${fn:split(match.awayTeamName,' ')}" />
					<div class="matchScheduleContentTeamFont2 m-2">${name[0] }<br>${name[1] }</div>
					<img src="${match.awayTeamLogo }" alt="" width="100px" height="100px">
					<div id="matchScheduleContentTeamFont1">vs</div>
					<img src="${match.homeTeamLogo }" alt="" width="100px" height="100px">
					<c:set var="name" value="${fn:split(match.homeTeamName, ' ')}" />
					<div class="matchScheduleContentTeamFont2 m-2">${name[0] }<br>${name[1] }</div>
				</div>
				<div id="matchScheduleContentDate">
					<div><fmt:formatDate value="${match.yyyymmdd }" pattern="yyyy.MM.dd" /></div>
					<c:if test="${match.time eq null }">
					<div>시간 미정</div>
					</c:if>
					<c:if test="${match.time ne null }">
					<div>${match.time }</div>
					</c:if>
					<div>${match.stadium }</div>
				</div>
			</div>
		</div>
	</div>
	<div id="recentBoard">
		<div id="recentBoardTitle">Giants News</div>
	</div>
</div>


<script>
	$(document).ready(function(){
		
		// 일정시간마다 배너 넘기기
		var banner = document.getElementById("banner");
		var bannerTitle = document.getElementById("bannerTitle");
		var bannerContent = document.getElementById("bannerContent");
		var bannerBtn = document.getElementById("bannerBtn");
		var arrImage = [
			"/static/images/ace.jpg",
			"/static/images/posey.png"
		];
		var arrTitle = [
			"1. 제목1",
			"2. 제목2"
		];
		var arrContent = [
			"내용1",
			"내용2"
		];
		var arrBtn = [
			"#",
			"#"
		];
		
		var index = 0;
		function changeBanner(){
			// 배너 이미지 바꾸기
			banner.setAttribute("src", arrImage[index]);
			// 배너 제목
			// 번호와 제목 내용 두 줄로 만들기 위한 과정
			var title = arrTitle[index].split(".");
			bannerTitle.innerHTML = title[0]+". <br>"+title[1];
			// 배너 내용
			bannerContent.innerHTML = arrContent[index];
			// 배너 버튼
			bannerBtn.setAttribute("href", arrBtn[index]);
			
			index++;
			if(index>=arrImage.length){
				index = 0;
			}
		}
		// 3초마다 함수 실행
		setInterval(changeBanner, 3000);
	});
</script>