package com.spark.network.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spark.network.entity.FaultLevel;
import com.spark.network.repository.FaultLevelRepository;
import com.spark.network.service.IFaultLevelService;
@Service(value="faultLevelService")
public class FaultLevelServiceImpl implements IFaultLevelService {

	@Resource(name="faultLevelRepository")
	private FaultLevelRepository faultLevelRepository;

	@Transactional
	public void save(FaultLevel faultLevel) {
	faultLevelRepository.save(faultLevel);
	}

	@Transactional
	public  void batchSave(List<FaultLevel> faultLevels) {
		for (FaultLevel faultLevel : faultLevels) {
			faultLevelRepository.save(faultLevel);
		}
    
	}
	@Transactional
	public void update(FaultLevel faultLevel,Integer id) {
   faultLevelRepository.updateFaultLeave(faultLevel, id);
	}

	@Transactional
	public void delete(Integer id) {
		faultLevelRepository.delete(id);
	}

	@Transactional(readOnly=true)
	public List<FaultLevel> getALL() {
		
		return faultLevelRepository.findAll();
	}

	@Transactional(readOnly=true)
	public FaultLevel getById(Integer id) {

		return faultLevelRepository.findOne(id);
	}
	

}

