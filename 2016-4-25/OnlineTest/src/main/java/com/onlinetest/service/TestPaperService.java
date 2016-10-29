package com.onlinetest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlinetest.common.MyRandom;
import com.onlinetest.common.QuickPager;
import com.onlinetest.dao.ClassDao;
import com.onlinetest.dao.CourseAndChapterDao;
import com.onlinetest.dao.EssayQuestionDao;
import com.onlinetest.dao.FillBlankDao;
import com.onlinetest.dao.MultiChooseDao;
import com.onlinetest.dao.SingleChooseDao;
import com.onlinetest.dao.TestPaperDao;
import com.onlinetest.dao.UserDao;
import com.onlinetest.util.StringUtils;


/**
 * @author 丁 鹏
 *
 */
@Service("TestPaperService")
public class TestPaperService {
	
	@Resource(name = "TestPaperDao")
	private TestPaperDao testpaperdao;
	
	@Resource(name = "CourseAndChapterDao")
	private CourseAndChapterDao courseandchapterdao;
	
	@Resource(name = "ClassDao")
	private ClassDao classdao;
	
	@Resource(name = "UserDao")
	private UserDao userdao;
	
	@Resource(name = "SingleChooseDao")
	private SingleChooseDao singlechoosedao;
	
	@Resource(name = "MultiChooseDao")
	private MultiChooseDao multichoosedao;
	
	@Resource(name = "FillBlankDao")
	private FillBlankDao fillblankdao;
	
	@Resource(name = "EssayQuestionDao")
	private EssayQuestionDao essayquestiondao;
	
