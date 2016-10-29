var Path1 = "";
var Path2 = ""
function lowerIe(){
	var UA = navigator.userAgent;
	var ieV = "";
	if(/msie/i.test(UA)){
		ieV = UA.match(/msie (\d+\.\d+)/i)[1];
	}
	if(parseInt(ieV) <= 8){
		return true;
	}
	return false;
}

$(document).ready(function(){
	var userId = getCookie("userId");
	if(userId != "" && userId != null){
		$("#loguserId").val( userId );
	}
});

function login(){
	var remb = "";
	if($("#loguserId").val() == "" || $("#loguserId").val() == null){
		if(lowerIe()){alert("用户编号 / 邮箱不能为空!")}else{swal("对不起","用户编号 / 邮箱不能为空!","error");}
		return false;
	}else if($("#logpassword").val() == "" || $("#logpassword").val() == null){
		if(lowerIe()){alert("密码不能为空!")}else{swal("对不起","密码不能为空!","error");}
		return false;
	}
	if($("#logcheckbox:checked").val() != "rememberMe"){
		remb = "notremember";
	}else{
		remb = $("#logcheckbox:checked").val();
	}
	$.ajax({
		type : "post",
		url : "/OnlineTest/service/login", //如果是ROOT，则 url : "/service/login",local不要
		data:{"loguserId":$("#loguserId").val(),
			  "logpassword":$("#logpassword").val(),
			  "remember":remb},
		success : function(data) {
			deleteCookie("sessionAlive");
			var status = eval("("+data+")").resCode;
			var info = eval("("+data+")").resMsg;
			if(status == "000000"){
				if(info == "教师" ){
					window.location.href = "/OnlineTest/service/teacher/home";
				}else if(info == "学生"){
					window.location.href = "/OnlineTest/service/student/home";
				}else{
					window.location.href = "/OnlineTest/service/admin/home";
				}
			}else if(status == "100001"){
				if(lowerIe()){alert(info)}else{swal("对不起",info,"error");}
			}
		}	
	});
}

$(document).ready(function(){
	if (window.history && window.history.pushState) {
		$(window).on('popstate', function () {
			if(getCookie("sessionAlive") == "1" && getCookie("sessionAlive") != null){
				window.history.pushState('forward', null, '');
				if(lowerIe()){ alert(resMsg) }else{ swal("对不起","会话已经过期,请重新登录!","error"); }
			}else{
				window.history.back();
			}
		});
		if( getCookie("sessionAlive") != null ){
			if(getCookie("sessionAlive") != "0"){
				window.history.pushState('forward', null, '');
			}
		}
	}
});

function getCookie(name){
	var arr = document.cookie.match(new RegExp("(^| )" + name +"=([^;]*)(;|$)") );
	if(arr != null){
		return unescape(arr[2]);
	}else{
		return null;
	}
}

function deleteCookie(name){
	var exp = new Date();
	exp.setTime( exp.getTime() - 1);
	var cval = "0";
	if(cval != null)
		document.cookie = name +"="+cval+";path=/";
}

