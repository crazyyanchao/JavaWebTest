package com.onlinetest.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onlinetest.common.Response;
import com.onlinetest.service.TeacherCourseService;
import com.onlinetest.util.StringUtils;

/**
 * @author 丁 鹏
 *
 */
@Controller("TeacherCourseController")
@RequestMapping("/teachercourse")
public class TeacherCourseController {
	
	@Resource(name = "TeacherCourseService")
	private TeacherCourseService teachercourseservice;
	
	/**
	 * 用于挑选教师课程
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/courselistfilter", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getCourseListForFilter(HttpServletRequest request){
		String userId = request.getSession().getAttribute("userId").toString();
		if(StringUtils.isEmpty(userId)){
			return Response.failuer("用户信息为空!");
		}
		List<Map<String,Object>> list = this.teachercourseservice.getCourseListForFilter(userId);
		if(list == null){
			return Response.failuer("所授课程为空!");
		}
		if(list.isEmpty()){
			return Response.failuer("所授课程为空!");
		}
		Response resp = new Response();
		resp.addList("list", list);
		return resp.toString();
	}
	
	/**
	 * 教师所授课程列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/courselist", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getCourseList(HttpServletRequest request){
		String userId = request.getSession().getAttribute("userId").toString();
		if(StringUtils.isEmpty(userId)){
			return Response.failuer("用户信息为空!");
		}
		List<Map<String,Object>> list = this.teachercourseservice.getCourseList(userId);
		if(list == null){
			return Response.failuer("所授课程为空!");
		}
		if(list.isEmpty()){
			return Response.failuer("所授课程为空!");
		}
		Response resp = new Response();
		resp.addList("list", list);
		return resp.toString();
	}
	
	/**
	 * 添加教师所授课程
	 * @param data
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addcourse", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addCourse(@RequestParam(value = "data")String data,HttpServletRequest request){
		String userId = request.getSession().getAttribute("userId").toString();
		if(StringUtils.isEmpty(data)){
			return Response.failuer("添加数据为空!");
		}
		int x = this.teachercourseservice.addCourse(userId, data);
		if(x != 1){
			return Response.failuer("添加失败!");
		}
		return Response.success("添加成功!");
	}
	
	/**
	 * 删除所授课程
	 * @param num
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deletecourse", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteCourse(@RequestParam(value = "num")String num,HttpServletRequest request){
		String userId = request.getSession().getAttribute("userId").toString();
		int x = this.teachercourseservice.deleteCourse(num, userId);
		if(x == -1){
			return Response.failuer("课程为空!");
		}else if(x == 0){
			return Response.failuer("删除失败!");
		}
		return Response.success("删除成功!");
	}
}
