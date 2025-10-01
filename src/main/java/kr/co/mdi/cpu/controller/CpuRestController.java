package kr.co.mdi.cpu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.mdi.cpu.dto.CpuDTO;
import kr.co.mdi.cpu.service.CpuService;

@RestController
@RequestMapping("/api/cpus") // 클래스 레벨에서의 기본 경로(prefix)
public class CpuRestController {

	@Autowired
	private CpuService cpuService;

	// JSON 반환 컨트롤러
	// 1) Java 객체(List<CpuDTO>)의
	// 2) JSON으로의 직렬화를 통한
	// 3) HTTP 응답으로의 반환
	@GetMapping // 메서드 레벨
	public List<CpuDTO> getCpuList() {
		return cpuService.getCpuList();
	}

}
