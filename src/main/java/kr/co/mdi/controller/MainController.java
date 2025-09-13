package kr.co.mdi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	// 메인 페이지
	@GetMapping("/")
	public String index() {
		return "index";
	}

	// 로그인 페이지
	@GetMapping("/login")
	public String loginPage() {
		return "login"; // templates/login.html
	}

}
