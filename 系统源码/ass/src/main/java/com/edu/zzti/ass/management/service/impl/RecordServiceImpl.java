package com.edu.zzti.ass.management.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.zzti.ass.management.dao.IRecordDao;
import com.edu.zzti.ass.management.model.TRecord;
import com.edu.zzti.ass.management.service.IRecordService;

@Service("recordService")
public class RecordServiceImpl implements IRecordService{
	@Autowired
	private IRecordDao recordDao;

	@Override
	public Serializable save(TRecord entity) {
		return recordDao.save(entity);
	}

	@Override
	public List<Map> findBySql(String sql) {
		return recordDao.findBySql(sql);
	}
	
	
	
}
