package com.edu.zzti.ass.server.model;

import java.util.HashSet;
import java.util.Set;

import com.edu.zzti.ass.testlibrary.model.TChapter;

public class Unit {

	private Integer id;
	private String unitName;
    private Set<Chapter> chapters;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Set<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(Set<Chapter> chapters) {
		this.chapters = chapters;
	}


	
	
}
