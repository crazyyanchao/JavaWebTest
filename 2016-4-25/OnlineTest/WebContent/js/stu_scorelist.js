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
function load(data){
	var totalPages = data.totalPages;
	var currPage = data.currPage;
	var len = data.list.length;
	var s = data.list;
	$("#currPage").html(currPage);
	$("#totalPage").html(totalPages);
	for(var i = 0; i < len; i++){
		var id = s[i].id;
		var course = s[i].course;
		var stime = s[i].startTime;
		var etime = s[i].endTime;
		var score = s[i].score;
		$("#scorelist").append("<tr><td>"+id+"</td><td><a onclick=\"checkMyTest(this)\">"+course+"</a></td><td>"+stime+"</td><td>"+etime+"</td><td>"+score+"</td></tr>");
	}
}
$(document).ready(function(){
	$.ajax({
		type : "post",
		url : "../tests/scorelist",
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			data = eval("("+data+")").data;
			if(resCode == "000000"){
				load(data);
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

function checkMyTest(x){
	var s = $(x).parent().prev().html();
	var course = $(x).html();
	$.ajax({
		type : "post",
		url : "../tests/testinfo",
		data: {"id":s},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				var single = eval("("+data+")").data.single;
				var singlelist = eval("("+data+")").data.singlelist;
				var multi = eval("("+data+")").data.multi;
				var multilist = eval("("+data+")").data.multilist;
				var tf =  eval("("+data+")").data.tf;
				var tflist =  eval("("+data+")").data.tflist;
				var fill = eval("("+data+")").data.fill;
				var filllist = eval("("+data+")").data.filllist;
				var essay = eval("("+data+")").data.essay;
				var essaylist = eval("("+data+")").data.essaylist;
				$('#testPaperShowing').html("");
				$('#testPaperShowing').css({'position':'fixed','margin':'0 auto','left':'0px','right':'0px','top':'10%','width':'90%','height':'80%','z-index':'12','background-color':'#eee'});
				$('#testPaperShowing').show();
				$('#testPaperShowing').append("<div class=\"panel\" style=\"height:100%\"><div class=\"panel-heading\" style=\"margin-top: 15px;\">"+
						"<h4 style=\"text-align: center;\">"+course+"</h4></div><hr style=\"margin-top: 10px;margin-bottom: 0;\">"+
						"<div id=\"testpaper_body\" class=\"panel-body\" style=\"height:86%;overflow: scroll;\">" +
						"</div>"+
						"<div class=\"panel-footer\" style=\"height:50px;\"><button onclick=\"closePanel()\" class=\"btn btn-default\" style=\"float:right; margin-right:20px;\">关闭</button></div></div>");
				if(singlelist != null && single != null){
					var s = single.split("`");
					$("#testpaper_body").append("<span class=\"suptitle\">单选题</span><br><br><ol id=\"singlechoose\"></ol>");
					for(var i = 0; i < singlelist.length; i++){
						$("#singlechoose").append("<li><span class=\"testTitle\">"+singlelist[i].title+"</span><span style=\"display:none\">"+singlelist[i].id+"</span><br>"+
							"A:&nbsp;&nbsp;<span class=\"testContent\">"+singlelist[i].answer1+"</span><br>"+
							"B:&nbsp;&nbsp;<span class=\"testContent\">"+singlelist[i].answer2+"</span><br>"+
							"C:&nbsp;&nbsp;<span class=\"testContent\">"+singlelist[i].answer3+"</span><br>"+
							"D:&nbsp;&nbsp;<span class=\"testContent\">"+singlelist[i].answer4+"</span><br>"+
							"&nbsp;&nbsp;<strong>你的答案</strong>："+s[i]+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong><strong>参考答案</strong></strong>:"+singlelist[i].trueanswer+
							"</li><br>");
					}
				}else if( (singlelist == null && single != null) || (singlelist != null && single == null) ){
					if(lowerIe()){
						alert("出现了一些错误，请重新尝试!");
					}else{
						swal("错误","s出现了一些错误，请重新尝试!","error");
					}
				}
				//multi
				if(multilist != null && multi != null){
					var s = multi.split("`");
					$("#testpaper_body").append("<span class=\"suptitle\">多选题</span><br><br><ol id=\"multichoose\"></ol>");
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
							"&nbsp;&nbsp;<strong>你的答案</strong>："+xx+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>参考答案</strong>:"+multilist[i].trueanswer+
							"</li><br>");
					}
				}else if( (multilist == null && multi != null) || (multilist != null && multi == null) ){
					if(lowerIe()){
						alert("出现了一些错误，请重新尝试!");
					}else{
						swal("错误","m出现了一些错误，请重新尝试!","error");
					}
				}
				//tf
				if(tflist != null && tf != null){
					var s = tf.split("`");
					$("#testpaper_body").append("<span class=\"suptitle\">判断题</span><br><br><ol id=\"tfquestion\"></ol>");
					for(var i = 0; i < tflist.length; i++){
						$("#tfquestion").append("<li><span class=\"testTitle\">"+tflist[i].title+"</span><span style=\"display:none\">"+tflist[i].id+"</span><br>"+
							"&nbsp;&nbsp;<strong>你的答案</strong>："+s[i]+"<br>" +
							"&nbsp;&nbsp;<strong>参考答案</strong>:"+tflist[i].answer+
							"</li><br>");
					}
				}
				//fill
				if(filllist != null && fill != null){
					var s = fill.split("`");
					$("#testpaper_body").append("<span class=\"suptitle\">填空题</span><br><br><ol id=\"fillchoose\"></ol>");
					for(var i = 0; i < filllist.length; i++){
						$("#fillchoose").append("<li><span class=\"testTitle\">"+filllist[i].title+"</span><span style=\"display:none\">"+filllist[i].id+"</span><br>"+
							"&nbsp;&nbsp;<strong>你的答案</strong>："+s[i]+"<br>" +
							"&nbsp;&nbsp;<strong>参考答案</strong>:"+filllist[i].answer+
							"</li><br>");
					}
				}else if( (filllist == null && fill != null) || (filllist != null && fill == null) ){
					if(lowerIe()){
						alert("出现了一些错误，请重新尝试!");
					}else{
						swal("错误","f出现了一些错误，请重新尝试!","error");
					}
				}
				//essay
				if(essaylist != null && essay != null){
					var s = essay.split("`");
					$("#testpaper_body").append("<span class=\"suptitle\">问答题</span><br><br><ol id=\"essaychoose\"></ol>");
					for(var i = 0; i < essaylist.length; i++){
						$("#essaychoose").append("<li><span class=\"testTitle\">"+essaylist[i].title+"</span><span style=\"display:none\">"+essaylist[i].id+"</span><br>"+
							"&nbsp;&nbsp;<strong>你的答案</strong>："+s[i]+"<br>" +
							"&nbsp;&nbsp;<strong>参考答案</strong>:"+essaylist[i].answer+
							"</li><br>");
					}
				}else if( (essaylist == null && essay != null) || (essaylist != null && essay == null) ){
					if(lowerIe()){
						alert("出现了一些错误，请重新尝试!");
					}else{
						swal("错误","e出现了一些错误，请重新尝试!","error");
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
	$('#testPaperShowing').hide("slow");
}

function stuScoreListFirst(){
	$("#scorelist").html("");
	$.ajax({
		type : "post",
		url : "../tests/scorelist",
		data: {"currPage":"1","pageSize":"10"}, 
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			data = eval("("+data+")").data;
			if(resCode == "000000"){
				load(data);
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

function stuScoreListLast(){
	$("#scorelist").html("");
	$.ajax({
		type : "post",
		url : "../tests/scorelist",
		data: {"currPage":$("#totalPage").html(),"pageSize":"10"}, 
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			data = eval("("+data+")").data;
			if(resCode == "000000"){
				load(data);
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

function stuScoreListNext(){
	var curr = parseInt($("#currPage").html());
	var total = parseInt($("#totalPage").html());
	if(curr < total){
		curr = curr + 1;
	}
	$("#scorelist").html("");
	$.ajax({
		type : "post",
		url : "../tests/scorelist",
		data: {"currPage": curr,"pageSize":"10"}, 
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			data = eval("("+data+")").data;
			if(resCode == "000000"){
				load(data);
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

function stuScoreListPrev(){
	var curr = parseInt($("#currPage").html());
	if(curr > 1){
		curr = curr - 1;
	}
	$("#scorelist").html("");
	$.ajax({
		type : "post",
		url : "../tests/scorelist",
		data: {"currPage": curr,"pageSize":"10"}, 
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			data = eval("("+data+")").data;
			if(resCode == "000000"){
				load(data);
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