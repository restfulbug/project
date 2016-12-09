package com.spark.network.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spark.network.entity.RepairForm;

public interface IRepairFormService {
	
public List<RepairForm> findAllRepairform();

public void save(RepairForm repairForm);

public void delete(Integer id);

public RepairForm getById(Integer id);

public Page<RepairForm> getPage(int pageNo, int pageSize);


public Long countAll();

public RepairForm getByProId(String processInstanceId);
//新加
public Page<RepairForm> getPageByUserName(String userName,Pageable pageable);

//新加
public Page<RepairForm> getByCreatDateBefore(Date date,Pageable pageable);

}
