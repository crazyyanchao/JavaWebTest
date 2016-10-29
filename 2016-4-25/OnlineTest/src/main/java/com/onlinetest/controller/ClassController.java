package com.onlinetest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onlinetest.common.QuickPager;
import com.onlinetest.common.Response;
import com.onlinetest.service.ClassService;
import com.onlinetest.util.StringUtils;

/**
 * @author �� ��
 *
 */
@Controller("ClassController")
@RequestMapping("/classes")
public class ClassController {

	@Resource(name = "ClassService")
	private ClassService classservice;
	
	@RequestMapping(value = "/classlist", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getClassList(@RequestParam(value="currPage",required=false)String currPage,
				               @RequestParam(value="pageSize",required=false)String pageSize,
				               @RequestParam(value="code",required=false)String code){
		QuickPager<Map<String,Object>> quickPager = new QuickPager<Map<String,Object>>(currPage,pageSize);
		this.classservice.getClassList(quickPager,code);
		if(quickPager.getTotalPages() == 0){
			return Response.failuer("�༶��ϢΪ��!");
		}
		Response resp = new Response();
		resp.addQuickPager(quickPager);
		return resp.toString();
	}
	
	@RequestMapping(value = "/clalist", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getClassList2(@RequestParam(value="currPage",required=false)String currPage,
				               @RequestParam(value="pageSize",required=false)String pageSize){
		QuickPager<Map<String,Object>> quickPager = new QuickPager<Map<String,Object>>(currPage,pageSize);
		this.classservice.getClassList2(quickPager);
		if(quickPager.getTotalPages() == 0){
			return Response.failuer("�༶��ϢΪ��!");
		}
		Response resp = new Response();
		resp.addQuickPager(quickPager);
		return resp.toString();
	}
	
	@RequestMapping(value = "/proclasslist", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getClassListByPro(@RequestParam(value = "profession")String profession){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list = this.classservice.getClassListByPro(profession);
		if(list == null){
			return Response.failuer("�༶��ϢΪ��!");
		}
		if(list.isEmpty()){
			return Response.failuer("�༶��ϢΪ��!");
		}
		Response resp = new Response();
		resp.addList("list", list);
		return resp.toString();
	}
	
	@RequestMapping(value = "/deleteclass", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteClass(@RequestParam(value = "id")String id){
		if(StringUtils.isEmpty(id)){
			return Response.failuer("ɾ����Ϣ��ȡʧ��!");
		}
		int x = this.classservice.deleteClass(id);
		if(x != 1){
			return Response.failuer("ɾ��ʧ��!");
		}
		return Response.success("ɾ���ɹ�!");
	}
	
	@RequestMapping(value = "/addclass", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addClass(@RequestParam(value = "data")String data){
		if(StringUtils.isEmpty(data)){
			return Response.failuer("�����ϢΪ��!");
		}
		int x = this.classservice.addClass(data);
		if(x == 0){
			return Response.failuer("���ʧ��!");
		}
		return Response.success("��ӳɹ�!");
	}
	
	@RequestMapping(value = "/modifyclass", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String modifyClass(@RequestParam(value ="id")String id,@RequestParam(value="name")String name){
		if(StringUtils.isEmpty(id)){
			return Response.failuer("�༶���Ϊ��!");
		}
		if(StringUtils.isEmpty(name)){
			return Response.failuer("�༶����Ϊ��!");
		}
		int x = this.classservice.modifyClass(id, name);
		if( x == 0 ){
			return Response.failuer("�޸�ʧ��!");
		}
		return Response.success("�޸ĳɹ�!");
	}
}
