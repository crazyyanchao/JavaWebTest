var academySelect = "";
var departSelect = "";
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
		var id = s[i].id;
		var academy = s[i].academy;
		var profession = s[i].profession;
		var classCode = s[i].classCode;
		var classValue = s[i].classValue;
		var star = s[i].startYear;
		$("#classlist").append("<tr><td>"+id+"</td><td>"+academy+"</td><td>"+profession+"</td><td>"+star+"</td><td>"+classValue+"</td><td>" +
		"<a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
		"<a class=\"btn btn-info table_input_modify\" href=\"javascript:void();\"><i class=\"fa fa-edit\"></i></a>"+   //修改
		"<a class=\"btn btn-danger table_input_delete\" href=\"javascript:void();\"><i class=\"fa fa-trash-o\"></i></a>" +
		"<span style=\"display:none\">"+classCode+"</span>"+
		"</td></tr>");
	}
}

$(document).ready(function(){
	$.ajax({
		type : "post",
		url : "../classes/clalist",
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
				$("#classlist").append("<tr><td></td><td></td><td></td><td></td><td></td><td>" +
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
		}
	});
});
/*  专业下拉选择  */
$(document).on("change",".academy_select",function(e){
	departSelect = "";
	var x = $(this).parent().next().find("select");
	var academy = $(this).val();
	$.ajax({
		type : "post",
		url : "../academy/departmentlist",
		data:{"code":academy},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				var s =  eval("("+data+")").data.list;
				for(var i = 0; i < s.length; i++){
					var code = s[i].secondCode;
					var value = s[i].secondCodeValue;
					departSelect += "<option value=\""+code+"\">"+value+"</option>";
				}
				x.append(departSelect);
			}else{
				if(lowerIe()){
					alert(resMsg);
				}else{
					swal("错误",resMsg,"error");
				}
			}
		}
	});
});

