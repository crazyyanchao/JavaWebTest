package com.onlinetest.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onlinetest.common.QuickPager;
import com.onlinetest.common.Response;
import com.onlinetest.service.TestInformationService;

/**
 * @author �� ��
 *
 */
@Controller("TestInformationController")
@RequestMapping("/tests")
public class TestInformationController {

	@Resource(name = "TestInformationService")
	private TestInformationService testinformationservice;
	
	/**
	 * ������Ϣ��ȡ��ѧ��
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/stutestinfo",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getStudentTestinformation(@RequestParam(value = "userId")String userId){
		if(StringUtils.isEmpty(userId)){
			return Response.failuer("�û�IdΪ��,�����µ�¼!");
		}
		Map<String, Object> map = new HashMap<String,Object>();
		map = this.testinformationservice.getStudentInformation(userId);
		if(map == null){
			return Response.failuer("��ȡ������Ϣʧ��!");
		}
		Response response = new Response();
		response.addMap(map);
		return response.toString();
	}
	
	/**
	 * ��֤�����Ƿ�����
	 * @param testpaperid
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value = "/checktestinfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String checkTestInfo(@RequestParam(value = "testPaperId")String testpaperid,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String info = this.testinformationservice.checkTestInfo(testpaperid);
		String userId = request.getSession().getAttribute("userId").toString();
		if(info.equals("TestStartNotEnd")){
			String x = this.testinformationservice.checkJoined(userId, testpaperid);
			if(Integer.parseInt(x) == 1){
				return Response.failuer("���Ѿ��μӹ����ο���!");
			}
			request.getSession().setAttribute("testPaperId", testpaperid);
			return Response.success("�����Ѿ���ʼ!");
		}else if(info.equals("TestEnd")){
			return Response.failuer("�����Ѿ�����!");
		}else if(info.equals("TestNotStart")){
			return Response.failuer("���Ի�û�п�ʼ!");
		}else if(info.equals("TodayNoTest")){
			return Response.failuer("���ſ��Բ��ǽ��쿪��!");
		}else if(info.equals("TestIsOver")){
			return Response.failuer("���ſ����Ѿ�������!");
		}else if(info.equals("IsALongTimeBeforeTest")){
			return Response.failuer("���ſ�Ŀ����һ��ʱ��ſ�ʼ����!");
		}
		return Response.failuer("��ô��Ŀ�Ŀ����Ѿ�����һ��ʱ����!");
	}
	
	/**
	 * ��ȡ�ɼ���Ϣ�б�
	 */
	@RequestMapping(value = "/scorelist", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getScoreList(@RequestParam(value="currPage",required=false)String currPage,
			  				   @RequestParam(value="pageSize",required=false)String pageSize,
			  				   HttpServletRequest request){
		QuickPager<Map<String,Object>> quickPager = new QuickPager<Map<String,Object>>(currPage,pageSize);
		String userId = request.getSession().getAttribute("userId").toString();
		this.testinformationservice.getScoreList(quickPager, userId);
		if(quickPager.getTotalPages() == 0){
			return Response.failuer("û�вμӹ��Ŀ�����Ϣ!");
		}
		Response resp = new Response();
		resp.addQuickPager(quickPager);
		return resp.toString();
	}
	
	/**
	 * ��ȡ�Ծ���Ϣ
	 */
	@RequestMapping(value = "/testinfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getTestAnswer(@RequestParam(value = "id")String testId){
		if(StringUtils.isEmpty(testId)){
			return Response.failuer("�Ծ��Ż�ȡʧ��!");
		}
		Map<String,Object> testsmap = new HashMap<String,Object>();
		testsmap = this.testinformationservice.getTestsInfo(testId); 
		String testPaperId = testsmap.get("testPaperId").toString();
		Map<String,Object> testpapermap = new HashMap<String,Object>();
		testpapermap = this.testinformationservice.getTestPaperInfo(testPaperId);
		String sc = "";
		String mc = "";
		String tf = "";
		String fb = "";
		String eq = "";
		String[] SC = null;
		String[] MC = null;
		String[] TF = null;
		String[] FB = null;
		String[] EQ = null;
		List<Map<String,Object>> listAll = new ArrayList<Map<String,Object>>();
		String type = testpapermap.get("fixed").toString();
		Response resp = new Response();
		if(type.equals("4001")){//����̶�������testPaper����
			sc = testpapermap.get("sc").toString();
			mc = testpapermap.get("mc").toString();
			tf = testpapermap.get("tf").toString();
			fb = testpapermap.get("fb").toString();
			eq = testpapermap.get("eq").toString();
			if(sc.length() > 0){
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				SC = sc.split("-");
				list = this.testinformationservice.getSingleFixed(SC);
				listAll.addAll(list);
				if(!list.isEmpty()){
					resp.addString("single", testsmap.get("sc").toString());
					resp.addList("singlelist", list);
				}
			}
			if(mc.length() > 0){
				MC = mc.split("-");
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				list = this.testinformationservice.getMultiFixed(MC);
				listAll.addAll(list);
				if(!list.isEmpty()){
					resp.addString("multi", testsmap.get("mc").toString());
					resp.addList("multilist", list);
				}
			}
			if(tf.length() > 0){
				TF = tf.split("-");
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				list = this.testinformationservice.getTFFixed(TF);
				listAll.addAll(list);
				String[] tfAns = testsmap.get("tf").toString().split("`");
				String tfans = "";
				for(int i = 0; i < tfAns.length; i++){
					String x = tfAns[i];
					if(x.equals("6000")){
						x = "��ȷ";
					}else{
						x = "����";
					}
					if(i == 0){
						tfans = x;
					}else{
						tfans += "`"+x;
					}
				}
				if(!list.isEmpty()){
					resp.addString("tf", tfans);
					resp.addList("tflist", list);
				}
			}
			if(fb.length() > 0){
				FB = fb.split("-");
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				list = this.testinformationservice.getFillFixed(FB);
				listAll.addAll(list);
				if(!list.isEmpty()){
					resp.addString("fill", testsmap.get("fb").toString());
					resp.addList("filllist", list);
				}
			}
			if(eq.length() > 0){
				EQ = eq.split("-");
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				list = this.testinformationservice.getEssayFixed(EQ);
				listAll.addAll(list);
				if(!list.isEmpty()){
					resp.addString("essay", testsmap.get("eq").toString());
					resp.addList("essaylist", list);
				}
			}
		}else{//���ⲻ�̶�������tests����
			sc = testsmap.get("sc").toString();
			mc = testsmap.get("mc").toString();
			tf = testsmap.get("tf").toString();
			fb = testsmap.get("fb").toString();
			eq = testsmap.get("eq").toString();
			if(sc.length() > 0){
				SC = sc.split("`");
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				List<String> title = new ArrayList<String>();
				sc = "";
				for(int i = 0; i < SC.length;){
					if(i == 0){
						sc = SC[i];
					}else{
						sc += "`"+SC[i];
					}
					title.add(SC[i+1]);
					i += 2;
				}
				String[] str = new String[title.size()];
				list = this.testinformationservice.getSingleFixed(title.toArray(str));
				listAll.addAll(list);
				if(!list.isEmpty()){
					resp.addString("single", sc);
					resp.addList("singlelist", list);
				}
			}
			if(mc.length() > 0){
				MC = mc.split("`");
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				List<String> title = new ArrayList<String>();
				mc = "";
				for(int i = 0; i < MC.length;){
					if(i == 0){
						mc = MC[i];
					}else{
						mc += "`"+MC[i];
					}
					title.add(MC[i+1]);
					i += 2;
				}
				String[] str = new String[title.size()];
				list = this.testinformationservice.getMultiFixed(title.toArray(str));
				listAll.addAll(list);
				if(!list.isEmpty()){
					resp.addString("multi", mc);
					resp.addList("multilist", list);
				}
			}
			if(tf.length() > 0){
				TF = tf.split("`");
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				List<String> title = new ArrayList<String>();
				tf = "";
				for(int i = 0; i < TF.length;){
					String x = TF[i];
					if(x.equals("6000")){
						x = "��ȷ";
					}else{
						x = "����";
					}
					if(i == 0){
						tf = x;
					}else{
						tf += "`"+x;
					}
					title.add(TF[i+1]);
					i += 2;
				}
				String[] str = new String[title.size()];
				list = this.testinformationservice.getTFFixed(title.toArray(str));
				listAll.addAll(list);
				if(!list.isEmpty()){
					resp.addString("tf", tf);
					resp.addList("tflist", list);
				}
			}
			if(fb.length() > 0){
				FB = fb.split("`");
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				List<String> title = new ArrayList<String>();
				fb = "";
				for(int i = 0; i < FB.length;){
					if(i == 0){
						fb = FB[i];
					}else{
						fb += "`"+FB[i];
					}
					title.add(FB[i+1]);
					i += 2;
				}
				String[] str = new String[title.size()];
				list = this.testinformationservice.getFillFixed(title.toArray(str));
				listAll.addAll(list);
				if(!list.isEmpty()){
					resp.addString("fill", fb);
					resp.addList("filllist", list);
				}
			}
			if(eq.length() > 0){
				EQ = eq.split("`");
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				List<String> title = new ArrayList<String>();
				eq = "";
				for(int i = 0; i < EQ.length;){
					if(i == 0){
						eq = EQ[i];
					}else{
						eq += "`"+EQ[i];
					}
					title.add(EQ[i+1]);
					i += 2;
				}
				String[] str = new String[title.size()];
				list = this.testinformationservice.getEssayFixed(title.toArray(str));
				listAll.addAll(list);
				if(!list.isEmpty()){
					resp.addString("essay", eq);
					resp.addList("essaylist", list);
				}
			}
		}
		if(listAll.isEmpty()){
			return Response.failuer("��ȡ��Ϣʧ��!");
		}
		//���
		return resp.toString();
	}
}
