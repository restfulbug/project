package com.spark.network.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.QueryHint;

import net.sf.ehcache.search.aggregator.Count;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import com.spark.network.entity.RepairForm;
import com.spark.network.entity.User;

public interface RepairFormRepository extends JpaRepository<RepairForm, Integer>,JpaSpecificationExecutor<RepairForm>{
	
@Query("SELECT r FROM RepairForm r WHERE r.user = :user")
public List<RepairForm> getrepairFormByUser(@Param("user") User user);

@QueryHints({@QueryHint(name=org.hibernate.jpa.QueryHints.HINT_CACHEABLE,value="true")})
@Query("FROM RepairForm r")
public List<RepairForm> getALL();

@Query(value="SELECT dic_id,COUNT(*) FROM t_repair_form  GROUP BY dic_id",nativeQuery=true)

public List<Count> counts();

RepairForm getByprocessInstanceId(String processInstanceId);

//新加
Page<RepairForm> getByUser_userName(String UserName,Pageable pageable);

//新加
Page<RepairForm> getByCreatDateAfter(Date date,Pageable pageable);

}
