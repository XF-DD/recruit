package qdu.java.recruit.service.impl;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * 获得测试账号密码
 * 123456
 * 4QrcOUm6Wau+VuBX8g+IPg==
 * @author PocketKnife
 * @create 2020-05-09  14:30
 */
public class UserServiceImplTest {

    public String EncodingByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();

        //加密后的字符串
        String encStr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        return encStr;
    }

    public static void main(String[] args) {
        UserServiceImplTest userServiceImplTest=new UserServiceImplTest();
        try {
            String password =userServiceImplTest.EncodingByMd5("123456");
            System.out.println(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}


