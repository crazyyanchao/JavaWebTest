var addJosnData;
var CourseCodeForAll;
var CourseValueForAll;
function courseload(s){
	var len = s.length;
	for(var i = 0; i < parseInt(len); i++){
		var id = s[i].id;
		var courseCode = s[i].courseCode;
		var courseCodeValue = s[i].courseCodeValue;
		$("#courseManage").append("<tr><td>"+id+"</td><td><a class=\"course_name\">"+courseCodeValue+"</a><span style=\"display:none;\">"+courseCode+"</span></td><td>" +
				 "<a class=\"btn btn-success course_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
			     "<a class=\"btn btn-danger course_delete\" href=\"javascript:void();\"><i class=\"fa fa-trash-o\"></i></a>"+   //删除
			     "</td></tr>");
	}
}
function chapterload(s){
	var len = s.length;
	for(var i = 0; i < parseInt(len); i++){
		var id = s[i].chapterCode;
		var chapterCodeValue = s[i].chapterCodeValue;
		$("#courseManage").append("<tr><td>"+id+"</td><td>"+chapterCodeValue+"</td><td>" +
				 "<a class=\"btn btn-success chapter_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
			     "<a class=\"btn btn-info chapter_modify\" href=\"javascript:void();\"><i class=\"fa fa-edit\"></i></a>"+   //修改
			     "<a class=\"btn btn-danger chapter_delete\" href=\"javascript:void();\"><i class=\"fa fa-trash-o\"></i></a>"+   //删除
			     "</td></tr>");
	}
}
$(document).ready(function(){
	$("#CMtitle").html("课程列表");
	$("#CMbuttonGroup").append("<div class=\"bk-margin-5 btn-group\" style=\"float: right; right: -5px;\">" +
			"<button type=\"button\" class=\"btn btn-default\" onclick=\"courseManageFirst()\">首页</button>" +
			"<button type=\"button\" class=\"btn btn-default\" onclick=\"courseManagePrev()\">上一页</button>" +
			"<button type=\"button\" class=\"btn btn-default\" onclick=\"courseManageNext()\">下一页</button>" +
			"<button type=\"button\" class=\"btn btn-default\" onclick=\"courseManageLast()\">尾页</button></div>");
	$.ajax({
		type : "post",
		url : "../courseandchapter/courselist",
		data:{"currPage":"1",
			  "pageSize":"10"},
		success : function(data) {
			var resMsg = eval("("+data+")").resMsg;
			var resCode = eval("("+data+")").resCode;
			var data = eval("("+data+")").data;
			if(resCode == "000000"){
				var totalPages = data.totalPages;
				var currPage = data.currPage;
				$("#currPage").html(currPage);
	 			$("#totalPage").html(totalPages);
	 			var s = data.list;
	 			courseload(s);
			}else{
				$("#courseManage").append("<tr><td></td><td></td><td>" +
						 "<a class=\"btn btn-success course_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
					     "</td></tr>");
				swal("错误",resMsg,"error");
			}
		}
	});
});

