package com.tacademy.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	@RequestMapping(value={"/login-form"})
	String loginForm() {
		return "login-form";
	}
}
