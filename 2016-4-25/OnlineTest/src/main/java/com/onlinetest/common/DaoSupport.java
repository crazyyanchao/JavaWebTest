package com.onlinetest.common;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;


/**
 * @author ¶¡Åô
 *
 */
public class DaoSupport {
	
	private JdbcTemplate jdbcTemplate=null;
	
	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate =  jdbcTemplate;
	}
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public String getCurrDateTime () {
		return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	public String formatDateTime (long time) {
		return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time));
	}
	
	public String getTableSequence(String tableName){
		
		int iCurSeq = -1;
		try{
			String sql ="select current_seq from sh_table_sequence where table_name = '"+tableName+"'";
			iCurSeq = this.getJdbcTemplate().queryForObject(sql, Integer.class);
			int iNxtSeq = iCurSeq + 1;
			sql = "update sh_table_sequence set current_seq = " + iNxtSeq + " where table_name = '"+tableName+"'";
			this.getJdbcTemplate().update(sql);

		}catch(org.springframework.dao.EmptyResultDataAccessException e){
			
			String sql1 = "INSERT INTO sh_table_sequence(table_name,current_seq) VALUES ('"+tableName+"',2)";
			this.getJdbcTemplate().update(sql1);
			iCurSeq = 1;
		}
		catch(Exception e){
			iCurSeq = -1;
		}
		
		try{
			if(iCurSeq > 0){
				String sCurSeq = "202" + iCurSeq;
				return sCurSeq;
				
			}else{
				return null;
			}
		}
		catch(Exception e){
			return null;
		}	
		
	}
	public Long getTableSequenceInSchoolService(String tableName) {
				int iCurSeq = -1;
				try{
					String sql ="select current_seq from schoolservice.sh_table_sequence where table_name = '"+tableName+"'";
					iCurSeq = this.getJdbcTemplate().queryForObject(sql, Integer.class);
					int iNxtSeq = iCurSeq + 1;
					sql = "update schoolservice.sh_table_sequence set current_seq = " + iNxtSeq + " where table_name = '"+tableName+"'";
					this.getJdbcTemplate().update(sql);

				}catch(org.springframework.dao.EmptyResultDataAccessException e){
					
					String sql1 = "INSERT INTO schoolservice.sh_table_sequence(table_name,current_seq) VALUES ('"+tableName+"',2)";
					this.getJdbcTemplate().update(sql1);
					iCurSeq = 1;
				}
				catch(Exception e){
					iCurSeq = -1;
					//e.printStackTrace();
				}
				
				try{
					if(iCurSeq > 0){
						String sCurSeq = "202" + iCurSeq;
						return Long.parseLong(sCurSeq);
						
					}else{
						return null;
					}
				}
				catch(Exception e){
					return null;
				}	
	}
}
