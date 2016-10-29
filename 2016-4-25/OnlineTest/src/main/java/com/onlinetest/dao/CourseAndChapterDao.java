package com.onlinetest.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onlinetest.common.DaoSupport;
import com.onlinetest.common.QuickPager;

/**
 * @author �� ��
 *
 */
@Repository("CourseAndChapterDao")
public class CourseAndChapterDao extends DaoSupport {

	/**
	 * ���ݿγ̱�Ż�ȡ�γ�����
	 * @param courseCode
	 * @return
	 */
	public String getCourseValue(String courseCode){
		try {
			String sql = "select courseCodeValue from course where courseCode = ? and isDelete = '0'";
			Object[] args = {courseCode};
			return this.getJdbcTemplate().queryForObject(sql, args, String.class);
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	/**
	 * ����xx��Ŀxx�½ڵ��½�����
	 * @param courseCode
	 * @param chapterCode
	 * @return
	 */
	public String getChapterValue(String courseCode, String chapterCode){
		try {
			String sql = "select chapterCodeValue from chapter where courseCode = ? and chapterCode = ? and isDelete = '0'";
			Object[] args = {courseCode,chapterCode};
			return this.getJdbcTemplate().queryForObject(sql, args, String.class);
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	/**
	 * ��������ģ�������γ�
	 * @param value
	 * @return
	 */
	public List<Map<String,Object>> searchCourse(String value){
		try {
			String sql = "select courseCode,courseCodeValue from course where isDelete = '0' and courseCodeValue like '%"+value+"%'";
			return this.getJdbcTemplate().queryForList(sql);
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	/**
	 * ���ݿγ������½�
	 * @param courseCode
	 * @return
	 */
	public List<Map<String,Object>> getChapter(String courseCode){
		try {
			String sql = "select chapterCode,chapterCodeValue from chapter where courseCode = ? and isDelete = '0' ";
			return this.getJdbcTemplate().queryForList(sql, courseCode);
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	/**
	 * �γ��б�
	 * @param quickPager
	 */
	public void courseList(QuickPager<Map<String,Object>> quickPager){
		String sql = "select id,courseCode,courseCodeValue from course where isDelete = '0' ";
		String sqlCount = "select Count(*) from course where isDelete = '0' ";
		if(quickPager == null) {
			quickPager  = new QuickPager<Map<String,Object>>();
		}
		int totalrows = this.getJdbcTemplate().queryForObject(sqlCount, Integer.class);
		quickPager.setTotalRows(totalrows);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list = this.getJdbcTemplate().queryForList(sql+quickPager.getSqlLimit());
		quickPager.setData(list);
	}
	
	
	/**
	 * ��ӿγ�
	 * @param courseCode
	 * @param course
	 * @return
	 */
	public int addCourse(String courseCode,String course){
		String sql = "insert into course(courseCode,courseCodeValue,isDelete) values(?,?,'0')";
		Object[] args = {courseCode,course};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * ���ݿγ�ǰ׺ģ����������ȡ���һ��ǰ׺�Ŀγ̵�Code
	 * @param code
	 * @return
	 */
	public String searchCoursePrefix(){
		String sql = "select  courseCode from course where isDelete = '0' ORDER BY courseCode DESC LIMIT 0,1";
		try {
			return this.getJdbcTemplate().queryForObject(sql, String.class);
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	/**
	 * ɾ���γ�
	 * @param courseCode
	 * @return
	 */
	public int deleteCourse(String courseCode){
		String sql = "update course set isDelete = '1' where courseCode = ? ";
		Object[] args = {courseCode};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * �½��б�
	 * @param quickPager
	 */
	public void chapterList(QuickPager<Map<String,Object>> quickPager,String courseCode){
		String sql = "select chapterCode,chapterCodeValue from chapter where courseCode = ? and isDelete = '0' ";
		String sqlCount = "select Count(*) from chapter where courseCode = ? and isDelete = '0' ";
		Object[] args = {courseCode};
		if(quickPager == null) {
			quickPager  = new QuickPager<Map<String,Object>>();
		}
		int totalrows = this.getJdbcTemplate().queryForObject(sqlCount, args, Integer.class);
		quickPager.setTotalRows(totalrows);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list = this.getJdbcTemplate().queryForList(sql+quickPager.getSqlLimit(), args);
		quickPager.setData(list);
	}
	
	/**
	 * ����½�
	 * @param courseCode
	 * @param chapterCode
	 * @param chapterValue
	 * @return
	 */
	public int addChapter(String courseCode,String chapterCode,String chapterValue){
		String sql = "insert into chapter(courseCode,chapterCode,chapterCodeValue,isDelete) values(?,?,?,'0')";
		Object[] args = {courseCode,chapterCode,chapterValue};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * �����½ڵ����һ��
	 * @param course
	 * @return
	 */
	public String searchChapterLastOne(String course){
		String sql = "select chapterCode from chapter where courseCode = ? and isDelete = '0' ORDER BY chapterCode desc LIMIT 0,1";
		Object[] args = {course};
		try {
			return this.getJdbcTemplate().queryForObject(sql, args, String.class);
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	/**
	 * �޸��½�����
	 * @param chapterValue
	 * @param courseCode
	 * @param chapterCode
	 * @return
	 */
	public int modifyChapter(String chapterValue,String courseCode,String chapterCode){
		String sql = "update chapter set chapterCodeValue = ? where courseCode = ? and chapterCode = ? ";
		Object[] args = {chapterValue,courseCode,chapterCode};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * ɾ���γ�
	 * @param courseCode
	 * @return
	 */
	public int deleteChapter(String courseCode,String chapterCode){
		String sql = "update chapter set isDelete = '1' where courseCode = ? and chapterCode = ? ";
		Object[] args = {courseCode,chapterCode};
		return this.getJdbcTemplate().update(sql, args);
	}
}
