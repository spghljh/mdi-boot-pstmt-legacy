package kr.co.mdi.dto;

public class MemberCpuPreferenceDTO {

	private String id; // 회원 ID
	private Integer cpuId; // 찜한 CPU ID

	public MemberCpuPreferenceDTO() {
	}

	public MemberCpuPreferenceDTO(String id, Integer cpuId) {
		this.id = id;
		this.cpuId = cpuId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCpuId() {
		return cpuId;
	}

	public void setCpuId(Integer cpuId) {
		this.cpuId = cpuId;
	}

	@Override
	public String toString() {
		return "MemberCpuPreferenceDTO [id=" + id + ", cpuId=" + cpuId + "]";
	}
}
