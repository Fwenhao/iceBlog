package cn.fwhspace.iceblog.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/12/2 14:18
 * @description JWT工具类
 */
@Component
public class JwtUtils {

    private final RedisUtils redisUtils;
    private final GlobleUtils globleUtils;

    /**
     * 密钥
     */
    private final static String SECRET_KEY = "98)*@^))!%fuWaO.";

    @Autowired
    public JwtUtils(RedisUtils redisUtils, GlobleUtils globleUtils) {
        this.redisUtils = redisUtils;
        this.globleUtils = globleUtils;
    }

    /**
     * 结构体 ： header.payload.sing
     *
     * @description : 〈生成token〉
     * @param map 封装的用户属性
     * @return : java.lang.String
     */
    public String setToken(Map<String,String> map){

        Calendar instance = Calendar.getInstance();
        //默认900s过期 15分钟
        instance.add(Calendar.SECOND,900);
        JWTCreator.Builder builder = JWT.create();
        //payload
        map.forEach(builder::withClaim);
        String token = builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(SECRET_KEY));
        if (globleUtils.getAccessToken() == null){
            return token;
        }else {
            //刷新token
            redisUtils.set(globleUtils.getAccessToken(),token);
            return globleUtils.getAccessToken();
        }
    }

    /**
     * @description : 〈验证token〉
     * @param key 校验key
     * @return : void
     */
    public boolean verify(String key){
        Object token = redisUtils.get(key);
        try {
            //若verify验证token失效则会主动抛出异常，
            JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(String.valueOf(token));
            return true;
        }catch (Exception e){
            System.out.println("jwt校验:token过期");
            reflushToken(key);
            System.out.println("jwt校验:token自动刷新成功");
        }
        return false;
    }

    /**
     * @description : 〈获取token信息详情〉
     * @param info 1
     * @return : java.lang.String
     */
    public String getTokenMsg(String info){
        DecodedJWT jwt = JWT.decode(String.valueOf(redisUtils.get(globleUtils.getAccessToken())));
        return jwt.getClaim(info).asString();
    }
    /**
     * @description : 〈获取token信息详情〉
     * @param info 1
     * @param accessToken 2
     * @return : java.lang.String
     */
    public String getTokenMsg(String info,String accessToken){
        DecodedJWT jwt = JWT.decode(String.valueOf(redisUtils.get(accessToken)));
        return jwt.getClaim(info).asString();
    }

    /**
     * @description : 〈刷新token〉
     * @param key 1
     * @return : java.lang.String
     */
    public void reflushToken(String key){
        //提取过期token中的msg
        DecodedJWT jwt = JWT.decode(String.valueOf(redisUtils.get(key)));
        Map<String,String> map = new HashMap<>();
        map.put("keyId", jwt.getClaim("keyId").asString());
        map.put("userName",jwt.getClaim("userName").asString());
        map.put("nickName",jwt.getClaim("nickName").asString());
        map.put("userEmail",jwt.getClaim("userEmail").asString());
        map.put("userSex",jwt.getClaim("userSex").asString());
        map.put("userPhone",jwt.getClaim("userPhone").asString());
        map.put("userId",jwt.getClaim("userId").asString());
        map.put("userIdentify",jwt.getClaim("userIdentify").asString());
        map.put("accountIdentity",jwt.getClaim("accountIdentity").asString());
        setToken(map);
    }

}
