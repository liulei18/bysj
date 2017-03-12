package com.edu.zzti.ass.onlinestudy.dao;

import com.edu.zzti.ass.core.dao.IBaseDao;
import com.edu.zzti.ass.onlinestudy.model.TestAnswerInfo;

public interface ITestAnswerInfoDao  extends IBaseDao<TestAnswerInfo>{

	public  boolean findBysql(String studentId, Integer paperid);

}
