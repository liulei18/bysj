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
@Table(name = "t_chapter", catalog = "ass")
public class TChapter implements java.io.Serializable {

	// 章
	/**
	 * 
	 */
	private static final long serialVersionUID = -5037694776795831613L;
	private Integer id;
	private String chaName;
	private TUnit tUnit;
	private Set<TSector> tSectors = new HashSet<TSector>();
	

	// Constructors

	/** default constructor */
	public TChapter() {
	}

	/** minimal constructor */
	public TChapter(String chaName) {
		this.chaName = chaName;
	}

	/** full constructor */
	public TChapter(String chaName, Set<TSector> tSectors,TUnit tUnit) {
		this.chaName = chaName;
		this.tSectors = tSectors;
		this.tUnit = tUnit;
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

	@Column(name = "chaName", nullable = false, length = 32)
	public String getChaName() {
		return this.chaName;
	}

	public void setChaName(String chaName) {
		this.chaName = chaName;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tChapter")
	public Set<TSector> gettSectors() {
		return tSectors;
	}

	public void settSectors(Set<TSector> tSectors) {
		this.tSectors = tSectors;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unitId")
	public TUnit gettUnit() {
		return tUnit;
	}

	public void settUnit(TUnit tUnit) {
		this.tUnit = tUnit;
	}
}