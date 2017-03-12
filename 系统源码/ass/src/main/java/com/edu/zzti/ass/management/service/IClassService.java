package com.edu.zzti.ass.management.service;

import java.util.List;

import com.edu.zzti.ass.management.model.TClass;
import com.edu.zzti.ass.management.model.TStudent;


public interface IClassService {
	
	public List<TClass> find(String teacherId);
	
}
