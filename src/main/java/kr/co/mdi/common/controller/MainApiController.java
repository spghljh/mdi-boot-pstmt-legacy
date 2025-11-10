package kr.co.mdi.common.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.mdi.cpu.service.CpuService;
import kr.co.mdi.device.service.DeviceService;

@RestController
@RequestMapping("/api/main")
//@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class MainApiController {

    @Autowired
    private CpuService cpuService;

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/summary")
    public Map<String, Object> getSummary() {
        Map<String, Object> result = new HashMap<>();
        result.put("totalCpuCount", cpuService.getTotalCpuCount());
        result.put("totalDeviceCount", deviceService.getTotalDeviceCount());
        result.put("hotCpuList", cpuService.getHotCpuList());
        result.put("hotDeviceList", deviceService.getHotDeviceList());
        return result;
    }
}
