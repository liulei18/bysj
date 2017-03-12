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
 * TSinglesel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_singlesel_temp", catalog = "ass")
public class TSinglesel implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3067494159594353979L;
	private Integer id;
	private TTeacher TTeacher;
	private TSector tSector;
	private String question;
	private String choiceA;
	private String choiceB;
	private String choiceC;
	private String choiceD;
	private String answer;
	private String skey1;
	// Constructors

	/** default constructor */
	public TSinglesel() {
	}

	/** full constructor */
	public TSinglesel(TTeacher TTeacher, TSector tSector,
			String question, String choiceA, String choiceB, String choiceC,
			String choiceD, String answer,String skey1) {
		this.TTeacher = TTeacher;
		this.tSector = tSector;
		this.question = question;
		this.choiceA = choiceA;
		this.choiceB = choiceB;
		this.choiceC = choiceC;
		this.choiceD = choiceD;
		this.answer = answer;
		this.skey1= skey1;
		
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

	@Column(name = "choiceA", length = 64)
	public String getChoiceA() {
		return this.choiceA;
	}

	public void setChoiceA(String choiceA) {
		this.choiceA = choiceA;
	}

	@Column(name = "choiceB", length = 64)
	public String getChoiceB() {
		return this.choiceB;
	}

	public void setChoiceB(String choiceB) {
		this.choiceB = choiceB;
	}

	@Column(name = "choiceC", length = 64)
	public String getChoiceC() {
		return this.choiceC;
	}

	public void setChoiceC(String choiceC) {
		this.choiceC = choiceC;
	}

	@Column(name = "choiceD", length = 64)
	public String getChoiceD() {
		return this.choiceD;
	}

	public void setChoiceD(String choiceD) {
		this.choiceD = choiceD;
	}

	@Column(name = "answer", length = 16)
	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Column(name = "skey1", length = 16)
	public String getSkey1() {
		return skey1;
	}

	public void setSkey1(String skey1) {
		this.skey1 = skey1;
	}
	
	@Override
	public String toString() {
		return "TSinglesel [id=" + id + ", TTeacher=" + TTeacher + ", tSector="
				+ tSector + ", question=" + question
				+ ", choiceA=" + choiceA + ", choiceB=" + choiceB
				+ ", choiceC=" + choiceC + ", choiceD=" + choiceD + ", answer="
				+ answer + ", skey1=" + skey1 + "]";
	}

}