var courseCodeForAll;
var chapterCodeForAll;
var idForAll;
var JsonData;
var LevelEasy = "2001";
var LevelNormal = "2002";
var LevelHard = "2003";
$(document).on("change","#singleChooseLevel",function(e){
	if($("#singleChooseLevel").val() == "0"){
		$("#SCchoose tr").last().remove();
		$("#SCchoose").append("<tr><td>自定义难度 </td><td>" +
				"<label>简单题数量</label><input type=\"number\" min=\"0\" class=\"form-control\" style=\"display: inline-block; width: 100px; margin:0 30px 0 10px;\">"+
				"<label>一般题数量</label><input type=\"number\" min=\"0\" class=\"form-control\" style=\"display: inline-block; width: 100px; margin:0 30px 0 10px;\">"+
				"<label>难题数量</label><input type=\"number\" min=\"0\"class=\"form-control\" style=\"display: inline-block; width: 100px; margin:0 30px 0 10px;\" >" +
				"</td></tr>");
	}
	e.stopPropagation();
});
$(document).on("change","#multiChooseLevel",function(e){
	if($("#multiChooseLevel").val() == "0"){
		$("#MCchoose tr").last().remove();
		$("#MCchoose").append("<tr><td>自定义难度 </td><td>" +
				"<label>简单题数量</label><input type=\"number\" min=\"0\" class=\"form-control\" style=\"display: inline-block; width: 100px; margin:0 30px 0 10px;\">"+
				"<label>一般题数量</label><input type=\"number\" min=\"0\" class=\"form-control\" style=\"display: inline-block; width: 100px; margin:0 30px 0 10px;\">"+
				"<label>难题数量</label><input type=\"number\" min=\"0\" class=\"form-control\" style=\"display: inline-block; width: 100px; margin:0 30px 0 10px;\" >" +
				"</td></tr>");
	}
	e.stopPropagation();
});
$(document).on("change","#fillBlankLevel",function(e){
	if($("#fillBlankLevel").val() == "0"){
		$("#FBchoose tr").last().remove();
		$("#FBchoose").append("<tr><td>自定义难度 </td><td>" +
				"<label>简单题数量</label><input type=\"number\" min=\"0\" class=\"form-control\" style=\"display: inline-block; width: 100px; margin:0 30px 0 10px;\">"+
				"<label>一般题数量</label><input type=\"number\" min=\"0\" class=\"form-control\" style=\"display: inline-block; width: 100px; margin:0 30px 0 10px;\">"+
				"<label>难题数量</label><input type=\"number\" min=\"0\" class=\"form-control\" style=\"display: inline-block; width: 100px; margin:0 30px 0 10px;\" >" +
				"</td></tr>");
	}
	e.stopPropagation();
});
$(document).on("change","#essayQuestionLevel",function(e){
	if($("#essayQuestionLevel").val() == "0"){
		$("#EQchoose tr").last().remove();
		$("#EQchoose").append("<tr><td>自定义难度 </td><td>" +
				"<label>简单题数量</label><input type=\"number\" min=\"0\" class=\"form-control\" style=\"display: inline-block; width: 100px; margin:0 30px 0 10px;\">"+
				"<label>一般题数量</label><input type=\"number\" min=\"0\" class=\"form-control\" style=\"display: inline-block; width: 100px; margin:0 30px 0 10px;\">"+
				"<label>难题数量</label><input type=\"number\" min=\"0\" class=\"form-control\" style=\"display: inline-block; width: 100px; margin:0 30px 0 10px;\" >" +
				"</td></tr>");
	}
	e.stopPropagation();
});


/* 显示course查询弹窗 */
function showCourseSelectPanel(s){
	idForAll = "";
	var id = $(s).parent().parent().parent().attr("id");
	idForAll = id;
	$("#course_chapterSelectArea").html("");
	$("#course_chapterSelectArea").css({'position':'fixed','top':'20%','left':'28%','width':'600px;','border':'1px solid #ccc','height':'350px;','z-index':'20'});
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
	var courseCode = $("#"+idForAll+" tr").first().find("td").last().next().html();
	var courseValue = $("#"+idForAll+" tr").first().find("td").last().html();
	if(typeof(courseCode) == "undefined"){
		swal("错误","课程为空,请输入后再试!","error");
		return false;
	}
	$("#course_chapterSelectArea").html("");
	$("#course_chapterSelectArea").css({'position':'fixed','top':'20%','left':'28%','width':'600px;','border':'1px solid #ccc','height':'350px;','z-index':'13'});
	$("#course_chapterSelectArea").show();
	$("#course_chapterSelectArea").append("<div class=\"panel\" style=\"width: 600px; height: 350px;\"><div class=\"panel-heading\" style=\"margin-top: 15px;\">"+
						"<h4 style=\"text-align: center;\">"+courseValue+"</h4></div><hr style=\"margin-top: 10px;margin-bottom: 0;\">"+
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
	$("#"+idForAll+" tr").first().find("td").last().next().remove();
	$("#"+idForAll+" tr").first().find("td").last().html("<a onclick=\"showCourseSelectPanel(this)\">"+course+"</a>");
	$("#"+idForAll+" tr").first().append("<span style=\"display:none;\">"+courseCodeForAll+"</span>");
	$("#course_chapterSelectArea").hide();
	e.stopPropagation();
});

/* 绑定chapter的查询结果 */
$(document).on("click",".chapterSelected",function(e){
	var chapter = $(this).html();
	chapterCodeForAll = $(this).next().html();
	$("#"+idForAll+" tr").first().next().find("td").last().next().remove();
	$("#"+idForAll+" tr").first().next().find("td").last().html("<a onclick=\"showCourseSelectPanel(this)\">"+chapter+"</a>");
	$("#"+idForAll+" tr").first().next().append("<span style=\"display:none;\">"+chapterCodeForAll+"</span>");
	$("#course_chapterSelectArea").hide();
	e.stopPropagation();
});

