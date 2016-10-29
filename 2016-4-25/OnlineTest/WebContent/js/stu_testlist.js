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
function goToTest(x){
	var testpaperid = $(x).parent().parent().find("td").first().html();
	$.ajax({
		type : "post",
		url : "../tests/checktestinfo",
		data:{"testPaperId":testpaperid},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				location.href = "../student/test";
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

$(document).ready(function(){
	$.ajax({
		type : "post",
		url : "../testpaper/studenttests",
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			data = eval("("+data+")").data;
			if(resCode == "000000"){
				var totalPages = data.totalPages;
				var currPage = data.currPage;
				var len = data.list.length;
				var s = data.list;
				$("#currPage").html(currPage);
				$("#totalPage").html(totalPages);
				for(var i = 0; i < len; i++){
					var id = s[i].id;
					var course = s[i].course;
					var date = s[i].date;
					var starttime = s[i].startTime;
					var endtime = s[i].endTime;
					$("#testslist").append("<tr><td>"+ id +"</td><td>"+ course +"</td><td>"+ date + " "+ starttime +"</td><td>"+ date + " "+ endtime +
							"</td><td><button id=\"gototest\" onclick=\"goToTest(this)\">前往考试</button></td></tr>");
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

function stuTestFirst(){
	$("#testslist").html("");
	$.ajax({
		type : "post",
		url : "../testpaper/studenttests",
		data: {"currPage":"1","pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			data = eval("("+data+")").data;
			if(resCode == "000000"){
				var totalPages = data.totalPages;
				var currPage = data.currPage;
				var len = data.list.length;
				var s = data.list;
				$("#currPage").html(currPage);
				$("#totalPage").html(totalPages);
				for(var i = 0; i < len; i++){
					var id = s[i].id;
					var course = s[i].course;
					var date = s[i].date;
					var starttime = s[i].startTime;
					var endtime = s[i].endTime;
					$("#testslist").append("<tr><td>"+ id +"</td><td>"+ course +"</td><td>"+ date + " "+ starttime +"</td><td>"+ date + " "+ endtime +
							"</td><td><button id=\"gototest\" onclick=\"goToTest(this)\">前往考试</button></td></tr>");
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

function stuTestLast(){
	$("#testslist").html("");
	$.ajax({
		type : "post",
		url : "../testpaper/studenttests",
		data: {"currPage": $("#totalPage").html(),"pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			data = eval("("+data+")").data;
			if(resCode == "000000"){
				var totalPages = data.totalPages;
				var currPage = data.currPage;
				var len = data.list.length;
				var s = data.list;
				$("#currPage").html(currPage);
				$("#totalPage").html(totalPages);
				for(var i = 0; i < len; i++){
					var id = s[i].id;
					var course = s[i].course;
					var date = s[i].date;
					var starttime = s[i].startTime;
					var endtime = s[i].endTime;
					$("#testslist").append("<tr><td>"+ id +"</td><td>"+ course +"</td><td>"+ date + " "+ starttime +"</td><td>"+ date + " "+ endtime +
							"</td><td><button id=\"gototest\" onclick=\"goToTest(this)\">前往考试</button></td></tr>");
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

function stuTestNext(){
	var curr = parseInt($("#currPage").html());
	var total = parseInt($("#totalPage").html());
	if(curr < total){
		curr = curr + 1;
	}
	$("#testslist").html("");
	$.ajax({
		type : "post",
		url : "../testpaper/studenttests",
		data: {"currPage": curr,"pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			data = eval("("+data+")").data;
			if(resCode == "000000"){
				var totalPages = data.totalPages;
				var currPage = data.currPage;
				var len = data.list.length;
				var s = data.list;
				$("#currPage").html(currPage);
				$("#totalPage").html(totalPages);
				for(var i = 0; i < len; i++){
					var id = s[i].id;
					var course = s[i].course;
					var date = s[i].date;
					var starttime = s[i].startTime;
					var endtime = s[i].endTime;
					$("#testslist").append("<tr><td>"+ id +"</td><td>"+ course +"</td><td>"+ date + " "+ starttime +"</td><td>"+ date + " "+ endtime +
							"</td><td><button id=\"gototest\" onclick=\"goToTest(this)\">前往考试</button></td></tr>");
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

function stuTestPrev(){
	var curr = parseInt($("#currPage").html());
	if(curr > 1){
		curr = curr - 1;
	}
	$("#testslist").html("");
	$.ajax({
		type : "post",
		url : "../testpaper/studenttests",
		data: {"currPage": curr,"pageSize":"10"},
		success : function(data) {
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			data = eval("("+data+")").data;
			if(resCode == "000000"){
				var totalPages = data.totalPages;
				var currPage = data.currPage;
				var len = data.list.length;
				var s = data.list;
				$("#currPage").html(currPage);
				$("#totalPage").html(totalPages);
				for(var i = 0; i < len; i++){
					var id = s[i].id;
					var course = s[i].course;
					var date = s[i].date;
					var starttime = s[i].startTime;
					var endtime = s[i].endTime;
					$("#testslist").append("<tr><td>"+ id +"</td><td>"+ course +"</td><td>"+ date + " "+ starttime +"</td><td>"+ date + " "+ endtime +
							"</td><td><button id=\"gototest\" onclick=\"goToTest(this)\">前往考试</button></td></tr>");
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
