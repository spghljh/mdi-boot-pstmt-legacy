package kr.co.mdi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import kr.co.mdi.dto.DeviceDTO;

@Profile("dev-psql")
@Repository
public class DeviceDaoPostgreImpl implements DeviceDao {

	private final DataSource dataSource;

	@Autowired
	public DeviceDaoPostgreImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection(); // 커넥션 풀에서 가져옴
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public List<DeviceDTO> selectAllDevices() {
		List<DeviceDTO> deviceList = new ArrayList<>();
		String sql = "SELECT * FROM mdl";

		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				DeviceDTO device = new DeviceDTO();
				device.setIdDevice(rs.getLong("id_device"));
				device.setTypeDevice(rs.getString("type_device"));
				device.setNameDevice(rs.getString("name_device"));
				device.setManfDevice(rs.getString("manf_device"));
				device.setDeviceManfCode(rs.getString("device_manf_code"));
				device.setCpuDevice(rs.getString("cpu_device"));
				device.setLineupDevice(rs.getString("lineup_device"));
				device.setReleaseDevice(rs.getLong("release_device"));
				device.setWeightDevice(rs.getFloat("weight_device"));
				device.setChoiceDevice(rs.getLong("choice_device"));

				deviceList.add(device);
			}

		} catch (SQLException e) {
			e.printStackTrace(); // 필요 시 로깅 처리
		}

		return deviceList;
	}

	@Override
	public DeviceDTO selectDeviceById(Long deviceId) {
		String sql = "SELECT * FROM mdl WHERE id_device = ?";
		DeviceDTO device = null;

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setLong(1, deviceId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					device = new DeviceDTO();
					device.setIdDevice(rs.getLong("id_device"));
					device.setTypeDevice(rs.getString("type_device"));
					device.setNameDevice(rs.getString("name_device"));
					device.setManfDevice(rs.getString("manf_device"));
					device.setDeviceManfCode(rs.getString("device_manf_code"));
					device.setCpuDevice(rs.getString("cpu_device"));
					device.setLineupDevice(rs.getString("lineup_device"));
					device.setReleaseDevice(rs.getLong("release_device"));
					device.setWeightDevice(rs.getFloat("weight_device"));
					device.setChoiceDevice(rs.getLong("choice_device"));

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return device;
	}

}
