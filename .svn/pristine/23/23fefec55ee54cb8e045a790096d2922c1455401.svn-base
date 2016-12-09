package com.spark.network.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.spark.network.entity.LeaveForm;

public interface ILeaveFormService {
	
	public void delete(Integer id);
	
	public LeaveForm getById(Integer id);
	
	public List<LeaveForm> ListLeaveForm(Integer userId);
	
	public List<LeaveForm> getALL();
	
	public Page<LeaveForm> getPage(int pageNo, int pageSize);

	public LeaveForm getByProId(String processInstanceId);

}
