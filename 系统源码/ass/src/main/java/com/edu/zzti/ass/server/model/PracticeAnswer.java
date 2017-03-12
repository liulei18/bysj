package com.edu.zzti.ass.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "t_practiceanswer", catalog = "ass")
public class PracticeAnswer implements Serializable{
	
	private static final long serialVersionUID = -3063348495809899463L;
	
	private Integer id;
	private String singleAnswers;
	private String judgeAnswers;
	@Lob
	private String subjectAnswers;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSingleAnswers() {
		return singleAnswers;
	}
	public void setSingleAnswers(String singleAnswers) {
		this.singleAnswers = singleAnswers;
	}
	public String getJudgeAnswers() {
		return judgeAnswers;
	}
	public void setJudgeAnswers(String judgeAnswers) {
		this.judgeAnswers = judgeAnswers;
	}
	@Column(length=16777216)
	public String getSubjectAnswers() {
		return subjectAnswers;
	}
	public void setSubjectAnswers(String subjectAnswers) {
		this.subjectAnswers = subjectAnswers;
	}
	
	
}
