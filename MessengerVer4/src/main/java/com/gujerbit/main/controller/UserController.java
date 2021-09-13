package com.gujerbit.main.controller;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.gujerbit.main.service.SessionServiceImpl;
import com.gujerbit.main.service.UserServiceImpl;
import com.gujerbit.main.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@Controller
@Slf4j
public class UserController {
	
	@Autowired
	private UserServiceImpl service;
	
	@Autowired
	private SessionServiceImpl sessionService;
	
	@Resource
	private UserVO user;
	
	@PostMapping("/user/login")
	public ResponseEntity<Map<String, Object>> loginProcess(@RequestBody String[] loginInfo, HttpServletResponse res) {
		String id = loginInfo[0];
		String originPassword = loginInfo[1];
		String salt = service.selectSalt(id);

		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("password", hashing(originPassword, salt));

		int result = service.login(map);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		if(result > 0) {
			try { //login 성공 시 token 생성
				user.setId(id);
				user.setPassword(hashing(originPassword, salt));
				String token = sessionService.create(user);
				//token 정보는 header로 보내주고 나머지는 map에 담기
				res.setHeader("jwt-auth-token", token);
				resultMap.put("status", true);
				resultMap.put("data", user);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				log.error("login faild", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			System.out.println("login faild");
			return null;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PostMapping("/user/info")
	public ResponseEntity<Map<String, Object>> getInfo(@RequestBody UserVO user, HttpServletRequest req) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			//사용자에게 전달할 정보
			String info = "info";
			//token에 담긴 정보
			resultMap.putAll(sessionService.get(req.getHeader("jwt-auth-token")));
			resultMap.put("status", true);
			resultMap.put("info", info);
			resultMap.put("request_body", user);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			log.error("select info faild", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}

	private String hashing(String password, String salt) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(password.getBytes());
			byte[] digestPassword = md.digest();
			md.update((byteToString(digestPassword) + salt).getBytes());
			byte[] saltingPassword = md.digest();
			result = byteToString(saltingPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	private String byteToString(byte[] str) {
		StringBuffer sb = new StringBuffer();

		for (byte value : str) {
			sb.append(String.format("%02x", value));
		}

		return sb.toString();
	}

}