/* 提交设定的表格 */
function designComfirm(){
	JsonData = "";
	var Course = $("#base_info tr").first().find("td").last().next().html();
	var Chapter = $("#base_info tr").first().next().find("td").last().next().html();
	var date = $("#base_info tr").first().next().next().find("td").last().find("input").val();
	var arr = new Array();
	arr = date.split("/");
	var time = new Date(); //获得当前时间
	var year = time.getFullYear();//获得年、月、日
	var month = time.getMonth() + 1;
	var day = time.getDate();
	var starttime = $("#base_info tr").first().next().next().next().find("td").last().find("input").val();
	var arr1 = new Array();
	arr1 = starttime.split(":");
	var endtime = $("#base_info tr").first().next().next().next().next().find("td").last().find("input").val();
	var arr2 = new Array();
	arr2 = endtime.split(":");
	var paperstyle = $("#base_info tr").last().prev().prev().prev().find("select").val();
	var totalScore = $("#base_info tr").last().prev().prev().find("input").val();
	var markers = $("#base_info tr").last().prev().find("td").last().find("span").html();
	var joinedclasses = $("#base_info tr").last().find("td").last().find("span").html();
	if(Course == "" || Course == null){
		swal("错误","科目为空!","error");
		return false;
	}else{
		JsonData += Course + "`";
	}
	if(Chapter != "" && Chapter != null){
		JsonData += Chapter + "`"; 
	}else{
		JsonData += "" + "`";
	}
	if(date == "" || date == null){
		swal("错误","考试日期为空!","error");
		return false;
	}else{
		JsonData += date + "`";
	}
	if(arr[2] < year || (arr[2] == year && arr[0] < month) || (arr[2] == year && arr[0] == month && arr[1] < day) ){
		swal("错误","设定时间不能晚于当前时间","error");
		return false;
	}
	if(starttime == "" || starttime == null){
		swal("错误","开始时间为空!","error");
		return false;
	}else{
		JsonData += starttime + "`";
	}
	if(endtime == "" || endtime == null){
		swal("错误","结束时间为空!","error");
		return false;
	}else{
		JsonData += endtime + "`";
	}
	if(parseInt(arr2[0]) < parseInt(arr1[0]) || (parseInt(arr2[0]) == parseInt(arr1[0]) && parseInt(arr2[1]) < parseInt(arr1[1])) ){
		swal("错误","考试结束时间不能晚于开始时间","error");
		return false;
	}else if( arr2[0] == arr1[0] && arr2[1] == arr1[1] ){
		swal("错误","考试结束时间不能和开始时间相同","error");
		return false;
	}
	if(paperstyle == "" || paperstyle == null){
		swal("错误","试卷类型为空!","error");
		return false;
	}else{
		JsonData += paperstyle + "`";
	}
	if(totalScore == "" || totalScore == null){
		swal("错误","试卷总分为空!","error");
		return false;
	}else{
		JsonData += totalScore + "`";
	}
	if(typeof(markers) == "undefined"){
		swal("错误","阅卷人为空!","error");
		return false;
	}else{
		JsonData += markers + "`";
	}
	if(typeof(joinedclasses) == "undefined"){
		JsonData += "ForAllStudent" + "`";
	}else{
		JsonData += joinedclasses + "`";
	}
	var SCcount = $("#SCchoose tr").first().find("td input").val();
	var SCscore = $("#SCchoose tr").first().next().find("input").val();
	var SClevel = $("#SCchoose tr").first().next().next().find("select").val();
	var SCeasy = $("#SCchoose tr").first().next().next().find("td input").first().val();
	var SCnormal = $("#SCchoose tr").first().next().next().find("td input").first().next().next().val();
	var SChard = $("#SCchoose tr").first().next().next().find("td input").last().val();
	if(SCcount != "" && SCcount != null && SCcount != "0"){
		if( parseInt(SCcount) > parseInt($("#singleChooseCount").html()) ){
			swal("错误","单选题数量超过上限!","error");
			return false;
		}
		if(SCscore == "" || SCscore == null){
			swal("错误","单选题每道题的分值为空!","error");
			return false;
		}
		JsonData += "SC" + "`" + SCcount + "`" + SCscore + "`";
		if(typeof(SClevel) == "undefined"){
			if(SCeasy == "" || SCeasy == null){
				swal("错误","单选题自定义难度简单题数量为空!","error");
				return false;
			}else{
				if(parseInt(SCeasy) > parseInt($("#singleEasy").html())){
					swal("错误","单选题的简单题数量超过上限!","error");
					return false;
				}
			}
			if(SCnormal == "" || SCnormal == null){
				swal("错误","单选题自定义难度一般题数量为空!","error");
				return false;
			}else{
				if(parseInt(SCnormal) > parseInt($("#singleNormal").html())){
					swal("错误","单选题的一般题数量超过上限!","error");
					return false;
				}
			}
			if(SChard == "" || SChard == null){
				swal("错误","单选题自定义难度难题数量为空!","error");
				return false;
			}else{
				if(parseInt(SChard) > parseInt($("#singleHard").html())){
					swal("错误","单选题的难题数量超过上限!","error");
					return false;
				}
			}
			if( (parseInt(SCeasy) + parseInt(SCnormal) + parseInt(SChard)) == parseInt(SCcount) ){
				JsonData += "level" + "`" + SCeasy + "`" + SCnormal + "`" +SChard + "`";
			}else{
				swal("错误","单选题自定义数量和总数不符!","error");
				return false;
			}
		}else{
			if(SClevel == LevelEasy){
				if( parseInt(SCcount) > parseInt($("#singleEasy").html()) ){
					swal("错误","所选难度的单选题数量超过上限!","error");
					return false;
				}
			}else if(SClevel == LevelNormal){
				if( parseInt(SCcount) > parseInt($("#singleNormal").html()) ){
					swal("错误","所选难度的单选题数量超过上限!","error");
					return false;
				}
			}else if(SClevel == LevelHard){
				if( parseInt(SCcount) > parseInt($("#singleHard").html()) ){
					swal("错误","所选难度的单选题数量超过上限!","error");
					return false;
				}
			}
			JsonData += SClevel + "`";
		}
	}
	var MCcount = $("#MCchoose tr").first().find("td input").val();
	var MCscore = $("#MCchoose tr").first().next().find("td input").val();
	var MClevel = $("#MCchoose tr").first().next().next().find("select").val();
	var MCeasy = $("#MCchoose tr").first().next().next().find("td input").first().val();
	var MCnormal = $("#MCchoose tr").first().next().next().find("td input").first().next().next().val();
	var MChard = $("#MCchoose tr").first().next().next().find("td input").last().val();
	if(MCcount != "" && MCcount != null && MCcount != "0"){
		if( parseInt(MCcount) > parseInt($("#multiChooseCount").html()) ){
			swal("错误","多选题数量超过上限!","error");
			return false;
		}
		if(MCscore == "" || MCscore == null){
			swal("错误","多选题每道题分值为空!","error");
			return false;
		}
		JsonData += "MC" + "`" + MCcount + "`" + MCscore + "`";
		if(typeof(MClevel) == "undefined"){
			if(MCeasy == "" || MCeasy == null){
				swal("错误","多选题自定义难度简单题数量为空!","error");
				return false;
			}else{
				if(parseInt(MCeasy) > parseInt($("#multiEasy").html())){
					swal("错误","多选题的简单题数量超过上限!","error");
					return false;
				}
			}
			if(MCnormal == "" || MCnormal == null){
				swal("错误","多选题自定义难度一般题数量为空!","error");
				return false;
			}else{
				if(parseInt(MCnormal) > parseInt($("#multiNormal").html())){
					swal("错误","多选题的一般题数量超过上限!","error");
					return false;
				}
			}
			if(MChard == "" || MChard == null){
				swal("错误","多选题自定义难度难题数量为空!","error");
				return false;
			}else{
				if(parseInt(MChard) > parseInt($("#multiHard").html())){
					swal("错误","多选题的难题数量超过上限!","error");
					return false;
				}
			}
			if( (parseInt(MCeasy) + parseInt(MCnormal) + parseInt(MChard)) == parseInt(MCcount) ){
				JsonData += "level" + "`"+ MCeasy + "`" + MCnormal + "`" +MChard + "`";
			}else{
				swal("错误","多选题自定义数量和总数不符!","error");
				return false;
			}
		}else{
			if(MClevel == LevelEasy){
				if( parseInt(MCcount) > parseInt($("#multiEasy").html()) ){
					swal("错误","所选难度的多选题数量超过上限!","error");
					return false;
				}
			}else if(MClevel == LevelNormal){
				if( parseInt(MCcount) > parseInt($("#multiNormal").html()) ){
					swal("错误","所选难度的多选题数量超过上限!","error");
					return false;
				}
			}else if(MClevel == LevelHard){
				if( parseInt(MCcount) > parseInt($("#multiHard").html()) ){
					swal("错误","所选难度的多选题数量超过上限!","error");
					return false;
				}
			}
			JsonData += MClevel + "`";
		}
	}
	var TFcount = $("#TFchoose tr").first().find("td input").val();
	var TFscore = $("#TFchoose tr").first().next().find("td input").val();
	var TFlevel = $("#TFchoose tr").first().next().next().find("select").val();
	var TFeasy = $("#TFchoose tr").first().next().next().find("td input").first().val();
	var TFnormal = $("#TFchoose tr").first().next().next().find("td input").first().next().next().val();
	var TFhard = $("#TFchoose tr").first().next().next().find("td input").last().val();
	if(TFcount != "" && TFcount != null && TFcount != "0"){
		if( parseInt(TFcount) > parseInt($("#tfCount").html()) ){
			swal("错误","判断题数量超过上限!","error");
			return false;
		}
		if(TFscore == "" || TFscore == null){
			swal("错误","判断题每道题的分值为空!","error");
			return false;
		}
		JsonData += "TF" + "`" + TFcount + "`" + TFscore + "`";
		if(typeof(TFlevel) == "undefined"){
			if(TFeasy == "" || TFeasy == null){
				swal("错误","判断题自定义难度简单题数量为空!","error");
				return false;
			}else{
				if(parseInt(TFeasy) > parseInt($("#tfEasy").html())){
					swal("错误","判断题的简单题数量超过上限!","error");
					return false;
				}
			}
			if(TFnormal == "" || TFnormal == null){
				swal("错误","判断题自定义难度一般题数量为空!","error");
				return false;
			}else{
				if(parseInt(TFnormal) > parseInt($("#tfNormal").html())){
					swal("错误","判断题的一般题数量超过上限!","error");
					return false;
				}
			}
			if(TFhard == "" || TFhard == null){
				swal("错误","判断题自定义难度难题数量为空!","error");
				return false;
			}else{
				if(parseInt(TFhard) > parseInt($("#tfHard").html())){
					swal("错误","判断题的难题数量超过上限!","error");
					return false;
				}
			}
			if( (parseInt(TFeasy) + parseInt(TFnormal) + parseInt(TFhard)) == parseInt(TFcount) ){
				JsonData += "level" + "`" + TFeasy + "`" + TFnormal + "`" +TFhard + "`";
			}else{
				swal("错误","判断题自定义数量和总数不符!","error");
				return false;
			}
		}else{
			if(TFlevel == LevelEasy){
				if( parseInt(TFcount) > parseInt($("#tfEasy").html()) ){
					swal("错误","所选难度的判断题数量超过上限!","error");
					return false;
				}
			}else if(TFlevel == LevelNormal){
				if( parseInt(TFcount) > parseInt($("#tfNormal").html()) ){
					swal("错误","所选难度的判断题数量超过上限!","error");
					return false;
				}
			}else if(TFlevel == LevelHard){
				if( parseInt(TFcount) > parseInt($("#tfHard").html()) ){
					swal("错误","所选难度的判断题数量超过上限!","error");
					return false;
				}
			}
			JsonData += TFlevel + "`";
		}
	}
	
	var FBcount = $("#FBchoose tr").first().find("td input").val();
	var FBscore = $("#FBchoose tr").first().next().find("td input").val();
	var FBlevel = $("#FBchoose tr").first().next().next().find("select").val();
	var FBeasy = $("#FBchoose tr").first().next().next().find("td input").first().val();
	var FBnormal = $("#FBchoose tr").first().next().next().find("td input").first().next().next().val();
	var FBhard = $("#FBchoose tr").first().next().next().find("td input").last().val();
	if(FBcount != "" && FBcount != null && FBcount != "0"){
		if( parseInt(FBcount) > parseInt($("#fillBlankCount").html()) ){
			swal("错误","填空题数量超过上限!","error");
			return false;
		}
		if(FBscore == "" || FBscore == null){
			swal("错误","填空题每道题的分值为空!","error");
			return false;
		}
		JsonData += "FB" + "`" + FBcount + "`" + FBscore + "`";
		if(typeof(FBlevel) == "undefined"){
			if(FBeasy == "" || FBeasy == null){
				swal("错误","填空题自定义难度简单题数量为空!","error");
				return false;
			}else{
				if(parseInt(FBeasy) > parseInt($("#fillEasy").html())){
					swal("错误","填空题的简单题数量超过上限!","error");
					return false;
				}
			}
			if(FBnormal == "" || FBnormal == null){
				swal("错误","填空题自定义难度一般题数量为空!","error");
				return false;
			}else{
				if(parseInt(FBnormal) > parseInt($("#fillNormal").html())){
					swal("错误","填空题的一般题数量超过上限!","error");
					return false;
				}
			}
			if(FBhard == "" || FBhard == null){
				swal("错误","填空题自定义难度难题数量为空!","error");
				return false;
			}else{
				if(parseInt(FBhard) > parseInt($("#fillHard").html())){
					swal("错误","填空题的难题数量超过上限!","error");
					return false;
				}
			}
			if( (parseInt(FBeasy) + parseInt(FBnormal) + parseInt(FBhard)) == parseInt(FBcount) ){
				JsonData += "level" + "`" + FBeasy + "`" + FBnormal + "`" +FBhard + "`";
			}else{
				swal("错误","填空题自定义数量和总数不符!","error");
				return false;
			}
		}else{
			if(FBlevel == LevelEasy){
				if( parseInt(FBcount) > parseInt($("#fillEasy").html()) ){
					swal("错误","所选难度的填空题数量超过上限!","error");
					return false;
				}
			}else if(FBlevel == LevelNormal){
				if( parseInt(FBcount) > parseInt($("#fillNormal").html()) ){
					swal("错误","所选难度的填空题数量超过上限!","error");
					return false;
				}
			}else if(FBlevel == LevelHard){
				if( parseInt(FBcount) > parseInt($("#fillHard").html()) ){
					swal("错误","所选难度的填空题数量超过上限!","error");
					return false;
				}
			}
			JsonData += FBlevel + "`";
		}
	}
	var EQcount = $("#EQchoose tr").first().find("td input").val();
	var EQscore = $("#EQchoose tr").first().next().find("td input").val();
	var EQlevel = $("#EQchoose tr").first().next().next().find("select").val();
	var EQeasy = $("#EQchoose tr").first().next().next().find("td input").first().val();
	var EQnormal = $("#EQchoose tr").first().next().next().find("td input").first().next().next().val();
	var EQhard = $("#EQchoose tr").first().next().next().find("td input").last().val();
	if(EQcount != "" && EQcount != null && EQcount != "0"){
		if( parseInt(EQcount) > parseInt($("#essayQuestionCount").html()) ){
			swal("错误","问答题数量超过上限!","error");
			return false;
		}
		if(EQscore == "" || EQscore == null){
			swal("错误","问答题每道题的分值为空!","error");
			return false;
		}
		JsonData += "EQ" + "`" + EQcount + "`" + EQscore + "`";
		if(typeof(EQlevel) == "undefined"){
			if(EQeasy == "" || EQeasy == null){
				swal("错误","问答题自定义难度简单题数量为空!","error");
				return false;
			}else{
				if(parseInt(EQeasy) > parseInt($("#essayEasy").html())){
					swal("错误","问答题的简单题数量超过上限!","error");
					return false;
				}
			}
			if(EQnormal == "" || EQnormal == null){
				swal("错误","问答题自定义难度一般题数量为空!","error");
				return false;
			}else{
				if(parseInt(EQnormal) > parseInt($("#essayNormal").html())){
					swal("错误","问答题的一般题数量超过上限!","error");
					return false;
				}
			}
			if(EQhard == "" || EQhard == null){
				swal("错误","问答题自定义难度难题数量为空!","error");
				return false;
			}else{
				if(parseInt(EQhard) > parseInt($("#essayHard").html())){
					swal("错误","问答题的难题数量超过上限!","error");
					return false;
				}
			}
			if( (parseInt(EQeasy) + parseInt(EQnormal) + parseInt(EQhard)) == parseInt(EQcount) ){
				JsonData += "level" + "`" +  EQeasy + "`" + EQnormal + "`" +EQhard;
			}else{
				swal("错误","填空题自定义数量和总数不符!","error");
				return false;
			}
		}else{
			if(EQlevel == LevelEasy){
				if( parseInt(EQcount) > parseInt($("#essayEasy").html()) ){
					swal("错误","所选难度的填空题数量超过上限!","error");
					return false;
				}
			}else if(EQlevel == LevelNormal){
				if( parseInt(EQcount) > parseInt($("#essayNormal").html()) ){
					swal("错误","所选难度的填空题数量超过上限!","error");
					return false;
				}
			}else if(EQlevel == LevelHard){
				if( parseInt(EQcount) > parseInt($("#essayHard").html()) ){
					swal("错误","所选难度的填空题数量超过上限!","error");
					return false;
				}
			}
			JsonData += EQlevel;
		}
	}
//	if( parseInt(SCcount) == 0 || parseInt(MCcount) == 0 || parseInt(FBcount) == 0 || parseInt(EQcount) == 0 ){
//		swal("错误","题目设定数量不能为空!","error");
//		return false;
//	}
//	alert(JsonData);
	if(EQcount != "" || FBcount != "" || TFcount != "" || MCcount != "" || SCcount != ""){
		$.ajax({
			type : "post",
			url : "../testpaper/settestpaper",
			data:{"data":JsonData,"paperstyle":paperstyle},
			success : function(data) {
				var resCode = eval("("+data+")").resCode;
				var resMsg = eval("("+data+")").resMsg;
				if(resCode == "000000"){
					swal("成功",resMsg,"success");
				}else{
					swal("错误",resMsg,"error");
				}
			}
		});
	}else{
		swal("错误","试卷没有设定题目!","error");
		return false;
	}
	
}

