<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="boardListViewContent">
	<div class="boardBox1 d-flex align-items-center justify-content-center">
		<div class="boardBox2 d-flex align-items-center justify-content-center">
			<div class="boardbox3">
				<div class="d-flex justify-content-center mb-4">
					<div class="detailTitleBox">
						<div class="detailTitle d-flex align-items-center justify-content-center">
							<c:if test="${boardKind eq 1 }">
							<span>공지사항</span>
							</c:if>
							<c:if test="${boardKind eq 2 }">
							<span>구단뉴스</span>
							</c:if>
							<c:if test="${boardKind eq 3 }">
							<span>팬 게시판</span>
							</c:if>
							<c:if test="${boardKind eq 4 }">
							<span>사진 게시판</span>
							</c:if>
							<c:if test="${boardKind eq 5 }">
							<span>건의사항 게시판</span>
							</c:if>
						</div>
					</div>
				</div>
				<div class="d-flex justify-content-center">
					<table class="boardTable text-center">
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
								<th>조회수</th>
								<th>추천수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="post" items="${postList }" varStatus="status">
							<tr class="boardTableSize">
								<td>${fn:length(postList) - status.index }</td>
								<td><a href="/board/board_view?boardId=${post.board.id }" class="boardTitle" data-board-id="${post.board.id }">${post.board.title }</a></td>
								<td>${post.board.userName }</td>
								<td><fmt:formatDate value="${post.board.createdAt }" pattern="yyyy.MM.dd."/></td>
								<td>${post.board.views }</td>
								<td>${post.recommend }</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="text-right m-3">
					<button class="btn writeBtn form-control" data-login-id="${loginId }" data-board-kind="${boardKind }">글쓰기</button>
				</div>
				<div class="d-flex justify-content-center">
					<c:if test="${prevId ne 0 }">
					<a href="/board/notice_view?prevId=${prevId }"><button class="btn prevAndNextBtn">이전</button></a>
					</c:if>
					<c:if test="${nextId ne 0 }">
					<a href="/board/notice_view?nextId=${nextId }"><button class="btn prevAndNextBtn">다음</button></a>
					</c:if>
				</div>
				<%-- 
				<c:if test="${loginId eq null}">
				<div class="text-right m-3">
					<input type="button" class="btn notWriteBtn form-control" value="로그인 후 글쓰기 가능합니다.">
				</div>
				</c:if>
				--%>
			</div>
		</div>
	</div>
</div>
<script>
	$(document).ready(function(){
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
		
		<%-- 글쓰기 클릭시 로그인 여부 확인 --%>
		$('.writeBtn').on('click', function(e){
			let loginId = $(this).data('login-id');
			let boardKind = $(this).data('board-kind');
			if(loginId==''){
				alert("로그인을 해주세요.");
				return;
			}
			location.href="/board/create_view?boardKind="+boardKind;
		});
	});
</script>