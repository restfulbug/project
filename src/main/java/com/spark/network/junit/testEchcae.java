package com.spark.network.junit;

import java.util.List;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spark.network.entity.Dictionary;
import com.spark.network.entity.RepairForm;
import com.spark.network.service.IDictionaryService;
import com.spark.network.service.IRepairFormService;

public class testEchcae {

	private ApplicationContext ctx;

	private IRepairFormService repairFormService;
    private IDictionaryService dictionaryService;
    private TaskService taskService;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		repairFormService = (IRepairFormService) ctx.getBean("repairFormService");
	    dictionaryService = (IDictionaryService) ctx.getBean("dictionaryService");
	    taskService = (TaskService) ctx.getBean("taskService");
	}

	@Test
	public void testRepairFrom() {
		@SuppressWarnings("unused")
		List<RepairForm> repairForms = null;
	 repairForms = repairFormService.findAllRepairform();
	
	}
	@Test
	public void testDic() {
		List<Dictionary> dictionaries = dictionaryService.getByKeyWord("区域类型");
	    System.out.println(dictionaries);
	}	
	@Test
	public void testTask() {
	Task task = taskService.createTaskQuery().taskName("员工维修").singleResult();
	System.out.println(task.getId());
	}	
}
