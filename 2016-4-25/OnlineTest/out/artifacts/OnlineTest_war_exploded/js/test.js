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
//function onbeforeunload_handler(){ 
//	alert("3213123123") 
//}
////禁用浏览器刷新
//window.attachEvent("onbeforeunload",onbeforeunload_handler());
/*禁止后退*/
$(document).ready(function ($) {
	if (window.history && window.history.pushState) {
		$(window).on('popstate', function () {
			var hashLocation = location.hash;
			var hashSplit = hashLocation.split("#!/");
			var hashName = hashSplit[1];
			if (hashName !== '') {
				var hash = window.location.hash;
				if (hash === '') {
					alert("考试页面，禁止后退!");
				}
			}
		});
	    window.history.pushState('forward', null, '');
	}
});


function disptime() {
	var time = new Date(); 
	var year = time.getFullYear();
	var month = time.getMonth() + 1;
	var day = time.getDate();
	var hour = time.getHours(); 
	var minute = time.getMinutes();
	var second = time.getSeconds();
	var apm = "AM"; 
	if (hour > 12) 
	{
		hour = hour - 12;
		apm = "PM";
	}
	if (minute < 10) //如果分钟只有1位，补0显示 
		minute = "0" + minute;
	if (second < 10) //如果秒数只有1位，补0显示 
		second = "0" + second;

	$("#dynamicTime").html(year + "年"
			+ month + "月" + day + "日 " + hour + ":" + minute + ":"
			+ second + " " + apm);
	setTimeout("disptime()", 1000);
}
$(document).ready(disptime);
function singleload(s){
	var len = s.length;
	for(var i = 0; i < parseInt(len); i++){
			$("#singlechoose").append("<li><span class=\"testTitle\">"+s[i].title+"</span><span style=\"display:none\">"+s[i].id+"</span><br>"+
				"<input type=\"radio\" name=\""+(i+1)+"\" value=\"A\"><span class=\"testContent\">"+s[i].answer1+"</span><br>"+
				"<input type=\"radio\" name=\""+(i+1)+"\" value=\"B\"><span class=\"testContent\">"+s[i].answer2+"</span><br>"+
				"<input type=\"radio\" name=\""+(i+1)+"\" value=\"C\"><span class=\"testContent\">"+s[i].answer3+"</span><br>"+
				"<input type=\"radio\" name=\""+(i+1)+"\" value=\"D\"><span class=\"testContent\">"+s[i].answer4+"</span><br>"+
				"</li><br>");
		}
}
function multiload(s){
	var len = s.length;
	for(var i = 0; i < parseInt(len); i++){
		var ans4 = "<input type=\"checkbox\" value=\"D\"><span class=\"testContent\">"+s[i].answer4+"</span><br>";
		var ans5 = "<input type=\"checkbox\" value=\"D\"><span class=\"testContent\">"+s[i].answer5+"</span><br>";
		var ans6 = "<input type=\"checkbox\" value=\"D\"><span class=\"testContent\">"+s[i].answer6+"</span><br>";
		if(s[i].answer4 == " " || s[i].answer4 == ""){
			ans4 = "";
		}
		if(s[i].answer5 == " " || s[i].answer5 == ""){
			ans5 ="";
		}
		if(s[i].answer6 == " " || s[i].answer6 == ""){
			ans6 = "";
		}
		$("#multichoose").append("<li><span class=\"testTitle\">"+s[i].title+"</span><span style=\"display:none\">"+s[i].id+"</span><br>"+
			"<input type=\"checkbox\" value=\"A\"><span class=\"testContent\">"+s[i].answer1+"</span><br>"+
			"<input type=\"checkbox\" value=\"B\"><span class=\"testContent\">"+s[i].answer2+"</span><br>"+
			"<input type=\"checkbox\" value=\"C\"><span class=\"testContent\">"+s[i].answer3+"</span><br>"+ ans4 + ans5 + ans6 +
			"</li><br>");
	}
}
function tfload(s){
	var len = s.length;
	for(var i = 0; i < parseInt(len); i++){
		$("#tfquestion").append("<li><span class=\"testTitle\">"+s[i].title+"</span><span style=\"display:none\">"+s[i].id+"</span><br>"+
				"<input type=\"radio\" name=\"tf"+(i+1)+"\" value=\"6000\"><span class=\"testContent\">True</span><br>"+
				"<input type=\"radio\" name=\"tf"+(i+1)+"\" value=\"6001\"><span class=\"testContent\">False</span><br>"+
				"</li><br>");
	}
}
function fillload(s){
	var len = s.length;
	for(var i = 0; i < parseInt(len); i++){
			$("#fillblank").append("<li><span class=\"testTitle\">"+s[i].title+"</span><span style=\"display:none\">"+s[i].id+"</span><br>"+
				"<input type=\"text\" style=\"width: 25%\">" +
				"</li><br>");
		}
}
function essayload(s){
	var len = s.length;
	for(var i = 0; i < parseInt(len); i++){
			$("#essayquestion").append("<li><span class=\"testTitle\">"+s[i].title+"</span><span style=\"display:none\">"+s[i].id+"</span><br>"+
				"<textarea class=\"form-control elastic\" rows=\"10\"></textarea>"+
				"</li><br>");
		}
}
$(document).ready(function(){
	$.ajax({
		type : "post",
		url : "../testonline/startTest",
		success : function(data) {
			var testPaperStyle = eval("("+data+")").data.testPaperStyle;
			var time = eval("("+data+")").data.time;
			var single = eval("("+data+")").data.single;
			var multi = eval("("+data+")").data.multi;
			var tf = eval("("+data+")").data.TF;
			var fill = eval("("+data+")").data.fill;
			var essay = eval("("+data+")").data.essay;
			if(single != null){
				$("#test_body").append("<span class=\"suptitle\">一、单选题</span><br><br><ol id=\"singlechoose\"></ol>");
				singleload(single);
			}
			if(multi != null){
				if(single != null){
					$("#test_body").append("<span class=\"suptitle\">二、多选题</span><br><br><ol id=\"multichoose\"></ol>");
				}else{
					$("#test_body").append("<span class=\"suptitle\">一、多选题</span><br><br><ol id=\"multichoose\"></ol>");
				}
				multiload(multi);
			}
			if(tf != null){
				if(single != null && multi != null){
					$("#test_body").append("<span class=\"suptitle\">三、判断题</span><br><br><ol id=\"tfquestion\"></ol>");
				}else if( (single == null && multi != null) || (single != null && multi == null) ){
					$("#test_body").append("<span class=\"suptitle\">二、判断题</span><br><br><ol id=\"tfquestion\"></ol>");
				}else{
					$("#test_body").append("<span class=\"suptitle\">一、判断题</span><br><br><ol id=\"tfquestion\"></ol>");
				}
				tfload(tf);
			}
			if(fill != null){
				if(single != null && multi != null && tf != null){
					$("#test_body").append("<span class=\"suptitle\">四、填空题</span><br><br><ol id=\"fillblank\"></ol>");
				}else if( (single == null && multi != null && tf != null) || (single != null && multi == null && tf != null) ||
						(single != null && multi != null && tf == null) ){
					$("#test_body").append("<span class=\"suptitle\">三、填空题</span><br><br><ol id=\"fillblank\"></ol>");
				}else if( (single == null && multi == null && tf != null) || (single == null && multi != null && tf == null) ||
						(single != null && multi == null && tf == null) ){
					$("#test_body").append("<span class=\"suptitle\">二、填空题</span><br><br><ol id=\"fillblank\"></ol>");
				}else{
					$("#test_body").append("<span class=\"suptitle\">一、填空题</span><br><br><ol id=\"fillblank\"></ol>");
				}
				fillload(fill);
			}
			if(essay != null){
				if(single != null && multi != null && tf != null && fill != null){
					$("#test_body").append("<span class=\"suptitle\">五、问答题</span><br><br><ol id=\"essayquestion\"></ol>");
				}else if( (single == null && multi != null && tf != null && fill != null) || (single != null && multi == null && tf != null && fill != null) ||
						(single != null && multi != null && tf == null  && fill != null) || (single != null && multi != null && tf != null  && fill == null) ){
					$("#test_body").append("<span class=\"suptitle\">四、问答题</span><br><br><ol id=\"essayquestion\"></ol>");
				}else if( (single == null && multi == null && tf != null  && fill != null) || (single == null && multi != null && tf == null  && fill != null) ||
						(single == null && multi != null && tf != null  && fill == null) || (single != null && multi == null && tf == null  && fill != null) ||
						(single != null && multi == null && tf != null  && fill == null) || (single != null && multi != null && tf == null  && fill == null) ){
					$("#test_body").append("<span class=\"suptitle\">三、问答题</span><br><br><ol id=\"essayquestion\"></ol>");
				}else if( (single != null && multi == null && tf == null  && fill == null) || (single == null && multi != null && tf == null  && fill == null) ||
						(single == null && multi == null && tf != null  && fill == null) || (single == null && multi == null && tf == null  && fill != null) ){
					$("#test_body").append("<span class=\"suptitle\">二、问答题</span><br><br><ol id=\"essayquestion\"></ol>");
				}else{
					$("#test_body").append("<span class=\"suptitle\">一、问答题</span><br><br><ol id=\"essayquestion\"></ol>");
				}
				essayload(essay);
			}
			$("#testpaperstyle").html(testPaperStyle);
			$("#rest_time").html(time);
		}
	});
});

