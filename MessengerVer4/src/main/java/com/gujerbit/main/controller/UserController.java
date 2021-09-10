package com.gujerbit.main.controller;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gujerbit.main.service.UserServiceImpl;

@CrossOrigin("*")
@Controller
public class UserController {

	@Autowired
	private UserServiceImpl service;
	
	@PostMapping("/login")
	public @ResponseBody int loginProcess(@RequestBody String[] loginInfo) {
		String id = loginInfo[0];
		String originPassword = loginInfo[1];
		String salt = service.selectSalt(id);

		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("password", hashing(originPassword, salt));

		int result = service.login(map);
		
		return result;
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
