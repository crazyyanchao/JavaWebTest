package com.onlinetest.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlinetest.common.QuickPager;
import com.onlinetest.dao.CourseAndChapterDao;
import com.onlinetest.dao.DataDictionaryDao;
import com.onlinetest.dao.EssayQuestionDao;
import com.onlinetest.dao.TeacherCourseDao;
import com.onlinetest.model.EssayQuestion;
import com.onlinetest.util.StringUtils;

/**
 * @author 丁 鹏
 *
 */
@Service("EssayQuestionService")
public class EssayQuestionService {

	@Resource( name = "EssayQuestionDao")
	private EssayQuestionDao essayquestiondao;
	
	@Resource( name = "CourseAndChapterDao")
	private CourseAndChapterDao courseandchapter;
	
	@Resource( name = "DataDictionaryDao")
	private DataDictionaryDao datadictionarydao;
	
	@Resource( name = "TeacherCourseDao")
	private TeacherCourseDao teachercoursedao;
	
	/**
	 * 问答题列表
	 * @param quickPager
	 */
	public void getEssayQuestionList(QuickPager<EssayQuestion> quickPager,String userId,String courseCode,String dateTime){
		String co = this.teachercoursedao.getCourseList(userId);
		List<String> courses = new ArrayList<String>();
		if(co != null){
			String[] c = co.split("-"); 
			for(String str : c){
				courses.add(str);
			}
		}else{
			courses = null;
		}
		if(!StringUtils.isEmpty(courseCode)){
			courses.clear();
			courses.add(courseCode);
		}
		long start = 0;
		long end = 0;
		if(!StringUtils.isEmpty(dateTime)){
			String[] d = dateTime.split("-");
			java.sql.Timestamp time = new java.sql.Timestamp(new java.util.Date().getTime());
			String currTime = String.valueOf(time).substring(11,19);
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy HH:mm:ss");
			try {
				if(!StringUtils.isEmpty(d[0]) && !StringUtils.isEmpty(d[1])){
					start = sdf.parse(d[0]+" "+currTime).getTime();
					end = sdf.parse(d[1]+" "+currTime).getTime();
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		this.essayquestiondao.getEssayQuestionList(quickPager,courses,start,end);
		List<EssayQuestion> list = new ArrayList<EssayQuestion>();
		list = quickPager.getData();
		if(list != null && !list.isEmpty()){
			for (EssayQuestion eq : list) {
				String course = eq.getCourse();
				String chapter = eq.getChapter();
				String level = eq.getLevel();
				chapter = "第"+chapter+"章  "+ this.courseandchapter.getChapterValue(course, chapter);
				course = this.courseandchapter.getCourseValue(course);
				level = this.datadictionarydao.getSecondCoudeValue(level);
				eq.setCourse(course);
				eq.setChapter(chapter);
				eq.setLevel(level);
			}
		}
	}
	
	@Transactional
	public Boolean addEssayQuestion(String data,String userId){
		String[] array = data.split("`");
		for(int i = 0; i < array.length;){
			long createTime = System.currentTimeMillis();
			String creator = userId;
			String course = array[i];
			String chapter = array[i+1];
			String level = array[i+2];
			String title = array[i+3];
			String ans = array[i+4];
			int x = this.essayquestiondao.addEssayQuestion(course, chapter, level, title, ans, creator, createTime);
			if(x != 1){
				return false;
			}
			i += 5;
		}
		return true;
	}
	
	@Transactional
	public int modifyEssayQuestion(String id,String title,String answer){
		return this.essayquestiondao.modifyEssayQuestion(id, title, answer);
	}
	
	@Transactional
	public int deleteEssayQuestion(String id){
		return this.essayquestiondao.deleteEssayQuestion(id);
	}
}
