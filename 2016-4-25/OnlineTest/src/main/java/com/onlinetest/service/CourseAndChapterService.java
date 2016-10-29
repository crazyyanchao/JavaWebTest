package com.onlinetest.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlinetest.common.QuickPager;
import com.onlinetest.dao.AcademyDepartmentDao;
import com.onlinetest.dao.CourseAndChapterDao;

/**
 * @author �� ��
 *
 */
@Service("CourseAndChapterService")
public class CourseAndChapterService {
	
	@Resource(name = "CourseAndChapterDao")
	private CourseAndChapterDao courseandchapterdao;
	
	@Resource(name = "AcademyDepartmentDao")
	private AcademyDepartmentDao academydepartmentdao;
	
	/**
	 * �����γ�
	 * @param value
	 * @return
	 */
	public List<Map<String,Object>> searchCourse(String value){
		return this.courseandchapterdao.searchCourse(value);
	}

	/**
	 * ���ݿγ̱�ţ���ȡ�½�
	 * @param courseCode
	 * @return
	 */
	public  List<Map<String, Object>> getChapter(String courseCode){
		return this.courseandchapterdao.getChapter(courseCode);
	}
	
	/**
	 * �γ��б�
	 * @param quickPager
	 */
	public void courseList(QuickPager<Map<String,Object>> quickPager){
		this.courseandchapterdao.courseList(quickPager);
	}
	
	/**
	 * ��ӿγ�
	 * @param data
	 * @return
	 */
	@Transactional
	public Boolean addCourse(String data){
		String[] array = data.split("`");
		for (int i = 0; i < array.length; i++) {
			String courseValue = array[i];
			String code = this.courseandchapterdao.searchCoursePrefix();
			String n = "";
			if(code != null){
				long x = Long.parseLong(code) + 1;
				n = String.valueOf(x);
				int l = 8 - n.length();
				for (int j = 0; j < l; j++) {
					n = "0" + n;
				}
			}else{
				n = "00000001";
			}
			int r = this.courseandchapterdao.addCourse( n, courseValue);
			if( r != 1){
				return false;
			}
		}
		return true;
	}
	
//	/**
//	 * ѧԺ�������д
//	 * @return
//	 */
//	public List<Map<String, Object>> getAcademyCodeValue(){
//		return this.academydepartmentdao.getDepartValue();
//	}
	
	/**
	 * ɾ���γ�
	 * @param courseCode
	 * @return
	 */
	@Transactional
	public Boolean deleteCourse(String courseCode){
		int x = this.courseandchapterdao.deleteCourse(courseCode);
		if(x != 1){
			return false;
		}
		return true;
	}
	
	/**
	 * �½��б�
	 * @param quickPager
	 */
	public void chapterList(QuickPager<Map<String,Object>> quickPager,String courseCode){
		this.courseandchapterdao.chapterList(quickPager, courseCode);
	}
	
	/**
	 * ����½�
	 * @param data
	 * @return
	 */
	@Transactional
	public Boolean addChapter(String data){
		String[] array = data.split("`");
		for (int i = 0; i < array.length;) {
			String chapterValue = array[i];
			String courseCode = array[i+1];
			String code = this.courseandchapterdao.searchChapterLastOne(courseCode);
			String x = "";
			if(code != null){
				int ncode = Integer.parseInt(code);
				ncode++;
				if(ncode < 10){
					x = "0" + String.valueOf(ncode);
				}else{
					x = String.valueOf(ncode);
				}
			}else{
				x = "01";
			}
			int r = this.courseandchapterdao.addChapter(courseCode, x, chapterValue);
			if( r != 1){
				return false;
			}
			i += 2;
		}
		return true;
	}
	
	/**
	 * �޸��½�Ŀ¼��Ϣ
	 * @param chapterValue
	 * @param courseCode
	 * @param chapterCode
	 * @return
	 */
	@Transactional
	public Boolean modifyChapter(String chapterValue,String courseCode,String chapterCode){
		int x = this.courseandchapterdao.modifyChapter(chapterValue, courseCode, chapterCode);
		if(x != 1){
			return false;
		}
		return true;
	}
	
	/**
	 * ɾ���½���Ϣ
	 * @param courseCode
	 * @param chapterCode
	 * @return
	 */
	@Transactional
	public Boolean deleteChapter(String courseCode,String chapterCode){
		int x = this.courseandchapterdao.deleteChapter(courseCode, chapterCode);
		if(x != 1){
			return false;
		}
		return true;
	}

}
