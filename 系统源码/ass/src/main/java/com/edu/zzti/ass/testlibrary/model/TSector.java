package com.edu.zzti.ass.testlibrary.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TUnit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_sector", catalog = "ass")
public class TSector implements java.io.Serializable {

	// 知识点
	/**
	 * 
	 */
	private static final long serialVersionUID = -5037694776795831613L;

	private Integer id;
	private String secName;
	private TChapter tChapter;
	private Set<TSubjective> TSubjectives = new HashSet<TSubjective>();
	private Set<TJudge> tJudges = new HashSet<TJudge>();
	private Set<TSinglesel> TSinglesels = new HashSet<TSinglesel>();
	private Set<TFill> TFills = new HashSet<TFill>();

	// Constructors

	/** default constructor */
	public TSector() {
	}

	/** minimal constructor */
	public TSector(String secName) {
		this.secName = secName;
	}

	/** full constructor */
	public TSector(String secName, TChapter tChapter,
			Set<TSubjective> TSubjectives, Set<TJudge> tJudges,
			Set<TSinglesel> TSinglesels,Set<TFill> TFills) {
		this.secName = secName;
		this.TSubjectives = TSubjectives;
		this.tJudges = tJudges;
		this.TSinglesels = TSinglesels;
		this.tChapter = tChapter;
		this.TFills = TFills;
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

	@Column(name = "secName", nullable = false, length = 32)
	public String getSecName() {
		return this.secName;
	}

	public void setSecName(String secName) {
		this.secName = secName;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chaId")
	public TChapter gettChapter() {
		return tChapter;
	}

	public void settChapter(TChapter tChapter) {
		this.tChapter = tChapter;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tSector")
	public Set<TSubjective> getTSubjectives() {
		return this.TSubjectives;
	}

	public void setTSubjectives(Set<TSubjective> TSubjectives) {
		this.TSubjectives = TSubjectives;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tSector")
	public Set<TJudge> gettJudges() {
		return this.tJudges;
	}

	public void settJudges(Set<TJudge> tJudges) {
		this.tJudges = tJudges;
	}


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tSector")
	public Set<TSinglesel> getTSinglesels() {
		return this.TSinglesels;
	}

	
	public void setTSinglesels(Set<TSinglesel> TSinglesels) {
		this.TSinglesels = TSinglesels;
	}

	
	

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tSector")
	public Set<TFill> getTFills() {
		return TFills;
	}

	public void setTFills(Set<TFill> tFills) {
		TFills = tFills;
	}

}