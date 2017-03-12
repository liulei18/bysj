package com.edu.zzti.ass.onlinestudy.service;

import java.util.List;

import com.edu.zzti.ass.management.model.TFile;
import com.edu.zzti.ass.management.model.TFileShow;
import com.edu.zzti.ass.testlibrary.model.TUnit;

public interface ILearningService {

	public 	List<TUnit> getAllUnits();

	public TFile getFile(Integer id, int i);
	
	
	public TFileShow getFileShow(Integer id, int i);

	public TUnit getunit(Integer id);

}
