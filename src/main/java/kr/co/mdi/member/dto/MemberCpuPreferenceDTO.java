package kr.co.mdi.member.dto;

public class MemberCpuPreferenceDTO {

	private String id_member; // 회원 PK
	private Integer cpuId; // 찜한 CPU ID

	public MemberCpuPreferenceDTO() {
	}

	public String getId_member() {
		return id_member;
	}

	public void setId_member(String id_member) {
		this.id_member = id_member;
	}

	public Integer getCpuId() {
		return cpuId;
	}

	public void setCpuId(Integer cpuId) {
		this.cpuId = cpuId;
	}

	@Override
	public String toString() {
		return "MemberCpuPreferenceDTO [id_member=" + id_member + ", cpuId=" + cpuId + "]";
	}

}
