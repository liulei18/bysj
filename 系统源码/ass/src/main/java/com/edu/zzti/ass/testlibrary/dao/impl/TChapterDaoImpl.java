package com.edu.zzti.ass.testlibrary.dao.impl;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.zzti.ass.core.dao.impl.BaseDaoImpl;
import com.edu.zzti.ass.testlibrary.dao.ITChapterDao;
import com.edu.zzti.ass.testlibrary.model.Judge;
import com.edu.zzti.ass.testlibrary.model.TChapter;

@Repository("chapterDao")
public class TChapterDaoImpl extends BaseDaoImpl implements
		ITChapterDao {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<TChapter> getChapters(Integer unitId) {
		String sql = "SELECT *FROM t_chapter where unitId = '"+unitId+"'";
		List<TChapter> list = this.findBySql(sql);
		return list;
	}
	
}
