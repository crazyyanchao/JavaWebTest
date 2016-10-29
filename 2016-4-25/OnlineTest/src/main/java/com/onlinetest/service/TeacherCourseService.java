package com.onlinetest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlinetest.dao.CourseAndChapterDao;
import com.onlinetest.dao.TeacherCourseDao;

/**
 * @author ∂° ≈Ù
 *
 */
@Service("TeacherCourseService")
public class TeacherCourseService {

	@Resource(name = "TeacherCourseDao")
	private TeacherCourseDao teachercoursedao;
	
	@Resource(name = "CourseAndChapterDao")
	private CourseAndChapterDao courseandchapterdao;
	
	public List<Map<String, Object>> getCourseListForFilter(String userId){
		String co = this.teachercoursedao.getCourseList(userId);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(co != null){
			String[] courses = co.split("-");
			for (String string : courses) {
				String str = this.courseandchapterdao.getCourseValue(string);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("value", str);
				map.put("code", string);
				list.add(map);
			}
		}else{
			return null;
		}
		return list;
	}
	
	public List<Map<String, Object>> getCourseList(String userId){
		String co = this.teachercoursedao.getCourseList(userId);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(co != null){
			String[] courses = co.split("-");
			for (String string : courses) {
				String str = this.courseandchapterdao.getCourseValue(string);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("value", str);
				list.add(map);
			}
		}else{
			return null;
		}
		return list;
	}
	
	@Transactional
	public int addCourse(String userId,String courses){
		return this.teachercoursedao.addCourse(courses, userId);
	}
	
	@Transactional
	public int deleteCourse(String num,String userId){
		String course = this.teachercoursedao.getCourseList(userId);
		if(course != null){
			String[] courses = course.split("-");
			course = "";
			for (int i = 0; i < courses.length; i++) {
				if( (i+1) != Integer.parseInt(num) ){
					course += courses[i]+"-";
				}
			}
			return this.teachercoursedao.deleteCourse(course, userId);
		}else{
			return -1;
		}
	}
}
