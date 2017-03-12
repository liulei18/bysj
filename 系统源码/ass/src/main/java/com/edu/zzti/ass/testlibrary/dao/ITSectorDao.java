package com.edu.zzti.ass.testlibrary.dao;


import java.util.List;

import com.edu.zzti.ass.core.dao.IBaseDao;
import com.edu.zzti.ass.testlibrary.model.TSector;
import com.edu.zzti.ass.testlibrary.model.TUnit;

public interface ITSectorDao  extends IBaseDao{
	/**
	 * 查询所有的单元和试题存入list中
	 * @return
	 */
	public List<TSector> findAllSector();

	public List<TSector> getSectors(Integer chapterId);
}
