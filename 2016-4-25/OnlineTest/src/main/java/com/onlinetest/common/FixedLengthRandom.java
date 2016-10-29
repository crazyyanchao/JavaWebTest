/**
 * 
 */
package com.onlinetest.common;

import java.util.Random;

/**
 * @author 丁鹏
 *
 */
public class FixedLengthRandom {

	public String getFixLenthRandom() {  //默认为6位数长度  
	    Random rm = new Random();  
	    // 获得随机数  
	    double pross = (1 + rm.nextDouble()) * Math.pow(10, 6); 
	    // 将获得的获得随机数转化为字符串  
	    String fixLenthString = String.valueOf(pross);  
	    // 返回固定的长度的随机数  
	    return fixLenthString.substring(1, 6 + 1);  
	}
	
	public String getFixLenthRandom(int strLength) {    
	    Random rm = new Random();  
	    // 获得随机数  
	    double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength); 
	    // 将获得的获得随机数转化为字符串  
	    String fixLenthString = String.valueOf(pross);  
	    // 返回固定的长度的随机数  
	    return fixLenthString.substring(2, strLength + 2);  
	}
}
