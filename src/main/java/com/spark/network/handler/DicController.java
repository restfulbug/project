package com.spark.network.handler;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spark.network.entity.Dictionary;
import com.spark.network.service.IDictionaryService;

@Controller
public class DicController {
	
	@Resource(name="dictionaryService")
	private IDictionaryService dictionaryService;
	
	@RequestMapping(value="/dic")
	public String show(Map<String, Object> map){
		map.put("dic", dictionaryService.getDisByKeyWord());
		map.put("dics", dictionaryService.getByKeyWord("性别"));
		return"dic/dictionary";
	}
	@RequestMapping(value="/dic/{dicId}")
	public String show(@PathVariable(value="dicId")Integer dicId,Map<String, Object> map){
				
		Dictionary dictionary = dictionaryService.getById(dicId);
		List<Dictionary> dictionaries = dictionaryService.getByKeyWord(dictionary.getKeyWord());
		map.put("typeDic",dictionaries);
		return"dic/dictionary";
	}
	
	@RequestMapping(value="/edit/{keyWord}")
	public String edit(@PathVariable(value="keyWord")String keyWord,Map<String, Object> map){
		map.put("dics", dictionaryService.getByKeyWord(keyWord));
		return"dic/dictionary";
	}

}
