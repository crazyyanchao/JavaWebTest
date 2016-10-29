package com.onlinetest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.onlinetest.common.DaoSupport;
import com.onlinetest.common.QuickPager;
import com.onlinetest.model.EssayQuestion;
import com.onlinetest.util.StringUtils;

/**
 * @author 丁 鹏
 *
 */
@Repository("EssayQuestionDao")
public class EssayQuestionDao extends DaoSupport {

	/**
	 * 问答题列表
	 * @param quickPager
	 */
	public void getEssayQuestionList(QuickPager<EssayQuestion> quickPager,List<String> courses,long start,long end){
		String sql = "select * from essayquestion where isDelete = '0' ";
		String sqlCount = "select Count(*) from essayquestion where isDelete = '0' ";
		if(quickPager == null) {
			quickPager = new QuickPager<EssayQuestion>();
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
			List<EssayQuestion> list = new ArrayList<EssayQuestion>();
			list = this.getJdbcTemplate().query(sql+quickPager.getSqlLimit(), args.toArray(), new RowMapper<EssayQuestion>(){
				public EssayQuestion mapRow(ResultSet rs, int rowNum) throws SQLException {
					EssayQuestion eq = new EssayQuestion();
					eq.setId(rs.getString("id"));
					eq.setCourse(rs.getString("course"));
					eq.setChapter(rs.getString("chapter"));
					eq.setLevel(rs.getString("level"));
					eq.setTitle(rs.getString("title"));
					eq.setAnswer(rs.getString("answer"));
					return eq;
				}
			});
			quickPager.setData(list);
		}else{
			quickPager.setData(null);
		}
	}
	
	/**
	 * 获取所有xx科目、xx章节、xx等级的问答题的id
	 * @param course
	 * @param chapter
	 * @param level
	 * @return
	 */
	public List<String> getFullEssayQuestionId(String course, String chapter, String level){
		String sql = "select id from essayquestion where course = ? and level = ? and isDelete = '0' ";
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
	 * 获取xx科目、xx章节的问答题的数量
	 * @param course
	 * @param chapter
	 * @return
	 */
	public String getEssayQuestionCount(String course,String chapter){
		String sql = "select count(*) from essayquestion where course = ? and isDelete = '0' ";
		List<String> args = new ArrayList<String>();
		args.add(course);
		if(!StringUtils.isEmpty(chapter)){
			sql += " and chapter = ? ";
			args.add(chapter);
		}
		return this.getJdbcTemplate().queryForObject(sql, args.toArray(), String.class);
	}
	
	/**
	 * 获取xx科目、xx章节、xx难度的问答题的数量
	 * @param course
	 * @param chapter
	 * @return
	 */
	public String getEssayQuestionLevelCount(String course, String chapter, String level){
		String sql = "select count(*) from essayquestion where course = ? and level = ? and isDelete = '0' ";
		List<String> args = new ArrayList<String>();
		args.add(course);
		args.add(level);
		if(!StringUtils.isEmpty(chapter)){
			sql += " and chapter = ? ";
			args.add(chapter);
		}
		return this.getJdbcTemplate().queryForObject(sql, args.toArray(), String.class);
	}
	
	/**
	 * 获取指定id的问答题的内容
	 * @param ids
	 * @return
	 */
	public List<Map<String,Object>> getSomeEssayQuestionId(List<String> ids){
		String sql = "select id,title from essayquestion where id = ? and isDelete = '0' ";
		List<Map<String,Object>> essayQuestion = new ArrayList<Map<String,Object>>();
		for (String str : ids) {
			Object[] args = { str };
			Map<String,Object> map = new HashMap<String,Object>();
			map = this.getJdbcTemplate().queryForMap(sql, args);
			essayQuestion.add(map);
		}
		return essayQuestion;
	}
	
	/**
	 * 获取指定id的问答题的内容和答案
	 * @param ids
	 * @return
	 */
	public List<Map<String,Object>> getEssayTitleAns(List<String> ids){
		String sql = "select id,title,answer from essayquestion where id = ? and isDelete = '0' ";
		List<Map<String,Object>> essayQuestion = new ArrayList<Map<String,Object>>();
		for (String str : ids) {
			Object[] args = { str };
			Map<String,Object> map = new HashMap<String,Object>();
			map = this.getJdbcTemplate().queryForMap(sql, args);
			essayQuestion.add(map);
		}
		return essayQuestion;
	}
	
	/**
	 * 添加问答题
	 * @param course
	 * @param chapter
	 * @param level
	 * @param title
	 * @param answer
	 * @return
	 */
	public int addEssayQuestion(String course,String chapter,String level,String title,String answer,String creator,long createTime){
		String sql = "insert into essayquestion(course,chapter,level,title,answer,creator,createTime,isDelete) values(?,?,?,?,?,?,?,'0') ";
		Object[] args = {course,chapter,level,title,answer,creator,createTime};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * 修改问答题
	 * @param id
	 * @param title
	 * @param answer
	 * @return
	 */
	public int modifyEssayQuestion(String id,String title,String answer){
		String sql = "update essayquestion set title = ?, answer = ? where id = ? and isDelete = '0' ";
		Object[] args = {title,answer,id};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * 删除问答题
	 * @param id
	 * @return
	 */
	public int deleteEssayQuestion(String id){
		String sql = "update essayquestion set isDelete = '1' where id = ? ";
		Object[] args = {id};
		return this.getJdbcTemplate().update(sql, args);
	}
}
