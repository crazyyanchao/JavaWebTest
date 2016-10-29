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
 * @author 丁 鹏
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
			return Response.failuer("试卷设定参数为空!");
		}
		int x = this.testpaperservice.setTestPaper(userId, data);
		if(x != 1){
			return Response.failuer("试卷设定失败!");
		}
		return Response.success("试卷设定成功!");
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
			return Response.failuer("当前试卷列表为空!");
		}
		Response resp = new Response();
		resp.addQuickPager(quickPager);
		return resp.toString();
	}
	
	@RequestMapping(value = "/deletetestpaper", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteTestPaper(@RequestParam(value = "id")String id){
		if(StringUtils.isEmpty(id)){
			return Response.failuer("获取试题编失败!");
		}
		int x = this.testpaperservice.deleteTestPaper(id);
		if(x != 1){
			return Response.failuer("删除失败!");
		}
		return Response.success("删除成功!");
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
			return Response.failuer("考试信息为空!");
		}
		Response resp = new Response();
		resp.addQuickPager(quickPager);
		return resp.toString();
	}
	
	/**
	 * 单选题 xx科目、xx章节的题目数量
	 */
	@RequestMapping(value = "/scount", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getSingleChooseCount(@RequestParam(value = "course")String course,
						@RequestParam(value = "chapter",required=false)String chapter){
		if(StringUtils.isEmpty(course)){
			return Response.failuer("课程为空!");
		}
		String count = this.testpaperservice.getSingleChooseCount(course, chapter);
		if(StringUtils.isEmpty(count)){
			return Response.failuer("可选数量为空!");
		}
		return Response.success(count);
	}
	
	/**
	 * 多选题 xx科目、xx章节的题目数量
	 */
	@RequestMapping(value = "/mcount", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getMultiChooseCount(@RequestParam(value = "course")String course,
						@RequestParam(value = "chapter",required=false)String chapter){
		if(StringUtils.isEmpty(course)){
			return Response.failuer("课程为空!");
		}
		String count = this.testpaperservice.getMultiChooseCount(course, chapter);
		if(StringUtils.isEmpty(count)){
			return Response.failuer("可选数量为空!");
		}
		return Response.success(count);
	}
	
	/**
	 * 填空题 xx科目、xx章节的题目数量
	 */
	@RequestMapping(value = "/fcount", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getFillBlankCount(@RequestParam(value = "course")String course,
						@RequestParam(value = "chapter",required=false)String chapter){
		if(StringUtils.isEmpty(course)){
			return Response.failuer("课程为空!");
		}
		String count = this.testpaperservice.getFillBlankCount(course, chapter);
		if(StringUtils.isEmpty(count)){
			return Response.failuer("可选数量为空!");
		}
		return Response.success(count);
	}
	
	/**
	 * 判断题 xx科目、xx章节的题目数量
	 */
	@RequestMapping(value = "/tfcount", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getTFCount(@RequestParam(value = "course")String course,
						     @RequestParam(value = "chapter",required=false)String chapter){
		if(StringUtils.isEmpty(course)){
			return Response.failuer("课程为空!");
		}
		String count = this.testpaperservice.getTFCount(course, chapter);
		if(StringUtils.isEmpty(count)){
			return Response.failuer("可选数量为空!");
		}
		return Response.success(count);
	}
	
	/**
	 * 问答题 xx科目、xx章节的题目数量
	 */
	@RequestMapping(value = "/ecount", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getEssayQuestionCount(@RequestParam(value = "course")String course,
						@RequestParam(value = "chapter",required=false)String chapter){
		if(StringUtils.isEmpty(course)){
			return Response.failuer("课程为空!");
		}
		String count = this.testpaperservice.getEssayQuestionCount(course, chapter);
		if(StringUtils.isEmpty(count)){
			return Response.failuer("可选数量为空!");
		}
		return Response.success(count);
	}
	
	/**
	 * 获取同一个学院的老师
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getmarkers", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getMarkers(HttpServletRequest request){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = this.testpaperservice.getmarkers();
		if(list.isEmpty()){
			return Response.failuer("获取信息失败!");
		}
		Response resp = new Response();
		resp.addList("list", list);
		return resp.toString();
	}
}