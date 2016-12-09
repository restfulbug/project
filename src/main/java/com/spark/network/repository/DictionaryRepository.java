package com.spark.network.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spark.network.entity.Dictionary;
public interface DictionaryRepository extends JpaRepository<Dictionary, Integer> {
	
	public List<Dictionary> getByKeyWord(String keyword);
	
	@Query(" SELECT d FROM Dictionary d GROUP BY  d.keyWord")
	public List<Dictionary> getDisByKeyWord();
	

}
