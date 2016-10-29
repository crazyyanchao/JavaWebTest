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

import com.onlinetest.common.MyRandom;
import com.onlinetest.common.QuickPager;
import com.onlinetest.common.Response;
import com.onlinetest.service.TestService;
import com.onlinetest.util.StringUtils;

/**
 * @author 丁 鹏
 *
 */
@Controller("TestController")
@RequestMapping("/testonline")
public class TestController {
	
	@Resource(name = "TestService")
	private TestService testservice;
	
	@RequestMapping(value = "/startTest", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String startTest(HttpServletRequest request){
		String testPaperId = String.valueOf(request.getSession().getAttribute("testPaperId"));
		Map<String,Object> map = this.testservice.getTestInfo(testPaperId);
		String course = map.get("course").toString();
		String chapter = map.get("chapter").toString();
		String fixed = map.get("fixed").toString();
		String time = map.get("time").toString();
		MyRandom mr = new MyRandom();
		String SC = map.get("sc").toString();
		String MC = map.get("mc").toString();
		String TF = map.get("tf").toString();
		String FB = map.get("fb").toString();
		String EQ = map.get("eq").toString();
		String[] sc = null;
		String[] mc = null;
		String[] tf = null;
		String[] fb = null;
		String[] eq = null;
		List<Map<String,Object>> singleChoose = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> multiChoose = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> TFQuestion = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> fillBlank = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> essayQuestion = new ArrayList<Map<String,Object>>();
		List<String> ids = new ArrayList<String>();
		if(fixed.equals("4002")){
			if(!StringUtils.isEmpty(SC)){
				sc = mr.getRan(SC);
				singleChoose = this.testservice.getSingleChoose(course, chapter, sc);
			}
			if(!StringUtils.isEmpty(MC)){
				mc = mr.getRan(MC);
				multiChoose = this.testservice.getMultiChoose(course, chapter, mc);
			}
			if(!StringUtils.isEmpty(TF)){
				tf = mr.getRan(TF);
				TFQuestion = this.testservice.getTFQuestion(course, chapter, tf);
			}
			if(!StringUtils.isEmpty(FB)){
				fb = mr.getRan(FB);
				fillBlank = this.testservice.getFillBlank(course, chapter, fb);
			}
			if(!StringUtils.isEmpty(EQ)){
				eq = mr.getRan(EQ);
				essayQuestion = this.testservice.getEssayQuestion(course, chapter, eq);
			}
		}else{
			if(!StringUtils.isEmpty(SC)){
				ids.clear();
				sc = SC.split("-");
				for (String string : sc) {
					ids.add(string);
				}
				singleChoose = this.testservice.getSingleChooseFixed(ids);
			}
			if(!StringUtils.isEmpty(MC)){
				ids.clear();
				mc = MC.split("-");
				for (String string : mc) {
					ids.add(string);
				}
				multiChoose = this.testservice.getMultiChooseFixed(ids);
			}
			if(!StringUtils.isEmpty(TF)){
				ids.clear();
				tf = TF.split("-");
				for (String string : tf) {
					ids.add(string);
				}
				TFQuestion = this.testservice.getTFFixed(ids);
			}
			if(!StringUtils.isEmpty(FB)){
				ids.clear();
				fb = FB.split("-");
				for (String string : fb) {
					ids.add(string);
				}
				fillBlank = this.testservice.getFillBlankFixed(ids);
			}
			if(!StringUtils.isEmpty(EQ)){
				ids.clear();
				eq = EQ.split("-");
				for (String string : eq) {
					ids.add(string);
				}
				essayQuestion = this.testservice.getEssayQuestionFixed(ids);
			}
		}
		Response resp = new Response();
		resp.addString("testPaperStyle", fixed);
		resp.addString("time", time);
		if(!singleChoose.isEmpty()){
			resp.addList("single", singleChoose);
		}
		if(!multiChoose.isEmpty()){
			resp.addList("multi", multiChoose);
		}
		if(!TFQuestion.isEmpty()){
			resp.addList("TF", TFQuestion);
		}
		if(!fillBlank.isEmpty()){
			resp.addList("fill", fillBlank);
		}
		if(!essayQuestion.isEmpty()){
			resp.addList("essay", essayQuestion);
		}
		if(singleChoose.isEmpty() && multiChoose.isEmpty() && fillBlank.isEmpty() && essayQuestion.isEmpty()){
			return Response.failuer("获取试题失败,请与老师联系!");
		}
		return resp.toString();
	}
	
	@RequestMapping(value = "/subTest", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String subPaper(@RequestParam(value = "singledata")String singledata,
						   @RequestParam(value = "multidata")String multidata,
						   @RequestParam(value = "tfdata")String tfdata,
						   @RequestParam(value = "filldata")String filldata,
						   @RequestParam(value = "essaydata")String essaydata,
						   HttpServletRequest request){
		if(StringUtils.isEmpty(singledata) && StringUtils.isEmpty(multidata) && StringUtils.isEmpty(filldata) && StringUtils.isEmpty(essaydata)){
			return Response.failuer("试卷信息获取失败!");
		}
		String userId = request.getSession().getAttribute("userId").toString();
		String testpaperId = request.getSession().getAttribute("testPaperId").toString();
		int x = this.testservice.subPaper(userId, testpaperId, singledata, multidata, tfdata, filldata, essaydata);
		if(x != 1){
			return Response.failuer("提交失败，请联系老师!");
		}
		return Response.success("提交成功!");
	}
	
	/**
	 * 获取考试列表  markPaper考试列表
	 * @return
	 */
	@RequestMapping(value = "/testslist", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getTestsList(HttpServletRequest request,
							   @RequestParam(value="class",required=false)String cla,
							   @RequestParam(value="course",required=false)String course,
							   @RequestParam(value="num",required=false)String num,
							   @RequestParam(value="currPage",required=false)String currPage,
							   @RequestParam(value="pageSize",required=false)String pageSize){
		String userId = request.getSession().getAttribute("userId").toString();
		QuickPager<Map<String,Object>> quickPager = new QuickPager<Map<String,Object>>(currPage,pageSize);
		this.testservice.getTestsList(userId, quickPager, cla, course, num);
		if(quickPager.getTotalRows() == 0){
			return Response.failuer("列表为空!");
		}
		Response resp = new Response();
		resp.addQuickPager(quickPager);
		return resp.toString();
	}
	
	@RequestMapping(value = "/markpaper", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getTestContent(@RequestParam(value = "testId")String testId){
		if(StringUtils.isEmpty(testId)){
			return Response.failuer("测试编号为空!");
		}
		List<Map<String,Object>> singleChoose = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> multiChoose = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> fillBlank = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> tfQuestion = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> essayQuestion = new ArrayList<Map<String,Object>>();
		String single = "";
		String multi = "";
		String tfques = "";
		String fill = "";
		String essay = "";
		Map<String,Object> map = this.testservice.getTestContent(testId);
		String testPaperId = map.get("testPaperId").toString();
		Map<String,Object> testpaper = this.testservice.getTestPaper(testPaperId);
		String fixed = testpaper.get("fixed").toString();
		String totalScore = testpaper.get("totalScore").toString();
		String sc = map.get("sc").toString();
		String mc = map.get("mc").toString();
		String tf = map.get("tf").toString();
		String fb = map.get("fb").toString();
		String eq = map.get("eq").toString();
		String sScore = testpaper.get("sScore").toString();
		String mScore = testpaper.get("mScore").toString();
		String tfScore = testpaper.get("tfScore").toString();
		String fScore = testpaper.get("fScore").toString();
		String eScore = testpaper.get("eScore").toString();
		if(fixed.equals("4002")){
			if(!StringUtils.isEmpty(sc)){
				String[] SC = sc.split("`");
				List<String> arr = new ArrayList<String>();
				for(int i = 0; i < SC.length;){
					if(i == 0){
						single = SC[i];
					}else{
						single += "-"+SC[i];
					}
					arr.add(SC[i+1]);
					i += 2;
				}
				singleChoose = this.testservice.getSingleById(arr);
			}
			if(!StringUtils.isEmpty(mc)){
				String[] MC = mc.split("`");
				List<String> arr = new ArrayList<String>();
				for(int i = 0; i < MC.length;){
					if(i == 0){
						multi = MC[i];
					}else{
						multi += "`"+MC[i];
					}
					arr.add(MC[i+1]);
					i += 2;
				}
				multiChoose = this.testservice.getMultiById(arr);
			}
			if(!StringUtils.isEmpty(tf)){
				String[] TF = tf.split("`");
				List<String> arr = new ArrayList<String>();
				for(int i = 0; i < TF.length;){
					if(i == 0){
						tfques = TF[i];
					}else{
						tfques += "`"+TF[i];
					}
					arr.add(TF[i+1]);
					i += 2;
				}
				tfQuestion = this.testservice.getTFById(arr);
			}
			if(!StringUtils.isEmpty(fb)){
				String[] FB = fb.split("`");
				List<String> arr = new ArrayList<String>();
				for(int i = 0; i < FB.length;){
					if(i == 0){
						fill = FB[i];
					}else{
						fill += "`"+FB[i];
					}
					arr.add(FB[i+1]);
					i += 2;
				}
				fillBlank = this.testservice.getFillById(arr);
			}
			if(!StringUtils.isEmpty(eq)){
				String[] EQ = eq.split("`");
				List<String> arr = new ArrayList<String>();
				for(int i = 0; i < EQ.length;){
					if(i == 0){
						essay = EQ[i];
					}else{
						essay += "`"+EQ[i];
					}
					arr.add(EQ[i+1]);
					i += 2;
				}
				essayQuestion = this.testservice.getEssayById(arr);
			}
		}else{
			if(!StringUtils.isEmpty(testpaper.get("sc").toString())){
				String[] SC = testpaper.get("sc").toString().split("-");
				List<String> arr = new ArrayList<String>();
				for(int i = 0; i < SC.length; i++){
					arr.add(SC[i]);
				}
				single = sc;
				singleChoose = this.testservice.getSingleById(arr);
			}
			if(!StringUtils.isEmpty(testpaper.get("mc").toString())){
				String[] MC = testpaper.get("mc").toString().split("-");
				List<String> arr = new ArrayList<String>();
				for(int i = 0; i < MC.length; i++){
					arr.add(MC[i]);
				}
				multi = mc;
				multiChoose = this.testservice.getMultiById(arr);
			}
			if(!StringUtils.isEmpty(testpaper.get("tf").toString())){
				String[] TF = testpaper.get("tf").toString().split("-");
				List<String> arr = new ArrayList<String>();
				for(int i = 0; i < TF.length; i++){
					arr.add(TF[i]);
				}
				tfques = tf;
				tfQuestion = this.testservice.getTFById(arr);
			}
			if(!StringUtils.isEmpty(testpaper.get("fb").toString())){
				String[] FB = testpaper.get("fb").toString().split("-");
				List<String> arr = new ArrayList<String>();
				for(int i = 0; i < FB.length; i++){
					arr.add(FB[i]);
				}
				fill = fb;
				fillBlank = this.testservice.getFillById(arr);
			}
			if(!StringUtils.isEmpty(testpaper.get("eq").toString())){
				String[] EQ = testpaper.get("eq").toString().split("-");
				List<String> arr = new ArrayList<String>();
				for(int i = 0; i < EQ.length; i++){
					arr.add(EQ[i]);
				}
				essay = eq;
				essayQuestion = this.testservice.getEssayById(arr);
			}
		}
		if( singleChoose.isEmpty() && multiChoose.isEmpty() && fillBlank.isEmpty() && essayQuestion.isEmpty() ){
			return Response.failuer("获取信息失败!");
		}
		Response resp = new Response();
		resp.addString("totalScore", totalScore);
		if(!singleChoose.isEmpty()){
			resp.addString("single", single);
			resp.addString("sScore", sScore);
			resp.addList("singlelist", singleChoose);
		}
		if(!multiChoose.isEmpty()){
			resp.addString("multi", multi);
			resp.addString("mScore", mScore);
			resp.addList("multilist", multiChoose);
		}
		if(!tfQuestion.isEmpty()){
			resp.addString("tf", tfques);
			resp.addString("tfScore", tfScore);
			resp.addList("tflist", tfQuestion);
		}
		if(!fillBlank.isEmpty()){
			resp.addString("fill", fill);
			resp.addString("fScore", fScore);
			resp.addList("filllist", fillBlank);
		}
		if(!essayQuestion.isEmpty()){
			resp.addString("essay", essay);
			resp.addString("eScore", eScore);
			resp.addList("essaylist", essayQuestion);
		}
		return resp.toString();
	}
	
	@RequestMapping(value = "/setscore", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String setTestScore(@RequestParam(value = "score")String score,@RequestParam(value = "testpaperid")String id){
		if(StringUtils.isEmpty(score)){
			return Response.failuer("成绩为空!");
		}
		if(StringUtils.isEmpty(id)){
			return Response.failuer("测试编号为空!");
		}
		int x = this.testservice.setTestScore(score, id);
		if(x != 1){
			return Response.failuer("成绩录入失败!");
		}
		return Response.success("成绩录入成功!");
	}
}
