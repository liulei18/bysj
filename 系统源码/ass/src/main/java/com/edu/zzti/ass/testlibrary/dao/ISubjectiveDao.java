package com.edu.zzti.ass.testlibrary.dao;

import java.io.Serializable;
import java.util.List;

import com.edu.zzti.ass.core.dao.IBaseDao;
import com.edu.zzti.ass.testlibrary.model.Subjective;

public interface ISubjectiveDao extends IBaseDao<Subjective>{

	public Serializable addSubjective(Subjective subjective);
	
	public Long countSubjective(String key,Integer sectorId,String difficult);

	public List<Subjective>  getListByPage(int currentPage,String key,Integer sectorId,String difficult);
	

	public List<Subjective> findAllSubjective();

	public List<Subjective> getListBySecIds(List<Integer> sectorIds);
	
	public List<Subjective> getListBySecIds(List<Integer> sectorIds,String difficult);
	
	public  void updateNum(Integer id);
	
	public  void cancelNum(Integer id);
}
