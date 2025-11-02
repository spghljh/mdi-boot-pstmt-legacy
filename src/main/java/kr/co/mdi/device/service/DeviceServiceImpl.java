package kr.co.mdi.device.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.mdi.device.dao.DeviceDao;
import kr.co.mdi.device.dto.DeviceDTO;

@Service
public class DeviceServiceImpl implements DeviceService {

	private final DeviceDao deviceDAO;

	@Autowired
	public DeviceServiceImpl(DeviceDao deviceDAO) {
		this.deviceDAO = deviceDAO;
	}

	@Override
	public int getTotalDeviceCount() {
		return deviceDAO.selectTotalDeviceCount();
	}

	@Override
	public List<DeviceDTO> getDeviceList() {
		return deviceDAO.selectAllDevices();
	}

	@Override
	public DeviceDTO getDeviceById(Integer deviceId) {
		return deviceDAO.selectDeviceById(deviceId);
	}
	
	// ----
	
	@Override
	public List<DeviceDTO> getDevicesByCpuName(String cpuName) {
	    return deviceDAO.selectDevicesByCpuName(cpuName);
	}

	// ----
	
	@Override
	public Map<String, Integer> getDeviceCountByBrand(String cpuName) {
	    return deviceDAO.selectDeviceCountByBrand(cpuName);
	}


}
