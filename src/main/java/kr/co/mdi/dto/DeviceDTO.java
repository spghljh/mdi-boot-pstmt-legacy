package kr.co.mdi.dto;

public class DeviceDTO {

	private Long idDevice; // mdl.id_device
	private String nameDevice; // mdl.name_device
	private String lineupDevice; // mdl.lineup_device
	private Long releaseDevice; // mdl.release_device
	private Float weightDevice; // mdl.weight_device
	private Long choiceDevice; // mdl.choice_device

	private String deviceTypeCode; // mdl.device_type_code (외래 키)
	private String typeDevice; // device_type.type_device (표시용 이름)

	private String deviceManfCode; // mdl.device_manf_code (외래 키)
	private String manfDevice; // device_manf_brand.manf_device (표시용 이름)

	private Long idCpu; // mdl.id_cpu (외래 키 → mcl.id_cpu)
	private String cpuDevice; // mcl.name_cpu (표시용 CPU 이름)

	public Long getIdDevice() {
		return idDevice;
	}

	public void setIdDevice(Long idDevice) {
		this.idDevice = idDevice;
	}

	public String getNameDevice() {
		return nameDevice;
	}

	public void setNameDevice(String nameDevice) {
		this.nameDevice = nameDevice;
	}

	public String getLineupDevice() {
		return lineupDevice;
	}

	public void setLineupDevice(String lineupDevice) {
		this.lineupDevice = lineupDevice;
	}

	public Long getReleaseDevice() {
		return releaseDevice;
	}

	public void setReleaseDevice(Long releaseDevice) {
		this.releaseDevice = releaseDevice;
	}

	public Float getWeightDevice() {
		return weightDevice;
	}

	public void setWeightDevice(Float weightDevice) {
		this.weightDevice = weightDevice;
	}

	public Long getChoiceDevice() {
		return choiceDevice;
	}

	public void setChoiceDevice(Long choiceDevice) {
		this.choiceDevice = choiceDevice;
	}

	public String getDeviceTypeCode() {
		return deviceTypeCode;
	}

	public void setDeviceTypeCode(String deviceTypeCode) {
		this.deviceTypeCode = deviceTypeCode;
	}

	public String getTypeDevice() {
		return typeDevice;
	}

	public void setTypeDevice(String typeDevice) {
		this.typeDevice = typeDevice;
	}

	public String getDeviceManfCode() {
		return deviceManfCode;
	}

	public void setDeviceManfCode(String deviceManfCode) {
		this.deviceManfCode = deviceManfCode;
	}

	public String getManfDevice() {
		return manfDevice;
	}

	public void setManfDevice(String manfDevice) {
		this.manfDevice = manfDevice;
	}

	public Long getIdCpu() {
		return idCpu;
	}

	public void setIdCpu(Long idCpu) {
		this.idCpu = idCpu;
	}

	public String getCpuDevice() {
		return cpuDevice;
	}

	public void setCpuDevice(String cpuDevice) {
		this.cpuDevice = cpuDevice;
	}

}
