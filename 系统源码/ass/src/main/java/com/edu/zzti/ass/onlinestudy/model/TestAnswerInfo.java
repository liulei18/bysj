package com.edu.zzti.ass.onlinestudy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.edu.zzti.ass.management.model.TStudent;
import com.edu.zzti.ass.testlibrary.model.TUnit;

@Entity
@Table(name = "t_testanswerinfo", catalog = "ass")
public class TestAnswerInfo {

	private Integer id;
	private String singleAnswers;
	private String judgeAnswers;
	@Lob
	private String subjectAnswers;
	private String fillAnswers;
	private Date createDate;
	private int info;
	private TestPaper paper;
	private TStudent student;
	private TUnit unit;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getInfo() {
		return info;
	}

	public void setInfo(int info) {
		this.info = info;
	}
	@OneToOne
	@JoinColumn(name = "paperId")
	public TestPaper getPaper() {
		return paper;
	}

	public void setPaper(TestPaper paper) {
		this.paper = paper;
	}
	
	public String getFillAnswers() {
		return fillAnswers;
	}

	public void setFillAnswers(String fillAnswers) {
		this.fillAnswers = fillAnswers;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "studentId")
	public TStudent getStudent() {
		return student;
	}

	public void setStudent(TStudent student) {
		this.student = student;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unitId")
	public TUnit getUnit() {
		return unit;
	}

	public void setUnit(TUnit unit) {
		this.unit = unit;
	}

}
