package com.spark.network.handler;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spark.network.entity.Role;
import com.spark.network.entity.User;
import com.spark.network.service.IRoleService;
import com.spark.network.service.IUserService;

@Controller
public class RoleController {
	
	@Resource(name="roleService")
	private IRoleService roleService;
	@Resource(name="userService")
	private IUserService userService;
	
	@RequestMapping(value="/role-add",method=RequestMethod.POST)
	public String addRole(Role role){
		roleService.save(role);
		return "redirect:/role-list";
	}
	@RequestMapping(value="/role-add",method=RequestMethod.GET)
	public String toAdd(Map<String, Object> map){
		map.put("role", new Role());
		return "role/addRole";
	}
	
	@RequestMapping(value="/role{id}",method=RequestMethod.DELETE)
	public String deleteRole(@RequestParam(value="id")Integer id){
		roleService.delete(id);
		return "redirect:/role-list";
	}
	
	@RequestMapping(value="/role{id}",method=RequestMethod.PUT)
	public String getRole(@RequestParam(value="id")Integer id){
		roleService.getById(id);
		return "";
	}
	
	@RequestMapping(value="/roles",method=RequestMethod.PUT)
	public String getAll(Map<String,Object> map){
		map.put("roles", roleService.getAll());
		return "";
	}
	
	@RequestMapping(value="/role-list")
	public String list(Map<String,Object> map){
		map.put("roles", roleService.getAll());
		return "role/list";
	}
	@ResponseBody
	@RequestMapping(value="/ajaxValidateRoleName",method=RequestMethod.POST)
	public String validateLastName(@RequestParam(value="roleName",required=true) String roleName){
		Role role = roleService.getRoleByRoleName(roleName);
		if(role == null){
			return "0";
		}
		else{
			return "1";
		}
	}
	
	
}
