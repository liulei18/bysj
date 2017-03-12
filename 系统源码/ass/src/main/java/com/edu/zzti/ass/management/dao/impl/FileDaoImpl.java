package com.edu.zzti.ass.management.dao.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.edu.zzti.ass.core.dao.impl.BaseDaoImpl;
import com.edu.zzti.ass.management.dao.IFileDao;
import com.edu.zzti.ass.management.model.TFile;


@Repository("fileDao")
public class FileDaoImpl extends BaseDaoImpl<TFile> implements IFileDao {

	@Override
	public List<TFile> findByUnitId(Integer unitId,Integer type) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("unitId", unitId);
		params.put("ftype", type);
		String hql="from TFile as f where  f.unit.id = :unitId and  f.ftype =:ftype" ;
		return this.find(hql, params);
	}

	@Override
	public List<TFile> findFiles() {
		String hql="from TFile ";
		return this.find(hql);
	}

	@Override
	public Long countFile(Integer type) {
		String sql ="SELECT count(*) FROM t_file where  ftype="+type;

		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		return  ((BigInteger)q.uniqueResult()).longValue();
	}

	@Override
	public List<TFile> getListByPage(Integer currentPage,Integer type) {
		
		return this.find("from TFile as f where  f.ftype ="+type, currentPage, 10);
	}

	

}
