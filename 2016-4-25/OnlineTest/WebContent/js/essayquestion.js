var courseCodeForAll;
var chapterCodeForAll;
var addJosnData;
var levelEasy = 2001;
var levelNormal = 2002;
var levelHard = 2003;
function load(data){
	var totalPages = data.totalPages;
	var currPage = data.currPage;
	var len = data.list.length;
	var s = data.list;
	$("#currPage").html(currPage);
	$("#totalPage").html(totalPages);
	for(var i = 0; i < parseInt(len); i++){
		var id = s[i].id;
		var course = s[i].course;
		var chapter = s[i].chapter;
		var level = s[i].level;
		var title = s[i].title;
		var answer = s[i].answer;
		if(course.length > 14){
			var xx = course.substr(0,14);
			course = "<td>"+xx+"<a class=\"table_block\">..more..</a><div style=\"display: none;\" class=\"table_block_hide\">"+
			"<table class=\"table table-striped table-bordered bootstrap-datatable datatable\">"+
			"<tr><td>"+course+"</td></tr></table></div>"+"</td>";
		}else{
			course = "<td>"+course+"</td>";
		}
		if(chapter.length > 8){
			var xx = chapter.substr(0,8);
			chapter = "<td>"+xx+"<a class=\"table_block\">..more..</a><div style=\"display: none;\" class=\"table_block_hide\">"+
			"<table class=\"table table-striped table-bordered bootstrap-datatable datatable\">"+
			"<tr><td>"+chapter+"</td></tr></table></div>"+"</td>";
		}else{
			chapter = "<td>"+chapter+"</td>";
		}
		if(title.length > 30){
			var xx = title.substr(0,28);
			title = "<td>"+xx+"<a class=\"table_block\">..more..</a><div style=\"display: none;\" class=\"table_block_hide\">"+
			"<table class=\"table table-striped table-bordered bootstrap-datatable datatable\">"+
			"<tr><td>"+title+"</td></tr></table></div>"+"</td>";
		}else{
			title = "<td>"+title+"</td>";
		}
		if(answer.length > 26){
			var xx = answer.substr(0,24);
			answer = "<td>"+xx+"<a class=\"table_block\">..more..</a><div style=\"display: none;\" class=\"table_block_hide\">"+
			"<table class=\"table table-striped table-bordered bootstrap-datatable datatable\">"+
			"<tr><td>"+answer+"</td></tr></table></div>"+"</td>";
		}else{
			answer = "<td>"+answer+"</td>";
		}
		$("#essayquestion").append("<tr><td>"+id+"</td>"+ course + chapter + "<td>"+level+"</td>"+ title +  answer +
			 "<td><a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
		     "<a class=\"btn btn-info table_input_modify\" href=\"javascript:void();\"><i class=\"fa fa-edit\"></i></a>"+   //修改
		     "<a class=\"btn btn-danger table_input_delete\" href=\"javascript:void();\"><i class=\"fa fa-trash-o\"></i></a>"+   //删除
		     "</td></tr>");
	}
}

$(document).ready(function(){
	$(document).on("click",function(e){
		var x = e.pageX;
		var y = e.pageY;
		var offset = $("#table_small").offset();
		var h = $("#table_small").innerHeight;
		var w = $("#table_small").innerWidth;
		var l = offset.left;
		var r = offset.left + w;
		var t = offset.top;
		var b = offset.top + h;
		if( (x < r)&&(x > l)&&(y > t)&&(y < b) ){
		}else{
			$("#table_small").hide("slow");
		}
		e.stopPropagation();
	});
	$.ajax({
		type : "post",
		url : "../essayquestion/essayquestionlist",
		data:{"currPage":"1",
			  "pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			data = eval("("+data+")").data;
			if(resCode == "000000"){
				load(data);
			}else{
				$("#essayquestion").append("<tr><td></td><td></td><td></td><td></td><td></td><td></td>"+
						 "<td><a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
					     "</td></tr>");
				swal("错误",resMsg,"error");
			}
		}  
	});
});
/* ready end */

/* 添加、修改弹窗  关闭*/
function table_input_hide(){
	$("#table_input").hide("slow");
}

