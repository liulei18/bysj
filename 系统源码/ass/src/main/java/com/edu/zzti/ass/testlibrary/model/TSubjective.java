package com.edu.zzti.ass.testlibrary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.edu.zzti.ass.management.model.TTeacher;

/**
 * TSubjective entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_subjective_temp", catalog = "ass")
public class TSubjective implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4102622647684553108L;
	private Integer id;
	private TTeacher TTeacher;
	private TSector tSector;
	private String question;
	@Lob
	private String answer;
	private String skey1;

	// Constructors

	/** default constructor */
	public TSubjective() {
	}

	/** full constructor */
	public TSubjective(TTeacher TTeacher, TSector tSector,
			String question, String answer, String skey1) {
		this.TTeacher = TTeacher;
		this.tSector = tSector;
		this.question = question;
		this.answer = answer;
		this.skey1 = skey1;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacherId")
	public TTeacher getTTeacher() {
		return this.TTeacher;
	}

	public void setTTeacher(TTeacher TTeacher) {
		this.TTeacher = TTeacher;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sectorId")
	public TSector gettSector() {
		return tSector;
	}

	public void settSector(TSector tSector) {
		this.tSector = tSector;
	}


	@Column(name = "question")
	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Column(name = "answer",length=16777216)
	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Column(name = "skey1")
	public String getSkey1() {
		return skey1;
	}

	public void setSkey1(String skey1) {
		this.skey1 = skey1;
	}

	@Override
	public String toString() {
		return "TSubjective [id=" + id + ", TTeacher=" + TTeacher
				+ ", tSector=" + tSector + ",  question=" + question + ", answer=" + answer + ", skey1="
				+ skey1 + "]";
	}

}