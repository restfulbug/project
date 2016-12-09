package com.spark.network.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spark.network.entity.FaultType;
public interface FaultTypeRepository extends JpaRepository<FaultType, Integer> {

	@Modifying
	@Query("UPDATE FaultType f SET f= :faultType WHERE f.id = :id")
	public void updateFaultType(@Param("faultType") FaultType faultType,@Param("id") Integer id);
}
