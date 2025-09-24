package kr.co.mdi.member.dao;

import kr.co.mdi.member.dto.MemberDTO;

public interface MemberDao {
	void insertUser(MemberDTO member);

	boolean existsById(String id); // 아이디 중복 체크용

	MemberDTO findById(String id);

	int getNextMemberId(); // 시퀀스 값 가져오기
}
