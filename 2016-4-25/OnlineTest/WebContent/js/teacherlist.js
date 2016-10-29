var academySelect = "";
var male = "1001";
var female = "1002";
var sec_gender = "1003";
function lowerIe(){
	var UA = navigator.userAgent;
	var ieV = "";
	if(/msie/i.test(UA)){
		ieV = UA.match(/msie (\d+\.\d+)/i)[1];
	}
	if(parseInt(ieV) <= 8){
		return true;
	}
	return false;
}

function load(s){
	for(var i = 0; i < s.length; i++){
		var id = s[i].userId;
		var name = s[i].name;
		var gender = s[i].gender;
		var photo1 = s[i].photo1;
		var photo2 = s[i].photo2;
		var pass = s[i].passed;
		var x;
		if(photo1 != null){
			x = "<a class='img-thumbnail lightbox' href='/OnlineTest/"+photo1+"' data-plugin-options='{ \"type\":\"image\" }'>点击查看</a>" +
				"<a class='img-thumbnail lightbox' href='/OnlineTest/"+photo2+"' data-plugin-options='{ \"type\":\"image\" }'>点击查看</a>";
		}else{
			x = "无"; 
		}
		if(pass == "未通过"){
			$("#teacherlist").append("<tr><td>"+id+"</td><td>"+name+"</td><td>"+gender+"</td><td>"+pass+"</td><td>"+x+"</td><td>" +
			"<a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
			"<a class=\"btn btn-danger table_input_delete\" href=\"javascript:void();\"><i class=\"fa fa-trash-o\"></i></a>" +
			"<a class=\"btn btn-info table_input_moify\" href=\"javascript:void();\" title='审核通过按钮'><i class=\"fa fa-check-circle\"></i></a>"+
			"</td></tr>");
		}else{
			$("#teacherlist").append("<tr><td>"+id+"</td><td>"+name+"</td><td>"+gender+"</td><td>"+pass+"</td><td>"+x+"</td><td>" +
			"<a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
			"<a class=\"btn btn-danger table_input_delete\" href=\"javascript:void();\"><i class=\"fa fa-trash-o\"></i></a>" +
			"</td></tr>");
		}
	}
	$(".lightbox").magnificPopup({type:'image'});
}

$(document).ready(function(){
	$.ajax({
		type : "post",
		url : "../user/teacherlist",
		data : {"currPage":"1","pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				var totalPages = eval("("+data+")").data.totalPages;
				var currPage = eval("("+data+")").data.currPage;
				$("#currPage").html(currPage);
	 			$("#totalPage").html(totalPages);
				var s =  eval("("+data+")").data.list;
				load(s);
			}else{
				if(lowerIe()){
					alert(resMsg);
				}else{
					$("#teacherlist").append("<tr><td></td><td></td><td></td><td></td><td></td><td>" +
						"<a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
						"</td></tr>");
					swal("错误",resMsg,"error");
				}
			}
		}
	});
});

function reload(){
	$("#teacherlist").html("");
	$.ajax({
		type : "post",
		url : "../user/teacherlist",
		data : {"currPage":$("#currPage").html(),"pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				var totalPages = eval("("+data+")").data.totalPages;
				var currPage = eval("("+data+")").data.currPage;
				$("#currPage").html(currPage);
	 			$("#totalPage").html(totalPages);
				var s =  eval("("+data+")").data.list;
				load(s);
			}else{
				if(lowerIe()){
					alert(resMsg);
				}else{
					$("#teacherlist").append("<tr><td></td><td></td><td></td><td></td><td></td><td>" +
							"<a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
							"</td></tr>");
					swal("错误",resMsg,"error");
				}
			}
		}
	});
}
/* 审核通过按钮 */
$(document).on("click",".table_input_moify",function(e){
	var userId = $(this).parent().parent().find("td").first().html();
	swal({
		title : "审核通过?",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "通过",
		cancelButtonText : "不通过",
		closeOnConfirm : false,
		closeOnCancel : false
	}, function(isConfirm) {
		if (isConfirm) {
			$.ajax({
				type : "post",
				url : "../user/modifyteacher",
				data : {"id" : userId},
				success : function(data) {
					var resCode = eval("(" + data + ")").resCode;
					var resMsg = eval("(" + data + ")").resMsg;
					if (resCode == "000000") {
						reload();
						swal("成功", resMsg, "success");
					} else {
						swal("错误", resMsg, "error");
					}
				}
			});
		} else {
			swal("取消操作", "你取消了提交", "error");
		}
	});
});

