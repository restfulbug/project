package com.spark.network.handler;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spark.network.form.AnalyCount;
import com.spark.network.service.IDataAnalyseService;
import com.spark.network.service.IRepairFormService;


@Controller
public class DataAnalyseController {
	
	@Resource(name="dataAnalyseService")
    private IDataAnalyseService dataAnalyseService;
	@Resource(name="repairFormService")
	private IRepairFormService repairFormService;
	
	@RequestMapping(value="/rep/timeAnalyse/{arg}")
	public String timeAnalyse(@PathVariable("arg")String arg,Map<String, Object> map){
		if (arg.equals("today")) {
			List<AnalyCount> analyCounts =dataAnalyseService.timeAnalyseToday();
			 int all = 0;
			for (AnalyCount analyCount : analyCounts) {
				all = all + Integer.parseInt(analyCount.getNum());
			}
			map.put("timeCounts", analyCounts);
			map.put("all", all);
			map.put("arg", arg);
		}
		if (arg.equals("yesterday")) {
			List<AnalyCount> analyCounts =dataAnalyseService.timeAnalyseYesterday();
			 int all = 0;
			for (AnalyCount analyCount : analyCounts) {
				all = all + Integer.parseInt(analyCount.getNum());
			}
			map.put("timeCounts", analyCounts);
			map.put("all", all);
			map.put("arg", arg);
		}
		if (arg.equals("thisWeek")) {
			List<AnalyCount> analyCounts =dataAnalyseService.timeAnalyseThisWeek();
			int all = 0;
			for (AnalyCount analyCount : analyCounts) {
				all = all + Integer.parseInt(analyCount.getNum());
			}
			map.put("timeCounts", analyCounts);
			map.put("all", all);
			map.put("arg", arg);
		}
		if (arg.equals("thisMonth")) {
			List<AnalyCount> analyCounts =dataAnalyseService.timeAnalyseThisMonth();
			int all = 0;
			for (AnalyCount analyCount : analyCounts) {
				all = all + Integer.parseInt(analyCount.getNum());
			}
			map.put("timeCounts", analyCounts);
			map.put("all", all);
			map.put("arg", arg);
		}
		if (arg.equals("thisYear")) {
			List<AnalyCount> analyCounts =dataAnalyseService.timeAnalyseThisYear();
			int all = 0;
			for (AnalyCount analyCount : analyCounts) {
				all = all + Integer.parseInt(analyCount.getNum());
			}
			map.put("timeCounts", analyCounts);
			map.put("all", all);
			map.put("arg", arg);
		}
		return"analyse/timeanalyse";
	}
	@ResponseBody
	@RequestMapping(value="/rep/timeAnalyseJson")
	public List<AnalyCount> timeAnalyseJson(@RequestParam("arg") String arg,Map<String, Object> map){
		if (arg.equals("yesterday")) {
			return dataAnalyseService.timeAnalyseYesterday();
		}
		if (arg.equals("thisWeek")) {
			return dataAnalyseService.timeAnalyseThisWeek();
		}
		if (arg.equals("thisMonth")) {
			return dataAnalyseService.timeAnalyseThisMonth();
		}
		if (arg.equals("thisYear")) {
			return dataAnalyseService.timeAnalyseThisYear();
		}
		return dataAnalyseService.timeAnalyseToday();
	}
	@RequestMapping(value="/rep/posAnalyse/{arg}")
	public String posAnalyse(@PathVariable("arg")String arg,Map<String, Object> map){
		if (arg.equals("today")) {
			List<AnalyCount> analyCounts =dataAnalyseService.posAnalyseToday();
			 int all = 0;
			for (AnalyCount analyCount : analyCounts) {
				all = all + Integer.parseInt(analyCount.getNum());
			}
			map.put("posCounts", analyCounts);
			map.put("all", all);
			map.put("arg", arg);
		}
		
		if (arg.equals("yesterday")) {
		List<AnalyCount> analyCounts = dataAnalyseService.posAnalyseYesterday();
		int all = 0;
		for (AnalyCount analyCount : analyCounts) {
			all = all + Integer.parseInt(analyCount.getNum());
		}
		map.put("all", all);
		map.put("arg", arg);
		map.put("posCounts", analyCounts);
	}
	if (arg.equals("thisWeek")) {
		List<AnalyCount> analyCounts = dataAnalyseService.posAnalyseThisweek();
		int all = 0;
		for (AnalyCount analyCount : analyCounts) {
			all = all + Integer.parseInt(analyCount.getNum());
		}
		map.put("all", all);
		map.put("arg", arg);
		map.put("posCounts", analyCounts);
	}
	if (arg.equals("thisMonth")) {
		List<AnalyCount> analyCounts = dataAnalyseService.posAnalyseThisMonth();
		int all = 0;
		for (AnalyCount analyCount : analyCounts) {
			all = all + Integer.parseInt(analyCount.getNum());
		}
		map.put("all", all);
		map.put("arg", arg);
		map.put("posCounts", analyCounts);
	}
	if (arg.equals("thisYear")) {
		List<AnalyCount> analyCounts = dataAnalyseService.posAnalyseThisyear();
		int all = 0;
		for (AnalyCount analyCount : analyCounts) {
			all = all + Integer.parseInt(analyCount.getNum());
		}
		map.put("all", all);
		map.put("arg", arg);
		map.put("posCounts", analyCounts);
	}
	return "analyse/posanalyse";
	}
	@ResponseBody
	@RequestMapping(value="/rep/posAnalyseJson")
	public List<AnalyCount> posAnalyseJson(@RequestParam("arg") String arg,Map<String, Object> map){
		if (arg.equals("yesterday")) {
			return dataAnalyseService.posAnalyseYesterday();
		}
		if (arg.equals("thisWeek")) {
			return dataAnalyseService.posAnalyseThisweek();
		}
		if (arg.equals("thisMonth")) {
			return dataAnalyseService.posAnalyseThisMonth();
		}
		if (arg.equals("thisYear")) {
			return dataAnalyseService.posAnalyseThisyear();
		}
		return dataAnalyseService.posAnalyseToday();
	}
}
