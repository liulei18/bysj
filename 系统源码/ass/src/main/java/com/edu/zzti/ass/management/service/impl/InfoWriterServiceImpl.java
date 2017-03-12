package com.edu.zzti.ass.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.zzti.ass.management.dao.IClassDao;
import com.edu.zzti.ass.management.dao.IStudentDao;
import com.edu.zzti.ass.management.dao.ITeacherDao;
import com.edu.zzti.ass.management.model.TClass;
import com.edu.zzti.ass.management.model.TStudent;
import com.edu.zzti.ass.management.model.TTeacher;
import com.edu.zzti.ass.management.service.IInfoWriteService;

@Service("infoWriteService")
public class InfoWriterServiceImpl implements IInfoWriteService {

	@Autowired
	private IStudentDao studentDao;
	@Autowired
	private ITeacherDao teacherDao;
	@Autowired
	private IClassDao classDao;

	@Override
	public void saveInfo(TClass tClass, TStudent student, TTeacher teacher) {
		tClass.setTeacher(teacher);
		TClass obj = classDao.getByName(tClass.getName());
		if (obj == null) {
			Integer classId = (Integer) classDao.save(tClass);
			tClass.setId(classId);
			student.setTClass(tClass);
		}else{
			student.setTClass(obj);
		}
		studentDao.save(student);
	}

}
