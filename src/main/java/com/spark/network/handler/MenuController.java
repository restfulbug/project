package com.spark.network.handler;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spark.network.entity.Menu;
import com.spark.network.service.IMenuService;

@Controller
public class MenuController {
	
	@Resource(name="menuService")
	private IMenuService menuService;
	
	@RequestMapping(value="/menu",method=RequestMethod.POST)
	public String addMenu(Menu menu){
		menuService.save(menu);
		return "";
	}
	@RequestMapping(value="/menu/{id}",method=RequestMethod.DELETE)
	public String deleteMenu(@RequestParam(value="id") Integer id){
		menuService.deleteById(id);
		return "";
	}
	@RequestMapping(value="/menu/{id}",method=RequestMethod.GET)
	public String getMenu(@RequestParam(value="id") Integer id){
		menuService.getById(id);
		return "";
	}
	@RequestMapping(value="/menus/{userid}")
	public String getMenusByRoleId(@RequestParam(value="userid" ,required= false) String userId){
		Map<String ,Object> map = new HashMap<String, Object>();
		map.put("menus",menuService.getByUserId(userId));
		return "system/nav";
	}
	
	@RequestMapping(value="/menu/{id}",method=RequestMethod.PUT)
	public String updateMenu(Menu menu){
		menuService.save(menu);
		return "";
	}
	@RequestMapping(value="/menu-list",method=RequestMethod.GET)
	public String listMneu(Map<String, Object>map){
		map.put("menus", menuService.getAll());
		return "menu/list";
	}

}