	@Transactional
	public int setTestPaper(String userId,String data){
		String creatime = String.valueOf(System.currentTimeMillis());
		String[] array = data.split("`");
		String courseCode = array[0];
		String chapterCode = array[1];
		String testdate = array[2];
		String starttime = array[3];
		String endtime = array[4];
		String paperstyle = array[5];
		String totalScore = array[6];
		String markers = array[7];
		String joinedclasses = array[8];
		String sc = "";
		String mc = "";
		String tf = "";
		String fb = "";
		String eq = "";
		String x = "";
		String Count = "";
		String Level = "";
		String Easy = "";
		String Normal = "";
		String Hard = "";
		String sscore = "";
		String mscore = "";
		String tfscore = "";
		String fscore = "";
		String escore = "";
		List<String> ids = new ArrayList<String>();
		List<String> testIdList = new ArrayList<String>();
		MyRandom mr = new MyRandom();
		for (int i = 9; i < array.length;) {
			String type = array[i];
			String count = array[i+1];
			String score = array[i+2];
			String level = array[i+3];
			String easy = "";
			String normal = "";
			String hard = "";
			if(level.equals("level")){
				easy = array[i+4];
				normal = array[i+5];
				hard = array[i+6];
				i += 3;
				x = count + "-" + easy + "-" + normal + "-" + hard ;
			}else{
				x = count + "-" + level;
			}
			if(type.equals("SC")){
				sc = x;
				sscore = score;
			}else if(type.equals("MC")){
				mc = x;
				mscore = score;
			}else if(type.equals("TF")){
				tf = x;
				tfscore = score;
			}else if(type.equals("FB")){
				fb = x;
				fscore = score;
			}else{
				eq = x;
				escore = score;
			}
			i += 4;
		}
		if(paperstyle.equals("4001")){
			String[] SC = null;
			String[] MC = null;
			String[] TF = null;
			String[] FB = null;
			String[] EQ = null;
			if(!StringUtils.isEmpty(sc)){
				ids.clear();
				testIdList.clear();
				SC = sc.split("-");
				sc = "";
				if(SC.length == 2){
					Count = SC[0];
					Level = SC[1];
					testIdList = this.singlechoosedao.getFullSingleChooseId(courseCode, chapterCode, Level);
					ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(Count)) );
				}else if(SC.length == 4){
					Count = SC[0];
					Easy = SC[1];
					testIdList = this.singlechoosedao.getFullSingleChooseId(courseCode, chapterCode, "2001");
					ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(Easy)) );
					Normal = SC[2];
					testIdList.addAll(this.singlechoosedao.getFullSingleChooseId(courseCode, chapterCode, "2002"));
					ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(Normal)) );
					Hard = SC[3];
					testIdList.addAll(this.singlechoosedao.getFullSingleChooseId(courseCode, chapterCode, "2003"));
					ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(Hard)) );
				}
				for (String string : ids) {
					sc += string + "-";
				}
				sc = sc.substring(0, sc.length()-1);
			}
			if(!StringUtils.isEmpty(mc)){
				ids.clear();
				testIdList.clear();
				MC = mc.split("-");
				mc = "";
				if(MC.length == 2){
					Count = MC[0];
					Level = MC[1];
					testIdList = this.multichoosedao.getFullMultiChooseId(courseCode, chapterCode, Level);
					ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(Count)) );
				}else if(MC.length == 4){
					Count = MC[0];
					Easy = MC[1];
					testIdList = this.multichoosedao.getFullMultiChooseId(courseCode, chapterCode, "2001");
					ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(Easy)) );
					Normal = MC[2];
					testIdList.addAll(this.multichoosedao.getFullMultiChooseId(courseCode, chapterCode, "2002"));
					ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(Normal)) );
					Hard = MC[3];
					testIdList.addAll(this.multichoosedao.getFullMultiChooseId(courseCode, chapterCode, "2003"));
					ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(Hard)) );
				}
				for (String string : ids) {
					mc += string + "-";
				}
				mc = mc.substring(0, mc.length()-1);
			}
			if(!StringUtils.isEmpty(tf)){
				ids.clear();
				testIdList.clear();
				TF = tf.split("-");
				tf = "";
				if(TF.length == 2){
					Count = TF[0];
					Level = TF[1];
					testIdList = this.fillblankdao.getFullTFId(courseCode, chapterCode, Level);
					ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(Count)) );
				}else if(TF.length == 4){
					Count = TF[0];
					Easy = TF[1];
					testIdList = this.fillblankdao.getFullTFId(courseCode, chapterCode, "2001");
					ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(Easy)) );
					Normal = TF[2];
					testIdList.addAll(this.fillblankdao.getFullTFId(courseCode, chapterCode, "2002"));
					ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(Normal)) );
					Hard = TF[3];
					testIdList.addAll(this.fillblankdao.getFullTFId(courseCode, chapterCode, "2003"));
					ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(Hard)) );
				}
				for (String string : ids) {
					tf += string + "-";
				}
				tf = tf.substring(0, tf.length()-1);
			}
			if(!StringUtils.isEmpty(fb)){
				ids.clear();
				testIdList.clear();
				FB = fb.split("-");
				fb = "";
				if(FB.length == 2){
					Count = FB[0];
					Level = FB[1];
					testIdList = this.fillblankdao.getFullFillBlankId(courseCode, chapterCode, Level);
					ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(Count)) );
				}else if(FB.length == 4){
					Count = FB[0];
					Easy = FB[1];
					testIdList = this.fillblankdao.getFullFillBlankId(courseCode, chapterCode, "2001");
					ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(Easy)) );
					Normal = FB[2];
					testIdList.addAll(this.fillblankdao.getFullFillBlankId(courseCode, chapterCode, "2002"));
					ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(Normal)) );
					Hard = FB[3];
					testIdList.addAll(this.fillblankdao.getFullFillBlankId(courseCode, chapterCode, "2003"));
					ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(Hard)) );
				}
				for (String string : ids) {
					fb += string + "-";
				}
				fb = fb.substring(0, fb.length()-1);
			}
			if(!StringUtils.isEmpty(eq)){
				ids.clear();
				testIdList.clear();
				EQ = eq.split("-");
				eq = "";
				if(EQ.length == 2){
					Count = EQ[0];
					Level = EQ[1];
					testIdList = this.essayquestiondao.getFullEssayQuestionId(courseCode, chapterCode, Level);
					ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(Count)) );
				}else if(EQ.length == 4){
					Count = EQ[0];
					Easy = EQ[1];
					testIdList = this.essayquestiondao.getFullEssayQuestionId(courseCode, chapterCode, "2001");
					ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(Easy)) );
					Normal = EQ[2];
					testIdList.addAll(this.essayquestiondao.getFullEssayQuestionId(courseCode, chapterCode, "2002"));
					ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(Normal)) );
					Hard = EQ[3];
					testIdList.addAll(this.essayquestiondao.getFullEssayQuestionId(courseCode, chapterCode, "2003"));
					ids.addAll( mr.getRandom(testIdList.toArray(), Integer.parseInt(Hard)) );
				}
				for (String string : ids) {
					eq += string + "-";
				}
				eq = eq.substring(0, eq.length()-1);
			}
		}
		return this.testpaperdao.setTestPaper(courseCode, chapterCode, testdate, starttime, endtime,joinedclasses, paperstyle, totalScore, markers, userId, creatime, sc, mc, tf, fb, eq, sscore, mscore, tfscore, fscore, escore);
	}
	
	/**
	 * 获取试卷列表
	 * @param quickPager
	 * @param userId
	 */
	public void getTestpaperList(QuickPager<Map<String,Object>> quickPager,String userId){
		this.testpaperdao.getTestpaperList(quickPager, userId);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = quickPager.getData();
		for (Map<String, Object> map : list) {
			String course = map.get("course").toString();
			course = this.courseandchapterdao.getCourseValue(course);
			map.put("course", course);
		}
	}
	
	/**
	 * 删除试卷
	 * @param id
	 * @return
	 */
	public int deleteTestPaper(String id){
		return this.testpaperdao.deleteTestPaper(id);
	}
	
	/**
	 * 获取学生考试信息
	 * @param quickPager
	 * @param userId
	 */
	public void getStudentTests(QuickPager<Map<String,Object>> quickPager,String userId){
		String classCode = this.userdao.getStudentClasses(userId);
		this.testpaperdao.getStudentTests(quickPager, classCode);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = quickPager.getData();
		for (Map<String, Object> map : list) {
			String course = map.get("course").toString();
			course = this.courseandchapterdao.getCourseValue(course);
			map.put("course", course);
		}
	}
	
	public String getSingleChooseCount(String course,String chapter){
		String all = this.singlechoosedao.getSingleChooseCount(course, chapter);
		String easy = this.singlechoosedao.getSingleChooseLevelCount(course, chapter, "2001");
		String normal = this.singlechoosedao.getSingleChooseLevelCount(course, chapter, "2002");
		String hard = this.singlechoosedao.getSingleChooseLevelCount(course, chapter, "2003");
		return all+"-"+easy+"-"+normal+"-"+hard;
	}
	
	public String getMultiChooseCount(String course,String chapter){
		String all = this.multichoosedao.getMultiChooseCount(course, chapter);
		String easy = this.multichoosedao.getMultiChooseLevelCount(course, chapter, "2001");
		String normal = this.multichoosedao.getMultiChooseLevelCount(course, chapter, "2002");
		String hard = this.multichoosedao.getMultiChooseLevelCount(course, chapter, "2003");
		return all+"-"+easy+"-"+normal+"-"+hard;
	}
	
	public String getTFCount(String course,String chapter){
		String all = this.fillblankdao.getTFCount(course, chapter);
		String easy = this.fillblankdao.getTFLevelCount(course, chapter, "2001");
		String normal = this.fillblankdao.getTFLevelCount(course, chapter, "2002");
		String hard = this.fillblankdao.getTFLevelCount(course, chapter, "2003");
		return all+"-"+easy+"-"+normal+"-"+hard;
	}
	
	public String getFillBlankCount(String course,String chapter){
		String all = this.fillblankdao.getFillBlankCount(course, chapter);
		String easy = this.fillblankdao.getFillBlankLevelCount(course, chapter, "2001");
		String normal = this.fillblankdao.getFillBlankLevelCount(course, chapter, "2002");
		String hard = this.fillblankdao.getFillBlankLevelCount(course, chapter, "2003");
		return all+"-"+easy+"-"+normal+"-"+hard;
	}
	
	public String getEssayQuestionCount(String course,String chapter){
		String all = this.essayquestiondao.getEssayQuestionCount(course, chapter);
		String easy = this.essayquestiondao.getEssayQuestionLevelCount(course, chapter, "2001");
		String normal = this.essayquestiondao.getEssayQuestionLevelCount(course, chapter, "2002");
		String hard = this.essayquestiondao.getEssayQuestionLevelCount(course, chapter, "2003");
		return all+"-"+easy+"-"+normal+"-"+hard;
	}
	
	/**
	 * 获取出题人同一学院的老师
	 * @param userId
	 * @return
	 */
	public List<Map<String,Object>> getmarkers(){
		return this.userdao.getMarkers();
	}
}
