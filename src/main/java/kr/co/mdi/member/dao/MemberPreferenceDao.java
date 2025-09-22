package kr.co.mdi.member.dao;

import java.util.List;

public interface MemberPreferenceDao {
	void insert(String memberId, Integer itemId);

	void delete(String memberId, Integer itemId);

	List<Integer> findByMemberId(String memberId);
}
