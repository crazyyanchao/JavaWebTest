/**
 * 
 */
package com.onlinetest.common;

import java.util.Random;

/**
 * @author ����
 *
 */
public class FixedLengthRandom {

	public String getFixLenthRandom() {  //Ĭ��Ϊ6λ������  
	    Random rm = new Random();  
	    // ��������  
	    double pross = (1 + rm.nextDouble()) * Math.pow(10, 6); 
	    // ����õĻ�������ת��Ϊ�ַ���  
	    String fixLenthString = String.valueOf(pross);  
	    // ���ع̶��ĳ��ȵ������  
	    return fixLenthString.substring(1, 6 + 1);  
	}
	
	public String getFixLenthRandom(int strLength) {    
	    Random rm = new Random();  
	    // ��������  
	    double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength); 
	    // ����õĻ�������ת��Ϊ�ַ���  
	    String fixLenthString = String.valueOf(pross);  
	    // ���ع̶��ĳ��ȵ������  
	    return fixLenthString.substring(2, strLength + 2);  
	}
}
