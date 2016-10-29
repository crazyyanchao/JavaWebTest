package com.onlinetest.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onlinetest.common.QuickPager;
import com.onlinetest.common.Response;
import com.onlinetest.model.EssayQuestion;
import com.onlinetest.service.EssayQuestionService;
import com.onlinetest.util.StringUtils;

/**
 * @author �� ��
 *
 */
@Controller("EssayQuestionController")
@RequestMapping("/essayquestion")
public class EssayQuestionController {

	@Resource(name = "EssayQuestionService")
	private EssayQuestionService essayquestionservice;
	
	/**
	 * �ʴ����б�
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/essayquestionlist", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getEssayQuestionList(@RequestParam(value="currPage",required=false)String currPage,
									   @RequestParam(value="pageSize",required=false)String pageSize,
									   @RequestParam(value="course",required=false)String course,
									   @RequestParam(value="date",required=false)String dateTime,
									   HttpServletRequest request){
		String userId = request.getSession().getAttribute("userId").toString();
		QuickPager<EssayQuestion> quickPager = new QuickPager<EssayQuestion>(currPage,pageSize);
		this.essayquestionservice.getEssayQuestionList(quickPager,userId,course,dateTime);
		if(quickPager.getTotalPages() == 0 || quickPager.getData() == null){
			return Response.failuer("�ʴ�����ϢΪ��!");
		}
		Response resp = new Response();
		resp.addQuickPager(quickPager);
		return resp.toString();
	}
	/**
	 * ����ʴ���
	 * @param data
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addessayquestion", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addEssayQuestion(@RequestParam(value = "data")String data,HttpServletRequest request){
		String userId = request.getSession().getAttribute("userId").toString();
		if(StringUtils.isEmpty(data)){
			return Response.failuer("�������Ϊ��!");
		}
		if(!this.essayquestionservice.addEssayQuestion(data,userId)){
			return Response.failuer("���ʧ��!");
		}
		return Response.success("��ӳɹ�!");
	}
	
	@RequestMapping(value = "/modifyessayquestion", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String modifyEssayQuestion(@RequestParam(value = "id")String id,
									  @RequestParam(value = "title")String title,
									  @RequestParam(value = "ans")String ans){
		if(StringUtils.isEmpty(id)){
			return Response.failuer("���Ϊ��!");
		}
		if(StringUtils.isEmpty(title)){
			return Response.failuer("��ĿΪ��!");
		}
		if(StringUtils.isEmpty(ans)){
			return Response.failuer("��Ϊ��!");
		}
		if( this.essayquestionservice.modifyEssayQuestion(id, title, ans) != 1 ){
			return Response.failuer("�޸�ʧ��!");
		}
		return Response.success("�޸ĳɹ�!");
	}
	
	@RequestMapping(value = "/deleteessayquestion", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteEssayQuestion(@RequestParam(value = "id")String id){
		if(StringUtils.isEmpty(id)){
			return Response.failuer("���Ϊ��!");
		}
		if(this.essayquestionservice.deleteEssayQuestion(id) != 1){
			return Response.failuer("ɾ��ʧ��!");
		}
		return Response.success("ɾ���ɹ�!");
	}
}
