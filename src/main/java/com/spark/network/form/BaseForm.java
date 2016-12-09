package com.spark.network.form;
import java.io.Serializable;

import com.spark.network.util.DateUtil;

public  class BaseForm implements Serializable {

	private static final long serialVersionUID = 8541185667131778272L;

	// 申请日期
	private String requestDate = DateUtil.getTodayString();

	// 流程实例id
	private String proId;

	// 申请的标题
	private String title;

	// 流程状态
   private String processStatus;
	
	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

}
