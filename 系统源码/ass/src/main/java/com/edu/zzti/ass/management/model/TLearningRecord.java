package com.edu.zzti.ass.management.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.edu.zzti.ass.testlibrary.model.TJudge;
import com.edu.zzti.ass.testlibrary.model.TSinglesel;
import com.edu.zzti.ass.testlibrary.model.TSubjective;

/**
 * TRecord entity. 
 */
@Entity
@Table(name = "t_learning_log", catalog = "ass")
public class TLearningRecord implements java.io.Serializable {

	private Integer id;
	private String num;
	private String name;
	private Date operTimes;
	private String unitId;
	


	public TLearningRecord() {
	}
	

	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	
	public Date getOperTimes() {
		return operTimes;
	}

	public void setOperTimes(Date operTimes) {
		this.operTimes = operTimes;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

}