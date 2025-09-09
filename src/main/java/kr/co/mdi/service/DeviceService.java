package kr.co.mdi.service;

import java.util.List;

import kr.co.mdi.dto.DeviceDTO;

public interface DeviceService {
	List<DeviceDTO> getDeviceList();

	DeviceDTO getDeviceById(Long deviceId);

}
