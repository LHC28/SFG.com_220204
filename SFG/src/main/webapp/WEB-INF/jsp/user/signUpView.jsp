<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="signUpBox1" class="d-flex align-items-center justify-content-center">
	<div id="signUpBox2" class="d-flex align-items-center justify-content-center">
		<div id="signUpBox3" class="d-flex align-items-center justify-content-center">
			<div id="signUpBox4" class="d-flex align-items-center justify-content-center">
				<div id="signUpBox5" class="d-flex justify-content-center">
					<div>
						<div id="signUpBoxTitle" class="text-center">회원 가입</div>
						<div id="signUpBox" class="mt-1">
							<div>
								<div>아이디</div>
								<div class="d-flex">
									<input type="text" id="loginId" class="form-control">
									<button class="btn btn-success duplicatedIdBtn ml-2">중복확인</button>
								</div>
								<div class="duplicatedLoginId mt-1 d-none">아이디가 중복됩니다.</div>
								<div class="possibleLoginId mt-1 d-none">이용가능한 아이디입니다.</div>
							</div>
							<div class="mt-2">
								<div>비밀번호</div>
								<input type="password" id="password" class="form-control">
							</div>
							<div class="mt-2">
								<div>이름</div>
								<input type="text" id="name" class="form-control">
							</div>
							<div class="mt-2">
								<div>이메일</div>
								<div class="d-flex">
									<input type="text" id="email" class="form-control">
									<button class="btn btn-primary checkEmailBtn ml-2">메일 확인</button>
								</div>
							</div>
							<div class="mt-2 emailCheckBox d-none">
								<div>인증번호 입력</div>
								<div class="d-flex">
									<input type="text" id="emailCheck" class="form-control" placeholder="인증번호를 입력해주세요.">
									<button class="btn btn-primary checkEmailBtn2 ml-2">확인</button>
								</div>
								<div class="impossibleEmail mt-1 d-none">이메일 인증 실패</div>
								<div class="possibleEmail mt-1 d-none">이메일 인증 성공</div>
							</div>
							<%-- 버튼을 입력하면 유효성 검사 후 로그인 화면으로 이동 --%>
							<input type="button" class="btn signUpBtn w-100 mt-3" value="회원가입하기">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	$(document).ready(function(){
		
		// 이메일 인증시 인증번호 저장하는 변수
		var cerNum = '';
		
		// loginId 중복확인 버튼 클릭시
		$('.duplicatedIdBtn').on('click',function(){
			var loginId = $('#loginId').val();
			if(loginId==''){
				alert("아이디를 입력해주세요.");
				return;
			}
			
			$.ajax({
				type: "post",
				url: "/user/duplicated_id",
				data: {"loginId":loginId},
				success: function(data){
					if(data.result=="success"){
						$('.possibleLoginId').removeClass("d-none");
						$('.duplicatedLoginId').addClass("d-none");
					}else if(data.result=="fail"){
						$('.possibleLoginId').addClass("d-none");
						$('.duplicatedLoginId').removeClass("d-none");
					}
				},
				error: function(e){
					alert("error : "+e);
				}
			});
		});
		
		// 메일 확인 버튼 클릭시
		$('.checkEmailBtn').on('click', function(){
			var email = $('#email').val();
			if(email==''){
				alert("이메일을 입력하세요.");
				return;
			}
			if($('.emailCheckBox').hasClass('d-none')){
				$('.emailCheckBox').removeClass('d-none');
			}
			// 인증번호 만들기
			$.ajax({
				url: "/user/email_authentication"
				,type: "post"
				,data: {"email":email}
				,success: function(data){
					cerNum = data.num;
				},
				error:function(request,status,error){
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
		});
		
		// 이메일 확인의 확인 버튼 클릭시
		$('.checkEmailBtn2').on('click', function(){
			var num = $('#emailCheck').val();
			
			if(num==''){
				alert("인증번호를 입력해주세요.");
				return;
			}
			
			// 인증번호와 입력한 번호가 같은 경우
			if(cerNum==num){
				$('.possibleEmail').removeClass('d-none');
				$('.impossibleEmail').addClass('d-none');
			}
			// 인증번호와 입력한 번호가 다른 경우
			else if(cerNum!=num){
				$('.impossibleEmail').removeClass('d-none');
				$('.possibleEmail').addClass('d-none');
			}
		});
		
		// 회원가입 버튼 클릭시
		$('.signUpBtn').on('click',function(){
			
			// 유효성 검사
			var loginId = $('#loginId').val();
			if(loginId==''){
				alert("아이디를 입력하세요.");
				return;
			}
			
			var password = $('#password').val();
			if(password==''){
				alert("비밀번호를 입력하세요.");
				return;
			}
			
			var name = $('#name').val();
			if(name==''){
				alert("이름을 입력하세요.");
				return;
			}
			
			var email = $('#email').val();
			if(email==''){
				alert("이메일을 입력하세요.");
				return;
			}
			
			if($('.possibleLoginId').hasClass("d-none")){
				alert("아이디 중복여부를 확인하세요.");
				return;
			}
			
			if($('.possibleEmail').hasClass('d-none')){
				alert("이메일 인증여부를 확인해주세요.");
				return;
			}
			
			$.ajax({
				type: "post",
				url: "/user/sign_up",
				data: {"loginId":loginId, "password":password, "name":name, "email":email},
				success: function(data){
					if(data.result=="success"){
						alert("회원가입에 성공하였습니다.");
						location.href="/user/sign_in_view"
					}
				},
				error: function(e){
					alert("error : "+e);
				}
				
			});
		});
	});
</script>