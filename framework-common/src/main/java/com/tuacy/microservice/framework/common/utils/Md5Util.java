package com.tuacy.microservice.framework.common.utils;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Iterator;

/**
 * MD5工具类
 */
@Slf4j
public class Md5Util {

    private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    /**
     * 获取MD5校验码，默认为UTF-8
     */
    public static String MD5(String s){
        return MD5(s,"utf-8");
    }
    /**
     * 获得UTF-8，可传入特定编码方式
     */
    public static String MD5(String s,String charset) {

        try {
            byte[] btInput = s.getBytes(charset);
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String encode(String data, String charsetStr) {
        try {
            Charset charset = Charset.forName(charsetStr);
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update("test".getBytes(charset));
            byte[] digest = md.digest();
            String checksum = DatatypeConverter.printHexBinary(digest).toUpperCase();
            return checksum;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static String encode(String data, Charset charset) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update("test".getBytes(charset));
            byte[] digest = md.digest();
            String checksum = DatatypeConverter.printHexBinary(digest).toUpperCase();
            return checksum;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }


    public static void main(String[] args) {
        String signKey = "signKey";
        String sb = "原数据";
        String sign = MD5(sb.toString() + signKey);
        System.out.println(sign);
    }
}