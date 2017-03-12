package com.edu.zzti.ass.management.dao;

import java.util.List;

import com.edu.zzti.ass.core.dao.IBaseDao;
import com.edu.zzti.ass.management.model.TFile;
import com.edu.zzti.ass.management.model.TFileShow;

public interface IFileShowDao extends IBaseDao<TFileShow>{

	public List<TFileShow> findByFKey(Integer fKey,Integer type);

	public List<TFileShow> findFiles();

	public Long countFile(Integer type);

	public List<TFileShow> getListByPage(Integer currentPage,Integer type);

}
