package com.spark.network.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spark.network.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User getByAccount(String account);
 
	@Query("SELECT u FROM User u WHERE u.isExist = :flag ")
	List<User> getALLExist(@Param("flag") String flag);
	
	@Query("FROM User u ")
	List<User> getALL();

	@Query("UPDATE User u SET u.password = :password WHERE u.id = :userId ")
	void changePass(String password,String userId);

	
}
