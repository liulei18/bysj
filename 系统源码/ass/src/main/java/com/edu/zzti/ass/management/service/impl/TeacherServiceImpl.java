package com.edu.zzti.ass.management.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.zzti.ass.management.dao.ITeacherDao;
import com.edu.zzti.ass.management.model.TTeacher;
import com.edu.zzti.ass.management.service.ITeacherService;

@Service("teacherService")
public class TeacherServiceImpl implements ITeacherService{
	@Autowired
	private ITeacherDao teacherDao;
	
	@Override
	public TTeacher findTeacher(TTeacher teacher) {
		return teacherDao.findTeacher(teacher);
	}

	@Override
	public Serializable save(TTeacher teacher) {
		return teacherDao.save(teacher);
	}

	@Override
	public void updateSql(String pwd,String id) {
		teacherDao.updateSql(pwd,id);
		
	}

	@Override
	public TTeacher findTeacherByName(String userName) {
		
		String Hql = "from TTeacher  where tname = '"+userName+"'";
		
		return teacherDao.getByHql(Hql);
	}

}
