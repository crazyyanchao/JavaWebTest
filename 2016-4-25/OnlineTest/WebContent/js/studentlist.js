var AllAcademy = "";
var ClassForAll = "";
var xxxxxxxxxxxxx;
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
		var classes = s[i].belongNo;
		$("#studentlist").append("<tr><td>"+id+"</td><td>"+name+"</td><td>"+gender+"</td><td>"+classes+"</td><td>" +
		"<a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
		"<a class=\"btn btn-info table_input_modify\" href=\"javascript:void();\"><i class=\"fa fa-edit\"></i></a>"+   //修改
		"<a class=\"btn btn-danger table_input_delete\" href=\"javascript:void();\"><i class=\"fa fa-trash-o\"></i></a>" +  //删除
		"</td></tr>");
	}
}

$(document).ready(function(){
	$.ajax({
		type : "post",
		url : "../user/studentlist",
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
					$("#studentlist").append("<tr><td></td><td></td><td></td><td></td><td>" +
						"<a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
						"</td></tr>");
					swal("错误",resMsg,"error");
				}
			}
		}
	});
});

function reload(){
	$("#studentlist").html("");
	$.ajax({
		type : "post",
		url : "../user/studentlist",
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
				$("#studentlist").append("<tr><td></td><td></td><td></td><td></td><td>" +
						"<a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
						"</td></tr>");
				if(lowerIe()){
					alert(resMsg);
				}else{
					swal("错误",resMsg,"error");
				}
			}
		}
	});
}

/* 学院  */
$(document).ready(function(){
	AllAcademy = "";
	$.ajax({
		type : "post",
		url : "../academy/academylist",
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				var s =  eval("("+data+")").data.list;
				AllAcademy = "<li>" ;
				for(var i = 0; i < s.length; i++){
					var x = parseInt(i/3)*30 + parseInt(80);
					if( (i % 3) == 0){
						AllAcademy += "<a class=\"acamySelected\" style=\"top:"+x+"px;position: absolute\">"+s[i].firstCodeValue+"</a>" +
						 "<span style=\"display:none;\">"+s[i].firstCode+"</span>";
					}else if( (i % 3) == 1){
						AllAcademy += "<a class=\"acamySelected\" style=\"top:"+x+"px;position: absolute;left:220px;\">"+s[i].firstCodeValue+"</a>" +
						 "<span style=\"display:none;\">"+s[i].firstCode+"</span>";
					}else if( (i % 3) == 2 ){
						AllAcademy += "<a class=\"acamySelected\" style=\"top:"+x+"px;position: absolute;left:400px;\">"+s[i].firstCodeValue+"</a>" +
					     "<span style=\"display:none;\">"+s[i].firstCode+"</span><li>";
					}
				}
				AllAcademy += "</li>";
			}else{
				if(lowerIe()){ alert(resMsg); }else{ swal("错误",resMsg,"error"); }
			}
		}
	});
});

/* 学院Panel */
$(document).on("click",".select_class",function(e){
	xxxxxxxxxxxxx = $(this);
	$('#academy_classPanel').html("");
	$('#academy_classPanel').css({'position':'fixed','margin':'0 auto','left':'0px','right':'0px','top':'50%','margin-top':'-182px','width':'600px','height':'365px','z-index':'12','background-color':'#eee'});
	$('#academy_classPanel').show();
	$('#academy_classPanel').append("<div class=\"panel\" style=\"width: 600px; height: 350px;\"><div class=\"panel-heading\" style=\"margin-top: 15px;\">" +
			"<h4 style=\"text-align: center;color:black;\">学院</h4></div><hr style=\"margin-top: 10px;margin-bottom: 0;\">"+
			"<div id=\"academy_classBody\" class=\"panel-body\" style=\"height:254px;width:598px;overflow: scroll;\"><ul style=\"list-style-type:none;\">" + AllAcademy +
			"</ul></div>"+
			"<div class=\"panel-footer\" style=\"height:50px;\">" +
			"<button onclick=\"closePanel()\" class=\"btn btn-default\" style=\"float:right; margin-right:20px;\">关闭</button>" +
			"</div></div>");
	e.stopPropagation();
});