function chooseDepartment(){
	$("#course_chapterSelectArea").html("");
	$("#course_chapterSelectArea").css({'position':'fixed','top':'20%','left':'28%','width':'600px;','border':'1px solid #ccc','height':'350px;','z-index':'20'});
	$("#course_chapterSelectArea").show();
	$.ajax({
		type : "post",
		url : "../academy/departmentlist",
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			data = eval("("+data+")").data;
			if(resCode == "000000"){
				var list = data.list;
				var len = list.length;
				var x = "<li>";
				for(var i = 0; i < parseInt(len); i++){
					x += "<input type=\"checkbox\" name=\"choosedDepart\" value=\""+list[i].secondCode+"\">" +
						 "<label for=\"choosedDepart\" style=\"margin: 10px 25px 10px 0;\">"+list[i].secondCodeValue+"</label>";
					if( (i + 1) % 5 == 0 ){
						x += "</li><li>";
					}
				}
				x += "</li>";
				$("#course_chapterSelectArea").append("<div class=\"panel\" style=\"width: 600px; height: 350px;\"><div class=\"panel-heading\" style=\"margin-top: 15px;\">"+
						"<input id=\"class_search_input\" class=\"form-control\" placeholder=\"请输入所在院系  如:计算机科学与技术系\" style=\"margin-left: 15%;width: 300px; display: inline;\">"+
						"<button type=\"button\" onclick=\"searchClasses()\" class=\"btn btn-default\" style=\"margin-left: 20px;margin-bottom: 5px;\">查询</button></div><hr style=\"margin-top: 10px;margin-bottom: 0;\">"+
						"<div id=\"class_search\" class=\"panel-body\" style=\"height:254px;width:598px;overflow: scroll;\"><ul style=\"list-style-type:none;\">" + x +
						"</ul></div>"+
						"<div class=\"panel-footer\" style=\"height:50px;\">" +
						"<button onclick=\"closeCourseSelection()\" class=\"btn btn-default\" style=\"float:right; margin-right:20px;\">关闭</button>" +
						"<button onclick=\"chooseClasses()\" class=\"btn btn-default\" style=\"float:right; margin-right:20px;\">确定</button>" +
						"<button onclick=\"BindAllStudent()\" class=\"btn btn-default\" style=\"float:left; margin-left:20px;\">任意学生</button>" +
						"</div></div>");
			}else{
				swal("错误",resMsg,"error");
				$("#course_chapterSelectArea").append("<div class=\"panel\" style=\"width: 600px; height: 350px;\"><div class=\"panel-heading\" style=\"margin-top: 15px;\">"+
						"<input id=\"class_search_input\" class=\"form-control\" placeholder=\"请输入所在院系  如:计算机科学与技术系\" style=\"margin-left: 15%;width: 300px; display: inline;\">"+
						"<button type=\"button\" onclick=\"searchClasses()\" class=\"btn btn-default\" style=\"margin-left: 20px;margin-bottom: 5px;\">查询</button></div><hr style=\"margin-top: 10px;margin-bottom: 0;\">"+
						"<div id=\"class_search\" class=\"panel-body\" style=\"height:254px;width:598px;overflow: scroll;\">" + 
						"</div>"+
						"<div class=\"panel-footer\" style=\"height:50px;\">" +
						"<button onclick=\"closeCourseSelection()\" class=\"btn btn-default\" style=\"float:right; margin-right:20px;\">关闭</button>" +
						"<button onclick=\"chooseClasses()\" class=\"btn btn-default\" style=\"float:right; margin-right:20px;\">确定</button>" +
						"<button onclick=\"BindAllStudent()\" class=\"btn btn-default\" style=\"float:left; margin-left:20px;\">任意学生</button>" +
						"</div></div>");
			}
		}
	});
}

