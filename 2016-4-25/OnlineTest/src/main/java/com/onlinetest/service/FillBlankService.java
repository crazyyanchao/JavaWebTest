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
import com.onlinetest.dao.FillBlankDao;
import com.onlinetest.dao.TeacherCourseDao;
import com.onlinetest.model.FillBlank;
import com.onlinetest.util.StringUtils;

/**
 * @author ∂° ≈Ù
 *
 */
@Service("FillBlankService")
public class FillBlankService {

	@Resource( name = "FillBlankDao")
	private FillBlankDao fillblankdao;
	
	@Resource( name = "CourseAndChapterDao")
	private CourseAndChapterDao courseandchapter;
	
	@Resource( name = "DataDictionaryDao")
	private DataDictionaryDao datadictionarydao;
	
	@Resource( name = "TeacherCourseDao")
	private TeacherCourseDao teachercoursedao;
	
	/**
	 * ÃÓø’Ã‚¡–±Ì
	 * @param quickPager
	 */
	public void getFillBlankList(QuickPager<FillBlank> quickPager,String userId,String courseCode,String dateTime){
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
		this.fillblankdao.getFillBlankList(quickPager,courses,start,end);
		List<FillBlank> list = new ArrayList<FillBlank>();
		list = quickPager.getData();
		if(list != null && !list.isEmpty()){
			for (FillBlank fillBlank : list) {
				String course = fillBlank.getCourse();
				String chapter = fillBlank.getChapter();
				String level = fillBlank.getLevel();
				String fixed = fillBlank.getIsAnswerFixed();
				String answer = fillBlank.getAnswer();
				chapter = "µ⁄"+chapter+"’¬  "+ this.courseandchapter.getChapterValue(course, chapter);
				course = this.courseandchapter.getCourseValue(course);
				level = this.datadictionarydao.getSecondCoudeValue(level);
				if(fixed.equals("3001")){
					answer = this.datadictionarydao.getSecondCoudeValue(answer);
				}
				fixed = this.datadictionarydao.getSecondCoudeValue(fixed);
				fillBlank.setLevel(level);
				fillBlank.setIsAnswerFixed(fixed);
				fillBlank.setCourse(course);
				fillBlank.setChapter(chapter);
				fillBlank.setAnswer(answer);
			}
		}
	}
	
	/**
	 * ÃÌº”ÃÓø’Ã‚
	 * @param data
	 * @return
	 */
	@Transactional
	public Boolean addFillBlank(String data,String userId){
		String[] array = data.split("`");
		for(int i = 0; i < array.length;){
			long createTime = System.currentTimeMillis();
			String creator = userId;
			String course = array[i];
			String chapter = array[i+1];
			String level = array[i+2];
			String title = array[i+3];
			String fixed = array[i+4];
			String ans = array[i+5];
			int x = this.fillblankdao.addFillBlank(course, chapter, level, title, fixed, ans, creator, createTime);
			if(x != 1){
				return false;
			}
			i += 6;
		}
		return true;
	}
	
	/**
	 * –ﬁ∏ƒÃÓø’Ã‚
	 * @param id
	 * @param title
	 * @param fixed
	 * @param ans
	 * @return
	 */
	@Transactional
	public int modifyFillBlank(String id,String title,String fixed,String ans){
		return this.fillblankdao.modifyFillBlank(id, title, fixed, ans);
	}
	
	/**
	 * …æ≥˝ÃÓø’Ã‚
	 * @param id
	 * @return
	 */
	@Transactional
	public int deleteFillBlank(String id){
		return this.fillblankdao.deleteFillBlank(id);
	}
}
