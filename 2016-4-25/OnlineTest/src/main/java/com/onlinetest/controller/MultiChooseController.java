package com.onlinetest.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onlinetest.common.QuickPager;
import com.onlinetest.common.Response;
import com.onlinetest.model.MultiChoose;
import com.onlinetest.service.MultiChooseService;
import com.onlinetest.util.StringUtils;


/**
 * @author �� ��
 *
 */
@Controller("MultiChooseController")
@RequestMapping("/multichoose")
public class MultiChooseController {

	@Resource(name = "MultiChooseService")
	private MultiChooseService multichooseservice;
	
	/**
	 * ��ѡ�� �б�
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/multichooselist", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getMultiChooseList(@RequestParam(value="currPage",required=false)String currPage,
			  						 @RequestParam(value="pageSize",required=false)String pageSize,
			  						 @RequestParam(value="course",required=false)String course,
									 @RequestParam(value="date",required=false)String dateTime,
			  						 HttpServletRequest request){
		String userId = request.getSession().getAttribute("userId").toString();
		QuickPager<MultiChoose> quickPager = new QuickPager<MultiChoose>(currPage,pageSize);
		this.multichooseservice.getMultiChooseList(quickPager,userId,course,dateTime);
		if(quickPager.getTotalPages() == 0 || quickPager.getData() == null){
			return Response.failuer("��ѡ����ϢΪ��!");
		}
		Response resp = new Response();
		resp.addQuickPager(quickPager);
		return resp.toString();
	}
	
	/**
	 * ��Ӷ�ѡ��
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/addmultichoose", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addMultiChoose(@RequestParam(value = "data")String data,HttpServletRequest request){
		if(StringUtils.isEmpty(data)){
			return Response.failuer("�������Ϊ��!");
		}
		String userId = request.getSession().getAttribute("userId").toString();
		if(!this.multichooseservice.addMultiChoose(data,userId)){
			return Response.failuer("���ʧ��!");
		}
		return Response.success("��ӳɹ�!");
	}
	
	/**
	 * ɾ����ѡ��
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deletemultichoose", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteMultiChoose(@RequestParam(value = "id")String id){
		if(!this.multichooseservice.deleteMultiChoose(id)){
			return Response.failuer("ɾ��ʧ��!");
		}
		return Response.success("ɾ���ɹ�!");
	}
	
	/**
	 * �޸Ķ�ѡ��
	 * @param id
	 * @param title
	 * @param ans1
	 * @param ans2
	 * @param ans3
	 * @param ans4
	 * @param ans5
	 * @param ans6
	 * @param tans
	 * @return
	 */
	@RequestMapping(value = "/modifymultichoose", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String modifyMultiChoose(@RequestParam(value = "id")String id,@RequestParam(value = "title")String title,
									@RequestParam(value = "ans1")String ans1,@RequestParam(value = "ans2")String ans2,
									@RequestParam(value = "ans3")String ans3,@RequestParam(value = "ans4")String ans4,
									@RequestParam(value = "ans5")String ans5,@RequestParam(value = "ans6")String ans6,
									@RequestParam(value = "tans")String tans){
		if(StringUtils.isEmpty(id) || StringUtils.isEmpty(title) || StringUtils.isEmpty(ans1) || StringUtils.isEmpty(ans2) || StringUtils.isEmpty(ans3) || StringUtils.isEmpty(tans) ){
			return Response.failuer("�п����ݣ���������������");
		}
		int x = this.multichooseservice.modifyMultiChoose(id, title, ans1, ans2, ans3, ans4, ans5, ans6, tans);
		if(x != 1){
			return Response.failuer("�޸�ʧ��!");
		}
		return Response.success("�޸ĳɹ�!");
	}
	
}
