<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="detailPlayerBoxContent">
	<div class="detailPlayerBox1 d-flex align-items-center justify-content-center">
		<div class="detailPlayerBox2 d-flex align-items-center justify-content-center">
			<div class="detailPlayerbox3">
				<div class="d-flex justify-content-center mb-4">
					<div class="detailTitleBox">
						<div class="detailTitle d-flex align-items-center justify-content-center">
							<c:if test="${player.position eq 'pitcher' }">
							<span>투수</span>
							</c:if>
							<c:if test="${player.position.contains('infielder') }">
							<span>내야수</span>
							</c:if>
							<c:if test="${player.position.contains('outfielder') }">
							<span>외야수</span>
							</c:if>
						</div>
					</div>
				</div>
				<div class="d-flex justify-content-center mb-4">
					<%-- 내용 --%>
					<div class="detailContentBox">
						<div class="detailTitleName d-flex align-items-center justify-content-center mb-2">
							<span class="title">
								${player.name}
							</span>
						</div>
						<div>
							${playerIntroduce.title}
						</div>
						<div class="detailContent mt-2">
							${playerIntroduce.content}
						</div>
					</div>
					<img src="${playerIntroduce.imagePath}" alt="선수 사진" width="300px" height="230px">
				</div>
				<div class="d-flex justify-content-center">
					<table class="playerStatTable text-center">
						<%-- 해당 선수 포지션이 투수인 경우 --%>
						<c:if test="${player.position eq 'pitcher' }">
						<thead>
							<tr>
								<th>경기수</th>
								<th>승</th>
								<th>패</th>
								<th>이닝</th>
								<th>평균자책점</th>
								<th>탈삼진</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${pitcherTotalStat.games}</td>
								<td>${pitcherTotalStat.wins}</td>
								<td>${pitcherTotalStat.losses}</td>
								<td>${pitcherTotalStat.innings_pitched}</td>
								<td>${pitcherTotalStat.earned_run_average }</td>
								<td>${pitcherTotalStat.strikeouts }</td>
							</tr>
						</tbody>
						</c:if>
						<c:if test="${player.position.contains('catcher') || player.position.contains('infielder') || player.position.contains('outfielder')}">
						<thead>
							<tr>
								<th>경기수</th>
								<th>안타</th>
								<th>홈런</th>
								<th>타점</th>
								<th>도루</th>
								<th>타율</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${batterTotalStat.games }</td>
								<td>${batterTotalStat.hits }</td>
								<td>${batterTotalStat.homerun }</td>
								<td>${batterTotalStat.runs_batted_in }</td>
								<td>${batterTotalStat.stolen_bases }</td>
								<td>${batterTotalStat.batting_average }</td>
							</tr>
						</tbody>
						</c:if>
					</table>
				</div>
				<div class="d-flex justify-content-center mt-4">
					<table class="playerAnnualStat text-center">
						<%-- 해당 선수 포지션이 내야, 외야수인 경우 --%>
						<c:if test="${player.position eq 'pitcher' }">
						<thead>
							<tr>
								<th>연도</th>
								<th>팀</th>
								<th>승리</th>
								<th>패</th>
								<th>평균자책점</th>
								<th>경기수</th>
								<th>선발등판</th>
								<th>세이브</th>
								<th>이닝</th>
								<th>피안타</th>
								<th>볼넷</th>
								<th>삼진</th>
								<th>WHIP</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="pitcherStat" items="${pitcherStats }" varStatus="status">
							<tr>
								<td>${pitcherStat.year}</td>
								<td>${pitcherStat.team}</td>
								<td>${pitcherStat.wins }</td>
								<td>${pitcherStat.losses }</td>
								<td>${pitcherStat.earned_run_average }</td>
								<td>${pitcherStat.games }</td>
								<td>${pitcherStat.game_started }</td>
								<td>${pitcherStat.saves }</td>
								<td>${pitcherStat.innings_pitched }</td>
								<td>${pitcherStat.hits }</td>
								<td>${pitcherStat.walks }</td>
								<td>${pitcherStat.strikeouts }</td>
								<td>${pitcherStat.whip}</td>
							</tr>
							</c:forEach>
						</tbody>
						</c:if>
						<c:if test="${player.position.contains('infielder') || player.position.contains('outfielder')}">
						<thead>
							<tr>
								<th>연도</th>
								<th>경기수</th>
								<th>안타</th>
								<th>홈런</th>
								<th>득점</th>
								<th>타점</th>
								<th>도루</th>
								<th>볼넷</th>
								<th>삼진</th>
								<th>타율</th>
								<th>출루율</th>
								<th>장타율</th>
								<th>OPS</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="batterStat" items="${batterStats }" varStatus="status">
							<tr>
								<%-- 소수점 아래 0빠지는건 여기서 변환하는 것으로 해결 --%>
								<td>${batterStat.year }</td>
								<td>${batterStat.games }</td>
								<td>${batterStat.hits }</td>
								<td>${batterStat.homerun }</td>
								<td>${batterStat.runs }</td>
								<td>${batterStat.runs_batted_in }</td>
								<td>${batterStat.stolen_bases }</td>
								<td>${batterStat.bases_on_balls }</td>
								<td>${batterStat.strikeouts }</td>
								<td><fmt:formatNumber value="${batterStat.batting_average }" pattern="0.000"/></td>
								<td><fmt:formatNumber value="${batterStat.on_base_percentage }" pattern="0.000"/></td>
								<td><fmt:formatNumber value="${batterStat.slugging_percentage }" pattern="0.000"/></td>
								<td><fmt:formatNumber value="${batterStat.on_base_plus_slugging }" pattern="0.000"/></td>
							</tr>
							</c:forEach>
						</tbody>
						</c:if>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
