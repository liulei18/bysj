package com.edu.zzti.ass.management.dao;

import java.util.List;

import com.edu.zzti.ass.core.dao.IBaseDao;
import com.edu.zzti.ass.management.model.TClass;

public interface IClassDao extends IBaseDao<TClass>{

	public  TClass getByName(String name);
	
	public List<TClass> find(String teacherId);
}
