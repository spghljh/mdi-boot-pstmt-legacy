package kr.co.mdi.dto;

public class DeviceDTO {

	private Long idDevice; // id_device NUMBER(10)
	private String typeDevice; // type_device VARCHAR2(255)
	private String nameDevice; // name_device VARCHAR2(255)
	private String manfDevice; // manf_device VARCHAR2(255)
	private String deviceManfCode; // device_manf_code VARCHAR2(255)
	private String cpuDevice; // cpu_device VARCHAR2(255)
	private String lineupDevice; // lineup_device VARCHAR2(255)
	private Long releaseDevice; // release_device NUMBER(10)
	private Float weightDevice; // weight_device FLOAT
	private Float diagonalDevice; // diagonal_device FLOAT
	private Long choiceDevice; // choice_device NUMBER(10)

	public Long getIdDevice() {
		return idDevice;
	}

	public void setIdDevice(Long idDevice) {
		this.idDevice = idDevice;
	}

	public String getTypeDevice() {
		return typeDevice;
	}

	public void setTypeDevice(String typeDevice) {
		this.typeDevice = typeDevice;
	}

	public String getNameDevice() {
		return nameDevice;
	}

	public void setNameDevice(String nameDevice) {
		this.nameDevice = nameDevice;
	}

	public String getManfDevice() {
		return manfDevice;
	}

	public void setManfDevice(String manfDevice) {
		this.manfDevice = manfDevice;
	}

	public String getDeviceManfCode() {
		return deviceManfCode;
	}

	public void setDeviceManfCode(String deviceManfCode) {
		this.deviceManfCode = deviceManfCode;
	}

	public String getCpuDevice() {
		return cpuDevice;
	}

	public void setCpuDevice(String cpuDevice) {
		this.cpuDevice = cpuDevice;
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

	public Float getDiagonalDevice() {
		return diagonalDevice;
	}

	public void setDiagonalDevice(Float diagonalDevice) {
		this.diagonalDevice = diagonalDevice;
	}

	public Long getChoiceDevice() {
		return choiceDevice;
	}

	public void setChoiceDevice(Long choiceDevice) {
		this.choiceDevice = choiceDevice;
	}

}
