package com.onlinetest.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlinetest.common.MyRandom;
import com.onlinetest.common.QuickPager;
import com.onlinetest.dao.ClassDao;
import com.onlinetest.dao.CourseAndChapterDao;
import com.onlinetest.dao.DataDictionaryDao;
import com.onlinetest.dao.EssayQuestionDao;
import com.onlinetest.dao.FillBlankDao;
import com.onlinetest.dao.MultiChooseDao;
import com.onlinetest.dao.SingleChooseDao;
import com.onlinetest.dao.TestDao;
import com.onlinetest.dao.TestPaperDao;
import com.onlinetest.dao.UserDao;
import com.onlinetest.util.StringUtils;

@Service("TestService")
public class TestService {

	@Resource(name = "TestPaperDao")
	private TestPaperDao testpaperdao;
	
	@Resource(name = "SingleChooseDao")
	private SingleChooseDao singlechoosedao;
	
	@Resource(name = "MultiChooseDao")
	private MultiChooseDao multichoosedao;
	
	@Resource(name = "FillBlankDao")
	private FillBlankDao fillblankdao;
	
	@Resource(name = "EssayQuestionDao")
	private EssayQuestionDao essayquestiondao;
	
	@Resource(name = "UserDao")
	private UserDao userdao;
	
	@Resource(name = "TestDao")
	private TestDao testdao;
	
	@Resource(name = "ClassDao")
	private ClassDao classdao;
	
	@Resource(name = "CourseAndChapterDao")
	private CourseAndChapterDao courseandchapterdao;
	
	@Resource(name = "DataDictionaryDao")
	private DataDictionaryDao datadictionarydao;
	/**
	 * 获取考试信息
	 * @param testpaperid
	 * @return
	 */
	public Map<String,Object> getTestInfo(String testpaperid){
		Map<String, Object> map = new HashMap<String,Object>();
		map = this.testpaperdao.getTestPaperInfo(testpaperid);
		Map<String, Object> m = new HashMap<String,Object>(); 
		m = this.testpaperdao.getDateTimeClassById(testpaperid);
		String endtime = m.get("endTime").toString();
		String[] arr = endtime.split(":");
		Calendar calender = Calendar.getInstance();
		int hour = calender.get(Calendar.HOUR_OF_DAY);
		int minute = calender.get(Calendar.MINUTE);
		int time = ( Integer.parseInt(arr[0]) - hour )*60 + Integer.parseInt(arr[1]) - minute;
		map.put("time", time);
		return map;
	}
	
