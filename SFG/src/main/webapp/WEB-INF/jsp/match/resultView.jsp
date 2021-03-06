<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="matchResultViewContent">
	<div class="matchBox1 d-flex align-items-center justify-content-center">
		<div class="matchBox2 d-flex align-items-start justify-content-center">
			<div class="matchBox3">
				<div class="d-flex justify-content-center mb-4">
					<div class="detailTitleBox">
						<div class="detailTitle d-flex justify-content-center">
							<span>경기일정</span>
						</div>
					</div>
				</div>
				<div class="d-flex justify-content-center">
					<div class="matchScheduleBox">
						<%-- 연월 및 방향버튼 --%>
						<div class="matchScheduleBoxTitle d-flex justify-content-center mb-4">
							<c:if test="${month ne 1 }">
								<a href="/match/match_result_view?inputMonth=${month-1 }" class="mr-3">&lt;</a>
							</c:if>
							<span>2021. ${month }.</span>
							<c:if test="${month ne 12 }">
								<a href="/match/match_result_view?inputMonth=${month+1 }" class="ml-3">&gt;</a>
							</c:if>
						</div>
						<%-- 일정표 --%>
						<div class="d-flex justify-content-center">
							<table class="matchScheduleBoxContent">
								<thead>
									<tr class="text-center">
										<th>월</th>
										<th>화</th>
										<th>수</th>
										<th>목</th>
										<th>금</th>
										<th>토</th>
										<th>일</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="game" items="${matchSchedule }" varStatus = "status">
									<%-- 월요일 및 경기 없는 경우 --%>
									<c:if test="${status.count%7 ==1 && game.id ne 0 && game.yyyymmdd ne null}">
									<tr>
										<td>
											<span class="dateFont d-flex justify-content-end mr-2">
												${game.day}
											</span>
											<div class="mt-3">
												<div class="d-flex justify-content-center">
													<c:if test="${game.homeTeamId ne 15 }">
													<img src="${game.homeTeamLogo }" alt="구단로고" width="50px" height="50px">
													</c:if>
													<c:if test="${game.awayTeamId ne 15 }">
													<img src="${game.awayTeamLogo }" alt="구단로고" width="50px" height="50px">
													</c:if>
												</div>
												<c:if test="${game.time ne null }">
												<div class="text-center mt-2">${game.time }</div>
												</c:if>
												<c:if test="${game.time eq null }">
												<div class="text-center mt-2">시간 미정</div>
												</c:if>
												<c:if test="${game.homeScore ne null && game.awayScore ne null }">
												<div class="text-center">${game.result} ${game.homeScore }:${game.awayScore }</div>
												</c:if>
												<div class="text-center mt-2">${game.stadium}</div>
												<br>
											</div>
										</td>
									</c:if>
									
									<c:if test="${status.count%7 ==1 && game.id ne 0 && game.yyyymmdd eq null}">
									<tr>
										<td>
											<span class="dateFont d-flex justify-content-end mr-2">
												${game.day}
											</span>
										</td>
									</c:if>
									
									<c:if test="${status.count%7==1 && game.id eq 0}">
									<tr>
										<td>
											<span class="dateFont d-flex justify-content-end mr-2">
												${game.day}
											</span>
										</td>
									</c:if>
									
									<c:if test="${status.count%7>1 && status.count%7<=6 && game.id ne 0 && game.yyyymmdd ne null}">
										<td>
											<span class="dateFont d-flex justify-content-end mr-2">
												${game.day}
											</span>
											<div class="mt-3">
												<div class="d-flex justify-content-center">
													<c:if test="${game.homeTeamId ne 15 }">
													<img src="${game.homeTeamLogo }" alt="구단로고" width="50px" height="50px">
													</c:if>
													<c:if test="${game.awayTeamId ne 15 }">
													<img src="${game.awayTeamLogo }" alt="구단로고" width="50px" height="50px">
													</c:if>
												</div>
												<c:if test="${game.time ne null }">
												<div class="text-center mt-2">${game.time }</div>
												</c:if>
												<c:if test="${game.time eq null }">
												<div class="text-center mt-2">시간 미정</div>
												</c:if>
												<c:if test="${game.homeScore ne null && game.awayScore ne null }">
												<div class="text-center">${game.result} ${game.homeScore }:${game.awayScore }</div>
												</c:if>
												<div class="text-center mt-2">${game.stadium}</div>
												<br>
											</div>
										</td>
									</c:if>
									
									<c:if test="${status.count%7>1 && status.count%7<=6 && game.yyyymmdd eq null}">
										<td>
											<span class="dateFont d-flex justify-content-end mr-2">
												${game.day}
											</span>
										</td>
									</c:if>
									
									<c:if test="${status.count%7==0 && game.id ne 0}">
										<td>
											<span class="dateFont text-danger font-weight-bold d-flex justify-content-end mr-2">
												${game.day}
											</span>
											<div class="mt-3">
												<div class="d-flex justify-content-center">
													<c:if test="${game.homeTeamId ne 15 }">
													<img src="${game.homeTeamLogo }" alt="구단로고" width="50px" height="50px">
													</c:if>
													<c:if test="${game.awayTeamId ne 15 }">
													<img src="${game.awayTeamLogo }" alt="구단로고" width="50px" height="50px">
													</c:if>
												</div>
												<c:if test="${game.time ne null }">
												<div class="text-center mt-2">${game.time }</div>
												</c:if>
												<c:if test="${game.time eq null }">
												<div class="text-center mt-2">시간 미정</div>
												</c:if>
												<c:if test="${game.homeScore ne null && game.awayScore ne null }">
												<div class="text-center">${game.result} ${game.homeScore }:${game.awayScore }</div>
												</c:if>
												<div class="text-center mt-2">${game.stadium}</div>
												<br>
											</div>
										</td>
									</tr>
									</c:if>
									
									<c:if test="${status.count%7==0 && game.id eq 0 }">
										<td>
											<span class="dateFont text-danger font-weight-bold d-flex justify-content-end mr-2">
												${game.day}
											</span>
										</td>
									</tr>
									</c:if>
									
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>