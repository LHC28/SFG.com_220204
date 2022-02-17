<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content">
	<div class="detailPlayerBox1 d-flex align-items-center justify-content-center">
		<div class="detailPlayerBox2 d-flex align-items-center justify-content-center">
			<div class="detailPlayerbox3">
				<div class="d-flex justify-content-center mb-4">
					<div class="detailTitleBox">
						<div class="detailTitle d-flex align-items-center justify-content-center">
							<span>투수</span>
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
					<img src="${playerIntroduce.imagePath }" alt="선수 사진" width="300px" height="230px">
				</div>
				<div class="d-flex justify-content-center">
					<table class="playerStatTable text-center">
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
					</table>
				</div>
				<div class="d-flex justify-content-center mt-4">
					<table class="playerAnnualStat text-center">
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
					</table>
				</div>
			</div>
		</div>
	</div>
</div>