/*添加按钮*/
$(document).on("click",".table_input_add",function(e){
	$('#table_input').html("");
	$('#table_input').css({'position':'fixed','top':'20%','width':'900px','left':'15%','z-index':'12'});
	$('#table_input').show();
	$("#table_input").append("<table class=\"table table-striped table-bordered bootstrap-datatable datatable\">"+
			"<thead><tr style=\"text-align: center;\">"+
			"<th width=\"100px\">编号</th>"+
			"<th width=\"180px\">所属学院</th>" +
			"<th width=\"180px\">专业</th>" +
			"<th width=\"\">开始年份</th>" +
			"<th width=\"\">班级名称</th></tr></thead>"+
			"<tbody><tr><td>1</td>"+
			"<td><select class=\"form-control academy_select\"><option value=\"\">请选择学院</option>"+academySelect+"</select></td>" +
			"<td><select class=\"form-control\"><option value=\"\">请选择专业</option></select></td>" +
			"<td><input class=\"form-control\" type=\"number\" min=\"0\"></td><td><input class=\"form-control\"></td></tr></tbody></table>"+
			"<a class=\"btn btn-success\" href=\"javascript:void();\" style=\"margin-left: 50px;\" onclick=\"table_input_addButton()\"><i class=\"fa fa-search-plus\"></i></a>" +
			"<a class=\"btn btn-success\" href=\"javascript:void();\" style=\"margin-left: 30px;\" onclick=\"table_input_mineButton()\"><i class=\"fa fa-minus-square\"></i></a>" +
			"<button type=\"button\" class=\"btn btn-default\" style=\"float: right; margin-right: 50px;\" onclick=\"table_input_hide()\">取消</button>"+
			"<button type=\"button\" class=\"btn btn-default\" style=\"float: right; margin-right: 50px;\" onclick=\"addClass()\">确认</button>");
	e.stopPropagation();
});
/* 修改按钮 */
$(document).on("click",".table_input_modify",function(e){
	var id = $(this).parent().parent().children().first().html();
	var academy = $(this).parent().parent().children().first().next().html();
	var pro = $(this).parent().parent().children().first().next().next().html();
	var start = $(this).parent().parent().children().first().next().next().next().html();
	var name = $(this).parent().parent().children().first().next().next().next().next().html();
	$('#table_input').html("");
	$('#table_input').css({'position':'fixed','top':'20%','width':'900px','left':'15%','z-index':'12'});
	$('#table_input').show();
	$("#table_input").append("<table class=\"table table-striped table-bordered bootstrap-datatable datatable\">"+
			"<thead><tr style=\"text-align: center;\">"+
			"<th width=\"100px\">编号</th>"+
			"<th width=\"180px\">所属学院</th>" +
			"<th width=\"180px\">专业</th>" +
			"<th width=\"\">开始年份</th>" +
			"<th width=\"\">班级名称</th></tr></thead>"+
			"<tbody><tr><td>"+id+"</td><td>"+academy+"</td><td>"+pro+"</td><td>"+start+"</td><td><input class=\"form-control\" value=\""+name+"\"></td></tr></tbody></table>"+
			"<button type=\"button\" class=\"btn btn-default\" style=\"float: right; margin-right: 50px;\" onclick=\"table_input_hide()\">取消</button>"+
			"<button type=\"button\" class=\"btn btn-default\" style=\"float: right; margin-right: 50px;\" onclick=\"modifyClass()\">确认</button>");
	e.stopPropagation();
});
/*删除按钮*/
$(document).on("click",".table_input_delete",function(e){
	var id = $(this).parent().parent().children().first().html();
	$(this).parent().parent().html("");
	if(lowerIe()){
		if(confirm("确定提交?"))
		 {
			$.ajax({
				type : "post",
				url : "../classes/deleteclass",
				data : {"id":id},
				success : function(data) {
					var resCode = eval("("+data+")").resCode;
					var resMsg = eval("("+data+")").resMsg;
					if(resCode == "000000"){
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
						url : "../classes/deleteclass",
						data : {"id":id},
						success : function(data) {
							var resCode = eval("("+data+")").resCode;
							var resMsg = eval("("+data+")").resMsg;
							if(resCode == "000000"){
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
	var id = parseInt(length) + 1;
	var x = $("#table_input tbody tr").first();
	for(var i = 1; i < length;i ++){
		x = x.next();
	}
	var academy = x.find("td").next().find("select").val()
	var department = x.find("td").next().next().find("select").val();
	var start = x.find("td").next().next().next().find("input").val();
	var name = x.find("td").next().next().next().next().find("input").val();
	if(academy == "" || academy == null){
		if(lowerIe()){ alert("学院为空!"); }else{ swal("学院号为空!");}
		return false;
	}else if(department == "" || department == null){
		if(lowerIe()){ alert("专业为空!"); }else{ swal("专业为空!");}
		return false;
	}else if(start == "" || start == null){
		if(lowerIe()){ alert("开始年份为空!"); }else{ swal("开始年份为空!");}
		return false;
	}else if(name == "" || name == null){
		if(lowerIe()){ alert("班级名称为空!"); }else{ swal("班级名称为空!");}
		return false;
	}
	$("#table_input table tbody").append("<tr><td>"+id+"</td><td><select class=\"form-control academy_select\"><option value=\"\">请选择学院</option>"+academySelect+"</select></td>" +
			"<td><select class=\"form-control\"><option value=\"\">请选择专业</option></select></td>" +
			"<td><input class=\"form-control\" type=\"number\" min=\"0\"></td><td><input class=\"form-control\"></td></tr>");
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

function reload(){
	$("#classlist").html("");
	$.ajax({
		type : "post",
		url : "../classes/clalist",
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
				$("#classlist").append("<tr><td></td><td></td><td></td><td></td><td></td><td>" +
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

function modifyClass(){
	var id = $("#table_input tbody tr").first().find("td").html();
	var name = $("#table_input tbody tr").first().find("td").last().find("input").val()
	$.ajax({
		type : "post",
		url : "../classes/modifyclass",
		data : {"id":id,"name":name},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				reload();
				table_input_hide();
				swal("成功",resMsg, "success");
			}else{
				swal("错误",resMsg,"error");
			}
		}
	});
}

function addClass(){
	var jsondata = "";
	var len = $("#table_input tbody tr").length;
	var x = $("#table_input tbody tr").first();
	for(var i = 0; i < len; i++){
		var academy = x.find("td").next().find("select").val()
		var department = x.find("td").next().next().find("select").val();
		var start = x.find("td").next().next().next().find("input").val();
		var name = x.find("td").next().next().next().next().find("input").val();
		if(academy == "" || academy == null){
			if(lowerIe()){ alert("第"+(i+1)+"行学院为空!"); }else{ swal("第"+(i+1)+"行学院号为空!");}
			return false;
		}else if(department == "" || department == null){
			if(lowerIe()){ alert("第"+(i+1)+"行专业为空!"); }else{ swal("第"+(i+1)+"行专业为空!");}
			return false;
		}else if(start == "" || start == null){
			if(lowerIe()){ alert("第"+(i+1)+"行开始年份为空!"); }else{ swal("第"+(i+1)+"行开始年份为空!");}
			return false;
		}else if(name == "" || name == null){
			if(lowerIe()){ alert("第"+(i+1)+"行班级名称为空!"); }else{ swal("第"+(i+1)+"行班级名称为空!");}
			return false;
		}
		if( i == 0){
			jsondata += department + "`" + start + "`" + name ;
		}else{
			jsondata += "`" + department + "`" + start + "`" + name ;
		}
		x = x.next();
	}
	$.ajax({
		type : "post",
		url : "../classes/addclass",
		data : {"data":jsondata},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				reload();
				table_input_hide();
				if(lowerIe()){ alert(resMsg); }else{ swal("成功",resMsg, "success");}
			}else{
				table_input_hide();
				if(lowerIe()){ alert(resMsg); }else{ swal("错误",resMsg,"error");}
			}
		}
	});
}

function classListFirst(){
	$("#classlist").html("");
	$.ajax({
		type : "post",
		url : "../classes/clalist",
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
				$("#classlist").append("<tr><td></td><td></td><td></td><td>" +
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

function classListLast(){
	$("#classlist").html("");
	$.ajax({
		type : "post",
		url : "../classes/clalist",
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
				$("#classlist").append("<tr><td></td><td></td><td></td><td>" +
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

function classListNext(){
	var curr = parseInt($("#currPage").html());
	var total = parseInt($("#totalPage").html());
	if(curr < total){
		curr = curr + 1;
	}
	$("#classlist").html("");
	$.ajax({
		type : "post",
		url : "../classes/clalist",
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
				$("#classlist").append("<tr><td></td><td></td><td></td><td>" +
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

function classListPrev(){
	var curr = parseInt($("#currPage").html());
	if(curr > 1){
		curr = curr - 1;
	}
	$("#classlist").html("");
	$.ajax({
		type : "post",
		url : "../classes/clalist",
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
				$("#classlist").append("<tr><td></td><td></td><td></td><td>" +
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