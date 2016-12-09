package com.spark.network.service;

import java.util.List;

import com.spark.network.entity.Menu;

public interface IMenuService {
	
	public void save(Menu permission);
	
	public void deleteById(Integer id);

	public Menu getById(Integer id);
	
	public List<Menu>getAll();

	public List<Menu> getByUserId(String userId);
}
