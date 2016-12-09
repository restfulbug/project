package com.spark.network.junit;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spark.network.entity.Menu;
import com.spark.network.entity.Role;
import com.spark.network.entity.User;
import com.spark.network.service.IMenuService;
import com.spark.network.service.IRoleService;
import com.spark.network.service.IUserService;
import com.spark.network.util.GobelKey;

public class testInitSystemData{
 
	private IRoleService roleService;
	private IUserService userService;
	private IMenuService menuService;
	private ApplicationContext ctx;
	{
		ctx =  new ClassPathXmlApplicationContext("applicationContext.xml");
		userService =  (IUserService) ctx.getBean("userService");
		 roleService = (IRoleService) ctx.getBean("roleService");
		 menuService =  (IMenuService) ctx.getBean("menuService");
	}
	@Test
	public void initPermission() throws Exception{
	}
	
	@Test
	public void initAdminRole() throws Exception{

		Role role = new Role();
		role.setRoleName("administrator");
		role.setDescription("系统管理员角色");
		Role role1 = new Role();
		role1.setRoleName("generaluser");
		role1.setDescription("一般管理员管理员角色");
		
		roleService.save(role);
		roleService.save(role1);
	}
	
	@Test
	public void initAdminUser(){
		List<Role> roles = new ArrayList<Role>();
		 Role  role = roleService.getRoleByRoleName("administrator"); 
	     Role  role2 = roleService.getRoleByRoleName("generaluser"); 
		roles.add(role);
		roles.add(role2);
	    User user = new User();
		user.setAccount("admin");
		user.setPassword("123456");
		user.setUserName("july");
		user.setIsExist(GobelKey.YES);
		
		user.setTelephone("15737315768");
		user.setRoles(roles);
		userService.save(user);
	}
	
	@Test
	public void testLefeOuterJoin(){
	
   User user = userService.getById(1);
   System.out.println(user.getRoles());
   User user2 = userService.getById(1);
		
	}
	
}
