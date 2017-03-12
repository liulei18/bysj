package com.edu.zzti.ass.server.dao;

import java.util.List;

import com.edu.zzti.ass.core.dao.IBaseDao;
import com.edu.zzti.ass.server.model.AnswerInfo;

public interface IAnswerInfoDao  extends IBaseDao<AnswerInfo>{

	public boolean findBysql(String studentId, Integer paperid);

	public List<AnswerInfo> getListByHql(String id, Integer unitId);

	public List<AnswerInfo> getAnswerlist(Integer paperid, Integer classId);

}
