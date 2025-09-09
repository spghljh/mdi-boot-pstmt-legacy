package kr.co.mdi.dao;

import java.util.List;

import kr.co.mdi.dto.DeviceDTO;

public interface DeviceDAO {

	List<DeviceDTO> selectAllDevices();

	DeviceDTO selectDeviceById(Long deviceId);
}
