package com.edu.zzti.ass.testlibrary.dao;

import java.io.Serializable;
import java.util.List;

import com.edu.zzti.ass.core.dao.IBaseDao;
import com.edu.zzti.ass.server.model.Lexicon;
import com.edu.zzti.ass.testlibrary.model.Judge;
import com.edu.zzti.ass.testlibrary.model.Singlesel;

public interface IJudgeDao extends IBaseDao<Judge> {

	public Serializable addJudge(Judge judge);
	
	public Long countJudge(String key,Integer sectorId,String difficult);

	public List<Judge>  getListByPage(int currentPage,String key,Integer sectorId,String difficult);

	public List<Judge> findAllJudge();
	
	public List<Judge> getListBySecIds(List<Integer> sectorIds);
	
	public List<Judge> getListBySecIds(List<Integer> sectorIds,String difficult);
	
	public  void updateNum(Integer id);
	
	public  void cancelNum(Integer id);
}
