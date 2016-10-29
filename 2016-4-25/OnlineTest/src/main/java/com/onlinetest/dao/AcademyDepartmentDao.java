package com.onlinetest.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onlinetest.common.DaoSupport;
import com.onlinetest.util.StringUtils;

/**
 * @author �� ��
 *
 */
@Repository("AcademyDepartmentDao")
public class AcademyDepartmentDao extends DaoSupport{
	
	/**
	 * ����ϵ�����ȡѧԺֵ
	 * @param code
	 * @return
	 */
	public String getFirstCoudeValue(String code){
		String sql = "select firstCodeValue from academyDepartment where secondCode = ? and isDelete = '0' ";
		Object[] args = {code};
		return this.getJdbcTemplate().queryForObject(sql, args, String.class);
	}
	
	/**
	 * �ж�ѧԺ�Ƿ����
	 * @param code
	 * @return
	 */
	public int searchAcademy(String value){
		String sql = "select count(*) from academyDepartment where firstCodeValue = ? and isDelete = '0' ";
		Object[] args = {value};
		return this.getJdbcTemplate().queryForObject(sql, args, Integer.class);
	}
	
	/**
	 * ����ϵ�����ȡϵֵ
	 * @param code
	 * @return
	 */
	public String getSecondCoudeValue(String code){
		String sql = "select secondCodeValue from academyDepartment where secondCode = ? and isDelete = '0' ";
		Object[] args = {code};
		String value = this.getJdbcTemplate().queryForObject(sql, args, String.class);
		return value;
	}
	
	/**
	 * ����ѧԺ�����ȡѧԺֵ
	 * @param firstCode
	 * @return
	 */
	public String getFCValueByFC(String firstCode){
		String sql = "select DISTINCT firstCodeValue from academyDepartment where firstCode = ? and isDelete = '0' ";
		Object[] args = {firstCode};
		return this.getJdbcTemplate().queryForObject(sql, args, String.class);
	}
	
	/**
	 * ��ȡϵ���룬ģ������
	 * @param profession
	 * @return
	 */
	public List<String> getProfessionCode(String profession){
		try {
			String sql = "SELECT secondCode from academyDepartment WHERE isDelete = '0' and secondCodeValue LIKE '%"+profession+"%' ";
			return this.getJdbcTemplate().queryForList(sql, String.class);
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	/**
	 * ��ȡѧԺ
	 */
	public List<Map<String,Object>> getDepartValue(){
		String sql = "SELECT DISTINCT firstCode,firstCodeValue from academyDepartment WHERE firstCode LIKE '0__' and isDelete = 0";
		return this.getJdbcTemplate().queryForList(sql);
	}
	
	/**
	 * ��ȡѧԺ
	 */
	public List<Map<String,Object>> getAcademyInfo(){
		String sql = "SELECT DISTINCT firstCode,firstCodeValue from academyDepartment WHERE firstCode LIKE '0__' and isDelete = 0";
		return this.getJdbcTemplate().queryForList(sql);
	}
	
	/**
	 * ���ѧԺ
	 */
	public int addAcademy(String code, String value){
		String sql = "insert into academyDepartment(firstCode,firstCodeValue) values(?,?)";
		Object[] args = {code,value};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * ���ϵ
	 */
	public int addDepartment(String fcode, String fvalue, String scode, String svalue){
		String sql = "insert into academyDepartment(firstCode,firstCodeValue,secondCode,secondCodeValue) values(?,?,?,?)";
		Object[] args = {fcode,fvalue,scode,svalue};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * ɾ��ѧԺ�Լ���ѧԺ������ϵ
	 * @param code
	 * @return
	 */
	public int deleteAcademy(String code){
		String sql = "update academyDepartment set isDelete = '1' where firstCode = ? ";
		Object[] args = {code};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * ɾ��ϵ
	 */
	public int deleteDepartment(String scode){
		String sql = "update academyDepartment set isDelete = '1'  where secondCode = ? ";
		Object[] args = {scode};
		return this.getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * ����ѧԺ�����ȡϵֵ
	 */
	public List<Map<String,Object>> getDepartmentList(String code){
		String sql = "select firstCodeValue,secondCode,secondCodeValue from academyDepartment where isDelete = '0' and secondCode != '' ";
		List<String> args = new ArrayList<String>();
		if(!StringUtils.isEmpty(code)){
			sql += " and firstCode = ? ";
			args.add(code);
			return this.getJdbcTemplate().queryForList(sql, args.toArray());
		}
		return this.getJdbcTemplate().queryForList(sql);
	}
	
	/**
	 * �жϿ�ϵ�Ƿ����
	 * @param code
	 * @return
	 */
	public int searchDepartment(String code){
		String sql = "select count(*) from academyDepartment where secondCode = ? and isDelete = '0' ";
		Object[] args = {code};
		return this.getJdbcTemplate().queryForObject(sql, args, Integer.class);
	}
	
	/**
	 * ����ѧԺ�����ȡϵֵ
	 * @param code
	 * @return
	 */
	public List<String> getDepartments(String code){
		String sql = "select DISTINCT secondCode from academyDepartment where isDelete = '0' and firstCode = ? ";
		Object[] args = {code};
		return this.getJdbcTemplate().queryForList(sql, args, String.class);
	}
}
