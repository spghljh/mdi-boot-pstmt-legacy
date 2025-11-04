package kr.co.mdi.device.service;

import java.util.List;
import java.util.Map;

import kr.co.mdi.cpu.dto.CpuDTO;
import kr.co.mdi.device.dto.DeviceDTO;

public interface DeviceService {

	int getTotalDeviceCount();

	List<DeviceDTO> getDeviceList();

	DeviceDTO getDeviceById(Integer deviceId);
	
	// ----
	
	List<DeviceDTO> getDevicesByCpuName(String cpuName);
	
	// ----
	
	Map<String, Integer> getDeviceCountByBrand(String cpuName);
	
	//
	
	List<DeviceDTO> getDeviceListByName(String nameDevice);
	
	List<DeviceDTO> getHotDeviceList();


}
