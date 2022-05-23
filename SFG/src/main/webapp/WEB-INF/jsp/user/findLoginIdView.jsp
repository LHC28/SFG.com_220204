<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="signInViewContent d-flex justify-content-center">
	<div id="loginPage" class="d-flex align-items-center justify-content-center">
		<div id="loginBox0" class="d-flex align-items-center justify-content-center">
			<div id="loginBox1" class="d-flex align-items-center justify-content-center">
				<div id="loginBox2" class="d-flex justify-content-center">
					<div>
						<div class="text-center loginTitle mt-1">아이디 찾기</div>
						<div class="loginInputTitle mt-2">이름</div>
						<input type="text" id="name" class="form-control loginInput">
						<div class="loginInputTitle mt-3">이메일</div>
						<input type="text" id="email" class="form-control">
						<div class="d-flex justify-content-center mt-4">
							<input type="button" class="btn findLoginIdBtn" value="아이디 찾기">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	$(document).ready(function(){
		$('.findLoginIdBtn').on('click', function(){
			// 이름 및 이메일 유효성 검사
			var name = $('#name').val();
			if(name==''){
				alert("이름을 입력해주세요.");
				return;
			}
			
			var email = $('#email').val();
			if(email==''){
				alert("이메일을 입력해주세요.");
				return;
			}
			
			// 이름과 이메일을 활용하여 아이디 알려주기
			$.ajax({
				url: '/user/find_loginId'
				,type: 'post'
				,data: {"name":name, "email":email}
				,success: function(data){
					if(data.result=="success"){
						alert("아이디는 "+data.loginId+"입니다.");
						location.href = "/user/sign_in_view";	
					}
					else if(data.result=="fail"){
						alert("이름이나 이메일을 확인해주세요.");
					}
				},
				error:function(request,status,error){
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
			
			
		});
	});
</script>