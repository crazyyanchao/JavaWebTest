package com.onlinetest.common;

/**
 * @author ¶¡ Åô
 *
 */
public class MyDate {

	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String getYear(){
		String[] array = date.split("/");
		return array[2].toString();
	}
	
	public String getMonth(){
		String[] array = date.split("/");
		return array[0].toString();
	}
	
	public String getDay(){
		String[] array = date.split("/");
		return array[1].toString();
	}
}
