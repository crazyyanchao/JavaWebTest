package com.onlinetest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.onlinetest.common.DaoSupport;
import com.onlinetest.common.QuickPager;
import com.onlinetest.model.SingleChoose;
import com.onlinetest.util.StringUtils;

/**
 * @author 丁 鹏
 *
 */
@Repository("SingleChooseDao")
public class SingleChooseDao extends DaoSupport {

	/**
	 * 单选题 列表
	 * @param quickPager
	 */
	public void getSingleChooseList(QuickPager<SingleChoose> quickPager,List<String> courses,long start,long end){
		String sql = "select id,course,chapter,level,title,answer1,answer2,answer3,answer4,trueanswer from singleChoose where isDelete = '0' ";
		String sqlCount = "select Count(*) from singleChoose where isDelete = '0' ";
		if(quickPager == null) {
			quickPager  = new QuickPager<SingleChoose>();
		}
		if(courses != null){
			sql += " and course in ( ? ";
			sqlCount += " and course in ( ? ";
			int len = courses.size();
			List<String> args = new ArrayList<String>();
			args.add( courses.get(0) );
			for(int i = 1; i < len; i++){
				String str = courses.get(i);
				sql += " ,? ";
				sqlCount += " ,? ";
				args.add(str);
			}
			sql += " )";
			sqlCount += " )";
			
			if(start != 0 && end != 0){
				sql += " and createTime BETWEEN ? and ? ";
				sqlCount += " and createTime BETWEEN ? and ? ";
				args.add( String.valueOf(start) );
				args.add( String.valueOf(end) );
			}
			
			int totalrows = this.getJdbcTemplate().queryForObject(sqlCount, args.toArray(), Integer.class);
			quickPager.setTotalRows(totalrows);
			
			List<SingleChoose> singlechoose = new ArrayList<SingleChoose>();
			singlechoose = this.getJdbcTemplate().query(sql+quickPager.getSqlLimit(), args.toArray(), new RowMapper<SingleChoose>() {
	            public SingleChoose mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	SingleChoose sc = new SingleChoose();
	                sc.setId(rs.getString("id"));
	                sc.setCourse(rs.getString("course"));
	                sc.setChapter(rs.getString("chapter"));
	                sc.setLevel(rs.getString("level"));
	                sc.setTitle(rs.getString("title"));
	                sc.setAnswer1(rs.getString("answer1"));
	                sc.setAnswer2(rs.getString("answer2"));
	                sc.setAnswer3(rs.getString("answer3"));
	                sc.setAnswer4(rs.getString("answer4"));
	                sc.setTrueanswer(rs.getString("trueanswer"));
	                return sc;
	            }
	        });
			quickPager.setData(singlechoose);
		}else{
			quickPager.setData(null);
		}
	}
	
	/**
	 * 抽取指定题号的单选题
	 * @param scIds
	 * @return
	 */
	public List<Map<String,Object>> getSomeSingleChoose(List<String> scIds) {
		String sql = "select id,title,answer1,answer2,answer3,answer4 from singleChoose where id = ?";
		List<Map<String,Object>> singleChoose = new ArrayList<Map<String,Object>>();
		for (String str : scIds) {
			Object[] args = { str };
			Map<String,Object> map = new HashMap<String,Object>();
			map = this.getJdbcTemplate().queryForMap(sql, args);
			singleChoose.add(map);
		}
		return singleChoose;
	}
	
	/**
	 * 抽取指定题号的单选题和答案
	 * @param scIds
	 * @return
	 */
	public List<Map<String,Object>> getSingleTitleAns(List<String> scIds) {
		String sql = "select id,title,answer1,answer2,answer3,answer4,trueanswer from singleChoose where id = ? and isDelete = '0' ";
		List<Map<String,Object>> singleChoose = new ArrayList<Map<String,Object>>();
		for (String str : scIds) {
			Object[] args = { str };
			Map<String, Object> map = new HashMap<String,Object>();
			try {
				map = this.getJdbcTemplate().queryForMap(sql, args);
			} catch (DataAccessException e) {
				map = null;
			}
			singleChoose.add(map);
		}
		return singleChoose;
	}
	
	/**
	 * 获取所有 xxx科目、xxx章节、xxx难度的单选题的Id
	 * @param course
	 * @param chapter
	 * @param level
	 * @return
	 */
	public List<String> getFullSingleChooseId(String course, String chapter, String level) {
		String sql = "select id from singlechoose where course = ? and level = ? and isDelete = '0' ";
		List<String> args = new ArrayList<String>();
		args.add(course);
		args.add(level);
		if(!StringUtils.isEmpty(chapter)){
			sql += " and chapter = ? ";
			args.add(chapter);
		}
		return this.getJdbcTemplate().queryForList(sql, args.toArray(), String.class);
	}
	
	/**
	 * 获取xx科目、xx章节的题目的数量
	 * @param course
	 * @param chapter
	 * @return
	 */
	public String getSingleChooseCount(String course,String chapter){
		String sql = "select count(*) from singlechoose where course = ? and isDelete = '0' ";
		List<String> args = new ArrayList<String>();
		args.add(course);
		if(!StringUtils.isEmpty(chapter)){
			sql += " and chapter = ?";
			args.add(chapter);
		}
		return this.getJdbcTemplate().queryForObject(sql, args.toArray(),String.class);
	}
	
	/**
	 * 获取xx科目、xx章节、xx难度的题目的数量
	 * @param course
	 * @param chapter
	 * @return
	 */
	public String getSingleChooseLevelCount(String course, String chapter, String level){
		String sql = "select count(*) from singlechoose where course = ? and level = ? and isDelete = '0' ";
		List<String> args = new ArrayList<String>();
		args.add(course);
		args.add(level);
		if(!StringUtils.isEmpty(chapter)){
			sql += " and chapter = ?";
			args.add(chapter);
		}
		return this.getJdbcTemplate().queryForObject(sql, args.toArray(),String.class);
	}
	
	/**
	 * 删除单选题
	 * @param id
	 * @return
	 */
	public int deleteSingleChoose(String id){
		String sql = "update singlechoose set isDelete = '1' where id = ?";
		Object[] args = {id};
		return this.getJdbcTemplate().update(sql, args);
	}
	/*
	 * 修改单选题
	 */
	public int modifySingleChose(String id,String title,String ans1,
			String ans2,String ans3,String ans4,String trueans){
		String sql = "update singlechoose set title = ?, answer1 = ?, answer2 = ?, answer3 = ?, answer4 = ?, trueanswer = ? where id = ?";
		Object[] args = {title,ans1,ans2,ans3,ans4,trueans,id};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/*
	 * 添加单选题(一个)
	 */
	public int addSingleChoose(String courseCode,String chapterCode,String level,String title,
			String ans1,String ans2,String ans3,String ans4,String trueans,String creator,long createTime){
		String sql = "insert into singlechoose(course,chapter,level,title,answer1,answer2,answer3,answer4,trueanswer,creator,createTime,isDelete) values(?,?,?,?,?,?,?,?,?,?,?,'0')";
		Object[] args = {courseCode,chapterCode,level,title,ans1,ans2,ans3,ans4,trueans,creator,createTime};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public String getSingleAns(String id){
		String sql = "select trueanswer from singleChoose where id = ? and isDelete = '0' ";
		Object[] args = {id};
		return this.getJdbcTemplate().queryForObject(sql, args, String.class);
	}
}
