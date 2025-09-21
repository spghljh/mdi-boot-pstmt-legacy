package kr.co.mdi.service;

import kr.co.mdi.dto.UserDTO;

public interface UserService {

	public void registerUser(UserDTO user);

	public boolean isDuplicateId(String id);

	UserDTO findUserById(String id);

}
