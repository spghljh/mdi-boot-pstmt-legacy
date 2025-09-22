package kr.co.mdi.member.dao;

import kr.co.mdi.member.dto.UserDTO;

public interface UserDao {
	void insertUser(UserDTO user);

	boolean existsById(String id); // 아이디 중복 체크용

	UserDTO findById(String id);

}
