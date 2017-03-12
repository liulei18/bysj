package com.edu.zzti.ass.testlibrary.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
@Entity
@Table(name="t_singlesel", catalog = "ass")
public class Singlesel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5424778774069223392L;
	
	private Integer id;
	private TSector tSector;
	private String question;
	private String skey1;
	private String choiceA;
	private String choiceB;
	private String choiceC;
	private String choiceD;
	private String answer;
	private String quesDifficult;
	private Integer score;
	private Integer useNum;
	private int flag = 0;
	
	public Singlesel() {
		super();
	}

	public Singlesel(Integer id) {
		this.id= id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sectorId")
	@JsonIgnore
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
	
	
	@Column(name = "skey1")
	public String getSkey1() {
		return skey1;
	}

	public void setSkey1(String skey1) {
		this.skey1 = skey1;
	}
	
	@Column(name = "quesDifficult")
	public String getQuesDifficult() {
		return quesDifficult;
	}

	public void setQuesDifficult(String quesDifficult) {
		this.quesDifficult = quesDifficult;
	}
	
	@Column(name = "score")
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	@Column(name = "useNum")
	public Integer getUseNum() {
		return useNum;
	}

	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}

	@Transient
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "Singlesel [id=" + id + ", tSector=" + tSector + ", question="
				+ question + ", skey1=" + skey1 + ", choiceA=" + choiceA
				+ ", choiceB=" + choiceB + ", choiceC=" + choiceC
				+ ", choiceD=" + choiceD + ", answer=" + answer
				+ ", quesDifficult=" + quesDifficult + ", score=" + score
				+ ", useNum=" + useNum + "]";
	}

	
	
}