/* 课程名称  点击 */
$(document).on("click",".course_name",function(e){
	CourseCodeForAll = "";
	CourseValueForAll = "";
	var x = $(this).next().html();
	CourseCodeForAll = x;
	CourseValueForAll = $(this).html();
	$("#ReturnToCourse").html("");
	$("#ReturnToCourse").append("<a href=\"javascript:void();\" onclick=\"courseReload()\" class=\"btn-setting\"><i class=\"fa fa-arrow-left\"></i></a>");
	$("#CMtitle").html("课程目录");
	$("#CMbuttonGroup").html("<h5 style=\"display: inline-block; margin-top: 15px;\">当前页面：</h5>" +
			"<h5 id=\"currPage\" style=\"display: inline-block; margin-top: 15px;\"></h5>&nbsp;&nbsp;&nbsp;&nbsp;" +
			"<h5 style=\"display: inline-block; margin-top: 15px;\">总页数：</h5>" +
			"<h5 id=\"totalPage\" style=\"display: inline-block; margin-top: 15px;\"></h5>");
	$("#CMbuttonGroup").append("<div class=\"bk-margin-5 btn-group\" style=\"float: right; right: -5px;\">" +
			"<button type=\"button\" class=\"btn btn-default\" onclick=\"chapterManageFirst()\">首页</button>" +
			"<button type=\"button\" class=\"btn btn-default\" onclick=\"chapterManagePrev()\">上一页</button>" +
			"<button type=\"button\" class=\"btn btn-default\" onclick=\"chapterManageNext()\">下一页</button>" +
			"<button type=\"button\" class=\"btn btn-default\" onclick=\"chapterManageLast()\">尾页</button></div>");
	$("#courseManage").html("");
	$.ajax({
		type : "post",
		url : "../courseandchapter/chapterlist",
		data:{"currPage":"1",
			  "pageSize":"10",
			  "courseCode":x},
		success : function(data) {
			var resMsg = eval("("+data+")").resMsg;
			var resCode = eval("("+data+")").resCode;
			var data = eval("("+data+")").data;
			if(resCode == "000000"){
				var totalPages = data.totalPages;
				var currPage = data.currPage;
				$("#currPage").html(currPage);
	 			$("#totalPage").html(totalPages);
	 			var s = data.list;
	 			chapterload(s);
			}else{
				swal("错误",resMsg,"error");
				$("#courseManage").append("<tr><td></td><td></td><td>" +
						 "<a class=\"btn btn-success chapter_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
					     "</td></tr>");
			}
		}
	});
	e.stopPropagation();
});

/* 课程  添加 */
$(document).on("click",".course_add",function(e){
	var x = $(this).next().html();
	$('#table_input').html("");
	$('#table_input').css({'position':'absolute','top':'25%','left':'20%','width':'900px;','z-index':'12','border':'1px solid #666'});
	$('#table_input').show();
	$("#table_input").append("<table class=\"table table-striped table-bordered bootstrap-datatable datatable\" style=\"\">"+
			"<thead><tr style=\"text-align: center;\"><th width=\"350px;\">序号</th>"+
			"<th width=\"450px;\">科目名称</th></tr></thead>"+
			"<tbody><tr><td>1</td><td><input class=\"form-control\"></td></tr></tbody></table>"+
			"<a class=\"btn btn-success\" href=\"javascript:void();\" style=\"margin-left: 50px;\" onclick=\"table_input_addButton()\"><i class=\"fa fa-search-plus\"></i></a>" +
			"<span class=\"label label-warning\" style=\"margin-left:20%;\">警告:科目名称中不能含有特殊符号(`)</span>"+
			"<button type=\"button\" class=\"btn btn-default\" style=\"float: right; margin-right: 50px;\" onclick=\"table_input_hide()\">取消</button>"+
			"<button type=\"button\" class=\"btn btn-default\" style=\"float: right; margin-right: 50px;\" onclick=\"addCourse()\">确认</button>");
	e.stopPropagation();
});

/* 课程 添加弹窗--添加按钮*/
function table_input_addButton(){
	var id = $("#table_input tbody tr").length + 1;
	var length = $("#table_input tbody tr").length;
	var x = $("#table_input tbody tr").first();
	for(var i = 1; i < length; i++){
		x = x.next();
	}
	var course = x.find("td").next().find("input").val();
	var courseAB = x.find("td").next().next().find("select").val();
	if(course == "" || course == null){
		swal("科目名称为空!");
		return false;
	}
	$("#table_input").children().find("tbody").parent().append("<tr><td>"+id+"</td><td><input class=\"form-control\"></td></tr>");
}

/* 课程  添加弹窗 确定 */
function addCourse(){
	addJosnData = "";
	var course;
	var length = $("#table_input tbody tr").length;
	var x = $("#table_input tbody tr").first();
	for(var i = 0; i < length; i++){
		course = x.find("td").next().find("input").val();
		if( parseInt(length) == 1){
			if(course == "" || course == null){
				swal("科目名称为空!");
				return false;
			}
		}
		addJosnData += course+"`";
		var xx = x.next();
		x = xx;
	}
	$.ajax({
		type : "post",
		url : "../courseandchapter/addCourse",
		data:{"data": addJosnData },
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				courseReload();
				table_input_hide();
				swal("成功",resMsg, "success");
			}else{
				swal("错误",resMsg,"error");
			}
		}
	});
}

