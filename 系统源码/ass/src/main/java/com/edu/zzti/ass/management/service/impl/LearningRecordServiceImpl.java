package com.edu.zzti.ass.management.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.zzti.ass.management.dao.ILearningRecordDao;
import com.edu.zzti.ass.management.dao.IRecordDao;
import com.edu.zzti.ass.management.model.TLearningRecord;
import com.edu.zzti.ass.management.model.TRecord;
import com.edu.zzti.ass.management.service.ILearningRecordService;
import com.edu.zzti.ass.management.service.IRecordService;

@Service("learngRecordService")
public class LearningRecordServiceImpl implements ILearningRecordService{
	@Autowired
	private ILearningRecordDao learningRecordDao;

	@Override
	public Serializable save(TLearningRecord entity) {
		return learningRecordDao.save(entity);
	}

	@Override
	public List<Map> findBySql(String sql) {
		return learningRecordDao.findBySql(sql);
	}

	
	
	
}
