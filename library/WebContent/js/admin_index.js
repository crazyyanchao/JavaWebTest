$().ready(function() {
	$("#left ul li p").toggle(function() {
		$(this).parent().children("ul").show();
	}, function() {
		$(this).parent().children("ul").hide();
	});
	$("select").change(function(){
		alert($(":selected").html());
	});
});

