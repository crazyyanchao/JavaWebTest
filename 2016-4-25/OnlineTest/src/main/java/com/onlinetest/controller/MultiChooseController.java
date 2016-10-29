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
 * @author 丁 鹏
 *
 */
@Controller("MultiChooseController")
@RequestMapping("/multichoose")
public class MultiChooseController {

	@Resource(name = "MultiChooseService")
	private MultiChooseService multichooseservice;
	
	/**
	 * 多选题 列表
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
			return Response.failuer("多选题信息为空!");
		}
		Response resp = new Response();
		resp.addQuickPager(quickPager);
		return resp.toString();
	}
	
	/**
	 * 添加多选题
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/addmultichoose", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addMultiChoose(@RequestParam(value = "data")String data,HttpServletRequest request){
		if(StringUtils.isEmpty(data)){
			return Response.failuer("添加数据为空!");
		}
		String userId = request.getSession().getAttribute("userId").toString();
		if(!this.multichooseservice.addMultiChoose(data,userId)){
			return Response.failuer("添加失败!");
		}
		return Response.success("添加成功!");
	}
	
	/**
	 * 删除多选题
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deletemultichoose", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteMultiChoose(@RequestParam(value = "id")String id){
		if(!this.multichooseservice.deleteMultiChoose(id)){
			return Response.failuer("删除失败!");
		}
		return Response.success("删除成功!");
	}
	
	/**
	 * 修改多选题
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
			return Response.failuer("有空数据，请填完整后再试");
		}
		int x = this.multichooseservice.modifyMultiChoose(id, title, ans1, ans2, ans3, ans4, ans5, ans6, tans);
		if(x != 1){
			return Response.failuer("修改失败!");
		}
		return Response.success("修改成功!");
	}
	
}
