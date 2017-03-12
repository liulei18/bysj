package com.edu.zzti.ass.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.zzti.ass.management.dao.IStudentDao;
import com.edu.zzti.ass.management.model.TStudent;
import com.edu.zzti.ass.management.service.IStudentService;

@Service("studentService")
public class StudentServiceImpl implements IStudentService{
	@Autowired
	private IStudentDao studentDao;
	@Override
	public TStudent findStudent(TStudent student) {
		
		return studentDao.findStudent(student);
	}

	@Override
	public List<TStudent> getall(Integer classId) {
		
		return studentDao.getall(classId);
	}

	@Override
	public List<TStudent> find(Integer classId) {
		
		return studentDao.find(classId);
	}

	
	
	
}
