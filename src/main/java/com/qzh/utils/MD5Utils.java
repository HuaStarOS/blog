package com.qzh.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: qzh
 * @Date: 2020/12/24 23:27
 */
public class MD5Utils {
    /**
     * MD5加密类
     * @param str ：要加密的字符串
     * @return ：加密后的字符串
     */
    public static  String code(String str){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] byteDigest=md.digest();
            int i;
            StringBuffer buffer=new StringBuffer("");
            for (int offest=0;offest<byteDigest.length;offest++){
                i=byteDigest[offest];
                if (i<0){
                    i+=256;
                }
                if (i<16){
                    buffer.append("0");
                }
                buffer.append(Integer.toHexString(i));
            }
            //32位加密
            return buffer.toString();
            //16位加密
//            return buffer.toString().substring(8,24);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(code("123456"));
    }
}
