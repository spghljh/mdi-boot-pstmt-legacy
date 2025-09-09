package kr.co.mdi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

	// 메인 페이지
	@GetMapping("/")
	public String index() {
		return "index";
	}


}
