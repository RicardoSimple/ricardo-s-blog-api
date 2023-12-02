package com.ricardo.blog.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class PasswordUtil {
    //加密算法
    public static final String ALGORITHM = "PBEWithMD5AndDES";
    //密钥
    public static final String Salt = "63292188";
    //迭代次数
    public static final int ITERATIONCOUNT = 1000;

    public static byte[] getSalt(){
        //实例化安全随机数
        SecureRandom random = new SecureRandom();
        //产出salt
        return random.generateSeed(8);
    }

    public static byte[] getStaticSalt(){
        return Salt.getBytes();
    }
    /**
     * 根据PBE密码生成密钥
     * @param password
     *      生成密钥使用的密码
     */
    private static Key getPBEKey(String password){
        SecretKeyFactory keyFactory;
        SecretKey secretKey = null;
        try{
            keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            //设置密钥参数
            PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
            //生成密钥
            secretKey=keyFactory.generateSecret(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return secretKey;
    }

    /**
     * 加密明文字符串
     * @param plaintext
     *      待加密的字符串
     * @param password
     *      生成密钥使用的密码
     * @param salt
     *      salt
     * @return
     *      加密后的密文字符串
     */
    public static String encrypt(String plaintext,String password,byte[] salt){
        Key key = getPBEKey(password);
        byte[] encipheredData = null;
        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt,ITERATIONCOUNT);
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE,key,pbeParameterSpec);

            encipheredData = cipher.doFinal(plaintext.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytesToHexString(encipheredData);
    }

    /**
     * 解密密文字符串
     * @param ciphertext
     *          待解密的字符串
     * @param password
     *          生成密钥使用的密码
     * @param salt
     *          salt与加密时一致
     * @return
     *          解密后的明文字符串
     */
    public static String decrypt(String ciphertext,String password,byte[] salt){
        Key key = getPBEKey(password);
        byte[] passDec = null;

        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(getStaticSalt(),ITERATIONCOUNT);
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key, pbeParameterSpec);

            passDec = cipher.doFinal(hexStringToBytes(ciphertext));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(passDec);
    }

    /**
     * 将十六进制字符串转换为字节数组
     * @param hexString
     * @return
     */
    private static byte[] hexStringToBytes(String hexString) {
        if(hexString==null||hexString.equals("")){
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length()/2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for(int i=0;i< length;i++){
            int pos = i*2;
            d[i] = (byte)(charToByte(hexChars[pos])<<4 | charToByte(hexChars[pos+1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 将字节数组转换为十六进制字符串
     * @param src
     *      字节数组
     * @return
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if(src==null||src.length<=0){
            return null;
        }
        for(int i = 0;i<src.length;i++){
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if(hv.length()<2){
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
