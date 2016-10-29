package library.util;

import java.util.List;

public class PageInfo<T> {
	private int first;
	private int last;
	private int start;
	private int end;
	private int current;
	private int pageSize;
	private List<T> datas;

	public PageInfo(int current, int end, int pageSize) {
		this.first = 1;
		this.end = end;
		this.pageSize = pageSize;
		this.current = current;
		int half = pageSize / 2;
		start = current - half <= 1 ? 1 : current - half;
		end = current + half > last ? last : current + half;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

}
