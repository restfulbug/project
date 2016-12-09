package com.spark.network.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spark.network.entity.LeaveForm;
import com.spark.network.entity.User;
import com.spark.network.repository.LeaveFormRepository;
import com.spark.network.repository.UserRepository;
import com.spark.network.service.ILeaveFormService;

@Service(value = "leaveFormService")
public class LeaveFormServiceImpl implements ILeaveFormService {

	@Resource(name = "runtimeService")
	private RuntimeService runtimeService;
	@Resource(name = "repositoryService")
	private RepositoryService repositoryService;
	@Resource(name = "taskService")
	private TaskService taskService;
	@Resource(name = "leaveFormRepository")
	private LeaveFormRepository leaveFormRepository;
	@Resource(name = "userRepository")
	private UserRepository userRepository;

	@Transactional
	public void delete(Integer id) {
		leaveFormRepository.delete(id);
	}

	@Transactional(readOnly = true)
	public LeaveForm getById(Integer id) {

		return leaveFormRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	public List<LeaveForm> getALL() {

		return leaveFormRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Page<LeaveForm> getPage(int pageNo, int pageSize) {
		PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
		return leaveFormRepository.findAll(pageable);
	}

	
	@Transactional(readOnly=true)
	public List<LeaveForm> ListLeaveForm(Integer userId) {
		User user = userRepository.getOne(userId);
		
		return leaveFormRepository.getByUser(user);
	}

	@Transactional(readOnly=true)
	public LeaveForm getByProId(String processInstanceId) {
		
		return leaveFormRepository.getByprocessInstanceId(processInstanceId);
	}

}
