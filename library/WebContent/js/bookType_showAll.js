$().ready(function(){
	$("#addInput").css("display","none");
	$(".addChild").click(function(){
		$("#addInput").css("display","block");
		var parentId = $(this).parent().children(".pid").html();
		$("#parentId").val(parentId);
		if(!parentId||parentId==0){
			$("#addInput caption").html("创建顶层类型");
		}else{
			$("#addInput caption").html("创建子层类型");
		}
		return false;
	});
	
	$(".addType").click(function(){
		$("#addInput").css("display","block");
		$("#addInput caption").html("创建本层类型");
		return false;
	});
	
});