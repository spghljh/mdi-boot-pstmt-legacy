package kr.co.mdi.cpu.service;

import java.util.List;

import kr.co.mdi.cpu.dto.CpuDTO;

public interface CpuService {
	List<CpuDTO> getCpuList();

	CpuDTO getCpuById(Integer cpuId);

}
