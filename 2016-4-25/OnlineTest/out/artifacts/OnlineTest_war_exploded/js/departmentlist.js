var academyCodeForAll = "";
var academyValueForAll = "";
var academySelect = "";
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
	$("#departmentlist").html("");
	for(var i = 0; i < s.length; i++){
		var academy = s[i].firstCodeValue;
		var code = s[i].secondCode;
		var value = s[i].secondCodeValue;
		var abb = s[i].abbreviation;
		$("#departmentlist").append("<tr><td>"+academy+"</td><td>"+value+"</td><td>"+abb+"</td><td>" +
			 "<a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
		     "<a class=\"btn btn-danger table_input_delete\" href=\"javascript:void();\"><i class=\"fa fa-trash-o\"></i></a>" +
		     "<span style=\"display:none\">"+code+"</span></td></tr>");
	}
}

$(document).ready(function(){
	$.ajax({
		type : "post",
		url : "../academy/departmentlist",
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				var s =  eval("("+data+")").data.list;
				load(s);
			}else{
				if(lowerIe()){
					alert(resMsg);
				}else{
					$("#departmentlist").append("<tr><td></td><td></td><td></td><td>" +
							 "<a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
						     "</td></tr>");
					swal("错误",resMsg,"error");
				}
			}
		}
	});
});
/* 学院下拉选择 */
$(document).ready(function(){
	$.ajax({
		type : "post",
		url : "../academy/academylist",
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				var s =  eval("("+data+")").data.list;
				for(var i = 0; i < s.length; i++){
					var code = s[i].firstCode;
					var value = s[i].firstCodeValue;
					academySelect += "<option value=\""+code+"\">"+value+"</option>";
				}
			}else{
				if(lowerIe()){ alert(resMsg); }else{ swal("错误",resMsg,"error"); }
			}
			$("#departmentlist_depart").append(academySelect);
		}
	});
});

