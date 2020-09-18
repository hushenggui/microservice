package com.tuacy.microservice.framework.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import sun.misc.BASE64Decoder;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * @program: microservice-framework
 * @description: jwt工具类
 * @author: hushenggui
 * @create: 2020-09-08 14:08
 **/
public class JWTUtil {
    public static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCjOrTFJTOcpkD1wOUEkQOnJxZQU-7S8S8ONKibzb88XMU0KaSP1oethQQ9-916YQU2eG1PBeErAZM0lFD_BincdizfG4rcm5U2fQM5AxopKYvBzS_-EohxXRZhW4yu1Mkqq2C5NEss2Yi2dd0tPhMRSL-HgK6t-deXK2mO-NLoRwIDAQAB";
    public static final String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKM6tMUlM5ymQPXA5QSRA6cnFlBT7tLxLw40qJvNvzxcxTQppI_Wh62FBD373XphBTZ4bU8F4SsBkzSUUP8GKdx2LN8bityblTZ9AzkDGikpi8HNL_4SiHFdFmFbjK7UySqrYLk0SyzZiLZ13S0-ExFIv4eArq3515craY740uhHAgMBAAECgYA8LStxtupVKRGvyEJmMZQYLpfSW28jg1kmspEqh2qmNUlalrOdd6ijdvN-anlXYadLUpBx3qgJc9YyPy-xJTOPBYfaWL0AzNjNkOO23m95bmN5z6p73x2IUNJw6eYszaxNZ91pGI5d_rc1SuQpUXR4-w0-Xlll3nxRKsU6Ns-R-QJBANR7oAhPRbMglwe99qAcxXV0H25cYQK_Ajgs9ok3shZKFlbQooH1Omphi73UZihex5ZfbSPGSHMxH9jN9afsQfMCQQDEqL1LSlJ-cLgmKTG1J6rSkPOdqnnLD0En1AGitud9sVKm3Fh0BKbdlrIpRm5h8RSrwDNUl72bu8cK_6c3jwFdAkBdKgNGAXP_EVgHNfxvwY22bP7nBawnZxaTCOvjyD7WVQ-savgF4T8Gq9R4nCtaogwC275fxC26DBz5k3yVbxqPAkA_FZ37a_EAjGoWG7rNUNc0SrlLR2dgYxlUjGal6qRhpqNL0X0yYleg13l5UhWQp4QpADO3HCH_tgHInm_mm6IlAkBi2Nh9hMIC6F-vIHt3EeZMVQaFGnku6r_tYrAzsS-Qyv5Af8jQuX0G2_i3CegtVFI1Ymt9zUvTjnVKxIVBfohx";


    /**
    * @Description: jwt 根据号码加密
    * @Param: [phone]
    * @return: 
    * @Author: hushenggui
    * @Date: 2020/9/8
    */
    public static String encodeJwtToken(String phone) {

        try {
            RSAPublicKey publicKeyRSA = getRSAPublicKey(publicKey);
            RSAPrivateKey privateKeyRSA = getRSAPrivateKey(privateKey);
            //超时时间 10分钟
            Date expiresAt = new Date(System.currentTimeMillis() + (10 * 60 * 1000));
            System.out.println(expiresAt.toString());
            //加密
            Algorithm algorithm = Algorithm.RSA256(publicKeyRSA, privateKeyRSA);
            String token = JWT.create()
                    .withIssuer("cngongbao")
                    .withClaim("phone", phone)
                    .withExpiresAt(expiresAt)
                    .sign(algorithm);
            return token;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String decodeJwtTokenToPhone(String jwtToken) {
        try {
            RSAPublicKey publicKeyRSA = getRSAPublicKey(publicKey);
            RSAPrivateKey privateKeyRSA = getRSAPrivateKey(privateKey);
            //加密
            Algorithm algorithm = Algorithm.RSA256(publicKeyRSA, privateKeyRSA);
            //解密
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("cngongbao")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(jwtToken);
            System.out.println("........................>>>> " + jwt.toString());
            String phone = jwt.getClaim("phone").asString();
            return phone;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static RSAPublicKey getRSAPublicKey(String publicK) throws Exception {
        byte[] publicBytes = decryptBASE64(publicK);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPublicKey pubKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
        return pubKey;
    }

    //解码返回byte
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    public static RSAPrivateKey getRSAPrivateKey(String privateK) throws Exception {
        byte[] clear = decryptBASE64(privateK);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);
        KeyFactory fact = KeyFactory.getInstance("RSA");
        RSAPrivateKey priv = (RSAPrivateKey) fact.generatePrivate(keySpec);
        Arrays.fill(clear, (byte) 0);
        return priv;

    }
}
