package com.onlinetest.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 丁 鹏
 *
 */
@Controller("JumpPageController")
public class JumpPageController {

	/*-------- 教师 --------*/
	@RequestMapping( value = "/teacher/home")
	public String toHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/home.jsp";
	}
	
	@RequestMapping( value = "/teacher/password")
	public String toPassWord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/password.jsp";
	}
	
	@RequestMapping( value = "/teacher/personinfo")
	public String toPersonInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/personinfo.jsp";
	}
	
	@RequestMapping( value = "/teacher/testpaperdesign")
	public String toTestPaperDesign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/testPaperDesign.jsp";
	}
	
	@RequestMapping( value = "/teacher/testpaperlist")
	public String toTestPaperList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/testPaperList.jsp";
	}
	
	@RequestMapping( value = "/teacher/markpaper")
	public String toMarkPaper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/markPaper.jsp";
	}
	
	@RequestMapping( value = "/teacher/singlechoose")
	public String toSingleChoose(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/SingleChoose.jsp";
	}
	
	@RequestMapping( value = "/teacher/multichoose")
	public String toMultiChoose(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/MultiChoose.jsp";
	}
	
	@RequestMapping( value = "/teacher/fillblank")
	public String toFillBlank(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/FillBlank.jsp";
	}
	
	@RequestMapping( value = "/teacher/essayquestion")
	public String toEssayQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/EssayQuestion.jsp";
	}
	
	@RequestMapping( value = "/teacher/scorelist")
	public String toScoreList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/ScoreList.jsp";
	}
	
	@RequestMapping( value = "/teacher/searchscore")
	public String toSearchScore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/SearchScore.jsp";
	}
	
	@RequestMapping( value = "/teacher/coursemanage")
	public String toCourseManage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/CourseManage.jsp";
	}
	
	@RequestMapping( value = "/teacher/teachercourse")
	public String toTeacherCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/teacherCourse.jsp";
	}
	
	/*-------学生------*/
	
	@RequestMapping( value = "/student/home")
	public String toStudentHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/stu_home.jsp";
	}
	
	@RequestMapping( value = "/student/scorelist")
	public String toStudentScoreList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/stu_scoreList.jsp";
	}
	
	@RequestMapping( value = "/student/personinfo")
	public String toStudentPersonInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/stu_personInfo.jsp";
	}
	
	@RequestMapping( value = "/student/password")
	public String toStudentPassWord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/stu_password.jsp";
	}
	
	@RequestMapping( value = "/student/testlist")
	public String toStudentTestList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/stu_testList.jsp";
	}
	
	/**
	 * 跳转考试页面
	 */
	@RequestMapping( value = "/student/test")
	public String toStudentTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if( request.getSession().getAttribute("testPaperId") == null ){
			return "/404.jsp";
		}
		return "/stu_test.jsp";
	}
	
	/*-----管理员-----*/
	@RequestMapping( value = "/admin/adminlist")
	public String toAdminList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/AdminList.jsp";
	}
	
	@RequestMapping( value = "/admin/classlist")
	public String toClassList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/ClassList.jsp";
	}
	
	@RequestMapping( value = "/admin/teacherlist")
	public String toTeacherList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/TeacherList.jsp";
	}
	
	@RequestMapping( value = "/admin/studentlist")
	public String toStudentList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/StudentList.jsp";
	}
	
	@RequestMapping( value = "/admin/academylist")
	public String toAcademyList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/AcademyList.jsp";
	}
	
	@RequestMapping( value = "/admin/departmentlist")
	public String toDepartmentList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/DepartmentList.jsp";
	}
	
	@RequestMapping( value = "/admin/home")
	public String toAdminHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/admin_home.jsp";
	}
	
	@RequestMapping( value = "/admin/personinfo")
	public String toAdminPersonInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/admin_personinfo.jsp";
	}
	
	@RequestMapping( value = "/admin/password")
	public String toAdminPassWord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		return "/admin_password.jsp";
	}
}
