package kr.co.mdi.device.service;

import java.util.List;

import kr.co.mdi.device.dto.DeviceDTO;

public interface DeviceService {

	int getTotalDeviceCount();

	List<DeviceDTO> getDeviceList();

	DeviceDTO getDeviceById(Integer deviceId);

}
