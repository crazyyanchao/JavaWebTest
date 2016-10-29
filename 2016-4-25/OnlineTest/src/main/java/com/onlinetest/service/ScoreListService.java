package com.onlinetest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.onlinetest.common.QuickPager;
import com.onlinetest.dao.CourseAndChapterDao;
import com.onlinetest.dao.TeacherCourseDao;
import com.onlinetest.dao.TestDao;
import com.onlinetest.dao.TestPaperDao;
import com.onlinetest.util.StringUtils;

/**
 * @author ∂° ≈Ù
 *
 */
@Service("ScoreListService")
public class ScoreListService {

	@Resource(name = "TestDao")
	private TestDao testdao;
	
	@Resource(name = "TestPaperDao")
	private TestPaperDao testpaperdao;
	
	@Resource(name= "CourseAndChapterDao")
	private CourseAndChapterDao coursedao;
	
	@Resource(name = "TeacherCourseDao")
	private TeacherCourseDao teachercoursedao;
	
	public void getScoreListOfAll(String userId,String course, String stuCode, String Date,
			QuickPager<Map<String,Object>> quickPager){
		String co = this.teachercoursedao.getCourseList(userId);
		if(co != null){
			String[] courses = co.split("-");
			List<String> initcourse = new ArrayList<String>();
			for (String str : courses) {
				initcourse.add(str);
			}
			List<String> ids = new ArrayList<String>();
			String[] d = new String[2];
			if(!StringUtils.isEmpty(Date)){
				String[] s = Date.split("-");
				d[0] = s[0];
				d[1] = s[1];
			}
			ids = this.testpaperdao.getIdByDate(d[0], d[1],initcourse);
			this.testdao.getStudentScoreList(quickPager, stuCode, ids);
			List<Map<String,Object>> list = quickPager.getData();
			for(int i = 0; i < list.size(); i++){
				Map<String, Object> map = new HashMap<String,Object>();
				map = list.get(i);
				map.remove("classCode");
				String testpaperid = map.get("testPaperId").toString();
				map.remove("testPaperId");
				Map<String,Object> m = new HashMap<String,Object>();
				m = this.testpaperdao.getCourseTimeById(testpaperid);
				String date = m.get("date").toString();
				String courseCode  = this.testpaperdao.getCourse(testpaperid);
				String totalScore = this.testpaperdao.getTotalScore(testpaperid);
				map.put("totalScore", totalScore);
				map.put("date", date);
				map.put("courseValue", this.coursedao.getCourseValue(courseCode));
				if(!StringUtils.isEmpty(course)){
					if(!courseCode.equals(course)){
						list.remove(map);
						i--;
					}
				}
			}
			
		}else{
			quickPager.setData(null);
		}
	}
}
