package com.spark.network.handler;

import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spark.network.service.IProcessDefinitionService;

@Controller
public class ProcessDefinitionController {
	
	@Resource(name="processDefinitionService")
	private IProcessDefinitionService processDefinitionService;
	@Resource(name="repositoryService")
	private RepositoryService repositoryService;

	//发布请假流程
	@RequestMapping(value="/deploy")
	public String deployLea(){
		processDefinitionService.initProcessDefinition(repositoryService);
		return "redirect:/pd-list";
	}
	//发布报修流程
	@RequestMapping(value="/deployRep")
	public String deployRep(){
		processDefinitionService.initProcessDefinitionRep(repositoryService);
		return "redirect:/pd-list";
	}
	//列出所有流程
	@RequestMapping(value="/pd-list")
	public String list(Map<String, Object> map){
		map.put("defs", processDefinitionService.list());
		return "pd/pd";
	}
}
