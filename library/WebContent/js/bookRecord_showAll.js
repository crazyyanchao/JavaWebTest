var bookId;

$().ready(function() {
	function listComments(data) {
		$.each(data.pageInfo.datas, function(i, comment) {
			if(i==0){
				$("<div>" + comment.book.bookName + "�����ۣ�</div>").appendTo("#comments");
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
		// ���ظ�������ҳ��
		// �ύ����
		// ������³ɹ������ر���飬���鼮�б��и������ݡ�
		return false;
	});

	$(".delete").click(function() {
		// ɾ����ǰ����
		// ����鼮�б�����
		// ����鼮�б�����
		// ѭ����ʾ�鼮�б�����
		return false;
	});

});