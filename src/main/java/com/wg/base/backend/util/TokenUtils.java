package com.wg.base.backend.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    /*private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000; //过期时间*/
    private static final long EXPIRE_TIME = 60 * 1000; //过期时间

    private static final String TOKEN_SECRET = "f26e587c28064d0e855e72c0a6a0e618"; //私钥

    /**
     * 生成签名
     * @param username
     * @param userId
     * @return
     */
    public static String sign(String username, String userId) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET); //根据私钥生成算法
            Map<String, Object> header = new HashMap<>(2);
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            return JWT.create().
                    withHeader(header).
                    withClaim("loginName",  username).
                    withClaim("userId", userId).
                    withExpiresAt(date).sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("loginName").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
