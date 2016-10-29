package com.onlinetest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlinetest.common.FixedLengthRandom;
import com.onlinetest.common.QuickPager;
import com.onlinetest.dao.AcademyDepartmentDao;
import com.onlinetest.dao.ClassDao;
import com.onlinetest.dao.DataDictionaryDao;
import com.onlinetest.dao.UserDao;
import com.onlinetest.model.User;
import com.onlinetest.util.Utils;

/**
 * @author 丁 鹏
 *
 */
@Service("UserService")
public class UserService {

	@Resource(name = "UserDao")
	private UserDao usserdao;
	
	@Resource(name = "DataDictionaryDao")
	private DataDictionaryDao datadictionarydao;
	
	@Resource(name = "AcademyDepartmentDao")
	private AcademyDepartmentDao academydepartmentdao;
	
	@Resource(name = "ClassDao")
	private ClassDao classdao;
	
	/**
	 * 注册
	 * @param userId
	 * @param name
	 * @param role
	 * @param password
	 * @return
	 */
	@Transactional
	public String register(String email, String name, String gender, String role, String password,String path1,String path2){
		User exist = this.usserdao.loginEmail(email);
		if(role.equals("1")){ //学生
			role = "SYS02";
			path1 = "";
			path2 = "";
		}else if(role.equals("2")){ //教师
			role = "SYS01";
		}else{
			return "failed";
		}
		String userId = createUserId();
		if(exist == null){
			User user = new User();
			user.setUserId(userId);
			user.setEmail(email);
			user.setName(name);
			user.setGender(gender);
			user.setRole(role);
			user.setPassword(password);
			user.setPath1(path1);
			user.setPath2(path2);
			int x = this.usserdao.register(user);
			if(x == 0){
				return "failed";
			}
			return userId;
		}
		return "exist";
	}
	
	
	/**
	 * 登陆，根据userId查询用户存在与否
	 * @param userId
	 * @return
	 */
	public User login(String userId){
		User user = this.usserdao.login(userId);
		if(user == null){
			return null;
		}
		if(user.getRole().equals("SYS01")){
			if(user.getPassed().equals("0")){
				user.setUserId(null);
				user.setPassed(null);
				return user;
			}
		}
		String role = this.datadictionarydao.getSecondCoudeValue(user.getRole());
		user.setRole(role);
		return user;
	}
	
	/**
	 * 登陆，根据email查询用户存在与否
	 * @param email
	 * @return
	 */
	public User loginEmail(String emial){
		User user = this.usserdao.loginEmail(emial);
		if(user == null){
			return null;
		}
		if(user.getRole().equals("SYS01")){
			if(user.getPassed().equals("0")){
				user.setUserId(null);
				user.setPassed(null);
				return user;
			}
		}
		String role = this.datadictionarydao.getSecondCoudeValue(user.getRole());
		user.setRole(role);
		return user;
	}
	
	//修改密码
	public int updatePassword(String password,String email){
		User user = new User();
		user.setPassword(password);
		user.setEmail(email);
		return this.usserdao.update(user);
	}
	/**
	 * 修改密码
	 * @param initpass
	 * @param newpass
	 * @return
	 */
	@Transactional
	public int changepassword(String initpass,String newpass,String userId){
		User user = this.usserdao.checkUserByUserId(userId);
		if(initpass.equals(user.getPassword())){
			return this.usserdao.changepassword(newpass, userId);
		}else{
			return -1;
		}
	}
	
	/**
	 * 获取学生信息
	 * @param userId
	 * @return
	 */
	public User getStudentInfo(String userId){
		User user = this.usserdao.getUserInfo(userId);
//		if(user != null){
//			String classCode = user.getBelongNo();
//			String profession = this.classdao.getProfession(classCode);
//			String classValue = this.classdao.getClassValue(classCode);
//			String academy = this.academydepartmentdao.getFirstCoudeValue(profession);
//			profession = this.academydepartmentdao.getSecondCoudeValue(profession);
//			user.setBelongNo(classValue);
//			user.setAcademy(academy);
//			user.setProfession(profession);
//		}
		return user;
	}
	/**
	 * 修改用户信息
	 * @param userId
	 * @param phone
	 * @param email
	 * @param qq
	 * @return
	 */
	@Transactional
	public int modifyPersonInfo(String userId,String phone,String email,String qq){
		return this.usserdao.modifyPersonInfo(userId, phone, email, qq);
	}
	
	/**
	 * 获取老师信息
	 * @param userId
	 * @return
	 */
	public User getTeacherInfo(String userId){
		User user = this.usserdao.getUserInfo(userId);
//		if(user != null){
//			String academyCode = user.getBelongNo();
//			String academy = this.academydepartmentdao.getFCValueByFC(academyCode);
//			if(academy == null){
//				return null;
//			}
//			user.setAcademy(academy);
//		}
		return user;
	}
	/**
	 * 获取管理员信息
	 * @param userId
	 * @return
	 */
	public User getAdminInfo(String userId){
		return this.usserdao.getUserInfo(userId);
	}
	
