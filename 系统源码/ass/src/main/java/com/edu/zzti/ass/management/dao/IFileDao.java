package com.edu.zzti.ass.management.dao;

import java.util.List;

import com.edu.zzti.ass.core.dao.IBaseDao;
import com.edu.zzti.ass.management.model.TFile;

public interface IFileDao extends IBaseDao<TFile>{

	public List<TFile> findByUnitId(Integer unitId,Integer type);

	public List<TFile> findFiles();

	public Long countFile(Integer type);

	public List<TFile> getListByPage(Integer currentPage,Integer type);

}
