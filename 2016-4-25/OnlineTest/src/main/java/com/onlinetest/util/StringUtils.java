package com.onlinetest.util;

/**
 * ÅÐ¶Ï×Ö·û´®Îª¿Õ
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
