package kr.co.mdi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MdiController {

	@GetMapping("/")
	public String index() {
		return "index"; // templates/index.html 렌더링
	}// index

}
