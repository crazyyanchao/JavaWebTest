package com.onlinetest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onlinetest.common.Response;
import com.onlinetest.service.AcademyService;
import com.onlinetest.util.StringUtils;

/**
 * @author �� ��
 *
 */
@Controller("AcademyController")
@RequestMapping("/academy")
public class AcademyController {

	@Resource(name = "AcademyService")
	private AcademyService academyservice;
	
	@RequestMapping(value = "/academylist", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getAcademyList(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = this.academyservice.getAcademyList();
		if(list.isEmpty()){
			return Response.failuer("ѧԺ��ϢΪ��!");
		}
		Response resp = new Response();
		resp.addList("list", list);
		return resp.toString();
	}
	
	@RequestMapping(value = "/addacademy", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addAcademy(@RequestParam(value = "data")String data){
		if(StringUtils.isEmpty(data)){
			return Response.failuer("�����ϢΪ��!");
		}
		int x = this.academyservice.addAcademy(data);
		if(x == -1){
			return Response.failuer("ѧԺ����д�Ѵ���!");
		}else if(x == 0){
			return Response.failuer("���ʧ��!");
		}
		return Response.success("��ӳɹ�!");
	}
	
	@RequestMapping(value = "/deleteacademy", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteAcademy(@RequestParam(value = "code")String code){
		if(StringUtils.isEmpty(code)){
			return Response.failuer("ɾ��ѧԺΪ�գ���ˢ�º���!");
		}
		int x = this.academyservice.deleteAcademy(code);
		if(x == 0){
			return Response.failuer("ɾ��ʧ��!");
		}
		return Response.success("ɾ���ɹ�!");
	}
	
	/**
	 * ϵ�б�
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/departmentlist", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getDepartmentList(@RequestParam(value = "code",required=false)String code){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = this.academyservice.getDepartmentList(code);
		if(list.isEmpty()){
			return Response.failuer("��ϵ��ϢΪ��!");
		}
		Response resp = new Response();
		resp.addList("list", list);
		return resp.toString();
	}
	
	@RequestMapping(value = "/adddepartment", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addDepartment(@RequestParam(value = "data")String data){
		if(StringUtils.isEmpty(data)){
			return Response.failuer("�����ϢΪ��!");
		}
		int x = this.academyservice.addDepartment(data);
		if(x == -1){
			return Response.failuer("��ϵ���ϵ��д�Ѵ���!");
		}else if(x == 0){
			return Response.failuer("���ʧ��!");
		}
		return Response.success("��ӳɹ�!");
	}
	
	@RequestMapping(value = "/deletedepartment", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteDepartment(@RequestParam(value = "code")String code){
		if(StringUtils.isEmpty(code)){
			return Response.failuer("ɾ����ϵΪ�գ���ˢ�º���!");
		}
		int x = this.academyservice.deleteDepartment(code);
		if(x == 0){
			return Response.failuer("ɾ��ʧ��!");
		}
		return Response.success("ɾ���ɹ�!");
	}
}
