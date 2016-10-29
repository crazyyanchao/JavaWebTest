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
import com.onlinetest.model.MultiChoose;
import com.onlinetest.util.StringUtils;

/**
 * @author 丁 鹏
 *
 */
@Repository("MultiChooseDao")
public class MultiChooseDao extends DaoSupport {

	/**
	 * 多选题 列表
	 * @param quickPager
	 */
	public void getMultiChooseList(QuickPager<MultiChoose> quickPager,List<String> courses,long start,long end){
		String sql = "select * from multichoose where isDelete = '0' ";
		String sqlCount = "select Count(*) from multichoose where isDelete = '0' ";
		if(quickPager == null) {
			quickPager  = new QuickPager<MultiChoose>();
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
			
			List<MultiChoose> multichoose = new ArrayList<MultiChoose>();
			multichoose = this.getJdbcTemplate().query(sql+quickPager.getSqlLimit(), args.toArray(), new RowMapper<MultiChoose>() {
	            public MultiChoose mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	MultiChoose mc = new MultiChoose();
	                mc.setId(rs.getString("id"));
	                mc.setCourse(rs.getString("course"));
	                mc.setChapter(rs.getString("chapter"));
	                mc.setLevel(rs.getString("level"));
	                mc.setTitle(rs.getString("title"));
	                mc.setAnswer1(rs.getString("answer1"));
	                mc.setAnswer2(rs.getString("answer2"));
	                mc.setAnswer3(rs.getString("answer3"));
	                mc.setAnswer4(rs.getString("answer4"));
	                mc.setAnswer5(rs.getString("answer5"));
	                mc.setAnswer6(rs.getString("answer6"));
	                mc.setTrueanswer1(rs.getString("trueanswer"));
	                return mc;
	            }
	        });
			quickPager.setData(multichoose);
		}else{
			quickPager.setData(null);
		}
	}
	
	/**
	 * 抽取指定题号的多选题
	 * @param scIds
	 * @return
	 */
	public List<Map<String,Object>> getSomeMultiChoose(List<String> scIds) {
		String sql = "select id,title,answer1,answer2,answer3,answer4,answer5,answer6 from multichoose where id = ? and isDelete = '0' ";
		List<Map<String,Object>> multiChoose = new ArrayList<Map<String,Object>>();
		for (String str : scIds) {
			Object[] args = { str };
			Map<String,Object> map = new HashMap<String,Object>();
			map = this.getJdbcTemplate().queryForMap(sql, args);
			multiChoose.add(map);
		}
		return multiChoose;
	}
	
	/**
	 * 抽取指定题号的多选题和答案
	 * @param scIds
	 * @return
	 */
	public List<Map<String,Object>> getMultiTitleAns(List<String> scIds) {
		String sql = "select id,title,answer1,answer2,answer3,answer4,answer5,answer6,trueanswer from multichoose where id = ? ";
		List<Map<String,Object>> multiChoose = new ArrayList<Map<String,Object>>();
		for (String str : scIds) {
			Object[] args = { str };
			Map<String,Object> map = new HashMap<String,Object>();
			map = this.getJdbcTemplate().queryForMap(sql, args);
			multiChoose.add(map);
		}
		return multiChoose;
	}
	
	/**
	 * 获取所有 xxx科目、xxx章节、xxx难度的多选题的Id
	 * @param course
	 * @param chapter
	 * @param level
	 * @return
	 */
	public List<String> getFullMultiChooseId(String course, String chapter, String level) {
		String sql = "select id from multichoose where course = ? and level = ? and isDelete = '0' ";
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
	 * 获取xx科目、xx章节的题目数量
	 * @param course
	 * @param chapter
	 * @return
	 */
	public String getMultiChooseCount(String course,String chapter){
		String sql = "select count(*) from multichoose where course = ? and isDelete = '0' ";
		List<String> args = new ArrayList<String>();
		args.add(course);
		if(!StringUtils.isEmpty(chapter)){
			sql += " and chapter = ? ";
			args.add(chapter);
		}
		return this.getJdbcTemplate().queryForObject(sql, args.toArray(), String.class);
	}
	
	/**
	 * 获取xx科目、xx章节、xx难度的题目数量
	 * @param course
	 * @param chapter
	 * @return
	 */
	public String getMultiChooseLevelCount(String course, String chapter, String level){
		String sql = "select count(*) from multichoose where course = ? and level = ? and isDelete = '0' ";
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
	 * 删除多选题
	 * @param id
	 * @return
	 */
	public int deleteMultiChoose(String id){
		String sql = "update multichoose set isDelete = '1' where id = ?";
		Object[] args = {id};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * 修改多选题
	 * @param id
	 * @param title
	 * @param ans1
	 * @param ans2
	 * @param ans3
	 * @param ans4
	 * @param ans5
	 * @param ans6
	 * @param trueans1
	 * @param trueans2
	 * @param trueans3
	 * @param trueans4
	 * @param trueans5
	 * @param trueans6
	 * @return
	 */
	public int modifyMultiChose(String id,String title,String ans1,String ans2,String ans3,String ans4,String ans5,String ans6,String trueans){
		String sql = "update multichoose set title = ?, answer1 = ?, answer2 = ?, answer3 = ?, answer4 = ?, answer5 = ?, answer6 = ?, trueanswer = ? where id = ? and isDelete = '0' ";
		Object[] args = {title,ans1,ans2,ans3,ans4,ans5,ans6,trueans,id};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * 添加多选题(一个)
	 */
	public int addMultiChoose(String courseCode,String chapterCode,String level,String title,String ans1,String ans2,String ans3,String ans4,String ans5,
			String ans6,String trueans,String creator,long createTime){
		String sql = "insert into multichoose(course,chapter,level,title,answer1,answer2,answer3,answer4,answer5,answer6,trueanswer,creator,createTime,isDelete) values(?,?,?,?,?,?,?,?,?,?,?,?,?,'0')";
		Object[] args = {courseCode,chapterCode,level,title,ans1,ans2,ans3,ans4,ans5,ans6,trueans,creator,createTime};
		return this.getJdbcTemplate().update(sql, args);
	}
}
