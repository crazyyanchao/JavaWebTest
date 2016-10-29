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
 * @author 丁 鹏
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
			return Response.failuer("学院信息为空!");
		}
		Response resp = new Response();
		resp.addList("list", list);
		return resp.toString();
	}
	
	@RequestMapping(value = "/addacademy", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addAcademy(@RequestParam(value = "data")String data){
		if(StringUtils.isEmpty(data)){
			return Response.failuer("添加信息为空!");
		}
		int x = this.academyservice.addAcademy(data);
		if(x == -1){
			return Response.failuer("学院或缩写已存在!");
		}else if(x == 0){
			return Response.failuer("添加失败!");
		}
		return Response.success("添加成功!");
	}
	
	@RequestMapping(value = "/deleteacademy", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteAcademy(@RequestParam(value = "code")String code){
		if(StringUtils.isEmpty(code)){
			return Response.failuer("删除学院为空，请刷新后尝试!");
		}
		int x = this.academyservice.deleteAcademy(code);
		if(x == 0){
			return Response.failuer("删除失败!");
		}
		return Response.success("删除成功!");
	}
	
	/**
	 * 系列表
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/departmentlist", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getDepartmentList(@RequestParam(value = "code",required=false)String code){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = this.academyservice.getDepartmentList(code);
		if(list.isEmpty()){
			return Response.failuer("科系信息为空!");
		}
		Response resp = new Response();
		resp.addList("list", list);
		return resp.toString();
	}
	
	@RequestMapping(value = "/adddepartment", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addDepartment(@RequestParam(value = "data")String data){
		if(StringUtils.isEmpty(data)){
			return Response.failuer("添加信息为空!");
		}
		int x = this.academyservice.addDepartment(data);
		if(x == -1){
			return Response.failuer("科系或科系缩写已存在!");
		}else if(x == 0){
			return Response.failuer("添加失败!");
		}
		return Response.success("添加成功!");
	}
	
	@RequestMapping(value = "/deletedepartment", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteDepartment(@RequestParam(value = "code")String code){
		if(StringUtils.isEmpty(code)){
			return Response.failuer("删除科系为空，请刷新后尝试!");
		}
		int x = this.academyservice.deleteDepartment(code);
		if(x == 0){
			return Response.failuer("删除失败!");
		}
		return Response.success("删除成功!");
	}
}
