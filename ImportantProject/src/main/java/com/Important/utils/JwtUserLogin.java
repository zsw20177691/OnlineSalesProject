package com.Important.utils;

import com.Important.dto.LoginUserDto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class JwtUserLogin {
    @Value(value = "${jwt.secret}")
    private String  secret;
    @Value(value = "${jwt.intervals}")
    private Long    intervals;
    /**
     * 生成token
     */
    public <T>String  createToken(LoginUserDto loginUserDto){
        try{
            Date afterDate = getAfterDate(intervals * 12 * 60 * 60 * 1000L);
            Map<String, Object> map = Maps.newHashMap();
            map.put("alg","HS256");
            map.put("typ","JWT");
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create().withHeader(map)
                    .withClaim("username",loginUserDto.getUsername())
                    .withJWTId(UUID.randomUUID().toString())
                    .withIssuedAt(new Date())//生成签名时间
                    .withExpiresAt(afterDate)//签名过期时间
                    .sign(algorithm);
        }catch (Exception   e){
            return null;
        }
    }

    public Date getAfterDate(Long intervals){
        long l = System.currentTimeMillis() + intervals;
        return new Date(l);
    }

    //token校验
    public DecodedJWT verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        } catch (Exception e) {
            return null;
        }
    }

    public String getUserName(String token) {
        DecodedJWT verify = verify(token);
        return verify.getClaim("username").asString();
    }
}
