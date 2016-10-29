var courseCodeForAll;
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

//页面
$(document).ready(function(){
	$.ajax({
		type : "post",
		url : "../score/scorelist",
		data:{"currPage":"1",
			  "pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			var totalPages = eval("("+data+")").data.totalPages;
			var currPage = eval("("+data+")").data.currPage;
			$("#currPage").html(currPage);
 			$("#totalPage").html(totalPages);
			var s = eval("("+data+")").data.list;
			if(resCode == "000000"){
				for(var i = 0; i < s.length; i++){
					var id = s[i].userId;
					var course = s[i].courseValue;
					var date = s[i].date;
					var score = s[i].score;
					var totalScore = s[i].totalScore;
					if(score == null){
						score = "暂无";
					}
					$("#scorelist").append("<tr><td>"+id+"</td><td>"+course+"</td><td>"+date+"</td><td>"+totalScore+"</td><td>"+score+"</td></tr>");
				}
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

/* 显示course查询弹窗 */
function showCourseSelectPanel(){
	$("#courseSelectArea").html("");
	$("#courseSelectArea").css({'position':'absolute','top':'20%','left':'28%','width':'600px;','border':'1px solid #ccc','height':'350px;','z-index':'13'});
	$("#courseSelectArea").show();
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
				$("#courseSelectArea").append("<div class=\"panel\" style=\"width: 600px; height: 350px;\"><div class=\"panel-heading\" style=\"margin-top: 15px;\">"+
						"<input id=\"singleChoose_search_input\" class=\"form-control\" placeholder=\"请输入要查找的科目---模糊查找\" style=\"margin-left: 15%;width: 300px; display: inline;\">"+
						"<button type=\"button\" onclick=\"searchCourse()\" class=\"btn btn-default\" style=\"margin-left: 20px;margin-bottom: 5px;\">查询</button></div><hr style=\"margin-top: 10px;margin-bottom: 0;\">"+
						"<div id=\"scorelist_course_search\" class=\"panel-body\" style=\"height:254px;width:598px;overflow: scroll;\"><ul style=\"list-style-type:none;\">" + x +
						"</ul></div>"+
						"<div class=\"panel-footer\" style=\"height:50px;\"><button onclick=\"closeCourseSelection()\" class=\"btn btn-default\" style=\"float:right; margin-right:20px;\">关闭</button></div></div>");
			}else{
				swal("错误",resMsg,"error");
				$("#courseSelectArea").append("<div class=\"panel\" style=\"width: 600px; height: 350px;\"><div class=\"panel-heading\" style=\"margin-top: 15px;\">"+
						"<input id=\"singleChoose_search_input\" class=\"form-control\" placeholder=\"请输入要查找的科目---模糊查找\" style=\"margin-left: 15%;width: 300px; display: inline;\">"+
						"<button type=\"button\" onclick=\"searchCourse()\" class=\"btn btn-default\" style=\"margin-left: 20px;margin-bottom: 5px;\">查询</button></div><hr style=\"margin-top: 10px;margin-bottom: 0;\">"+
						"<div id=\"scorelist_course_search\" class=\"panel-body\" style=\"height:254px;width:598px;overflow: scroll;\">" + 
						"</div>"+
						"<div class=\"panel-footer\" style=\"height:50px;\"><button onclick=\"closeCourseSelection()\" class=\"btn btn-default\" style=\"float:right; margin-right:20px;\">关闭</button></div></div>");
			}
		}
	});
	
}

/* 关闭course、chapter查询弹窗 */
function closeCourseSelection(){
	$("#courseSelectArea").hide();
}

/* 执行course查询 */
function searchCourse(){
	var value = $("#singleChoose_search_input").val();
	$("#scorelist_course_search").html("");
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
				$("#scorelist_course_search").append("<ul style=\"list-style-type:none;\">"+ x +"</ul>");
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
	var x = $("#scorelist_course").parent().children().last();
	if(typeof(x.attr("style")) != "undefined"){
		x.remove();
	}
	$("#scorelist_course").val(course);
	$("#scorelist_course").parent().append("<span style=\"display:none;\">"+courseCodeForAll+"</span>");
	$("#courseSelectArea").hide();
	e.stopPropagation();
});

function search_score(){
	var classes = "";
	var x = $("#scorelist_class");
	var course = "";
	var xc = $("#scorelist_course").parent().children().last();
	var stuId = "";
	var xx = $("#scorelist_num");
	if(typeof(x) != "undefined"){
		classes = x.val();
	}
	if(typeof(xc.attr("style")) != "undefined"){
		course = xc.html();
	}
	if(typeof(xx) != "undefined"){
		stuId = xx.val();
	}
	var staDate = $("#date_start").val();
	var endDate = $("#date_end").val();
	var date = staDate + "-" + endDate;
	$.ajax({
		type : "post",
		url : "../score/scorelist",
		data:{"course":course,
			  "stuCode":stuId,
			  "date": date,
			  "currPage":"1",
			  "pageSize":"10"},
		success : function(data) {
			$("#scorelist").html(""); 
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			var totalPages = eval("("+data+")").data.totalPages;
			var currPage = eval("("+data+")").data.currPage;
			$("#currPage").html(currPage);
 			$("#totalPage").html(totalPages);
			var s = eval("("+data+")").data.list;
			if(resCode == "000000"){
				for(var i = 0; i < s.length; i++){
					var id = s[i].userId;
					var course = s[i].courseValue;
					var date = s[i].date;
					var score = s[i].score;
					var totalScore = s[i].totalScore;
					if(score == null){
						score = "暂无";
					}
					$("#scorelist").append("<tr><td>"+id+"</td><td>"+course+"</td><td>"+date+"</td><td>"+totalScore+"</td><td>"+score+"</td></tr>");
				}
			}else{
				if(lowerIe()){
					alert(resMsg);
				}else{
					swal("错误",resMsg,"error");
				}
			}
		}
	});
}

function scoreListFirst(){
	$("#scorelist").html("");
	$.ajax({
		type : "post",
		url : "../score/scorelist",
		data:{"currPage":"1",
			  "pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			var totalPages = eval("("+data+")").data.totalPages;
			var currPage = eval("("+data+")").data.currPage;
			$("#currPage").html(currPage);
 			$("#totalPage").html(totalPages);
			var s = eval("("+data+")").data.list;
			if(resCode == "000000"){
				for(var i = 0; i < s.length; i++){
					var id = s[i].userId;
					var course = s[i].courseValue;
					var date = s[i].date;
					var score = s[i].score;
					var totalScore = s[i].totalScore;
					if(score == null){
						score = "暂无";
					}
					$("#scorelist").append("<tr><td>"+id+"</td><td>"+course+"</td><td>"+date+"</td><td>"+totalScore+"</td><td>"+score+"</td></tr>");
				}
			}else{
				if(lowerIe()){
					alert(resMsg);
				}else{
					swal("错误",resMsg,"error");
				}
			}
		}
	});
}

function scoreListLast(){
	$("#scorelist").html("");
	$.ajax({
		type : "post",
		url : "../score/scorelist",
		data:{"currPage": $("#totalPage").html(),
			  "pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			var totalPages = eval("("+data+")").data.totalPages;
			var currPage = eval("("+data+")").data.currPage;
			$("#currPage").html(currPage);
 			$("#totalPage").html(totalPages);
			var s = eval("("+data+")").data.list;
			if(resCode == "000000"){
				for(var i = 0; i < s.length; i++){
					var id = s[i].userId;
					var course = s[i].courseValue;
					var date = s[i].date;
					var score = s[i].score;
					var totalScore = s[i].totalScore;
					if(score == null){
						score = "暂无";
					}
					$("#scorelist").append("<tr><td>"+id+"</td><td>"+course+"</td><td>"+date+"</td><td>"+totalScore+"</td><td>"+score+"</td></tr>");
				}
			}else{
				if(lowerIe()){
					alert(resMsg);
				}else{
					swal("错误",resMsg,"error");
				}
			}
		}
	});
}

function scoreListNext(){
	var curr = parseInt($("#currPage").html());
	var total = parseInt($("#totalPage").html());
	if(curr < total){
		curr = curr + 1;
	}
	$("#scorelist").html("");
	$.ajax({
		type : "post",
		url : "../score/scorelist",
		data:{"currPage":curr,
			  "pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			var totalPages = eval("("+data+")").data.totalPages;
			var currPage = eval("("+data+")").data.currPage;
			$("#currPage").html(currPage);
 			$("#totalPage").html(totalPages);
			var s = eval("("+data+")").data.list;
			if(resCode == "000000"){
				for(var i = 0; i < s.length; i++){
					var id = s[i].userId;
					var course = s[i].courseValue;
					var date = s[i].date;
					var score = s[i].score;
					var totalScore = s[i].totalScore;
					if(score == null){
						score = "暂无";
					}
					$("#scorelist").append("<tr><td>"+id+"</td><td>"+course+"</td><td>"+date+"</td><td>"+totalScore+"</td><td>"+score+"</td></tr>");
				}
			}else{
				if(lowerIe()){
					alert(resMsg);
				}else{
					swal("错误",resMsg,"error");
				}
			}
		}
	});
}

function scoreListPrev(){
	var curr = parseInt($("#currPage").html());
	if(curr > 1){
		curr = curr - 1;
	}
	$("#scorelist").html("");
	$.ajax({
		type : "post",
		url : "../score/scorelist",
		data:{"currPage":curr,
			  "pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			var totalPages = eval("("+data+")").data.totalPages;
			var currPage = eval("("+data+")").data.currPage;
			$("#currPage").html(currPage);
 			$("#totalPage").html(totalPages);
			var s = eval("("+data+")").data.list;
			if(resCode == "000000"){
				for(var i = 0; i < s.length; i++){
					var id = s[i].userId;
					var course = s[i].courseValue;
					var date = s[i].date;
					var score = s[i].score;
					var totalScore = s[i].totalScore;
					if(score == null){
						score = "暂无";
					}
					$("#scorelist").append("<tr><td>"+id+"</td><td>"+course+"</td><td>"+date+"</td><td>"+totalScore+"</td><td>"+score+"</td></tr>");
				}
			}else{
				if(lowerIe()){
					alert(resMsg);
				}else{
					swal("错误",resMsg,"error");
				}
			}
		}
	});
}