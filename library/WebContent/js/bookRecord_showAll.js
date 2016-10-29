var bookId;

$().ready(function() {
	function listComments(data) {
		$.each(data.pageInfo.datas, function(i, comment) {
			if(i==0){
				$("<div>" + comment.book.bookName + "的评价：</div>").appendTo("#comments");
			}
			$('<div><a href="">' + comment.content + '</a></div>').appendTo("#comments");
		});
	}
	$(".showAllBookRecords").click(function() {
		$("#comments").html("").css("display","block");
		bookId = $(this).parent().parent("").children(".bookId").html();
		$.getJSON("showAllJson_Comment?query=bookId&value="+bookId,function(data){
			listComments(data);
			});
		return false;
	});

	$(".addComment").click(function() {
		$("#comments").html("");
		$("#commentInput").css("display","block");
		$("#commentInput textarea").val("");
		//$("#comments").load("admin/addInput_Comment");
		bookId = $(this).parent().parent("").children(".bookId").html();
		return false;
	});

	$("#submitComment").click(function() {
		$("#commentInput").css("display","none");
		var content=$("#commentInput textarea").val();
		$.getJSON("addJson_Comment?comment.book.bookId="+bookId+"&comment.content="+content,function(data){
			listComments(data);
		});
	});

	$("#cancelComment").click(function(){alert("aaa");
		$("#commentInput").css("display","none");
		return false;
	});
	$(".update").click(function() {
		$("#comments").load("admin/addInput_Comment");
		// 加载更新输入页面
		// 提交更新
		// 如果更新成功，隐藏本板块，在书籍列表中更新数据。
		return false;
	});

	$(".delete").click(function() {
		// 删除当前数据
		// 清空书籍列表数据
		// 获得书籍列表数据
		// 循环显示书籍列表数据
		return false;
	});

});