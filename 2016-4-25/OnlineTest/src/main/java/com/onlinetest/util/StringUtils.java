package com.onlinetest.util;

/**
 * �ж��ַ���Ϊ��
 */
public class StringUtils {
    public static boolean isEmpty(String ...strs){
        boolean isEmpty=false;
        if(strs==null){
            return true;
        }
        for(String str:strs){
          if(null==str||"".equals(str.trim())){
              isEmpty=true;
              break;
          }
        }
        return isEmpty;
    }
}
