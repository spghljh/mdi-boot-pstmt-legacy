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
	
	// CPU 목록 페이지2
	@GetMapping("/cpus2")
	public String cpuList2() {
		return "cpu-list2";
	}

	// CPU 상세 페이지2
	@GetMapping("/cpus2/{cpuId}")
	public String cpuDetail2(@PathVariable Long cpuId, Model model) {
		model.addAttribute("cpuId", cpuId);
		return "cpu-detail2";
	}

}
