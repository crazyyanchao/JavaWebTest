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
import com.onlinetest.dao.SingleChooseDao;
import com.onlinetest.dao.TeacherCourseDao;
import com.onlinetest.model.SingleChoose;
import com.onlinetest.util.StringUtils;

/**
 * @author 丁 鹏
 *
 */
@Service("SingleChooseService")
public class SingleChooseService {
	
	@Resource( name = "SingleChooseDao")
	private SingleChooseDao singlechoosedao;
	
	@Resource( name = "CourseAndChapterDao")
	private CourseAndChapterDao courseandchapter;
	
	@Resource( name = "DataDictionaryDao")
	private DataDictionaryDao datadictionarydao;
	
	@Resource( name = "TeacherCourseDao")
	private TeacherCourseDao teachercoursedao;
	
	/**
	 * 单选题 列表
	 * @param quickPager
	 */
	public void getSingleChooseList(QuickPager<SingleChoose> quickPager,String userId,String courseCode,String dateTime){
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
		this.singlechoosedao.getSingleChooseList(quickPager,courses,start,end);
		List<SingleChoose> lists = new ArrayList<SingleChoose>();
		lists = quickPager.getData();
		if(lists != null && !lists.isEmpty()){
			for (SingleChoose singleChoose : lists) {
				String course = singleChoose.getCourse();
				String chapter = singleChoose.getChapter();
				String level = singleChoose.getLevel();
				chapter = "第"+chapter+"章  "+ this.courseandchapter.getChapterValue(course, chapter);
				course = this.courseandchapter.getCourseValue(course);
				level = this.datadictionarydao.getSecondCoudeValue(level);
				singleChoose.setCourse(course);
				singleChoose.setChapter(chapter);
				singleChoose.setLevel(level);
			}
		}
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@Transactional
	public int deleteSingleChoose(String id){
		return this.singlechoosedao.deleteSingleChoose(id);
	}
	/**
	 * 修改
	 * @param id
	 * @param title
	 * @param ans1
	 * @param ans2
	 * @param ans3
	 * @param ans4
	 * @param trueans
	 * @return
	 */
	@Transactional
	public int modifySingleChose(String id,String title,String ans1,
			String ans2,String ans3,String ans4,String trueans){
		return this.singlechoosedao.modifySingleChose(id, title, ans1, ans2, ans3, ans4, trueans);
	}
	
	/**
	 * 添加单选题
	 * @param data
	 * @return
	 */
	@Transactional
	public Boolean addSingleChoose(String data,String userId){
		String[] array = data.split("`");
		for (int i = 0; i < array.length;) {
			long createTime = System.currentTimeMillis();
			String creator = userId;
			int x = this.singlechoosedao.addSingleChoose(array[i], array[i+1], array[i+2], array[i+3], array[i+4], array[i+5], array[i+6], array[i+7], array[i+8], creator, createTime);
			if( x != 1){
				return false;
			}
			i += 9;
		}
		return true;
	}
}
