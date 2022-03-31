<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="content">
	<div class="detailPlayerBox1 d-flex align-items-center justify-content-center">
		<div class="detailPlayerBox2 d-flex align-items-center justify-content-center">
			<div class="detailPlayerbox3">
				<div class="d-flex justify-content-center mb-4">
					<div class="detailTitleBox">
						<div class="detailTitle d-flex align-items-center justify-content-center">
							<span>경기일정</span>
						</div>
					</div>
				</div>
				<div class="d-flex justify-content-center">
					<div class="matchScheduleBox">
						<%-- 연월 및 방향버튼 --%>
						<div class="matchScheduleBoxTitle d-flex justify-content-center mb-4">
							<a href="" class="mr-3">&lt;</a>
							<span>2021. 8.</span>
							<a href="" class="ml-3">&gt;</a>
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
									<%--  --%>
									<c:if test="${status.count%7 ==1 && game ne null}">
									<tr>
										<td>
											<span class="dateFont d-flex justify-content-end mr-2">
												${status.count }
											</span>
											<div>
												<div class="d-flex justify-content-center">
													<img src="/static/images/sfglogo.png" alt="구단로고" width="50px" height="50px">
												</div>
												<c:if test="${game.time ne null }">
												<div class="text-center mt-2">${game.time }</div>
												</c:if>
												<c:if test="">
												<div class="text-center mt-2">시간 미정</div>
												</c:if>
												<c:if test="${game.homeScore ne null && game.awayScore ne null }">
												<div class="text-center">${game.result} ${game.homeScore }:${game.awayScore }</div>
												</c:if>
												<br>
											</div>
										</td>
									</c:if>
									
									<c:if test="${status.count%7==1 && game eq null }">
									<tr>
										<td>
											<span class="dateFont d-flex justify-content-end mr-2">
												${status.count }
											</span>
										</td>
									</c:if>
									
									<c:if test="${status.count%7>1 && status.count%7<=6 && game ne null}">
										<td>
											<span class="dateFont d-flex justify-content-end mr-2">
												${status.count }
											</span>
											<div>
												<div class="d-flex justify-content-center">
													<img src="/static/images/sfglogo.png" alt="구단로고" width="50px" height="50px">
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
												<br>
											</div>
										</td>
									</c:if>
									<c:if test="${status.count%7>1 && status.count%7<=6 && game eq null}">
										<td>
											<span class="dateFont d-flex justify-content-end mr-2">
												${status.count }
											</span>
										</td>
									</c:if>
									
									<c:if test="${status.count%7==0 && game ne null }">
										<td>
											<span class="dateFont d-flex justify-content-end mr-2">
												${status.count }
											</span>
											<div>
												<div class="d-flex justify-content-center">
													<img src="/static/images/sfglogo.png" alt="구단로고" width="50px" height="50px">
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
												<br>
											</div>
										</td>
									</tr>
									</c:if>
									
									<c:if test="${status.count%7==0 && game eq null }">
										<td>
											<span class="dateFont d-flex justify-content-end mr-2">
												${status.count }
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