package com.edu.zzti.ass.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.zzti.ass.management.dao.IClassDao;
import com.edu.zzti.ass.management.dao.IStudentDao;
import com.edu.zzti.ass.management.model.TClass;
import com.edu.zzti.ass.management.model.TStudent;
import com.edu.zzti.ass.management.service.IClassService;
import com.edu.zzti.ass.management.service.IStudentService;

@Service("classService")
public class ClassServiceImpl implements IClassService{
	@Autowired
	private IClassDao classDao;

	@Override
	public List<TClass> find(String teacherId) {
		
		return classDao.find(teacherId);
	}
	
}
