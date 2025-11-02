package kr.co.mdi.device.dao;

import java.util.List;
import java.util.Map;

import kr.co.mdi.device.dto.DeviceDTO;

public interface DeviceDao {

	int selectTotalDeviceCount();

	List<DeviceDTO> selectAllDevices();

	DeviceDTO selectDeviceById(Integer deviceId);
	
	// ok
	
	List<DeviceDTO> selectDevicesByCpuName(String cpuName);
	
	// --
	
	Map<String, Integer> selectDeviceCountByBrand(String cpuName);


}
