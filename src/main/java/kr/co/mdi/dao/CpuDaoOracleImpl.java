package kr.co.mdi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import kr.co.mdi.dto.CpuDTO;

@Profile("dev-oracle")
@Repository
public class CpuDaoOracleImpl extends AbstractJdbcDao implements CpuDao {

//	private final DataSource dataSource;
//
//	@Autowired
//	public CpuDaoOracleImpl(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}
//
//	public Connection getConnection() throws SQLException {
//		return dataSource.getConnection(); // 커넥션 풀에서 가져옴
//	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public List<CpuDTO> selectAllCpus() {
		List<CpuDTO> cpuList = new ArrayList<>();
		String sql = """
				    SELECT
				        m.id_cpu,
				        m.name_cpu,
				        m.release_cpu,
				        m.core_cpu,
				        m.thread_cpu,
				        m.maxghz_cpu,
				        m.minghz_cpu,
				        m.choice_cpu,
				        m.cpu_type_code,
				        t.type_cpu,
				        m.cpu_manf_code,
				        b.manf_cpu
				    FROM mcl m
				    LEFT JOIN cpu_type t ON m.cpu_type_code = t.cpu_type_code
				    LEFT JOIN cpu_manf_brand b ON m.cpu_manf_code = b.cpu_manf_code
				""";

		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				CpuDTO cpu = new CpuDTO();
				cpu.setIdCpu(rs.getLong("id_cpu"));
				cpu.setNameCpu(rs.getString("name_cpu"));
				cpu.setReleaseCpu(rs.getLong("release_cpu"));
				cpu.setCoreCpu(rs.getFloat("core_cpu"));
				cpu.setThreadCpu(rs.getLong("thread_cpu"));
				cpu.setMaxghzCpu(rs.getFloat("maxghz_cpu"));
				cpu.setMinghzCpu(rs.getFloat("minghz_cpu"));
				cpu.setChoiceCpu(rs.getLong("choice_cpu"));
				cpu.setCpuTypeCode(rs.getString("cpu_type_code"));
				cpu.setTypeCpu(rs.getString("type_cpu"));
				cpu.setCpuManfCode(rs.getString("cpu_manf_code"));
				cpu.setManfCpu(rs.getString("manf_cpu"));
				cpuList.add(cpu);
			}

		} catch (SQLException se) {
			se.printStackTrace(); // 필요 시 로깅 처리
			throw new RuntimeException("DB 조회 중 오류 발생", se);
		}

		return cpuList;
	}

	@Override
	public CpuDTO selectCpuById(Long cpuId) {
		String sql = """
				    SELECT
				        m.id_cpu,
				        m.name_cpu,
				        m.release_cpu,
				        m.core_cpu,
				        m.thread_cpu,
				        m.maxghz_cpu,
				        m.minghz_cpu,
				        m.choice_cpu,
				        m.cpu_type_code,
				        t.type_cpu,
				        m.cpu_manf_code,
				        b.manf_cpu
				    FROM mcl m
				    LEFT JOIN cpu_type t ON m.cpu_type_code = t.cpu_type_code
				    LEFT JOIN cpu_manf_brand b ON m.cpu_manf_code = b.cpu_manf_code
				    WHERE m.id_cpu = ?
				""";

		CpuDTO cpu = null;

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setLong(1, cpuId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					cpu = new CpuDTO();
					cpu.setIdCpu(rs.getLong("id_cpu"));
					cpu.setNameCpu(rs.getString("name_cpu"));
					cpu.setReleaseCpu(rs.getLong("release_cpu"));
					cpu.setCoreCpu(rs.getFloat("core_cpu"));
					cpu.setThreadCpu(rs.getLong("thread_cpu"));
					cpu.setMaxghzCpu(rs.getFloat("maxghz_cpu"));
					cpu.setMinghzCpu(rs.getFloat("minghz_cpu"));
					cpu.setChoiceCpu(rs.getLong("choice_cpu"));
					cpu.setCpuTypeCode(rs.getString("cpu_type_code"));
					cpu.setTypeCpu(rs.getString("type_cpu"));
					cpu.setCpuManfCode(rs.getString("cpu_manf_code"));
					cpu.setManfCpu(rs.getString("manf_cpu"));
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
			throw new RuntimeException("DB 조회 중 오류 발생2", se);
		}

		return cpu;
	}

}
