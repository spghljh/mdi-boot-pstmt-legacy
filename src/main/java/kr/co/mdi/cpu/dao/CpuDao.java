package kr.co.mdi.cpu.dao;

import java.util.List;

import kr.co.mdi.cpu.dto.CpuDTO;

public interface CpuDao {

	int selectTotalCpuCount();

	List<CpuDTO> selectAllCpus();

	CpuDTO selectCpuById(Integer cpuId);
}
