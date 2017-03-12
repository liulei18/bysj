package com.edu.zzti.ass.onlinestudy.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.edu.zzti.ass.core.dao.impl.BaseDaoImpl;
import com.edu.zzti.ass.onlinestudy.dao.ITestPaperDao;
import com.edu.zzti.ass.onlinestudy.model.TestPaper;

@Repository("testPaperDao")
public class TestPaperImpl extends BaseDaoImpl<TestPaper> implements
		ITestPaperDao {

	@Override
	public void updateSql(Integer id) {
		String sql = "update t_testpaper set isRelease = 1 where id = " + id;
		super.executeSql(sql);
	}

	@Override
	public void deleteByHql(Integer id) {
		String sql = "delete from t_page_judge  where paperId = " + id;
		super.executeSql(sql);
		String sql1 = "delete from t_page_single where paperId = " + id;
		super.executeSql(sql1);
		String sql2 = "delete from t_page_subj  where paperId = " + id;
		super.executeSql(sql2);
		TestPaper paper = super.getById(TestPaper.class, id);
		int answerid = paper.getTestAnswer().getId();
		String sql4 = "delete from t_testpaper where id = " + id;
		super.executeSql(sql4);
		String sql5 = "delete from t_testanswer  where id = " + answerid;
		super.executeSql(sql5);

	}

	@Override
	public Long countTestPaper() {
		String sql = "SELECT count(*) FROM t_testpaper where isRelease = 1";

		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		return ((BigInteger) q.uniqueResult()).longValue();
	}

	@Override
	public List<TestPaper> getListByPage(int currentPage) {

		return this.find("from TestPaper where isRelease = 1 ",
				currentPage, 10);

	}


	@Override
	public Long countNoRelease(String teacherId) {
		String sql = "SELECT count(*) FROM t_testpaper where isRelease = 0 and teacherId='"+teacherId+"'";

		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		return ((BigInteger) q.uniqueResult()).longValue();
	}

	@Override
	public List<TestPaper> getNoReleaseByPage(int currentPage,String teacherId) {
		return this.find("from TestPaper where isRelease = 0 and teacherId='"+teacherId+"'",
				currentPage, 10);
	}

}
