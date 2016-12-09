package com.spark.network.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_dic")
public class Dictionary implements Serializable{
	
	private static final long serialVersionUID = 2482346930585232186L;
	private Integer id;
	private String keyWord;
	private String keyCode;
	private String keyName;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="c_key_word")
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	@Column(name="c_key_code")
	public String getKeyCode() {
		return keyCode;
	}
	
	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}
	@Column(name="c_key_name")
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	} 
}
