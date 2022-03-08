<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content">
	<div class="boardviewBox1 d-flex align-items-center justify-content-center">
		<div class="boardviewBox2 d-flex align-items-center justify-content-center">
			<div class="boardviewbox3">
				<div class="d-flex justify-content-center mb-4">
					<div class="boardViewTitleBox">
						<div class="boardViewTitle d-flex align-items-center justify-content-center">
							<span>게시글</span>
						</div>
					</div>
				</div>
				<div class="d-flex justify-content-center">
					<div class="boardViewContentBox">
						<div class="boardViewHead d-flex">
							<%-- 작성자 --%>
							<div class="boardViewHeadUserName d-flex align-items-center justify-content-center">${post.board.userName }</div>
							<%-- 제목 --%>
							<div class="boardViewHeadTitle d-flex align-items-center justify-content-center">${post.board.title }</div>
							<%-- 조회수 --%>
							<div class="boardViewHeadViews d-flex align-items-center justify-content-center">조회수 : ${post.board.views }</div>
							<%-- 추천수 --%>
							<div class="boardViewHeadRecommend d-flex align-items-center justify-content-center">추천수 : ${post.recommend }</div>
						</div>
						<div class="mt-3">
							<div class="d-flex flex-wrap justify-content-between">
								<c:forEach var="file" items="${post.fileList }" varStatus="status">
								<img src="${file.imagePath }" alt="게시물 사진" width="280px" height="280px">
								</c:forEach>
							</div>
							<div class="boardViewContent m-2 mt-3">${post.board.content }</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>