package com.spark.network.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spark.network.entity.FaultType;
import com.spark.network.repository.FaultTypeRepository;
import com.spark.network.service.IFaultTypeService;
@Service(value="faultTypeService")
public class FaultTypeServiceImpl implements IFaultTypeService {

	@Resource(name="faultTypeRepository")
	private FaultTypeRepository faultTypeRepository;

	@Transactional
	public void save(FaultType faultType) {
		
		faultTypeRepository.save(faultType);

	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void batchSave(List<FaultType> faultTypes) {
		for (FaultType faultType : faultTypes) {
			faultTypeRepository.save(faultType);
		}
		
	}

	@Transactional
	public void update(FaultType faultType, Integer id) {
		faultTypeRepository.updateFaultType(faultType, id);
	}

	@Transactional
	public void delete(Integer id) {
	faultTypeRepository.delete(id);
		
	}

	@Transactional(readOnly=true)
	public List<FaultType> getAll() {
		
		return faultTypeRepository.findAll();
	}

	@Transactional(readOnly=true)
	public FaultType getById(Integer id) {
		
		return faultTypeRepository.findOne(id);
	}
	
}
