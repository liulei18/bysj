package com.edu.zzti.ass.testlibrary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.edu.zzti.ass.management.model.TTeacher;

/**
 * TJudge entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_judge_temp", catalog = "ass")
public class TJudge implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 736433706690935094L;
	private Integer id;
	private TTeacher TTeacher;
	private TSector tSector;
	private String question;
	private Integer answer;
	private String jkey1;
	// Constructors

	/** default constructor */
	public TJudge() {
	}

	/** full constructor */
	public TJudge(TTeacher TTeacher, TSector tSector,
			String question, Integer answer, String jkey1) {
		this.TTeacher = TTeacher;
		this.tSector = tSector;
		this.question = question;
		this.answer = answer;
		this.jkey1 = jkey1;
		
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
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

	@Column(name = "answer")
	public Integer getAnswer() {
		return this.answer;
	}

	public void setAnswer(Integer answer) {
		this.answer = answer;
	}
	@Column(name = "jkey1")
	public String getJkey1() {
		return jkey1;
	}

	public void setJkey1(String jkey1) {
		this.jkey1 = jkey1;
	}
	


	@Override
	public String toString() {
		return "TJudge [id=" + id + ", TTeacher=" + TTeacher + ", tSector="
				+ tSector + ",  question=" + question
				+ ", answer=" + answer + ", jkey1=" + jkey1 + "]";
	}

		
}