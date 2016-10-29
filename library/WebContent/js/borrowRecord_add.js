$().ready(function() {
	$("#bookInfo").css("display","none");
	$(".warm").css("display","none");
	$("#search_book").click(function(){
		var bookId=$("#bookId").val();
		$.getJSON("showJson_Book?book.bookId="+bookId,function(data){
			if(!data.book){
				$(".warm").css("display","block");
				return false;
			}
			$("#bookInfo").css("display","block");
			var inputs=$("#bookInfo :input");
			inputs[0].value=data.book.bookId;
			inputs[1].value=data.book.bookName;
			inputs[2].value=data.book.press;
			inputs[3].value=data.book.publishTime;
			inputs[4].value=data.book.count;
			inputs[5].value=data.book.importTime;
			inputs[6].value=data.book.importTime;
			inputs[7].value=data.book.importTime;
		});
		return false;
	});
});