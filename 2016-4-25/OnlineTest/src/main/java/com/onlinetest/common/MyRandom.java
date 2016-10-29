package com.onlinetest.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 丁 鹏
 *
 */
public class MyRandom {

	public List<String> getRandomData(Object[] l,int len){

		List<String> output = new ArrayList<String>();  //选定的要抽取的题号
        Random random = new Random();
        int end = l.length - 1;
        for (int i = 0; i < len; i++)
        {
            int num = random.nextInt(end);
            output.add(l[num].toString());
            l[num] = l[end];
            end--;
        }
        return output;
	}

	public String[] getRan(String topic){
		String[] array = topic.split("-");
		return array;
	}
	
	/**
	 * 不重复随机抽取
	 */
	public List<String> getRandom(Object[] l,int len){
		List<String> output = new ArrayList<String>();  //选定的要抽取的题号
		int[] arr = new int[len];
		RandomDifferent(0,l.length-1,len,arr);
		for(int i = 0; i < len; i++){
			int x = arr[i];
			output.add( String.valueOf(l[x]) );
		}
	    return output;
	}
	/**
	 * 抽取固定范围且不重复的随机抽取法
	 * @param n 起始范围
	 * @param m 结束范围
	 * @param k 数量
	 * @param arrayK 存储数组
	 */
	 private static void RandomDifferent(int n, int m, int k, int[] arrayK){
         int i = 0;
         int a, j;
         Random random = new Random();
         while (i < k)
         {
             a = random.nextInt(m - n + 1) + n;
             for (j = 0; j < i; j++)
             {
                 if (a == arrayK[j])
                 {
                     break;
                 }
             }
             if (j == i)
             {
                 arrayK[i] = a;
                 i++;
             }
         }
     }
}
