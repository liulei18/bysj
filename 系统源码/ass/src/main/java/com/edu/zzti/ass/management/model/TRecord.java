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
@Table(name = "t_student_log", catalog = "ass")
public class TRecord implements java.io.Serializable {

	private Integer id;
	private String num;
	private Date operTimes;
	private String oper;
	
	private String remark;


	public TRecord() {
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

	
	public String getOper() {
		return this.oper;
	}

	
	public void setOper(String oper) {
		this.oper = oper;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}