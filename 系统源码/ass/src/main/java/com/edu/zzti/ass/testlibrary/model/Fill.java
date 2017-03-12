package com.edu.zzti.ass.testlibrary.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
@Table(name = "t_fill", catalog = "ass")
public class Fill implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 256777469176731168L;
	private Integer id;
	private TSector tSector;
	private String question;
	private Integer answer;
	private String fkey;
	private String quesDifficult;
	private Integer score;
	private Integer useNum;
	private int flag = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY )
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

	@Column(name = "answer")
	public Integer getAnswer() {
		return answer;
	}

	public void setAnswer(Integer answer) {
		this.answer = answer;
	}

	@Column(name = "fkey")
	public String getFkey() {
		return fkey;
	}

	public void setFkey(String fkey) {
		this.fkey = fkey;
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
		return "Judge [id=" + id + ", tSector=" + tSector + ", question="
				+ question + ", answer=" + answer + ", fkey=" + fkey
				+ ", quesDifficult=" + quesDifficult + ", score=" + score
				+ ", useNum=" + useNum + "]";
	}

	

}
