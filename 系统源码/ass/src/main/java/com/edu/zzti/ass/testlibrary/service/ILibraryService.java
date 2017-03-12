package com.edu.zzti.ass.testlibrary.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.edu.zzti.ass.onlinestudy.model.TestPaper;
import com.edu.zzti.ass.testlibrary.model.Fill;
import com.edu.zzti.ass.testlibrary.model.Judge;
import com.edu.zzti.ass.testlibrary.model.Singlesel;
import com.edu.zzti.ass.testlibrary.model.Subjective;
import com.edu.zzti.ass.testlibrary.model.TChapter;
import com.edu.zzti.ass.testlibrary.model.TSector;

public interface ILibraryService {
	
	public List<TChapter>  checkChapter(Integer unitId); 
	
	public List<TSector> checkSector(Integer chapterId); 
	
	public Judge getByJudgeId(Integer id); 
	
	public Singlesel getBySingleId(Integer id); 
	
	public Subjective getBySubjecId(Integer id); 
	
	public Fill getByFillId(Integer id);
	
	public TestPaper addTest(String paperName,HttpSession httpSession);
	

}