function changeToRegester(){
	$("#login_panel").html("");
	$("#login-wrapper").css({'margin':'5% auto 0'});
	$("#login_panel").append("<div class=\"form-group\"><label class=\"form_lable\">邮箱</label>"+ 
						"<input type=\"text\" id=\"registerId\" class=\"form-control\" onblur=\"checkRegesterId()\" name=\"registerId\" placeholder=\"email\" aria-describedby=\"basic-addon1\">"+
					    "</div>");
	$("#login_panel").append("<div class=\"form-group\"><label class=\"form_lable\">用户名</label>"+ 
			"<input type=\"text\" id=\"registerName\" class=\"form-control\" onblur=\"checkRegesterName()\"placeholder=\"用户名\" name=\"registerName\" >"+
		    "</div>");
	$("#login_panel").append("<div class=\"form-group\"><div><label class=\"form_lable\">性别</label></div>" +
			"<input type=\"radio\" name=\"gender\" value=\"1001\" style=\"width:15px;height:15px;\"><lable class=\"gender_lable\">男</lable>"+
			"<input type=\"radio\" name=\"gender\" value=\"1002\" style=\"width:15px;height:15px;\"><lable class=\"gender_lable\">女</lable>"+
			"<input type=\"radio\" name=\"gender\" value=\"1003\" style=\"width:15px;height:15px;\"><lable class=\"gender_lable\">保密</lable>" +
			"</div>");
	$("#login_panel").append("<div class=\"form-group\"><label class=\"form_lable\">用户类型</label>"+ 
			"<select id=\"userType\" class=\"form-control\"><option value=\"0\">请选择用户类型</option><option value=\"1\">学生</option><option value=\"2\" onclick=\"show()\">老师</option></select>"+
		    "</div>");
	$("#login_panel").append("<div class='row'>" +
			"<div class='col-sm-6'>" +
			"<label>密码</label>" +
			"<input type=\"password\" id=\"PassWord\" class=\"form-control\" onblur=\"checkRegesterPass()\" name=\"PassWord\"></div>" +
			"<div class='col-sm-6'>" +
			"<label>确认密码</label>" +
			"<input type=\"password\" id=\"passwordconfirm\" class=\"form-control\" onblur=\"confirmPass()\" name=\"passwordconfirm\"></div></div><br/>");
	$("#login_panel").append("<button class=\"btn btn btn-primary btn-block\" onclick=\"register()\">注册</button><div class=\"row-block\"><div class=\"row\"></div></div>");
	$("#login_panel").append("<div><br/><p style=\"text-align: center;\" class=\"form_lable\">已有账号？返回<a onclick=\"changeToLogin()\">登录</a>！</p></div>");
}

function changeToLogin(){
	$("#login_panel").html("");
	$("#login-wrapper").css({'margin':'15% auto 0'});
	$("#login_panel").append("<div class=\"form-group\">" +
			"<div class='form-group'>"+
			"<div class='input-group'>"+
			"<span class='input-group-addon'><i class='fa fa-user'></i></span>"+
			"<input type='text' id='loguserId' class='form-control' name='loguserId' placeholder='用户编号 /  邮箱' aria-describedby='basic-addon1'></div></div>"+
			"<div class='form-group'>"+
			"<div class='input-group'>"+
			"<span class='input-group-addon'><i class='fa fa-lock'></i></span>"+
			"<input type='password' id='logpassword' class='form-control' name='logpassword' placeholder='密码' aria-describedby='basic-addon1'></div></div>"+
			"</div><div class=\"checkbox pull-left\" id=\"logpage-checkbox\">"+
			"<label><input type=\"checkbox\" id=\"logcheckbox\" value=\"rememberMe\"> 记住我 </label></div>"+
			"<button class=\"btn btn btn-primary pull-right\" onclick=\"login()\">登录</button>"+
			"<div class=\"row-block\"><div class=\"row\"></div></div>"+
			"<div><p style=\"text-align: center;\" class=\"form_lable\">还没有账号？立即<a onclick=\"changeToRegester()\">注册</a>！</p></div>");
}
function IsEmail(email){
	var reEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	return(reEmail.test(email));
}
function checkRegesterId(){
	if($("#registerId").val() == "" || $("#registerId").val() == null){
		if(lowerIe()){alert("邮箱为空!")}else{swal("对不起","邮箱为空!","error");}
		return false;
	}else{
		if(  !IsEmail( $("#registerId").val() )  ){
			swal("警告","请输入正确的邮箱","warning");
		}
	}
}

function checkRegesterName(){
	if($("#registerName").val() == "" || $("#registerName").val() == null){
		if(lowerIe()){alert("用户名为空!")}else{swal("对不起","用户名为空!","error");}
		return false;
	}
}

function checkRegesterPass(){
	if($("#PassWord").val() == "" || $("#PassWord").val() == null){
		if(lowerIe()){alert("密码为空!")}else{swal("对不起","密码为空!","error");}
		return false;
	}
}

