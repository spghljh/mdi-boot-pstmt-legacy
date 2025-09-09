package kr.co.mdi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.co.mdi.dto.CpuDTO;

@Controller
public class CpuController {

	@Autowired
	private kr.co.mdi.service.CpuService cpuService;

	@GetMapping("/cpus")
	public String cpuList(Model model) {
		List<CpuDTO> cpuList = cpuService.getCpuList();
		model.addAttribute("cpus", cpuList);
		return "cpu-list";
	}

	// CPU 상세 페이지
	@GetMapping("/cpus/{cpuId}")
	public String cpuDetail(@PathVariable Long cpuId, Model model) {
		model.addAttribute("cpuId", cpuId);
		return "cpu-detail";
	}

}
