package com.edu.zzti.ass.testlibrary.service;

import java.util.List;
import java.util.Map;

import com.edu.zzti.ass.core.model.PageInfo;
import com.edu.zzti.ass.testlibrary.model.Fill;
import com.edu.zzti.ass.testlibrary.model.Judge;
import com.edu.zzti.ass.testlibrary.model.Singlesel;
import com.edu.zzti.ass.testlibrary.model.Subjective;
import com.edu.zzti.ass.testlibrary.model.TFill;
import com.edu.zzti.ass.testlibrary.model.TJudge;
import com.edu.zzti.ass.testlibrary.model.TSinglesel;
import com.edu.zzti.ass.testlibrary.model.TSubjective;


public interface ITestPaperService {
	public Map<String, List> find();
	
	public  PageInfo findAll(int currentPage,String key,int type,Integer sectorId,String difficult,String teacherId );
	
	public void addTjudge(TJudge judge);
	
	public void addJudge(Judge judge);

	public void addTSinglesel(TSinglesel singlesel);
	
	public void addSinglesel(Singlesel singlesel);
	
	public void addTSubjective(TSubjective subjective);

	public void addSubjective(Subjective subjective);
	
	public void addTFill(TFill fill);
	
	public void addFill(Fill fill);
	
	public void deleteTJudge(Integer id);
	
	public void deleteTSinglesel(Integer id);
	
	public void deleteTSubjective(Integer id);

	public void deleteTFill(Integer id);
	
	public TJudge getByIdTJudge(Integer id);
	
	public TSinglesel getByIdTSinglesel(Integer id);
	
	public TSubjective getByIdTSubjective(Integer id);
	
	public TFill getByIdTFill(Integer id);
	
	
}
