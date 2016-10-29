package com.onlinetest.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * @author ����
 * 
 */
public class QuickPager<T> {

	private int currPage = 1;    // ��ǰҳ��
	private int pageSize = 10;   // ÿҳ��С
	private int totalRows = 0;   // �ܵ�����
	private int totalPages = 0;  // �ܵ�ҳ��
	
	private boolean ispage = true;  //�Ƿ�ִ�з�ҳ��ѯ��

	private List<T> data = null;

	public QuickPager() {

	}
	

	public QuickPager(int currpage) {
		this.currPage = currpage;
	}

	public QuickPager(Integer currpage, Integer pageSize) {
		if(currpage == null && pageSize==null ) {
			ispage = false;
		} else {
			if(currpage == null || currpage == 0){
				currpage = 1;
			}
			if(pageSize == null || pageSize == 0) {
				pageSize = 10;
			}
			this.currPage = currpage;
			this.pageSize = pageSize;
		}
	}

	public QuickPager(String currPage, String pageSize) {
		try {
			this.currPage = StringUtils.isEmpty(currPage) ? 1 : Integer.parseInt(currPage);
			this.pageSize = StringUtils.isEmpty(pageSize) ? 10 : Integer.parseInt(pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	public void addData(T ele) {
		if(this.data == null ) {
			this.data = new ArrayList<T>();
		}
		this.data.add(ele);
	}

	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getMaxLine() {
		return pageSize;
	}


	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
		this.totalPages = (this.totalRows == 0 ? 0 : (this.totalRows % this.pageSize != 0 ? this.totalRows / this.pageSize + 1 : this.totalRows / this.pageSize));
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public String getSqlLimit() {
		if( ! this.ispage ) {
			return "";
		}
		return " limit " + ((this.getCurrPage() - 1) * this.getMaxLine()) + " , " + this.getMaxLine();
	}

	/**
	 * �����Ƿ�ִ�з�ҳ��ѯ��
	 * @param ispage
	 */
	public void setIspage(boolean ispage) {
		this.ispage = ispage;
	}
	
	public boolean getIspage() {
		return ispage;
	}

}
