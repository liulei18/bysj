package com.edu.zzti.ass.management.service;

import java.util.List;

import com.edu.zzti.ass.management.model.TStudent;


public interface IStudentService {

	public TStudent findStudent(TStudent student);

	public List<TStudent> getall(Integer classId);
	
	public List<TStudent> find(Integer classId);
	
}
