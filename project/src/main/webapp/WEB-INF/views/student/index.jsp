<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
function insert(){
	var param = 
	{
			"id" : $("#id").val(),
			"name" : $("#name").val(),
			"age" : $("#age").val()
	};
	
	$.ajax({
		url:'/project/student/insert',
		method : 'post',
		data : JSON.stringify(param), 
		dataType : 'json',
		contentType:'application/json',
		success : function(res) {
			console.log(res);
			if (res['_id']) {
				alert(`정상적으로 등록되었습니다`);
			}
		},
		error:function(res){
			console.log(res);
		}
	});
}
function search(){
	var param = {
			"sword" : $("#sword").val()
	};
	$.ajax({
		url : '/project/student/list',
		method : 'post',
		data : JSON.stringify(param),
		dataType : 'json',
		contentType : 'application/json',
		success : function(res){
			console.log(res);
			var html = '';
			$.each(res,function(i,v){
				console.log(i,v)
				html += v['id'] + ' ' + v['name'] + ' ' + v['age'] + '<br>';
			});
			$('#listArea').html(html);
		}
	})
}
</script>
</head>
<body>
아이디 : <input type="text" id="id"><br>
이름 : <input type="text" id="name"><br>
나이 : <input type="text" id="age"><br>
<input type="button" value="저장" onclick="insert();"><!-- ajax안에서 json포맷으로 바꿔서 넘겨야 requestbody 에 담김.  -->
<br>
<input type="text" id="sword"><input type="button" value="검색" onclick="javascript:search();"><br>
<div id="listArea"></div>
</body>
</html>