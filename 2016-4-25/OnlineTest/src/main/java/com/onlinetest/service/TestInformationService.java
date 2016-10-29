package com.onlinetest.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.onlinetest.common.MyDate;
import com.onlinetest.common.MyTime;
import com.onlinetest.common.QuickPager;
import com.onlinetest.dao.CourseAndChapterDao;
import com.onlinetest.dao.DataDictionaryDao;
import com.onlinetest.dao.EssayQuestionDao;
import com.onlinetest.dao.FillBlankDao;
import com.onlinetest.dao.MultiChooseDao;
import com.onlinetest.dao.SingleChooseDao;
import com.onlinetest.dao.TestDao;
import com.onlinetest.dao.TestInformationDao;
import com.onlinetest.dao.TestPaperDao;
import com.onlinetest.dao.UserDao;

/**
 * @author 丁 鹏
 *
 */
@Service("TestInformationService")
public class TestInformationService {

	@Resource(name = "TestInformationDao")
	private TestInformationDao testinformationdao;
	
	@Resource(name = "TestPaperDao")
	private TestPaperDao testpaperdao;
	
	@Resource(name = "UserDao")
	private UserDao userdao;
	
	@Resource(name = "TestDao")
	private TestDao testdao;
	
	@Resource(name = "DataDictionaryDao")
	private DataDictionaryDao datadictionarydao;
	
	@Resource(name = "CourseAndChapterDao")
	private CourseAndChapterDao courseandchapterdao;
	
	@Resource(name = "SingleChooseDao")
	private SingleChooseDao singlechoosedao;
	
	@Resource(name = "MultiChooseDao")
	private MultiChooseDao multichoosedao;
	
	@Resource(name = "FillBlankDao")
	private FillBlankDao fillblankdao;
	
	@Resource(name = "EssayQuestionDao")
	private EssayQuestionDao essayquestiondao;
	
