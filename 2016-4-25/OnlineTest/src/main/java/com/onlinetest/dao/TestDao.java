package com.onlinetest.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.onlinetest.common.DaoSupport;
import com.onlinetest.common.QuickPager;
import com.onlinetest.util.StringUtils;

/**
 * @author 丁 鹏
 *
 */
@Repository("TestDao")
public class TestDao extends DaoSupport {

	/**
	 * 提交试卷，将试题写入数据库
	 */
	public int subTest(String userId, String classCode, String testPaperId, String sc, 
			String mc, String tf, String fb, String eq){
		String sql = "insert into tests(userId,classCode,testPaperId,sc,mc,tf,fb,eq,isDelete) values(?,?,?,?,?,?,?,?,'0');";
		Object[] args = {userId,classCode,testPaperId,sc,mc,tf,fb,eq};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * 提交试卷，将试题和成绩写入数据库
	 * @param userId
	 * @param classCode
	 * @param testPaperId
	 * @param score  成绩
	 * @param sc
	 * @param mc
	 * @param tf
	 * @param fb
	 * @param eq
	 * @return
	 */
	public int subTestAndScore(String userId, String classCode, String testPaperId, int score, String sc, String tf){
		String sql = "insert into tests(userId,classCode,testPaperId,score,sc,mc,tf,fb,eq,isDelete) values(?,?,?,?,?,'',?,'','','0');";
		Object[] args = {userId,classCode,testPaperId,score,sc,tf};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * 判断学生是否已经考过
	 */
	public String checkJoined(String userId,String testPaperId){
		String sql = "select count(*) from tests where userId = ? and testPaperId = ? and isDelete = '0' ";
		Object[] args = {userId,testPaperId};
		return this.getJdbcTemplate().queryForObject(sql, args, String.class);
	}
	
	/**
	 * 获取考试成绩等信息
	 * @param quickPager
	 * @param userId
	 */
	public void getScoreList(QuickPager<Map<String,Object>> quickPager,String userId){
		String sql = "select id,testPaperId,score from tests where userId = ? and isDelete='0' ";
		String sqlCount = "select count(*) from tests where userId = ? and isDelete='0' ";
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
	 * 获取试卷编号和内容
	 * @param id
	 * @return
	 */
	public Map<String,Object> getTestPaperId(String id){
		String sql = "select testPaperId,sc,mc,tf,fb,eq from tests where id = ? ";
		Object[] args = {id};
		return this.getJdbcTemplate().queryForMap(sql, args);
	}
	
	/**
	 * 教师获取学生的成绩列表
	 */
	public void getStudentScoreList(QuickPager<Map<String,Object>> quickPager,String stuCode,List<String> ids){
		String sql = "select userId,classCode,testPaperId,score from tests where isDelete='0' ";
		String sqlCount = "select count(*) from tests where isDelete='0' ";
		List<String> args = new ArrayList<String>();
		if(quickPager == null) {
			quickPager = new QuickPager<Map<String,Object>>();
		}
		if(!StringUtils.isEmpty(stuCode) || !ids.isEmpty() ){
			if( !StringUtils.isEmpty(stuCode) ){
				sql += " and userId = ? ";
				sqlCount += " and userId = ? ";
				args.add(stuCode);
			}
			if( !ids.isEmpty() ){
				sql += " and testPaperId in (";
				sqlCount += " and testPaperId in ( ";
				for (String str : ids) {
					sql += " ?,";
					sqlCount += " ?,";
					args.add(str);
				}
				sql = sql.substring(0,sql.length() - 1 );
				sqlCount = sqlCount.substring(0,sqlCount.length() - 1 );
				
				sql += ")";
				sqlCount += ")";
			}
			int totalrows = this.getJdbcTemplate().queryForObject(sqlCount, args.toArray(), Integer.class);
			quickPager.setTotalRows(totalrows);
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			list = this.getJdbcTemplate().queryForList(sql+quickPager.getSqlLimit(), args.toArray());
			quickPager.setData(list);
		}else{
			int totalrows = this.getJdbcTemplate().queryForObject(sqlCount, Integer.class);
			quickPager.setTotalRows(totalrows);
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			list = this.getJdbcTemplate().queryForList(sql+quickPager.getSqlLimit());
			quickPager.setData(list);
		}
	}
	
	public void getTestsList(List<String> ids,QuickPager<Map<String,Object>> quickPager, String classCode, String num){
		String sql = "select id,userId,testPaperId,score from tests where isDelete = '0' ";
		String sqlcount = "select count(*) from tests where isDelete = '0' ";
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		int totalrows = 0;
		if(quickPager == null) {
			quickPager = new QuickPager<Map<String,Object>>();
		}
		List<String> args = new ArrayList<String>();
		if(!StringUtils.isEmpty(classCode)){
			sql += " and classCode = ? ";
			sqlcount += " and classCode = ? ";
			args.add(classCode);
		}
		if(!StringUtils.isEmpty(num)){
			sql += " and userId = ? ";
			sqlcount += " and userId = ? ";
			args.add(num);
		}
		String x = "";
		if(!ids.isEmpty()){
			for(int i = 0; i < ids.size(); i++){
				if(i == 0){
					x = "?";
				}else{
					x += ",?";
				}
				args.add(ids.get(i));
			}
			sql += " and testPaperId in ("+x+")";
			sqlcount += " and testPaperId in ("+x+")";
		}
		totalrows += this.getJdbcTemplate().queryForObject(sqlcount, args.toArray(), Integer.class);
		list.addAll( this.getJdbcTemplate().queryForList(sql+quickPager.getSqlLimit(), args.toArray()) );
		quickPager.setTotalRows(totalrows);
		quickPager.setData(list);
	}
	
	public int setTestScore(String score,String id){
		String sql = "update tests set score = ? where id = ? ";
		Object[] args = {score,id};
		return this.getJdbcTemplate().update(sql, args);
	}
}
