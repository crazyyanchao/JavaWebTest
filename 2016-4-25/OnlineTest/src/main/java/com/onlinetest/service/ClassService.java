package com.onlinetest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlinetest.common.QuickPager;
import com.onlinetest.dao.AcademyDepartmentDao;
import com.onlinetest.dao.ClassDao;
import com.onlinetest.util.StringUtils;

/**
 * @author ∂° ≈Ù
 *
 */
@Service("ClassService")
public class ClassService {

	@Resource(name = "ClassDao")
	private ClassDao classdao;
	
	@Resource(name = "AcademyDepartmentDao")
	private AcademyDepartmentDao academydepartmentdao;
	
	public void getClassList(QuickPager<Map<String,Object>> quickPager,String code){
		List<String> list = new ArrayList<String>();
		if(!StringUtils.isEmpty(code)){
			list = this.academydepartmentdao.getDepartments(code);
		}
		this.classdao.getClassList(quickPager,list);
	}
	
	public void getClassList2(QuickPager<Map<String,Object>> quickPager){
		this.classdao.getClassList2(quickPager);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = quickPager.getData();
		for (Map<String, Object> map : list) {
			String depart = map.get("classCode").toString();
			map.remove("classCode");
			String profession = map.get("profession").toString();
			String academy = depart.substring(0, 3);
			academy = this.academydepartmentdao.getFCValueByFC(academy);
			profession = this.academydepartmentdao.getSecondCoudeValue(profession);
			map.put("academy", academy);
			map.put("profession", profession);
		}
	}
	
	public List<Map<String, Object>> getClassListByPro(String profession){
		List<String> list = new ArrayList<String>();
		list = this.academydepartmentdao.getProfessionCode(profession);
		List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
		if(list == null){
			return null;
		}
		for (String string : list) {
			List<Map<String, Object>> map = this.classdao.getClassListByPro(string);
			data.addAll(map);
		}
		return data;
	}
	
	@Transactional
	public int deleteClass(String id){
		return this.classdao.deleteClass(id);
	}
	
	@Transactional
	public int addClass(String data){
		String[] arr = data.split("`");
		String profession = "";
		String start = "";
		String classValue = "";
		for (int i = 0; i < arr.length;) {
			profession = arr[i];
			start = arr[i+1];
			classValue = arr[i+2];
			String classCode = String.valueOf(this.classdao.countClasses(profession, start) + 1);
			if(classCode.length() == 1){
				classCode = "0"+classCode; 
			}
			classCode = profession + start + classCode;
			int x = this.classdao.addClass(profession,start,classCode, classValue);
			if(x == 0){
				return 0;
			}
			i += 3;
		}
		return 1;
	}
	
	@Transactional
	public int modifyClass(String id,String name){
		return this.classdao.modifyClass(id, name);
	}
	
}
