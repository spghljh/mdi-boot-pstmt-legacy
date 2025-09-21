package kr.co.mdi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.mdi.dto.UserDTO;
import kr.co.mdi.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("user", new UserDTO());
		return "register";
	}

	@PostMapping("/register")
	public String processRegister(@ModelAttribute UserDTO user) {
		userService.registerUser(user);
		return "redirect:/login";
	}

	@GetMapping("/check-id")
	@ResponseBody
	public boolean checkId(@RequestParam String id) {
		return userService.isDuplicateId(id);
	}
}
