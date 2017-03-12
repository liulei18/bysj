package com.edu.zzti.ass.onlinestudy.service;

import com.edu.zzti.ass.onlinestudy.model.TestAnswerInfo;
import com.edu.zzti.ass.onlinestudy.model.TestPaper;

public interface IPaperService {

	public TestPaper getPaper(Integer id);
	
	public void updateSql(Integer id);

	public boolean findStuAnswer(String studentId, Integer paperid);

	public void saveStuAnswer(TestAnswerInfo answerInfo);

	public TestAnswerInfo getAnswer(Integer answerId);

}