function restTime(){
	var t = parseInt( $("#rest_time").html() );
	t = t - 1;
	if( t == 0 ){
		finishTest();
	}
	if( t == 15){
		swal({
			title: "距离考试结束还有15分钟",   
			text: "3秒后自动关闭弹窗,点击OK关闭弹窗",   
			timer: 3000,   
			showConfirmButton: true
		});
	}
	if( t == 5){
		swal({
			title: "距离考试结束还有5分钟",   
			text: "3秒后自动关闭弹窗,点击OK关闭弹窗",   
			timer: 3000,   
			showConfirmButton: true
		});
	}
	$("#rest_time").html(t);
	//每60秒执行一次
	setTimeout("restTime()", 60000);
}

$(document).ready(restTime);

function finishTest(){
	if(lowerIe()){
		if(confirm("确定提交?"))
		 {
			saveAnswers();
		 }else{
			 alert("你取消了提交");
		 }
	}else{
		swal(
			{   
				title: "确定提交?",   
				text: "一旦提交，将不能再次修改答案!",
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
					var x = $("#testpaperstyle").html();
					if(x == "4001"){
						saveAnswersFixed();
					}else{
						saveAnswersNotFixed();
					}
					
				} 
				else{     
					swal("Cancelled", "你取消了提交", "error");   
				} 
			}
			);
	}
	
}

