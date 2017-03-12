package com.edu.zzti.ass.management.dao;


import java.io.Serializable;

import com.edu.zzti.ass.core.dao.IBaseDao;
import com.edu.zzti.ass.management.model.TTeacher;
import com.edu.zzti.ass.management.model.User;

public interface ITeacherDao extends IBaseDao<TTeacher>{
	public TTeacher findTeacher(TTeacher teacher);
	
	public Serializable save(TTeacher teacher);

	public TTeacher getOne(String id);

	public void updateInfo(TTeacher temp);

	public void updateSql(String pwd,String id);
}
