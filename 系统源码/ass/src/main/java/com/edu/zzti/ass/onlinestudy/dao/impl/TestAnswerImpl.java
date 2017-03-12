package com.edu.zzti.ass.onlinestudy.dao.impl;

import org.springframework.stereotype.Repository;

import com.edu.zzti.ass.core.dao.impl.BaseDaoImpl;
import com.edu.zzti.ass.onlinestudy.dao.ITestAnswerDao;
import com.edu.zzti.ass.onlinestudy.model.TestAnswer;

@Repository("testAnswerDao")
public class TestAnswerImpl extends BaseDaoImpl<TestAnswer> implements ITestAnswerDao {

}
