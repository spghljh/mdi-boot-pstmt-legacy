package kr.co.mdi.member.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import kr.co.mdi.common.jdbc.AbstractJdbcDao;
import kr.co.mdi.cpu.dto.CpuDTO;
import kr.co.mdi.member.dao.MemberDao;
import kr.co.mdi.member.dto.MemberDTO;

@Profile("dev-user-mssql")
@Repository
public class MemberDaoImpl extends AbstractJdbcDao implements MemberDao {

	@Override
	public void insertUser(MemberDTO member) {
		String sql = """
				    INSERT INTO member (
				        id, pass, name, email,
				        role, status, email_verified, fail_count, regist_day
				    ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
				""";

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPass());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getRole());
			pstmt.setString(6, member.getStatus());
			pstmt.setString(7, member.getEmailVerified());
			pstmt.setInt(8, member.getFailCount());
			pstmt.setTimestamp(9, java.sql.Timestamp.valueOf(member.getRegistDay()));

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("회원가입 중 오류 발생", e);
		}
	}

	@Override
	public boolean existsById(String id) {
		String sql = "SELECT COUNT(*) FROM member WHERE id = ?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("아이디 중복 체크 중 오류 발생", e);
		}
		return false;
	}

	@Override
	public MemberDTO findById(String id) {
		String sql = "SELECT * FROM member WHERE id = ?";

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				MemberDTO member = new MemberDTO();
				member.setIdMember(rs.getInt("id_member"));
				member.setId(rs.getString("id"));
				member.setPass(rs.getString("pass"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setRole(rs.getString("role"));
				member.setStatus(rs.getString("status"));
				member.setEmailVerified(rs.getString("email_verified"));
				member.setFailCount(rs.getInt("fail_count"));

				member.setRegistDay(toLocalDateTime(rs.getTimestamp("regist_day")));
				member.setLastLogin(toLocalDateTime(rs.getTimestamp("last_login")));
				member.setUpdatedAt(toLocalDateTime(rs.getTimestamp("updated_at")));
				member.setDeletedAt(toLocalDateTime(rs.getTimestamp("deleted_at")));

				return member;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("회원 조회 중 오류 발생", e);
		}

		return null;
	}

	// 헬퍼 메서드
	private LocalDateTime toLocalDateTime(java.sql.Timestamp timestamp) {
		return timestamp != null ? timestamp.toLocalDateTime() : null;
	}
	
	// --------------
	
	@Override
	public void insertCpuPreference(String memberId, int cpuId) {
	    String sql = "INSERT INTO member_cpu_preference (member_id, cpu_id) VALUES (?, ?)";

	    try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, memberId);
	        pstmt.setInt(2, cpuId);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("관심 CPU 추가 중 오류 발생", e);
	    }
	}

	@Override
	public boolean existsCpuPreference(String memberId, int cpuId) {
	    String sql = "SELECT COUNT(*) FROM member_cpu_preference WHERE member_id = ? AND cpu_id = ?";

	    try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, memberId);
	        pstmt.setInt(2, cpuId);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1) > 0;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("관심 CPU 중복 확인 중 오류 발생", e);
	    }
	    return false;
	}

	@Override
	public void incrementCpuChoiceCount(int cpuId) {
	    String sql = "UPDATE mcl SET choice_cpu = choice_cpu + 1 WHERE id_cpu = ?";

	    try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, cpuId);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("CPU 선택 수 증가 중 오류 발생", e);
	    }
	}

	//
	
	@Override
	public int getIdMemberById(String id) {
	    String sql = "SELECT id_member FROM member WHERE id = ?";
	    try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, id);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt("id_member");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("회원 ID 조회 중 오류 발생", e);
	    }
	    throw new IllegalArgumentException("해당 ID의 회원을 찾을 수 없습니다: " + id);
	}
	
	@Override
	public List<Integer> findCpuPreferences(int idMember) {
	    String sql = "SELECT cpu_id FROM cpu_prefer WHERE id_member = ?";
	    List<Integer> cpuIds = new ArrayList<>();

	    try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, idMember);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                cpuIds.add(rs.getInt("cpu_id"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("관심 CPU 목록 조회 중 오류 발생", e);
	    }

	    return cpuIds;
	}
	
	@Override
	public String findCpuNameById(int cpuId) {
	    String sql = "SELECT name_cpu FROM cpu WHERE id_cpu = ?";
	    try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, cpuId);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getString("name_cpu");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("CPU 이름 조회 중 오류 발생", e);
	    }
	    return null;
	}
	
//
	
	@Override
	public CpuDTO findCpuDetailById(int cpuId) {
	    String sql = """
	        SELECT c.*, t.type_cpu, m.manf_cpu
	        FROM cpu c
	        LEFT JOIN cpu_type t ON c.cpu_type_code = t.cpu_type_code
	        LEFT JOIN cpu_manf_brand m ON c.cpu_manf_code = m.cpu_manf_code
	        WHERE c.id_cpu = ?
	    """;

	    try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, cpuId);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                CpuDTO cpu = new CpuDTO();
	                cpu.setIdCpu(rs.getInt("id_cpu"));
	                cpu.setNameCpu(rs.getString("name_cpu"));
	                cpu.setReleaseCpu(rs.getInt("release_cpu"));
	                cpu.setCoreCpu(rs.getInt("core_cpu"));
	                cpu.setThreadCpu(rs.getInt("thread_cpu"));
	                cpu.setMaxghzCpu(rs.getFloat("maxghz_cpu"));
	                cpu.setMinghzCpu(rs.getFloat("minghz_cpu"));
	                cpu.setChoiceCpu(rs.getInt("choice_cpu"));
	                cpu.setCpuTypeCode(rs.getString("cpu_type_code"));
	                cpu.setTypeCpu(rs.getString("type_cpu"));
	                cpu.setCpuManfCode(rs.getString("cpu_manf_code"));
	                cpu.setManfCpu(rs.getString("manf_cpu"));
	                return cpu;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("CPU 상세 조회 중 오류 발생", e);
	    }
	    return null;
	}
}
