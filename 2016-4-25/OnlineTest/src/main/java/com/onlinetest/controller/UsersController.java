package com.onlinetest.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onlinetest.common.QuickPager;
import com.onlinetest.common.Response;
import com.onlinetest.model.User;
import com.onlinetest.util.StringUtils;
import com.onlinetest.util.Utils;
import com.onlinetest.service.UserService;

/**
 * @author 丁 鹏
 *
 */

@Controller("UsersController")
public class UsersController {
	
	@Resource( name = "UserService")
	private UserService userService;
	
	/**
	 *  登录
	 * @param request
	 * @param response
	 * @param userName
	 * @param password
	 * @param remember
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/login",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String login(HttpServletRequest request,
						HttpServletResponse response,
						@RequestParam(value = "loguserId") String userId,
						@RequestParam(value = "logpassword") String password,
						@RequestParam( value = "remember") String remember) throws IOException, ServletException{
		if(userId.equals(null)||userId.equals("")||password.equals(null)||password.equals("")){
			return Response.failuer("用户编号或者密码为空");
		}
		User user = null;
		password = Utils.md5(password);
		if(userId.indexOf("@") != -1){
			user = this.userService.loginEmail(userId);
		}else{
			user = this.userService.login(userId);
		}
		if(user == null){
			return Response.failuer("用户不存在");
		}else if(user.getUserId() == null && user.getPassed() == null ){
			return Response.failuer("您的审核尚未通过!");
		}else if( !user.getPassword().equals(password) ){
			return Response.failuer("密码错误");
		}
		int flag = 0;
//		int remflag = 0;
//		String info = Utils.md5(userId+password+userId);
		if(request.getCookies()!=null){
			Cookie[] cookies = request.getCookies();
			for(int i = 0 ; i < cookies.length; i++){
				/*if(cookies[i].getValue().equals(info)){
					remflag = 1;
					break;
				}*/
				if(cookies[i].getValue().equals(userId)){
					flag = 1;
					break;
				}
			}
		}
		/*if(remflag != 1 && remember.equals("rememberMe")){
			//cookie,用于免登陆
			Cookie rememberuserCookie = new Cookie("userlog", info);
			rememberuserCookie.setMaxAge(3600*24*7); //设置生命周期为1周
			rememberuserCookie.setPath("/OnlineTest/");
			response.addCookie(rememberuserCookie);
		}*/
		if(flag != 1 && remember.equals("rememberMe") ){//cookie,记住用户名
			Cookie userNameCookie = new Cookie("userId", userId);
			userNameCookie.setMaxAge(3600*24*7); //设置生命周期为1周
			userNameCookie.setPath("/");
			response.addCookie(userNameCookie);
		}
		HttpSession session = request.getSession();
		session.setAttribute("userName", user.getName());
		session.setAttribute("userId", user.getUserId());
		session.setAttribute("role", user.getRole());
		//response.sendRedirect("/WEB-INF/pages/testMain.jsp");
		return Response.success(user.getRole());
	}
	
	/**
	 * 注册
	 */
	@RequestMapping( value = "/register",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String register(@RequestParam(value = "userId") String emial,
						   @RequestParam(value = "userName") String userName,
						   @RequestParam(value = "userType") String userType,
						   @RequestParam(value = "password") String password,
						   @RequestParam(value = "gender") String gender,
						   @RequestParam(value = "photo1",required=false)String path1,
						   @RequestParam(value = "photo2",required=false)String path2){
		if(StringUtils.isEmpty(emial)){
			return Response.failuer("邮箱为空!");
		}
		if(StringUtils.isEmpty(userName)){
			return Response.failuer("用户名为空!");
		}
		if(StringUtils.isEmpty(userType)){
			return Response.failuer("用户类型为空!");
		}
		if(StringUtils.isEmpty(password)){
			return Response.failuer("密码为空!");
		}
		if(StringUtils.isEmpty(gender)){
			return Response.failuer("性别为空!");
		}
		password = Utils.md5(password);
		String resulet = this.userService.register(emial, userName, gender, userType, password,path1,path2);
		Response resp = new Response();
		if(resulet.equals("exist")){
			return Response.failuer("用户已存在!");
		}else if(resulet.equals("failed")){
			return Response.failuer("注册失败!");
		}else{
			resp.addString("userId",resulet);
		}
		return resp.toString();
	}
	
	/**
	 * 注销
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout",produces = "application/json;charset=UTF-8")
	public void unlogin(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.getSession().invalidate();
		Cookie sessionIsAlive = new Cookie("sessionAlive", "1");
		//cookie不设置生周期，则会话结束失效
		sessionIsAlive.setPath("/");
		response.addCookie(sessionIsAlive);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @param initpass
	 * @param newpass
	 * @param userId
	 * @return
	 */
	@RequestMapping( value = "/changepassword",produces = "application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String changepassword(@RequestParam(value = "initalpassword")String initpass,
								 @RequestParam(value = "newpassword")String newpass,
								 HttpServletRequest request){
		String userId = request.getSession().getAttribute("userId").toString();
		if(StringUtils.isEmpty(initpass)){
			return Response.failuer("原始密码为空!");
		}
		if(StringUtils.isEmpty(newpass)){
			return Response.failuer("新密码为空!");
		}
		initpass = Utils.md5(initpass);
		newpass = Utils.md5(newpass);
		int x = this.userService.changepassword(initpass, newpass, userId);
		if(x == 1){
			return Response.success("密码修改成功!");
		}else if(x == -1){
			return Response.failuer("原始密码不正确!");
		}
		return Response.failuer("修改密码失败!");
	}
	
	/**
	 * 获取学生信息
	 * @param userId
	 * @return
	 */
	@RequestMapping( value = "/getstudentinfo",produces = "application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String getStudentInfo(HttpServletRequest request){
		String userId = request.getSession().getAttribute("userId").toString();
		User user = this.userService.getStudentInfo(userId);
		if(user == null){
			return Response.failuer("用户不存在或者信息不完整!");
		}
		Response respon = new Response();
		respon.addObject("user", user);
		return respon.toString();
	}
	
	/**
	 * 修改用户信息
	 * @param userId
	 * @param phone
	 * @param email
	 * @param qq
	 * @return
	 */
	@RequestMapping(value = "/modifypersoninfo",produces = "application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String modifyPersonInfo(HttpServletRequest request,
			              		   @RequestParam(value = "phone")String phone,
			              		   @RequestParam(value = "email")String email,
			              		   @RequestParam(value = "QQ")String qq){
		String userId = request.getSession().getAttribute("userId").toString();
		int x = this.userService.modifyPersonInfo(userId, phone, email, qq);
		if(x == 1){
			return Response.success("修改成功!");
		}
		return Response.failuer("修改失败!");
	}
	
	/**
	 * 获取老师信息
	 * @param userId
	 * @return
	 */
	@RequestMapping( value = "/getteacherinfo",produces = "application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String getTeacherInfo(HttpServletRequest request){
		String userId = request.getSession().getAttribute("userId").toString();
		User user = this.userService.getTeacherInfo(userId);
		if(user == null){
			return Response.failuer("用户不存在或者信息不完整!");
		}
		Response respon = new Response();
		respon.addObject("user", user);
		return respon.toString(); 
	}
	
	/**
	 * 获取管理员信息
	 */
	@RequestMapping( value = "/getadmininfo",produces = "application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String getAdminInfo(HttpServletRequest request){
		String userId = request.getSession().getAttribute("userId").toString();
		if(this.userService.checkAdmin(userId) == false){
			return Response.failuer("您的权限不足");
		}
		User user = this.userService.getAdminInfo(userId);
		if(user == null){
			return Response.failuer("用户不存在或者信息不完整!");
		}
		Response respon = new Response();
		respon.addObject("user", user);
		return respon.toString(); 
	}
	
	/**
	 * 教师列表
	 */
	@RequestMapping( value = "/user/teacherlist",produces = "application/json;charset=UTF-8",method=RequestMethod.POST )
	@ResponseBody
	public String getTeacherList(HttpServletRequest request,
						@RequestParam(value = "currPage",required=false)String currPage,
						@RequestParam(value = "pageSize",required=false)String pageSize){
		String userId = request.getSession().getAttribute("userId").toString();
		QuickPager<Map<String,Object>> quickPager = new QuickPager<Map<String,Object>>(currPage,pageSize);
		this.userService.getTeacherList(userId, quickPager);
		if(quickPager.getData() == null){
			return Response.failuer("您的权限不够!");
		}
		if(quickPager.getTotalRows() == 0){
			return Response.failuer("列表信息为空!");
		}
		Response resp = new Response();
		resp.addQuickPager(quickPager);
		return resp.toString();
	}
	
	/**
	 * 审核 教师信息
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/user/modifyteacher",produces = "application/json;charset=UTF-8",method=RequestMethod.POST )
	@ResponseBody
	public String modifyTeacher(HttpServletRequest request,@RequestParam(value="id")String id){
		String userId = request.getSession().getAttribute("userId").toString();
		if(StringUtils.isEmpty(id)){
			return Response.failuer("教师职工号为空!");
		}
		if(this.userService.checkAdmin(userId) == false){
			return Response.failuer("您的权限不足");
		}
		int x = this.userService.modifyTeacher(id);
		if(x == 0){
			return Response.failuer("信息修改失败!");
		}
		return Response.success("信息修改成功!");
	}
	/**
	 * 添加用户
	 * @param data
	 * @return
	 */
	@RequestMapping( value = "/user/addperson",produces = "application/json;charset=UTF-8",method=RequestMethod.POST )
	@ResponseBody
	public String addPerson(HttpServletRequest request,@RequestParam(value = "data")String data){
		String userId = request.getSession().getAttribute("userId").toString();
		if(StringUtils.isEmpty(data)){
			return Response.failuer("添加数据为空!");
		}
		if(this.userService.checkAdmin(userId) == false){
			return Response.failuer("您的权限不足");
		}
		int x = this.userService.addPerson(data);
		if(x == -1){
			return Response.failuer("用户已存在!");
		}
		if(x == 0){
			return Response.failuer("添加失败!");
		}
		return Response.success("添加成功!");
	}
	
	/**
	 * 删除用户
	 * @param data
	 * @return
	 */
	@RequestMapping( value = "/user/deleteperson",produces = "application/json;charset=UTF-8",method=RequestMethod.POST )
	@ResponseBody
	public String deletePerson(HttpServletRequest request,@RequestParam(value = "userId")String userId){
		String id = request.getSession().getAttribute("userId").toString();
		if(this.userService.checkAdmin(id) == false){
			return Response.failuer("您的权限不足");
		}
		if(StringUtils.isEmpty(userId)){
			return Response.failuer("没有获取到要删除的用户信息!");
		}
		int x = this.userService.deletePerson(userId);
		if(x != 1){
			return Response.failuer("删除失败!");
		}
		return Response.success("删除成功!");
	}
	
	/**
	 * 学生列表
	 */
	@RequestMapping( value = "/user/studentlist",produces = "application/json;charset=UTF-8",method=RequestMethod.POST )
	@ResponseBody
	public String getStudentList(HttpServletRequest request,
						@RequestParam(value = "currPage",required=false)String currPage,
						@RequestParam(value = "pageSize",required=false)String pageSize){
		String userId = request.getSession().getAttribute("userId").toString();
		QuickPager<Map<String,Object>> quickPager = new QuickPager<Map<String,Object>>(currPage, pageSize);
		this.userService.getStudentList(userId, quickPager);
		if(quickPager.getData() == null){
			return Response.failuer("您的权限不足!");
		}
		if(quickPager.getTotalRows() == 0){
			return Response.failuer("列表信息为空!");
		}
		Response resp = new Response();
		resp.addQuickPager(quickPager);
		return resp.toString();
	}
	
	/**
	 * 添加学生
	 * @param data
	 * @return
	 */
	@RequestMapping( value = "/admin/addstudent",produces = "application/json;charset=UTF-8",method=RequestMethod.POST )
	@ResponseBody
	public String addStudnet(@RequestParam(value = "data")String data){
		if(StringUtils.isEmpty(data)){
			return Response.failuer("添加数据为空!");
		}
		int x = this.userService.addStudent(data);
		if(x == -1){
			return Response.failuer("用户已存在!");
		}
		if(x == 0){
			return Response.failuer("添加失败!");
		}
		return Response.success("添加成功!");
	}
	
	/**
	 * 管理员列表
	 */
	@RequestMapping( value = "/admin/lists",produces = "application/json;charset=UTF-8",method=RequestMethod.POST )
	@ResponseBody
	public String getAdminList(HttpServletRequest request){
		String userId = request.getSession().getAttribute("userId").toString();
		List<Map<String,Object>> list = this.userService.getAdminList(userId);
		if(list == null){
			return Response.failuer("您的权限不足!");
		}
		if(list.isEmpty()){
			return Response.failuer("列表信息为空!");
		}
		Response resp = new Response();
		resp.addList("list", list);
		return resp.toString();
	}
	/**
	 * 添加管理员
	 * @param data
	 * @return
	 */
	@RequestMapping( value = "/admin/addadmin",produces = "application/json;charset=UTF-8",method=RequestMethod.POST )
	@ResponseBody
	public String addAdmin(@RequestParam(value = "data")String data){
		if(StringUtils.isEmpty(data)){
			return Response.failuer("添加数据为空!");
		}
		int x = this.userService.addAdmin(data);
		if(x == -1){
			return Response.failuer("用户已存在!");
		}
		if(x == 0){
			return Response.failuer("添加失败!");
		}
		return Response.success("添加成功!");
	}
	
	@RequestMapping( value = "/admin/initpassword",produces = "application/json;charset=UTF-8",method=RequestMethod.POST )
	@ResponseBody
	public String checkInitPassword(HttpServletRequest request) throws IOException{
		String userId = request.getSession().getAttribute("userId").toString();
		User user = this.userService.login(userId);
		String intipassword = Utils.md5("admin123");
		if(user.getPassword().equals(intipassword)){
			return Response.failuer("原始密码");
		}
		return Response.success("密码不是原始密码");
	}
	
	@RequestMapping( value = "/admin/modifystucla",produces = "application/json;charset=UTF-8",method=RequestMethod.POST )
	@ResponseBody
	public String modifyStudent(HttpServletRequest request,
								@RequestParam(value = "class")String cla,
								@RequestParam(value = "userId")String userId){
		if(StringUtils.isEmpty(cla)){
			return Response.failuer("班级信息为空!");
		}
		if(StringUtils.isEmpty(userId)){
			return Response.failuer("学生学号为空!");
		}
		int x = this.userService.modifyStudent(userId, cla);
		if(x != 1){
			return Response.failuer("添加失败!");
		}
		return Response.success("添加成功!");
	}
}
