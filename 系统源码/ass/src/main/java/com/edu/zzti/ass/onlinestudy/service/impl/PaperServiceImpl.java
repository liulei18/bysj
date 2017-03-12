package com.edu.zzti.ass.onlinestudy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.zzti.ass.onlinestudy.dao.ITestAnswerInfoDao;
import com.edu.zzti.ass.onlinestudy.dao.ITestPaperDao;
import com.edu.zzti.ass.onlinestudy.model.TestAnswerInfo;
import com.edu.zzti.ass.onlinestudy.model.TestPaper;
import com.edu.zzti.ass.onlinestudy.service.IPaperService;

@Service("paperService")
public class PaperServiceImpl implements IPaperService{

	@Autowired
	private  ITestPaperDao testPaperDao;
	
	@Autowired
	private ITestAnswerInfoDao testAnswerInfoDao;
	
	@Override
	public TestPaper getPaper(Integer id) {
		
		return testPaperDao.getById(TestPaper.class, id);
	}

	@Override
	public void updateSql(Integer id) {
		testPaperDao.updateSql(id);
		
	}

	@Override
	public boolean findStuAnswer(String studentId, Integer paperid) {
		
		return testAnswerInfoDao.findBysql(studentId,paperid);
	}

	@Override
	public void saveStuAnswer(TestAnswerInfo answerInfo) {
		
		testAnswerInfoDao.save(answerInfo);
	}

	@Override
	public TestAnswerInfo getAnswer(Integer answerId) {
		
		return testAnswerInfoDao.getById(TestAnswerInfo.class, answerId);
	}

}
