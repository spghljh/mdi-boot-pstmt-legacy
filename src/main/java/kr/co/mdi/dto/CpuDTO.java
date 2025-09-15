package kr.co.mdi.dto;

public class CpuDTO {
	private Long idCpu; // mcl.id_cpu
	private String nameCpu; // mcl.name_cpu
	private Long releaseCpu; // mcl.release_cpu
	private Float coreCpu; // mcl.core_cpu
	private Long threadCpu; // mcl.thread_cpu
	private Float maxghzCpu; // mcl.maxghz_cpu
	private Float minghzCpu; // mcl.minghz_cpu
	private Long choiceCpu; // mcl.choice_cpu

	private String cpuTypeCode; // mcl.cpu_type_code (외래 키)
	private String typeCpu; // cpu_type.type_cpu (표시용 이름)

	private String cpuManfCode; // mcl.cpu_manf_code (외래 키)
	private String manfCpu; // cpu_manf_brand.manf_cpu (표시용 이름)

	public Long getIdCpu() {
		return idCpu;
	}

	public void setIdCpu(Long idCpu) {
		this.idCpu = idCpu;
	}

	public String getNameCpu() {
		return nameCpu;
	}

	public void setNameCpu(String nameCpu) {
		this.nameCpu = nameCpu;
	}

	public Long getReleaseCpu() {
		return releaseCpu;
	}

	public void setReleaseCpu(Long releaseCpu) {
		this.releaseCpu = releaseCpu;
	}

	public Float getCoreCpu() {
		return coreCpu;
	}

	public void setCoreCpu(Float coreCpu) {
		this.coreCpu = coreCpu;
	}

	public Long getThreadCpu() {
		return threadCpu;
	}

	public void setThreadCpu(Long threadCpu) {
		this.threadCpu = threadCpu;
	}

	public Float getMaxghzCpu() {
		return maxghzCpu;
	}

	public void setMaxghzCpu(Float maxghzCpu) {
		this.maxghzCpu = maxghzCpu;
	}

	public Float getMinghzCpu() {
		return minghzCpu;
	}

	public void setMinghzCpu(Float minghzCpu) {
		this.minghzCpu = minghzCpu;
	}

	public Long getChoiceCpu() {
		return choiceCpu;
	}

	public void setChoiceCpu(Long choiceCpu) {
		this.choiceCpu = choiceCpu;
	}

	public String getCpuTypeCode() {
		return cpuTypeCode;
	}

	public void setCpuTypeCode(String cpuTypeCode) {
		this.cpuTypeCode = cpuTypeCode;
	}

	public String getTypeCpu() {
		return typeCpu;
	}

	public void setTypeCpu(String typeCpu) {
		this.typeCpu = typeCpu;
	}

	public String getCpuManfCode() {
		return cpuManfCode;
	}

	public void setCpuManfCode(String cpuManfCode) {
		this.cpuManfCode = cpuManfCode;
	}

	public String getManfCpu() {
		return manfCpu;
	}

	public void setManfCpu(String manfCpu) {
		this.manfCpu = manfCpu;
	}

}
