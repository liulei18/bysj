package com.edu.zzti.ass.onlinestudy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
@Entity
@Table(name = "t_testanswer", catalog = "ass")
public class TestAnswer implements Serializable{

	private static final long serialVersionUID = -8415857848635102881L;
	private Integer id;
	private String singleAnswers;
	private String judgeAnswers;
	@Lob
	private String subjectAnswers;
	private String fillAnswers;
	
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
	public String getFillAnswers() {
		return fillAnswers;
	}
	public void setFillAnswers(String fillAnswers) {
		this.fillAnswers = fillAnswers;
	}
	
	
	
}
