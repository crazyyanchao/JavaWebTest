package com.onlinetest.common;

/**
 * @author ∂° ≈Ù
 *
 */
public class MyTime {

	private String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public String getHour(){
		String[] array = time.split(":");
		return array[0];
	}
	
	public String getMinute(){
		String[] array = time.split(":");
		return array[1];
	}
}
