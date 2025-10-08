package kr.co.mdi.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.mdi.common.config.DbProfileResolver;
import kr.co.mdi.cpu.service.CpuService;
import kr.co.mdi.device.service.DeviceService;

@Controller
public class MainController {

	@Autowired
    private CpuService cpuService;

    @Autowired
    private DeviceService deviceService;

    // 메인 페이지
    @GetMapping("/")
    public String index(Model model) {
        int totalCpuCount = cpuService.getTotalCpuCount();
        int totalDeviceCount = deviceService.getTotalDeviceCount();
        model.addAttribute("totalCpuCount", totalCpuCount);
        model.addAttribute("totalDeviceCount", totalDeviceCount);
        return "index";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage() {
        return "/member/login";
    }
}
