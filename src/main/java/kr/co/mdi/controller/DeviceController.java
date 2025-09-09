package kr.co.mdi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.co.mdi.dto.DeviceDTO;
import kr.co.mdi.service.DeviceService;

@Controller
public class DeviceController {

	@Autowired
	private DeviceService deviceService;

	@GetMapping("/devices")
	public String deviceList(Model model) {
		List<DeviceDTO> deviceList = deviceService.getDeviceList();
		model.addAttribute("devices", deviceList);
		return "device-list";
	}

	// DEVICE 상세 페이지
	@GetMapping("/devices/{deviceId}")
	public String cpuDetail(@PathVariable Long deviceId, Model model) {
		DeviceDTO device = deviceService.getDeviceById(deviceId); // 상세 정보 조회
		model.addAttribute("device", device); // 뷰에 전달
		return "device-detail";
	}

}
