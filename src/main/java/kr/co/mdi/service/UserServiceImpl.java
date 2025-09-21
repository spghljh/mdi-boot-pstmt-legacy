package kr.co.mdi.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.co.mdi.dao.MemberPreferenceDao;
import kr.co.mdi.dao.UserDao;
import kr.co.mdi.dto.UserDTO;

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

		// 3. 가입일 설정
		user.setRegistDay(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd (HH:mm)")));

		// 4. 회원 정보 저장
		userDao.insertUser(user);

		// 5. 관심 CPU 저장
		if (user.getCpuInterestIds() != null) {
			for (Integer cpuId : user.getCpuInterestIds()) {
				cpuPreferenceDao.insert(user.getId(), cpuId);
			}
		}

		// 6. 관심 디바이스 저장
		if (user.getDeviceInterestIds() != null) {
			for (Integer deviceId : user.getDeviceInterestIds()) {
				devicePreferenceDao.insert(user.getId(), deviceId);
			}
		}
	}

	@Override
	public boolean isDuplicateId(String id) {
		return userDao.existsById(id);
	}
}