function reload(){
	var code = "";
	if(academyCodeForAll != ""){
		code = academyCodeForAll;
	}
	$.ajax({
		type : "post",
		url : "../academy/departmentlist",
		data : {"code": code },
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				var s =  eval("("+data+")").data.list;
				load(s);
			}else{
				$("#departmentlist").append("<tr><td></td><td></td><td></td><td>" +
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

/*添加按钮*/
$(document).on("click",".table_input_add",function(e){
	$('#table_input').html("");
	$('#table_input').css({'position':'fixed','top':'20%','width':'900px','left':'15%','z-index':'12'});
	$('#table_input').show();
	$("#table_input").append("<table class=\"table table-striped table-bordered bootstrap-datatable datatable\">"+
			"<thead><tr style=\"text-align: center;\">"+
			"<th width=\"150px\">编号</th>"+
			"<th width=\"220px\">学院</th>" +
			"<th width=\"\">科系</th>" +
			"<th width=\"\">缩写</th></tr></thead>"+
			"<tbody></tbody></table>"+
			"<a class=\"btn btn-success\" href=\"javascript:void();\" style=\"margin-left: 50px;\" onclick=\"table_input_addButton()\"><i class=\"fa fa-search-plus\"></i></a>" +
			"<a class=\"btn btn-success\" href=\"javascript:void();\" style=\"margin-left: 30px;\" onclick=\"table_input_mineButton()\"><i class=\"fa fa-minus-square\"></i></a>" +
			"<span class=\"label label-warning\" style=\"margin-left:15%;\">警告:题目和答案选项中不能含有特殊符号(`)</span>"+
			"<button type=\"button\" class=\"btn btn-default\" style=\"float: right; margin-right: 50px;\" onclick=\"table_input_hide()\">取消</button>"+
			"<button type=\"button\" class=\"btn btn-default\" style=\"float: right; margin-right: 50px;\" onclick=\"addDepartment()\">确认</button>");
	if(academyCodeForAll != ""){
		$("#table_input table tbody").append("<tr><td>1</td><td>"+academyValueForAll+"</td><td><input class=\"form-control\"></td>"+
		"<td><input class=\"form-control\"></td></tr>");
	}else{
		$("#table_input table tbody").append("<tr><td>1</td><td><select class=\"form-control\"><option value=\"\">所有学院</option>"+academySelect+"</select></td><td><input class=\"form-control\"></td>"+
		"<td><input class=\"form-control\"></td></tr>");
	}
	
	e.stopPropagation();
});

function table_input_hide(){
	$('#table_input').hide("slow");
}

/*删除按钮*/
$(document).on("click",".table_input_delete",function(e){
	var x = $(this).next().html();
	$(this).parent().parent().html("");
	if(lowerIe()){
		if(confirm("确定提交?"))
		 {
			$.ajax({
				type : "post",
				url : "../academy/deletedepartment",
				data:{"code": x },
				success : function(data) {
					var resCode = eval("("+data+")").resCode;
					var resMsg = eval("("+data+")").resMsg;
					if(resCode == "000000"){
						reload();
						alert(resMsg);
						table_input_hide();
					}else{
						reload();
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
				text: "一旦删除，将连同该学院的所有系科一并删除!",
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
						url : "../academy/deletedepartment",
						data:{"code": x },
						success : function(data) {
							var resCode = eval("("+data+")").resCode;
							var resMsg = eval("("+data+")").resMsg;
							if(resCode == "000000"){
								swal("成功",resMsg, "success");
								reload();
								table_input_hide();
							}else{
								reload();
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
	var id = $("#table_input tbody tr").length + 1;
	var length = $("#table_input tbody tr").length;
	var x = $("#table_input tbody tr").first();
	for(var i = 1; i < length;i ++){
		x = x.next();
	}
	var academy = x.find("td").next().find("select").val();
	var department = x.find("td").next().next().find("input").val();
	var abb = x.find("td").next().next().next().find("input").val();
	if(academyCodeForAll != ""){
		academy = x.find("td").next().html();
	}
	if(academy == "" || academy == null){
		if(lowerIe()){ alert("学院为空!"); }else{ swal("学院为空!");}
		return false;
	}if(department == "" || department == null){
		if(lowerIe()){ alert("科系为空!"); }else{ swal("科系为空!");}
		return false;
	}else if(abb == "" || abb == null){
		if(lowerIe()){ alert("缩写为空!"); }else{ swal("缩写为空!");}
		return false;
	}
	if(academyCodeForAll != ""){
		$("#table_input table tbody").append("<tr><td>"+id+"</td><td>"+academyValueForAll+"</td><td><input class=\"form-control\"></td>"+
		"<td><input class=\"form-control\"></td></tr>");
	}else{
		$("#table_input table tbody").append("<tr><td>"+id+"</td><td><select class=\"form-control\"><option value=\"\">所有学院</option>"+academySelect+"</select></td><td><input class=\"form-control\"></td>"+
		"<td><input class=\"form-control\"></td></tr>");
	}
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

function addDepartment(){
	var jsondata = "";
	var length = $("#table_input tbody tr").length;
	var x = $("#table_input tbody tr").first();
	for(var i = 0; i < length;i ++){
		var academyCode = x.find("td").next().find("select").val();
		var academyValue = x.find("td").next().find("select").find("option:selected").text();
		var department = x.find("td").next().next().find("input").val();
		var abb = x.find("td").next().next().next().find("input").val();
		if(academyCodeForAll != ""){
			academyCode = academyCodeForAll;
			academyValue = academyValueForAll;
		}
		if(academyCode == "" || academyCode == null){
			if(lowerIe()){ alert("第"+(i+1)+"行学院为空!"); }else{ swal("第"+(i+1)+"行学院为空!");}
			return false;
		}if(department == "" || department == null){
			if(lowerIe()){ alert("第"+(i+1)+"行科系为空!"); }else{ swal("第"+(i+1)+"行科系为空!");}
			return false;
		}else if(abb == "" || abb == null){
			if(lowerIe()){ alert("第"+(i+1)+"行缩写为空!"); }else{ swal("第"+(i+1)+"行缩写为空!");}
			return false;
		}
		if(i == 0){
			jsondata += academyCode + "`" + academyValue + "`"+ department + "`" + abb;
		}else{
			jsondata += "`" + academyCode + "`" + academyValue + "`"+ department + "`" + abb;
		}
		
		x = x.next();
	}
	$.ajax({
		type : "post",
		url : "../academy/adddepartment",
		data:{"data": jsondata },
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				reload();
				if(lowerIe()){ alert(resMsg); }else{ swal("成功",resMsg, "success");}
				table_input_hide();
			}else{
				reload();
				if(lowerIe()){ alert(resMsg); }else{ swal("错误",resMsg,"error");}
			}
		}
	});
}

function search_depart(e){
	academyCodeForAll = "";
	var code = $(e).prev().val();
	academyCodeForAll = code;
	academyValueForAll = $(e).prev().find("option:selected").text();
	$("#departmentlist").html("");
	$.ajax({
		type : "post",
		url : "../academy/departmentlist",
		data : {"code": code },
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				var s =  eval("("+data+")").data.list;
				load(s);
			}else{
				if(lowerIe()){
					alert(resMsg);
					$("#departmentlist").append("<tr><td></td><td></td><td></td><td>" +
						 "<a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
					     "<a class=\"btn btn-danger table_input_delete\" href=\"javascript:void();\"><i class=\"fa fa-trash-o\"></i></a>" +
						 "</td></tr>");
				}else{
					swal("错误",resMsg,"error");
					$("#departmentlist").append("<tr><td></td><td></td><td></td><td>" +
						 "<a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
						 "</td></tr>");
				}
			}
		}
	});
}