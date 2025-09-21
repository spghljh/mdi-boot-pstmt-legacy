package kr.co.mdi.dto;

import java.util.List;

public class UserDTO {
	private String id;
	private String pass;
	private String passConfirm;
	private String name;
	private String email;
	private String registDay;

	private List<Integer> cpuInterestIds; // 관심 CPU ID 리스트
	private List<Integer> deviceInterestIds; // 관심 디바이스 ID 리스트

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPassConfirm() {
		return passConfirm;
	}

	public void setPassConfirm(String passConfirm) {
		this.passConfirm = passConfirm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegistDay() {
		return registDay;
	}

	public void setRegistDay(String registDay) {
		this.registDay = registDay;
	}

	public List<Integer> getCpuInterestIds() {
		return cpuInterestIds;
	}

	public void setCpuInterestIds(List<Integer> cpuInterestIds) {
		this.cpuInterestIds = cpuInterestIds;
	}

	public List<Integer> getDeviceInterestIds() {
		return deviceInterestIds;
	}

	public void setDeviceInterestIds(List<Integer> deviceInterestIds) {
		this.deviceInterestIds = deviceInterestIds;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", pass=" + pass + ", passConfirm=" + passConfirm + ", name=" + name + ", email="
				+ email + ", registDay=" + registDay + "]";
	}

}
