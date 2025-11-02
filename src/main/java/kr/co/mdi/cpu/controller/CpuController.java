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
//	@GetMapping("/cpus")
//	public String cpuList(Model model) {
//		List<CpuDTO> cpuList = cpuService.getCpuList();
//		model.addAttribute("cpus", cpuList);
//		return "cpu/cpu-list";
//	}
	
	// HTML 반환 컨트롤러
	// CPU 목록 페이지(mdi)
	@GetMapping("/cpus")
	public String cpuList(Model model) {
	    List<CpuDTO> cpuList = cpuService.getCpuList();

	    // 총 CPU 수
	    int totalCpuCount = cpuList.size();

	    // 코어별 빈도 계산
	    long coreCount2 = cpuList.stream().filter(cpu -> cpu.getCoreCpu() == 2).count();
	    long coreCount4 = cpuList.stream().filter(cpu -> cpu.getCoreCpu() == 4).count();
	    long coreCount6 = cpuList.stream().filter(cpu -> cpu.getCoreCpu() == 6).count();
	    long coreCount8 = cpuList.stream().filter(cpu -> cpu.getCoreCpu() == 8).count();
	    long coreCount12 = cpuList.stream().filter(cpu -> cpu.getCoreCpu() == 12).count();

	    // 모델에 추가
	    model.addAttribute("cpus", cpuList);
	    model.addAttribute("totalCpuCount", totalCpuCount);
	    model.addAttribute("coreCount2", coreCount2);
	    model.addAttribute("coreCount4", coreCount4);
	    model.addAttribute("coreCount6", coreCount6);
	    model.addAttribute("coreCount8", coreCount8);
	    model.addAttribute("coreCount12", coreCount12);

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
	
	// ---------------------------------
	
	@GetMapping("/cpu-core-graph/{name}")
	public String cpuCoreGraph(@PathVariable String name, Model model) {
	    CpuDTO cpu = cpuService.getCpuByName(name);
	    if (cpu == null) {
	        return "error/404"; // 또는 에러 처리
	    }
	    model.addAttribute("coreCpu", cpu.getCoreCpu());
	    model.addAttribute("nameCpu", cpu.getNameCpu());
	    return "cpu/core-graph";
	}

	
	


}
