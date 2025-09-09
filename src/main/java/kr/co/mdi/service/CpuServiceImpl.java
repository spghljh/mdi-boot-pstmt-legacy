package kr.co.mdi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.mdi.dao.CpuDAO;
import kr.co.mdi.dto.CpuDTO;
import kr.co.mdi.dto.CpuViewModel;

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
	public List<CpuViewModel> getCpuListWithImage() {
		List<CpuDTO> rawList = cpuDAO.selectAllCpus();
		List<CpuViewModel> viewList = new ArrayList<>();

		for (CpuDTO cpu : rawList) {
			CpuViewModel vm = new CpuViewModel();
			vm.setCpu(cpu);
			vm.setImagePath(resolveImagePath(cpu.getManfCpu()));
			viewList.add(vm);
		}

		return viewList;
	}

	public String resolveImagePath(String manfCpu) {
		return switch (manfCpu.toLowerCase()) {
		case "intel" -> "/image/intel.png";
		case "amd" -> "/image/amd.png";
		default -> "/image/default.png";
		};
	}

	@Override
	public CpuDTO getCpuById(Long cpuId) {
		return cpuDAO.selectCpuById(cpuId);
	}

}
