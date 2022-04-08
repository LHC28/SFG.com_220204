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
	<div id="recentBoardBox">
		<div id="recentBoardTitle" class="d-flex justify-content-center">Giants News</div>
		<div class="mt-3 d-flex justify-content-center">
			<div id="recentBoardContentBox" class="d-flex">
				<%-- 반복문 활용 --%>
				<c:forEach var="news" items="${newsList }" varStatus="status">
				<c:if test="${status.count eq 1 }">
				<div class="recentBoardContent1 d-flex align-items-center justify-content-center">
					<div id="recentBoard1">
						<a href="/board/board_view?boardId=${news.board.id }">
						<div id="recentBoard1Title">
							<c:forEach var="image" items="${news.fileList }" varStatus="status">
							<c:if test="${status.first}">
							<img id="recentBoardNews1" src="${image.imagePath}" alt="뉴스 사진" width="100%" height="100%">
							</c:if>
							</c:forEach>
						</div>
						<div id="recentBoard1Content">
							<div id="recentBoard1ContentTitle" class="d-flex justify-content-center p-2">${news.board.title }</div>
							<div class="d-flex justify-content-end mr-3"><fmt:formatDate value="${news.board.createdAt}" pattern="yyyy.MM.dd" /></div>
						</div>
						</a>
					</div>
				</div>
				</c:if>
				<%-- flex-column을 활용할 수도 있을듯 --%>
				<c:if test="${status.count eq 2 }">
				<div class="recentBoardContent2">
					<div class="recentBoardContent3 d-flex">
						<div class="recentBoardContent4 d-flex align-items-center justify-content-center">
							<div class="recentBoard2">
							<a href="/board/board_view?boardId=${news.board.id }">
								<div class="recentBoard2Title">
									<c:forEach var="image" items="${news.fileList }" varStatus="status">
									<c:if test="${status.first}">
									<img id="recentBoardNews1" src="${image.imagePath}" alt="뉴스 사진" width="100%" height="100%">
									</c:if>
									</c:forEach>
								</div>
								<div class="recentBoard2Content">
									<div class="recentBoard2ContentTitle d-flex justify-content-center pt-2">${news.board.title }</div>
									<div class="recentBoard2Contentcontent d-flex justify-content-end mr-3"><fmt:formatDate value="${news.board.createdAt}" pattern="yyyy.MM.dd" /></div>
								</div>
							</a>
							</div>
						</div>
				</c:if>
				<c:if test="${status.count eq 3 }">
						<div class="recentBoardContent4 d-flex align-items-center justify-content-center">
							<div class="recentBoard2">
							<a href="/board/board_view?boardId=${news.board.id }">
								<div class="recentBoard2Title">
									<c:forEach var="image" items="${news.fileList }" varStatus="status">
									<c:if test="${status.first}">
									<img id="recentBoardNews1" src="${image.imagePath}" alt="뉴스 사진" width="100%" height="100%">
									</c:if>
									</c:forEach>
								</div>
								<div class="recentBoard2Content">
									<div class="recentBoard2ContentTitle d-flex justify-content-center pt-2">${news.board.title }</div>
									<div class="recentBoard2Contentcontent d-flex justify-content-end mr-3"><fmt:formatDate value="${news.board.createdAt}" pattern="yyyy.MM.dd" /></div>
								</div>
							</a>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${status.count eq 4 }">
					<div class="recentBoardContent3 d-flex">
						<div class="recentBoardContent4 d-flex align-items-center justify-content-center">
							<div class="recentBoard2">
							<a href="/board/board_view?boardId=${news.board.id }">
								<div class="recentBoard2Title">
									<c:forEach var="image" items="${news.fileList }" varStatus="status">
									<c:if test="${status.first}">
									<img id="recentBoardNews1" src="${image.imagePath}" alt="뉴스 사진" width="100%" height="100%">
									</c:if>
									</c:forEach>
								</div>
								<div class="recentBoard2Content">
									<div class="recentBoard2ContentTitle d-flex justify-content-center pt-2">${news.board.title }</div>
									<div class="recentBoard2Contentcontent d-flex justify-content-end mr-3"><fmt:formatDate value="${news.board.createdAt}" pattern="yyyy.MM.dd" /></div>
								</div>
							</a>
							</div>
						</div>
				</c:if>
				<c:if test="${status.count eq 5 }">
						<div class="recentBoardContent4 d-flex align-items-center justify-content-center">
							<div class="recentBoard2">
							<a href="/board/board_view?boardId=${news.board.id }">
								<div class="recentBoard2Title">
									<c:forEach var="image" items="${news.fileList }" varStatus="status">
									<c:if test="${status.first}">
									<img id="recentBoardNews1" src="${image.imagePath}" alt="뉴스 사진" width="100%" height="100%">
									</c:if>
									</c:forEach>
								</div>
								<div class="recentBoard2Content">
									<div class="recentBoard2ContentTitle d-flex justify-content-center pt-2">${news.board.title }</div>
									<div class="recentBoard2Contentcontent d-flex justify-content-end mr-3"><fmt:formatDate value="${news.board.createdAt}" pattern="yyyy.MM.dd" /></div>
								</div>
							</a>
							</div>
						</div>
					</div>
				</div>
				</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
	<div id="etcBox" class="d-flex justify-content-center">
		<div id="etcBoxTeamSnsBox">
			<div class="tableTitle">
				<div class="d-flex align-items-center justify-content-center">
					
				</div>
			</div>
			
		</div>
		<div id="etcBoxBoardBox">
			<div class="tableTitle"></div>
			<div class="d-flex justify-content-center mt-5">
				<table class="etcBoxBoardTable text-center">
					<tr>
						<th>공지사항</th>
						<th></th>
						<th><a class="etcBoxBoardTableMoreBtn" href="#">더보기+</a></th>
					</tr>
					<%-- 3번 반복 --%>
					<c:forEach var="notice" items="${noticeList }" varStatus="status">
					<tr>
						<td class="etcBoxBoardTableId">${notice.board.id }</td>
						<td class="etcBoxBoardTableTitle"><a class="boardTitle" data-board-id=${notice.board.id }  href="/board/board_view?boardId=${notice.board.id }">${notice.board.title }</a></td>
						<td class="etcBoxBoardTableName">${notice.board.userName }</td>
					</tr>
					</c:forEach>
				</table>
			</div>
			<div class="d-flex justify-content-center mt-4">
				<table class="etcBoxBoardTable text-center">
					<tr>
						<th>팬게시판</th>
						<td></td>
						<td><a class="etcBoxBoardTableMoreBtn" href="#">더보기+</a></td>
					</tr>
					<%-- 3번 반복 --%>
					<c:forEach var="fan" items="${fanList }" varStatus="status">
					<tr>
						<td class="etcBoxBoardTableId">${fan.board.id }</td>
						<td class="etcBoxBoardTableTitle"><a class="boardTitle" data-board-id=${fan.board.id } href="/board/board_view?boardId=${fan.board.id }">${fan.board.title }</a></td>
						<td class="etcBoxBoardTableName">${fan.board.userName }</td>
					</tr>
					</c:forEach>
				</table>
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
		
		<%-- 조회수 증가 기능 --%>
		$('.boardTitle').on('click', function(e){
			let boardId = $(this).data('board-id');
			
			$.ajax({
				type: 'post'
				,url: '/board/add_views'
				,data: {"boardId":boardId}
				,success: function(data){
					if(data.result=="false"){
						alert("조회수 관련 에러가 발생하였습니다. 관리자에게 문의해주세요.");
					}
				},error: function(e){
					alert("error : "+e);
				}
			});
		});
	});
</script>