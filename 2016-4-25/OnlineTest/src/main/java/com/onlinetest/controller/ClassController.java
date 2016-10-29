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
 * @author 丁 鹏
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
			return Response.failuer("班级信息为空!");
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
			return Response.failuer("班级信息为空!");
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
			return Response.failuer("班级信息为空!");
		}
		if(list.isEmpty()){
			return Response.failuer("班级信息为空!");
		}
		Response resp = new Response();
		resp.addList("list", list);
		return resp.toString();
	}
	
	@RequestMapping(value = "/deleteclass", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteClass(@RequestParam(value = "id")String id){
		if(StringUtils.isEmpty(id)){
			return Response.failuer("删除信息获取失败!");
		}
		int x = this.classservice.deleteClass(id);
		if(x != 1){
			return Response.failuer("删除失败!");
		}
		return Response.success("删除成功!");
	}
	
	@RequestMapping(value = "/addclass", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addClass(@RequestParam(value = "data")String data){
		if(StringUtils.isEmpty(data)){
			return Response.failuer("添加信息为空!");
		}
		int x = this.classservice.addClass(data);
		if(x == 0){
			return Response.failuer("添加失败!");
		}
		return Response.success("添加成功!");
	}
	
	@RequestMapping(value = "/modifyclass", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String modifyClass(@RequestParam(value ="id")String id,@RequestParam(value="name")String name){
		if(StringUtils.isEmpty(id)){
			return Response.failuer("班级编号为空!");
		}
		if(StringUtils.isEmpty(name)){
			return Response.failuer("班级名称为空!");
		}
		int x = this.classservice.modifyClass(id, name);
		if( x == 0 ){
			return Response.failuer("修改失败!");
		}
		return Response.success("修改成功!");
	}
}
