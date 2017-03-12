package com.edu.zzti.ass.testlibrary.dao;

import java.io.Serializable;
import java.util.List;

import com.edu.zzti.ass.core.dao.IBaseDao;
import com.edu.zzti.ass.testlibrary.model.TSinglesel;

public interface ITSingleselDao extends IBaseDao<TSinglesel>{
	
	public Serializable addSinglesel(TSinglesel singlesel);
	
	public Long countTSinglesel(String key,String teacherId);

	public List<TSinglesel>  getListByPage(int currentPage,String key,String teacherId);

	public List<TSinglesel> findAllSingle();
	
	public void deleteTSinglesel(Integer id);
	
	
}
