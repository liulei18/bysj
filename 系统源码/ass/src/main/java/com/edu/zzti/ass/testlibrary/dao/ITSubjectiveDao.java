package com.edu.zzti.ass.testlibrary.dao;

import java.io.Serializable;
import java.util.List;

import com.edu.zzti.ass.core.dao.IBaseDao;
import com.edu.zzti.ass.testlibrary.model.TSubjective;

public interface ITSubjectiveDao extends IBaseDao<TSubjective>{

	public Serializable addSubjective(TSubjective subjective);
	
	public Long countTSubjective(String key,String teacherId);

	public List<TSubjective>  getListByPage(int currentPage,String key,String teacherId);

	public List<TSubjective> findAllSubjective();
	
	public void deleteTSubjective(Integer id);
}
