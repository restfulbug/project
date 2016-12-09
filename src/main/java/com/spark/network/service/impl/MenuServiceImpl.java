package com.spark.network.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spark.network.entity.Menu;
import com.spark.network.entity.Role;
import com.spark.network.entity.User;
import com.spark.network.repository.MenuRepository;
import com.spark.network.repository.UserRepository;
import com.spark.network.service.IMenuService;
import com.spark.network.service.IRoleService;

@Service("menuService")
public class MenuServiceImpl implements IMenuService{

	@Resource(name="menuRepository")
	private MenuRepository menuRepository;
	@Resource(name="userRepository")
	private UserRepository userRepository;
	@Resource(name="roleService")
	private IRoleService roleService;
	
	@Transactional
	public void save(Menu menu) {
		menuRepository.save(menu);
	}

	@Transactional
	public void deleteById(Integer id) {
		menuRepository.delete(id);
		
	}

	@Transactional(readOnly=true)
	public Menu getById(Integer id) {
		return menuRepository.findOne(id);
		
	}

	@Transactional(readOnly=true)
	public List<Menu> getAll() {
		
		return menuRepository.findAll();
	}

	@Transactional(readOnly=true)
	public List<Menu> getByUserId(String userId) {
		List<Menu> lists = new ArrayList<>();
	    User user = userRepository.getOne(Integer.parseInt(userId));
	    Collection<Role> roles = user.getRoles();
	    for (Role role : roles) {
			Collection<Menu> menus = role.getMenus();
			lists.addAll(menus);
		}
		return lists;
	}

}
