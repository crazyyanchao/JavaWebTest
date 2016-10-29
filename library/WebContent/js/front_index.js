var column;
$().ready(function() {
	initNewAnnouncement();
	initNewComments();
	initBorrowRank();
	initNewBooks();
	$("#left ul li p").toggle(function() {
		$(this).parent().children("ul").show();
	}, function() {
		$(this).parent().children("ul").hide();
	});
	$("select").change(function(){
		column=$(":selected").val();
	});
	$("#search input:submit").click(function(){
		var submit=$(this);
		$("#search input[name=query.coumn]").val(column);
		submit.submit();
		return ;
	});
});

function initNewAnnouncement() {
	$.getJSON("showAllJson_Announcement?query.key=new", function(data) {
		$("marquee").html(data.pageInfo.datas[0].content).css("margin-left","25px");
	});
}

function initNewComments() {
	$.getJSON("showAllJson_Comment?query.key=new", function(data) {
		$.each(data.pageInfo.datas, function(i, comment) {
			$('<li><a href="">' + comment.content + '</a></li>').appendTo("#new_comments ul");
		});
	});
}

function initBorrowRank() {
	$.getJSON("showAllJson_Book?query.key=rank", function(data) {
		$.each(data.pageInfo.datas, function(i, book) {
			$('<li width="300"><div style="float:right">' + book.borrowedCount + '</div><div><a href="">' + book.bookName + '</a></div></li>').appendTo($("#borrow_rank ul"));
		});
	});
}

function initNewBooks() {
	$.getJSON("showAllJson_Book?query.key=new", function(data) {
		$.each(data.pageInfo.datas, function(i, book) {
			$('<li><a href="">' + book.bookName + '</a></li>').appendTo("#new_books ul");
		});
	});
}

