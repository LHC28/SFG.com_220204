<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="boardCreateContent">
	<div class="boardCreateBox1 d-flex align-items-center justify-content-center">
		<div class="boardCreate2 d-flex align-items-center justify-content-center">
			<div class="boardCreatebox3">
				<div class="d-flex justify-content-center mb-4">
					<div class="boardCreateTitleBox">
						<div class="boardCreateTitle d-flex align-items-center justify-content-center">
						<c:choose>
							<c:when test="${boardKind eq 1 }">
							<span>공지사항 글쓰기</span>
							</c:when>
							<c:when test="${boardKind eq 2 }">
							<span>구단뉴스 글쓰기</span>
							</c:when>
							<c:when test="${boardKind eq 3 }">
							<span>팬게시판 글쓰기</span>
							</c:when>
							<c:when test="${boardKind eq 4 }">
							<span>사진 게시판 글쓰기</span>
							</c:when>
							<c:when test="${boardKind eq 5 }">
							<span>건의게시판 글쓰기</span>
							</c:when>
						</c:choose>
						</div>
					</div>
				</div>
				<div class="d-flex justify-content-center">
					<div>
						<textarea class="form-control" id="uploadTitleTextarea" cols="100" rows="1" placeholder="제목을 입력해주세요."></textarea>
						<textarea class="form-control mt-2" id="uploadContentTextarea" cols="100" rows="15" placeholder="내용을 입력해주세요."></textarea>
						<div class="d-flex justify-content-end align-items-center">
							<div id="fileName" class="mr-2"></div>
							<a href="" class="imageUploadBtn"><img width="50" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"></a>
						</div>
						<%-- 업로드 버튼 클릭시 d-none 비활성화. --%>
						<form id="uploadForm" method="post" action="/board_create" enctype="multipart/form-data">
							<input type="file" name="images" multiple="multiple" class="d-none" accept=".jpg,.jpeg,.png,.gif">
						</form>
						<div  class="d-flex justify-content-end">
							<button type="button" class="btn boardCreateBtn" value="${boardKind}">등록하기</button>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>
<script>
	$(document).ready(function(){
		// 이미지 추가 버튼 클릭시
		$('.imageUploadBtn').on('click', function(e){
			e.preventDefault();
			// 이미지 버튼을 누르면 파일 업로드 창이 생김
			$('input[name=images]').click();
		});
		
		var inputFileList = new Array();
		
		$('input[name=images]').on('change',function(e){
			
			let fileList = $(this)[0].files;
			var files = e.target.files;
			var filesArr = Array.prototype.slice.call(files);
			
			filesArr.forEach(function(f){
		    	var reader = new FileReader();
		    	reader.onload = function(e){
		    		console.log("onload.."+ f);
		    		inputFileList.push(f);
		    	}
		    	reader.readAsDataURL(f);
		    })
		    
		    let name="";
			for(var i=0;i < fileList.length;i++){
		        var file = fileList[i];
		     	// 확장자 검사
		        let extention = file.name.split(".");
				if(extention.length<2 || (extention[extention.length-1] != 'gif'
					&& extention[extention.length-1] != 'png'
					&& extention[extention.length-1] != 'jpg'
					&& extention[extention.length-1] != 'jpeg')){
					alert("이미지 파일만 업로드할 수 있습니다.");
					$(this).val(''); // input에 들어간 파일을 없애주는 역할.
					return;
				}
				
				// 파일간 이름 구분용 ,추가
				if(i<fileList.length-1){
					name+=file.name+", ";	
				}else{
					name+=file.name;
				}
		        
		    }
			$('#fileName').text(name); // 이미지 파일 등록시 div안에 name을 가져온다.
		});
		
		// 등록하기 버튼 클릭
		$('.boardCreateBtn').on('click', function(e){
			
			// 게시판 id값 가져오기
			let boardKind = $('.boardCreateBtn').val();
			
			let title = $('#uploadTitleTextarea').val();
			if(title==''){
				alert("제목을 입력해주세요.");
				return;
			}
			
			let content = $('#uploadContentTextarea').val();
			if(content==''){
				alert("내용을 입력해주세요.");
				return;
			}
			
			var formData = new FormData();
			
			// boardId값
			formData.append("boardKind", boardKind);
			
			for(let i=0; i<inputFileList.length; i++){
				// 이미지 값
				formData.append("images", inputFileList[i]);
			}
			
			// title값
			formData.append("title", title);
			
			// content값
			formData.append("content",content);
			
			$.ajax({
				type: "post",
				url: "/board/upload_post",
				data: formData,
				enctype: 'multipart/form-data',
				processData: false,
				contentType: false,
				success: function(data){
					if(data.result=="success"){
						<%-- 글쓰기 직전 게시판의 boarKind를 활용하여 페이지 이동 --%>
						if(boardKind==1){
							location.href="/board/notice_view"
						}else if(boardKind==2){
							location.href="/board/news_view"
						}else if(boardKind==3){
							location.href="/board/fan_view"
						}else if(boardKind==4){
							location.href="/board/picture_view"
						}else if(boardKind==5){
							location.href="/board/suggest_view"
						}
					}else{
						alert("관리자에게 문의해주세요.");
					}
				},
				error: function(e){
					alert("error : "+e);
				}
			})
		});
	});
</script>