var groupId;
var group;
var urls;
$().ready(function() {
	urls=$(".urls");
	$("#permisionUrls").css("display","none");
	$(".urls").attr("checked",false);
	$(".mangage_url").click(function(){
		group=$(this).parent().parent("");
		$(".urls").attr("checked",false);
		groupId = $(group).children(".groupId").html();
		$.getJSON("showJson_PermissionGroup?permissionGroup.permissionGroupId="+groupId,function(data){
			var myurls=data.permissionGroup.permissionUrls;
			$.each(urls, function(i, url) {
				$.each(myurls, function(j, myurl) {
					if($(url).val()==myurl.permissionUrlId){
						$(url).attr("checked",true);
					}
				});
			});
		});
		$("#permisionUrls").css("display","block");
		return false;
	});
	
	$("#update_groupurls").click(function(){
		var checkedurls=$(".urls:checked");
		var urls="";
		$.each(checkedurls, function(i, url) {
			if(i!=0){
				urls+=",";
			}
			urls+=$(url).val();
		});
		$.getJSON("updateJson_PermissionGroup?permissionGroup.permissionGroupId="+groupId+"&urls="+urls,function(data){
			var select=$(group).children("td").children("select");
			$(select).html("");
			$.each(data.permissionGroup.permissionUrls, function(i, url) {
				$("<option>"+url.description+ "</option>").appendTo($(select));
			});
			$("#permisionUrls").css("display","none");
			$(".urls").css("checked","");
		});
		return false;
	});
});