	/**
	 * 获取学生考试信息
	 * @param userId
	 * @return
	 */
	public Map<String, Object> getStudentInformation(String userId){
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			map = this.testinformationdao.getStudentTestinfo(userId);
			if(map == null){
				return null;
			}
			String testpaperid = map.get("testPaperId").toString();
			Map<String, Object> testpaper = new HashMap<String,Object>();
			testpaper = this.testpaperdao.getStudentTestPaperInfo(testpaperid);
			if(testpaper == null){
				return null;
			}
			map.put("course", testpaper.get("course"));
			map.put("startTime", testpaper.get("startTime"));
			map.put("endTime", testpaper.get("endTime"));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 判断学生的考试时间
	 * @param testpaperid
	 * @param userId
	 * @return
	 */
	public String checkTestInfo(String testpaperid){
		Map<String, Object> info = new HashMap<String,Object>();
		info = this.testpaperdao.getDateTimeClassById(testpaperid);
		String date = info.get("date").toString();
		String startTime = info.get("startTime").toString();
		String endTime = info.get("endTime").toString();
		MyDate mydate = new MyDate();
		MyTime mytime = new MyTime();
		mydate.setDate(date);
		String year = mydate.getYear();
		String month =mydate.getMonth();
		String day = mydate.getDay();
		mytime.setTime(startTime);
		String stH = mytime.getHour();
		String stM = mytime.getMinute();
		mytime.setTime(endTime);
		String enH = mytime.getHour();
		String enM = mytime.getMinute();
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		int Year = c.get(Calendar.YEAR); 
		int Month = c.get(Calendar.MONTH) + 1; 
		int Date = c.get(Calendar.DATE); 
		int hour = c.get(Calendar.HOUR_OF_DAY); 
		int minute = c.get(Calendar.MINUTE);
		if(Year == Integer.parseInt(year) && Month == Integer.parseInt(month) && Date == Integer.parseInt(day) ){
			if(hour < Integer.parseInt(stH) || (hour == Integer.parseInt(stH) && minute < Integer.parseInt(stM) ) ){
				return "TestNotStart";
			}else if(hour == Integer.parseInt(stH) && minute >= Integer.parseInt(stM)){
				return "TestStartNotEnd";
			}else if(hour < Integer.parseInt(enH) || (hour == Integer.parseInt(enH) && minute < Integer.parseInt(enM) )){
				return "TestStartNotEnd";
			}else if(hour == Integer.parseInt(enH) && minute > Integer.parseInt(enM)){
				return "TestEnd";
			}else if(hour  > Integer.parseInt(enH)){
				return "TestEnd";
			}
		}else if(Year == Integer.parseInt(year) && Month == Integer.parseInt(month) && Date < Integer.parseInt(day) ){
			return "TodayNoTest";
		}else if(Year == Integer.parseInt(year) && Month == Integer.parseInt(month) && Date > Integer.parseInt(day)){
			return "TestIsOver";
		}else if(Year == Integer.parseInt(year) && Month < Integer.parseInt(month)){
			return "IsALongTimeBeforeTest";
		}else if(Year == Integer.parseInt(year) && Month > Integer.parseInt(month)){
			return "IsALongTimeAfterTest";
		}else if(Year < Integer.parseInt(year)){
			return "IsALongTimeBeforeTest";
		}
		return "IsALongTimeAfterTest";
	}
	/**
	 * 是否参加考试
	 */
	public String checkJoined(String userId,String testPaperId){
		return this.testdao.checkJoined(userId, testPaperId);
	}
	
	public void getScoreList(QuickPager<Map<String,Object>> quickPager, String userId){
		this.testdao.getScoreList(quickPager, userId);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = quickPager.getData();
		for (Map<String, Object> map : list) {
			String testpaperid = map.get("testPaperId").toString();
			if(map.get("score") == null){
				map.put("score", "暂无");
			}
			Map<String,Object> m = new HashMap<String,Object>();
			m = this.testpaperdao.getCourseTimeById(testpaperid);
			String course = m.get("course").toString();
			String date = m.get("date").toString();
			String stime = m.get("startTime").toString();
			String etime = m.get("endTime").toString();
			stime = date +" "+stime;
			etime = date +" "+etime;
			course = this.courseandchapterdao.getCourseValue(course);
			
			map.remove("testPaperId");
			map.put("course", course);
			map.put("startTime", stime);
			map.put("endTime", etime);
		}
	}
	
	
	/**
	 * 获取测试信息记录
	 * @param testId
	 * @return
	 */
	public Map<String,Object> getTestsInfo(String testId){
		return this.testdao.getTestPaperId(testId);
	}
	
	/**
	 * 获取试卷信息
	 */
	public Map<String,Object> getTestPaperInfo(String testPaperId){
		return this.testpaperdao.getTestInfoForCheck(testPaperId);
	}
	
	public List<Map<String,Object>> getSingleFixed(String[] arr){
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}
		return this.singlechoosedao.getSingleTitleAns(list);
	}
	
	public List<Map<String,Object>> getMultiFixed(String[] arr){
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}
		return this.multichoosedao.getMultiTitleAns(list);
	}
	
	public List<Map<String,Object>> getFillFixed(String[] arr){
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}
		return this.fillblankdao.getFillTitleAns(list);
	}
	
	public List<Map<String,Object>> getTFFixed(String[] arr){
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}
		List<Map<String,Object>> arrlist = new ArrayList<Map<String,Object>>();
		arrlist = this.fillblankdao.getFillTitleAns(list);
		for (Map<String, Object> map : arrlist) {
			String ans = map.get("answer").toString();
			ans = this.datadictionarydao.getSecondCoudeValue(ans);
			map.put("answer", ans);
		}
		return arrlist;
	}
	
	public List<Map<String,Object>> getEssayFixed(String[] arr){
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}
		return this.essayquestiondao.getEssayTitleAns(list);
	}
}
