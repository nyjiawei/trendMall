package com.fjw.coolerMall.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author jiawe
 * @description JWTtoken工具
 * @date 2019/6/8
 */
public class JWTUtil {

    //过期时间设置为15分钟
    private static final long EXPIRE_TIME = 60 * 60 * 1000;

    //token私钥(这里选用的是一个UUID)
    private static final String TOKEN_SECRET = "02ace2efd64147e8b60f9e734d09eb64";

    private JWTUtil(){

    }

    /**
     * 生成token
     * @param userName 用户名
     * @param userId 用户ID
     * @return 加密的token
     */
    public static String createToken (String userName,String userId) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            Map<String,Object> header = new HashMap<>();
            header.put("typ","JWT");
            header.put("alg","HS256");
            return JWT.create()
                    .withHeader(header)
                    .withClaim("loginName",userName)
                    .withClaim("userId",userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验token是否正确
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    /**
     * 从token获取用户信息，无需解密
     * @param token
     * @return token中包含的用户名
     */
    public static String getUserNameFromToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("loginName").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 从token获取用户id
     * @param token
     * @return token中包含的用户id
     */
    public static String getUserIdFromToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
