package com.onlinetest.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onlinetest.common.QuickPager;
import com.onlinetest.common.Response;
import com.onlinetest.model.SingleChoose;
import com.onlinetest.service.SingleChooseService;

/**
 * @author 丁 鹏
 *
 */
@Controller("SingleChooseController")
@RequestMapping("/singlechoose")
public class SingleChooseController {

	@Resource(name = "SingleChooseService")
	private SingleChooseService singlechooseservice;
	
	/**
	 * 单选题列表
	 * @return
	 */
	@RequestMapping(value = "/singlechooselist" , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getSingleChoose(@RequestParam(value="currPage",required=false)String currPage,
								  @RequestParam(value="pageSize",required=false)String pageSize,
								  @RequestParam(value="course",required=false)String course,
								  @RequestParam(value="date",required=false)String dateTime,
								  HttpServletRequest request){
		String userId = request.getSession().getAttribute("userId").toString();
		QuickPager<SingleChoose> quickPager = new QuickPager<SingleChoose>(currPage,pageSize);
		this.singlechooseservice.getSingleChooseList(quickPager,userId,course,dateTime);
		if(quickPager.getData() == null || quickPager.getTotalRows() == 0){
			return Response.failuer("单选题题信息为空!");
		}
		Response resp = new Response();
		resp.addQuickPager(quickPager);
		return resp.toString();
	}
	
	/**
	 * 添加单选题
	 * @param addJsonData
	 * @return
	 */
	@RequestMapping(value = "/addsinglechoose", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addSingleChoose(@RequestParam(value = "addJsonData")String addJsonData,
								  HttpServletRequest request){
		String userId = request.getSession().getAttribute("userId").toString();
		if(this.singlechooseservice.addSingleChoose(addJsonData,userId) == false){
			return Response.failuer("添加失败!");
		}
		return Response.success("添加成功!");
	}
	
	/**
	 * 修改单选题
	 * @param id
	 * @param title
	 * @param ans1
	 * @param ans2
	 * @param ans3
	 * @param ans4
	 * @param trueans
	 * @return
	 */
	@RequestMapping(value = "modifysinglechoose", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String modifySingleChoose(@RequestParam(value = "id")String id,
			@RequestParam(value = "title")String title,@RequestParam(value = "ans1")String ans1,
			@RequestParam(value = "ans2")String ans2,@RequestParam(value = "ans3")String ans3,
			@RequestParam(value = "ans4")String ans4,@RequestParam(value = "trueans")String trueans){
		//System.out.println(id+"---"+title+"---"+ans1+"---"+ans2+"---"+ans3+"---"+ans4+"---"+trueans);
		int r = this.singlechooseservice.modifySingleChose(id, title, ans1, ans2, ans3, ans4, trueans);
		if( r == 1){
			return Response.success("修改成功!");
		}
		return Response.failuer("修改失败!");
	}
	
	/**
	 * 删除单选题
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deletesinglechoose", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteSingleChoose(@RequestParam(value = "id")String id){
		int r = this.singlechooseservice.deleteSingleChoose(id);
		if(r == 1){
			return Response.success("删除成功!");
		}
		return Response.failuer("删除失败!");
	}
}
