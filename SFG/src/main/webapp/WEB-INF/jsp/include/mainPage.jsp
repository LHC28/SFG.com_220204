<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<div id="matchScheduleContentTeam">
					<span></span>
					<img src="${match.homeTeamLogo }" alt="" width="80px" height="80px">
					<span>vs</span>
					<img src="${match.awayTeamLogo }" alt="" width="80px" height="80px">
					<span></span>
				</div>
				<div id="matchScheduleContentDate"></div>
			</div>
		</div>
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