	/**
	 * 教师列表
	 */
	public void getTeacherList(String userId,QuickPager<Map<String,Object>> quickPager){
		User user = this.usserdao.login(userId);
		String role = user.getRole().toString();
		role = this.datadictionarydao.getSecondCoudeValue(role);
		if(role.equals("管理员") || role.equals("超级管理员")){
			this.usserdao.getTeacherList(quickPager);
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			list = quickPager.getData();
			for (Map<String, Object> map : list) {
				String gender = map.get("gender").toString();
				String pass = "";
				if(map.get("passed")!=null){
					pass = map.get("passed").toString();
				}
				if(pass.equals("0")){
					pass = "未通过";
				}else{
					pass = "通过";
				}
				gender = this.datadictionarydao.getSecondCoudeValue(gender);
				map.put("gender", gender);
				map.put("passed", pass);
			}
		}else{
			quickPager.setData(null);
		}
	}
	/**
	 * 审核 教师信息
	 * @param id
	 * @return
	 */
	public int modifyTeacher(String id){
		return this.usserdao.modifyTeacher(id);
	}
	
	/**
	 * 添加用户
	 */
	@Transactional
	public int addPerson(String data){
		String[] array = data.split("`");
		String userId = "";
		String name = "";
		String password = "";
		String gender = "";
		String role = array[0];
		for (int i = 1; i < array.length;) {
			userId = array[i];
			name = array[i+1];
			password = array[i+2];
			gender = array[i+3];
			password = Utils.md5(password);
			int e = this.usserdao.isExist(userId);
			if(e != 0){
				return -1;
			}
			int x = this.usserdao.addPerson(userId, name, password, gender,role);
			if(x == 0){
				return 0;
			}
			i += 4;
		}
		return 1;
	}
	
	/**
	 * 添加学生
	 */
	@Transactional
	public int addStudent(String data){
		String[] array = data.split("`");
		String userId = "";
		String name = "";
		String password = "";
		String gender = "";
		String belongNo = "";
		String role = array[0];
		for (int i = 1; i < array.length;) {
			userId = array[i];
			name = array[i+1];
			password = array[i+2];
			gender = array[i+3];
			belongNo = array[i+4];
			password = Utils.md5(password);
			int e = this.usserdao.isExist(userId);
			if(e != 0){
				return -1;
			}
			int x = this.usserdao.addStudent(userId, name, password, gender, role, belongNo);
			if(x == 0){
				return 0;
			}
			i += 5;
		}
		return 1;
	}
	
	/**
	 * 删除用户
	 */
	public int deletePerson(String userId){
		return this.usserdao.deletePerson(userId);
	}
	
	/**
	 * 学生列表
	 */
	public void getStudentList(String userId,QuickPager<Map<String,Object>> quickPager){
		User user = this.usserdao.login(userId);
		String role = user.getRole().toString();
		role = this.datadictionarydao.getSecondCoudeValue(role);
		if(role.equals("管理员") || role.equals("超级管理员")){
			this.usserdao.getStudentList(quickPager);
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			list = quickPager.getData();
			for (Map<String, Object> map : list) {
				String gender = map.get("gender").toString();
				String belongNo = null;
				if( map.get("belongNo") != null ){
					belongNo = map.get("belongNo").toString();
				}
				if( belongNo != null ){
					if(!belongNo.equals("") && !belongNo.equals(" ")){
						belongNo = this.classdao.getClassValue(belongNo);
						map.put("belongNo", belongNo);
					}else{
						map.put("belongNo", "无");
					}
				}else{
					map.put("belongNo", "无");
				}
				gender = this.datadictionarydao.getSecondCoudeValue(gender);
				map.put("gender", gender);
				
			}
		}else{
			quickPager.setData(null);
		}
	}
	
	/**
	 * 获取管理员列表
	 */
	public List<Map<String,Object>> getAdminList(String userId){
		String role = this.usserdao.login(userId).getRole().toString();
		role = this.datadictionarydao.getSecondCoudeValue(role);
		if(role.equals("超级管理员")){
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			list = this.usserdao.getAdminList();
			for (Map<String, Object> map : list) {
				String gender = map.get("gender").toString();
				gender = this.datadictionarydao.getSecondCoudeValue(gender);
				map.put("gender", gender);
			}
			return list;
		}
		return null;
	}
	/**
	 * 添加管理员
	 * @param data
	 * @return
	 */
	public int addAdmin(String data){
		String[] array = data.split("`");
		String userId = "";
		String name = "";
		String password = "";
		String gender = "";
		String role = array[0];
		for (int i = 1; i < array.length;) {
			userId = array[i];
			name = array[i+1];
			password = array[i+2];
			gender = array[i+3];
			password = Utils.md5(password);
			int e = this.usserdao.isExist(userId);
			if(e != 0){
				return -1;
			}
			int x = this.usserdao.addAdmin(userId, name, password, gender, role);
			if(x == 0){
				return 0;
			}
			i += 4;
		}
		return 1;
	}
	
	/**
	 * 修改学生班级信息
	 * @param userId
	 * @param cla
	 * @return
	 */
	@Transactional
	public int modifyStudent(String userId,String cla){
		return this.usserdao.modifyStudent(userId, cla);
	}
	
	/**
	 * 验证是否为管理员
	 * @param userId
	 * @return
	 */
	public boolean checkAdmin(String userId){
		User user = this.usserdao.login(userId);
		String role = user.getRole().toString();
		role = this.datadictionarydao.getSecondCoudeValue(role);
		if(role.equals("管理员") || role.equals("超级管理员")){
			return true;
		}else{
			return false;
		}
	}
	
	private String createUserId(){
		String userId = "";
		FixedLengthRandom flr = new FixedLengthRandom();
		userId = flr.getFixLenthRandom(10);
		if( this.usserdao.login(userId) != null){
			createUserId();
		}
		return userId;
	}
}
