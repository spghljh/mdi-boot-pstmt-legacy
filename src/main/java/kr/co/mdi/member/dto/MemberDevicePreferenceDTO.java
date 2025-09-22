package kr.co.mdi.member.dto;

public class MemberDevicePreferenceDTO {

	private String id; // 회원 ID
	private Integer deviceId; // 찜한 디바이스 ID

	public MemberDevicePreferenceDTO() {
	}

	public MemberDevicePreferenceDTO(String id, Integer deviceId) {
		this.id = id;
		this.deviceId = deviceId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public String toString() {
		return "MemberDevicePreferenceDTO [id=" + id + ", deviceId=" + deviceId + "]";
	}
}
