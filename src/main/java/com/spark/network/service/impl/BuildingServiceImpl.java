package com.spark.network.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spark.network.entity.Building;
import com.spark.network.repository.BuildingRepository;
import com.spark.network.service.IBuildingService;

@Service(value="buildingService")
public class BuildingServiceImpl  implements IBuildingService{

	@Resource(name="buildingRepository")
	private BuildingRepository buildingRepository;
	
	@Transactional(readOnly=true)
	public List<Building> getALL() {
		
		return buildingRepository.findAll();
	}
	@Transactional
	public void deleteById(Integer buildingId) {
		buildingRepository.delete(buildingId);
	}
	@Transactional
	public void add(Building building) {
		buildingRepository.save(building );
	}
	@Transactional(readOnly=true)
	public Building getBuildingById(Integer buildingId) {
		
		return buildingRepository.getOne(buildingId);
	}

}
