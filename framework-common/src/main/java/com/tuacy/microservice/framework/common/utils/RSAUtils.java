package com.tuacy.microservice.framework.common.utils;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: microservice-framework
 * @description: RSA工具类
 * @author: hushenggui
 *
 * java security 包下 公钥加密私钥解密
 *
 * hutool包下 则是私钥加密 公钥解密
 *
 *
 *
 *
 * @create: 2020-09-04 15:58
 **/
public class RSAUtils {

    public static final String CHARSET = "UTF-8";
    public static final String RSA_ALGORITHM = "RSA";


    public static Map<String, String> createKeys(int keySize){
        //为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator kpg;
        try{
            kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        }catch(NoSuchAlgorithmException e){
            throw new IllegalArgumentException("No such algorithm-->[" + RSA_ALGORITHM + "]");
        }

        //初始化KeyPairGenerator对象,密钥长度
        kpg.initialize(keySize);
        //生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();
        //得到公钥
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.encodeBase64URLSafeString(publicKey.getEncoded());
        //得到私钥
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64.encodeBase64URLSafeString(privateKey.getEncoded());
        Map<String, String> keyPairMap = new HashMap<String, String>();
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);

        return keyPairMap;
    }

    /**
     * 得到公钥
     * @param publicKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        return key;
    }

    /**
     * 得到私钥
     * @param privateKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        return key;
    }

    /**
     * 公钥加密
     * @param data
     * @param publicKey
     * @return
     */
    public static String publicEncrypt(String data, RSAPublicKey publicKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), publicKey.getModulus().bitLength()));
        }catch(Exception e){
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥解密
     * @param data
     * @param privateKey
     * @return
     */

    public static String privateDecrypt(String data, RSAPrivateKey privateKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), privateKey.getModulus().bitLength()), CHARSET);
        }catch(Exception e){
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥加密
     * @param data
     * @param privateKey
     * @return
     */

    public static String privateEncrypt(String data, RSAPrivateKey privateKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), privateKey.getModulus().bitLength()));
        }catch(Exception e){
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 公钥解密
     * @param data
     * @param publicKey
     * @return
     */

    public static String publicDecrypt(String data, RSAPublicKey publicKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), publicKey.getModulus().bitLength()), CHARSET);
        }catch(Exception e){
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize){
        int maxBlock = 0;
        if(opmode == Cipher.DECRYPT_MODE){
            maxBlock = keySize / 8;
        }else{
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream
                out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try{
            while(datas.length > offSet){
                if(datas.length-offSet > maxBlock){
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                }else{
                    buff = cipher.doFinal(datas, offSet, datas.length-offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        }catch(Exception e){
            throw new RuntimeException("加解密阀值为["+maxBlock+"]的数据时发生异常", e);
        }
        byte[] resultDatas = out.toByteArray();
        IOUtils.closeQuietly(out);
        return resultDatas;
    }

    /**
     * 加密
     * @param orgData
     * @param privateKey
     * @return
     */
    public static String encrypt(String orgData, String privateKey) {
        cn.hutool.crypto.asymmetric.RSA rsa = SecureUtil.rsa(privateKey, null);
        return new String(Base64Util.encode(rsa.encrypt(orgData, KeyType.PrivateKey)));
    }

    /**
     * 解密
     * @param signData
     * @param publicKey
     * @return
     */
    public static String decrypt(String signData, String publicKey) {
        RSA rsa = SecureUtil.rsa(null, publicKey);
        return new String(rsa.decrypt(signData, KeyType.PublicKey));
    }

    public static void main (String[] args) throws Exception {
        Map<String, String> keyMap = RSAUtils.createKeys(1024);
        String  publicKey = keyMap.get("publicKey");
        String  privateKey = keyMap.get("privateKey");
        System.out.println("公钥: \n\r" + publicKey);
        System.out.println("私钥： \n\r" + privateKey);

        System.out.println("公钥加密——私钥解密");
        String str = "code_cayden";
        System.out.println("\r明文：\r\n" + str);
        System.out.println("\r明文大小：\r\n" + str.getBytes().length);
        String encodedData = RSAUtils.publicEncrypt(str, RSAUtils.getPublicKey(publicKey));
        System.out.println("密文：\r\n" + encodedData);
        String decodedData = RSAUtils.privateDecrypt(encodedData, RSAUtils.getPrivateKey(privateKey));
        System.out.println("解密后文字: \r\n" + decodedData);

       /* String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJNhIH9zgKn";
        String sign = "3CBFE2B76CEA683205B339CDA027EEFB";
        String decodedData = RSAUtils.decrypt(sign,privateKey);
        System.out.println("解密后文字: \r\n" + decodedData);*/

       /*String publickey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCTYSB_c4Cp_lkgiEWSuWnspK8degbkClvL94ZPhi3kU326bSpsMwt1O5WEiE-BztlzGFqA3hZr-On0CHxqZLxkUxwjxAmSm9p0mf_MpA__iZSDC3FiL9KLt92kYLYNR3U7GFYJVDLO_ZmcldXcv1qWYZFtaMp4KVgu_icnZwlquQIDAQAB";
       String priviteKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJNhIH9zgKn-WSCIRZK5aeykrx16BuQKW8v3hk-GLeRTfbptKmwzC3U7lYSIT4HO2XMYWoDeFmv46fQIfGpkvGRTHCPECZKb2nSZ_8ykD_-JlIMLcWIv0ou33aRgtg1HdTsYVglUMs79mZyV1dy_WpZhkW1oyngpWC7-JydnCWq5AgMBAAECgYB-WdS8r89s7iyHIY1UHzXatc2T3dwugVr14P37C7QofhPaUmD-Zjy6gxzKhPp09VujuwtMVBqw8rPQjb3skPSZVuc0cTSe9Du6AYfM08PLNtXbVYFbQRv8LFAdqAGFTPVJt9O9sUe6Oh-R0i2Dv9n3Vy1To-puj-6KIWjWfmafKQJBANq7kHxA_P6Am_FUbMNdzTbBM8K8SM2fwB65h-mRCzchHXT0aGlzIZEvBfs4eYAkP2gOTo8R6lGktq5zkVwVk-MCQQCsfVsZc0WJ1npm0Em6HEyS9Hg602d3ndEcJsWvhyULel4KeXzO6ZRRqagjQthmucrkBYuYLs5UqoVoBxgljmGzAkEA0wXfuDCOpA555FLZ94W4pRmyES7LYi28EShXWlwHoPE-1QWy8gEUIlhmgd8TQTA3R_F9kwPqt0ecwyNV2qwqlwJAVVaPw7-sH83lMQjL8TAfEnWxJimBf0StOGhWVclfIpagAD6q6RPXNa_iZ8-ikPBK2RWFQv2lR242IomgALS40QJAHiNCrYJp8LxMwSyZp_MYOfRrvriiL0SMBBR0-ilDOTQSYk_ms82MBZwRFvi_oUxKEuFw_JRBE-j3w1gM-G_iNw";

       String salt = "123";

       String enStr =  RSAUtils.encrypt(salt,priviteKey);
       String deStr = RSAUtils.decrypt(enStr,publickey);
        System.out.println(deStr);*/


    }
}
