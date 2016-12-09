package com.spark.network.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spark.network.entity.Role;
import com.spark.network.entity.User;
import com.spark.network.repository.RoleRepository;
import com.spark.network.repository.UserRepository;
import com.spark.network.service.IUserService;

@Service(value = "userService")
public class UserServiceImpl implements IUserService {

	@Resource(name = "userRepository")
	private UserRepository userRepository;
	@Resource(name = "roleRepository")
	private RoleRepository roleRepository;

	@Transactional
	public User getByAccount(String account) {

		User user = userRepository.getByAccount(account);
		return user;
	}

	@Transactional
	public void save(User user) {

		userRepository.saveAndFlush(user);

	}

	@Transactional
	public void delete(Integer id) {
		userRepository.delete(id);
	}

	@Transactional
	public void addUser(User user) {
		userRepository.save(user);

	}

	@Transactional(readOnly = true)
	public User getById(Integer id) {

		return userRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	public List<User> getAll() {

		return userRepository.getALL();
	}
	
	@Transactional(readOnly = true)
	public List<User> getAllExist(String flag) {
		
		return userRepository.getALLExist(flag);
	}
	
	@Transactional(readOnly=true)
	public Page<User> getPage(int pageNo, int pageSize){
		PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
		return userRepository.findAll(pageable);
	}

	@Transactional(readOnly=true)
	public List<User> getByRoleName(String roleName) {
		Role role = roleRepository.findRoleByRoleName(roleName);
		return (List<User>) role.getUsers();
	}

	@Transactional
	public void changePassword(String password,String userId) {
		userRepository.changePass(password,userId);
	}

}
