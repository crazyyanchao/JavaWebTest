var singleScore = 0;
var truefalseScore = 0;
var testsId = "";
var Tans = "6000";
var totalScore = "";
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
$(document).ready(function(){
	$.ajax({
		type : "post",
		url : "../testonline/testslist",
		data:{"currPage":"1",
			  "pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg; 
			var totalPages = eval("("+data+")").data.totalPages;
			var currPage = eval("("+data+")").data.currPage;
			$("#currPage").html(currPage);
 			$("#totalPage").html(totalPages);
			if(resCode == "000000"){
				var s = eval("("+data+")").data.list;
				for(var i = 0; i < s.length; i++){
					var id = s[i].id;
					var userId = s[i].userId;
					var course = s[i].course;
					var score = s[i].score;
					var mark = "";
					if(score == null){
						mark = "<a onclick=\"markPaper(this)\" style=\"color: green;\">评阅</a>";
					}else{
						mark = "<strong style=\"font-size: 16px;color: red;\">"+score + "</strong>&nbsp;&nbsp;&nbsp;<a onclick=\"markPaper(this)\" style=\"color: green;\">重新评阅</a>";
					}
					$("#testslist").append("<tr><td>"+id+"</td><td><a onclick=\"readTestPaper(this)\">"+course+"</a></td><td>"+userId+"</td><td>"+mark+"</td></tr>");
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

function markPaper(e){
	singleScore = 0;
	truefalseScore = 0;
	testsId = "";
	var x = $(e).parent().parent().find("td").first().html();
	testsId = x;
	var course = $(e).parent().parent().find("td").first().next().find("a").html();
	$.ajax({
		type : "post",
		url : "../testonline/markpaper",
		data: {"testId":x},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				totalScore = eval("("+data+")").data.totalScore;
				var single = eval("("+data+")").data.single;
				var singlelist = eval("("+data+")").data.singlelist;
				var sScore = eval("("+data+")").data.sScore;
				var multi = eval("("+data+")").data.multi;
				var multilist = eval("("+data+")").data.multilist;
				var mScore = eval("("+data+")").data.mScore;
				var tf = eval("("+data+")").data.tf;
				var tfScore = eval("("+data+")").data.tfScore;
				var tflist = eval("("+data+")").data.tflist;
				var fill = eval("("+data+")").data.fill;
				var filllist = eval("("+data+")").data.filllist;
				var fScore = eval("("+data+")").data.fScore;
				var essay = eval("("+data+")").data.essay;
				var essaylist = eval("("+data+")").data.essaylist;
				var eScore = eval("("+data+")").data.eScore;
				$('#testPaperShowing').html("");
				$('#testPaperShowing').css({'position':'fixed','margin':'0 auto','left':'0px','right':'0px','top':'10%','width':'90%','height':'80%','z-index':'12','background-color':'#eee'});
				$('#testPaperShowing').show();
				$('#testPaperShowing').append("<div class=\"panel\" style=\"height:100%\"><div class=\"panel-heading\" style=\"margin-top: 15px;\">"+
						"<h4 style=\"text-align: center;color:black;\">"+course+"</h4></div><hr style=\"margin-top: 10px;margin-bottom: 0;\">"+
						"<div id=\"testpaper_body\" class=\"panel-body\" style=\"height:86%;overflow: scroll;\">" +
						"</div>"+
						"<div class=\"panel-footer\" style=\"height:50px;\">" +
						"<button onclick=\"closePanel()\" class=\"btn btn-default\" style=\"float:right; margin-right:20px;\">关闭</button>" +
						"<button onclick=\"markpaperConfirm()\" class=\"btn btn-default\" style=\"float:right; margin-right:20px;\">确认</button>" +
						"</div></div>");
				//singlechoose
				if(singlelist != null && single != null){
					var s = single.split("`");
					$("#testpaper_body").append("<span class=\"suptitle\">单选题(每题<span id=\"singleScore\">"+sScore+"</span>分)</span><br><br><ol id=\"singlechoose\"></ol>");
					for(var i = 0; i < singlelist.length; i++){
						var studentscore = "0";
						if(s[i] == singlelist[i].trueanswer ){
							studentscore = sScore;
							singleScore += parseInt(studentscore);
						}
						$("#singlechoose").append("<li><span class=\"testTitle\">"+singlelist[i].title+"</span><span style=\"display:none\">"+singlelist[i].id+"</span><br>"+
							"A:&nbsp;&nbsp;<span class=\"testContent\">"+singlelist[i].answer1+"</span><br>"+
							"B:&nbsp;&nbsp;<span class=\"testContent\">"+singlelist[i].answer2+"</span><br>"+
							"C:&nbsp;&nbsp;<span class=\"testContent\">"+singlelist[i].answer3+"</span><br>"+
							"D:&nbsp;&nbsp;<span class=\"testContent\">"+singlelist[i].answer4+"</span><br>"+
							"<strong>学生答案</strong>："+s[i]+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>参考答案</strong>:"+singlelist[i].trueanswer+
							"<br><strong>学生得分</strong>：<span style=\"color: red;\">" + studentscore +"</span>"+
							"</li><br>");
					}
				}else if( (singlelist == null && single != null) || (singlelist != null && single == null) ){
					if(lowerIe()){
						alert("出现了一些错误，请重新尝试!");
					}else{
						swal("错误","出现了一些错误，请重新尝试!","error");
					}
				}
				//multichoose
				if(multilist != null && multi != null){
					var s = multi.split("`");
					$("#testpaper_body").append("<span class=\"suptitle\">多选题(每题<span id=\"multiScore\">"+mScore+"</span>分)</span><br><br><ol id=\"multichoose\"></ol>");
					for(var i = 0; i < multilist.length; i++){
						var xxx = s[i].split("-");
						var xx = "";
						for(var j = 0; j < xxx.length; j++){
							xx += xxx[j];
						}
						var ans4 = "D:&nbsp;&nbsp;<span class=\"testContent\">"+multilist[i].answer4+"</span><br>";
						var ans5 = "E:&nbsp;&nbsp;<span class=\"testContent\">"+multilist[i].answer5+"</span><br>";
						var ans6 = "F:&nbsp;&nbsp;<span class=\"testContent\">"+multilist[i].answer6+"</span><br>";
						if(multilist[i].answer4 == " " || multilist[i].answer4 == ""){
							ans4 = "";
						}
						if(multilist[i].answer5 == " " || multilist[i].answer5 == ""){
							ans5 ="";
						}
						if(multilist[i].answer6 == " " || multilist[i].answer6 == ""){
							ans6 = "";
						}
						$("#multichoose").append("<li><span class=\"testTitle\">"+multilist[i].title+"</span><span style=\"display:none\">"+multilist[i].id+"</span><br>"+
							"A:&nbsp;&nbsp;<span class=\"testContent\">"+multilist[i].answer1+"</span><br>"+
							"B:&nbsp;&nbsp;<span class=\"testContent\">"+multilist[i].answer2+"</span><br>"+
							"C:&nbsp;&nbsp;<span class=\"testContent\">"+multilist[i].answer3+"</span><br>"+ ans4 + ans5 + ans6 +
							"<strong>学生答案</strong>："+xx+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>参考答案</strong>:"+multilist[i].trueanswer+
							"<br><strong>学生得分</strong>：<input class=\"from-control markpaper_multi\" type=\"number\" min=\"0\" style=\"width: 100px\">" +
							"</li><br>");
					}
				}else if( (multilist == null && multi != null) || (multilist != null && multi == null) ){
					if(lowerIe()){
						alert("出现了一些错误，请重新尝试!");
					}else{
						swal("错误","出现了一些错误，请重新尝试!","error");
					}
				}
				//TureOrFalse
				if(tflist != null && tf != null){
					var s = tf.split("`");
					$("#testpaper_body").append("<span class=\"suptitle\">判断题(每题<span id=\"tfScore\">"+tfScore+"</span>分)</span><br><br><ol id=\"tfQuestion\"></ol>");
					for(var i = 0; i < tflist.length; i++){
						var ans = "";
						var stuScore = tfScore;
						if(s[i] != tflist[i].answer){
							stuScore = "0";
						}
						truefalseScore += parseInt(stuScore);
						if(s[i] == Tans){
							ans = "正确";
						}else{
							ans = "错误";
 						}
						$("#tfQuestion").append("<li><span class=\"testTitle\">"+tflist[i].title+"</span><span style=\"display:none\">"+tflist[i].id+"</span><br>"+
							"<strong>学生答案</strong>："+ ans +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>参考答案</strong>："+tflist[i].ans+
							"<br><strong>学生得分</strong>：<span style=\"color: red;\">" + stuScore + "</span>" +
							"</li><br>");
					}
				}else if( (filllist == null && fill != null) || (filllist != null && fill == null) ){
					if(lowerIe()){
						alert("出现了一些错误，请重新尝试!");
					}else{
						swal("错误","出现了一些错误，请重新尝试!","error");
					}
				}
				//fillblank
				if(filllist != null && fill != null){
					var s = fill.split("`");
					$("#testpaper_body").append("<span class=\"suptitle\">填空题(每题<span id=\"fillScore\">"+fScore+"</span>分)</span><br><br><ol id=\"fillblank\"></ol>");
					for(var i = 0; i < filllist.length; i++){
						$("#fillblank").append("<li><span class=\"testTitle\">"+filllist[i].title+"</span><span style=\"display:none\">"+filllist[i].id+"</span><br>"+
							"<strong>学生答案</strong>："+s[i]+"<br>" +
							"<strong>参考答案</strong>:"+filllist[i].answer+
							"<br><strong>学生得分</strong>：<input class=\"from-control markpaper_fill\" type=\"number\" min=\"0\" style=\"width: 100px\">" +
							"</li><br>");
					}
				}else if( (filllist == null && fill != null) || (filllist != null && fill == null) ){
					if(lowerIe()){
						alert("出现了一些错误，请重新尝试!");
					}else{
						swal("错误","出现了一些错误，请重新尝试!","error");
					}
				}
				//essayquestion
				if(essaylist != null && essay != null){
					var s = essay.split("`");
					$("#testpaper_body").append("<span class=\"suptitle\">问答题(每题<span id=\"essayScore\">"+eScore+"</span>分)</span><br><br><ol id=\"essayquestion\"></ol>");
					for(var i = 0; i < essaylist.length; i++){
						$("#essayquestion").append("<li><span class=\"testTitle\">"+essaylist[i].title+"</span><span style=\"display:none\">"+essaylist[i].id+"</span><br>"+
							"<strong>学生答案</strong>："+s[i]+"<br>" +
							"<strong>参考答案</strong>:"+essaylist[i].answer+
							"<br><strong>学生得分</strong>：<input class=\"from-control markpaper_essay\" type=\"number\" min=\"0\" style=\"width: 100px\">" +
							"</li><br>");
					}
				}else if( (essaylist == null && essay != null) || (essaylist != null && essay == null) ){
					if(lowerIe()){
						alert("出现了一些错误，请重新尝试!");
					}else{
						swal("错误","出现了一些错误，请重新尝试!","error");
					}
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

function closePanel(){
	$('#testPaperShowing').hide();
}


function search_tests(){
	var cla = $("#markpaper_class").val();
	var course = "";
	var num = $("#markpaper_num").val();
	var x = $("#markpaper_course").parent().children().last();
	if(typeof(x.attr("style")) != "undefined"){
		course = x.html();
	}
	$("#testslist").html("");
	$.ajax({
		type : "post",
		url : "../testonline/testslist",
		data : {"course":course,"num":num,"currPage":$("#currPage").html(),"pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg; 
			if(resCode == "000000"){
				var s = eval("("+data+")").data.list;
				for(var i = 0; i < s.length; i++){
					var id = s[i].id;
					var userId = s[i].userId;
					var course = s[i].course;
					var classValue = s[i].classValue;
					var score = s[i].score;
					if(score == null){
						score = "<a onclick=\"markPaper(this)\">评阅</a>"
					}else{
						score = "已评阅";
					}
					$("#testslist").append("<tr><td>"+id+"</td><td>"+course+"</td><td>"+userId+"</td><td>"+classValue+"</td><td>"+score+"</td></tr>");
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

/* 显示course查询弹窗 */
function showCourseSelectPanel(){
	$("#course_chapterSelectArea").html("");
	$("#course_chapterSelectArea").css({'position':'absolute','top':'20%','left':'28%','width':'600px;','border':'1px solid #ccc','height':'350px;','z-index':'13'});
	$("#course_chapterSelectArea").show();
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
				$("#course_chapterSelectArea").append("<div class=\"panel\" style=\"width: 600px; height: 350px;\"><div class=\"panel-heading\" style=\"margin-top: 15px;\">"+
						"<input id=\"singleChoose_search_input\" class=\"form-control\" placeholder=\"请输入要查找的科目---模糊查找\" style=\"margin-left: 15%;width: 300px; display: inline;\">"+
						"<button type=\"button\" onclick=\"searchCourse()\" class=\"btn btn-default\" style=\"margin-left: 20px;margin-bottom: 5px;\">查询</button></div><hr style=\"margin-top: 10px;margin-bottom: 0;\">"+
						"<div id=\"singleChoose_course_search\" class=\"panel-body\" style=\"height:254px;width:598px;overflow: scroll;\"><ul style=\"list-style-type:none;\">" + x +
						"</ul></div>"+
						"<div class=\"panel-footer\" style=\"height:50px;\"><button onclick=\"closeCourseSelection()\" class=\"btn btn-default\" style=\"float:right; margin-right:20px;\">关闭</button></div></div>");
			}else{
				swal("错误",resMsg,"error");
				$("#course_chapterSelectArea").append("<div class=\"panel\" style=\"width: 600px; height: 350px;\"><div class=\"panel-heading\" style=\"margin-top: 15px;\">"+
						"<input id=\"singleChoose_search_input\" class=\"form-control\" placeholder=\"请输入要查找的科目---模糊查找\" style=\"margin-left: 15%;width: 300px; display: inline;\">"+
						"<button type=\"button\" onclick=\"searchCourse()\" class=\"btn btn-default\" style=\"margin-left: 20px;margin-bottom: 5px;\">查询</button></div><hr style=\"margin-top: 10px;margin-bottom: 0;\">"+
						"<div id=\"singleChoose_course_search\" class=\"panel-body\" style=\"height:254px;width:598px;overflow: scroll;\">" + 
						"</div>"+
						"<div class=\"panel-footer\" style=\"height:50px;\"><button onclick=\"closeCourseSelection()\" class=\"btn btn-default\" style=\"float:right; margin-right:20px;\">关闭</button></div></div>");
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

/* 绑定course的查询结果 */
$(document).on("click",".courseSelected",function(e){
	var course = $(this).html();
	var courseCode = $(this).next().html();
	var x = $("#markpaper_course").parent().children().last();
	if(typeof(x.attr("style")) != "undefined"){
		x.remove();
	}
	$("#markpaper_course").val(course);
	$("#markpaper_course").parent().append("<span style=\"display:none;\">"+courseCode+"</span>")
	$("#course_chapterSelectArea").hide();
	e.stopPropagation();
});

function choosedepartfirst(){
	var depart = $("#markpaper_depart").val();
	if(depart == ""){
		if(lowerIe()){
			alert("请先选择学院!");
		}else{
			swal("错误","请先选择学院!","error");
		}
	}
}

function reload(){
	$("#testslist").html("");
	$.ajax({
		type : "post",
		url : "../testonline/testslist",
		data:{"currPage":$("#currPage").html(),
			  "pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg; 
			var totalPages = eval("("+data+")").data.totalPages;
			var currPage = eval("("+data+")").data.currPage;
			$("#currPage").html(currPage);
 			$("#totalPage").html(totalPages);
			if(resCode == "000000"){
				var s = eval("("+data+")").data.list;
				for(var i = 0; i < s.length; i++){
					var id = s[i].id;
					var userId = s[i].userId;
					var course = s[i].course;
					//var classValue = s[i].classValue;
					var score = s[i].score;
					var mark = "";
					if(score == null){
						mark = "<a onclick=\"markPaper(this)\" style=\"color: green;\">评阅</a>";
					}else{
						mark = "<strong style=\"font-size: 16px;color: red;\">"+score + "</strong>&nbsp;&nbsp;&nbsp;<a onclick=\"markPaper(this)\" style=\"color: green;\">重新评阅</a>";
					}
					$("#testslist").append("<tr><td>"+id+"</td><td><a onclick=\"readTestPaper(this)\">"+course+"</a></td><td>"+userId+"</td><td>"+mark+"</td></tr>");
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

function markpaperConfirm(){
	var multi = $("#multiScore").html();
	var fill = $("#fillScore").html();
	var essay = $("#essayScore").html();
	var score = 0;
	if(typeof(multi) != "undefined"){
		var x = $("#multichoose li").first();
		var len = $("#multichoose li").length;
		for(var i = 0; i < len; i++){
			var ss = x.find("input").val();
			if(ss == ""){
				swal("错误","多选第"+(i+1)+"题得分为空!","error");
				return false;
			}
			if(parseInt(ss) > parseInt(multi)){
				swal("错误","多选第"+(i+1)+"题得分能超过分值!","error");
				return false;
			}
			score += parseInt(ss);
			x = x.next().next();
		}
	}
	if(typeof(fill) != "undefined"){
		var x = $("#fillblank li").first();
		var len = $("#fillblank li").length;
		for(var i = 0; i < len; i++){
			var ss = x.find("input").val();
			if(ss == ""){
				swal("错误","填空题第"+(i+1)+"题得分为空!","error");
				return false;
			}
			if(parseInt(ss) > parseInt(fill)){
				swal("错误","填空题第"+(i+1)+"题得分能超过分值!","error");
				return false;
			}
			score += parseInt(ss);
			x = x.next().next();
		}
	}
	if(typeof(essay) != "undefined"){
		var x = $("#essayquestion li").first();
		var len = $("#essayquestion li").length;
		for(var i = 0; i < len; i++){
			var ss = x.find("input").val();
			if(ss == ""){
				swal("错误","问答题第"+(i+1)+"题得分为空!","error");
				return false;
			}
			if(parseInt(ss) > parseInt(essay)){
				swal("错误","问答题第"+(i+1)+"题得分能超过分值!","error");
				return false;
			}
			score += parseInt(ss);
			x = x.next().next();
		}
	}
	score += singleScore + truefalseScore;
	if(score > parseInt(totalScore)){
		swal("错误","当前分值超过试卷总分!","error");
		return false;
	}
	$.ajax({
		type : "post",
		url : "../testonline/setscore",
		data:{"score":score,"testpaperid":testsId},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				$('#testPaperShowing').hide();
				reload();
				if(lowerIe()){
					alert(resMsg);
				}else{
					swal("成功",resMsg,"success");
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

function markPaperFirst(){
	$("#testslist").html("");
	$.ajax({
		type : "post",
		url : "../testonline/testslist",
		data:{"currPage":"1",
			  "pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg; 
			var totalPages = eval("("+data+")").data.totalPages;
			var currPage = eval("("+data+")").data.currPage;
			$("#currPage").html(currPage);
 			$("#totalPage").html(totalPages);
			if(resCode == "000000"){
				var s = eval("("+data+")").data.list;
				for(var i = 0; i < s.length; i++){
					var id = s[i].id;
					var userId = s[i].userId;
					var course = s[i].course;
					//var classValue = s[i].classValue;
					var score = s[i].score;
					if(score == null){
						score = "<a onclick=\"markPaper(this)\">评阅</a>"
					}else{
						score = "已评阅";
					}
					$("#testslist").append("<tr><td>"+id+"</td><td>"+course+"</td><td>"+userId+"</td><td>"+score+"</td></tr>");
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

function markPaperLast(){
	$("#testslist").html("");
	$.ajax({
		type : "post",
		url : "../testonline/testslist",
		data:{"currPage":$("#totalPage").html(),
			  "pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg; 
			var totalPages = eval("("+data+")").data.totalPages;
			var currPage = eval("("+data+")").data.currPage;
			$("#currPage").html(currPage);
 			$("#totalPage").html(totalPages);
			if(resCode == "000000"){
				var s = eval("("+data+")").data.list;
				for(var i = 0; i < s.length; i++){
					var id = s[i].id;
					var userId = s[i].userId;
					var course = s[i].course;
					//var classValue = s[i].classValue;
					var score = s[i].score;
					if(score == null){
						score = "<a onclick=\"markPaper(this)\">评阅</a>"
					}else{
						score = "已评阅";
					}
					$("#testslist").append("<tr><td>"+id+"</td><td>"+course+"</td><td>"+userId+"</td><td>"+score+"</td></tr>");
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

function markPaperNext(){
	var curr = parseInt($("#currPage").html());
	var total = parseInt($("#totalPage").html());
	if(curr < total){
		curr = curr + 1;
	}
	$("#testslist").html("");
	$.ajax({
		type : "post",
		url : "../testonline/testslist",
		data:{"currPage":curr,
			  "pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg; 
			var totalPages = eval("("+data+")").data.totalPages;
			var currPage = eval("("+data+")").data.currPage;
			$("#currPage").html(currPage);
 			$("#totalPage").html(totalPages);
			if(resCode == "000000"){
				var s = eval("("+data+")").data.list;
				for(var i = 0; i < s.length; i++){
					var id = s[i].id;
					var userId = s[i].userId;
					var course = s[i].course;
					//var classValue = s[i].classValue;
					var score = s[i].score;
					if(score == null){
						score = "<a onclick=\"markPaper(this)\">评阅</a>"
					}else{
						score = "已评阅";
					}
					$("#testslist").append("<tr><td>"+id+"</td><td>"+course+"</td><td>"+userId+"</td><td>"+score+"</td></tr>");
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

function markPaperPrev(){
	var curr = parseInt($("#currPage").html());
	if(curr > 1){
		curr = curr - 1;
	}
	$("#testslist").html("");
	$.ajax({
		type : "post",
		url : "../testonline/testslist",
		data:{"currPage":curr,
			  "pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg; 
			var totalPages = eval("("+data+")").data.totalPages;
			var currPage = eval("("+data+")").data.currPage;
			$("#currPage").html(currPage);
 			$("#totalPage").html(totalPages);
			if(resCode == "000000"){
				var s = eval("("+data+")").data.list;
				for(var i = 0; i < s.length; i++){
					var id = s[i].id;
					var userId = s[i].userId;
					var course = s[i].course;
					//var classValue = s[i].classValue;
					var score = s[i].score;
					if(score == null){
						score = "<a onclick=\"markPaper(this)\">评阅</a>"
					}else{
						score = "已评阅";
					}
					$("#testslist").append("<tr><td>"+id+"</td><td>"+course+"</td><td>"+userId+"</td><td>"+score+"</td></tr>");
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

function readTestPaper(e){
	var tId = $(e).parent().parent().find("td").first().html();
	var course = $(e).parent().parent().find("td").first().next().find("a").html();
	$.ajax({
		type : "post",
		url : "../testonline/markpaper",
		data: {"testId": tId},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				var totalScore = eval("("+data+")").data.totalScore;
				var single = eval("("+data+")").data.single;
				var singlelist = eval("("+data+")").data.singlelist;
				var sScore = eval("("+data+")").data.sScore;
				var multi = eval("("+data+")").data.multi;
				var multilist = eval("("+data+")").data.multilist;
				var mScore = eval("("+data+")").data.mScore;
				var tf = eval("("+data+")").data.tf;
				var tfScore = eval("("+data+")").data.tfScore;
				var tflist = eval("("+data+")").data.tflist;
				var fill = eval("("+data+")").data.fill;
				var filllist = eval("("+data+")").data.filllist;
				var fScore = eval("("+data+")").data.fScore;
				var essay = eval("("+data+")").data.essay;
				var essaylist = eval("("+data+")").data.essaylist;
				var eScore = eval("("+data+")").data.eScore;
				$('#testPaperShowing').html("");
				$('#testPaperShowing').css({'position':'fixed','margin':'0 auto','left':'0px','right':'0px','top':'10%','width':'90%','height':'80%','z-index':'12','background-color':'#eee'});
				$('#testPaperShowing').show();
				$('#testPaperShowing').append("<div class=\"panel\" style=\"height:100%\"><div class=\"panel-heading\" style=\"margin-top: 15px;\">"+
						"<h4 style=\"text-align: center;color:black;\">"+course+"</h4></div><hr style=\"margin-top: 10px;margin-bottom: 0;\">"+
						"<div id=\"testpaper_body\" class=\"panel-body\" style=\"height:86%;overflow: scroll;\">" +
						"</div>"+
						"<div class=\"panel-footer\" style=\"height:50px;\">" +
						"<button onclick=\"closePanel()\" class=\"btn btn-default\" style=\"float:right; margin-right:20px;\">关闭</button>" +
						"</div></div>");
				//singlechoose
				if(singlelist != null && single != null){
					var s = single.split("`");
					$("#testpaper_body").append("<span class=\"suptitle\">单选题(每题<span id=\"singleScore\">"+sScore+"</span>分)</span><br><br><ol id=\"singlechoose\"></ol>");
					for(var i = 0; i < singlelist.length; i++){
						var studentscore = "0";
						if(s[i] == singlelist[i].trueanswer ){
							studentscore = sScore;
							singleScore += parseInt(studentscore);
						}
						$("#singlechoose").append("<li><span class=\"testTitle\">"+singlelist[i].title+"</span><span style=\"display:none\">"+singlelist[i].id+"</span><br>"+
							"A:&nbsp;&nbsp;<span class=\"testContent\">"+singlelist[i].answer1+"</span><br>"+
							"B:&nbsp;&nbsp;<span class=\"testContent\">"+singlelist[i].answer2+"</span><br>"+
							"C:&nbsp;&nbsp;<span class=\"testContent\">"+singlelist[i].answer3+"</span><br>"+
							"D:&nbsp;&nbsp;<span class=\"testContent\">"+singlelist[i].answer4+"</span><br>"+
							"<strong>学生答案</strong>："+s[i]+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>参考答案</strong>:"+singlelist[i].trueanswer+
							"</li><br>");
					}
				}else if( (singlelist == null && single != null) || (singlelist != null && single == null) ){
					if(lowerIe()){
						alert("出现了一些错误，请重新尝试!");
					}else{
						swal("错误","出现了一些错误，请重新尝试!","error");
					}
				}
				//multichoose
				if(multilist != null && multi != null){
					var s = multi.split("`");
					$("#testpaper_body").append("<span class=\"suptitle\">多选题(每题<span id=\"multiScore\">"+mScore+"</span>分)</span><br><br><ol id=\"multichoose\"></ol>");
					for(var i = 0; i < multilist.length; i++){
						var xxx = s[i].split("-");
						var xx = "";
						for(var j = 0; j < xxx.length; j++){
							xx += xxx[j];
						}
						var ans4 = "D:&nbsp;&nbsp;<span class=\"testContent\">"+multilist[i].answer4+"</span><br>";
						var ans5 = "E:&nbsp;&nbsp;<span class=\"testContent\">"+multilist[i].answer5+"</span><br>";
						var ans6 = "F:&nbsp;&nbsp;<span class=\"testContent\">"+multilist[i].answer6+"</span><br>";
						if(multilist[i].answer4 == " " || multilist[i].answer4 == ""){
							ans4 = "";
						}
						if(multilist[i].answer5 == " " || multilist[i].answer5 == ""){
							ans5 ="";
						}
						if(multilist[i].answer6 == " " || multilist[i].answer6 == ""){
							ans6 = "";
						}
						$("#multichoose").append("<li><span class=\"testTitle\">"+multilist[i].title+"</span><span style=\"display:none\">"+multilist[i].id+"</span><br>"+
							"A:&nbsp;&nbsp;<span class=\"testContent\">"+multilist[i].answer1+"</span><br>"+
							"B:&nbsp;&nbsp;<span class=\"testContent\">"+multilist[i].answer2+"</span><br>"+
							"C:&nbsp;&nbsp;<span class=\"testContent\">"+multilist[i].answer3+"</span><br>"+ ans4 + ans5 + ans6 +
							"<strong>学生答案</strong>："+xx+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>参考答案</strong>:"+multilist[i].trueanswer+
							"</li><br>");
					}
				}else if( (multilist == null && multi != null) || (multilist != null && multi == null) ){
					if(lowerIe()){
						alert("出现了一些错误，请重新尝试!");
					}else{
						swal("错误","出现了一些错误，请重新尝试!","error");
					}
				}
				//TureOrFalse
				if(tflist != null && tf != null){
					var s = tf.split("`");
					$("#testpaper_body").append("<span class=\"suptitle\">判断题(每题<span id=\"tfScore\">"+tfScore+"</span>分)</span><br><br><ol id=\"tfQuestion\"></ol>");
					for(var i = 0; i < tflist.length; i++){
						var ans = "";
						var stuScore = tfScore;
						if(ans != tflist[i].answer){
							stuScore = "0";
						}
						if(s[i] == Tans){
							ans = "正确";
						}else{
							ans = "错误";
 						}
						$("#tfQuestion").append("<li><span class=\"testTitle\">"+tflist[i].title+"</span><span style=\"display:none\">"+tflist[i].id+"</span><br>"+
							"<strong>学生答案</strong>："+ ans +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>参考答案</strong>："+tflist[i].ans+
							"</li><br>");
					}
				}else if( (filllist == null && fill != null) || (filllist != null && fill == null) ){
					if(lowerIe()){
						alert("出现了一些错误，请重新尝试!");
					}else{
						swal("错误","出现了一些错误，请重新尝试!","error");
					}
				}
				//fillblank
				if(filllist != null && fill != null){
					var s = fill.split("`");
					$("#testpaper_body").append("<span class=\"suptitle\">填空题(每题<span id=\"fillScore\">"+fScore+"</span>分)</span><br><br><ol id=\"fillblank\"></ol>");
					for(var i = 0; i < filllist.length; i++){
						$("#fillblank").append("<li><span class=\"testTitle\">"+filllist[i].title+"</span><span style=\"display:none\">"+filllist[i].id+"</span><br>"+
							"<strong>学生答案</strong>："+s[i]+"<br>" +
							"<strong>参考答案</strong>:"+filllist[i].answer+
							"</li><br>");
					}
				}else if( (filllist == null && fill != null) || (filllist != null && fill == null) ){
					if(lowerIe()){
						alert("出现了一些错误，请重新尝试!");
					}else{
						swal("错误","出现了一些错误，请重新尝试!","error");
					}
				}
				//essayquestion
				if(essaylist != null && essay != null){
					var s = essay.split("`");
					$("#testpaper_body").append("<span class=\"suptitle\">问答题(每题<span id=\"essayScore\">"+eScore+"</span>分)</span><br><br><ol id=\"essayquestion\"></ol>");
					for(var i = 0; i < essaylist.length; i++){
						$("#essayquestion").append("<li><span class=\"testTitle\">"+essaylist[i].title+"</span><span style=\"display:none\">"+essaylist[i].id+"</span><br>"+
							"<strong>学生答案</strong>："+s[i]+"<br>" +
							"<strong>参考答案</strong>:"+essaylist[i].answer+
							"</li><br>");
					}
				}else if( (essaylist == null && essay != null) || (essaylist != null && essay == null) ){
					if(lowerIe()){
						alert("出现了一些错误，请重新尝试!");
					}else{
						swal("错误","出现了一些错误，请重新尝试!","error");
					}
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

$(document).on("change",".markpaper_multi",function(e){
	var multiScore = $("#multiScore").html();
	var x = $("#multichoose li").first();
	var len = $("#multichoose li").length;
	for(var i = 0; i < len; i++){
		var ss = x.find("input").val();
		if(parseInt(ss) > parseInt(multiScore)){
			swal("错误","当前多选题分值不能超过"+multiScore+"分","error");
			return false;
		}
		x = x.next().next();
	}
	e.stopPropagation();
});

$(document).on("change",".markpaper_fill",function(e){
	var fillScore = $("#fillScore").html();
	var x = $("#fillblank li").first();
	var len = $("#fillblank li").length;
	for(var i = 0; i < len; i++){
		var ss = x.find("input").val();
		if(parseInt(ss) > parseInt(fillScore)){
			swal("错误","当前填空题分值不能超过"+fillScore+"分","error");
			return false;
		}
		x = x.next().next();
	}
	e.stopPropagation();
});

$(document).on("change",".markpaper_essay",function(e){	
	var essayScore = $("#essayScore").html();
	var x = $("#essayquestion li").first();
	var len = $("#essayquestion li").length;
	for(var i = 0; i < len; i++){
		var ss = x.find("input").val();
		if(parseInt(ss) > parseInt(essayScore)){
			swal("错误","当前问答题分值不能超过"+essayScore+"分","error");
			return false;
		}
		x = x.next().next();
	}
	e.stopPropagation();
});