function chooseClasses(){
	var code = "";
	$("#class_search ul li input:checked").each(function(){ 
		if(code != ""){
			code += "-";
		}
		code += $(this).attr('value');
	});
	$("#course_chapterSelectArea").html("");
	$("#course_chapterSelectArea").css({'position':'fixed','top':'20%','left':'28%','width':'600px;','border':'1px solid #ccc','height':'350px;','z-index':'20'});
	$("#course_chapterSelectArea").show();
	$.ajax({
		type : "post",
		url : "../classes/classlist",
		data:{"code": code},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			data = eval("("+data+")").data;
			if(resCode == "000000"){
				var list = data.list;
				var len = list.length;
				var x = "<li>";
				for(var i = 0; i < parseInt(len); i++){
					x += "<input type=\"checkbox\" name=\"choosedclasses\" value=\""+list[i].classCode+"\">" +
						 "<label for=\"choosedclasses\" style=\"margin: 10px 25px 10px 0;\">"+list[i].classValue+"</label>";
					if( (i + 1) % 5 == 0 ){
						x += "</li><li>";
					}
				}
				x += "</li>";
				$("#course_chapterSelectArea").append("<div class=\"panel\" style=\"width: 600px; height: 350px;\"><div class=\"panel-heading\" style=\"margin-top: 15px;\">"+
						"<input id=\"class_search_input\" class=\"form-control\" placeholder=\"请输入所在院系  如:计算机科学与技术系\" style=\"margin-left: 15%;width: 300px; display: inline;\">"+
						"<button type=\"button\" onclick=\"searchClasses()\" class=\"btn btn-default\" style=\"margin-left: 20px;margin-bottom: 5px;\">查询</button></div><hr style=\"margin-top: 10px;margin-bottom: 0;\">"+
						"<div id=\"class_search\" class=\"panel-body\" style=\"height:254px;width:598px;overflow: scroll;\"><ul style=\"list-style-type:none;\">" + x +
						"</ul></div>"+
						"<div class=\"panel-footer\" style=\"height:50px;\">" +
						"<button onclick=\"closeCourseSelection()\" class=\"btn btn-default\" style=\"float:right; margin-right:20px;\">关闭</button>" +
						"<button onclick=\"bondSearchResult()\" class=\"btn btn-default\" style=\"float:right; margin-right:20px;\">确定</button>" +
						"<button onclick=\"BindAllStudent()\" class=\"btn btn-default\" style=\"float:left; margin-left:20px;\">任意学生</button>" +
						"</div></div>");
			}else{
				swal("错误",resMsg,"error");
				$("#course_chapterSelectArea").append("<div class=\"panel\" style=\"width: 600px; height: 350px;\"><div class=\"panel-heading\" style=\"margin-top: 15px;\">"+
						"<input id=\"class_search_input\" class=\"form-control\" placeholder=\"请输入所在院系  如:计算机科学与技术系\" style=\"margin-left: 15%;width: 300px; display: inline;\">"+
						"<button type=\"button\" onclick=\"searchClasses()\" class=\"btn btn-default\" style=\"margin-left: 20px;margin-bottom: 5px;\">查询</button></div><hr style=\"margin-top: 10px;margin-bottom: 0;\">"+
						"<div id=\"class_search\" class=\"panel-body\" style=\"height:254px;width:598px;overflow: scroll;\">" + 
						"</div>"+
						"<div class=\"panel-footer\" style=\"height:50px;\">" +
						"<button onclick=\"closeCourseSelection()\" class=\"btn btn-default\" style=\"float:right; margin-right:20px;\">关闭</button>" +
						"<button onclick=\"bondSearchResult()\" class=\"btn btn-default\" style=\"float:right; margin-right:20px;\">确定</button>" +
						"<button onclick=\"BindAllStudent()\" class=\"btn btn-default\" style=\"float:left; margin-left:20px;\">任意学生</button>" +
						"</div></div>");
			}
		}
	});
}
function searchClasses(){
	var value = $("#class_search_input").val();
	$.ajax({
		type : "post",
		url : "../classes/proclasslist",
		data:{"profession":value},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			data = eval("("+data+")").data;
			if(resCode == "000000"){
				var list = data.list;
				var len = list.length;
				var x = "<li>";
				for(var i = 0; i < parseInt(len); i++){
					x += "<input type=\"checkbox\" name=\"choosedclasses\" value=\""+list[i].classCode+"\">" +
						 "<label for=\"choosedclasses\" style=\"margin: 10px 25px 10px 0;\">"+list[i].classValue+"</label>";
					if( (i + 1) % 5 == 0 ){
						x += "</li><li>";
					}
				}
				x += "</li>";
				$("#class_search").html("");
				$("#class_search").append("<ul style=\"list-style-type:none;\">" + x + "</ul>");
			}else{
				swal("错误",resMsg,"error");
			}
		}
	});
}