/* 添加按钮 */
$(document).on("click",".table_input_add",function(e){
	$('#table_input').html("");
	$('#table_input').css({'position':'fixed','top':'20%','width':'1000px','left':'15%','z-index':'12'});
	$('#table_input').show();
	$("#table_input").append("<table class=\"table table-striped table-bordered bootstrap-datatable datatable\">"+
			"<thead><tr style=\"text-align: center;\">"+
			"<th width=\"250px\">工号</th>"+
			"<th width=\"\">姓名</th>" +
			"<th width=\"\">密码</th>" +
			"<th width=\"250px\">性别</th>" +
			"</tr></thead>"+
			"<tbody><tr><td><input class=\"form-control\"></td><td><input class=\"form-control\"></td>"+
			"<td><input class=\"form-control\" type=\"password\">" +
			"<td><select class=\"form-control\"><option value=\"\">请选择性别</option><option value=\""+male+"\">男</option><option value=\""+female+"\">女</option><option value=\""+sec_gender+"\">保密</option></select></td>" +
			"</tr></tbody></table>"+
			"<a class=\"btn btn-success\" href=\"javascript:void();\" style=\"margin-left: 50px;\" onclick=\"table_input_addButton()\"><i class=\"fa fa-search-plus\"></i></a>" +
			"<a class=\"btn btn-success\" href=\"javascript:void();\" style=\"margin-left: 30px;\" onclick=\"table_input_mineButton()\"><i class=\"fa fa-minus-square\"></i></a>" +
			"<button type=\"button\" class=\"btn btn-default\" style=\"float: right; margin-right: 50px;\" onclick=\"table_input_hide()\">取消</button>"+
			"<button type=\"button\" class=\"btn btn-default\" style=\"float: right; margin-right: 50px;\" onclick=\"addTeacher()\">确认</button>");
	e.stopPropagation();
});

function table_input_hide(){
	$('#table_input').hide("slow");
}

/*删除按钮*/
$(document).on("click",".table_input_delete",function(e){
	var userId = $(this).parent().parent().children().first().html();
	$(this).parent().parent().html("");
	if(lowerIe()){
		if(confirm("确定提交?"))
		 {
			$.ajax({
				type : "post",
				url : "../user/deleteperson",
				data : {"userId":userId},
				success : function(data) {
					var resCode = eval("("+data+")").resCode;
					var resMsg = eval("("+data+")").resMsg;
					if(resCode == "000000"){
						reload();
						alert(resMsg);
					}else{
						alert(resMsg);
					}
				}
			});
		 }else{
			 alert("你取消了提交");
		 }
	}else{
		swal(
			{   
				title: "确定删除?",   
				type: "warning",   
				showCancelButton: true,   
				confirmButtonColor: "#DD6B55",   
				confirmButtonText: "是的, 提交!",   
				cancelButtonText: "不,暂时不提交!",   
				closeOnConfirm: false,   
				closeOnCancel: false 
			},
			function(isConfirm){   
				if (isConfirm){
					$.ajax({
						type : "post",
						url : "../user/deleteperson",
						data : {"userId":userId},
						success : function(data) {
							var resCode = eval("("+data+")").resCode;
							var resMsg = eval("("+data+")").resMsg;
							if(resCode == "000000"){
								reload();
								swal("成功",resMsg, "success");
							}else{
								swal("错误",resMsg,"error");
							}
						}
					});
				} 
				else{     
					swal("取消操作", "你取消了提交", "error");   
				} 
			}
			);
	}
	e.stopPropagation();
});


/* 添加弹窗--添加按钮*/
function table_input_addButton(){
	var length = $("#table_input tbody tr").length;
	var x = $("#table_input tbody tr").first();
	for(var i = 1; i < length;i ++){
		x = x.next();
	}
	var userId = x.find("td").find("input").val();
	var name = x.find("td").next().find("input").val();
	var password = x.find("td").next().next().find("input").val();
	var gender = x.find("td").next().next().next().find("select").val();
	if(userId == "" || userId == null){
		if(lowerIe()){ alert("工号为空!"); }else{ swal("工号为空!");}
		return false;
	}else if(userId.length < 10){
		if(lowerIe()){ alert("工号长度小于10!"); }else{ swal("工号长度小于10!");}
		return false;
	}else if(userId.length > 10){
		if(lowerIe()){ alert("工号长度大于10!"); }else{ swal("工号长度大于10!");}
		return false;
	}else if(name == "" || name == null){
		if(lowerIe()){ alert("姓名为空!"); }else{ swal("姓名为空!");}
		return false;
	}else if(password == "" || password == null){
		if(lowerIe()){ alert("密码为空!"); }else{ swal("密码为空!");}
		return false;
	}else if(gender == "" || gender == null){
		if(lowerIe()){ alert("性别为空!"); }else{ swal("性别为空!");}
		return false;
	}
	$("#table_input table tbody").append("<tr><td><input class=\"form-control\"></td><td><input class=\"form-control\"></td>"+
			"<td><input class=\"form-control\" type=\"password\">" +
			"<td><select class=\"form-control\"><option value=\"\">请选择性别</option><option value=\""+male+"\">男</option><option value=\""+female+"\">女</option></select></td>" +
			"</tr>");
}
/* 添加弹窗 -- 缩减按钮 */
function table_input_mineButton(){
	var length = $("#table_input tbody tr").length;
	var x = $("#table_input tbody tr").first();
	if(length == 1){
		return false;
	}
	for(var i = 1; i < length;i ++){
		x = x.next();
	}
	x.remove();
}

