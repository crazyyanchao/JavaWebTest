$().ready(function(){
	$("#addInput").css("display","none");
	$(".addChild").click(function(){
		$("#addInput").css("display","block");
		var parentId = $(this).parent().children(".pid").html();
		$("#parentId").val(parentId);
		if(!parentId||parentId==0){
			$("#addInput caption").html("������������");
		}else{
			$("#addInput caption").html("�����Ӳ�����");
		}
		return false;
	});
	
	$(".addType").click(function(){
		$("#addInput").css("display","block");
		$("#addInput caption").html("������������");
		return false;
	});
	
});