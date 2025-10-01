package kr.co.mdi.cpu.dao;

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

import kr.co.mdi.cpu.dto.CpuDTO;

@Profile("dev-mysql")
@Repository
public class CpuDaoMysqlImpl implements CpuDao {

	private final DataSource dataSource;

	@Autowired
	public CpuDaoMysqlImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection(); // 커넥션 풀에서 가져옴
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public int selectTotalCpuCount() {
		String sql = "SELECT COUNT(*) FROM cpu";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException("CPU row 수 조회 중 오류 발생", e);
		}
		return 0;
	}

	@Override
	public List<CpuDTO> selectAllCpus() {
		List<CpuDTO> cpuList = new ArrayList<>();
		String sql = "SELECT * FROM cpu";

		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				CpuDTO cpu = new CpuDTO();
				cpu.setIdCpu(rs.getInt("id_cpu"));
				cpu.setNameCpu(rs.getString("name_cpu"));
				cpu.setReleaseCpu(rs.getInt("release_cpu"));
				cpu.setManfCpu(rs.getString("manf_cpu"));
				cpu.setCpuManfCode(rs.getString("cpu_manf_code"));
				cpu.setCoreCpu(rs.getInt("core_cpu"));
				cpu.setThreadCpu(rs.getInt("thread_cpu"));
				cpu.setMaxghzCpu(rs.getFloat("maxghz_cpu"));
				cpu.setMinghzCpu(rs.getFloat("minghz_cpu"));
				cpu.setChoiceCpu(rs.getInt("choice_cpu"));
				cpu.setTypeCpu(rs.getString("type_cpu"));
				cpuList.add(cpu);
			}

		} catch (SQLException se) {
			se.printStackTrace(); // 필요 시 로깅 처리
			throw new RuntimeException("DB 조회 중 오류 발생", se);
		}

		return cpuList;
	}

	@Override
	public CpuDTO selectCpuById(Integer cpuId) {
		String sql = "SELECT * FROM cpu WHERE id_cpu = ?";
		CpuDTO cpu = null;

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, cpuId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					cpu = new CpuDTO();
					cpu.setIdCpu(rs.getInt("id_cpu"));
					cpu.setNameCpu(rs.getString("name_cpu"));
					cpu.setReleaseCpu(rs.getInt("release_cpu"));
					cpu.setManfCpu(rs.getString("manf_cpu"));
					cpu.setCpuManfCode(rs.getString("cpu_manf_code"));
					cpu.setCoreCpu(rs.getInt("core_cpu"));
					cpu.setThreadCpu(rs.getInt("thread_cpu"));
					cpu.setMaxghzCpu(rs.getFloat("maxghz_cpu"));
					cpu.setMinghzCpu(rs.getFloat("minghz_cpu"));
					cpu.setChoiceCpu(rs.getInt("choice_cpu"));
					cpu.setTypeCpu(rs.getString("type_cpu"));
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
			throw new RuntimeException("DB 조회 중 오류 발생2", se);
		}
		return cpu;
	}

}
