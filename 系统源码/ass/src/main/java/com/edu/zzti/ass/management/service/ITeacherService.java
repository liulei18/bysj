package com.edu.zzti.ass.management.service;
import java.io.Serializable;

import com.edu.zzti.ass.management.model.TTeacher;

public interface ITeacherService {
	
	public TTeacher findTeacher(TTeacher teacher);
	
	public Serializable save(TTeacher teacher);
	
	public void updateSql(String pwd,String id);

	public TTeacher findTeacherByName(String userName);
	
}
