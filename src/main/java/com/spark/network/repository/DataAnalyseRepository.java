package com.spark.network.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.spark.network.form.AnalyCount;

@Repository
public class DataAnalyseRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	public List<AnalyCount> timeAnalyseToday(){
		String jpql ="SELECT c_time 小时, COUNT(HOUR(c_creat_time)) 数量 FROM t_cons_time  LEFT JOIN  t_repair_form ON t_cons_time.c_time = HOUR(c_creat_time) AND DATE(`c_creat_time`)=DATE(NOW()) GROUP BY t_cons_time.c_time";
		List<Object[]> list =  entityManager.createNativeQuery(jpql).getResultList();
         return objectArrayToAnalyCount(list);
	}
	public List<AnalyCount> timeAnalyseYestetday(){
		String jpql ="SELECT c_time 小时, COUNT(HOUR(c_creat_time)) 数量 FROM t_cons_time  LEFT JOIN  t_repair_form ON t_cons_time.c_time = HOUR(c_creat_time) AND TO_DAYS(NOW()) - TO_DAYS(`c_creat_time`) = 1  GROUP BY t_cons_time.c_time";
		List<Object[]> list =  entityManager.createNativeQuery(jpql).getResultList();
		return objectArrayToAnalyCount(list);
	}
	public List<AnalyCount> timeAnalyseThisWeek(){
		String jpql ="SELECT c_time 小时, COUNT(HOUR(c_creat_time)) 数量 FROM t_cons_time  LEFT JOIN  t_repair_form ON t_cons_time.c_time = HOUR(c_creat_time) AND MONTH(`c_creat_time`) = MONTH(CURDATE()) AND WEEK(`c_creat_time`) = WEEK(CURDATE()) GROUP BY t_cons_time.c_time";
		List<Object[]> list =  entityManager.createNativeQuery(jpql).getResultList();
		return objectArrayToAnalyCount(list);
	}
	public List<AnalyCount> timeAnalyseThisMonth(){
		String jpql ="SELECT c_time 小时, COUNT(HOUR(c_creat_time)) 数量 FROM t_cons_time  LEFT JOIN  t_repair_form ON t_cons_time.c_time = HOUR(c_creat_time) AND MONTH(`c_creat_time`) = MONTH(CURDATE()) AND YEAR(`c_creat_time`) = YEAR(CURDATE()) GROUP BY t_cons_time.c_time";
		List<Object[]> list =  entityManager.createNativeQuery(jpql).getResultList();
		return objectArrayToAnalyCount(list);
	}
	public List<AnalyCount> timeAnalyseThisYear(){
		String jpql ="SELECT c_time 小时, COUNT(HOUR(c_creat_time)) 数量 FROM t_cons_time  LEFT JOIN  t_repair_form ON t_cons_time.c_time = HOUR(c_creat_time) AND YEAR( `c_creat_time`) = YEAR( CURDATE( )) GROUP BY t_cons_time.c_time";
		List<Object[]> list =  entityManager.createNativeQuery(jpql).getResultList();
		return objectArrayToAnalyCount(list);
	}
	public List<AnalyCount> posAnalyseToday(){
		String jpql ="SELECT c_key_name 区域, COUNT(dic_id) 数量 FROM `t_dic` LEFT JOIN  t_repair_form ON dic_id =`t_dic`.id WHERE `t_dic`.c_key_word = '区域类型'AND DATE(`c_creat_time`)=DATE(NOW()) GROUP BY `t_dic`.c_key_name";
		List<Object[]> list =  entityManager.createNativeQuery(jpql).getResultList();
         return objectArrayToAnalyCount(list);
	}
	public List<AnalyCount> posAnalyseYesterday(){
		String jpql ="SELECT c_key_name 区域, COUNT(dic_id) 数量 FROM `t_dic` LEFT JOIN  t_repair_form ON dic_id =`t_dic`.id WHERE `t_dic`.c_key_word = '区域类型'AND TO_DAYS(NOW()) - TO_DAYS(`c_creat_time`) = 1  GROUP BY `t_dic`.c_key_name";
		List<Object[]> list =  entityManager.createNativeQuery(jpql).getResultList();
		return objectArrayToAnalyCount(list);
	}
	public List<AnalyCount> posAnalyseThisweek(){
		String jpql ="SELECT c_key_name 区域, COUNT(dic_id) 数量 FROM `t_dic` LEFT JOIN  t_repair_form ON dic_id =`t_dic`.id WHERE `t_dic`.c_key_word = '区域类型'AND MONTH(`c_creat_time`) = MONTH(CURDATE()) AND WEEK(`c_creat_time`) = WEEK(CURDATE())  GROUP BY `t_dic`.c_key_name";
		List<Object[]> list =  entityManager.createNativeQuery(jpql).getResultList();
		return objectArrayToAnalyCount(list);
	}
	public List<AnalyCount> posAnalyseThisMonth(){
		String jpql ="SELECT c_key_name 区域, COUNT(dic_id) 数量 FROM `t_dic` LEFT JOIN  t_repair_form ON dic_id =`t_dic`.id WHERE `t_dic`.c_key_word = '区域类型'AND MONTH(`c_creat_time`) = MONTH(CURDATE()) AND YEAR(`c_creat_time`) = YEAR(CURDATE()) GROUP BY `t_dic`.c_key_name";
		List<Object[]> list =  entityManager.createNativeQuery(jpql).getResultList();
		return objectArrayToAnalyCount(list);
	}
	public List<AnalyCount> posAnalyseThisYear(){
		String jpql ="SELECT c_key_name 区域, COUNT(dic_id) 数量 FROM `t_dic` LEFT JOIN  t_repair_form ON dic_id =`t_dic`.id WHERE `t_dic`.c_key_word = '区域类型'AND YEAR( `c_creat_time`) = YEAR( CURDATE( ))  GROUP BY `t_dic`.c_key_name";
		List<Object[]> list =  entityManager.createNativeQuery(jpql).getResultList();
		return objectArrayToAnalyCount(list);
	}
	
    private  List<AnalyCount> objectArrayToAnalyCount(List<Object[]> list){
    	List<AnalyCount> analyCounts = new ArrayList<>();
    	for (Object[] objects : list) {
			AnalyCount analyCount = new AnalyCount();
			analyCount.setName(objects[0].toString());
			analyCount.setNum(objects[1].toString());
			analyCounts.add(analyCount);
		}
    	return analyCounts;
    }
}
