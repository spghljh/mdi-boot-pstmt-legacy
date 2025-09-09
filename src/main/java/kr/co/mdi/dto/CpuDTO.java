package kr.co.mdi.dto;

public class CpuDTO {
	private Long idCpu;
	private String nameCpu;
	private Long releaseCpu;
	private String manfCpu;
	private Float coreCpu;
	private Long threadCpu;
	private Float maxghzCpu;
	private Float minghzCpu;
	private Long choiceCpu;
	private String typeCpu;

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

	public String getManfCpu() {
		return manfCpu;
	}

	public void setManfCpu(String manfCpu) {
		this.manfCpu = manfCpu;
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

	public String getTypeCpu() {
		return typeCpu;
	}

	public void setTypeCpu(String typeCpu) {
		this.typeCpu = typeCpu;
	}

	@Override
	public String toString() {
		return "CpuDTO [idCpu=" + idCpu + ", nameCpu=" + nameCpu + ", releaseCpu=" + releaseCpu + ", manfCpu=" + manfCpu
				+ ", coreCpu=" + coreCpu + ", threadCpu=" + threadCpu + ", maxghzCpu=" + maxghzCpu + ", minghzCpu="
				+ minghzCpu + ", choiceCpu=" + choiceCpu + ", typeCpu=" + typeCpu + "]";
	}

}
