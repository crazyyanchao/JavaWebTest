package com.onlinetest.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlinetest.common.QuickPager;
import com.onlinetest.dao.CourseAndChapterDao;
import com.onlinetest.dao.DataDictionaryDao;
import com.onlinetest.dao.MultiChooseDao;
import com.onlinetest.dao.TeacherCourseDao;
import com.onlinetest.model.MultiChoose;
import com.onlinetest.util.StringUtils;

/**
 * @author 丁 鹏
 *
 */
@Service("MultiChooseService")
public class MultiChooseService {

	@Resource( name = "MultiChooseDao")
	private MultiChooseDao multichoosedao;
	
	@Resource( name = "CourseAndChapterDao")
	private CourseAndChapterDao courseandchapter;
	
	@Resource( name = "DataDictionaryDao")
	private DataDictionaryDao datadictionarydao;
	
	@Resource( name = "TeacherCourseDao")
	private TeacherCourseDao teachercoursedao;
	
	/**
	 * 多选题 列表
	 * @param quickPager
	 */
	public void getMultiChooseList(QuickPager<MultiChoose> quickPager,String userId,String courseCode,String dateTime){
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
		this.multichoosedao.getMultiChooseList(quickPager,courses,start,end);
		List<MultiChoose> lists = quickPager.getData();
		if(lists != null && !lists.isEmpty()){
			for (MultiChoose multiChoose : lists) {
				String course = multiChoose.getCourse();
				String chapter = multiChoose.getChapter();
				String level = multiChoose.getLevel();
				chapter = "第"+chapter+"章  "+ this.courseandchapter.getChapterValue(course, chapter);
				course = this.courseandchapter.getCourseValue(course);
				level = this.datadictionarydao.getSecondCoudeValue(level);
				multiChoose.setCourse(course);
				multiChoose.setChapter(chapter);
				multiChoose.setLevel(level);
			}
		}
		
	}
	
	/**
	 * 删除多选题
	 * @param id
	 * @return
	 */
	@Transactional
	public Boolean deleteMultiChoose(String id){
		int x = this.multichoosedao.deleteMultiChoose(id);
		if(x != 1){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 修改多选题
	 * @param id
	 * @param title
	 * @param ans1
	 * @param ans2
	 * @param ans3
	 * @param ans4
	 * @param ans5
	 * @param ans6
	 * @param tans
	 * @return
	 */
	@Transactional
	public int modifyMultiChoose(String id,String title,String ans1,String ans2,String ans3,String ans4,String ans5,String ans6,String tans){
		int len = tans.length();
		String[] t = {"","","","","",""};
		int i = 0;
		for(int j = 0; j < len; j++){
			String x = tans.substring(j, j+1);
			if(!x.equals(" ")){
				t[i] = x.toUpperCase();
				i++;
			}
		}
		Arrays.sort(t);
		String trueans = "";
		for (String str : t) {
			trueans += str;
		}
		return this.multichoosedao.modifyMultiChose(id, title, ans1, ans2, ans3, ans4, ans5, ans6, trueans);
	}
	
	/**
	 * 添加多选题
	 * @param data
	 * @return
	 */
	@Transactional
	public Boolean addMultiChoose(String data,String userId){
		String[] array = data.split("`");
		String[] t = {"","","","","",""};
		int k = 0;
		for(int i = 0; i < array.length;){
			long createTime = System.currentTimeMillis();
			String creator = userId;
			String courseCode = array[i];
			String chapterCode = array[i+1];
			String level = array[i+2];
			String title = array[i+3];
			String ans1 = array[i+4];
			String ans2 = array[i+5];
			String ans3 = array[i+6];
			String ans4 = array[i+7];
			String ans5 = array[i+8];
			String ans6 = array[i+9];
			String tans = array[i+10];
			for(int j = 0; j < tans.length(); j++){
				String x = tans.substring(j, j+1);
				if(!x.equals(" ")){
					t[k] = x.toUpperCase();
					k++;
				}
			}
			Arrays.sort(t);
			String trueans = "";
			for (String str : t) {
				trueans += str;
			}
			int x = this.multichoosedao.addMultiChoose(courseCode, chapterCode, level, title, ans1, ans2, ans3, ans4, ans5, ans6, trueans,creator,createTime);
			if(x != 1){
				return false;
			}
			i += 11;
		}
		return true;
	}
}
