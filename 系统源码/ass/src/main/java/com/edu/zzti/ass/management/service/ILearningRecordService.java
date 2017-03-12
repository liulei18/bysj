package com.edu.zzti.ass.management.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.edu.zzti.ass.management.model.TLearningRecord;
import com.edu.zzti.ass.management.model.TRecord;



public interface ILearningRecordService {

	public Serializable save(TLearningRecord entity);
	
	public List<Map> findBySql(String sql);
	
}
