<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="content">
	<%-- 슬라이드 부분 --%>
	<div id="slideBanner">
		<img id="banner" src="${newsList[0].fileList[0].imagePath }" alt="" width="1110px;" height="450px;">
		<div id="bannerContentBox">
			<div id="bannerTitle" class="d-flex align-items-center ml-2">1.<br>
			<c:choose>
				<c:when test="${fn:length(newsList[0].board.title) gt 20 }">
					${fn:substring(newsList[0].board.title,0,20) }...
				</c:when>
				<c:when test="${fn:length(newsList[0].board.title) le 20 }">
					${newsList[0].board.title }
				</c:when>
			</c:choose>
			</div>
			<div id="bannerContent" class="d-flex align-items-center ml-2 mb-2">
			<c:choose>
				<c:when test="${fn:length(newsList[0].board.content) gt 35 }">
					${fn:substring(newsList[0].board.content,0,30) }...
				</c:when>
				<c:when test="${fn:length(newsList[0].board.content) le 35 }">
					${newsList[0].board.content }
				</c:when>
			</c:choose>
			
			</div>
			<a href="/board/board_view?boardId=${newsList[0].board.id}" id="bannerBtn">Read more</a>
		</div>
	</div>
	<%-- 다음경기 일정 --%>
	<div id="matchSchedule">
		<img id="matchImage" src="/static/images/mainpage/matchImage.png" alt="경기일정 배경사진" width="1110px" height="450px">
		<div id="matchScheduleContent">
			<div id="matchScheduleContentBox">
				<div id="matchScheduleContentTitle">
					[메이저리그 네셔널리그 서부지구]
					<br>
					<c:if test="${match.day eq day}">오늘 일정</c:if>
					<c:if test="${match.day ne day}">다음 일정</c:if>
				</div>
				<div id="matchScheduleContentTeam" class="d-flex justify-content-center align-items-center">
					<c:set var="name" value="${fn:split(match.awayTeamName,' ')}" />
					<div class="matchScheduleContentTeamFont2 m-2">${name[0] }<br>${name[1] }</div>
					<img src="${match.awayTeamLogo }" alt="구단 마크" width="100px" height="100px">
					<div class="matchScheduleContentTeamFont3 m-3">${match.awayScore }</div>
					<div id="matchScheduleContentTeamFont1">vs</div>
					<div class="matchScheduleContentTeamFont3 m-3">${match.homeScore }</div>
					<img src="${match.homeTeamLogo }" alt="구단 마크" width="100px" height="100px">
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
	<%-- 팀 순위 --%>
	<div id="teamRankBox">
		<div id="teamRankBoxTitle" class="d-flex align-items-center justify-content-center">
			<div>Sanfrancisco Giants Record</div>
		</div>
		<div id="teamRankBoxContent" class="d-flex align-items-center justify-content-center">
			<div id="teamRankBoxContentImageBox" class="d-flex justify-content-center align-items-center">
				<div class="teamRankBoxContentImageBoxFont1 text-center">
					<c:forEach var="team" items="${teamRanks }" varStatus="status">
					<c:if test="${team.teamName eq '샌프란시스코' }">
					<div class="teamRankBoxContentImageBoxFont2">네셔널리그 서부지구</div>
					<div class="teamRankBoxContentImageBoxFont3 m-2">${team.rank }</div>
					<div class="teamRankBoxContentImageBoxFont4 mt-2">현재순위 ${team.rank }위</div>
					<div class="teamRankBoxContentImageBoxFont5">2022-${month }-${day } (${team.wins }승 ${team.loses }패)</div>
					<a href="/match/match_result_view" class="btn matchViewBtn text-white m-2">Learn more > </a>
					</c:if>
					</c:forEach>
				</div>
			</div>
			<div id="teamRankBoxContentTableBox">
				<table class="teamRankBoxContentTable text-center">
					<tr>
						<td>순위</td>
						<td colspan="3">팀명</td>
						<td>경기</td>
						<td>승</td>
						<td>패</td>
						<td>게임차</td>
					</tr>
					<c:forEach var="team" items="${teamRanks }" varStatus="status">
					<tr>
						<td>${team.rank }</td>
						<td colspan="3">${team.teamName }</td>
						<td>${team.games }</td>
						<td>${team.wins }</td>
						<td>${team.loses }</td>
						<td>${team.gamesBehind }</td>
					</tr>
					</c:forEach>
				</table>
				<div>위 정보는 오후 중에 업데이트 됩니다.</div>
			</div>
		</div>
	</div>
	<%-- 뉴스 게시판 최신순 --%>
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
							<div id="recentBoard1ContentTitle" class="d-flex justify-content-center p-2">
							<c:if test="${fn:length(news.board.title) gt 20}">
								${fn:substring(news.board.title,0,20)}...
							</c:if>
							<c:if test="${fn:length(news.board.title) le 20}">
								${news.board.title }
							</c:if>
							</div>
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
									<div class="recentBoard2ContentTitle d-flex justify-content-center pt-2">
									<c:if test="${fn:length(news.board.title) gt 23}">
										${fn:substring(news.board.title,0,20)}...
									</c:if>
									<c:if test="${fn:length(news.board.title) le 23}">
										${news.board.title }
									</c:if>
									</div>
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
									<div class="recentBoard2ContentTitle d-flex justify-content-center pt-2">
									<c:if test="${fn:length(news.board.title) gt 23}">
										${fn:substring(news.board.title,0,20)}...
									</c:if>
									<c:if test="${fn:length(news.board.title) le 23}">
										${news.board.title }
									</c:if>
									</div>
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
									<div class="recentBoard2ContentTitle d-flex justify-content-center pt-2">
									<c:if test="${fn:length(news.board.title) gt 23}">
										${fn:substring(news.board.title,0,20)}...
									</c:if>
									<c:if test="${fn:length(news.board.title) le 23}">
										${news.board.title }
									</c:if>
									</div>
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
									<div class="recentBoard2ContentTitle d-flex justify-content-center pt-2">
									<c:if test="${fn:length(news.board.title) gt 23}">
										${fn:substring(news.board.title,0,20)}...
									</c:if>
									<c:if test="${fn:length(news.board.title) le 23}">
										${news.board.title }
									</c:if>
									</div>
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
	<%-- 기타 부분 - 구단 SNS 및 공지사항, 팬 게시판 부분 --%>
	<div id="etcBox" class="d-flex justify-content-center">
		<div id="etcBoxTeamSnsBox" class="d-flex align-items-center">
			<blockquote class="twitter-tweet"><p lang="en" dir="ltr">Leaving on a winning note. <a href="https://twitter.com/hashtag/SFGameUp?src=hash&amp;ref_src=twsrc%5Etfw">#SFGameUp</a> <a href="https://t.co/nJj4mec8xV">pic.twitter.com/nJj4mec8xV</a></p>&mdash; SFGiants (@SFGiants) <a href="https://twitter.com/SFGiants/status/1530984791100624896?ref_src=twsrc%5Etfw">May 29, 2022</a></blockquote> <script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>
		</div>
		<div id="etcBoxBoardBox">
			<div class="tableTitle d-flex align-items-end justify-content-center">
				공지사항 및 팬 게시판
			</div>
			<div class="d-flex justify-content-center mt-5">
				<table class="etcBoxBoardTable text-center">
					<tr>
						<th>공지사항</th>
						<th></th>
						<th><a class="etcBoxBoardTableMoreBtn" href="/board/notice_view">더보기+</a></th>
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
						<th><a class="etcBoxBoardTableMoreBtn" href="/board/fan_view">더보기+</a></th>
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
		var limit = 5;
		var boardKind = 2
		var postList;
		
		$.ajax({
			type: 'post'
			,url: '/post/get_post_boardAndImage'
			,async: false // 전역변수에 저장하기 위함.
			,data: {"limit":limit, "boardKind":boardKind}
			,success: function(data){
				postList = data;
			},error: function(e){
				alert("슬라이드 부분 에러발생, 관리자에게 문의해주세요.");
			}
		});
		
		// 일정시간마다 배너 넘기기
		var banner = document.getElementById("banner");
		var bannerTitle = document.getElementById("bannerTitle");
		var bannerContent = document.getElementById("bannerContent");
		var bannerBtn = document.getElementById("bannerBtn");
		var arrImage = [
			postList[0].fileList[0].imagePath
			,postList[1].fileList[0].imagePath
			,postList[2].fileList[0].imagePath
			,postList[3].fileList[0].imagePath
			,postList[4].fileList[0].imagePath
		];
		var arrTitle = [
			"1. "+postList[0].board.title
			,"2. "+postList[1].board.title
			,"3. "+postList[2].board.title
			,"4. "+postList[3].board.title
			,"5. "+postList[4].board.title
		];
		var arrContent = [
			postList[0].board.content
			,postList[1].board.content
			,postList[2].board.content
			,postList[3].board.content
			,postList[4].board.content
			
		];
		var arrBtn = [
			"/board/board_view?boardId="+postList[0].board.id
			,"/board/board_view?boardId="+postList[1].board.id
			,"/board/board_view?boardId="+postList[2].board.id
			,"/board/board_view?boardId="+postList[3].board.id
			,"/board/board_view?boardId="+postList[4].board.id
		];
		
		var index = 0;
		function changeBanner(){
			// 배너 이미지 바꾸기
			banner.setAttribute("src", arrImage[index]);
			// 배너 제목
			// 번호와 제목 내용 두 줄로 만들기 위한 과정
			var title = arrTitle[index].split(".");
			if(title[1].length > 20){
				title[1] = title[1].substring(0,20)+"...";
			}
			bannerTitle.innerHTML = title[0]+". <br>"+title[1];
			// 배너 내용
			for(let i=0; i<arrContent.length; i++){
				if(arrContent[i].length > 35){
					arrContent[i] = arrContent[i].substring(0,30)+"...";
				}
			}
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