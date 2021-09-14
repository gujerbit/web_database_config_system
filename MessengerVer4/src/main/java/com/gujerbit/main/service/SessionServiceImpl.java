package com.gujerbit.main.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gujerbit.main.vo.UserVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SessionServiceImpl implements SessionService {
	
	@Value("${jwt.salt}")
	private String salt;
	
	@Value("${jwt.expmin}")
	private Long expireMin;
	
	@Override
	public String create(UserVO user) {
		log.trace("time: {}", expireMin);
		JwtBuilder builder = Jwts.builder();
		builder.setHeaderParam("typ", "JWT"); //type jwt token
		//setSubject => token title ����
		
		//claim => jwt token�� ��� ���� ������ ���
		builder.setSubject("token").claim("User", user);
		//setExpiration => token ��ȿ �Ⱓ
//		setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin));
		//secret key�� �̿��� ��ȣȭ
		builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());
		//����ȭ
		String jwt = builder.compact();
		log.debug("token: {}", jwt);
		
		return jwt;
	}
	
	@Override
	public void checkValid(String jwt) {
		log.trace("token check: {}", jwt);
		Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
	}
	
	@Override
	public Map<String, Object> get(String jwt) {
		Jws<Claims> claims = null;
		
		try {
			claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		log.trace("claims: {}", claims);
		//claims => map�� ����ü
		return claims.getBody();
	}
	
}
