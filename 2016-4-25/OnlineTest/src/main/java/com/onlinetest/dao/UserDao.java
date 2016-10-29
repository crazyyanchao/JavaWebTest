package com.onlinetest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.onlinetest.common.DaoSupport;
import com.onlinetest.common.QuickPager;
import com.onlinetest.model.User;
import com.onlinetest.util.StringUtils;

/**
 * @author 丁鹏
 *
 */
@Repository("UserDao")
public class UserDao extends DaoSupport{

	/**
	 * 注册
	 * @param user
	 * @return
	 */
	public int register(User user){
		String sql = "insert into user(userId,name,password,gender,email,role,isDelete) values(?,?,?,?,?,?,'0')";
		Object[] args=new Object[]{user.getUserId(),user.getName(),user.getPassword(),user.getGender(),user.getEmail(),user.getRole()};
		if(!user.getPath1().equals("") && !user.getPath2().equals("")){
			sql = "insert into user(userId,name,password,gender,email,role,photo1,photo2,passed,isDelete) values(?,?,?,?,?,?,?,?,'0','0')";
			args = new Object[]{user.getUserId(),user.getName(),user.getPassword(),user.getGender(),user.getEmail(),user.getRole(),user.getPath1(),user.getPath2()};
		}
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * 登陆验证，根据userId查询用户是否存在
	 * @param userId
	 * @return
	 */
	public User login(String userId){
		String sql = "select id,userId,name,password,role,passed from user where userId = ? and isDelete = '0' ";
		Object[] args = new Object[]{userId};
		return this.getJdbcTemplate().query(sql, args,  new ResultSetExtractor<User>(){
			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					User user = new User();
					user.setId(rs.getString("id"));
					user.setUserId(rs.getString("userId"));
					user.setName(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					user.setRole(rs.getString("role"));
					user.setPassed(rs.getString("passed"));
					return user;
				}
				return null;
			}
		});
	}
	
