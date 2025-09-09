package kr.co.mdi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.mdi.dao.DeviceDAO;
import kr.co.mdi.dto.DeviceDTO;

@Service
public class DeviceServiceImpl implements DeviceService {

	private final DeviceDAO deviceDAO;

	@Autowired
	public DeviceServiceImpl(DeviceDAO deviceDAO) {
		this.deviceDAO = deviceDAO;
	}

	@Override
	public List<DeviceDTO> getDeviceList() {
		return deviceDAO.selectAllDevices();
	}

	@Override
	public DeviceDTO getDeviceById(Long deviceId) {
		return deviceDAO.selectDeviceById(deviceId);
	}

}
