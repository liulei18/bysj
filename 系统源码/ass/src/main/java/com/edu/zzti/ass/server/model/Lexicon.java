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
@Table(name = "t_lexicon")
public class Lexicon implements Serializable {

	private static final long serialVersionUID = -7294716898609924656L;

	private Integer id;
	private String word;
	@Lob
	private String enMean;
	@Lob
	private String zhMean;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "word", length = 64,unique=true)
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Column(name = "en_mean", length = 16777216)
	public String getEnMean() {
		return enMean;
	}

	public void setEnMean(String enMean) {
		this.enMean = enMean;
	}

	@Column(name = "zh_mean", length = 16777216)
	public String getZhMean() {
		return zhMean;
	}

	public void setZhMean(String zhMean) {
		this.zhMean = zhMean;
	}

}
