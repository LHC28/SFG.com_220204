<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="content">
	<div id="slideBanner">
		<img id="banner" src="/static/images/ace.jpg" alt="" width="1110px;" height="450px;">
	</div>
	<div id="matchSchedule"></div>
</div>


<script>
	$(document).ready(function(){
		
		// 일정시간마다 이미지 넘기기
		var banner = document.getElementById("banner");
		var arrImage = [
			"/static/images/ace.jpg",
			""
		];
		var index = 0;
		function changeBanner(){
			banner.setAttribute("src", arrImage[index]);
			index++;
			if(index>=arrImage.length){
				index = 0;
			}
		}
		// 3초마다 함수 실행
		setInterval(changeBanner, 3000);
	});
</script>