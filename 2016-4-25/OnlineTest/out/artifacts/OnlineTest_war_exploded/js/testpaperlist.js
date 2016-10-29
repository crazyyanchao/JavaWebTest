function load(data){
	var totalPages = data.totalPages;
	var currPage = data.currPage;
	var s = data.list;
	var len = s.length;
	$("#currPage").html(currPage);
	$("#totalPage").html(totalPages);
	for(var i = 0; i < parseInt(len); i++){
		var id = s[i].id;
		var course = s[i].course;
		var date = s[i].date;
		var startTime = s[i].startTime;
		var endTime = s[i].endTime;
		if(course.length > 14){
			var xx = course.substr(0,14);
			course = "<td>"+xx+"<a class=\"table_block\">..more..</a><div style=\"display: none;\" class=\"table_block_hide\">"+
			"<table class=\"table table-striped table-bordered bootstrap-datatable datatable\">"+
			"<tr><td>"+course+"</td></tr></table></div>"+"</td>";
		}else{
			course = "<td>"+course+"</td>";
		}
		$("#testpaperlist").append("<tr><td>"+ id + "</td>" + course + "<td>"+ date +"</td>"+
				"<td>" + startTime + "</td>"+ "<td>" + endTime + "</td>" + 
				"<td><a class=\"btn btn-danger table_input_delete\" href=\"javascript:void();\"><i class=\"fa fa-trash-o\"></i></a></td>"+   //删除
				"</tr>");
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
		url : "../testpaper/testpaperlist",
		data:{"currPage":"1",
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
});

/* 缩写显示  事件绑定 */
$(document).on("click",".table_block",function(e){
	var xx = $(this).next().html();
	$('#table_small').html("");
	$('#table_small').css({'position':'absolute','top':e.pageY,'left':e.pageX,'z-index':'10'});
	$('#table_small').show();
	$("#table_small").append(xx);
	e.stopPropagation();
});

function paperlistReload(){
	var curr = $("#currPage").html();
	$("#testpaperlist").html("");
	$.ajax({
		type : "post",
		url : "../testpaper/testpaperlist",
		data:{"currPage":curr,
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

function paperlistNext(){
	var curr = parseInt($("#currPage").html());
	var total = parseInt($("#totalPage").html());
	if(curr < total){
		curr = curr + 1;
	}
	$("#testpaperlist").html("");
	$.ajax({
		type : "post",
		url : "../testpaper/testpaperlist",
		data:{"currPage":curr,
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

function paperlistPrev(){
	var curr = parseInt($("#currPage").html());
	if(curr > 1){
		curr = curr - 1;
	}
	$("#testpaperlist").html("");
	$.ajax({
		type : "post",
		url : "../testpaper/testpaperlist",
		data:{"currPage":curr,
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

function paperlistFirst(){
	$("#testpaperlist").html("");
	$.ajax({
		type : "post",
		url : "../testpaper/testpaperlist",
		data:{"currPage":"1",
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

function paperlistLast(){
	$("#testpaperlist").html("");
	$.ajax({
		type : "post",
		url : "../testpaper/testpaperlist",
		data:{"currPage":$("#totalPage").html(),
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
					url : "../testpaper/deletetestpaper",
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