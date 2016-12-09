package com.spark.network.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Cacheable
@Entity
@Table(name = "t_repair_form")
public class RepairForm implements Serializable {

	private static final long serialVersionUID = 3945747658611817436L;

	private Integer id;
	
	private Date creatDate;

	private String detailDesc;

	private String detailLocation;

	private User user;

	private User staff;

	private FaultLevel faultLevel;

	private RepairFormState repairState;

	private FaultType faultType;

	private Dictionary dictionary;

	private Building building;
	
	private String tel;
	
	private String processInstanceId;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "c_creat_time")
	public Date getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}

	@Column(name = "c_detail_info")
	public String getDetailDesc() {
		return detailDesc;
	}

	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name = "staff_id")
	public User getStaff() {
		return staff;
	}

	public void setStaff(User staff) {
		this.staff = staff;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fault_leave_id")
	public FaultLevel getFaultLevel() {
		return faultLevel;
	}

	public void setFaultLevel(FaultLevel faultLevel) {
		this.faultLevel = faultLevel;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	public RepairFormState getRepairState() {
		return repairState;
	}

	public void setRepairState(RepairFormState repairState) {
		this.repairState = repairState;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fault_type_id")
	public FaultType getFaultType() {
		return faultType;
	}

	public void setFaultType(FaultType faultType) {
		this.faultType = faultType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dic_id")
	public Dictionary getDictionary() {
		return dictionary;
	}

	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	@Column(name = "c_detail_location")
	public String getDetailLocation() {
		return detailLocation;
	}

	public void setDetailLocation(String detailLocation) {
		this.detailLocation = detailLocation;
	}

	@Column(name = "c_tel")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@ManyToOne
	@JoinColumn(name = "build_id")
	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}
   
	@Column(name="proc_inst_id")
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
}