/* 修改弹窗  确定 */
function table_modify_confirm(){
	var id = $("#table_input").children().find("td").html();
	var title = $("#table_input").children().find("input").val();
	var ans = $("#table_input").children().find("input").parent().next().find("input").val();
	$.ajax({
		type : "post",
		url : "../essayquestion/modifyessayquestion",
		data:{"id":id,"title":title,"ans":ans},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				essayQuestionReload();
				swal("成功",resMsg, "success");
				table_input_hide();
			}else{
				swal("错误",resMsg, "error");
			}
		}
	});
}

/* 缩写显示  事件绑定 */
$(document).on("click",".table_block",function(e){
	var xx = $(this).next().html();
	$('#table_small').html("");
	$('#table_small').css({'position':'absolute','top':e.pageY,'left':e.pageX,'z-index':'10'});
	$('#table_small').show();
	$("#table_small").append(xx);
	e.stopPropagation();
});

/*添加按钮*/
$(document).on("click",".table_input_add",function(e){
	$('#table_input').html("");
	$('#table_input').css({'position':'absolute','top':'25%','left':'3px','z-index':'12'});
	$('#table_input').show();
	$("#table_input").append("<table class=\"table table-striped table-bordered bootstrap-datatable datatable\">"+
			"<thead><tr style=\"text-align: center;\">"+
			"<th width=\"10%;\">题号</th>"+
			"<th width=\"15%\">科目</th><th width=\"15%\">章节</th><th width=\"10%\">难度等级</th>"+
			"<th>题目</th><th width=\"25%\">答案</th></tr></thead>"+
			"<tbody><tr><td><input class=\"form-control\" value=\"1\"></td><td><input class=\"form-control\" onclick=\"showCourseSelectPanel()\"></td>"+
			"<td><input class=\"form-control\" onclick=\"showChapterSelectPanel()\"></td>" +
			"<td><select id=\"level_select\" class=\"form-control\"><option>请选择</option><option value=\""+levelEasy+"\">简单</option><option value=\""+levelNormal+"\">一般</option><option value=\""+levelHard+"\">难</option></select></td>"+
			"<td><input class=\"form-control\"></td>" +
			"<td><input class=\"form-control\"></td></tr></tbody></table>"+
			"<a class=\"btn btn-success\" href=\"javascript:void();\" style=\"margin-left: 50px;\" onclick=\"table_input_addButton()\"><i class=\"fa fa-plus-square\"></i></a>" +
			"<a class=\"btn btn-success\" href=\"javascript:void();\" style=\"margin-left: 30px;\" onclick=\"table_input_mineButton()\"><i class=\"fa fa-minus-square\"></i></a>" +
			"<span class=\"label label-warning\" style=\"margin-left:30%;\">警告:题目和答案选项中不能含有特殊符号(`)</span>"+
			"<button type=\"button\" class=\"btn btn-default\" style=\"float: right; margin-right: 50px;\" onclick=\"table_input_hide()\">取消</button>"+
			"<button type=\"button\" class=\"btn btn-default\" style=\"float: right; margin-right: 50px;\" onclick=\"addEssayQuestion()\">确认</button>");
	e.stopPropagation();
});

/*修改按钮*/
$(document).on("click",".table_input_modify",function(e){
	var course = $(this).parent().parent().children().first().next().find("td").html();
	var chapter = $(this).parent().parent().children().first().next().next().find("td").html();
	var level = $(this).parent().parent().children().first().next().next().next().find("td").html();
	var title = $(this).parent().parent().children().first().next().next().next().next().find("td").html();
	var ans = $(this).parent().parent().children().first().next().next().next().next().next().find("td").html();
	if(typeof(course) == "undefined"){
		course = $(this).parent().parent().children().first().next().html();
	}
	if(typeof(chapter) == "undefined"){
		chapter = $(this).parent().parent().children().first().next().next().html();
	}
	if(typeof(level) == "undefined"){
		level = $(this).parent().parent().children().first().next().next().next().html();
	}
	if(typeof(title) == "undefined"){
		title = $(this).parent().parent().children().first().next().next().next().next().html();
	}
	if(typeof(ans) == "undefined"){
		ans = $(this).parent().parent().children().first().next().next().next().next().next().html();
	}
	$('#table_input').html("");
	$('#table_input').css({'position':'absolute','top':'25%','left':'3px','width':'100%','z-index':'12'});
	$('#table_input').show();
	$("#table_input").append("<table class=\"table table-striped table-bordered bootstrap-datatable datatable\">"+
			"<thead><tr style=\"text-align: center;\">"+
			"<th width=\"10%\">题号</th>"+
			"<th width=\"15%\">科目</th><th width=\"15%\">章节</th><th width=\"10%\">难度等级</th>"+
			"<th>题目</th><th width=\"25%\">答案</th></tr></thead>"+
			"<tbody><tr><td>"+$(this).parent().parent().children().first().html()+"</td>"+
			"<td>"+course+"</td>"+"<td>"+chapter+"</td>"+"<td>"+level+"</td>"+
			"<td><input class=\"form-control\" value=\""+title+"\"></td>"+
			"<td><input class=\"form-control\" value=\""+ans+"\"></td></tr></tbody></table>"+
			"<span class=\"label label-warning\" style=\"margin-left:30%;\">警告:题目和答案选项中不能含有特殊符号(`)</span>"+
			"<button type=\"button\" class=\"btn btn-default\" style=\"float: right; margin-right: 50px;\" onclick=\"table_input_hide()\">取消</button>"+
			"<button type=\"button\" class=\"btn btn-default\" style=\"float: right; margin-right: 50px;\" onclick=\"table_modify_confirm()\">确认</button>");
	e.stopPropagation();
});

