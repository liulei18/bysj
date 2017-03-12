package com.edu.zzti.ass.management.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edu.zzti.ass.core.dao.impl.BaseDaoImpl;
import com.edu.zzti.ass.management.dao.IClassDao;
import com.edu.zzti.ass.management.model.TClass;



@Repository("classDao")
public class ClassDaoImpl extends BaseDaoImpl<TClass> implements IClassDao{

	@Override
	public TClass getByName(String name) {
		
		return this.getByHql("from TClass where name ='"+name+"'");
	}
	@Override
	public List<TClass> find(String teacherId){
		String hql ="from TClass where teacher_id = '"+teacherId+"'";
		return super.find(hql);
		
	}
	
	
}
