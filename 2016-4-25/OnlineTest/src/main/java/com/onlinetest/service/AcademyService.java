package com.onlinetest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlinetest.dao.AcademyDepartmentDao;

/**
 * @author ∂° ≈Ù
 *
 */
@Service("AcademyService")
public class AcademyService {

	@Resource(name = "AcademyDepartmentDao")
	private AcademyDepartmentDao academydepartmentdao;
	
	public List<Map<String,Object>> getAcademyList(){
		return this.academydepartmentdao.getAcademyInfo();
	}
	
	@Transactional
	public int addAcademy(String data){
		String[] array = data.split("`");
		int s = this.academydepartmentdao.getDepartValue().size();
		for(int i = 0; i < array.length;){
			String academy = array[i];
			int exist = this.academydepartmentdao.searchAcademy(academy);
			if(exist != 0){
				return -1;
			}
			s++;
			String code = "";
			if(s < 10){
				code = "00"+String.valueOf(s);
			}else{
				code = "0"+String.valueOf(s);
			}
			int x = this.academydepartmentdao.addAcademy(code, academy);
			if(x != 1){
				return 0;
			}
			i += 2;
		}
		return 1;
	}
	
	@Transactional
	public int deleteAcademy(String code){
		return this.academydepartmentdao.deleteAcademy(code);
	}
	
	/**
	 * œµ¡–±Ì
	 * @param code
	 * @return
	 */
	public List<Map<String, Object>> getDepartmentList(String code){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list = this.academydepartmentdao.getDepartmentList(code);
		for(int i = 0; i < list.size(); i++){
			Map<String,Object> map = new HashMap<String,Object>();
			map = list.get(i);
			String c = map.get("secondCode").toString();
			String abb = c.substring(3);
			map.put("abbreviation", abb);
		}
		return list;
	}
	
	@Transactional
	public int addDepartment(String data){
		String[] array = data.split("`");
		for(int i = 0; i < array.length;){
			String academyCode = array[i];
			String academyValue = array[i+1];
			String department = array[i+2];
			String abb = array[i+3].toUpperCase();
			String departmentCode = academyCode+abb;
			int exist = this.academydepartmentdao.searchDepartment(department);
			if(exist != 0){
				return -1;
			}
			int x = this.academydepartmentdao.addDepartment(academyCode, academyValue,departmentCode, department);
			if(x != 1){
				return 0;
			}
			i += 4;
		}
		return 1;
	}
	
	@Transactional
	public int deleteDepartment(String code){
		return this.academydepartmentdao.deleteDepartment(code);
	}
	
}
