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
 * @author �� ��
 *
 */
@Controller("TeacherCourseController")
@RequestMapping("/teachercourse")
public class TeacherCourseController {
	
	@Resource(name = "TeacherCourseService")
	private TeacherCourseService teachercourseservice;
	
	/**
	 * ������ѡ��ʦ�γ�
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/courselistfilter", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getCourseListForFilter(HttpServletRequest request){
		String userId = request.getSession().getAttribute("userId").toString();
		if(StringUtils.isEmpty(userId)){
			return Response.failuer("�û���ϢΪ��!");
		}
		List<Map<String,Object>> list = this.teachercourseservice.getCourseListForFilter(userId);
		if(list == null){
			return Response.failuer("���ڿγ�Ϊ��!");
		}
		if(list.isEmpty()){
			return Response.failuer("���ڿγ�Ϊ��!");
		}
		Response resp = new Response();
		resp.addList("list", list);
		return resp.toString();
	}
	
	/**
	 * ��ʦ���ڿγ��б�
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/courselist", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getCourseList(HttpServletRequest request){
		String userId = request.getSession().getAttribute("userId").toString();
		if(StringUtils.isEmpty(userId)){
			return Response.failuer("�û���ϢΪ��!");
		}
		List<Map<String,Object>> list = this.teachercourseservice.getCourseList(userId);
		if(list == null){
			return Response.failuer("���ڿγ�Ϊ��!");
		}
		if(list.isEmpty()){
			return Response.failuer("���ڿγ�Ϊ��!");
		}
		Response resp = new Response();
		resp.addList("list", list);
		return resp.toString();
	}
	
	/**
	 * ��ӽ�ʦ���ڿγ�
	 * @param data
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addcourse", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addCourse(@RequestParam(value = "data")String data,HttpServletRequest request){
		String userId = request.getSession().getAttribute("userId").toString();
		if(StringUtils.isEmpty(data)){
			return Response.failuer("�������Ϊ��!");
		}
		int x = this.teachercourseservice.addCourse(userId, data);
		if(x != 1){
			return Response.failuer("���ʧ��!");
		}
		return Response.success("��ӳɹ�!");
	}
	
	/**
	 * ɾ�����ڿγ�
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
			return Response.failuer("�γ�Ϊ��!");
		}else if(x == 0){
			return Response.failuer("ɾ��ʧ��!");
		}
		return Response.success("ɾ���ɹ�!");
	}
}