/* 添加、修改弹窗  关闭*/
function table_input_hide(){
	$("#table_input").hide("slow");
}

/* 课程内容  删除 */
$(document).on("click",".course_delete",function(e){
	var courseCode = $(this).parent().prev().children("span").html();
	var x = $(this).parent().parent();
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
						url : "../courseandchapter/deletecourse",
						data:{"courseCode": courseCode },
						success : function(data) {
							var resMsg = eval("("+data+")").resMsg;
							var resCode = eval("("+data+")").resCode;
							if(resCode == "000000"){
								x.html("");
								swal("成功",resMsg,"success");
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

function courseReload(){
	$("#ReturnToCourse").html("");
	$("#CMtitle").html("课程列表");
	$("#courseManage").html("");
	$("#CMbuttonGroup").html("<h5 style=\"display: inline-block; margin-top: 15px;\">当前页面：</h5>" +
			"<h5 id=\"currPage\" style=\"display: inline-block; margin-top: 15px;\"></h5>&nbsp;&nbsp;&nbsp;&nbsp;" +
			"<h5 style=\"display: inline-block; margin-top: 15px;\">总页数：</h5>" +
			"<h5 id=\"totalPage\" style=\"display: inline-block; margin-top: 15px;\"></h5>");
	$("#CMbuttonGroup").append("<div class=\"bk-margin-5 btn-group\" style=\"float: right; right: -5px;\">" +
			"<button type=\"button\" class=\"btn btn-default\" onclick=\"courseManageFirst()\">First</button>" +
			"<button type=\"button\" class=\"btn btn-default\" onclick=\"courseManagePrev()\">Prev</button>" +
			"<button type=\"button\" class=\"btn btn-default\" onclick=\"courseManageNext()\">Next</button>" +
			"<button type=\"button\" class=\"btn btn-default\" onclick=\"courseManageLast()\">Last</button></div>");
	$.ajax({
		type : "post",
		url : "../courseandchapter/courselist",
		data:{"currPage":$("#currPage").html(),
			  "pageSize":"10"},
		success : function(data) {
			var resMsg = eval("("+data+")").resMsg;
			var resCode = eval("("+data+")").resCode;
			var data = eval("("+data+")").data;
			if(resCode == "000000"){
				var totalPages = data.totalPages;
				var currPage = data.currPage;
				$("#currPage").html(currPage);
	 			$("#totalPage").html(totalPages);
	 			var s = data.list;
	 			courseload(s);
			}else{
				swal("错误",resMsg,"error");
			}
		}
	});
}

function chapterReload(){
	$("#courseManage").html("");
	$.ajax({
		type : "post",
		url : "../courseandchapter/chapterlist",
		data:{"currPage":$("#currPage").html(),
			  "pageSize":"10",
			  "courseCode":CourseCodeForAll},
		success : function(data) {
			var resMsg = eval("("+data+")").resMsg;
			var resCode = eval("("+data+")").resCode;
			var data = eval("("+data+")").data;
			if(resCode == "000000"){
				var totalPages = data.totalPages;
				var currPage = data.currPage;
				$("#currPage").html(currPage);
	 			$("#totalPage").html(totalPages);
	 			var s = data.list;
	 			chapterload(s);
			}else{
				swal("错误",resMsg,"error");
				$("#courseManage").append("<tr><td></td><td></td><td>" +
						 "<a class=\"btn btn-success chapter_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
					     "</td></tr>");
			}
		}
	});
}

/* 章节  添加 */
$(document).on("click",".chapter_add",function(e){
	var x = $(this).next().html();
	$('#table_input').html("");
	$('#table_input').css({'position':'absolute','top':'25%','left':'20%','width':'900px;','z-index':'12','border':'1px solid #666'});
	$('#table_input').show();
	$("#table_input").append("<table class=\"table table-striped table-bordered bootstrap-datatable datatable\"><span id=\"course_code_all\" style=\"display:none;\">"+CourseCodeForAll+"</span>"+
			"<thead><tr style=\"text-align: center;\"><th width=\"180px;\">序号</th>"+
			"<th width=\"360px;\">章节名称</th>"+
			"<th width=\"360px;\">所属课程</th></tr></thead>"+
			"<tbody><tr><td>1</td><td><input class=\"form-control\"></td><td>"+CourseValueForAll+"</td></tr></tbody></table>"+
			"<a class=\"btn btn-success\" href=\"javascript:void();\" style=\"margin-left: 50px;\" onclick=\"table_input_chapter_addButton()\"><i class=\"fa fa-search-plus\"></i></a>" +
			"<span class=\"label label-warning\" style=\"margin-left:20%;\">警告:章节名称中不能含有特殊符号(`)</span>"+
			"<button type=\"button\" class=\"btn btn-default\" style=\"float: right; margin-right: 50px;\" onclick=\"table_input_hide()\">取消</button>"+
			"<button type=\"button\" class=\"btn btn-default\" style=\"float: right; margin-right: 50px;\" onclick=\"addChapter()\">确认</button>");
	e.stopPropagation();
});
/* 章节 添加弹窗--添加按钮*/
function table_input_chapter_addButton(){
	var id = $("#table_input tbody tr").length + 1;
	var length = $("#table_input tbody tr").length;
	var x = $("#table_input tbody tr").first();
	for(var i = 1; i < length; i++){
		x = x.next();
	}
	var course = x.find("td").next().find("input").val();
	if(course == "" || course == null){
		swal("章节名称为空!");
		return false;
	}
	$("#table_input").children().find("tbody").parent().append("<tr><td>"+id+"</td><td><input class=\"form-control\"></td><td>"+CourseValueForAll+"</td></tr>");
}
/* 章节 添加弹窗 确定 */
function addChapter(){
	addJosnData = "";
	var chapter;
	var courseCode = $("#course_code_all").html();
	var length = $("#table_input tbody tr").length;
	var x = $("#table_input tbody tr").first();
	for(var i = 0; i < length; i++){
		chapter = x.find("input").val();
		if( parseInt(length) == 1){
			if(chapter == "" || chapter == null){
				swal("章节名称为空!");
				return false;
			}
		}
		addJosnData += chapter+"`"+courseCode+"`";
		var xx = x.next();
		x = xx;
	}
	$.ajax({
		type : "post",
		url : "../courseandchapter/addChapter",
		data:{"data": addJosnData },
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				chapterReload();
				table_input_hide();
				swal("成功",resMsg, "success");
			}else{
				swal("错误",resMsg,"error");
			}
		}
	});
}

/* 章节  修改 */
$(document).on("click",".chapter_modify",function(e){
	var chapter = $(this).parent().prev().html();
	var id = $(this).parent().prev().prev().html();
	$('#table_input').html("");
	$('#table_input').css({'position':'absolute','top':'25%','left':'20%','width':'900px;','z-index':'12','border':'1px solid #666'});
	$('#table_input').show();
	$("#table_input").append("<table class=\"table table-striped table-bordered bootstrap-datatable datatable\">"+
			"<thead><tr style=\"text-align: center;\"><th width=\"180px;\">序号</th>"+
			"<th width=\"360px;\">科目名称</th>"+
			"<th width=\"360px;\">所属课程</th></tr></thead>"+
			"<tbody><tr><td>"+id+"</td><td><input class=\"form-control\" value=\""+chapter+"\"><span style=\"display:none;\">"+CourseCodeForAll+"</span></td><td>"+CourseValueForAll+"</td></tr></tbody></table>"+
			"<span class=\"label label-warning\" style=\"margin-left:20%;\">警告:科目名称中不能含有特殊符号(`)</span>"+
			"<button type=\"button\" class=\"btn btn-default\" style=\"float: right; margin-right: 50px;\" onclick=\"table_input_hide()\">取消</button>"+
			"<button type=\"button\" class=\"btn btn-default\" style=\"float: right; margin-right: 50px;\" onclick=\"modifyChapter()\">确认</button>");
	e.stopPropagation();
});

/* 章节内容  删除 */
$(document).on("click",".chapter_delete",function(e){
	var x = $(this).parent().parent();
	var cod = $(this).parent().prev().prev().html();
	$.ajax({
		type : "post",
		url : "../courseandchapter/deletechapter",
		data:{"courseCode": CourseCodeForAll,"chapterCode":cod },
		success : function(data) {
			var resMsg = eval("("+data+")").resMsg;
			var resCode = eval("("+data+")").resCode;
			if(resCode == "000000"){
				x.html("");
				swal("成功",resMsg,"success");
			}else{
				swal("错误",resMsg,"error");
			}
		}
	});
	e.stopPropagation();
});

function modifyChapter(){
	var chapterCode = $("#table_input table tbody td").first().html();
	var chapterValue = $("#table_input table tbody td input").val();
	$.ajax({
		type : "post",
		url : "../courseandchapter/modifychapter",
		data:{"chapterValue": chapterValue,"courseCode":CourseCodeForAll,"chapterCode":chapterCode },
		success : function(data) {
			var resMsg = eval("("+data+")").resMsg;
			var resCode = eval("("+data+")").resCode;
			if(resCode == "000000"){
				swal("成功",resMsg,"success");
			}else{
				swal("错误",resMsg,"error");
			}
		}
	});
}

function courseManageFirst(){
	$("#courseManage").html("");
	$.ajax({
		type : "post",
		url : "../courseandchapter/courselist",
		data:{"currPage":"1",
			  "pageSize":"10"},
		success : function(data) {
			var resMsg = eval("("+data+")").resMsg;
			var resCode = eval("("+data+")").resCode;
			var data = eval("("+data+")").data;
			if(resCode == "000000"){
				var totalPages = data.totalPages;
				var currPage = data.currPage;
				$("#currPage").html(currPage);
	 			$("#totalPage").html(totalPages);
	 			var s = data.list;
	 			courseload(s);
			}else{
				swal("错误",resMsg,"error");
			}
		}
	});
}

function courseManageLast(){
	$("#courseManage").html("");
	$.ajax({
		type : "post",
		url : "../courseandchapter/courselist",
		data:{"currPage":$("#totalPage").html(),
			  "pageSize":"10"},
		success : function(data) {
			var resMsg = eval("("+data+")").resMsg;
			var resCode = eval("("+data+")").resCode;
			var data = eval("("+data+")").data;
			if(resCode == "000000"){
				var totalPages = data.totalPages;
				var currPage = data.currPage;
				$("#currPage").html(currPage);
	 			$("#totalPage").html(totalPages);
	 			var s = data.list;
	 			courseload(s);
			}else{
				swal("错误",resMsg,"error");
			}
		}
	});
}

function courseManageNext(){
	var curr = parseInt($("#currPage").html());
	var total = parseInt($("#totalPage").html());
	if(curr < total){
		curr = curr + 1;
	}
	$("#courseManage").html("");
	$.ajax({
		type : "post",
		url : "../courseandchapter/courselist",
		data:{"currPage":curr,
			  "pageSize":"10"},
		success : function(data) {
			var resMsg = eval("("+data+")").resMsg;
			var resCode = eval("("+data+")").resCode;
			var data = eval("("+data+")").data;
			if(resCode == "000000"){
				var totalPages = data.totalPages;
				var currPage = data.currPage;
				$("#currPage").html(currPage);
	 			$("#totalPage").html(totalPages);
	 			var s = data.list;
	 			courseload(s);
			}else{
				swal("错误",resMsg,"error");
			}
		}
	});
}

function courseManagePrev(){
	var curr = parseInt($("#currPage").html());
	if(curr > 1){
		curr = curr - 1;
	}
	$("#courseManage").html("");
	$.ajax({
		type : "post",
		url : "../courseandchapter/courselist",
		data:{"currPage":curr,
			  "pageSize":"10"},
		success : function(data) {
			var resMsg = eval("("+data+")").resMsg;
			var resCode = eval("("+data+")").resCode;
			var data = eval("("+data+")").data;
			if(resCode == "000000"){
				var totalPages = data.totalPages;
				var currPage = data.currPage;
				$("#currPage").html(currPage);
	 			$("#totalPage").html(totalPages);
	 			var s = data.list;
	 			courseload(s);
			}else{
				swal("错误",resMsg,"error");
			}
		}
	});
}

function chapterManageFirst(){
	$("#courseManage").html("");
	$.ajax({
		type : "post",
		url : "../courseandchapter/chapterlist",
		data:{"currPage":"1",
			  "pageSize":"10",
			  "courseCode":CourseCodeForAll},
		success : function(data) {
			var resMsg = eval("("+data+")").resMsg;
			var resCode = eval("("+data+")").resCode;
			var data = eval("("+data+")").data;
			if(resCode == "000000"){
				var totalPages = data.totalPages;
				var currPage = data.currPage;
				$("#currPage").html(currPage);
	 			$("#totalPage").html(totalPages);
	 			var s = data.list;
	 			chapterload(s);
			}else{
				swal("错误",resMsg,"error");
				$("#courseManage").append("<tr><td></td><td></td><td>" +
						 "<a class=\"btn btn-success chapter_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
					     "</td></tr>");
			}
		}
	});
}

function chapterManageLast(){
	$("#courseManage").html("");
	$.ajax({
		type : "post",
		url : "../courseandchapter/chapterlist",
		data:{"currPage":$("#totalPage").html(),
			  "pageSize":"10",
			  "courseCode":CourseCodeForAll},
		success : function(data) {
			var resMsg = eval("("+data+")").resMsg;
			var resCode = eval("("+data+")").resCode;
			var data = eval("("+data+")").data;
			if(resCode == "000000"){
				var totalPages = data.totalPages;
				var currPage = data.currPage;
				$("#currPage").html(currPage);
	 			$("#totalPage").html(totalPages);
	 			var s = data.list;
	 			chapterload(s);
			}else{
				swal("错误",resMsg,"error");
				$("#courseManage").append("<tr><td></td><td></td><td>" +
						 "<a class=\"btn btn-success chapter_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
					     "</td></tr>");
			}
		}
	});
}

function chapterManageNext(){
	var curr = parseInt($("#currPage").html());
	var total = parseInt($("#totalPage").html());
	if(curr < total){
		curr = curr + 1;
	}
	$("#courseManage").html("");
	$.ajax({
		type : "post",
		url : "../courseandchapter/chapterlist",
		data:{"currPage":curr,
			  "pageSize":"10",
			  "courseCode":CourseCodeForAll},
		success : function(data) {
			var resMsg = eval("("+data+")").resMsg;
			var resCode = eval("("+data+")").resCode;
			var data = eval("("+data+")").data;
			if(resCode == "000000"){
				var totalPages = data.totalPages;
				var currPage = data.currPage;
				$("#currPage").html(currPage);
	 			$("#totalPage").html(totalPages);
	 			var s = data.list;
	 			chapterload(s);
			}else{
				swal("错误",resMsg,"error");
				$("#courseManage").append("<tr><td></td><td></td><td>" +
						 "<a class=\"btn btn-success chapter_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
					     "</td></tr>");
			}
		}
	});
}

function chapterManagePrev(){
	var curr = parseInt($("#currPage").html());
	if(curr > 1){
		curr = curr - 1;
	}
	$("#courseManage").html("");
	$.ajax({
		type : "post",
		url : "../courseandchapter/chapterlist",
		data:{"currPage":curr,
			  "pageSize":"10",
			  "courseCode":CourseCodeForAll},
		success : function(data) {
			var resMsg = eval("("+data+")").resMsg;
			var resCode = eval("("+data+")").resCode;
			var data = eval("("+data+")").data;
			if(resCode == "000000"){
				var totalPages = data.totalPages;
				var currPage = data.currPage;
				$("#currPage").html(currPage);
	 			$("#totalPage").html(totalPages);
	 			var s = data.list;
	 			chapterload(s);
			}else{
				swal("错误",resMsg,"error");
				$("#courseManage").append("<tr><td></td><td></td><td>" +
						 "<a class=\"btn btn-success chapter_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
					     "</td></tr>");
			}
		}
	});
}