package kr.co.mdi.cpu.dao;

import java.util.List;

import kr.co.mdi.cpu.dto.CpuDTO;

public interface CpuDao {

	List<CpuDTO> selectAllCpus();

	CpuDTO selectCpuById(Integer cpuId);
}
