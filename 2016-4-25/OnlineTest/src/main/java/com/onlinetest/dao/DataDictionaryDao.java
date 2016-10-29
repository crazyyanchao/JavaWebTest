package com.onlinetest.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onlinetest.common.DaoSupport;

/**
 * @author 丁 鹏
 *
 */
@Repository("DataDictionaryDao")
public class DataDictionaryDao extends DaoSupport{
	
	public String getFirstCoudeValue(String code){
		String sql = "select firstCodeValue from datadictionary where secondCode = ? and isDelete = '0' ";
		Object[] args = {code};
		return this.getJdbcTemplate().queryForObject(sql, args, String.class);
	}
	
	public String getSecondCoudeValue(String code){
		String sql = "select secondCodeValue from datadictionary where secondCode = ? and isDelete = '0' ";
		Object[] args = {code};
		String value = this.getJdbcTemplate().queryForObject(sql, args, String.class);
		return value;
	}
	
	public String getFCValueByFC(String firstCode){
		String sql = "select DISTINCT firstCodeValue from datadictionary where firstCode = ? and isDelete = '0' ";
		Object[] args = {firstCode};
		return this.getJdbcTemplate().queryForObject(sql, args, String.class);
	}
	
	public List<Map<String,Object>> getAbbreviation(){
		String sql = "select DISTINCT firstCodeValue,abbreviation from datadictionary WHERE firstCode REGEXP '0[01][0-9]' and isDelete = '0' ";
		return this.getJdbcTemplate().queryForList(sql);
	}
	
	/**
	 * 获取专业代码，模糊搜索
	 * @param profession
	 * @return
	 */
	public List<String> getProfessionCode(String profession){
		try {
			String sql = "SELECT secondCode from datadictionary WHERE isDelete = '0' and secondCodeValue LIKE '%"+profession+"%' ";
			return this.getJdbcTemplate().queryForList(sql, String.class);
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	/**
	 * 获取学院
	 */
	public List<Map<String,Object>> getDepartValue(){
		String sql = "SELECT DISTINCT firstCode,firstCodeValue from datadictionary WHERE firstCode LIKE '0__' and isDelete = 0";
		return this.getJdbcTemplate().queryForList(sql);
	}
	
	/**
	 * 获取学院和缩写
	 */
	public List<Map<String,Object>> getAcademyInfo(){
		String sql = "SELECT DISTINCT firstCode,firstCodeValue,abbreviation from datadictionary WHERE firstCode LIKE '0__' and isDelete = 0";
		return this.getJdbcTemplate().queryForList(sql);
	}
}
