package com.edu.zzti.ass.testlibrary.model;

import java.io.Serializable;

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
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.metamodel.source.annotations.attribute.type.LobTypeResolver;
@Entity
@Table(name="t_subjective", catalog = "ass")
public class Subjective implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1246812002745107234L;

	private Integer id;
	private TSector tSector;
	private String question;
	@Lob
	private String answer;
	private String skey1;
	private String quesDifficult;
	private Integer score;
	private Integer useNum;
	private int flag=0;
	public Subjective() {
		super();
	}
	public Subjective(Integer id) {
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
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	@Column(name = "answer" ,length=16777216)
	public String getAnswer() {
		return answer;
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
		return "Subjective [id=" + id + ", tSector=" + tSector + ", question="
				+ question + ", answer=" + answer + ", skey1=" + skey1
				+ ", quesDifficult=" + quesDifficult + ", score=" + score
				+ ", useNum=" + useNum + "]";
	}
	
	
	
}
