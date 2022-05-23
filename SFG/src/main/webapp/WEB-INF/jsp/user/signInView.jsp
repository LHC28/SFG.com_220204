<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="signInViewContent d-flex justify-content-center">
	<div id="loginPage" class="d-flex align-items-center justify-content-center">
		<div id="loginBox0" class="d-flex align-items-center justify-content-center">
			<div id="loginBox1" class="d-flex align-items-center justify-content-center">
				<div id="loginBox2" class="d-flex justify-content-center">
					<div>
						<div class="text-center loginTitle mt-1">LOGIN</div>
						<div class="loginInputTitle mt-2">아이디</div>
						<input type="text" id="loginId" class="form-control loginInput">
						<div class="loginInputTitle mt-3">비밀번호</div>
						<input type="password" id="password" class="form-control">
						<div class="d-flex justify-content-center mt-4">
							<input type="button" class="btn loginBtn" value="로그인">
						</div>
						<div class="d-flex justify-content-center">
							<div class="loginFindBox d-flex align-items-center justify-content-between mt-4">
								<a href="/user/find_loginId">아이디 찾기</a>
								<a href="/user/find_password">비밀번호 찾기</a>
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
		// 로그인
		$('.loginBtn').on('click',function(){
			// 유효성 검사
			let loginId = $('#loginId').val();
			if(loginId==''){
				alert("아이디를 입력해주세요.");
				return;
			}
			
			let password = $('#password').val();
			if(password==''){
				alert("비밀번호를 입력해주세요.");
				return;
			}
			
			$.ajax({
				type: "post",
				url: "/user/sign_in",
				data: {"loginId":loginId, "password":password},
				success: function(data){
					if(data.result=="success"){
						location.href="/main/main_page"
					}else if(data.result=="fail"){
						alert("아이디 또는 비밀번호가 틀렸습니다.");
					}
				},
				error: function(e){
					alert("error : "+e);
				}
			});
		});
	});
</script>