package com.spark.network.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spark.network.entity.Role;
import com.spark.network.entity.User;
public interface RoleRepository extends JpaRepository<Role, Integer>{
	
@Query("SELECT r FROM Role r WHERE r.roleName = ?1")
  public Role findRoleByRoleName(String roleName);

@Query("SELECT r FROM Role r inner join r.users as u ")
public Role findRoleByUser(User user);
	
	
	
}
