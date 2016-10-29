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
			var code = s[i].firstCode;
			var value = s[i].firstCodeValue;
			$("#departmentlist").append("<tr><td>"+value+"</td><td>" +
			 "<a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
		     "<a class=\"btn btn-danger table_input_delete\" href=\"javascript:void();\"><i class=\"fa fa-trash-o\"></i></a>" +
		     "<span style=\"display:none\">"+code+"</span></td></tr>");
		} 
 }
$(document).ready(function(){
	$.ajax({
		type : "post",
		url : "../academy/academylist",
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
					$("#departmentlist").append("<tr><td></td><td>" +
							 "<a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>" +
							 "</td></tr>");
					swal("错误",resMsg,"error");
				}
			}
		}
	});
});

function reload(){
	$("#departmentlist").html("");
	$.ajax({
		type : "post",
		url : "../academy/academylist",
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				var s =  eval("("+data+")").data.list;
				load(s);
			}else{
				$("#departmentlist").append("<tr><td></td><td>" +
						 "<a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>" +
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
			"<th width=\"\">学院</th></tr></thead>"+
			"<tbody><tr><td>1</td><td><input class=\"form-control\"></td></tr></tbody></table>"+
			"<a class=\"btn btn-success\" href=\"javascript:void();\" style=\"margin-left: 50px;\" onclick=\"table_input_addButton()\"><i class=\"fa fa-search-plus\"></i></a>" +
			"<a class=\"btn btn-success\" href=\"javascript:void();\" style=\"margin-left: 30px;\" onclick=\"table_input_mineButton()\"><i class=\"fa fa-minus-square\"></i></a>" +
			"<span class=\"label label-warning\" style=\"margin-left:15%;\">警告:题目和答案选项中不能含有特殊符号(`)</span>"+
			"<button type=\"button\" class=\"btn btn-default\" style=\"float: right; margin-right: 50px;\" onclick=\"table_input_hide()\">取消</button>"+
			"<button type=\"button\" class=\"btn btn-default\" style=\"float: right; margin-right: 50px;\" onclick=\"addAcademy()\">确认</button>");
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
				url : "../academy/deleteacademy",
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
						url : "../academy/deleteacademy",
						data:{"code": x },
						success : function(data) {
							var resCode = eval("("+data+")").resCode;
							var resMsg = eval("("+data+")").resMsg;
							if(resCode == "000000"){
								swal("成功",resMsg, "success");
								reload();
								table_input_hide();
							}else{
								singleChooseReload();
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
	var academy = x.find("td").next().find("input").val();
	if(academy == "" || academy == null){
		if(lowerIe()){ alert("学院为空!"); }else{ swal("学院为空!");}
		return false;
	}
	$("#table_input").children().find("tbody").parent().append("<tr><td>"+id+"</td>"+
			"<td><input class=\"form-control\"></td></tr>");
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

function addAcademy(){
	var jsondata = "";
	var length = $("#table_input tbody tr").length;
	var x = $("#table_input tbody tr").first();
	for(var i = 0; i < length;i ++){
		var academy = x.find("td").next().find("input").val();
		if(academy == "" || academy == null){
			if(lowerIe()){ alert("第"+(i+1)+"行学院为空!"); }else{ swal("第"+(i+1)+"行学院为空!");}
			return false;
		}
		if( i == 0){
			jsondata = academy;
		}else{
			jsondata += "`" + academy;
		}
		x = x.next();
	}
	$.ajax({
		type : "post",
		url : "../academy/addacademy",
		data:{"data": jsondata },
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				reload();
				table_input_hide();
				if(lowerIe()){ alert(resMsg); }else{ swal("成功",resMsg, "success");}
			}else{
				reload();
				if(lowerIe()){ alert(resMsg); }else{ swal("错误",resMsg,"error");}
			}
		}
	});
}