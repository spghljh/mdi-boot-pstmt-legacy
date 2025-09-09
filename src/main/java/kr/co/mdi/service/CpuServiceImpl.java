package kr.co.mdi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.mdi.dao.CpuDAO;
import kr.co.mdi.dto.CpuDTO;

@Service
public class CpuServiceImpl implements CpuService {

	private final CpuDAO cpuDAO;

	@Autowired
	public CpuServiceImpl(CpuDAO cpuDAO) {
		this.cpuDAO = cpuDAO;
	}

	@Override
	public List<CpuDTO> getCpuList() {
		return cpuDAO.selectAllCpus();
	}

	@Override
	public CpuDTO getCpuById(Long cpuId) {
		return cpuDAO.selectCpuById(cpuId);
	}

}