function bondSearchResult(){
	var code = "";
	var text = "";
	$("#class_search ul li input:checked").each(function(){ 
		if(code != ""){
			code += "-";
			text += "  ";
		}
		code += $(this).attr('value');
		text += $(this).next().html();
	});
	if(code != ""){
		$("#base_info tr").last().find("td").last().html("");
	    $("#base_info tr").last().find("td").last().html("<a onclick=\"chooseDepartment()\">"+text+"</a><span style=\"display:none\">"+code+"</span>");
	}else{
		$("#base_info tr").last().find("td").last().html("");
		$("#base_info tr").last().find("td").last().html("<a onclick=\"chooseDepartment()\">点击选择参与班级");
	}
	$("#course_chapterSelectArea").hide();  
}

/* 显示可以选用的单选题数量 */
function showSingleCount(){
	var Course = $("#base_info tr").first().find("td").last().next().html();
	var Chapter = $("#base_info tr").first().next().find("td").last().next().html();
	if(Course == "" || Course == null){
		swal("错误","科目为空!","error");
		return false;
	}
	$("#RestScore_single").html( $("#base_info tr").last().prev().prev().find("input").val() );
	$("#RestScore_single").parent().show();
	$.ajax({
		type : "post",
		url : "../testpaper/scount",
		data:{"course":Course,"chapter":Chapter},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			var arr = new Array();
			arr = resMsg.split("-")
			var all = arr[0];
			var easy = arr[1];
			var normal = arr[2];
			var hard = arr[3];
			if(resCode == "000000"){
				$("#singleChooseCount").html(all);
				$("#singleEasy").html(easy);
				$("#singleNormal").html(normal);
				$("#singleHard").html(hard);
				$("#singleChooseCount").parent().show();
			}else{
				swal("错误",resMsg,"error");
			}
		}
	});
}
/* 显示可以选用的多选题数量 */
function showMultiCount(){
	var Course = $("#base_info tr").first().find("td").last().next().html();
	var Chapter = $("#base_info tr").first().next().find("td").last().next().html();
	if(Course == "" || Course == null){
		swal("错误","科目为空!","error");
		return false;
	}
	var totalcount = $("#base_info tr").last().prev().prev().find("input").val();
	var count = $("#SCchoose tr td").first().next().find("input").val();
	var sco = $("#SCchoose tr").first().next().find("input").val();
	var x;
	if(count != "" && count != "0" && sco != "" && sco != "0"){
		x = parseInt(totalcount) - parseInt(count)*parseInt(sco);
	}else{
		x = totalcount;
	}
	$("#RestScore_multi").html(x);
	$("#RestScore_multi").parent().show();
	$.ajax({
		type : "post",
		url : "../testpaper/mcount",
		data:{"course":Course,"chapter":Chapter},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			var arr = new Array();
			arr = resMsg.split("-")
			var all = arr[0];
			var easy = arr[1];
			var normal = arr[2];
			var hard = arr[3];
			if(resCode == "000000"){
				$("#multiChooseCount").html(all);
				$("#multiEasy").html(easy);
				$("#multiNormal").html(normal);
				$("#multiHard").html(hard);
				$("#multiChooseCount").parent().show();
			}else{
				swal("错误",resMsg,"error");
			}
		}
	});
}
/* 显示可以选用的判断题数量 */
function showTFCount(){
	var Course = $("#base_info tr").first().find("td").last().next().html();
	var Chapter = $("#base_info tr").first().next().find("td").last().next().html();
	if(Course == "" || Course == null){
		swal("错误","科目为空!","error");
		return false;
	}
	var totalcount = $("#base_info tr").last().prev().prev().find("input").val();
	var Scount = $("#SCchoose tr td").first().next().find("input").val();
	var Ssco = $("#SCchoose tr").first().next().find("input").val();
	var Mcount = $("#MCchoose tr").first().find("td input").val();
	var Msco = $("#MCchoose tr").first().next().find("td input").val();
	var x;
	if(Scount != "" && Scount != "0" && Ssco != "" && Ssco != "0"){
		totalcount = parseInt(totalcount) - parseInt(Scount)*parseInt(Ssco)
	}
	if(Mcount != "" && Mcount != "0" && Msco != "" && Msco != "0"){
		x = parseInt(totalcount) - parseInt(Mcount)*parseInt(Msco);
		
	}else{
		x = totalcount;
	}
	$("#RestScore_tf").html(x);
	$("#RestScore_tf").parent().show();
	$.ajax({
		type : "post",
		url : "../testpaper/tfcount",
		data:{"course":Course,"chapter":Chapter},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			var arr = new Array();
			arr = resMsg.split("-")
			var all = arr[0];
			var easy = arr[1];
			var normal = arr[2];
			var hard = arr[3];
			if(resCode == "000000"){
				$("#tfCount").html(all);
				$("#tfEasy").html(easy);
				$("#tfNormal").html(normal);
				$("#tfHard").html(hard);
				$("#tfCount").parent().show();
			}else{
				swal("错误",resMsg,"error");
			}
		}
	});
}
/* 显示可以选用的填空题数量 */
function showFillCount(){
	var Course = $("#base_info tr").first().find("td").last().next().html();
	var Chapter = $("#base_info tr").first().next().find("td").last().next().html();
	if(Course == "" || Course == null){
		swal("错误","科目为空!","error");
		return false;
	}
	var totalcount = $("#base_info tr").last().prev().prev().find("input").val();
	var Scount = $("#SCchoose tr td").first().next().find("input").val();
	var Ssco = $("#SCchoose tr").first().next().find("input").val();
	var Mcount = $("#MCchoose tr").first().find("td input").val();
	var Msco = $("#MCchoose tr").first().next().find("td input").val();
	var TFcount = $("#TFchoose tr").first().find("td input").val();
	var TFsco = $("#TFchoose tr").first().next().find("td input").val();
	var x;
	if(Scount != "" && Scount != "0" && Ssco != "" && Ssco != "0"){
		totalcount = parseInt(totalcount) - parseInt(Scount)*parseInt(Ssco)
	}
	if(Mcount != "" && Mcount != "0" && Mcount != "" && Mcount != "0"){
		totalcount = parseInt(totalcount) - parseInt(Mcount)*parseInt(Msco);
	}
	if(TFcount != "" && TFcount != "0" && TFsco != "" && TFsco != "0"){
		x = parseInt(totalcount) - parseInt(TFcount)*parseInt(TFsco);
	}else{
		x = totalcount;
	}
	$("#RestScore_fill").html(x);
	$("#RestScore_fill").parent().show();
	$.ajax({
		type : "post",
		url : "../testpaper/fcount",
		data:{"course":Course,"chapter":Chapter},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			var arr = new Array();
			arr = resMsg.split("-")
			var all = arr[0];
			var easy = arr[1];
			var normal = arr[2];
			var hard = arr[3];
			if(resCode == "000000"){
				$("#fillBlankCount").html(all);
				$("#fillEasy").html(easy);
				$("#fillNormal").html(normal);
				$("#fillHard").html(hard);
				$("#fillBlankCount").parent().show();
			}else{
				swal("错误",resMsg,"error");
			}
		}
	});
}
/* 显示可以选用的问答题数量 */
function showEssayCount(){
	var Course = $("#base_info tr").first().find("td").last().next().html();
	var Chapter = $("#base_info tr").first().next().find("td").last().next().html();
	if(Course == "" || Course == null){
		swal("错误","科目为空!","error");
		return false;
	}
	var totalcount = $("#base_info tr").last().prev().prev().find("input").val();
	var Scount = $("#SCchoose tr td").first().next().find("input").val();
	var Ssco = $("#SCchoose tr").first().next().find("input").val();
	var Mcount = $("#MCchoose tr").first().find("td input").val();
	var Msco = $("#MCchoose tr").first().next().find("td input").val();
	var TFcount = $("#TFchoose tr").first().find("td input").val();
	var TFsco = $("#TFchoose tr").first().next().find("td input").val();
	var Fcount = $("#FBchoose tr").first().find("td input").val();
	var Fsco = $("#FBchoose tr").first().next().find("td input").val();
	var x;
	if(Scount != "" && Scount != "0" && Scount != "" && Scount != "0"){
		totalcount = parseInt(totalcount) - parseInt(Scount)*parseInt(Ssco)
	}
	if(Mcount != "" && Mcount != "0" && Mcount != "" && Mcount != "0"){
		totalcount = parseInt(totalcount) - parseInt(Mcount)*parseInt(Msco);
	}
	if(TFcount != "" && TFcount != "0" && TFcount != "" && TFcount != "0"){
		totalcount = parseInt(totalcount) - parseInt(TFcount)*parseInt(TFsco);
	}
	if(Fcount != "" && Fcount != "0" && Fsco != "" && Fsco != "0"){
		x = parseInt(totalcount) - parseInt(Fcount)*parseInt(Fsco);
	}else{
		x = totalcount;
	}
	$("#RestScore_essay").html(x);
	$("#RestScore_essay").parent().show();
	$.ajax({
		type : "post",
		url : "../testpaper/ecount",
		data:{"course":Course,"chapter":Chapter},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			var arr = new Array();
			arr = resMsg.split("-")
			var all = arr[0];
			var easy = arr[1];
			var normal = arr[2];
			var hard = arr[3];
			if(resCode == "000000"){
				$("#essayQuestionCount").html(all);
				$("#essayEasy").html(easy);
				$("#essayNormal").html(normal);
				$("#essayHard").html(hard);
				$("#essayQuestionCount").parent().show();
			}else{
				swal("错误",resMsg,"error");
			}
		}
	});
}

