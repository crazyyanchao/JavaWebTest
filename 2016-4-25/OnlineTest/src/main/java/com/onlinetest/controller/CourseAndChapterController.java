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
import com.onlinetest.service.CourseAndChapterService;
import com.onlinetest.util.StringUtils;

/**
 * @author �� ��
 *
 */
@Controller("CourseAndChapterController")
@RequestMapping("/courseandchapter")
public class CourseAndChapterController {

	@Resource(name = "CourseAndChapterService")
	private CourseAndChapterService courseandchapterservice;
	
	/**
	 * ���ݸ������ַ���ģ������
	 * @param value
	 * @return
	 */
	@RequestMapping(value = "/searchcourse", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String searchCourse(@RequestParam(value = "value")String value){
		if(StringUtils.isEmpty(value)){
			return Response.failuer("��������Ϊ��!");
		}
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = this.courseandchapterservice.searchCourse(value);
		if(list.size() == 0){
			return Response.failuer("û�в��ҵ�����Ҫ�Ŀγ�!");
		}
		Response response = new Response();
		response.addList("list", list);
		return response.toJson();
	}
	
	/**
	 * ���ݿγ̱��������½�
	 * @param courseCode
	 * @return
	 */
	@RequestMapping(value = "/getchapter", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getChapter(@RequestParam(value = "courseCode")String courseCode){
		if(StringUtils.isEmpty(courseCode)){
			return Response.failuer("�γ�Ϊ��!");
		}
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = this.courseandchapterservice.getChapter(courseCode);
		if(list.size() == 0){
			return Response.failuer("û�в��ҵ�����Ҫ�Ŀγ�!");
		}
		Response response = new Response();
		response.addList("list", list);
		return response.toString();
	}
	
	/**
	 * �γ��б�
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/courselist", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String courseList(@RequestParam(value="currPage",required=false)String currPage,
			  				 @RequestParam(value="pageSize",required=false)String pageSize){
		QuickPager<Map<String,Object>> quickPager = new QuickPager<Map<String,Object>>(currPage,pageSize);
		this.courseandchapterservice.courseList(quickPager);
		if(quickPager.getTotalRows()== 0 || quickPager.getData() == null){
			return Response.failuer("�γ���Ϣ�б�Ϊ��!");
		}
		Response response = new Response();
		response.addQuickPager(quickPager);
		return response.toString();
	}
	
	/**
	 * ��ӿγ�
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/addCourse", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addCourse(@RequestParam(value = "data")String data){
		Boolean r = this.courseandchapterservice.addCourse(data);
		if(!r){
			return Response.failuer("���ʧ��!");
		}
		return Response.success("��ӳɹ�!");
	}
	
//	/**
//	 * ѧԺ����ʾ
//	 * @return
//	 */
//	@RequestMapping(value = "/getAcademyCodeValue", produces = "application/json;charset=UTF-8")
//	@ResponseBody
//	public String getAbbreviation(){
//		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//		list = this.courseandchapterservice.getAcademyCodeValue();
//		if(list.isEmpty()){
//			return Response.failuer("��ȡԺϵ��дʧ��!");
//		}
//		Response resp = new Response();
//		resp.addList("list", list);
//		return resp.toString();
//	}
	
	/**
	 * ɾ���γ�
	 * @param courseCode
	 * @return
	 */
	@RequestMapping(value = "/deletecourse", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteCourse(@RequestParam(value = "courseCode")String courseCode){
		if(this.courseandchapterservice.deleteCourse(courseCode)){
			return Response.success("ɾ���ɹ�!");
		}
		return Response.failuer("ɾ��ʧ��!");
	}
	
	/**
	 * �½��б�
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/chapterlist", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String chapterList(@RequestParam(value="currPage",required=false)String currPage,
			  				  @RequestParam(value="pageSize",required=false)String pageSize,
			  				  @RequestParam(value="courseCode",required=false)String courseCode){
		QuickPager<Map<String,Object>> quickPager = new QuickPager<Map<String,Object>>(currPage,pageSize);
		this.courseandchapterservice.chapterList(quickPager, courseCode);
		if(quickPager.getTotalPages() == 0){
			return Response.failuer("�ÿγ�û��¼���½�!");
		}
		Response response = new Response();
		response.addQuickPager(quickPager);
		return response.toString();
	}
	
	/**
	 * ����½���Ϣ
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/addChapter", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addChapter(@RequestParam(value = "data")String data){
		Boolean r = this.courseandchapterservice.addChapter(data);
		if(!r){
			return Response.failuer("���ʧ��!");
		}
		return Response.success("��ӳɹ�!");
	}
	
	/**
	 * �޸��½�Ŀ¼��Ϣ
	 * @param chapterValue
	 * @param courseCode
	 * @param chapterCode
	 * @return
	 */
	@RequestMapping(value = "/modifychapter", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String modifyChapter(@RequestParam(value = "chapterValue")String chapterValue,
							    @RequestParam(value = "courseCode")String courseCode,
							    @RequestParam(value = "chapterCode")String chapterCode){
		if( this.courseandchapterservice.modifyChapter(chapterValue, courseCode, chapterCode) ){
			return Response.success("�޸ĳɹ�!");
		}
		return Response.failuer("�޸�ʧ��!");
	}
	
	/**
	 * ɾ���½�
	 * @param courseCode
	 * @param chapterCode
	 * @return
	 */
	@RequestMapping(value = "/deletechapter", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteChapter(@RequestParam(value = "courseCode")String courseCode,
							    @RequestParam(value = "chapterCode")String chapterCode){
		if( this.courseandchapterservice.deleteChapter(courseCode, chapterCode) ){
			return Response.success("ɾ���ɹ�!");
		}
		return Response.failuer("ɾ��ʧ��!");
	}
	
}
