<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="content">
	<div class="boardBox1 d-flex align-items-center justify-content-center">
		<div class="boardBox2 d-flex align-items-center justify-content-center">
			<div class="boardbox3">
				<div class="d-flex justify-content-center mb-4">
					<div class="detailTitleBox">
						<div class="detailTitle d-flex align-items-center justify-content-center">
							<span>공지사항</span>
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
								<td>${post.board.id }</td>
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
					<a href="/board/create_view?boardKind=${boardKind }"><button class="btn writeBtn form-control">글쓰기</button></a>
				</div>
				<div class="d-flex justify-content-center">
					<c:if test="${prevId ne 0 }">
					<a href="/board/notice_view?prevId=${prevId }">&lt;&lt; 이전</a>
					</c:if>
					<c:if test="${nextId ne 0 }">
					<a href="/board/notice_view?nextId=${nextId }">다음 &gt;&gt;</a>
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