/* 班级   */
$(document).on("click",".acamySelected",function(e){
	var academy = $(this).next().html();
	ClassForAll = "";
	$.ajax({
		type : "post",
		url : "../classes/classlist",
		data:{"code":academy},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				var s =  eval("("+data+")").data.list;
				$("#academy_classBody").html("");
				ClassForAll = "<li>" ;
				for(var i = 0; i < s.length; i++){
					var x = parseInt(i/3)*30 + parseInt(80);
					if( (i % 3) == 0){
						ClassForAll += "<a class=\"classSelected\" style=\"top:"+x+"px;position: absolute\">"+s[i].classValue+"</a>" +
						 "<span style=\"display:none;\">"+s[i].classCode+"</span>";
					}else if( (i % 3) == 1){
						ClassForAll += "<a class=\"classSelected\" style=\"top:"+x+"px;position: absolute;left:220px;\">"+s[i].classValue+"</a>" +
						 "<span style=\"display:none;\">"+s[i].classCode+"</span>";
					}else if( (i % 3) == 2 ){
						ClassForAll += "<a class=\"classSelected\" style=\"top:"+x+"px;position: absolute;left:400px;\">"+s[i].classValue+"</a>" +
					     "<span style=\"display:none;\">"+s[i].classCode+"</span><li>";
					}
				}
				$("#academy_classBody").prev().prev().find("h4").html("班级");
				ClassForAll += "</li>";
				$("#academy_classBody").append("<ul style=\"list-style-type:none;\">" + ClassForAll + "</ul>");
			}else{
				if(lowerIe()){
					alert(resMsg);
				}else{
					swal("错误",resMsg,"error");
				}
			}
		}
	});
	e.stopPropagation();
});
/* 班级 绑定值  */
$(document).on("click",".classSelected",function(e){
	var classCode = $(this).next().html();
	var classValue = $(this).html();
	xxxxxxxxxxxxx.val(classValue);
	xxxxxxxxxxxxx.parent().parent().append("<span style=\"display:none;\">"+classCode+"</span>");
	closePanel();
	e.stopPropagation();
});
/*添加按钮*/
$(document).on("click",".table_input_add",function(e){
	$('#table_input').html("");
	$('#table_input').css({'position':'fixed','top':'20%','width':'1100px','left':'10%','z-index':'12'});
	$('#table_input').show();
	$("#table_input").append("<table class=\"table table-striped table-bordered bootstrap-datatable datatable\">"+
			"<thead><tr style=\"text-align: center;\">"+
			"<th width=\"200px\">学号</th>"+
			"<th width=\"\">姓名</th>" +
			"<th width=\"\">密码</th>" +
			"<th width=\"150px\">性别</th>" +
			"<th width=\"180px\">所在班级</th></tr></thead>"+
			"<tbody><tr><td><input class=\"form-control\"></td><td><input class=\"form-control\"></td>"+
			"<td><input class=\"form-control\" type=\"password\">" +
			"<td><select class=\"form-control\"><option value=\"\">请选择性别</option><option value=\""+male+"\">男</option><option value=\""+female+"\">女</option><option value=\""+sec_gender+"\">保密</option></select></td>" +
			"<td><input class=\"form-control select_class\"></td>" +
			"</tr></tbody></table>"+
			"<a class=\"btn btn-success\" href=\"javascript:void();\" style=\"margin-left: 50px;\" onclick=\"table_input_addButton()\"><i class=\"fa fa-search-plus\"></i></a>" +
			"<a class=\"btn btn-success\" href=\"javascript:void();\" style=\"margin-left: 30px;\" onclick=\"table_input_mineButton()\"><i class=\"fa fa-minus-square\"></i></a>" +
			"<button type=\"button\" class=\"btn btn-default\" style=\"float: right; margin-right: 50px;\" onclick=\"table_input_hide()\">取消</button>"+
			"<button type=\"button\" class=\"btn btn-default\" style=\"float: right; margin-right: 50px;\" onclick=\"addStudent()\">确认</button>");
	e.stopPropagation();
});

/*修改按钮 -- 修改班级*/
$(document).on("click",".table_input_modify",function(e){
	var id = $(this).parent().prev().prev().prev().prev().html();
	var name = $(this).parent().prev().prev().prev().html();
	var gender = $(this).parent().prev().prev().html();
	$('#table_input').html("");
	$('#table_input').css({'position':'fixed','top':'25%','width':'800px','left':'20%','z-index':'12'});
	$('#table_input').show();
	$("#table_input").append("<table class=\"table table-striped table-bordered bootstrap-datatable datatable\">"+
			"<thead><tr style=\"text-align: center;\">"+
			"<th width=\"200px\">学号</th>"+
			"<th width=\"\">姓名</th>" +
			"<th width=\"200px\">性别</th>" +
			"<th width=\"200px\">所在班级</th></tr></thead>"+
			"<tbody><tr><td>"+id+"</td><td>"+name+"</td><td>"+gender+"</td>" +
			"<td><input class=\"form-control select_class\"></td>" +
			"</tr></tbody></table>"+
			"<button type=\"button\" class=\"btn btn-default\" style=\"float: right; margin-right: 50px;\" onclick=\"table_input_hide()\">取消</button>"+
			"<button type=\"button\" class=\"btn btn-default\" style=\"float: right; margin-right: 50px;\" onclick=\"modifyStudent()\">确认</button>");
	e.stopPropagation();
});

