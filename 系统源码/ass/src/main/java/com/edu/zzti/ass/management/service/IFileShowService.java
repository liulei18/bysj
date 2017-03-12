package com.edu.zzti.ass.management.service;

import java.io.Serializable;
import java.util.List;

import com.edu.zzti.ass.core.model.PageInfo;
import com.edu.zzti.ass.management.model.TFile;
import com.edu.zzti.ass.management.model.TFileShow;

public interface IFileShowService {

	
	public Serializable save(TFileShow file);


	public PageInfo<TFileShow> findByPage(Integer currentPage,Integer type);

	public void targetDelete(Integer id,String url);
	
	public List<TFileShow> findByFKey(Integer fkey,Integer type);
	
}
