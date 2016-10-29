package com.onlinetest.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onlinetest.common.DaoSupport;
import com.onlinetest.common.QuickPager;
import com.onlinetest.util.StringUtils;

/**
 * @author 丁 鹏
 *
 */
@Repository("TestPaperDao")
public class TestPaperDao extends DaoSupport{

	/**
	 * 获取学生的试卷信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> getStudentTestPaperInfo(String id){
		try {
			String sql = "select course,startTime,endTime from tests where id = ? and isDelete = '0' ";
			Object[] args = {id};
			return this.getJdbcTemplate().queryForMap(sql, args);
		} catch (DataAccessException e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 设定试卷
	 */
	public int setTestPaper(String course, String chapterCode, String testdate,
			String starttime, String endtime,String joinedclasses, String paperstyle, String totalScore, String markers, String userId, String creatime, 
			String sc, String mc,String tf,String fb,String eq,String sscore, String mscore,String tfscore,String fscore,String escore){
		String sql = "insert into testpaper(course,chapter,date,startTime,endTime,joinClass,creater,creatTime,totalScore,marker,sc,sScore,mc,mScore,tf,tfScore,fb,fScore,eq,eScore,fixed,isDelete) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'0') ";
		Object[] args = {course,chapterCode,testdate,starttime,endtime,joinedclasses,userId,creatime,totalScore,markers,sc,sscore,mc,mscore,tf,tfscore,fb,fscore,eq,escore,paperstyle};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * 获取试卷列表，根据出卷人id查询
	 * @param quickPager
	 * @param userId
	 */
	public void getTestpaperList(QuickPager<Map<String,Object>> quickPager,String userId){
		String sql = "select id,course,date,startTime,endTime from testpaper where isDelete = '0' and creater = ? ";
		String sqlCount = "select Count(*) from testpaper where isDelete = '0' and creater = ? ";
		Object[] args = {userId};
		if(quickPager == null) {
			quickPager  = new QuickPager<Map<String,Object>>();
		}
		int totalrows = this.getJdbcTemplate().queryForObject(sqlCount, args, Integer.class);
		quickPager.setTotalRows(totalrows);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = this.getJdbcTemplate().queryForList(sql+quickPager.getSqlLimit(), args); 
		quickPager.setData(list);
	}
	
	/**
	 * 删除试卷
	 * @param id
	 * @return
	 */
	public int deleteTestPaper(String id){
		String sql = "update testpaper set isDelete = '1' where id = ? ";
		Object[] args = {id};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * 获取学生试卷信息
	 * @param quickPager
	 * @param classCode
	 */
	public void getStudentTests(QuickPager<Map<String,Object>> quickPager,String classCode){
		String sql = "SELECT id,course,date,startTime,endTime from testpaper where  isDelete = '0' ";
		String sqlCount = "SELECT Count(*) from testpaper where  isDelete = '0' ";
		if(quickPager == null) {
			quickPager  = new QuickPager<Map<String,Object>>();
		}
		if(!StringUtils.isEmpty(classCode) && !classCode.equals("ForAllStudent") ){
			sql += " and joinClass LIKE '%"+ classCode +"%' or joinClass = 'ForAllStudent' ";
			sqlCount += " and joinClass LIKE '%"+ classCode +"%' or joinClass = 'ForAllStudent' ";
		}else{
			sql += " and joinClass = 'ForAllStudent' ";
			sqlCount += " and joinClass = 'ForAllStudent' ";
		}
		int totalrows = this.getJdbcTemplate().queryForObject(sqlCount, Integer.class);
		quickPager.setTotalRows(totalrows);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = this.getJdbcTemplate().queryForList(sql+quickPager.getSqlLimit());
		quickPager.setData(list);
	}
	
	/**
	 * 根据试卷id获取参与班级
	 * @param id
	 * @return
	 */
	public Map<String, Object> getDateTimeClassById(String id){
		String sql = "select date,startTime,endTime,joinClass from testpaper where id = ? and isDelete = '0' ";
		Object[] args = {id};
		return this.getJdbcTemplate().queryForMap(sql, args);
	}
	
	/**
	 * 根据试卷id获取试题信息
	 * @param testPaperId
	 * @return
	 */
	public Map<String, Object> getTestPaperInfo(String testPaperId){
		String sql = "select course,chapter,sc,mc,tf,fb,eq,fixed from testpaper where id = ? ";
		Object[] args = {testPaperId};
		return this.getJdbcTemplate().queryForMap(sql, args);
	}
	
	/**
	 * 获取考试时间和科目
	 */
	public Map<String, Object> getCourseTimeById(String id){
		String sql = "select course,date,startTime,endTime from testpaper where id = ?";
		Object[] args = {id};
		return this.getJdbcTemplate().queryForMap(sql, args);
	}
	
	/**
	 * 根据试卷id获取题目和试卷类型
	 * @param id
	 * @return
	 */
	public Map<String, Object> getTestInfoForCheck(String id){
		String sql = "select totalScore,sc,sScore,mc,mScore,tf,tfScore,fb,fScore,eq,eScore,fixed from testpaper where id = ? ";
		Object[] args = {id};
		return this.getJdbcTemplate().queryForMap(sql, args);
	}
	
	/**
	 * 获取科目
	 */
	public String getCourse(String id){
		String sql = "select course from testpaper where id = ? ";
		Object[] args = {id};
		return this.getJdbcTemplate().queryForObject(sql, args, String.class);
	}
	
	/**
	 * 获取阅卷人可以阅卷的试卷ID
	 * @param userId
	 * @return
	 */
	public List<String> getIdByMarker(String userId){
		String sql = "select id from testpaper where marker like '%"+userId+"%' and isDelete = '0' ";
		String sql2 = "select id from testpaper where creater = ? and isDelete = '0' ";
		Object[] args = {userId};
		List<String> list = new ArrayList<String>();
		list = this.getJdbcTemplate().queryForList(sql, String.class);
		List<String> list2 = new ArrayList<String>();
		list2 = this.getJdbcTemplate().queryForList(sql2, args, String.class);
		List<String> list3 = new ArrayList<String>(list); 
		list3.retainAll(list2);   //获取list和list2的公共数据
		list2.removeAll(list3);
		list.addAll(list2);
		return list;
	}
	
//	public String getJoinClass(String id){
//		String sql = " select joinClass from testpaper where id = ? ";
//		Object[] args = {id};
//		return this.getJdbcTemplate().queryForObject(sql, args, String.class);
//	}
	
	/**
	 * 获取试卷总分
	 * @param id
	 * @return
	 */
	public String getTotalScore(String id){
		String sql = " select totalScore from testpaper where id = ? and isDelete = '0' ";
		Object[] args = {id};
		return this.getJdbcTemplate().queryForObject(sql, args, String.class);
	}
	
	/**
	 * 获取某段时间的试卷id
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<String> getIdByDate(String startDate, String endDate,List<String> initcourse){
		String sql = " select id from testpaper where isDelete = '0' ";
		List<String> args = new ArrayList<String>();
		if(!StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate)){
			sql += " and date between ? and ? ";
			args.add(startDate);
			args.add(endDate);
		}
		if(!initcourse.isEmpty()){
			sql += " and course in ( ";
			for (String str : initcourse) {
				sql += " ?,";
				args.add(str);
			}
			sql = sql.substring(0, sql.length()-1);
			sql += ")";
		}
		List<String> list = new ArrayList<String>();
		try {
			list = this.getJdbcTemplate().queryForList(sql, args.toArray(), String.class);
		} catch (DataAccessException e) {
			list.addAll(null); 
		}
		return list;
	}
}
