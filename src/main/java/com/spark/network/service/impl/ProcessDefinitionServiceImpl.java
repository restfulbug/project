package com.spark.network.service.impl;

import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spark.network.service.IProcessDefinitionService;

@Service(value="processDefinitionService")
public class ProcessDefinitionServiceImpl implements IProcessDefinitionService {

	@Resource(name = "repositoryService")
	private RepositoryService repositoryService;

	@Transactional(readOnly=true)
	public List<ProcessDefinition> list() {
		return repositoryService.createProcessDefinitionQuery().list();
	}

	// 部署请假流程定义
	public void initProcessDefinition(RepositoryService repositoryService) {
		InputStream in1 = ProcessDefinition.class.getResourceAsStream("/bpmn/Leave.bpmn");
		InputStream in2 = ProcessDefinition.class.getResourceAsStream("/bpmn/Leave.png");
        ProcessDefinition processDefinition = null;
		repositoryService.createDeployment().name("请假流程")
				.addInputStream("/bpmn/Leave.bpmn", in1).addInputStream("/bpmn/Leave.png", in2).deploy();
	}
	// 部署报修流程定义
	public void initProcessDefinitionRep(RepositoryService repositoryService) {
		InputStream in3 = ProcessDefinition.class.getResourceAsStream("/bpmn/repairForm.bpmn");
		InputStream in4 = ProcessDefinition.class.getResourceAsStream("/bpmn/repairForm.png");
		
		repositoryService.createDeployment().name("报修流程")
		.addInputStream("/bpmn/repairForm.bpmn", in3).addInputStream("/bpmn/repairForm.png", in4).deploy();
	}
}
