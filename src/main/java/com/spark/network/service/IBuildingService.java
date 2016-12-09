package com.spark.network.service;

import java.util.List;

import com.spark.network.entity.Building;

public interface IBuildingService {

public List<Building> getALL();

public void deleteById(Integer buildingId);

public void add(Building building);

public Building getBuildingById(Integer buildingId) ;
}
