package cn.java.utils;

import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtToken22 {
	
	static Logger logger = Logger.getLogger(JwtToken22.class);
	// 超时时间：单位为毫秒 1000*60
    private static long time = 1000*60;
    private static String signature = "admin";
	//创建token的方法
    public static String createToken(){
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                //header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //payload
                .claim("username","tom")
                .claim("role","admin")
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256,signature)
                .compact();
        
        return jwtToken;
    }
	//校验token，布尔类型
    public static boolean checkToken(String token){
    	logger.info("token=="+token);
        if (token ==null){
            return false;
        }
        
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signature).parseClaimsJws(token);
        }catch (Exception e){
        	logger.error(e);
            return false;
        }
        return true;
    }
}