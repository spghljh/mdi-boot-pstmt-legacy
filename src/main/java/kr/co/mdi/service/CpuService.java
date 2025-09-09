package kr.co.mdi.service;

import java.util.List;

import kr.co.mdi.dto.CpuDTO;
import kr.co.mdi.dto.CpuViewModel;

public interface CpuService {
	List<CpuDTO> getCpuList();

	CpuDTO getCpuById(Long cpuId);

	String resolveImagePath(String manfCpu);

	List<CpuViewModel> getCpuListWithImage();

}
