package kr.co.mdi.dao;

import java.util.List;

import kr.co.mdi.dto.DeviceDTO;

public interface DeviceDao {

	List<DeviceDTO> selectAllDevices();

	DeviceDTO selectDeviceById(Integer deviceId);
}
