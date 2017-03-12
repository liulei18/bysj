package com.edu.zzti.ass.onlinestudy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.zzti.ass.management.dao.IFileDao;
import com.edu.zzti.ass.management.dao.IFileShowDao;
import com.edu.zzti.ass.management.model.TFile;
import com.edu.zzti.ass.management.model.TFileShow;
import com.edu.zzti.ass.onlinestudy.service.ILearningService;
import com.edu.zzti.ass.testlibrary.dao.ITunitDao;
import com.edu.zzti.ass.testlibrary.model.TUnit;

@Service("learningService")
public class LearningServiceImpl implements ILearningService {

	@Autowired
	private ITunitDao unitDao;
	
	
	@Autowired
	private IFileDao fileDao;
	
	@Autowired
	private IFileShowDao fileShowDao;
	
	@Override
	public List<TUnit> getAllUnits() {
		
		return unitDao.findAllUnit();
	}

	@Override
	public TFile getFile(Integer id, int i) {
		String hql = "from TFile as t where t.unit.id = "+id+" and  t.ftype ="+i;
		return fileDao.getByHql(hql);
	}

	
	@Override
	public TFileShow getFileShow(Integer id, int i) {
		String hql = "from TFileShow as t where t.fkey = "+id+" and  t.ftype ="+i;
		return fileShowDao.getByHql(hql);
	}
	
	@Override
	public TUnit getunit(Integer id) {
		
		return unitDao.getById(TUnit.class, id);
	}

}
