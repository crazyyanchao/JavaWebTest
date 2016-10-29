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
 * @author �� ��
 *
 */

@Controller("UsersController")
public class UsersController {
	
	@Resource( name = "UserService")
	private UserService userService;
	
	/**
	 *  ��¼
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
			return Response.failuer("�û���Ż�������Ϊ��");
		}
		User user = null;
		password = Utils.md5(password);
		if(userId.indexOf("@") != -1){
			user = this.userService.loginEmail(userId);
		}else{
			user = this.userService.login(userId);
		}
		if(user == null){
			return Response.failuer("�û�������");
		}else if(user.getUserId() == null && user.getPassed() == null ){
			return Response.failuer("���������δͨ��!");
		}else if( !user.getPassword().equals(password) ){
			return Response.failuer("�������");
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
			//cookie,�������½
			Cookie rememberuserCookie = new Cookie("userlog", info);
			rememberuserCookie.setMaxAge(3600*24*7); //������������Ϊ1��
			rememberuserCookie.setPath("/OnlineTest/");
			response.addCookie(rememberuserCookie);
		}*/
		if(flag != 1 && remember.equals("rememberMe") ){//cookie,��ס�û���
			Cookie userNameCookie = new Cookie("userId", userId);
			userNameCookie.setMaxAge(3600*24*7); //������������Ϊ1��
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
	 * ע��
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
			return Response.failuer("����Ϊ��!");
		}
		if(StringUtils.isEmpty(userName)){
			return Response.failuer("�û���Ϊ��!");
		}
		if(StringUtils.isEmpty(userType)){
			return Response.failuer("�û�����Ϊ��!");
		}
		if(StringUtils.isEmpty(password)){
			return Response.failuer("����Ϊ��!");
		}
		if(StringUtils.isEmpty(gender)){
			return Response.failuer("�Ա�Ϊ��!");
		}
		password = Utils.md5(password);
		String resulet = this.userService.register(emial, userName, gender, userType, password,path1,path2);
		Response resp = new Response();
		if(resulet.equals("exist")){
			return Response.failuer("�û��Ѵ���!");
		}else if(resulet.equals("failed")){
			return Response.failuer("ע��ʧ��!");
		}else{
			resp.addString("userId",resulet);
		}
		return resp.toString();
	}
	
	/**
	 * ע��
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout",produces = "application/json;charset=UTF-8")
	public void unlogin(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.getSession().invalidate();
		Cookie sessionIsAlive = new Cookie("sessionAlive", "1");
		//cookie�����������ڣ���Ự����ʧЧ
		sessionIsAlive.setPath("/");
		response.addCookie(sessionIsAlive);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	/**
	 * �޸�����
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
			return Response.failuer("ԭʼ����Ϊ��!");
		}
		if(StringUtils.isEmpty(newpass)){
			return Response.failuer("������Ϊ��!");
		}
		initpass = Utils.md5(initpass);
		newpass = Utils.md5(newpass);
		int x = this.userService.changepassword(initpass, newpass, userId);
		if(x == 1){
			return Response.success("�����޸ĳɹ�!");
		}else if(x == -1){
			return Response.failuer("ԭʼ���벻��ȷ!");
		}
		return Response.failuer("�޸�����ʧ��!");
	}
	
	/**
	 * ��ȡѧ����Ϣ
	 * @param userId
	 * @return
	 */
	@RequestMapping( value = "/getstudentinfo",produces = "application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String getStudentInfo(HttpServletRequest request){
		String userId = request.getSession().getAttribute("userId").toString();
		User user = this.userService.getStudentInfo(userId);
		if(user == null){
			return Response.failuer("�û������ڻ�����Ϣ������!");
		}
		Response respon = new Response();
		respon.addObject("user", user);
		return respon.toString();
	}
	
	/**
	 * �޸��û���Ϣ
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
			return Response.success("�޸ĳɹ�!");
		}
		return Response.failuer("�޸�ʧ��!");
	}
	
	/**
	 * ��ȡ��ʦ��Ϣ
	 * @param userId
	 * @return
	 */
	@RequestMapping( value = "/getteacherinfo",produces = "application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String getTeacherInfo(HttpServletRequest request){
		String userId = request.getSession().getAttribute("userId").toString();
		User user = this.userService.getTeacherInfo(userId);
		if(user == null){
			return Response.failuer("�û������ڻ�����Ϣ������!");
		}
		Response respon = new Response();
		respon.addObject("user", user);
		return respon.toString(); 
	}
	
	/**
	 * ��ȡ����Ա��Ϣ
	 */
	@RequestMapping( value = "/getadmininfo",produces = "application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String getAdminInfo(HttpServletRequest request){
		String userId = request.getSession().getAttribute("userId").toString();
		if(this.userService.checkAdmin(userId) == false){
			return Response.failuer("����Ȩ�޲���");
		}
		User user = this.userService.getAdminInfo(userId);
		if(user == null){
			return Response.failuer("�û������ڻ�����Ϣ������!");
		}
		Response respon = new Response();
		respon.addObject("user", user);
		return respon.toString(); 
	}
	
	/**
	 * ��ʦ�б�
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
			return Response.failuer("����Ȩ�޲���!");
		}
		if(quickPager.getTotalRows() == 0){
			return Response.failuer("�б���ϢΪ��!");
		}
		Response resp = new Response();
		resp.addQuickPager(quickPager);
		return resp.toString();
	}
	
	/**
	 * ��� ��ʦ��Ϣ
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/user/modifyteacher",produces = "application/json;charset=UTF-8",method=RequestMethod.POST )
	@ResponseBody
	public String modifyTeacher(HttpServletRequest request,@RequestParam(value="id")String id){
		String userId = request.getSession().getAttribute("userId").toString();
		if(StringUtils.isEmpty(id)){
			return Response.failuer("��ʦְ����Ϊ��!");
		}
		if(this.userService.checkAdmin(userId) == false){
			return Response.failuer("����Ȩ�޲���");
		}
		int x = this.userService.modifyTeacher(id);
		if(x == 0){
			return Response.failuer("��Ϣ�޸�ʧ��!");
		}
		return Response.success("��Ϣ�޸ĳɹ�!");
	}
	/**
	 * ����û�
	 * @param data
	 * @return
	 */
	@RequestMapping( value = "/user/addperson",produces = "application/json;charset=UTF-8",method=RequestMethod.POST )
	@ResponseBody
	public String addPerson(HttpServletRequest request,@RequestParam(value = "data")String data){
		String userId = request.getSession().getAttribute("userId").toString();
		if(StringUtils.isEmpty(data)){
			return Response.failuer("�������Ϊ��!");
		}
		if(this.userService.checkAdmin(userId) == false){
			return Response.failuer("����Ȩ�޲���");
		}
		int x = this.userService.addPerson(data);
		if(x == -1){
			return Response.failuer("�û��Ѵ���!");
		}
		if(x == 0){
			return Response.failuer("���ʧ��!");
		}
		return Response.success("��ӳɹ�!");
	}
	
	/**
	 * ɾ���û�
	 * @param data
	 * @return
	 */
	@RequestMapping( value = "/user/deleteperson",produces = "application/json;charset=UTF-8",method=RequestMethod.POST )
	@ResponseBody
	public String deletePerson(HttpServletRequest request,@RequestParam(value = "userId")String userId){
		String id = request.getSession().getAttribute("userId").toString();
		if(this.userService.checkAdmin(id) == false){
			return Response.failuer("����Ȩ�޲���");
		}
		if(StringUtils.isEmpty(userId)){
			return Response.failuer("û�л�ȡ��Ҫɾ�����û���Ϣ!");
		}
		int x = this.userService.deletePerson(userId);
		if(x != 1){
			return Response.failuer("ɾ��ʧ��!");
		}
		return Response.success("ɾ���ɹ�!");
	}
	
	/**
	 * ѧ���б�
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
			return Response.failuer("����Ȩ�޲���!");
		}
		if(quickPager.getTotalRows() == 0){
			return Response.failuer("�б���ϢΪ��!");
		}
		Response resp = new Response();
		resp.addQuickPager(quickPager);
		return resp.toString();
	}
	
	/**
	 * ���ѧ��
	 * @param data
	 * @return
	 */
	@RequestMapping( value = "/admin/addstudent",produces = "application/json;charset=UTF-8",method=RequestMethod.POST )
	@ResponseBody
	public String addStudnet(@RequestParam(value = "data")String data){
		if(StringUtils.isEmpty(data)){
			return Response.failuer("�������Ϊ��!");
		}
		int x = this.userService.addStudent(data);
		if(x == -1){
			return Response.failuer("�û��Ѵ���!");
		}
		if(x == 0){
			return Response.failuer("���ʧ��!");
		}
		return Response.success("��ӳɹ�!");
	}
	
	/**
	 * ����Ա�б�
	 */
	@RequestMapping( value = "/admin/lists",produces = "application/json;charset=UTF-8",method=RequestMethod.POST )
	@ResponseBody
	public String getAdminList(HttpServletRequest request){
		String userId = request.getSession().getAttribute("userId").toString();
		List<Map<String,Object>> list = this.userService.getAdminList(userId);
		if(list == null){
			return Response.failuer("����Ȩ�޲���!");
		}
		if(list.isEmpty()){
			return Response.failuer("�б���ϢΪ��!");
		}
		Response resp = new Response();
		resp.addList("list", list);
		return resp.toString();
	}
	/**
	 * ��ӹ���Ա
	 * @param data
	 * @return
	 */
	@RequestMapping( value = "/admin/addadmin",produces = "application/json;charset=UTF-8",method=RequestMethod.POST )
	@ResponseBody
	public String addAdmin(@RequestParam(value = "data")String data){
		if(StringUtils.isEmpty(data)){
			return Response.failuer("�������Ϊ��!");
		}
		int x = this.userService.addAdmin(data);
		if(x == -1){
			return Response.failuer("�û��Ѵ���!");
		}
		if(x == 0){
			return Response.failuer("���ʧ��!");
		}
		return Response.success("��ӳɹ�!");
	}
	
	@RequestMapping( value = "/admin/initpassword",produces = "application/json;charset=UTF-8",method=RequestMethod.POST )
	@ResponseBody
	public String checkInitPassword(HttpServletRequest request) throws IOException{
		String userId = request.getSession().getAttribute("userId").toString();
		User user = this.userService.login(userId);
		String intipassword = Utils.md5("admin123");
		if(user.getPassword().equals(intipassword)){
			return Response.failuer("ԭʼ����");
		}
		return Response.success("���벻��ԭʼ����");
	}
	
	@RequestMapping( value = "/admin/modifystucla",produces = "application/json;charset=UTF-8",method=RequestMethod.POST )
	@ResponseBody
	public String modifyStudent(HttpServletRequest request,
								@RequestParam(value = "class")String cla,
								@RequestParam(value = "userId")String userId){
		if(StringUtils.isEmpty(cla)){
			return Response.failuer("�༶��ϢΪ��!");
		}
		if(StringUtils.isEmpty(userId)){
			return Response.failuer("ѧ��ѧ��Ϊ��!");
		}
		int x = this.userService.modifyStudent(userId, cla);
		if(x != 1){
			return Response.failuer("���ʧ��!");
		}
		return Response.success("��ӳɹ�!");
	}
}
