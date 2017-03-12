package com.edu.zzti.ass.onlinestudy.dao;

import java.util.List;

import com.edu.zzti.ass.core.dao.IBaseDao;
import com.edu.zzti.ass.testlibrary.model.Judge;
import com.edu.zzti.ass.testlibrary.model.Singlesel;
import com.edu.zzti.ass.testlibrary.model.Subjective;

public interface IOnlineStudyDao extends IBaseDao {

	public List<Judge> getJudgeByUnitId(Integer unitId);
	
	public List<Singlesel> getSingleselByUnitId(Integer unitId);
	
	public List<Subjective> getSubjectiveByUnitId(Integer unitId);
	
}