function chooseMarkers(){
	$("#course_chapterSelectArea").html("");
	$("#course_chapterSelectArea").css({'position':'fixed','top':'20%','left':'28%','width':'600px;','border':'1px solid #ccc','height':'350px;','z-index':'20'});
	$("#course_chapterSelectArea").show();
	$.ajax({
		type : "post",
		url : "../testpaper/getmarkers",
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			data = eval("("+data+")").data;
			if(resCode == "000000"){
				var list = data.list;
				var len = list.length;
				var x = "<li>";
				for(var i = 0; i < parseInt(len); i++){
					x += "<input type=\"checkbox\" name=\"choosedmarkers\" value=\""+list[i].userId+"\">" +
						 "<label for=\"choosedmarkers\" style=\"margin: 10px 25px 10px 0;\">"+list[i].name+"</label>";
					if( (i + 1) % 5 == 0 ){
						x += "</li><li>";
					}
				}
				x += "</li>";
				$("#course_chapterSelectArea").append("<div class=\"panel\" style=\"width: 600px; height: 350px;\"><div class=\"panel-heading\" style=\"margin-top: 15px;\">"+
						"<input id=\"class_search_input\" class=\"form-control\" placeholder=\"请输入所在院系  如:计算机科学与技术系\" style=\"margin-left: 15%;width: 300px; display: inline;\">"+
						"<button type=\"button\" onclick=\"searchMarkers()\" class=\"btn btn-default\" style=\"margin-left: 20px;margin-bottom: 5px;\">查询</button></div><hr style=\"margin-top: 10px;margin-bottom: 0;\">"+
						"<div id=\"search_markers\" class=\"panel-body\" style=\"height:254px;width:598px;overflow: scroll;\"><ul style=\"list-style-type:none;\">" + x +
						"</ul></div>"+
						"<div class=\"panel-footer\" style=\"height:50px;\"><button onclick=\"closeCourseSelection()\" class=\"btn btn-default\" style=\"float:right; margin-right:20px;\">关闭</button><button onclick=\"bondMarkers()\" class=\"btn btn-default\" style=\"float:right; margin-right:20px;\">确定</button></div></div>");
			}else{
				swal("错误",resMsg,"error");
				$("#course_chapterSelectArea").append("<div class=\"panel\" style=\"width: 600px; height: 350px;\"><div class=\"panel-heading\" style=\"margin-top: 15px;\">"+
						"<input id=\"class_search_input\" class=\"form-control\" placeholder=\"请输入所在院系  如:计算机科学与技术系\" style=\"margin-left: 15%;width: 300px; display: inline;\">"+
						"<button type=\"button\" onclick=\"searchMarkers()\" class=\"btn btn-default\" style=\"margin-left: 20px;margin-bottom: 5px;\">查询</button></div><hr style=\"margin-top: 10px;margin-bottom: 0;\">"+
						"<div id=\"search_markers\" class=\"panel-body\" style=\"height:254px;width:598px;overflow: scroll;\">" + 
						"</div>"+
						"<div class=\"panel-footer\" style=\"height:50px;\"><button onclick=\"closeCourseSelection()\" class=\"btn btn-default\" style=\"float:right; margin-right:20px;\">关闭</button><button onclick=\"bondMarkers()\" class=\"btn btn-default\" style=\"float:right; margin-right:20px;\">确定</button></div></div>");
			}
		}
	});
}