/*删除按钮*/
$(document).on("click",".table_input_delete",function(e){
	var x = $(this).parent().parent().children().first().html();
	var xx = $(this).parent().parent();
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
					url : "../essayquestion/deleteessayquestion",
					data:{"id": x },
					success : function(data) {
						var resCode = eval("("+data+")").resCode;
						var resMsg = eval("("+data+")").resMsg;
						if(resCode == "000000"){
							xx.html("");
							swal("成功",resMsg, "success");
						}else{
							singleChooseReload();
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

/* 添加弹窗--添加按钮*/
function table_input_addButton(){
	var id = $("#table_input tbody tr").length + 1;
	var length = $("#table_input tbody tr").length;
	var x = $("#table_input tbody tr").first();
	for(var i = 1; i < length;i ++){
		x = x.next();
	}
	var course = x.find("td").next().find("input").next().html();
	var chapter = x.find("td").next().next().find("input").next().html();
	var level = x.find("td").next().next().next().find("select").val();
	var title = x.find("td").next().next().next().next().find("input").val();
	var ans = x.find("td").next().next().next().next().next().find("input").val();
	if(chapter == "" || chapter == null){
		swal("章节为空!");
		return false;
	}else if( parseInt(level) != levelEasy && parseInt(level) != levelNormal && parseInt(level) != levelHard){
		swal("难度等级为空!");
		return false;
	}else if(title == "" || title == null){
		swal("题目为空!");
		return false;
	}else if(ans == "" || ans == null){
		swal("答案为空!");
		return false;
	}
	$("#table_input").children().find("tbody").parent().append("<tr><td><input class=\"form-control\" value=\""+id+"\"></td><td><input class=\"form-control\" onclick=\"showCourseSelectPanel()\"></td>"+
			"<td><input class=\"form-control\" onclick=\"showChapterSelectPanel()\"></td>" +
			"<td><select id=\"level_select\" class=\"form-control\"><option>请选择</option><option value=\""+levelEasy+"\">简单</option><option value=\""+levelNormal+"\">一般</option><option value=\""+levelHard+"\">难</option></select></td>"+
			"<td><input class=\"form-control\"></td>" +
			"<td><input class=\"form-control\"></td></tr>");
}

/* 下一页  */
function essayQuestionNext(){
	var curr = parseInt($("#currPage").html());
	var total = parseInt($("#totalPage").html());
	if(curr < total){
		curr = curr + 1;
	}
	$("#essayquestion").html("");
	$.ajax({
		type : "post",
		url : "../essayquestion/essayquestionlist",
		data:{"currPage": curr,
			  "pageSize": "10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			data = eval("("+data+")").data;
			if(resCode == "000000"){
				load(data);
			}else{
				swal("错误",resMsg,"error");
			}
		}  
	});
}

/* 上一页  */
function essayQuestionPrev(){
	var curr = parseInt($("#currPage").html());
	if(curr > 1){
		curr = curr - 1;
	}
	$("#essayquestion").html("");
	$.ajax({
		type : "post",
		url : "../essayquestion/essayquestionlist",
		data:{"currPage": curr,
			  "pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			data = eval("("+data+")").data;
			if(resCode == "000000"){
				load(data);
			}else{
				swal("错误",resMsg,"error");
			}
		}  
	});
}

/* 第一页  */
function essayQuestionFirst(){
	$("#essayquestion").html("");
	$.ajax({
		type : "post",
		url : "../essayquestion/essayquestionlist",
		data:{"currPage": "1",
			  "pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			data = eval("("+data+")").data;
			if(resCode == "000000"){
				load(data);
			}else{
				swal("错误",resMsg,"error");
			}
		}  
	});
}

/* 最后一页  */
function essayQuestionLast(){
	$("#essayquestion").html("");
	$.ajax({
		type : "post",
		url : "../essayquestion/essayquestionlist",
		data:{"currPage": $("#totalPage").html(),
			  "pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			data = eval("("+data+")").data;
			if(resCode == "000000"){
				load(data);
			}else{
				swal("错误",resMsg,"error");
			}
		}  
	});
}

/* 重新载入  */
function essayQuestionReload(){
	$("#essayquestion").html("");
	$.ajax({
		type : "post",
		url : "../essayquestion/essayquestionlist",
		data:{"currPage": $("#currPage").html(),
			  "pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			data = eval("("+data+")").data;
			if(resCode == "000000"){
				load(data);
			}else{
				$("#essayquestion").append("<tr><td></td><td></td><td></td><td></td><td></td><td></td>"+
						 "<td><a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
					     "</td></tr>");
				swal("错误",resMsg,"error");
			}
		}  
	});
}

/* 添加弹窗 确定 */
function addEssayQuestion(){
	addJosnData = "";
	var courseCode;
	var chapterCode;
	var level ;
	var title;
	var ans;
	var length = $("#table_input tbody tr").length;
	var x = $("#table_input tbody tr").first();
	for(var i = 0; i < length; i++){
		courseCode = x.find("td").next().find("input").next().html();
		chapterCode = x.find("td").next().next().find("input").next().html();
		level = x.find("td").next().next().next().find("select").val();
		title = x.find("td").next().next().next().next().find("input").val();
		ans = x.find("td").next().next().next().next().next().find("input").val();
		if(chapterCode == "" || chapterCode == null){
			swal("章节为空!");
			return false;
		}else if( parseInt(level) != levelEasy && parseInt(level) != levelNormal && parseInt(level) != levelHard){
			swal("难度等级为空!");
			return false;
		}else if(title == "" || title == null){
			swal("题目为空!");
			return false;
		}else if(ans == "" || ans == null){
			swal("答案为空!");
			return false;
		}
		addJosnData += courseCode+"`"+chapterCode+"`"+level+"`"+title+"`"+ans+"`";
		var xx = x.next();
		x = xx;
	}
	$.ajax({
		type : "post",
		url : "../essayquestion/addessayquestion",
		data:{"data": addJosnData },
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				essayQuestionReload();
				swal("成功",resMsg, "success");
				table_input_hide();
			}else{
				swal("错误",resMsg,"error");
			}
		}
	});
}

/* 显示course查询弹窗 */
function showCourseSelectPanel(){
	$("#course_chapterSelectArea").html("");
	$("#course_chapterSelectArea").css({'position':'absolute','top':'20%','left':'28%','width':'600px;','border':'1px solid #ccc','height':'350px;','z-index':'13'});
	$("#course_chapterSelectArea").show();
	$.ajax({
		type : "post",
		url : "../teachercourse/courselistfilter",
		success : function(data) {
			var resMsg = eval("("+data+")").resMsg;
			var resCode = eval("("+data+")").resCode;
			var data = eval("("+data+")").data;
			if(resCode == "000000"){
	 			var s = data.list;
	 			var len = s.length;
	 			var x = "<li>";
	 			for(var i = 0; i < parseInt(len); i++){
	 				var value = s[i].value;
	 				var code = s[i].code;
	 				x += "<a class=\"courseSelected\" style=\"margin: 10px 25px;\">"+value+"</a>" +
					 "<span style=\"display:none;\">"+code+"</span>";
					if( (i + 1) % 3 == 0 ){
						x += "</li><li>";
					}
	 			}
	 			x += "</li>";
	 			$("#course_chapterSelectArea").append("<div class=\"panel\" style=\"width: 600px; height: 350px;\"><div class=\"panel-heading\" style=\"margin-top: 15px;\">"+
	 					"<p style=\"margin:0;text-align: center;font-size: 22px;\">您教授的课程</p>"+
						"</div><hr style=\"margin-top: 10px;margin-bottom: 0;\">"+
						"<div id=\"singleChoose_course_search\" class=\"panel-body\" style=\"height:254px;width:598px;overflow: scroll;\"><ul style=\"list-style-type:none;\">" + x +
						"</ul></div>"+
						"<div class=\"panel-footer\" style=\"height:50px;\"><button onclick=\"closeCourseSelection()\" class=\"btn btn-default\" style=\"float:right; margin-right:20px;\">关闭</button></div></div>");
			}else{
				swal("错误",resMsg,"error");
			}
		}
	});
}

/* 关闭course、chapter查询弹窗 */
function closeCourseSelection(){
	$("#course_chapterSelectArea").hide();
}

/* 执行course查询 */
function searchCourse(){
	var value = $("#singleChoose_search_input").val();
	$("#singleChoose_course_search").html("");
	$.ajax({
		type : "post",
		url : "../courseandchapter/searchcourse",
		data:{"value": value },
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			var listSize = eval("("+data+")").data.listSize;
			var list = eval("("+data+")").data.list;
			var x = "<li>" ;
			if(resCode == "000000"){
				for(var i = 0; i < listSize;i++){
					x += "<a class=\"courseSelected\" style=\"margin: 10px 25px;\">"+list[i].courseCodeValue+"</a>" +
						 "<span style=\"display:none;\">"+list[i].courseCode+"</span>";
					if( (i + 1) % 3 == 0 ){
						x += "</li><li>";
					}
				}
				x += "</li>";
				$("#singleChoose_course_search").append("<ul style=\"list-style-type:none;\">"+ x +"</ul>");
			}else{
				swal("错误",resMsg,"error");
			}
		}
	});
}
/* 显示chapter查询弹窗 */
function showChapterSelectPanel(){
	var length = $("#table_input tbody tr").length;
	var x = $("#table_input tbody tr").first();
	for(var i = 1; i < length;i ++){
		x = x.next();
	}
	var course = x.find("td").next().find("input").val();
	if(course == "" || course == null){
		swal("错误","课程为空,请输入后再试!");
		return false;
	}
	$("#course_chapterSelectArea").html("");
	$("#course_chapterSelectArea").css({'position':'absolute','top':'20%','left':'28%','width':'600px;','border':'1px solid #ccc','height':'350px;','z-index':'13'});
	$("#course_chapterSelectArea").show();
	$("#course_chapterSelectArea").append("<div class=\"panel\" style=\"width: 600px; height: 350px;\"><div class=\"panel-heading\" style=\"margin-top: 15px;\">"+
						"<h4 style=\"text-align: center;\">"+course+"</h4></div><hr style=\"margin-top: 10px;margin-bottom: 0;\">"+
						"<div id=\"singleChoose_chapter_search\" class=\"panel-body\" style=\"height:254px;width:598px;overflow: scroll;\">" +
						"</div>"+
						"<div class=\"panel-footer\" style=\"height:50px;\"><button onclick=\"closeCourseSelection()\" class=\"btn btn-default\" style=\"float:right; margin-right:20px;\">关闭</button></div></div>");
	$.ajax({
		type : "post",
		url : "../courseandchapter/getchapter",
		data:{"courseCode": courseCodeForAll },
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			var listSize = eval("("+data+")").data.listSize;
			var list = eval("("+data+")").data.list;
			var x = "<li>" ;
			if(resCode == "000000"){
				for(var i = 0; i < listSize;i++){
					x += "<a class=\"chapterSelected\" style=\"margin: 10px 25px;\">"+list[i].chapterCodeValue+"</a>"+
					     "<span style=\"display:none;\">"+list[i].chapterCode+"</span>";
					if( (i + 1) % 3 == 0 ){
						x += "</li><li>";
					}
				}
				x += "</li>";
				$("#singleChoose_chapter_search").append("<ul style=\"list-style-type:none;\">"+ x +"</ul>");
			}else{
				swal("错误",resMsg,"error");
			}
		}
	});
}

