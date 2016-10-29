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
import com.onlinetest.model.FillBlank;
import com.onlinetest.util.StringUtils;

/**
 * @author 丁 鹏
 *
 */
@Repository("FillBlankDao")
public class FillBlankDao extends DaoSupport {

	/**
	 * 填空判断 列表
	 * @param quickPager
	 */
	public void getFillBlankList(QuickPager<FillBlank> quickPager,List<String> courses,long start,long end) {
		String sql = "select * from fillblank where isDelete = '0' ";
		String sqlCount = "select Count(*) from fillblank where isDelete = '0' ";
		if(quickPager == null) {
			quickPager = new QuickPager<FillBlank>();
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
			List<FillBlank> list = new ArrayList<FillBlank>();
			list = this.getJdbcTemplate().query(sql+quickPager.getSqlLimit(), args.toArray(), new RowMapper<FillBlank>(){
				public FillBlank mapRow(ResultSet rs, int rowNum) throws SQLException {
					FillBlank fb = new FillBlank();
					fb.setId(rs.getString("id"));
					fb.setCourse(rs.getString("course"));
					fb.setChapter(rs.getString("chapter"));
					fb.setLevel(rs.getString("level"));
					fb.setTitle(rs.getString("title"));
					fb.setIsAnswerFixed(rs.getString("isAnswerFixed"));
					fb.setAnswer(rs.getString("answer"));
					return fb;
				}
			});
			quickPager.setData(list);
		}else{
			quickPager.setData(null);
		}
	}

