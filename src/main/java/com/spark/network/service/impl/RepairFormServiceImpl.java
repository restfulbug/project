package com.spark.network.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spark.network.entity.RepairForm;
import com.spark.network.repository.RepairFormRepository;
import com.spark.network.service.IRepairFormService;

@Service(value="repairFormService")
public class RepairFormServiceImpl implements IRepairFormService{

	@Resource(name="repairFormRepository")
	private RepairFormRepository repairFormRepository;

	@Transactional(readOnly=true)
	public List<RepairForm> findAllRepairform() {
		
		return repairFormRepository.getALL();
	}
	/*
	 * 添加和更新操作
	 * */
	@Transactional
	public void save(RepairForm repairForm) {
      if (repairForm.getId()==null) {
			repairForm.setCreatDate(new Date());
		}
		repairFormRepository.saveAndFlush(repairForm);
	}
	
	@Transactional(readOnly=true)
	public Page<RepairForm> getPage(int pageNo, int pageSize){
		PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
		return repairFormRepository.findAll(pageable);
	}

	@Transactional
	public void delete(Integer id) {
		repairFormRepository.delete(id);
	}

	@Transactional(readOnly=true)
	public RepairForm getById(Integer id) {
		return repairFormRepository.findOne(id);
	}
	@Transactional(readOnly=true)
	public Long countAll() {
		return repairFormRepository.count();
	}
	@Override
	@Transactional(readOnly=true)
	public RepairForm getByProId(String processInstanceId) {
		return repairFormRepository.getByprocessInstanceId(processInstanceId);
	}
//	新加
	@Transactional(readOnly=true)
	public Page<RepairForm> getPageByUserName(String userName,Pageable pageable){
		
		return repairFormRepository.getByUser_userName(userName, pageable);
	}
	
	
//	新加
	@Transactional(readOnly=true)
	public Page<RepairForm> getByCreatDateBefore(Date date,Pageable pageable){
		
		return repairFormRepository.getByCreatDateAfter(date, pageable);
	}


}
