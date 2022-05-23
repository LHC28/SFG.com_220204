<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="signInViewContent d-flex justify-content-center">
	<div id="loginPage" class="d-flex align-items-center justify-content-center">
		<div id="loginBox0" class="d-flex align-items-center justify-content-center">
			<div id="loginBox1" class="d-flex align-items-center justify-content-center">
				<div id="loginBox2" class="d-flex justify-content-center">
					<div>
						<div id="findPasswordBox">
							<div class="text-center loginTitle mt-1">비밀번호 찾기</div>
							<div class="loginInputTitle mt-2">이름</div>
							<input type="text" id="name" class="form-control loginInput">
							<div class="loginInputTitle mt-2">아이디</div>
							<input type="text" id="loginId" class="form-control loginInput">
							<div class="loginInputTitle mt-3">이메일</div>
							<input type="text" id="email" class="form-control">
							<div class="d-flex justify-content-center mt-4">
								<input type="button" class="btn findPasswordIdBtn" value="비밀번호 찾기">
							</div>
						</div>
						<div id="setNewPasswordBox" class="d-none">
							<div class="text-center loginTitle mt-1">비밀번호 설정</div>
							<div class="loginInputTitle mt-2">비밀번호 입력</div>
							<input type="password" class="password form-control loginInput">
							<div class="loginInputTitle mt-2">비밀번호 재입력</div>
							<input type="password" class="confirmPassword loginInput form-control">
							<div class="d-flex justify-content-center mt-4">
								<input type="button" class="btn setNewPasswordBtn" value="비밀번호 재설정">
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
		// 전역변수로 활용
		var sendLoginId = null;
		
		// 비밀번호 찾기 클릭시
		$('.findPasswordIdBtn').on('click', function(){
			var name = $('#name').val();
			if(name==''){
				alert("이름을 입력하세요.");
				return;
			}
			
			var loginId = $('#loginId').val();
			if(loginId==''){
				alert("아이디를 입력하세요.");
				return;
			}
			
			var email = $('#email').val();
			if(email==''){
				alert("이메일을 입력하세요.");
				return;
			}
			
			$.ajax({
				url: '/user/find_password'
				,type: 'post'
				,data: {"name":name, "loginId":loginId, "email":email}
				,success: function(data){
					if(data.result=='success'){
						sendLoginId = data.loginId;
						$('#findPasswordBox').addClass('d-none');
						$('#setNewPasswordBox').removeClass('d-none');
					}else if(data.result=='fail'){
						alert("입력하신 값을 다시 확인해주시기 바랍니다.");
					}
				},
				error:function(request,status,error){
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
			
		});
		
		// 비밀번호 재설정
		$('.setNewPasswordBtn').on('click', function(){
			// 유효성 검사
			var password = $('.password').val();
			if(password==''){
				alert("비밀번호를 입력해주세요.");
				return;
			}
			if(password.length < 10){
				alert("비밀번호를 10자리 이상 입력해주세요.");
				return;
			}
			var confirmPassword = $('.confirmPassword').val();
			if(confirmPassword==''){
				alert("비밀번호 재입력을 입력해주세요.");
				return;
			}
			
			if(password != confirmPassword){
				alert("비밀번호와 비밀번호 재입력이 다릅니다. 확인해주세요.");
				return;
			}
			
			$.ajax({
				url: '/user/change_password'
				,type: 'post'
				,data: {"loginId":sendLoginId, "password":password}
				,success: function(data){
					if(data.result=='success'){
						alert("비밀번호 설정이 완료되었습니다.");
						location.href="/user/sign_in_view";
					}
				},
				error:function(request,status,error){
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
		})
	});
</script>