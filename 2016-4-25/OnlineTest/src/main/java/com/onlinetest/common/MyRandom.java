package com.onlinetest.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author �� ��
 *
 */
public class MyRandom {

	public List<String> getRandomData(Object[] l,int len){

		List<String> output = new ArrayList<String>();  //ѡ����Ҫ��ȡ�����
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
	 * ���ظ������ȡ
	 */
	public List<String> getRandom(Object[] l,int len){
		List<String> output = new ArrayList<String>();  //ѡ����Ҫ��ȡ�����
		int[] arr = new int[len];
		RandomDifferent(0,l.length-1,len,arr);
		for(int i = 0; i < len; i++){
			int x = arr[i];
			output.add( String.valueOf(l[x]) );
		}
	    return output;
	}
	/**
	 * ��ȡ�̶���Χ�Ҳ��ظ��������ȡ��
	 * @param n ��ʼ��Χ
	 * @param m ������Χ
	 * @param k ����
	 * @param arrayK �洢����
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
