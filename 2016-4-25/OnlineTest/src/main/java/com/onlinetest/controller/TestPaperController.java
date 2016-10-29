package com.onlinetest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onlinetest.common.QuickPager;
import com.onlinetest.common.Response;
import com.onlinetest.service.TestPaperService;
import com.onlinetest.util.StringUtils;

/**
 * @author �� ��
 *
 */
@Controller("TestPaperController")
@RequestMapping("/testpaper")
public class TestPaperController {

	@Resource(name = "TestPaperService")
	private TestPaperService testpaperservice;
	
	@RequestMapping(value = "/settestpaper", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String setTestPaper(@RequestParam(value = "data")String data,
							   HttpServletRequest request){
		String userId = String.valueOf( request.getSession().getAttribute("userId") );
		if(StringUtils.isEmpty(data)){
			return Response.failuer("�Ծ��趨����Ϊ��!");
		}
		int x = this.testpaperservice.setTestPaper(userId, data);
		if(x != 1){
			return Response.failuer("�Ծ��趨ʧ��!");
		}
		return Response.success("�Ծ��趨�ɹ�!");
	}
	
	@RequestMapping(value = "/testpaperlist", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getTestPaperList(HttpServletRequest request,
								   @RequestParam(value="currPage",required=false)String currPage,
						           @RequestParam(value="pageSize",required=false)String pageSize){
		QuickPager<Map<String,Object>> quickPager = new QuickPager<Map<String,Object>>(currPage,pageSize);
		String userId = String.valueOf( request.getSession().getAttribute("userId") );
		this.testpaperservice.getTestpaperList(quickPager, userId);
		if(quickPager.getTotalPages() == 0){
			return Response.failuer("��ǰ�Ծ��б�Ϊ��!");
		}
		Response resp = new Response();
		resp.addQuickPager(quickPager);
		return resp.toString();
	}
	
	@RequestMapping(value = "/deletetestpaper", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteTestPaper(@RequestParam(value = "id")String id){
		if(StringUtils.isEmpty(id)){
			return Response.failuer("��ȡ�����ʧ��!");
		}
		int x = this.testpaperservice.deleteTestPaper(id);
		if(x != 1){
			return Response.failuer("ɾ��ʧ��!");
		}
		return Response.success("ɾ���ɹ�!");
	}
	
	@RequestMapping(value = "/studenttests", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getStudentTests(HttpServletRequest request,
								  @RequestParam(value="currPage",required=false)String currPage,
								  @RequestParam(value="pageSize",required=false)String pageSize){
		QuickPager<Map<String,Object>> quickPager = new QuickPager<Map<String,Object>>(currPage,pageSize);
		String userId = request.getSession().getAttribute("userId").toString();
		this.testpaperservice.getStudentTests(quickPager, userId);
		if(quickPager.getTotalPages() == 0){
			return Response.failuer("������ϢΪ��!");
		}
		Response resp = new Response();
		resp.addQuickPager(quickPager);
		return resp.toString();
	}
	
	/**
	 * ��ѡ�� xx��Ŀ��xx�½ڵ���Ŀ����
	 */
	@RequestMapping(value = "/scount", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getSingleChooseCount(@RequestParam(value = "course")String course,
						@RequestParam(value = "chapter",required=false)String chapter){
		if(StringUtils.isEmpty(course)){
			return Response.failuer("�γ�Ϊ��!");
		}
		String count = this.testpaperservice.getSingleChooseCount(course, chapter);
		if(StringUtils.isEmpty(count)){
			return Response.failuer("��ѡ����Ϊ��!");
		}
		return Response.success(count);
	}
	
	/**
	 * ��ѡ�� xx��Ŀ��xx�½ڵ���Ŀ����
	 */
	@RequestMapping(value = "/mcount", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getMultiChooseCount(@RequestParam(value = "course")String course,
						@RequestParam(value = "chapter",required=false)String chapter){
		if(StringUtils.isEmpty(course)){
			return Response.failuer("�γ�Ϊ��!");
		}
		String count = this.testpaperservice.getMultiChooseCount(course, chapter);
		if(StringUtils.isEmpty(count)){
			return Response.failuer("��ѡ����Ϊ��!");
		}
		return Response.success(count);
	}
	
	/**
	 * ����� xx��Ŀ��xx�½ڵ���Ŀ����
	 */
	@RequestMapping(value = "/fcount", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getFillBlankCount(@RequestParam(value = "course")String course,
						@RequestParam(value = "chapter",required=false)String chapter){
		if(StringUtils.isEmpty(course)){
			return Response.failuer("�γ�Ϊ��!");
		}
		String count = this.testpaperservice.getFillBlankCount(course, chapter);
		if(StringUtils.isEmpty(count)){
			return Response.failuer("��ѡ����Ϊ��!");
		}
		return Response.success(count);
	}
	
	/**
	 * �ж��� xx��Ŀ��xx�½ڵ���Ŀ����
	 */
	@RequestMapping(value = "/tfcount", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getTFCount(@RequestParam(value = "course")String course,
						     @RequestParam(value = "chapter",required=false)String chapter){
		if(StringUtils.isEmpty(course)){
			return Response.failuer("�γ�Ϊ��!");
		}
		String count = this.testpaperservice.getTFCount(course, chapter);
		if(StringUtils.isEmpty(count)){
			return Response.failuer("��ѡ����Ϊ��!");
		}
		return Response.success(count);
	}
	
	/**
	 * �ʴ��� xx��Ŀ��xx�½ڵ���Ŀ����
	 */
	@RequestMapping(value = "/ecount", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getEssayQuestionCount(@RequestParam(value = "course")String course,
						@RequestParam(value = "chapter",required=false)String chapter){
		if(StringUtils.isEmpty(course)){
			return Response.failuer("�γ�Ϊ��!");
		}
		String count = this.testpaperservice.getEssayQuestionCount(course, chapter);
		if(StringUtils.isEmpty(count)){
			return Response.failuer("��ѡ����Ϊ��!");
		}
		return Response.success(count);
	}
	
	/**
	 * ��ȡͬһ��ѧԺ����ʦ
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getmarkers", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getMarkers(HttpServletRequest request){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = this.testpaperservice.getmarkers();
		if(list.isEmpty()){
			return Response.failuer("��ȡ��Ϣʧ��!");
		}
		Response resp = new Response();
		resp.addList("list", list);
		return resp.toString();
	}
}