/* 绑定course的查询结果 */
$(document).on("click",".courseSelected",function(e){
	var course = $(this).html();
	courseCodeForAll = $(this).next().html();
	var length = $("#table_input tbody tr").length;
	var x = $("#table_input tbody tr").first();
	for(var i = 0; i < (length - 1); i++){
		x = x.next();
	}
	x.find("td").first().next().find("input").val(course);
	x.find("td").first().next().find("input").parent().append("<span style=\"display:none;\">"+courseCodeForAll+"</span>");
	$("#course_chapterSelectArea").hide();
	e.stopPropagation();
});

/* 绑定chapter的查询结果 */
$(document).on("click",".chapterSelected",function(e){
	var chapter = $(this).html();
	chapterCodeForAll = $(this).next().html();
	var length = $("#table_input tbody tr").length;
	var x = $("#table_input tbody tr").first();
	for(var i = 0; i < (length - 1); i++){
		x = x.next();
	}
	x.find("td").first().next().next().find("input").val(chapter);
	var zz = x.find("td").first().next().next().find("input").next().html();
	if(typeof(zz) != "undefined"){
		x.find("td").first().next().next().find("input").next().remove();
	}
	x.find("td").first().next().next().find("input").parent().append("<span style=\"display:none;\">"+chapterCodeForAll+"</span>");
	$("#course_chapterSelectArea").hide();
	e.stopPropagation();
});

