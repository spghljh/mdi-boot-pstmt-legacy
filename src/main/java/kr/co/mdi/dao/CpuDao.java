package kr.co.mdi.dao;

import java.util.List;

import kr.co.mdi.dto.CpuDTO;

public interface CpuDao {

	List<CpuDTO> selectAllCpus();

	CpuDTO selectCpuById(Long cpuId);
}
