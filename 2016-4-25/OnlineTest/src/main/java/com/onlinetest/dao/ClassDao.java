package com.onlinetest.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.onlinetest.common.DaoSupport;
import com.onlinetest.common.QuickPager;

/**
 * @author 丁 鹏
 *
 */
@Repository("ClassDao")
public class ClassDao extends DaoSupport {

	/**
	 * 获取班级列表
	 * @param quickPager
	 */
	public void getClassList(QuickPager<Map<String,Object>> quickPager,List<String> departs){
		String sql = "select id,classCode,classValue from class where isDelete = '0' ";
		String sqlCount = "select Count(*) from class where isDelete = '0' ";
		if(quickPager == null) {
			quickPager  = new QuickPager<Map<String,Object>>();
		}
		List<String> args = new ArrayList<String>();
		if(!departs.isEmpty()){
			int len = departs.size();
			sql += " and profession in ( ? ";
			sqlCount += "and profession in ( ? ";
			args.add( departs.get(0) );
			for(int i = 1; i < len; i++){
				sql += " ,? ";
				sqlCount += " ,? ";
				args.add( departs.get(i) );
			}
			sql += " ) ";
			sqlCount += " )";
			int totalrows = this.getJdbcTemplate().queryForObject(sqlCount, args.toArray(), Integer.class);
			quickPager.setTotalRows(totalrows);
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			list = this.getJdbcTemplate().queryForList(sql, args.toArray());
			quickPager.setData(list);
		}else{
			int totalrows = this.getJdbcTemplate().queryForObject(sqlCount, Integer.class);
			quickPager.setTotalRows(totalrows);
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			list = this.getJdbcTemplate().queryForList(sql);
			quickPager.setData(list);
		}
	}
	
	public void getClassList2(QuickPager<Map<String,Object>> quickPager){
		String sql = "select id,profession,startYear,classValue,classCode from class where isDelete = '0' ";
		String sqlCount = "select Count(*) from class where isDelete = '0' ";
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
	 * 根据系别获取班级列表
	 * @param quickPager
	 * @param profession
	 */
	public List<Map<String,Object>> getClassListByPro(String profession){
		String sql = "select id,classCode,classValue from class where isDelete = '0' and profession = ? ";
		Object[] args = {profession};
		return this.getJdbcTemplate().queryForList(sql, args);
	}
	
	public String getClassValue(String classCode){
		String sql = "select classValue from class where classCode = ? and isDelete = '0' ";
		Object[] args = {classCode};
		String str = this.getJdbcTemplate().queryForObject(sql, args, String.class);
		return str;
	}
	
	public List<Map<String,Object>> getClasses(String department){
		String sql = "select classCode,classValue from class where profession like '"+department+"%' and isDelete='0' ";
		return this.getJdbcTemplate().queryForList(sql);
	}
	
	public int deleteClass(String id){
		String sql = "update class set isDelete = '1' where id = ? ";
		Object[] args = {id};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	public int countClasses(String profession,String startYear){
		String sql = "select count(*) from class where profession = ? and startYear = ? and isDelete = '0' ";
		Object[] args = {profession,startYear};
		return this.getJdbcTemplate().queryForObject(sql, args, Integer.class);
	}
	
	public int addClass(String profession,String startYear, String classCode,String classValue){
		String sql = "insert into class(profession,startYear,classCode,ClassValue,isDelete) values(?,?,?,?,'0')";
		Object[] args = {profession,startYear,classCode,classValue};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	public int modifyClass(String id,String name){
		String sql = " update class set classValue = ? where id = ? and isDelete = '0' ";
		Object[] args = {name,id};
		return this.getJdbcTemplate().update(sql, args);
	}
}
