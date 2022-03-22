<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex">
<%-- 관리자 페이지 nav --%>
	<jsp:include page="../include/adminNav.jsp"></jsp:include>
	<section class="adminSection p-3">
		<a class="editor-create">+ Create new record</a>
		<table id="userList">
			<thead>
				<tr>
					<th>loginId</th>
					<th>name</th>
					<th>email</th>
					<th>createdAt</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>loginId</th>
					<th>name</th>
					<th>email</th>
					<th>createdAt</th>
					<th></th>
					<th></th>
				</tr>
			</tbody>
		</table>
	</section>
</div>
<script>
var editor;
$(document).ready(function(){
	editor = new $.fn.dataTable.Editor({
		ajax: "/user/get_all_user",
		table: "#userList",
		// idSrc가 없으면 해당 row의 식별자가 없기 때문에 수정 및 삭제 버튼 클릭시 에러가 발생한다.
		idSrc:  'id',
		fields: [
			{
				label: "아이디",
				name: "loginId"
			},{
				label: "이름",
				name: "name"
			},{
				label: "이메일",
				name: "email"
			},{
				label: "생성일",
				name: "createdAt",
			}
		]
	});
	
	editorDelete = new $.fn.dataTable.Editor({
		ajax: "/user/delete_user",
		table: "#userList",
		// idSrc가 없으면 해당 row의 식별자가 없기 때문에 수정 및 삭제 버튼 클릭시 에러가 발생한다.
		idSrc:  'id',
		data: [
			{
				label: "아이디",
				name: "loginId"
			},{
				label: "이름",
				name: "name"
			},{
				label: "이메일",
				name: "email"
			},{
				label: "생성일",
				name: "createdAt",
			}
		],
		fields: [
			{
				label: "아이디",
				name: "loginId"
			},{
				label: "이름",
				name: "name"
			},{
				label: "이메일",
				name: "email"
			},{
				label: "생성일",
				name: "createdAt",
			}
		]
	});
	

	$('a.editor-create').on('click', function (e) {
        e.preventDefault();
 
        editor.create( {
            title: 'Create new record',
            buttons: 'Add'
        } );
    } );
	
	// Edit record
    $('#userList').on('click', 'td.editor-edit', function (e) {
        e.preventDefault();
        editor.edit( $(this).closest('tr'), {
            title: 'Edit record',
            buttons: 'Update'
        } );
    } );
 
    // Delete a record
    $('#userList').on('click', 'td.editor-delete', function (e) {
        e.preventDefault();
 		
        editorDelete.remove( $(this).closest('tr'), {
            title: 'Delete record',
            message: 'Are you sure you wish to remove this record?',
            buttons: 'Delete'
        } );
    } );
    
	// 이전에 만든 것 없애고 다시 그리기 위한 기능
	// $("#userList").DataTable().destroy();
	
	var userList = $("#userList").DataTable({
		responsive: false,  //반응형 설정
        pageLength: 10,     //페이지 당 글 개수 설정
        autoWidth: false,
        destroy: true,
        processing: true,
        serverSide: false,
        searching: false,    //검색란 표시 설정
        ordering: true,      //글 순서 설정
        paging: true,        //페이징 표시 설정
        dom: "Blfrtip",      //버튼 dom 설정 l을 추가하면 pagelength 드롭다운 표시
         
        language: {
			emptyTable: "데이터가 없습니다.",
            lengthMenu: "페이지당 _MENU_ 개씩 보기",
            info: "현재 _START_ - _END_ / _TOTAL_건",
            infoEmpty: "데이터 없음",
            infoFiltered: "( _MAX_건의 데이터에서 필터링됨 )",
            search: "",
            zeroRecords: "일치하는 데이터가 없습니다.",
            loadingRecords: "로딩중...",
            processing: "잠시만 기다려 주세요.",
            paginate: {
              next: "다음",
              previous: "이전",
            },
          },
		
        ajax:{
        	url:"/user/get_all_user",
        	type:"GET",
        	dataSrc :''
        },
        columns:[
        	{data:"loginId"},
        	{data:"name"},
        	{data:"email"},
        	{data:"createdAt"},
        	{
                data: null,
                className: "dt-center editor-edit",
                defaultContent: '<i class="fa fa-pencil"/>',
                orderable: false
            },
            {
                data: null,
                className: "dt-center editor-delete",
                defaultContent: '<i class="fa fa-trash"/>',
                orderable: false
            }
        ],
        "columnDefs": [
        	{
        		"type" : 'datetime',
        		"render": function(data, type, rows){
        			if(type==="sort" || type === "type"){
        				return data;
        			}
        			var createdAt = "";
        			if(data!=null){
        				createdAt = moment(data).format("YYYY-MM-DD HH:mm:ss")
        			}
        			return createdAt;
        		},
        		"targets":3 // 변경하는 column의 위치를 말하는듯. 없으면 바뀌지 않는다.
        	}
        ]
	});
	
});
</script>