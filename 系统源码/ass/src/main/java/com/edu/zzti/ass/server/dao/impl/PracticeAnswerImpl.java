package com.edu.zzti.ass.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.edu.zzti.ass.core.dao.impl.BaseDaoImpl;
import com.edu.zzti.ass.server.dao.IPracticeAnswerDao;
import com.edu.zzti.ass.server.model.PracticeAnswer;

@Repository("practiceAnswerDao")
public class PracticeAnswerImpl extends BaseDaoImpl<PracticeAnswer> implements IPracticeAnswerDao {

}
