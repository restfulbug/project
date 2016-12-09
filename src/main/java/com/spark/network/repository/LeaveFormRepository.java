package com.spark.network.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spark.network.entity.LeaveForm;
import com.spark.network.entity.User;

public interface LeaveFormRepository extends JpaRepository<LeaveForm, Integer>{

	List<LeaveForm> getByUser(User user);

	LeaveForm getByprocessInstanceId(String processInstanceId);


}