/* 删除按钮  */
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

function table_input_hide(){
	$('#table_input').hide("slow");
}

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
		if(lowerIe()){ alert("学号为空!"); }else{ swal("学号为空!");}
		return false;
	}else if(userId.length < 10){
		if(lowerIe()){ alert("学号长度小于10!"); }else{ swal("学号长度小于10!");}
		return false;
	}else if(userId.length > 10){
		if(lowerIe()){ alert("学号长度大于10!"); }else{ swal("学号长度大于10!");}
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
			"<td><input class=\"form-control select_class\"></td></tr>");
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

function addStudent(){
	var jsondata = "SYS02";
	var len = $("#table_input tbody tr").length;
	var x = $("#table_input tbody tr").first();
	for(var i = 0; i < len; i++){
		var userId = x.find("td").find("input").val();
		var name = x.find("td").next().find("input").val();
		var password = x.find("td").next().next().find("input").val();
		var gender = x.find("td").next().next().next().find("select").val();
		var classes = x.find("td").last().next().html();
		if(typeof(classes)=="undefined"){
			classes = " ";
		}
		if(userId == "" || userId == null){
			if(lowerIe()){ alert("第"+(i+1)+"行学号为空!"); }else{ swal("第"+(i+1)+"行学号为空!");}
			return false;
		}else if(userId.length < 10){
			if(lowerIe()){ alert("学号长度小于10!"); }else{ swal("学号长度小于10!");}
			return false;
		}else if(userId.length > 10){
			if(lowerIe()){ alert("学号长度大于10!"); }else{ swal("学号长度大于10!");}
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
		jsondata += "`" + userId + "`" + name + "`" + password + "`" + gender + "`" + classes;
		x = x.next();
	}
	$.ajax({
		type : "post",
		url : "../admin/addstudent",
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

function studentListFirst(){
	$("#studentlist").html("");
	$.ajax({
		type : "post",
		url : "../user/studentlist",
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
				$("#studentlist").append("<tr><td></td><td></td><td></td><td></td><td></td><td>" +
						"<a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
						"</td></tr>");
				if(lowerIe()){
					alert(resMsg);
				}else{
					swal("错误",resMsg,"error");
				}
			}
		}
	});
}

function studentListLast(){
	$("#studentlist").html("");
	$.ajax({
		type : "post",
		url : "../user/studentlist",
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
				$("#studentlist").append("<tr><td></td><td></td><td></td><td></td><td></td><td>" +
						"<a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
						"</td></tr>");
				if(lowerIe()){
					alert(resMsg);
				}else{
					swal("错误",resMsg,"error");
				}
			}
		}
	});
}

function studentListNext(){
	var curr = parseInt($("#currPage").html());
	var total = parseInt($("#totalPage").html());
	if(curr < total){
		curr = curr + 1;
	}
	$("#studentlist").html("");
	$.ajax({
		type : "post",
		url : "../user/studentlist",
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
				$("#studentlist").append("<tr><td></td><td></td><td></td><td></td><td></td><td>" +
						"<a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
						"</td></tr>");
				if(lowerIe()){
					alert(resMsg);
				}else{
					swal("错误",resMsg,"error");
				}
			}
		}
	});
}

function studentListPrev(){
	var curr = parseInt($("#currPage").html());
	if(curr > 1){
		curr = curr - 1;
	}
	$("#studentlist").html("");
	$.ajax({
		type : "post",
		url : "../user/studentlist",
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
				$("#studentlist").append("<tr><td></td><td></td><td></td><td></td><td></td><td>" +
						"<a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
						"</td></tr>");
				if(lowerIe()){
					alert(resMsg);
				}else{
					swal("错误",resMsg,"error");
				}
			}
		}
	});
}

function closePanel(){
	$('#academy_classPanel').hide('slow');
}

function modifyStudent(){
	var userId = $("#table_input table tbody tr td").first().html();
	var classCode = $("#table_input table tbody tr td").last().next().html();
	if(typeof(classCode)=="undefined"){
		classCode = "";
	}
	$.ajax({
		type : "post",
		url : "../admin/modifystucla",
		data : {"class":classCode,"userId":userId},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				reload();
				if(lowerIe()){ alert(resMsg); }else{ swal("成功",resMsg, "success");}
				table_input_hide();
			}else{
				if(lowerIe()){ alert(resMsg); }else{ swal("错误",resMsg,"error");}
			}
		}
	});
}