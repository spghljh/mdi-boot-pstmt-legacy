package kr.co.mdi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DeviceController {

	// 디바이스 목록 페이지
	@GetMapping("/devices")
	public String deviceList() {
		return "device-list";
	}

	// 디바이스 상세 페이지
	@GetMapping("/devices/{deviceId}")
	public String deviceDetail(@PathVariable Long deviceId, Model model) {
		model.addAttribute("deviceId", deviceId);
		return "device-detail";
	}

}
