package com.mario.springboot.services;

import org.springframework.stereotype.Component;

@Component
public class LoginService {

	public boolean validateUser(String user, String password) {
		return user.equalsIgnoreCase("guedim") && password.equals("123456");
	}
}
