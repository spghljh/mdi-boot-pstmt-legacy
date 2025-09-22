package kr.co.mdi.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import kr.co.mdi.common.jdbc.AbstractJdbcDao;
import kr.co.mdi.member.dto.MemberDTO;

@Profile("dev-oracle")
@Repository
public class MemberDaoOracleImpl extends AbstractJdbcDao implements MemberDao {

	@Override
	public void insertUser(MemberDTO member) {
		String sql = """
				    INSERT INTO member (id, pass, name, email)
				    VALUES (?, ?, ?, ?)
				""";

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPass());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
			// regist_day는 생략 → SYSDATE 자동 적용

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
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				MemberDTO member = new MemberDTO();
				member.setId(rs.getString("id"));
				member.setPass(rs.getString("pass"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setRegistDay(rs.getString("regist_day"));
				// 관심 CPU/디바이스는 필요 시 추가 조회
				return member;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
