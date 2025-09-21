package kr.co.mdi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("memberCpuPreferenceDao")
public class MemberCpuPreferenceDaoImpl extends AbstractJdbcDao implements MemberPreferenceDao {

	@Override
	public void insert(String memberId, Integer itemId) {
		String sql = "INSERT INTO member_cpu_preference (id, cpu_id) VALUES (?, ?)";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);
			pstmt.setInt(2, itemId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("CPU 찜 등록 중 오류 발생", e);
		}
	}

	@Override
	public void delete(String memberId, Integer itemId) {
		String sql = "DELETE FROM member_cpu_preference WHERE id = ? AND cpu_id = ?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);
			pstmt.setInt(2, itemId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("CPU 찜 삭제 중 오류 발생", e);
		}
	}

	@Override
	public List<Integer> findByMemberId(String memberId) {
		String sql = "SELECT cpu_id FROM member_cpu_preference WHERE id = ?";
		List<Integer> cpuIds = new ArrayList<>();
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					cpuIds.add(rs.getInt("cpu_id"));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("CPU 찜 목록 조회 중 오류 발생", e);
		}
		return cpuIds;
	}
}
