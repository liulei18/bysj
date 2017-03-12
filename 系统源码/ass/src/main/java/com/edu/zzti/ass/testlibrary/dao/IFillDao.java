package com.edu.zzti.ass.testlibrary.dao;

import java.io.Serializable;
import java.util.List;

import com.edu.zzti.ass.core.dao.IBaseDao;
import com.edu.zzti.ass.testlibrary.model.Fill;
public interface IFillDao extends IBaseDao<Fill> {

	public Serializable addFill(Fill fill);
	
	public Long countFill(String key,Integer sectorId,String difficult);

	public List<Fill>  getListByPage(int currentPage,String key,Integer sectorId,String difficult);

	public List<Fill> findAllFill();
	
	public List<Fill> getListBySecIds(List<Integer> sectorIds);
	
	public List<Fill> getListBySecIds(List<Integer> sectorIds,String difficult);
	
	public  void updateNum(Integer id);
	
	public  void cancelNum(Integer id);
}
