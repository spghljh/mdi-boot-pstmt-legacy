package kr.co.mdi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CpuController {

	// CPU 목록 페이지
	@GetMapping("/cpus")
	public String cpuList() {
		return "cpu-list";
	}

	// CPU 상세 페이지
	@GetMapping("/cpus/{cpuId}")
	public String cpuDetail(@PathVariable Long cpuId, Model model) {
		model.addAttribute("cpuId", cpuId);
		return "cpu-detail";
	}

}
