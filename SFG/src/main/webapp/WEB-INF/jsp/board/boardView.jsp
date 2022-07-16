<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="boardContent">
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
							<div class="boardViewHeadTitle d-flex align-items-center justify-content-center">
								${post.board.title }
								<%-- 삭제 버튼 --%>
								<c:if test="${userId ne null && post.board.userId eq userId}">
								<a href=""><img src="/static/images/button/deleteBtn.png" class="deleteBoardBtn ml-2" alt="삭제 버튼" width="20px" height="20px" data-board-id="${post.board.id }" data-user-id="${userId }"></a>
								</c:if>
							</div>
							<%-- 조회수 --%>
							<div class="boardViewHeadViews d-flex align-items-center justify-content-center">조회수 : ${post.board.views }</div>
							<%-- 추천수 --%>
							<div class="boardViewHeadRecommend d-flex align-items-center justify-content-between">
								<span>추천수 : ${post.recommend }</span>
								<c:if test="${userId eq null }">
								<a href="" onclick="return false" class="recommendBtn" data-user-id="${userId }" data-board-id="${post.board.id}"><img src="/static/images/button/smile.png" alt="추천 버튼" width="30px" height="30px" class="mr-2"></a>
								</c:if>
								<c:if test="${recommendCheck eq false}">
								<a href="" onclick="return false" class="recommendBtn" data-user-id="${userId }" data-board-id="${post.board.id}"><img src="/static/images/button/smile.png" alt="추천 버튼" width="30px" height="30px" class="mr-2"></a>
								</c:if>
								<c:if test="${recommendCheck eq true }">
								<a href="" onclick="return false" class="recommendBtn" data-user-id="${userId }" data-board-id="${post.board.id}"><img src="/static/images/button/grin.png" alt="추천 버튼" width="30px" height="30px" class="mr-2"></a>
								</c:if>
							</div>
						</div>
						<div class="mt-3">
							<div class="d-flex flex-wrap justify-content-center">
								<c:forEach var="file" items="${post.fileList }" varStatus="status">
								<img class="mb-1" src="${file.imagePath }" alt="게시물 사진" width="800px" height="500px">
								</c:forEach>
							</div>
							<div class="boardViewContent m-2 mt-3 mb-5">${post.board.content }</div>
							<%-- 게시글과 댓글 사이 구분선 --%>
							<hr class="commentContour">
							<%-- 댓글 --%>
							<div class="commentBox">
								<div class="d-flex mb-2">
									<input type="text" class="postComment form-control">
									<button id="postCommentBtn" class="btn" data-user-id="${userId }" data-post-id=${post.board.id }>등록</button>
								</div>
								<table class="w-100 text-center">
									<tr>
										<td class="w-25">작성자</td>
										<td class="w-50">내용</td>
										<td class="w-25">작성시간</td>
									</tr>
									<c:forEach var="comment" items="${commentList }" varStatus="status">
									<tr>
										<%-- 댓글 들어갈 위치 --%>
										<td>${comment.userName }</td>
										<td>${comment.content }</td>
										<td>${comment.updatedAt }</td>
									</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	$(document).ready(function(){
		var referrer = document.referrer;
		
		$('.recommendBtn').on('click', function(e){
			let userId = $(this).data("user-id");
			let boardId = $(this).data("board-id");
			
			<%-- 비로그인 상태인 경우 --%>
			if(userId==""){
				alert("로그인 후 추천 버튼을 눌러주세요.");
				return;
			}
			
			$.ajax({
				type: "post"
				,url: "/recommend/recommend_click"
				,data: {"userId":userId, "boardId":boardId}
				,success: function(data){
					if(data.result=="success"){
						location.reload();
					}
				},error: function(e){
					alert("error : "+e);
				}
			});
		});
		
		$('.deleteBoardBtn').on('click', function(){
			// 게시글 id
			var boardId = $(this).data('board-id');
			// 유저 id
			var userId = $(this).data('user-id');
			
			$.ajax({
				url: '/post/delete_post'
				,type: 'post'
				,data: {"boardId":boardId, "userId":userId}
				,success: function(data){
					if(data.result=='success'){
						location.href=referrer;
					}
				},error:function(request,status,error){
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
			
		});
		
		// 댓글 달기
		$('#postCommentBtn').on('click', function(){
			var content = $('.postComment').val();
			var postId = $(this).data('post-id');
			
			// 유효성 검사
			if(content==''){
				alert("댓글 내용을 입력해주세요.");
				return;
			}
			
			if(postId==''){
				alert("에러가 발생하였습니다. 관리자에게 문의해주세요.");
				return;
			}
			

			// 로그인 여부 확인
			var userId = $(this).data('user-id');
			if(userId==''){
				alert("로그인 후 등록해주세요.");
				return;
			}
			
			$.ajax({
				url: '/comment/create_comment'
				,type: 'post'
				,data: {'userId':userId, 'postId':postId, 'content': content}
				,success: function(data){
					if(data.result=="success"){
						alert("댓글 등록에 성공하였습니다.");
						location.reload();
					}
				},error:function(request,status,error){
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
		});
	});
</script>