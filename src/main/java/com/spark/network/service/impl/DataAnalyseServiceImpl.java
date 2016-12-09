package com.spark.network.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spark.network.form.AnalyCount;
import com.spark.network.repository.DataAnalyseRepository;
import com.spark.network.service.IDataAnalyseService;

@Service("dataAnalyseService")
public class DataAnalyseServiceImpl implements IDataAnalyseService{

	@Resource(name= "dataAnalyseRepository")
	private DataAnalyseRepository dataAnalyseRepository;

	@Transactional
	public List<AnalyCount> timeAnalyseToday() {
		
		return dataAnalyseRepository.timeAnalyseToday();
	}

	@Transactional
	public List<AnalyCount> timeAnalyseYesterday() {
		return dataAnalyseRepository.timeAnalyseYestetday();
	}

	@Transactional
	public List<AnalyCount> timeAnalyseThisWeek() {
		return dataAnalyseRepository.timeAnalyseThisWeek();
	}

	@Transactional
	public List<AnalyCount> timeAnalyseThisMonth() {
		return dataAnalyseRepository.timeAnalyseThisMonth();
	}

	@Transactional
	public List<AnalyCount> timeAnalyseThisYear() {
		return dataAnalyseRepository.timeAnalyseThisYear();
	}

	@Transactional
	public List<AnalyCount> posAnalyseToday() {
		return dataAnalyseRepository.posAnalyseToday();
	}
	@Transactional
	public List<AnalyCount> posAnalyseYesterday() {
		return dataAnalyseRepository.posAnalyseYesterday();
	}
	@Transactional
	public List<AnalyCount> posAnalyseThisweek() {
		return dataAnalyseRepository.posAnalyseThisweek();
	}
	@Transactional
	public List<AnalyCount> posAnalyseThisMonth() {
		return dataAnalyseRepository.posAnalyseThisMonth();
	}
	@Transactional
	public List<AnalyCount> posAnalyseThisyear() {
		return dataAnalyseRepository.posAnalyseThisYear();
	}
	
}
