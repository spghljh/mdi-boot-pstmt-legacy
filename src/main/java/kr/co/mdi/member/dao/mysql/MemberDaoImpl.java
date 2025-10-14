package kr.co.mdi.member.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import kr.co.mdi.common.jdbc.AbstractJdbcDao;
import kr.co.mdi.member.dao.MemberDao;
import kr.co.mdi.member.dto.MemberDTO;

@Profile("dev-user-mysql")
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
}