	public User loginEmail(String email){
		String sql = "select userId,name,password,role,passed from user where email = ? and isDelete = '0' ";
		Object[] args = {email};
		return this.getJdbcTemplate().query(sql, args,  new ResultSetExtractor<User>(){
			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					User user = new User();
					user.setUserId(rs.getString("userId"));
					user.setName(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					user.setRole(rs.getString("role"));
					user.setPassed(rs.getString("passed"));
					return user;
				}
				return null;
			}
		});
	}
	
	//修改密码
	public int update(User user){
		String sql = "UPDATE user SET password = ? where userId = ? and isDelete = '0' ";
		Object[] args=new Object[]{user.getPassword(),user.getUserId()};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * 修改密码
	 * @param newpass
	 * @param userId
	 * @return
	 */
	public int changepassword(String newpass,String userId){
		String sql = "UPDATE user SET password = ? where userId = ? and isDelete = '0' ";
		Object[] args = new Object[]{newpass,userId};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * 根据Id查询用户信息
	 * @param userId
	 * @return
	 */
	public User checkUserByUserId(String userId){
		String sql = "select * from user where userId = ? and isDelete = '0' ";
		Object[] args = new Object[]{userId};
		return this.getJdbcTemplate().query(sql, args,  new ResultSetExtractor<User>(){
			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					User user = new User();
					user.setUserId(rs.getString("userId"));
					user.setName(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					user.setGender(rs.getString("gender"));
					user.setEmail(rs.getString("email"));
					return user;
				}
				return null;
			}
		});
	}
	
	public User checkUser(String name){
		String sql = "select * from user where name = ?";
		Object[] args = new Object[]{name};
		return this.getJdbcTemplate().query(sql, args,  new ResultSetExtractor<User>(){
			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					User user = new User();
					user.setId(rs.getString("id"));
					user.setName(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					user.setGender(rs.getString("gender"));
					user.setEmail(rs.getString("email"));
					return user;
				}
				return null;
			}
		});
	}
	
	/**
	 * 获取用户信息
	 * @param userId
	 * @return
	 */
	public User getUserInfo(String userId){
		String sql = "select userId,name,gender,phone,email,qq,role from user where userId = ? and isDelete = '0' ";
		Object[] args = {userId};
		User user = this.getJdbcTemplate().queryForObject(sql, args,  new RowMapper<User>(){
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUserId(rs.getString("userId"));
				user.setName(rs.getString("name"));
				user.setGender(rs.getString("gender"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setQq(rs.getString("qq"));
				user.setRole(rs.getString("role"));
				return user;
            }
		});
		return user;
	}
	
	public int modifyPersonInfo(String userId,String phone,String email,String qq){
		String sql = "UPDATE user SET ";
		List<String> args = new ArrayList<String>();
		if(!StringUtils.isEmpty(phone)){
			sql += " phone = ?";
			args.add(phone);
		}
		if(!StringUtils.isEmpty(email)){
			if(!StringUtils.isEmpty(phone)){
				sql += ", email = ? ";
			}else{
				sql = " email = ? ";
			}
			args.add(email);
		}
		if(!StringUtils.isEmpty(qq)){
			if(!StringUtils.isEmpty(email) || !StringUtils.isEmpty(phone)){
				sql += ", qq = ? ";
			}else{
				sql = " qq = ? ";
			}
			args.add(qq);
		}
		sql += "where userId = ? and isDelete = '0' "; 
		args.add(userId);
		return this.getJdbcTemplate().update(sql, args.toArray());
	}
	
	/**
	 * 获取学生的班级编码
	 * @param userId
	 * @return
	 */
	public String getStudentClasses(String userId){
		String sql = "select belongNo from user where userId = ? and isDelete = '0' ";
		Object[] args = {userId};
		return this.getJdbcTemplate().queryForObject(sql, args, String.class);
	}
	
	/**
	 * 获取阅卷人列表
	 */
	public List<Map<String,Object>> getMarkers(){
		String sql = "select userId,name from user where role = 'SYS01' and isDelete = '0' ";
		return this.getJdbcTemplate().queryForList(sql);
	}
	
	/**
	 * 获取教师信息列表
	 */
	public void getTeacherList(QuickPager<Map<String,Object>> quickPager){
		String sql = "select userId,name,gender,photo1,photo2,passed from user where role = 'SYS01' and isDelete = '0' ";
		String sqlCount = "select count(*) from user where role = 'SYS01' and isDelete = '0' ";
		if(quickPager == null) {
			quickPager  = new QuickPager<Map<String,Object>>();
		}
		int totalrows = this.getJdbcTemplate().queryForObject(sqlCount, Integer.class);
		quickPager.setTotalRows(totalrows);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = this.getJdbcTemplate().queryForList(sql+quickPager.getSqlLimit());
		quickPager.setData(list);
	}
	/**
	 * 审核 教师信息
	 * @param id
	 * @return
	 */
	public int modifyTeacher(String id){
		String sql = "update user set passed = '1' where userId = ? and isDelete = '0' ";
		Object[] args = {id};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * 获取学生信息列表
	 */
	public void getStudentList(QuickPager<Map<String,Object>> quickPager){
		String sql = "select userId,name,gender,belongNo from user where role = 'SYS02' and isDelete = '0' ";
		String sqlCount = "select count(*) from user where role = 'SYS02' and isDelete = '0' ";
		if(quickPager == null) {
			quickPager  = new QuickPager<Map<String,Object>>();
		}
		int totalrows = this.getJdbcTemplate().queryForObject(sqlCount, Integer.class);
		quickPager.setTotalRows(totalrows);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = this.getJdbcTemplate().queryForList(sql+quickPager.getSqlLimit());
		quickPager.setData(list);
	}
	
	/**
	 * 判断用户是否存在
	 */
	public int isExist(String userId){
		String sql = "select count(*) from user where userId = ? and isDelete = '0' ";
		Object[] args = {userId};
		return this.getJdbcTemplate().queryForObject(sql, args, Integer.class);
	}
	
	/**
	 * 添加用户
	 */
	public int addPerson(String userId,String name,String password,String gender,String role){
		String sql = "insert into user(userId,name,password,gender,role,isDelete) values(?,?,?,?,?,'0')";
		Object[] args = {userId,name,password,gender,role};
		if(role.equals("SYS01")){
			sql = "insert into user(userId,name,password,gender,role,passed,isDelete) values(?,?,?,?,?,'1','0')";
		}
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * 添加学生
	 */
	public int addStudent(String userId,String name,String password,String gender,String role,String belongNo){
		String sql = "insert into user(userId,name,password,gender,role,belongNo,isDelete) values(?,?,?,?,?,?,'0')";
		Object[] args = {userId,name,password,gender,role,belongNo};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * 删除用户
	 */
	public int deletePerson(String userId){
		String sql = "update user set isDelete = '1' where userId = ? ";
		Object[] args = {userId};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * 获取管理员列表
	 */
	public List<Map<String,Object>> getAdminList(){
		String sql = "select userId,name,gender from user where role = 'SYS03' and isDelete = '0' ";
		return this.getJdbcTemplate().queryForList(sql);
	}
	
	/**
	 * 添加管理员
	 */
	public int addAdmin(String userId,String name,String password,String gender,String role){
		String sql = "insert into user(userId,name,password,gender,role,isDelete) values(?,?,?,?,?,'0')";
		Object[] args = {userId,name,password,gender,role};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * 修改学生班级
	 * @param userId
	 * @param cla
	 * @return
	 */
	public int modifyStudent(String userId,String cla){
		String sql = " update user set belongNo = ? where userId = ? and isDelete = '0' ";
		Object[] args = {cla, userId};
		return this.getJdbcTemplate().update(sql, args);
	}
}