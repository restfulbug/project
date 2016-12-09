package com.spark.network.service;

import java.util.List;

import com.spark.network.entity.Dictionary;

public interface IDictionaryService {

	public List<Dictionary> getAll();
	
	public void save(Dictionary dictionary);
	
	public Dictionary getById(Integer id);
	
	public List<Dictionary> getByKeyWord(String keyword);
	
	public List<Dictionary> getDisByKeyWord();
	
}