function confirmPass(){
	if($("#PassWord").val() != $("#passwordconfirm").val()){
		if(lowerIe()){alert("两次输入密码不一致!")}else{swal("对不起","两次输入密码不一致!","error");}
		return false;
	}
}

function register(){
	var gender = $("input[name='gender']:checked").val();
	var belong = "";
	if($("#registerId").val() == "" || $("#registerId").val() == null){
		if(lowerIe()){alert("邮箱为空!")}else{swal("对不起","邮箱为空!","error");}
		return false;
	}else if($("#registerName").val() == "" || $("#registerName").val() == null){
		if(lowerIe()){alert("用户名为空!")}else{swal("对不起","用户名为空!","error");}
		return false;
	}else if(gender == "" || gender == null){
		if(lowerIe()){alert("性别为空!")}else{swal("对不起","性别为空!","error");}
		return false;
	}else if($("#userType").val() == "0"){
		if(lowerIe()){alert("用户类型为空!")}else{swal("对不起","用户类型为空!","error");}
		return false;
	}else if($("#PassWord").val() == "" || $("#PassWord").val() == null){
		if(lowerIe()){alert("密码为空!")}else{swal("对不起","密码为空!","error");}
		return false;
	}else if($("#PassWord").val() != $("#passwordconfirm").val()){
		if(lowerIe()){alert("两次输入密码不一致!")}else{swal("对不起","两次输入密码不一致!","error");}
		return false;
	}
	if($("#userType").val() == "2"){
		if(Path1 == "" || Path2 == ""){
			swal("警告","您没有上传您的证件照片","warning");
			return false;
		}
	}
	$.ajax({
		type : "post",
		url : "/OnlineTest/service/register",
		data: {"userId": $("#registerId").val(),
			   "userName" :$("#registerName").val(),
			   "userType": $("#userType").val(),
			   "password": $("#PassWord").val(),
			   "gender": gender,
			   "photo1": Path1,
			   "photo2": Path2},
		success : function(data) {
			alert(data)
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				var userId = eval("("+data+")").data.userId;
				swal("恭喜您","您的用户Id为"+userId,"success");
			}else{
				swal("错误",resMsg,"error");
			}
		}
	});
}

$(document).on("change","#userType",function(e){
	var x = $("#userType").val();
	if(x == "2"){
		$("#login_panel").children().last().remove();
		$("#login_panel").children().last().remove();
		$("#login_panel").children().last().remove();
		$("#login_panel").children().last().remove();
		$("#login_panel").children().last().remove();
		$("#login_panel").append("<div class=\"form-group\" id='teacherphoto'><label class=\"form_lable\">教师证件照片</label><br>"+
				"<a href=\"javascript:;\" id=\"Certificate\" class=\"file\">选择文件" +
				"<input type=\"file\" id=\"uploadfile1\" name=\"uploadfile1\"></a>" +
				"<span style='font-size: 15px;vertical-align: super;margin-left:10px'></span><br/>"+
				"<a href=\"javascript:;\" id=\"PersonCertificate\" class=\"file\">选择文件" +
				"<input type=\"file\" id=\"uploadfile2\" name=\"uploadfile2\"></a>" +
				"<span style='font-size: 15px;vertical-align: super; margin-left:10px'></span><br/>"+
				"<button type=\"button\" class=\"bk-margin-5 btn btn-success\" id='uploadbutton' onclick='uploadfile()'>点击上传</button>"+
				"<p>请选择教师证件照片和本人持证件的照片,只能是jpg或者png格式</p>" +
			    "</div>");
		$("#login_panel").append("<div class='row'>" +
				"<div class='col-sm-6'>" +
				"<label>密码</label>" +
				"<input type=\"password\" id=\"PassWord\" class=\"form-control\" onblur=\"checkRegesterPass()\" name=\"PassWord\"></div>" +
				"<div class='col-sm-6'>" +
				"<label>确认密码</label>" +
				"<input type=\"password\" id=\"passwordconfirm\" class=\"form-control\" onblur=\"confirmPass()\" name=\"passwordconfirm\"></div></div><br/>");
		$("#login_panel").append("<button class=\"btn btn btn-primary btn-block\" onclick=\"register()\">注册</button><div class=\"row-block\"><div class=\"row\"></div></div>");
		$("#login_panel").append("<div><br/><p style=\"text-align: center;\" class=\"form_lable\">已有账号？返回<a onclick=\"changeToLogin()\">登录</a>！</p></div>");
	}else{
		var x = $("#Certificate").val();
		if(typeof(x) != "undefined"){
			$("#login_panel").children().last().remove();
			$("#login_panel").children().last().remove();
			$("#login_panel").children().last().remove();
			$("#login_panel").children().last().remove();
			$("#login_panel").children().last().remove();
			$("#login_panel").children().last().remove();
			$("#login_panel").append("<div class='row'>" +
					"<div class='col-sm-6'>" +
					"<label>密码</label>" +
					"<input type=\"password\" id=\"PassWord\" class=\"form-control\" onblur=\"checkRegesterPass()\" name=\"PassWord\"></div>" +
					"<div class='col-sm-6'>" +
					"<label>确认密码</label>" +
					"<input type=\"password\" id=\"passwordconfirm\" class=\"form-control\" onblur=\"confirmPass()\" name=\"passwordconfirm\"></div></div><br/>");
			$("#login_panel").append("<button class=\"btn btn btn-primary btn-block\" onclick=\"register()\">注册</button><div class=\"row-block\"><div class=\"row\"></div></div>");
			$("#login_panel").append("<div><br/><p style=\"text-align: center;\" class=\"form_lable\">已有账号？返回<a onclick=\"changeToLogin()\">登录</a>！</p></div>");
		}
	}
	e.stopPropagation();
});


