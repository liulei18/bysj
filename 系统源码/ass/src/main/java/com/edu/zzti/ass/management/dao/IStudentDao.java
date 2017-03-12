package com.edu.zzti.ass.management.dao;

import java.util.List;

import com.edu.zzti.ass.core.dao.IBaseDao;
import com.edu.zzti.ass.management.model.TStudent;

public interface IStudentDao extends IBaseDao<TStudent> {

	public TStudent findStudent(TStudent student);

	public TStudent getOne(String id);

	public void updateInfo(TStudent temp);

	public List<TStudent> getall(Integer classId);
	
	public List<TStudent> find(Integer classId);
	
}
