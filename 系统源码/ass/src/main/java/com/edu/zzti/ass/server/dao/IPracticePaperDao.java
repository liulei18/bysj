package com.edu.zzti.ass.server.dao;

import com.edu.zzti.ass.core.dao.IBaseDao;
import com.edu.zzti.ass.server.model.PracticePaper;

public interface IPracticePaperDao extends IBaseDao<PracticePaper> {

	public  void updateSql(Integer id);

	public void deleteByHql(Integer id);

}