function bondMarkers(){
	var code = "";
	var text = "";
	$("#search_markers ul li input:checked").each(function(){ 
		if(code != ""){
			code += "-";
			text += "  ";
		}
		code += $(this).attr('value');
		text += $(this).next().html();
	});
	if(code != ""){
		$("#base_info tr").last().prev().find("td").last().html("");
	    $("#base_info tr").last().prev().find("td").last().html("<a onclick=\"chooseMarkers()\">"+text+"</a><span style=\"display:none\">"+code+"</span>");
	}else{
		$("#base_info tr").last().prev().find("td").last().html("");
		$("#base_info tr").last().prev().find("td").last().html("<a onclick=\"chooseMarkers()\">点击选择阅卷人");
	}
	$("#course_chapterSelectArea").hide();
}

function searchMarkers(){
	
}

function checkRestScore_single(){
	var Stotal = $("#RestScore_single").html();
	var Scount = $("#SCchoose tr td").first().next().find("input").val();
	var Ssco = $("#SCchoose tr").first().next().find("input").val();
	if( parseInt(Scount) < 0){
		swal("警告","数量不能小于0!","warning");
	}
	if( parseInt(Ssco) < 0){
		swal("警告","分值不能小于0!","warning");
	}
	if( parseInt(Stotal) < parseInt(Scount)*parseInt(Ssco) ){
		var r = parseInt(Scount)*parseInt(Ssco) - parseInt(Stotal);
		swal("警告","所选分值能超过试卷总分"+r+"分!","warning");
	}
}
function checkRestScore_multi(){
	var Mtotal = $("#RestScore_multi").html();
	var Mcount = $("#MCchoose tr").first().find("td input").val();
	var Msco = $("#MCchoose tr").first().next().find("td input").val();
	if( parseInt(Mcount) < 0){
		swal("警告","数量不能小于0!","warning");
	}
	if( parseInt(Msco) < 0){
		swal("警告","分值不能小于0!","warning");
	}
	if( parseInt(Mtotal) < parseInt(Mcount)*parseInt(Msco) ){
		var r = parseInt(Mcount)*parseInt(Msco) - parseInt(Mtotal);
		swal("警告","所选分值能超过试卷总分"+r+"分!","warning");
	}
}
function checkRestScore_tf(){
	var Ttotal = $("#RestScore_tf").html();
	var Tcount = $("#TFchoose tr").first().find("td input").val();
	var Tsco = $("#TFchoose tr").first().next().find("td input").val();
	if( parseInt(Tcount) < 0){
		swal("警告","数量不能小于0!","warning");
	}
	if( parseInt(Tsco) < 0){
		swal("警告","分值不能小于0!","warning");
	}
	if( parseInt(Ttotal) < parseInt(Tcount)*parseInt(Tsco) ){
		var r = parseInt(Tcount)*parseInt(Tsco) - parseInt(Ttotal);
		swal("警告","所选分值能超过试卷总分"+r+"分!","warning");
	}
}
function checkRestScore_fill(){
	var Ftotal = $("#RestScore_fill").html();
	var Fcount = $("#FBchoose tr").first().find("td input").val();
	var Fsco = $("#FBchoose tr").first().next().find("td input").val();
	if( parseInt(Fcount) < 0){
		swal("警告","数量不能小于0!","warning");
	}
	if( parseInt(Fsco) < 0){
		swal("警告","分值不能小于0!","warning");
	}
	if( parseInt(Ftotal) < parseInt(Fcount)*parseInt(Fsco) ){
		var r = parseInt(Fcount)*parseInt(Fsco) - parseInt(Ftotal);
		swal("警告","所选分值能超过试卷总分"+r+"分!","warning");
	}
}
function checkRestScore_essay(){
	var Etotal = $("#RestScore_essay").html();
	var Ecount = $("#EQchoose tr").first().find("td input").val();
	var Esco = $("#EQchoose tr").first().next().find("td input").val();
	if( parseInt(Ecount) < 0){
		swal("警告","数量不能小于0!","warning");
	}
	if( parseInt(Esco) < 0){
		swal("警告","分值不能小于0!","warning");
	}
	if( parseInt(Etotal) < parseInt(Ecount)*parseInt(Esco) ){
		swal("警告","所选分值不能超过试卷总分!","warning");
	}
	if( parseInt(Etotal) > parseInt(Ecount)*parseInt(Esco) ){
		var r = parseInt(Ecount)*parseInt(Esco) - parseInt(Etotal);
		swal("警告","所选分值能超过试卷总分"+r+"分!","warning");
	}
}

