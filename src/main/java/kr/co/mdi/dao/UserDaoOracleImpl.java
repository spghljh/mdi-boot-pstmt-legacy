package kr.co.mdi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import kr.co.mdi.dto.UserDTO;

@Profile("dev-oracle")
@Repository
public class UserDaoOracleImpl extends AbstractJdbcDao implements UserDao {

	@Override
	public void insertUser(UserDTO user) {
		String sql = """
				    INSERT INTO members (id, pass, name, email)
				    VALUES (?, ?, ?, ?)
				""";

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPass());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
			// regist_day는 생략 → SYSDATE 자동 적용

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("회원가입 중 오류 발생", e);
		}
	}

	@Override
	public boolean existsById(String id) {
		String sql = "SELECT COUNT(*) FROM members WHERE id = ?";
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
}