$(document).on("change","#Certificate",function(e){
	var x = $(this).next();
	var filePath=$(this).find("input").val();
	if(filePath.indexOf("jpg")!=-1 || filePath.indexOf("png")!=-1){
		var arr=filePath.split('\\');
	    var fileName=arr[arr.length-1];
	    x.html("");
	    x.html(fileName);
    }else{
    	swal("错误","上传的文件不是照片","error");
    	return false;
    }
});
$(document).on("change","#PersonCertificate",function(e){
	var x = $(this).next();
	var filePath=$(this).find("input").val();
	if(filePath.indexOf("jpg")!=-1 || filePath.indexOf("png")!=-1){
		var arr=filePath.split('\\');
	    var fileName=arr[arr.length-1];
	    x.html("");
	    x.html(fileName)
    }else{
    	swal("错误","上传的文件不是照片","error");
    	return false;
    }
});
function uploadfile(){
	Path1 = "";
	Path2 = "";
	$.ajaxFileUpload({
		url : "/OnlineTest/service/files/uploadfile",
		secureuri:false,
		fileElementId: ['uploadfile1','uploadfile2'],
		cache: false,
		dataType:'text',
		success : function(data) {
            var i = data.indexOf("{");
            var j = data.lastIndexOf("}");
            data = data.substring(i,j+1); 
			var resCode = eval("("+data+")").resCode;
			var resMsg = eval("("+data+")").resMsg;
			if(resCode == "000000"){
				Path1 = eval("("+data+")").data.path1;
				Path2 = eval("("+data+")").data.path2;
				if( Path1 != ""){
					$("#Certificate").css("cursor", "default");
					$("#uploadfile1").attr("disabled","true");
					$("#Certificate").next().append("<i class='fa fa-check' style='color: green;margin-left: 10px;'></i>")
				}
				if( Path2 != ""){
					$("#PersonCertificate").css("cursor", "default");
					$("#uploadfile2").attr("disabled","true");
					$("#PersonCertificate").next().append("<i class='fa fa-check' style='color: green;margin-left: 10px;'></i>")
				}
				$("#userType").attr("disabled","true");
				$("#uploadbutton").attr("disabled","true");
				swal("成功",resMsg,"success");
			}else{
				swal("对不起",resMsg,"error");
			}
		},error:function(data){  
		     alert("发生了错误");  
		}
	});
}