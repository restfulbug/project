package com.spark.network.service;

import java.util.List;

import com.spark.network.entity.FaultType;

public interface IFaultTypeService{

	public void save (FaultType faultType);
	
	public void batchSave(List<FaultType> faultTypes);
	
	public void update(FaultType faultType,Integer id);
	
	public void delete(Integer id);
	
	public FaultType getById(Integer id);
	
	public List<FaultType> getAll();
}
