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

import kr.co.mdi.dto.CpuDTO;

@Profile("dev-psql")
@Repository
public class CpuDaoPostgreImpl implements CpuDao {

	private final DataSource dataSource;

	@Autowired
	public CpuDaoPostgreImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection(); // 커넥션 풀에서 가져옴
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public List<CpuDTO> selectAllCpus() {
		List<CpuDTO> cpuList = new ArrayList<>();
		String sql = "SELECT * FROM mcl";

		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				CpuDTO cpu = new CpuDTO();
				cpu.setIdCpu(rs.getLong("id_cpu"));
				cpu.setNameCpu(rs.getString("name_cpu"));
				cpu.setReleaseCpu(rs.getLong("release_cpu"));
				cpu.setManfCpu(rs.getString("manf_cpu"));
				cpu.setCpuManfCode(rs.getString("cpu_manf_code"));
				cpu.setCoreCpu(rs.getFloat("core_cpu"));
				cpu.setThreadCpu(rs.getLong("thread_cpu"));
				cpu.setMaxghzCpu(rs.getFloat("maxghz_cpu"));
				cpu.setMinghzCpu(rs.getFloat("minghz_cpu"));
				cpu.setChoiceCpu(rs.getLong("choice_cpu"));
				cpu.setTypeCpu(rs.getString("type_cpu"));
				cpuList.add(cpu);
			}

		} catch (SQLException e) {
			e.printStackTrace(); // 필요 시 로깅 처리
		}

		return cpuList;
	}

	@Override
	public CpuDTO selectCpuById(Long cpuId) {
		String sql = "SELECT * FROM mcl WHERE id_cpu = ?";
		CpuDTO cpu = null;

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setLong(1, cpuId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					cpu = new CpuDTO();
					cpu.setIdCpu(rs.getLong("id_cpu"));
					cpu.setNameCpu(rs.getString("name_cpu"));
					cpu.setReleaseCpu(rs.getLong("release_cpu"));
					cpu.setManfCpu(rs.getString("manf_cpu"));
					cpu.setCpuManfCode(rs.getString("cpu_manf_code"));
					cpu.setCoreCpu(rs.getFloat("core_cpu"));
					cpu.setThreadCpu(rs.getLong("thread_cpu"));
					cpu.setMaxghzCpu(rs.getFloat("maxghz_cpu"));
					cpu.setMinghzCpu(rs.getFloat("minghz_cpu"));
					cpu.setChoiceCpu(rs.getLong("choice_cpu"));
					cpu.setTypeCpu(rs.getString("type_cpu"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cpu;
	}

}
