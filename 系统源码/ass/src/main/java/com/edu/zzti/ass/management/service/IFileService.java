package com.edu.zzti.ass.management.service;

import java.io.Serializable;
import java.util.List;

import com.edu.zzti.ass.core.model.PageInfo;
import com.edu.zzti.ass.management.model.TFile;
import com.edu.zzti.ass.testlibrary.model.TUnit;

public interface IFileService {

	public List<TUnit> findUnit();
	
	public Serializable save(TFile file);

	public List<TFile> findByUnitId(Integer unitId,Integer type);

	public PageInfo<TFile> findByPage(Integer currentPage,Integer type);

	public void pptDelete(Integer id,String url);
	
}
