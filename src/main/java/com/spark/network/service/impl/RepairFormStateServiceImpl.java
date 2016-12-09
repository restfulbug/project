package com.spark.network.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spark.network.entity.RepairFormState;
import com.spark.network.repository.RepairFormStateRepository;
import com.spark.network.service.IRepairFormStateService;
@Service(value="repairFormStateService")
public class RepairFormStateServiceImpl implements IRepairFormStateService {

	@Resource(name="repairFormStateRepository")
	private RepairFormStateRepository repairFormStateRepository;

	@Transactional
	public void save(RepairFormState repairFormState) {
		repairFormStateRepository.save(repairFormState);
		
	}

	@Transactional
	public void delete(Integer id) {
   repairFormStateRepository.delete(id);
	}

	@Transactional(readOnly=true)
	public RepairFormState getById(Integer id) {
		
		return repairFormStateRepository.findOne(id);
	}

	@Transactional(readOnly=true)
	public List<RepairFormState> getAll() {
		
		return repairFormStateRepository.findAll();
	}

	@Transactional(readOnly=true)
	public RepairFormState getByStateName(String stateName) {

		return repairFormStateRepository.getByStateName(stateName);
	}

	

}
