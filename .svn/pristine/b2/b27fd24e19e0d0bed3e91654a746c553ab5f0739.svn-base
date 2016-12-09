package com.spark.network.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spark.network.entity.Dictionary;
import com.spark.network.repository.DictionaryRepository;
import com.spark.network.service.IDictionaryService;
@Service(value="dictionaryService")
public class DictionaryServiceImpl implements IDictionaryService {

	@Resource(name="dictionaryRepository")
	private DictionaryRepository dictionaryRepository;

	@Transactional
	public void save(Dictionary dictionary) {
		dictionaryRepository.save(dictionary);
	}

	@Transactional(readOnly=true)
	public List<Dictionary> getAll() {
	
		return dictionaryRepository.findAll();
	}

	@Transactional(readOnly=true)
	public Dictionary getById(Integer id) {

		return  dictionaryRepository.findOne(id) ;
	}

	@Transactional(readOnly=true)
	public List<Dictionary> getByKeyWord(String keyword) {
		
		return dictionaryRepository.getByKeyWord(keyword);
	}
	@Transactional(readOnly=true)
	public List<Dictionary> getDisByKeyWord() {
		
		return dictionaryRepository.getDisByKeyWord();
	}
}