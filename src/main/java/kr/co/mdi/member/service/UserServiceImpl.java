package kr.co.mdi.member.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.co.mdi.common.util.PasswordUtil;
import kr.co.mdi.member.dao.MemberPreferenceDao;
import kr.co.mdi.member.dao.UserDao;
import kr.co.mdi.member.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	@Qualifier("memberCpuPreferenceDao")
	private MemberPreferenceDao cpuPreferenceDao;

	@Autowired
	@Qualifier("memberDevicePreferenceDao")
	private MemberPreferenceDao devicePreferenceDao;

	@Override
	public void registerUser(UserDTO user) {
		// 1. 비밀번호 확인
		if (!user.getPass().equals(user.getPassConfirm())) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}

		// 2. 아이디 중복 체크
		if (userDao.existsById(user.getId())) {
			throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
		}

		// 3-1. 비밀번호 암호화 (레거시 방식)
		
		// 바로 적용하는 관점
//		user.setPass(BCrypt.hashpw(user.getPass(), BCrypt.gensalt()));
		
		// 유틸클래스로 관리하는 관점
		user.setPass(PasswordUtil.encode(user.getPass()));

		// 3-2. 비밀번호 암호화 (스프링 시큐리티 방식)
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		user.setPass(passwordEncoder.encode(user.getPass()));

		// 4. 가입일 설정
		user.setRegistDay(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd (HH:mm)")));

		// 5. 회원 정보 저장
		userDao.insertUser(user);

		// 6. 관심 CPU 저장
		if (user.getCpuInterestIds() != null && !user.getCpuInterestIds().isEmpty()) {
			for (Integer cpuId : user.getCpuInterestIds()) {
				cpuPreferenceDao.insert(user.getId(), cpuId);
			}
		}

		// 7. 관심 디바이스 저장
		if (user.getDeviceInterestIds() != null && !user.getDeviceInterestIds().isEmpty()) {
			for (Integer deviceId : user.getDeviceInterestIds()) {
				devicePreferenceDao.insert(user.getId(), deviceId);
			}
		}
	}

	@Override
	public boolean isDuplicateId(String id) {
		return userDao.existsById(id);
	}

	@Override
	public UserDTO findUserById(String id) {
		return userDao.findById(id);
	}

}
