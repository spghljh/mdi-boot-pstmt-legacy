package kr.co.mdi.device.dao;

import java.util.List;

import kr.co.mdi.device.dto.DeviceDTO;

public interface DeviceDao {

	int selectTotalDeviceCount();

	List<DeviceDTO> selectAllDevices();

	DeviceDTO selectDeviceById(Integer deviceId);
}
