package kr.co.mdi.cpu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.co.mdi.cpu.dto.CpuDTO;
import kr.co.mdi.cpu.service.CpuService;

@Controller
public class CpuController {

	@Autowired
	private CpuService cpuService;

	// HTML 반환 컨트롤러
	// CPU 목록 페이지
	@GetMapping("/cpus")
	public String cpuList(Model model) {
		List<CpuDTO> cpuList = cpuService.getCpuList();
		model.addAttribute("cpus", cpuList);
		return "cpu/cpu-list";
	}

	// HTML 반환 컨트롤러
	// CPU 상세 페이지
	@GetMapping("/cpus/{cpuId}")
	public String cpuDetail(@PathVariable Integer cpuId, Model model) {
		CpuDTO cpu = cpuService.getCpuById(cpuId); // 상세 정보 조회
		model.addAttribute("cpu", cpu); // 뷰에 전달
//		return "cpu/cpu-detail";
		return "cpu/cpu-detail-current";
	}

	// HTML 반환 컨트롤러
	@GetMapping("/cpus-fetch")
	public String cpuRestPageFetch() {
		return "cpu/cpu-list-fetch";
	}

	// HTML 반환 컨트롤러
	@GetMapping("/cpus-jquery")
	public String cpuRestPageJquery() {
		return "cpu/cpu-list-jquery";
	}

}
