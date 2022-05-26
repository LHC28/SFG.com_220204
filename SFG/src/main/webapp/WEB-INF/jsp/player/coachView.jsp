<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="couchContent">
	<div class="couchBox1 d-flex align-items-center justify-content-center">
		<div class="couchBox2 d-flex align-items-center justify-content-center">
			<div class="couchbox3">
				<div class="d-flex justify-content-center mb-4">
					<div class="introduceTitleBox">
						<div class="introduceTitle d-flex align-items-center justify-content-center">
							<span>코칭스태프</span>
						</div>
					</div>
				</div>
				<div class="d-flex justify-content-center">
					<div class="couchBox d-flex flex-wrap mb-4">
						<%-- player --%>
						<c:forEach var="coach" items="${coachList }" varStatus="status">
						<div class="couch">
							<table class="couchTable text-center d-flex justify-content-center align-items-center">
								<tr>
									<c:if test="${fn:length(coach.position) ge 15 }">
									<td>${coach.position}</td>
									</c:if>
									<c:if test="${fn:length(coach.position) lt 15 }">
									<td>${coach.position}</td>
									</c:if>
								</tr>
								<tr>
									<td>
										<img src="${coach.imagePath}" alt="코치" width="150px;" height="180px;">
									</td>
								</tr>
								<tr>
									<td>${coach.number}. ${coach.name}</td>
								</tr>
							</table>
						</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>