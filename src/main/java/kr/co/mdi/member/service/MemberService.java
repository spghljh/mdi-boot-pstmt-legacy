package kr.co.mdi.member.service;

import java.util.List;

import kr.co.mdi.cpu.dto.CpuDTO;
import kr.co.mdi.member.dto.MemberDTO;

public interface MemberService {

	public void registerUser(MemberDTO member);

	public boolean isDuplicateId(String id);

	MemberDTO findUserById(String id);

	//

//	void addCpuPreference(String userId, int cpuId);
	
	boolean addCpuPreference(String userId, int cpuId);

	//

	List<String> getFavoriteCpuNames(String userId);

	List<CpuDTO> getFavoriteCpuDetails(String userId);

	//

	void removeCpuPreference(String userId, int cpuId);

}
