package kr.co.mdi.member.service;

import kr.co.mdi.member.dto.MemberDTO;

public interface MemberService {

	public void registerUser(MemberDTO member);

	public boolean isDuplicateId(String id);

	MemberDTO findUserById(String id);

}
