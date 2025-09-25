package kr.co.mdi.member.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.co.mdi.common.util.PasswordUtil;
import kr.co.mdi.member.dao.MemberDao;
import kr.co.mdi.member.dao.MemberPreferenceDao;
import kr.co.mdi.member.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;

	@Autowired
	@Qualifier("memberCpuPreferenceDao")
	private MemberPreferenceDao cpuPreferenceDao;

	@Autowired
	@Qualifier("memberDevicePreferenceDao")
	private MemberPreferenceDao devicePreferenceDao;

	@Override
	public void registerUser(MemberDTO member) {
		// 1. 비밀번호 확인
		if (!member.getPass().equals(member.getPassConfirm())) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}

		// 2. 아이디 중복 체크
		if (memberDao.existsById(member.getId())) {
			throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
		}

		// 3-1. 비밀번호 암호화 (레거시 방식)

		// 바로 적용하는 관점
//		member.setPass(BCrypt.hashpw(member.getPass(), BCrypt.gensalt()));

		// 유틸클래스로 관리하는 관점
		member.setPass(PasswordUtil.encode(member.getPass()));

		// 3-2. 비밀번호 암호화 (스프링 시큐리티 방식)
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		member.setPass(passwordEncoder.encode(member.getPass()));

		// 4. 가입일 설정
//		member.setRegistDay(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd (HH:mm)")));
		member.setRegistDay(LocalDateTime.now());


		// 5. 회원 정보 저장
		member.setRole("USER");
		member.setStatus("ACTIVE");
		member.setEmailVerified("N");
		member.setFailCount(0);

		member.setIdMember(memberDao.getNextMemberId());
		memberDao.insertUser(member);

		// 6. 관심 CPU 저장
		if (member.getCpuInterestIds() != null && !member.getCpuInterestIds().isEmpty()) {
			for (Integer cpuId : member.getCpuInterestIds()) {
				cpuPreferenceDao.insert(member.getId(), cpuId);
			}
		}

		// 7. 관심 디바이스 저장
		if (member.getDeviceInterestIds() != null && !member.getDeviceInterestIds().isEmpty()) {
			for (Integer deviceId : member.getDeviceInterestIds()) {
				devicePreferenceDao.insert(member.getId(), deviceId);
			}
		}
	}

	@Override
	public boolean isDuplicateId(String id) {
		return memberDao.existsById(id);
	}

	@Override
	public MemberDTO findUserById(String id) {
		return memberDao.findById(id);
	}

}
