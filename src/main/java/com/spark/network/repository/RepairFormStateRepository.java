package com.spark.network.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spark.network.entity.RepairFormState;
public interface RepairFormStateRepository extends JpaRepository<RepairFormState, Integer> {

	
	public RepairFormState getByStateName (String stateName);
}
