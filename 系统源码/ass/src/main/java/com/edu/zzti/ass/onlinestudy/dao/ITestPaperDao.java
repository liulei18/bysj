package com.edu.zzti.ass.onlinestudy.dao;

import java.util.List;

import com.edu.zzti.ass.core.dao.IBaseDao;
import com.edu.zzti.ass.onlinestudy.model.TestPaper;
import com.edu.zzti.ass.testlibrary.model.Judge;

public interface ITestPaperDao extends IBaseDao<TestPaper> {

	public  void updateSql(Integer id);

	public void deleteByHql(Integer id);
	

	public Long countTestPaper();
	
	public Long countNoRelease(String teacherId);

	public List<TestPaper>  getListByPage(int currentPage);
	
	public List<TestPaper>  getNoReleaseByPage(int currentPage,String teacherId);

}
