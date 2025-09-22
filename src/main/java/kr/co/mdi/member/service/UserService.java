package kr.co.mdi.member.service;

import kr.co.mdi.member.dto.UserDTO;

public interface UserService {

	public void registerUser(UserDTO user);

	public boolean isDuplicateId(String id);

	UserDTO findUserById(String id);

}
