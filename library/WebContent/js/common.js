$().ready(function() {
	$("tr :odd").each(function(i) {
		$(this).css("backgroundColor","gray");
	});
	$("tr :even").each(function(i) {
		$(this).css("backgroundColor","#9FB6CD");
	});
});