/* 根据course对题目筛选  */
function courseSelectPanel_search(){
	$("#course_chapterSelectArea").html("");
	$("#course_chapterSelectArea").css({'position':'absolute','top':'238px','left':'35%','width':'600px;','border':'1px solid #ccc','height':'350px;','z-index':'13'});
	$("#course_chapterSelectArea").show();
	$.ajax({
		type : "post",
		url : "../teachercourse/courselistfilter",
		success : function(data) {
			var resMsg = eval("("+data+")").resMsg;
			var resCode = eval("("+data+")").resCode;
			var data = eval("("+data+")").data;
			if(resCode == "000000"){
	 			var s = data.list;
	 			var len = s.length;
	 			var x = "<li>";
	 			for(var i = 0; i < parseInt(len); i++){
	 				var value = s[i].value;
	 				var code = s[i].code;
	 				x += "<a class=\"searchCourseSelected\" style=\"margin: 10px 25px;\">"+value+"</a>" +
					 "<span style=\"display:none;\">"+code+"</span>";
					if( (i + 1) % 3 == 0 ){
						x += "</li><li>";
					}
	 			}
	 			x += "</li>";
	 			$("#course_chapterSelectArea").append("<div class=\"panel\" style=\"width: 600px; height: 350px;\"><div class=\"panel-heading\" style=\"margin-top: 15px;\">"+
	 					"<p style=\"margin:0;text-align: center;font-size: 22px;\">您教授的课程</p>"+
						"</div><hr style=\"margin-top: 10px;margin-bottom: 0;\">"+
						"<div id=\"singleChoose_course_search\" class=\"panel-body\" style=\"height:254px;width:598px;overflow: scroll;\"><ul style=\"list-style-type:none;\">" + x +
						"</ul></div>"+
						"<div class=\"panel-footer\" style=\"height:50px;\"><button onclick=\"closeCourseSelection()\" class=\"btn btn-default\" style=\"float:right; margin-right:20px;\">关闭</button></div></div>");
			}else{
				swal("错误",resMsg,"error");
			}
		}
	});
}

