package com.edu.zzti.ass.testlibrary.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.edu.zzti.ass.onlinestudy.model.TestPaper;
import com.edu.zzti.ass.server.model.PracticePaper;
import com.edu.zzti.ass.server.model.Unit;

public interface IAutoTestService {
	
	public TestPaper getPaper(Integer id);

	public void delTest(Integer testId);

	public void del(Integer id);

	public TestPaper addTest(String paperName, Integer jEasy, Integer jMid,
			Integer jDif, Integer siEasy, Integer siMid, Integer siDif,
			Integer suEasy, Integer suMid, Integer suDif,Integer fEasy, Integer fMid,
			Integer fDif,
			List<Integer> chapterIdList,HttpSession httpSession);
	
}