function saveAnswersFixed(){
	var singleData = "";
	var multiData = "";
	var tfData = "";
	var fillData = "";
	var essayData = "";
	var singlelen = $("#singlechoose li").length;
	var multilen = $("#multichoose li").length;
	var tflen = $("#tfquestion li").length;
	var filllen = $("#fillblank li").length;
	var essaylen = $("#essayquestion li").length;
	if(singlelen != 0){
		var x = $("#singlechoose li").first();
		for(var i = 0; i < singlelen; i++){
			if(typeof(x.find("input:radio:checked").val()) == "undefined"){
				singleData += " "+"`";
			}else{
				singleData += x.find("input:radio:checked").val() +"`";
				
			}
			x = x.next().next();
		}
		singleData = singleData.substring(0,singleData.length-1);
	}
	if(multilen != 0){
		var x = $("#multichoose li").first();
		for(var i = 0; i < multilen; i++){
			var str = "";
			x.find("input:checked").each(function(){
				str += $(this).val() +"-";
			});
			if(str === ""){
				str = " ";
			}	
			multiData += str;
			multiData = multiData.substring(0,multiData.length-1);
			multiData = multiData + "`";
			x = x.next().next();
		}
		multiData = multiData.substring(0,multiData.length-1);
	}
	if(tflen != 0){
		var x = $("#tfquestion li").first();
		for(var i = 0; i < tflen; i++){
			if(typeof(x.find("input:radio:checked").val()) == "undefined"){
				tfData += " "+"`";
			}else{
				tfData += x.find("input:radio:checked").val() +"`";
			}
			x = x.next().next();
		}
		tfData = tfData.substring(0,tfData.length-1);
	}
	if(filllen != 0){
		var x = $("#fillblank li").first();
		for(var i = 0; i < filllen; i++){
			var val =  x.find("input").val();
			if(val == ""){
				val = " ";
			}
			fillData  += val + "`";
			x = x.next().next();
		}
		fillData = fillData.substring(0,fillData.length-1);
	}
	if(essaylen != 0){
		var x = $("#essayquestion li").first();
		for(var i = 0; i < essaylen; i++){
			var ans = x.find("textarea").val();
			if(ans == ""){
				ans = " ";
			}
			essayData += ans + "`";
			x = x.next().next();
		}
		essayData = essayData.substring(0,essayData.length-1);
	}
	$.ajax({
		type : "post",
		url : "../testonline/subTest",
		data:{"singledata":singleData,
			  "multidata":multiData,
			  "tfdata":tfData,
			  "filldata":fillData,
			  "essaydata":essayData},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				if(lowerIe()){
					alert(resMsg);
				}else{
					swal("成功",resMsg,"success");
				}
				setTimeout( function(){
					location.href = "../student/home"
				}, 2 * 1000 );
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

function saveAnswersNotFixed(){
	var singleData = "";
	var multiData = "";
	var tfData = "";
	var fillData = "";
	var essayData = "";
	var singlelen = $("#singlechoose li").length;
	var multilen = $("#multichoose li").length;
	var tflen = $("#tfquestion li").length;
	var filllen = $("#fillblank li").length;
	var essaylen = $("#essayquestion li").length;
	if(singlelen != 0){
		var x = $("#singlechoose li").first();
		for(var i = 0; i < singlelen; i++){
			if(typeof(x.find("input:radio:checked").val()) == "undefined"){
				singleData += " "+"`";
			}else{
				singleData += x.find("input:radio:checked").val() +"`"+ x.find("input:radio:checked").parent().children().first().next().html() +"`";
			}
			x = x.next().next();
		}
	}
	if(multilen != 0){
		var x = $("#multichoose li").first();
		for(var i = 0; i < multilen; i++){
			var str = "";
			x.find("input:checked").each(function(){
				str += $(this).val() +"-";
			});
			if(str === ""){
				str = " ";
			}	
			multiData += str;
			multiData = multiData.substring(0,multiData.length-1);
			multiData = multiData + "`"+ x.find("span").next().html() + "`";
			x = x.next().next();
		}
		multiData = multiData.substring(0,multiData.length-1);
	}
	if(tflen != 0){
		var x = $("#tfquestion li").first();
		for(var i = 0; i < tflen; i++){
			if(typeof(x.find("input:radio:checked").val()) == "undefined"){
				tfData += " "+"`";
			}else{
				tfData += x.find("input:radio:checked").val() +"`"+ x.find("input:radio:checked").parent().children().first().next().html() +"`";
			}
			x = x.next().next();
		}
		tfData = tfData.substring(0,tfData.length-1);
	}
	if(filllen != 0){
		var x = $("#fillblank li").first();
		for(var i = 0; i < filllen; i++){
			var val =  x.find("input").val();
			if(val == ""){
				val = " ";
			}
			fillData  += val + "`" + x.find("span").next().html() + "`";
			x = x.next().next();
		}
		fillData = fillData.substring(0,fillData.length-1);
	}
	if(essaylen != 0){
		var x = $("#essayquestion li").first();
		for(var i = 0; i < essaylen; i++){
			var id = x.find("span").next().html();
			var ans = x.find("textarea").val();
			essayData += ans +"`"+  id +"`";
			x = x.next().next();
		}
		essayData = essayData.substring(0,essayData.length-1);
	}
	$.ajax({
		type : "post",
		url : "../testonline/subTest",
		data:{"singledata":singleData,
			  "multidata":multiData,
			  "tfdata":tfData,
			  "filldata":fillData,
			  "essaydata":essayData},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				if(lowerIe()){
					alert(resMsg);
				}else{
					swal("成功",resMsg,"success");
				}
				setTimeout( function(){
					location.href = "../student/home"
				}, 2 * 1000 );
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