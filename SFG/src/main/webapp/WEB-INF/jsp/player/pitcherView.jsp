<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="pitcherContent">
	<div class="pitcherBox1 d-flex align-items-center justify-content-center">
		<div class="pitcherBox2 d-flex align-items-center justify-content-center">
			<div class="pitcherbox3">
				<div class="d-flex justify-content-center mb-4">
					<div class="introduceTitleBox">
						<div class="introduceTitle d-flex align-items-center justify-content-center">
							<span>투수소개</span>
						</div>
					</div>
				</div>
				<div class="d-flex justify-content-center">
					<div class="pitcherBox d-flex flex-wrap mb-4">
						<%-- player --%>
						<c:forEach var="pitcher" items="${pitcherList }" varStatus="status">
						<div class="player ml-2 mr-2">
							<table class="playerTable text-center d-flex justify-content-center align-items-center">
								<tr>
									<td>${pitcher.position}</td>
								</tr>
								<tr>
									<td>
										<a href="/player/player_detail_view?playerId=${pitcher.id}"><img src="${pitcher.imagePath}" alt="투수" width="150px;" height="180px;"></a>
									</td>
								</tr>
								<tr>
									<td>${pitcher.number }. ${pitcher.name}</td>
								</tr>
							</table>
						</div>
						</c:forEach>
						<%-- 칸이 남을 땐 안 보이도록 하여 한줄에 4개를 채운다. --%>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>