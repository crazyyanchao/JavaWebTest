package com.onlinetest.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onlinetest.common.DaoSupport;

@Repository("TestInformationDao")
public class TestInformationDao extends DaoSupport {

	/**
	 * 获取考试信息
	 * @param userId
	 * @return
	 */
	public Map<String, Object> getStudentTestinfo(String userId){
		try {
			String sql = "select * from tests where userId = ? and isDelete = '0' ";
			Object[] args = {userId};
			return this.getJdbcTemplate().queryForMap(sql, args);
		} catch (DataAccessException e) {
			//e.printStackTrace();
			return null;
		}
	}
}
