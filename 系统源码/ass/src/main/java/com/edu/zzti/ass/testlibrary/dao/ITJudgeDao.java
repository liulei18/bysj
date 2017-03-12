package com.edu.zzti.ass.testlibrary.dao;

import java.io.Serializable;
import java.util.List;

import com.edu.zzti.ass.core.dao.IBaseDao;
import com.edu.zzti.ass.testlibrary.model.Judge;
import com.edu.zzti.ass.testlibrary.model.TJudge;

public interface ITJudgeDao extends IBaseDao<TJudge> {

	public Serializable addJudge(TJudge judge);
	
	public void updateLex(TJudge judge);
	
	public Long countTJudge(String key,String teacherId);

	public List<TJudge>  getListByPage(int currentPage,String key,String teacherId);

	public List<TJudge> findAllJudge();
	
	public void deleteTjudge(Integer id);
}
