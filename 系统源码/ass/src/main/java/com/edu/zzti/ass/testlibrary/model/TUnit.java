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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.edu.zzti.ass.onlinestudy.model.TestPaper;
import com.edu.zzti.ass.server.model.PracticePaper;

/**
 * TUnit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_unit", catalog = "ass")
public class TUnit implements java.io.Serializable {

	private static final long serialVersionUID = -5037694776795831613L;
	private Integer id;
	private String unitName;
	private Set<TChapter> tChapters = new HashSet<TChapter>();
	private Set<TestPaper> Testpapers = new HashSet<TestPaper>();
	private Set<PracticePaper> practicePapers = new HashSet<PracticePaper>();
	public TUnit() {
	}

	public TUnit(Integer id) {
		this.id=id;
	}
	

	public TUnit(String unitName) {
		this.unitName = unitName;
	}


	public TUnit(String unitName, Set<TChapter> tChapters) {
		this.unitName = unitName;
		this.tChapters = tChapters;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "unitName", nullable = false, length = 32)
	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tUnit")
	public Set<TChapter> gettChapters() {
		return tChapters;
	}

	public void settChapters(Set<TChapter> tChapters) {
		this.tChapters = tChapters;
	}

	@OneToMany(mappedBy="unit")
	public Set<TestPaper> getTestpapers() {
		return Testpapers;
	}

	public void setTestpapers(Set<TestPaper> testpapers) {
		Testpapers = testpapers;
	}
	@OneToMany(mappedBy="unit")
	public Set<PracticePaper> getPracticePapers() {
		return practicePapers;
	}

	public void setPracticePapers(Set<PracticePaper> practicePapers) {
		this.practicePapers = practicePapers;
	}

	@Override
	public String toString() {
		return "TUnit [id=" + id + ", unitName=" + unitName + ", tChapters="
				+ tChapters + ", Testpapers=" + Testpapers
				+ ", practicePapers=" + practicePapers + "]";
	}

	
	
}