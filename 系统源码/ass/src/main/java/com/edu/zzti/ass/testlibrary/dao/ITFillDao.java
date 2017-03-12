package com.edu.zzti.ass.testlibrary.dao;

import java.io.Serializable;
import java.util.List;

import com.edu.zzti.ass.core.dao.IBaseDao;
import com.edu.zzti.ass.testlibrary.model.TFill;

public interface ITFillDao extends IBaseDao<TFill>{
	
	public Serializable addFill(TFill fill);
	
	public Long countTFill(String key,String teacherId);

	public List<TFill>  getListByPage(int currentPage,String key,String teacherId);

	public List<TFill> findAllFill();
	
	public void deleteTFill(Integer id);
	
	
}
