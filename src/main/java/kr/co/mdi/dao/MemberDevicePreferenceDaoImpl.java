package kr.co.mdi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("memberDevicePreferenceDao")
public class MemberDevicePreferenceDaoImpl extends AbstractJdbcDao implements MemberPreferenceDao {

	@Override
	public void insert(String memberId, Integer itemId) {
		String sql = "INSERT INTO member_device_preference (id, device_id) VALUES (?, ?)";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);
			pstmt.setInt(2, itemId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("디바이스 찜 등록 중 오류 발생", e);
		}
	}

	@Override
	public void delete(String memberId, Integer itemId) {
		String sql = "DELETE FROM member_device_preference WHERE id = ? AND device_id = ?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);
			pstmt.setInt(2, itemId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("디바이스 찜 삭제 중 오류 발생", e);
		}
	}

	@Override
	public List<Integer> findByMemberId(String memberId) {
		String sql = "SELECT device_id FROM member_device_preference WHERE id = ?";
		List<Integer> deviceIds = new ArrayList<>();
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					deviceIds.add(rs.getInt("device_id"));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("디바이스 찜 목록 조회 중 오류 발생", e);
		}
		return deviceIds;
	}
}
