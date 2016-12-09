package com.spark.network.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spark.network.entity.FaultLevel;

public interface FaultLevelRepository extends JpaRepository<FaultLevel, Integer> {
    
	@Modifying
	@Query("UPDATE FaultLevel f SET f= :faultLevel WHERE f.id = :id")
	public void updateFaultLeave(@Param("faultLevel") FaultLevel faultLeave,@Param("id") Integer id);
	
}
