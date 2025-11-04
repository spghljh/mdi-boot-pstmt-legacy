package kr.co.mdi.cpu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.mdi.cpu.dao.CpuDao;
import kr.co.mdi.cpu.dto.CpuDTO;

@Service
public class CpuServiceImpl implements CpuService {

	private final CpuDao cpuDAO;

	@Autowired
	public CpuServiceImpl(CpuDao cpuDAO) {
		this.cpuDAO = cpuDAO;
	}

	@Override
	public int getTotalCpuCount() {
		return cpuDAO.selectTotalCpuCount();
	}

	@Override
	public List<CpuDTO> getCpuList() {
		return cpuDAO.selectAllCpus();
	}

	@Override
	public CpuDTO getCpuById(Integer cpuId) {
		return cpuDAO.selectCpuById(cpuId);
	}
	
	// ----------
	
	@Override
	public CpuDTO getCpuByName(String nameCpu) {
	    return cpuDAO.selectCpuByName(nameCpu);
	}

	// ----------
	
	@Override
	public List<CpuDTO> getCpuListByName(String nameCpu) {
	    return cpuDAO.selectCpuListByName(nameCpu);
	}
	
	
	

}
