package kr.co.mdi.device.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.co.mdi.device.dto.DeviceDTO;
import kr.co.mdi.device.service.DeviceService;

@Controller
public class DeviceController {

	@Autowired
	private DeviceService deviceService;

	// HTML 반환 컨트롤러
	// DEVICE 목록 페이지
//	@GetMapping("/devices")
//	public String deviceList(Model model) {
//		List<DeviceDTO> deviceList = deviceService.getDeviceList();
//		model.addAttribute("devices", deviceList);
//		return "device/device-list";
//	}
	
	// HTML 반환 컨트롤러
	// DEVICE 목록 페이지(mdi)
	@GetMapping("/devices")
	public String deviceList(Model model) {
	    List<DeviceDTO> deviceList = deviceService.getDeviceList();

	    // 총 디바이스 수
	    int totalDeviceCount = deviceList.size();

	    // 제조사별 디바이스 수 계산
	    long hpCount = deviceList.stream().filter(d -> "hp".equalsIgnoreCase(d.getManfDevice())).count();
	    long msiCount = deviceList.stream().filter(d -> "msi".equalsIgnoreCase(d.getManfDevice())).count();
	    long asusCount = deviceList.stream().filter(d -> "asus".equalsIgnoreCase(d.getManfDevice())).count();
	    long dellCount = deviceList.stream().filter(d -> "dell".equalsIgnoreCase(d.getManfDevice())).count();
	    long samsungCount = deviceList.stream().filter(d -> "samsung".equalsIgnoreCase(d.getManfDevice())).count();
	    long lgCount = deviceList.stream().filter(d -> "lg".equalsIgnoreCase(d.getManfDevice())).count();

	    // 모델에 추가
	    model.addAttribute("devices", deviceList);
	    model.addAttribute("totalDeviceCount", totalDeviceCount);
	    model.addAttribute("hpCount", hpCount);
	    model.addAttribute("msiCount", msiCount);
	    model.addAttribute("asusCount", asusCount);
	    model.addAttribute("dellCount", dellCount);
	    model.addAttribute("samsungCount", samsungCount);
	    model.addAttribute("lgCount", lgCount);

	    return "device/device-list";
	}


	// DEVICE 상세 페이지
	@GetMapping("/devices/{deviceId}")
	public String deviceDetail(@PathVariable Integer deviceId, Model model) {
		DeviceDTO device = deviceService.getDeviceById(deviceId); // 상세 정보 조회
		model.addAttribute("device", device); // 뷰에 전달
//		return "device/device-detail";
		return "device/device-detail-current";
	}

}