/* 绑定筛选的course结果 */
$(document).on("click",".searchCourseSelected",function(e){
	var course = $(this).html();
	courseCodeForAll = $(this).next().html();
	var x = $("#essay_course").parent().children().last();
	if(typeof(x.attr("class")) == "undefined"){
		x.remove();
	}
	$("#essay_course").val(course);
	$("#essay_course").parent().append("<span style=\"display:none;\">"+courseCodeForAll+"</span>");
	$("#course_chapterSelectArea").hide();
	e.stopPropagation();
});

function searchFilter(){
	var x = $("#essay_course").parent().children().last();
	var courseCode = "";
	if(typeof(x.attr("class")) == "undefined"){
		courseCode = x.html();
	} 
	var start = $("#date_start").val();
	var end = $("#date_end").val();
	var dateTime = "";
	if(start != "" && end != ""){
		dateTime = start +"-"+ end;
	}
	$("#essayquestion").html("");
	$.ajax({
		type : "post",
		url : "../essayquestion/essayquestionlist",
		data:{"currPage":"1",
			  "pageSize":"10",
			  "course":courseCode,
			  "date":dateTime},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			data = eval("("+data+")").data;
			if(resCode == "000000"){
				load(data);
			}else{
				swal("错误",resMsg,"error");
				$("#essayquestion").append("<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>"+
						"<td><a class=\"btn btn-success table_input_add\" href=\"javascript:void();\"><i class=\"fa fa-search-plus\"></i></a>"+  //添加
						     "</td></tr>");
			}
		}  
	});
}