	/**
	 * 获取单选题 试题不固定
	 * @param course
	 * @param chapter
	 * @param arr
	 * @return
	 */
	public List<Map<String,Object>> getSingleChoose(String course,String chapter,String[] arr){
		List<String> ids = new ArrayList<String>();
		List<String> testIdList = new ArrayList<String>();
		MyRandom mr = new MyRandom();
		String scCount = "";
		String scLevel = "";
		String scEasy = "";
		String scNormal = "";
		String scHard = "";
		if(arr.length == 2){
			scCount = arr[0];
			scLevel = arr[1];
			testIdList = this.singlechoosedao.getFullSingleChooseId(course, chapter, scLevel);
			ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(scCount)) );
		}else if(arr.length == 4){
			scCount = arr[0];
			scEasy = arr[1];
			testIdList = this.singlechoosedao.getFullSingleChooseId(course, chapter, "2001");
			ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(scEasy)) );
			scNormal = arr[2];
			testIdList.addAll(this.singlechoosedao.getFullSingleChooseId(course, chapter, "2002"));
			ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(scNormal)) );
			scHard = arr[3];
			testIdList.addAll(this.singlechoosedao.getFullSingleChooseId(course, chapter, "2003"));
			ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(scHard)) );
		}
		List<Map<String,Object>> list = this.singlechoosedao.getSomeSingleChoose(ids);
		return list;
	}
	
	public List<Map<String,Object>> getMultiChoose(String course,String chapter,String[] arr){
		List<String> ids = new ArrayList<String>();
		List<String> testIdList = new ArrayList<String>();
		MyRandom mr = new MyRandom();
		String mcCount = "";
		String mcLevel = "";
		String mcEasy = "";
		String mcNormal = "";
		String mcHard = "";
		if(arr.length == 2){
			mcCount = arr[0];
			mcLevel = arr[1];
			testIdList = this.multichoosedao.getFullMultiChooseId(course, chapter, mcLevel);
			ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(mcCount)) );
		}else if(arr.length == 4){
			mcCount = arr[0];
			mcEasy = arr[1];
			testIdList = this.multichoosedao.getFullMultiChooseId(course, chapter, "2001");
			ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(mcEasy)) );
			mcNormal = arr[2];
			testIdList = this.multichoosedao.getFullMultiChooseId(course, chapter, "2002");
			ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(mcNormal)) );
			mcHard = arr[3];
			testIdList = this.multichoosedao.getFullMultiChooseId(course, chapter, "2003");
			ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(mcHard)) );
		}
		List<Map<String,Object>> list = this.multichoosedao.getSomeMultiChoose(ids);
		return list;
	}
	
	public List<Map<String,Object>> getTFQuestion(String course,String chapter,String[] arr){
		List<String> ids = new ArrayList<String>();
		List<String> testIdList = new ArrayList<String>();
		MyRandom mr = new MyRandom();
		String fbCount = "";
		String fbLevel = "";
		String fbEasy = "";
		String fbNormal = "";
		String fbHard = "";
		if(arr.length == 2){
			fbCount = arr[0];
			fbLevel = arr[1];
			testIdList = this.fillblankdao.getFullTFId(course, chapter, fbLevel);
			ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(fbCount)) );
		}else if(arr.length == 4){
			fbCount = arr[0];
			fbEasy = arr[1];
			testIdList = this.fillblankdao.getFullTFId(course, chapter, "2001");
			ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(fbEasy)) );
			fbNormal = arr[2];
			testIdList = this.fillblankdao.getFullTFId(course, chapter, "2002");
			ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(fbNormal)) );
			fbHard = arr[3];
			testIdList = this.fillblankdao.getFullTFId(course, chapter, "2003");
			ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(fbHard)) );
		}
		List<Map<String,Object>> list = this.fillblankdao.getSomeTFQuestion(ids);
		return list;
	}
	
	public List<Map<String,Object>> getFillBlank(String course,String chapter,String[] arr){
		List<String> ids = new ArrayList<String>();
		List<String> testIdList = new ArrayList<String>();
		MyRandom mr = new MyRandom();
		String fbCount = "";
		String fbLevel = "";
		String fbEasy = "";
		String fbNormal = "";
		String fbHard = "";
		if(arr.length == 2){
			fbCount = arr[0];
			fbLevel = arr[1];
			testIdList = this.fillblankdao.getFullFillBlankId(course, chapter, fbLevel);
			ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(fbCount)) );
		}else if(arr.length == 4){
			fbCount = arr[0];
			fbEasy = arr[1];
			testIdList = this.fillblankdao.getFullFillBlankId(course, chapter, "2001");
			ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(fbEasy)) );
			fbNormal = arr[2];
			testIdList = this.fillblankdao.getFullFillBlankId(course, chapter, "2002");
			ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(fbNormal)) );
			fbHard = arr[3];
			testIdList = this.fillblankdao.getFullFillBlankId(course, chapter, "2003");
			ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(fbHard)) );
		}
		List<Map<String,Object>> list = this.fillblankdao.getSomeFillBlank(ids);
		return list;
	}
	
	public List<Map<String,Object>> getEssayQuestion(String course,String chapter,String[] arr){
		List<String> ids = new ArrayList<String>();
		List<String> testIdList = new ArrayList<String>();
		MyRandom mr = new MyRandom();
		String eqCount = "";
		String eqLevel = "";
		String eqEasy = "";
		String eqNormal = "";
		String eqHard = "";
		if(arr.length == 2){
			eqCount = arr[0];
			eqLevel = arr[1];
			testIdList = this.essayquestiondao.getFullEssayQuestionId(course, chapter, eqLevel);
			ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(eqCount)) );
		}else if(arr.length == 4){
			eqCount = arr[0];
			eqEasy = arr[1];
			testIdList = this.essayquestiondao.getFullEssayQuestionId(course, chapter, "2001");
			ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(eqEasy)) );
			eqNormal = arr[2];
			testIdList = this.essayquestiondao.getFullEssayQuestionId(course, chapter, "2002");
			ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(eqNormal)) );
			eqHard = arr[3];
			testIdList = this.essayquestiondao.getFullEssayQuestionId(course, chapter, "2003");
			ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(eqHard)) );
		}
		List<Map<String,Object>> list = this.essayquestiondao.getSomeEssayQuestionId(ids);
		return list;
	}
	
	/**
	 * 获取单选题 题目固定
	 * @param ids
	 * @return
	 */
	public List<Map<String, Object>> getSingleChooseFixed(List<String> ids){
		return this.singlechoosedao.getSomeSingleChoose(ids);
	}
	
	public List<Map<String, Object>> getMultiChooseFixed(List<String> ids){
		return this.multichoosedao.getSomeMultiChoose(ids);
	}
	
	public List<Map<String, Object>> getTFFixed(List<String> ids){
		return this.fillblankdao.getSomeTFQuestion(ids);
	}
	
	public List<Map<String, Object>> getFillBlankFixed(List<String> ids){
		return this.fillblankdao.getSomeFillBlank(ids);
	}
	
	public List<Map<String, Object>> getEssayQuestionFixed(List<String> ids){
		return this.essayquestiondao.getSomeEssayQuestionId(ids);
	}
	
	/**
	 * 提交试卷
	 * @param userId
	 * @param data
	 * @return
	 */
	@Transactional
	public int subPaper(String userId, String testPaperId, String singledata, String multidata, String tfdata, String filldata, String essaydata){
		String classCode = this.userdao.getStudentClasses(userId);
		//直接判断只有单选题或者只有单选和判断题的试卷
		if( !StringUtils.isEmpty(singledata) && StringUtils.isEmpty(multidata) && StringUtils.isEmpty(tfdata) 
				&& StringUtils.isEmpty(filldata) && StringUtils.isEmpty(essaydata) ){
			Map<String,Object> testpaper = new HashMap<String,Object>();
			testpaper = this.testpaperdao.getTestInfoForCheck(testPaperId);
			String paperType = testpaper.get("fixed").toString();
			if(paperType.equals("4001")){  //4001试题固定，即题目在testpaper中，答案在tests中
				String sc = testpaper.get("sc").toString();
				String sScore = testpaper.get("sScore").toString();
				String[] SC = sc.split("-");
				String[] sAns = singledata.split("`");
				int testScore = 0;
				for (int i = 0; i < SC.length; i++) {
					String ans = this.singlechoosedao.getSingleAns(SC[i]);
					if(ans.equals(sAns[i])){
						testScore += Integer.parseInt(sScore);
					}
				}
				return this.testdao.subTestAndScore(userId, classCode, testPaperId, testScore, singledata, tfdata);
			}else{
				String sScore = testpaper.get("sScore").toString();
				String[] SC = singledata.split("`");
				int testScore = 0;
				for (int i = 0; i < SC.length;) {
					String sAns = SC[i];
					String sid = SC[i+1];
					String ans = this.singlechoosedao.getSingleAns(sid);
					if(ans.equals(sAns)){
						testScore += Integer.parseInt(sScore);
					}
					i += 2;
				}
				return this.testdao.subTestAndScore(userId, classCode, testPaperId, testScore, singledata, tfdata);
			}
		}else if( !StringUtils.isEmpty(singledata) && StringUtils.isEmpty(multidata) && !StringUtils.isEmpty(tfdata) 
				&& StringUtils.isEmpty(filldata) && StringUtils.isEmpty(essaydata) ){
			Map<String,Object> testpaper = new HashMap<String,Object>();
			testpaper = this.testpaperdao.getTestInfoForCheck(testPaperId);
			String paperType = testpaper.get("fixed").toString();
			if(paperType.equals("4001")){  //4001试题固定，即题目在testpaper中，答案在tests中
				String sc = testpaper.get("sc").toString();
				String tf = testpaper.get("tf").toString();
				String sScore = testpaper.get("sScore").toString();
				String tfScore = testpaper.get("tfScore").toString();
				String[] SC = sc.split("-");
				String[] TF = tf.split("-");
				String[] sAns = singledata.split("`");
				String[] tfAns = tfdata.split("`");
				int testScore = 0;
				for (int i = 0; i < SC.length; i++) {
					String ans = this.singlechoosedao.getSingleAns(SC[i]);
					if(ans.equals(sAns[i])){
						testScore += Integer.parseInt(sScore);
					}
				}
				for (int i = 0; i < tfAns.length; i++) {
					String ans = this.fillblankdao.getTFAns(TF[i]);
					if(ans.equals(tfAns[i])){
						testScore += Integer.parseInt(tfScore);
					}
				}
				return this.testdao.subTestAndScore(userId, classCode, testPaperId, testScore, singledata, tfdata);
			}else{
				String sScore = testpaper.get("sScore").toString();
				String tfScore = testpaper.get("tfScore").toString();
				String[] SC = singledata.split("`");
				String[] TF = tfdata.split("`");
				int testScore = 0;
				for (int i = 0; i < SC.length;) {
					String sAns = SC[i];
					String sid = SC[i+1];
					String ans = this.singlechoosedao.getSingleAns(sid);
					if(ans.equals(sAns)){
						testScore += Integer.parseInt(sScore);
					}
					i += 2;
				}
				for (int i = 0; i < TF.length;) {
					String tfAns = TF[i];
					String tfId = TF[i+1];
					String ans = this.fillblankdao.getTFAns(tfId);
					if(ans.equals(tfAns)){
						testScore += Integer.parseInt(tfScore);
					}
					i += 2;
				}
				return this.testdao.subTestAndScore(userId, classCode, testPaperId, testScore, singledata, tfdata);
			}
		}
		return this.testdao.subTest(userId, classCode, testPaperId, singledata, multidata, tfdata, filldata, essaydata);
	}
	
	public void getTestsList(String userId,QuickPager<Map<String,Object>> quickPager,
			String cla, String courseCode, String num){
		List<String> ids = new ArrayList<String>();
		ids = this.testpaperdao.getIdByMarker(userId);
		Object[] id = ids.toArray();
		int[] idd = new int[id.length];
		for (int i = 0; i < idd.length; i++) {
			idd[i] = Integer.parseInt( String.valueOf(id[i]) );
		}
		Arrays.sort(idd);
		ids.clear();
		for (int i = 0; i < idd.length; i++) {
			ids.add( String.valueOf(idd[i]) );
		}
		this.testdao.getTestsList(ids, quickPager, cla, num);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = quickPager.getData();
		list = sortTestList(list);
		for(int i = 0; i < list.size(); i++){
			Map<String, Object> map = new HashMap<String,Object>();
			map = list.get(i);
			String testpaperid = map.get("testPaperId").toString();
			String course = this.testpaperdao.getCourse(testpaperid);
			map.remove("classCode");
			map.remove("testPaperId");
			if(!StringUtils.isEmpty(courseCode)){
				if(!course.equals(courseCode)){
					list.remove(map);
					i--;
				}else{
					course = this.courseandchapterdao.getCourseValue(course);
					map.put("course", course);
				}
			}else{
				course = this.courseandchapterdao.getCourseValue(course);
				map.put("course", course);
			}
		}
	}
	
	/**
	 * 题目和答案
	 */
	public Map<String,Object> getTestContent(String testId){
		return this.testdao.getTestPaperId(testId);
	}
	
	public Map<String,Object> getTestPaper(String id){
		return this.testpaperdao.getTestInfoForCheck(id);
	}
	
	public List<Map<String,Object>> getSingleById(List<String> ids){
		return this.singlechoosedao.getSingleTitleAns(ids);
	}
	
	public List<Map<String,Object>> getMultiById(List<String> ids){
		return this.multichoosedao.getMultiTitleAns(ids);
	}
	
	public List<Map<String,Object>> getTFById(List<String> ids){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = this.fillblankdao.getFillTitleAns(ids);
		for (Map<String, Object> map : list) {
			String ans = map.get("answer").toString();
			ans = this.datadictionarydao.getSecondCoudeValue(ans);
			map.put("ans", ans);
		}
		return list;
	}
	
	public List<Map<String,Object>> getFillById(List<String> ids){
		return this.fillblankdao.getFillTitleAns(ids);
	}
	
	public List<Map<String,Object>> getEssayById(List<String> ids){
		return this.essayquestiondao.getEssayTitleAns(ids);
	}
	
	/**
	 * 对list集合进行排序
	 */
	private List<Map<String,Object>> sortTestList(List<Map<String,Object>> list){
		for (int i = 0; i < list.size()-1; i++) {
			Map<String,Object> map = new HashMap<String,Object>();
			map = list.get(i);
			String id = map.get("id").toString();
			for (int j = i; j < list.size(); j++) {
				Map<String,Object> map2 = list.get(j); 
				String id2 = map2.get("id").toString();
				if(Integer.parseInt(id) > Integer.parseInt(id2)){
					Map<String,Object> temp = new HashMap<String,Object>();
					temp.putAll(map);
					map.clear();
					map.putAll(map2);
					map2.clear();
					map2.putAll(temp);
				}
			}
		}
		return list;
	}
	/**
	 * 录入成绩
	 */
	@Transactional
	public int setTestScore(String score,String id){
		return this.testdao.setTestScore(score, id);
	}
}
