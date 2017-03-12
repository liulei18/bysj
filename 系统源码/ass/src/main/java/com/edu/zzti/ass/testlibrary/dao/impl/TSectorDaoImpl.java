package com.edu.zzti.ass.testlibrary.dao.impl;


import java.util.List;






import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.zzti.ass.core.dao.impl.BaseDaoImpl;
import com.edu.zzti.ass.testlibrary.dao.ITSectorDao;
import com.edu.zzti.ass.testlibrary.dao.ITunitDao;
import com.edu.zzti.ass.testlibrary.model.TChapter;
import com.edu.zzti.ass.testlibrary.model.TSector;
import com.edu.zzti.ass.testlibrary.model.TUnit;

@Repository("sectorDao")
public class TSectorDaoImpl extends BaseDaoImpl implements
		ITSectorDao {
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
	public List<TSector> findAllSector() {
		String hql = "from TSector";
		return super.find(hql);
	}

	@Override
	public List<TSector> getSectors(Integer chapterId) {
		String sql = "SELECT *FROM t_sector where chaId = '"+chapterId+"'";
		System.out.println("dasfsdaf");
		List<TSector> list = this.findBySql(sql);
		return list;
	}

	

	
}
