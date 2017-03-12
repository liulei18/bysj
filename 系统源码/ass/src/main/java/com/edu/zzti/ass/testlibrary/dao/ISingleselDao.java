package com.edu.zzti.ass.testlibrary.dao;

import java.io.Serializable;
import java.util.List;

import com.edu.zzti.ass.core.dao.IBaseDao;
import com.edu.zzti.ass.testlibrary.model.Singlesel;

public interface ISingleselDao extends IBaseDao<Singlesel>{
	
	public Serializable addSinglesel(Singlesel singlesel);
	
	public Long countSinglesel(String key,Integer sectorId,String difficult);

	public List<Singlesel>  getListByPage(int currentPage,String key,Integer sectorId,String  difficult);

	public List<Singlesel> findAllSingle();

	public List<Singlesel> getListBySecIds(List<Integer> sectorIds);
	
	public List<Singlesel> getListBySecIds(List<Integer> sectorIds,String difficult);
	
	public  void updateNum(Integer id);
	
	public  void cancelNum(Integer id);
}
