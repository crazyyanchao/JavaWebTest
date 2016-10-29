package com.onlinetest.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onlinetest.common.QuickPager;
import com.onlinetest.common.Response;
import com.onlinetest.model.FillBlank;
import com.onlinetest.service.FillBlankService;

/**
 * @author �� ��
 *
 */
@Controller("FillBlankController")
@RequestMapping("/fillblank")
public class FillBlankController {

	@Resource(name = "FillBlankService")
	private FillBlankService fillblankservice;
	
	@RequestMapping(value = "/fillblanklist", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getFillBlankList(@RequestParam(value="currPage",required=false)String currPage,
				                   @RequestParam(value="pageSize",required=false)String pageSize,
				                   @RequestParam(value="course",required=false)String course,
								   @RequestParam(value="date",required=false)String dateTime,
				                   HttpServletRequest request){
		String userId = request.getSession().getAttribute("userId").toString();
		QuickPager<FillBlank> quickPager = new QuickPager<FillBlank>(currPage,pageSize);
		this.fillblankservice.getFillBlankList(quickPager,userId,course,dateTime);
		if(quickPager.getTotalPages() == 0 || quickPager.getData() == null){
			return Response.failuer("��Ŀ��ϢΪ��!");
		}
		Response resp = new Response();
		resp.addQuickPager(quickPager);
		return resp.toString();
	}
	/**
	 * �������ж���
	 * @param data
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addfillblank", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addFillBlank(@RequestParam(value = "data")String data,HttpServletRequest request){
		String userId = request.getSession().getAttribute("userId").toString();
		if(!this.fillblankservice.addFillBlank(data,userId)){
			return Response.failuer("���ʧ��!");
		}
		return Response.success("��ӳɹ�!");
	}
	
	@RequestMapping(value = "/modifyfillblank", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String modifyFillBlank(@RequestParam(value = "id")String id,
								  @RequestParam(value = "title")String title,
								  @RequestParam(value = "fixed")String fixed,
								  @RequestParam(value = "ans")String ans){
		int x = this.fillblankservice.modifyFillBlank(id, title, fixed, ans);
		if( x != 1){
			return Response.failuer("�޸�ʧ��!");
		}
		return Response.success("�޸ĳɹ�!");
	}
	
	@RequestMapping(value = "/deletefillblank", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteFillBlank(@RequestParam(value = "id")String id){
		int x = this.fillblankservice.deleteFillBlank(id);
		if(x != 1){
			return Response.failuer("ɾ��ʧ��!");
		}
		return Response.success("ɾ���ɹ�!");
	}
	
}