function checkTestTime(){
	//alert("check");
	var starttime = $("#base_info tr").first().next().next().next().find("td").last().find("input").val();
	var arr1 = new Array();
	arr1 = starttime.split(":");
	var endtime = $("#base_info tr").first().next().next().next().next().find("td").last().find("input").val();
	var arr2 = new Array();
	arr2 = endtime.split(":");
	if(parseInt(arr2[0]) < parseInt(arr1[0]) ){
		swal("错误","考试结束时间不能晚于开始时间","error");
		return false;
	}
	if( parseInt(arr2[0]) == parseInt(arr1[0]) && parseInt(arr2[1]) < parseInt(arr1[1]) ){
		swal("错误","考试结束时间不能晚于开始时间","error");
		return false;
	}
	if(parseInt(arr2[0]) < parseInt(arr1[0]) && parseInt(arr2[1]) < parseInt(arr1[1])){
		swal("错误","考试结束时间不能晚于开始时间","error");
		return false;
	}
	if( arr2[0] == arr1[0] && arr2[1] == arr1[1] ){
		swal("错误","考试结束时间不能和开始时间相同","error");
		return false;
	}
	var h = arr2[0] - arr1[0];
	var m = arr2[1] - arr1[1];
	var x = h * 60 + m;
	if(x > 150){
		swal("错误","建议考试时间为150钟以内!","error");
		return false;
	}
}

function BindAllStudent(){
	$("#base_info tr").last().find("td").last().html("");
    $("#base_info tr").last().find("td").last().html("<a onclick=\"chooseDepartment()\">任意学生</a><span style=\"display:none\">ForAllStudent</span>");
    $("#course_chapterSelectArea").hide();
}
