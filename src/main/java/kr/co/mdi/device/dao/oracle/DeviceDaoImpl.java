package kr.co.mdi.device.dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import kr.co.mdi.common.jdbc.AbstractJdbcDao;
import kr.co.mdi.device.dao.DeviceDao;
import kr.co.mdi.device.dto.DeviceDTO;

@Profile("dev-oracle")
@Repository
public class DeviceDaoImpl extends AbstractJdbcDao implements DeviceDao {

//	private final DataSource dataSource;
//
//	@Autowired
//	public DeviceDaoOracleImpl(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}
//
//	public Connection getConnection() throws SQLException {
//		return dataSource.getConnection(); // 커넥션 풀에서 가져옴
//	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public int selectTotalDeviceCount() {
		String sql = "SELECT COUNT(*) FROM device";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException("디바이스 row 수 조회 중 오류 발생", e);
		}
		return 0;
	}

	@Override
	public List<DeviceDTO> selectAllDevices() {
		List<DeviceDTO> deviceList = new ArrayList<>();
		String sql = """
				    SELECT
				        m.id_device,
				        m.name_device,
				        m.lineup_device,
				        m.release_device,
				        m.weight_device,
				        m.choice_device,
				        m.device_type_code,
				        t.type_device,
				        m.device_manf_code,
				        b.manf_device,
				        m.id_cpu,
				        c.name_cpu
				    FROM device m
				    LEFT JOIN device_type t ON m.device_type_code = t.device_type_code
				    LEFT JOIN device_manf_brand b ON m.device_manf_code = b.device_manf_code
				    LEFT JOIN cpu c ON m.id_cpu = c.id_cpu
				""";

		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				DeviceDTO device = new DeviceDTO();
				device.setIdDevice(rs.getInt("id_device"));
				device.setNameDevice(rs.getString("name_device"));
				device.setLineupDevice(rs.getString("lineup_device"));
				device.setReleaseDevice(rs.getInt("release_device"));
				device.setWeightDevice(rs.getFloat("weight_device"));
				device.setChoiceDevice(rs.getInt("choice_device"));

				device.setDeviceTypeCode(rs.getString("device_type_code"));
				device.setTypeDevice(rs.getString("type_device"));

				device.setDeviceManfCode(rs.getString("device_manf_code"));
				device.setManfDevice(rs.getString("manf_device"));

				device.setIdCpu(rs.getInt("id_cpu"));
				device.setCpuDevice(rs.getString("name_cpu"));

				deviceList.add(device);
			}

		} catch (SQLException se) {
			se.printStackTrace();
			throw new RuntimeException("DB 조회 중 오류 발생", se);
		}

		return deviceList;
	}

	@Override
	public DeviceDTO selectDeviceById(Integer deviceId) {
		String sql = """
				    SELECT
				        m.id_device,
				        m.name_device,
				        m.lineup_device,
				        m.release_device,
				        m.weight_device,
				        m.choice_device,
				        m.device_type_code,
				        t.type_device,
				        m.device_manf_code,
				        b.manf_device,
				        m.id_cpu,
				        c.name_cpu
				    FROM device m
				    LEFT JOIN device_type t ON m.device_type_code = t.device_type_code
				    LEFT JOIN device_manf_brand b ON m.device_manf_code = b.device_manf_code
				    LEFT JOIN cpu c ON m.id_cpu = c.id_cpu
				    WHERE m.id_device = ?
				""";

		DeviceDTO device = null;

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, deviceId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					device = new DeviceDTO();
					device.setIdDevice(rs.getInt("id_device"));
					device.setNameDevice(rs.getString("name_device"));
					device.setLineupDevice(rs.getString("lineup_device"));
					device.setReleaseDevice(rs.getInt("release_device"));
					device.setWeightDevice(rs.getFloat("weight_device"));
					device.setChoiceDevice(rs.getInt("choice_device"));

					device.setDeviceTypeCode(rs.getString("device_type_code"));
					device.setTypeDevice(rs.getString("type_device"));

					device.setDeviceManfCode(rs.getString("device_manf_code"));
					device.setManfDevice(rs.getString("manf_device"));

					device.setIdCpu(rs.getInt("id_cpu"));
					device.setCpuDevice(rs.getString("name_cpu"));
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
			throw new RuntimeException("DB 조회 중 오류 발생", se);
		}

		return device;
	}

}
