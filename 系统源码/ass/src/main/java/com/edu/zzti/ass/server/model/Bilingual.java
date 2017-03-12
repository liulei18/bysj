package com.edu.zzti.ass.server.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "t_bilingual")
public class Bilingual implements Serializable {

	private static final long serialVersionUID = -7294716898609924656L;

	private Integer id;
	private String title;
	@Lob
	private String enContent;
	@Lob
	private String zhContent;
	
	private Date addTimes;

	public Date getAddTimes() {
		return addTimes;
	}

	public void setAddTimes(Date addTimes) {
		this.addTimes = addTimes;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "title", length = 64,unique=true)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "enContent", length = 16777216)
	public String getEnContent() {
		return enContent;
	}

	public void setEnContent(String enContent) {
		this.enContent = enContent;
	}

	@Column(name = "zhContent", length = 16777216)
	public String getZhContent() {
		return zhContent;
	}

	public void setZhContent(String zhContent) {
		this.zhContent = zhContent;
	}

}