function addTeacher(){
	var jsondata = "SYS01";
	var len = $("#table_input tbody tr").length;
	var x = $("#table_input tbody tr").first();
	for(var i = 0; i < len; i++){
		var userId = x.find("td").find("input").val();
		var name = x.find("td").next().find("input").val();
		var password = x.find("td").next().next().find("input").val();
		var gender = x.find("td").next().next().next().find("select").val();
		if(userId == "" || userId == null){
			if(lowerIe()){ alert("第"+(i+1)+"行工号为空!"); }else{ swal("第"+(i+1)+"行工号为空!");}
			return false;
		}else if(userId.length < 10){
			if(lowerIe()){ alert("工号长度小于10!"); }else{ swal("工号长度小于10!");}
			return false;
		}else if(userId.length > 10){
			if(lowerIe()){ alert("工号长度大于10!"); }else{ swal("工号长度大于10!");}
			return false;
		}else if(name == "" || name == null){
			if(lowerIe()){ alert("第"+(i+1)+"行姓名为空!"); }else{ swal("第"+(i+1)+"行姓名为空!");}
			return false;
		}else if(password == "" || password == null){
			if(lowerIe()){ alert("第"+(i+1)+"行密码为空!"); }else{ swal("第"+(i+1)+"行密码为空!");}
			return false;
		}else if(gender == "" || gender == null){
			if(lowerIe()){ alert("第"+(i+1)+"行性别为空!"); }else{ swal("第"+(i+1)+"行性别为空!");}
			return false;
		}
		jsondata += "`" + userId + "`" + name + "`" + password + "`" + gender;
		x = x.next();
	}
	$.ajax({
		type : "post",
		url : "../user/addperson",
		data : {"data":jsondata},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				reload();
				if(lowerIe()){ alert(resMsg); }else{ swal("成功",resMsg, "success");}
				table_input_hide();
			}else{
				if(lowerIe()){ alert(resMsg); }else{ swal("错误",resMsg,"error");}
				table_input_hide();
			}
		}
	});
}

function teacherListFirst(){
	$("#teacherlist").html("");
	$.ajax({
		type : "post",
		url : "../user/teacherlist",
		data : {"currPage":"1","pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				var totalPages = eval("("+data+")").data.totalPages;
				var currPage = eval("("+data+")").data.currPage;
				$("#currPage").html(currPage);
	 			$("#totalPage").html(totalPages);
				var s =  eval("("+data+")").data.list;
				load(s);
			}else{
				if(lowerIe()){
					alert(resMsg);
				}else{
					$("#teacherlist").append("<tr><td></td><td></td><td></td><td></td><td>" +
							"<a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
							"</td></tr>");
					swal("错误",resMsg,"error");
				}
			}
		}
	});
}

function teacherListLast(){
	$("#teacherlist").html("");
	$.ajax({
		type : "post",
		url : "../user/teacherlist",
		data : {"currPage":$("#totalPage").html(),"pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				var totalPages = eval("("+data+")").data.totalPages;
				var currPage = eval("("+data+")").data.currPage;
				$("#currPage").html(currPage);
	 			$("#totalPage").html(totalPages);
				var s =  eval("("+data+")").data.list;
				load(s);
			}else{
				if(lowerIe()){
					alert(resMsg);
				}else{
					$("#teacherlist").append("<tr><td></td><td></td><td></td><td></td><td>" +
							"<a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
							"</td></tr>");
					swal("错误",resMsg,"error");
				}
			}
		}
	});
}

function teacherListNext(){
	var curr = parseInt($("#currPage").html());
	var total = parseInt($("#totalPage").html());
	if(curr < total){
		curr = curr + 1;
	}
	$("#teacherlist").html("");
	$.ajax({
		type : "post",
		url : "../user/teacherlist",
		data : {"currPage": curr,"pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				var totalPages = eval("("+data+")").data.totalPages;
				var currPage = eval("("+data+")").data.currPage;
				$("#currPage").html(currPage);
	 			$("#totalPage").html(totalPages);
				var s =  eval("("+data+")").data.list;
				load(s);
			}else{
				if(lowerIe()){
					alert(resMsg);
				}else{
					$("#teacherlist").append("<tr><td></td><td></td><td></td><td></td><td>" +
							"<a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
							"</td></tr>");
					swal("错误",resMsg,"error");
				}
			}
		}
	});
}

function teacherListPrev(){
	var curr = parseInt($("#currPage").html());
	if(curr > 1){
		curr = curr - 1;
	}
	$("#teacherlist").html("");
	$.ajax({
		type : "post",
		url : "../user/teacherlist",
		data : {"currPage":curr,"pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				var totalPages = eval("("+data+")").data.totalPages;
				var currPage = eval("("+data+")").data.currPage;
				$("#currPage").html(currPage);
	 			$("#totalPage").html(totalPages);
				var s =  eval("("+data+")").data.list;
				load(s);
			}else{
				if(lowerIe()){
					alert(resMsg);
				}else{
					$("#teacherlist").append("<tr><td></td><td></td><td></td><td></td><td>" +
							"<a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
							"</td></tr>");
					swal("错误",resMsg,"error");
				}
			}
		}
	});
}