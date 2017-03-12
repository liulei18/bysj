package com.edu.zzti.ass.management.dao.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.edu.zzti.ass.core.dao.impl.BaseDaoImpl;
import com.edu.zzti.ass.management.dao.IFileDao;
import com.edu.zzti.ass.management.dao.IFileShowDao;
import com.edu.zzti.ass.management.model.TFile;
import com.edu.zzti.ass.management.model.TFileShow;


@Repository("fileShowDao")
public class FileShowDaoImpl extends BaseDaoImpl<TFileShow> implements IFileShowDao {

	@Override
	public List<TFileShow> findByFKey(Integer fkey,Integer type) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("fkey", fkey);
		params.put("ftype", type);
		String hql="from TFileShow as f where f.fkey = :fkey and  f.ftype =:ftype" ;
		return this.find(hql, params);
	}

	@Override
	public List<TFileShow> findFiles() {
		String hql="from TFile ";
		return this.find(hql);
	}

	@Override
	public Long countFile(Integer type) {
		String sql ="SELECT count(*) FROM t_file_show where  ftype="+type;

		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		return  ((BigInteger)q.uniqueResult()).longValue();
	}

	@Override
	public List<TFileShow> getListByPage(Integer currentPage,Integer type) {
		
		return this.find("from TFileShow as f where  f.ftype ="+type, currentPage, 10);
	}

	

}
