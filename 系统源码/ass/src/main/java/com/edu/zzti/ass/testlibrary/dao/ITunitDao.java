package com.edu.zzti.ass.testlibrary.dao;


import java.util.List;

import com.edu.zzti.ass.core.dao.IBaseDao;
import com.edu.zzti.ass.testlibrary.model.TUnit;

public interface ITunitDao  extends IBaseDao<TUnit>{
	/**
	 * 查询所有的单元和试题存入list中
	 * @return
	 */
	public List<TUnit> findAllUnit();
}
