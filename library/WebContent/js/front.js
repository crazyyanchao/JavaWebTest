// JavaScript Document
$().ready(function(){
	$("#left ul li p").toggle(
  		function () {
   			$(this).parent().children("ul").show();
  		},
  		function () {
    		$(this).parent().children("ul").hide();
  		}
	);
	
	
	$("#search").click(
  		function () {
			//$().post("","",function(data){});
			$("#content").html("").append("<div> </div>")
			.append("<div class=\"filename\"><a>java编程思想</a></div><br />")
			.append("<div><a href=\"\">java编程思想</a></div><br />")
			.append("<div><a href=\"\">java编程思想</a></div><br />")
			.append("<div><a href=\"\">java编程思想</a></div><br />");
  		}
	);
	
	$("#content .filename").live("click",function(){
		$("#content").html("").append("<p>aaaaaaaaaaaaaaaaaaaaaaaaaaaaa</p> ").css("text-align","left").css("margin-left","10px");
		});
	
});