package kr.co.mdi.service;

import java.util.List;

import kr.co.mdi.dto.CpuDTO;

public interface CpuService {
	List<CpuDTO> getCpuList();

	CpuDTO getCpuById(Integer cpuId);

}
