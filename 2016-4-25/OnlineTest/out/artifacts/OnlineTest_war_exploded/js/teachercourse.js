var addJosnData = "";
var courseCodeForAll = "";
function Reload(){
	$("#courseManage").html("");
	$.ajax({
		type : "post",
		url : "../teachercourse/courselist",
		success : function(data) {
			var resMsg = eval("("+data+")").resMsg;
			var resCode = eval("("+data+")").resCode;
			var data = eval("("+data+")").data;
			if(resCode == "000000"){
	 			var s = data.list;
	 			var len = s.length;
	 			for(var i = 0; i < parseInt(len); i++){
	 				var id = i + 1;
	 				var value = s[i].value;
	 				$("#courseManage").append("<tr><td>"+id+"</td><td>"+value+"</td><td>" +
	 						 "<a class=\"btn btn-success course_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
	 					     "<a class=\"btn btn-danger course_delete\" href=\"javascript:void();\"><i class=\"fa fa-trash-o\"></i></a>"+   //删除
	 					     "</td></tr>");
	 			}
			}else{
				swal("错误",resMsg,"error");
				$("#courseManage").append("<tr><td></td><td></td><td>" +
						 "<a class=\"btn btn-success course_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
					     "</td></tr>");
			}
		}
	});
}
$(document).ready(function(){
	$.ajax({
		type : "post",
		url : "../teachercourse/courselist",
		success : function(data) {
			var resMsg = eval("("+data+")").resMsg;
			var resCode = eval("("+data+")").resCode;
			var data = eval("("+data+")").data;
			if(resCode == "000000"){
	 			var s = data.list;
	 			var len = s.length;
	 			for(var i = 0; i < parseInt(len); i++){
	 				var id = i + 1;
	 				var value = s[i].value;
	 				$("#courseManage").append("<tr><td>"+id+"</td><td>"+value+"</td><td>" +
	 						 "<a class=\"btn btn-success course_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
	 					     "<a class=\"btn btn-danger course_delete\" href=\"javascript:void();\"><i class=\"fa fa-trash-o\"></i></a>"+   //删除
	 					     "</td></tr>");
	 			}
			}else{
				swal("错误",resMsg,"error");
				$("#courseManage").append("<tr><td></td><td></td><td>" +
						 "<a class=\"btn btn-success course_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
					     "</td></tr>");
			}
		}
	});
});

/* 课程  添加 */
$(document).on("click",".course_add",function(e){
	var x = $(this).next().html();
	$('#table_input').html("");
	$('#table_input').css({'position':'absolute','top':'25%','left':'20%','width':'900px;','z-index':'12','border':'1px solid #666'});
	$('#table_input').show();
	$("#table_input").append("<table class=\"table table-striped table-bordered bootstrap-datatable datatable\" style=\"\">"+
		"<thead><tr style=\"text-align: center;\"><th width=\"180px;\">序号</th>"+
		"<th width=\"360px;\">科目名称</th>"+
//		"<th width=\"360px;\">所属学院</th></tr></thead>"+
		"<tbody><tr><td>1</td><td><input class=\"form-control\" onclick=\"chooseCourse()\"></td></tr></tbody></table>"+
		"<a class=\"btn btn-success\" href=\"javascript:void();\" style=\"margin-left: 50px;\" onclick=\"table_input_addButton()\"><i class=\"fa fa-search-plus\"></i></a>" +
//		"<span class=\"label label-warning\" style=\"margin-left:20%;\">警告:科目名称中不能含有特殊符号(`)</span>"+
		"<button type=\"button\" class=\"btn btn-default\" style=\"float: right; margin-right: 50px;\" onclick=\"table_input_hide()\">取消</button>"+
		"<button type=\"button\" class=\"btn btn-default\" style=\"float: right; margin-right: 50px;\" onclick=\"addCourse()\">确认</button>");	
	e.stopPropagation();
});

/* 添加、修改弹窗  关闭*/
function table_input_hide(){
	$("#table_input").hide("slow");
}

/* 课程  添加弹窗 确定 */
function addCourse(){
	addJosnData = "";
	var course;
	var courseCode;
	var length = $("#table_input tbody tr").length;
	var x = $("#table_input tbody tr").first();
	for(var i = 0; i < length; i++){
		course = x.find("td").next().find("input").val();
		courseCode = x.find("td").next().find("input").next().html();
		if( parseInt(length) == 1){
			if(course == "" || course == null){
				swal("科目名称为空!");
				return false;
			}
		}
		addJosnData += courseCode+"-";
		var xx = x.next();
		x = xx;
	}
	$.ajax({
		type : "post",
		url : "../teachercourse/addcourse",
		data:{"data": addJosnData },
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				table_input_hide();
				Reload();
				swal("成功",resMsg, "success");
			}else{
				table_input_hide();
				swal("错误",resMsg,"error");
			}
		}
	});
}

