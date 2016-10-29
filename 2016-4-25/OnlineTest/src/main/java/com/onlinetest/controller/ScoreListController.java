package com.onlinetest.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onlinetest.common.QuickPager;
import com.onlinetest.common.Response;
import com.onlinetest.service.ScoreListService;

/**
 * @author 丁 鹏
 *
 */
@Controller("ScoreListController")
@RequestMapping("/score")
public class ScoreListController {

	@Resource(name = "ScoreListService")
	private ScoreListService scoreservice;

	@RequestMapping(value = "/scorelist", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getScoreList(@RequestParam(value = "course",required=false)String course,
							   @RequestParam(value = "stuCode",required=false)String stuCode,
							   @RequestParam(value = "date",required=false)String date,
							   @RequestParam(value="currPage",required=false)String currPage,
				               @RequestParam(value="pageSize",required=false)String pageSize,
				               HttpServletRequest request){
		String userId = request.getSession().getAttribute("userId").toString();
		QuickPager<Map<String,Object>> quickPager = new QuickPager<Map<String,Object>>(currPage,pageSize);
		this.scoreservice.getScoreListOfAll(userId, course, stuCode, date, quickPager);
		if(quickPager.getData() == null || quickPager.getData().size() == 0 ){
			return Response.failuer("列表信息为空!");
		}
		Response resp = new Response();
		resp.addQuickPager(quickPager);
		return resp.toString();
	}
}