	/**
	 * 获取所有 xxx科目、xxx章节、xxx难度的填空题的id
	 * @param course
	 * @param chapter
	 * @param level
	 * @return
	 */
	public List<String> getFullFillBlankId(String course, String chapter, String level) {
		String sql = "select id from fillblank where course = ? and level = ? and isAnswerFixed = '3000' and isDelete = '0' ";
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
	 * 获取所有 xxx科目、xxx章节、xxx难度的判断题的id
	 * @param course
	 * @param chapter
	 * @param level
	 * @return
	 */
	public List<String> getFullTFId(String course, String chapter, String level) {
		String sql = "select id from fillblank where course = ? and level = ? and isAnswerFixed = '3001' and isDelete = '0' ";
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
	 * 获取所有 xxx科目、xxx章节、xxx难度的判断题的id
	 * @param course
	 * @param chapter
	 * @param level
	 * @return
	 */
	public List<String> getFullTrueOrFalseId(String course, String chapter, String level) {
		String sql = "select id from fillblank where course = ? and level = ? and isAnswerFixed = '3001' and isDelete = '0' ";
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
	 * 获取xxx科目、xxx章节填空题的数量
	 * @param course
	 * @param chapter
	 * @return
	 */
	public String getFillBlankCount(String course,String chapter){
		String sql = "select count(*) from fillblank where course = ? and isAnswerFixed = '3000' and isDelete = '0' ";
		List<String> args = new ArrayList<String>();
		args.add(course);
		if(!StringUtils.isEmpty(chapter)){
			sql += " and chapter = ? ";
			args.add(chapter);
		}
		return this.getJdbcTemplate().queryForObject(sql, args.toArray(), String.class);
	}
	
	/**
	 * 获取xxx科目、xxx章节判断题的数量
	 * @param course
	 * @param chapter
	 * @return
	 */
	public String getTFCount(String course,String chapter){
		String sql = "select count(*) from fillblank where course = ? and isAnswerFixed = '3001' and isDelete = '0' ";
		List<String> args = new ArrayList<String>();
		args.add(course);
		if(!StringUtils.isEmpty(chapter)){
			sql += " and chapter = ? ";
			args.add(chapter);
		}
		return this.getJdbcTemplate().queryForObject(sql, args.toArray(), String.class);
	}
	
	/**
	 * 获取xxx科目、xxx章节判断题的数量
	 * @param course
	 * @param chapter
	 * @return
	 */
	public String getTrueOrFalseCount(String course,String chapter){
		String sql = "select count(*) from fillblank where course = ? and isAnswerFixed = '3001' and isDelete = '0' ";
		List<String> args = new ArrayList<String>();
		args.add(course);
		if(!StringUtils.isEmpty(chapter)){
			sql += " and chapter = ? ";
			args.add(chapter);
		}
		return this.getJdbcTemplate().queryForObject(sql, args.toArray(), String.class);
	}
	
	/**
	 * 获取xxx科目、xxx章节、xxn难度填空题的数量
	 * @param course
	 * @param chapter
	 * @return
	 */
	public String getFillBlankLevelCount(String course, String chapter, String level){
		String sql = "select count(*) from fillblank where course = ? and level = ? and isAnswerFixed = '3000'  and isDelete = '0' ";
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
	 * 获取xxx科目、xxx章节、xxn难度判断题的数量
	 * @param course
	 * @param chapter
	 * @return
	 */
	public String getTFLevelCount(String course, String chapter, String level){
		String sql = "select count(*) from fillblank where course = ? and level = ? and isAnswerFixed = '3001'  and isDelete = '0' ";
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
	 * 获取xxx科目、xxx章节、xxn难度判断题的数量
	 * @param course
	 * @param chapter
	 * @return
	 */
	public String getTrueOrFalseLevelCount(String course, String chapter, String level){
		String sql = "select count(*) from fillblank where course = ? and level = ? and isAnswerFixed = '3001'  and isDelete = '0' ";
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
	 * 获取指定Id的填空题
	 * @param ids
	 * @return
	 */
	public List<Map<String,Object>> getSomeFillBlank(List<String> ids){
		String sql = "select id,title from fillblank where id = ? and isDelete = '0' ";
		List<Map<String,Object>> fillBlank = new ArrayList<Map<String,Object>>();
		for (String str : ids) {
			Object[] args = { str };
			Map<String,Object> map = new HashMap<String,Object>();
			map = this.getJdbcTemplate().queryForMap(sql, args);
			fillBlank.add(map);
		}
		return fillBlank;
	}
	
	/**
	 * 获取指定Id的判断题
	 * @param ids
	 * @return
	 */
	public List<Map<String,Object>> getSomeTFQuestion(List<String> ids){
		String sql = "select id,title from fillblank where id = ? and isDelete = '0' ";
		List<Map<String,Object>> fillBlank = new ArrayList<Map<String,Object>>();
		for (String str : ids) {
			Object[] args = { str };
			Map<String,Object> map = new HashMap<String,Object>();
			map = this.getJdbcTemplate().queryForMap(sql, args);
			fillBlank.add(map);
		}
		return fillBlank;
	}
	
	/**
	 * 获取指定Id的填空题和答案
	 * @param ids
	 * @return
	 */
	public List<Map<String,Object>> getFillTitleAns(List<String> ids){
		String sql = "select id,title,answer from fillblank where id = ? and isDelete = '0' ";
		List<Map<String,Object>> fillBlank = new ArrayList<Map<String,Object>>();
		for (String str : ids) {
			Object[] args = { str };
			Map<String,Object> map = new HashMap<String,Object>();
			map = this.getJdbcTemplate().queryForMap(sql, args);
			fillBlank.add(map);
		}
		return fillBlank;
	}
	
	/**
	 * 添加填空题
	 * @param course
	 * @param chapter
	 * @param level
	 * @param title
	 * @param fixd
	 * @param answer
	 * @return
	 */
	public int addFillBlank(String course,String chapter,String level,String title,String fixed,String answer,String creator,long createTime){
		String sql = "insert into fillblank(course,chapter,level,title,isAnswerFixed,answer,creator,createTime,isDelete) values(?,?,?,?,?,?,?,?,'0') ";
		Object[] args = {course,chapter,level,title,fixed,answer,creator,createTime};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * 修改填空题
	 * @param id
	 * @param title
	 * @param fixd
	 * @param answer
	 * @return
	 */
	public int modifyFillBlank(String id,String title,String fixed,String answer){
		String sql = "update fillblank set title = ?, isAnswerFixed = ?, answer = ? where id = ? and isDelete = '0' ";
		Object[] args = {title,fixed,answer,id};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * 删除填空题
	 * @param id
	 * @return
	 */
	public int deleteFillBlank(String id){
		String sql = "update fillblank set isDelete = '1' where id = ? ";
		Object[] args = {id};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public String getTFAns(String id){
		String sql = "select answer from fillblank where id = ? and isDelete = '0' ";
		Object[] args = {id};
		return this.getJdbcTemplate().queryForObject(sql, args, String.class);
	}
}