function chooseCourse(){
	$('#table_choosecourse').html("");
	$('#table_choosecourse').css({'position':'absolute','top':'25%','left':'20%','width':'900px;','z-index':'12','border':'1px solid #666'});
	$('#table_choosecourse').show();
	$.ajax({
		type : "post",
		url : "../courseandchapter/courselist",
		data:{"currPage": "1", "pageSize":"20"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			data = eval("("+data+")").data;
			if(resCode == "000000"){
				var list = data.list;
				var len = list.length;
				var x = "";
				for(var i = 0; i < parseInt(len); i++){
					x += "<a class=\"courseSelected\" style=\"margin: 10px 25px;\">"+list[i].courseCodeValue+"</a>" +
						 "<span style=\"display:none;\">"+list[i].courseCode+"</span>";
					if( (i + 1) % 3 == 0 ){
						x += "</li><li>";
					}
				}
				x += "</li>";
				$("#table_choosecourse").append("<div class=\"panel\" style=\"width: 600px; height: 350px;\"><div class=\"panel-heading\" style=\"margin-top: 15px;\">"+
						"<input id=\"singleChoose_search_input\" class=\"form-control\" placeholder=\"请输入要查找的科目---模糊查找\" style=\"margin-left: 15%;width: 300px; display: inline;\">"+
						"<button type=\"button\" onclick=\"searchCourse()\" class=\"btn btn-default\" style=\"margin-left: 20px;margin-bottom: 5px;\">查询</button></div><hr style=\"margin-top: 10px;margin-bottom: 0;\">"+
						"<div id=\"singleChoose_course_search\" class=\"panel-body\" style=\"height:254px;width:598px;overflow: scroll;\"><ul style=\"list-style-type:none;\">" + x +
						"</ul></div>"+
						"<div class=\"panel-footer\" style=\"height:50px;\"><button onclick=\"closeCourseSelection()\" class=\"btn btn-default\" style=\"float:right; margin-right:20px;\">关闭</button></div></div>");
			}else{
				swal("错误",resMsg,"error");
				$("#table_choosecourse").append("<div class=\"panel\" style=\"width: 600px; height: 350px;\"><div class=\"panel-heading\" style=\"margin-top: 15px;\">"+
						"<input id=\"singleChoose_search_input\" class=\"form-control\" placeholder=\"请输入要查找的科目---模糊查找\" style=\"margin-left: 15%;width: 300px; display: inline;\">"+
						"<button type=\"button\" onclick=\"searchCourse()\" class=\"btn btn-default\" style=\"margin-left: 20px;margin-bottom: 5px;\">查询</button></div><hr style=\"margin-top: 10px;margin-bottom: 0;\">"+
						"<div id=\"singleChoose_course_search\" class=\"panel-body\" style=\"height:254px;width:598px;overflow: scroll;\">" + 
						"</div>"+
						"<div class=\"panel-footer\" style=\"height:50px;\"><button onclick=\"closeCourseSelection()\" class=\"btn btn-default\" style=\"float:right; margin-right:20px;\">关闭</button></div></div>");
			}
		}
	});
}

function closeCourseSelection(){
	$('#table_choosecourse').hide("slow");
}

/* 绑定course的查询结果 */
$(document).on("click",".courseSelected",function(e){
	var course = $(this).html();
	courseCodeForAll = $(this).next().html();
	$("#table_input tbody tr").last().find("td").last().find("input").val(course);
	$("#table_input tbody tr").last().find("td").append("<span style=\"display:none;\">"+courseCodeForAll+"</span>");
	$("#table_choosecourse").hide();
	e.stopPropagation();
});

function table_input_addButton(){
	var course = $("#table_input tbody tr").last().find("td").last().find("input").val();
	$("#table_input tbody").append("<tr><td>"+($("#table_input tbody tr").size()+1)+"</td><td><input class=\"form-control\" onclick=\"chooseCourse()\"></td></tr>");
}

/* 课程 删除 */
$(document).on("click",".course_delete",function(e){
	var x = $(this).parent().prev().prev().html();
	swal(
			{   
				title: "确定删除?",   
				text: "一旦删除，该题将不再出现!",
				type: "warning",   
				showCancelButton: true,   
				confirmButtonColor: "#DD6B55",   
				confirmButtonText: "是的, 删除!",   
				cancelButtonText: "不,暂时不删除!",   
				closeOnConfirm: false,   
				closeOnCancel: false 
			},
			function(isConfirm){   
				if (isConfirm){
					$.ajax({
						type : "post",
						url : "../teachercourse/deletecourse",
						data:{"num": x },
						success : function(data) {
							var resCode = eval("("+data+")").resCode;
							var resMsg = eval("("+data+")").resMsg;
							if(resCode == "000000"){
								Reload();
								swal("成功",resMsg, "success");
							}else{
								swal("错误",resMsg,"error");
							}
						}
					});
				} 
				else{     
					swal("取消操作", "你取消了删除", "error");   
				} 
			}
		);
	e.stopPropagation();
});