package com.spark.network.junit;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spark.network.entity.LeaveForm;
import com.spark.network.form.BaseForm;
import com.spark.network.form.AnalyCount;
import com.spark.network.repository.DataAnalyseRepository;
import com.spark.network.repository.DictionaryRepository;
import com.spark.network.repository.RepairFormRepository;
import com.spark.network.service.ILeaveFormService;
import com.spark.network.service.IProcessService;


public class testJpa {

	private ApplicationContext ctx;
	
	private IProcessService processService;
	private ILeaveFormService leaveFormService;
	@SuppressWarnings("unused")
	private DictionaryRepository dictionaryRepository;
	private RepairFormRepository repairFormRepository;
	private DataAnalyseRepository dataAnalyseRepository;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		leaveFormService = (ILeaveFormService) ctx.getBean("leaveFormService"); 
		dictionaryRepository = (DictionaryRepository) ctx.getBean("dictionaryRepository"); 
	    processService = (IProcessService) ctx.getBean("processService");
	    repairFormRepository = (RepairFormRepository) ctx.getBean("repairFormRepository");
	   dataAnalyseRepository = (DataAnalyseRepository) ctx.getBean("dataAnalyseRepository");
	}
	
	
	public void test() {
		List<LeaveForm> leaveForms = leaveFormService.ListLeaveForm(1);
		     leaveForms.add(new LeaveForm());
		for (LeaveForm leaveForm : leaveForms) {
			System.out.println(leaveForm.getId());
		}
	}
	@Test
	public void test1() {
		List<BaseForm> process = processService.listLeaveFrom(1);
		
			System.out.println(process.size());
	
	
		}
	/*@Test
	public void testcount() throws SQLException {
	List<AnalyCount> counts = dataAnalyseRepository.posAnalyse();
	for (AnalyCount posCount : counts) {
		System.out.print("id"+posCount.getNum());
		System.out.println("num"+posCount.getName());
	}
	}*/
	@Test
	public void testTimeAnalyse() throws SQLException {
		
		 List<AnalyCount> lists = dataAnalyseRepository.timeAnalyseToday();
	  for (AnalyCount analyCount : lists) {
		System.out.print("名称    "+ analyCount.getName());
		System.out.println("    数量  "+ analyCount.getNum());
	}
	}


}
	
	
	
