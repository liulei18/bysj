package com.edu.zzti.ass.onlinestudy.service;

import java.util.List;
import java.util.Map;

import com.edu.zzti.ass.onlinestudy.model.TestAnswerInfo;
import com.edu.zzti.ass.onlinestudy.model.TestPaper;

public interface IOnlineStudyService {
	
	public Map<String, List> find(Integer unitId);

	public String getTeacherId(String id);

	public List<TestPaper> getPaperByUnit(String teacherId, Integer unitId);

	public List<TestAnswerInfo> getAnswerByUnit(String id, Integer unitId); 

}
