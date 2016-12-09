package com.spark.network.service;

import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;

public interface IProcessDefinitionService {

	List<ProcessDefinition> list();
	
 void initProcessDefinition(RepositoryService repositoryService);
 
 void initProcessDefinitionRep(RepositoryService repositoryService);
}
