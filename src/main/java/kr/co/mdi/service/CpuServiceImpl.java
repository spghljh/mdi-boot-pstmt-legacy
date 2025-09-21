package kr.co.mdi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.mdi.dao.CpuDao;
import kr.co.mdi.dto.CpuDTO;

@Service
public class CpuServiceImpl implements CpuService {

	private final CpuDao cpuDAO;

	@Autowired
	public CpuServiceImpl(CpuDao cpuDAO) {
		this.cpuDAO = cpuDAO;
	}

	@Override
	public List<CpuDTO> getCpuList() {
		return cpuDAO.selectAllCpus();
	}

	@Override
	public CpuDTO getCpuById(Integer cpuId) {
		return cpuDAO.selectCpuById(cpuId);
	}

}
