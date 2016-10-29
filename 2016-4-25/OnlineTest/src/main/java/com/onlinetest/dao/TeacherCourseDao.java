package com.onlinetest.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onlinetest.common.DaoSupport;
import com.onlinetest.util.StringUtils;

/**
 * @author ¶¡ Åô
 *
 */
@Repository("TeacherCourseDao")
public class TeacherCourseDao extends DaoSupport {
	/**
	 * ²éÑ¯ËùÊÚ¿Î³Ì
	 */
	public String getCourseList(String userId){
		String sql = "select courses from teachercourse where userId = ? and isDelete = '0' ";
		Object[] args = {userId};
		String r = "";
		try {
			r = this.getJdbcTemplate().queryForObject(sql, args, String.class);	
		} catch (DataAccessException e) {
			return null;
		}
		return r;
	}
	
	public int addCourse(String courses,String userId){
		String sql1 = "select courses from teachercourse where userId = ? and isDelete = '0' ";
		Object[] args1 = {userId};
		String co = "";
		try {
			co = this.getJdbcTemplate().queryForObject(sql1, args1, String.class);
		} catch (DataAccessException e) {
			co = "";
		}
		int us = this.getJdbcTemplate().queryForObject(" select Count(*) from teachercourse where userId = ? and isDelete = '0' ",args1,Integer.class);
		if(!StringUtils.isEmpty(co) || (us == 1 && StringUtils.isEmpty(co))){
			courses = co + courses;
			String sql = "update teachercourse set courses = ? where userId = ? and isDelete = '0' ";
			Object[] args = {courses,userId};
			return this.getJdbcTemplate().update(sql, args);
		}
		String sql = "insert into teachercourse(userId,courses,isDelete) values(?,?,'0')";
		Object[] args = {userId,courses};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	public int deleteCourse(String course,String userId){
		String sql = "update teachercourse set courses = ? where userId = ? and isDelete = '0' ";
		Object[] args = {course,userId};
		return this.getJdbcTemplate().update(sql, args);
	}
}
