package com.edu.zzti.ass.onlinestudy.dao.impl;

import java.math.BigInteger;
import java.util.List;

import javax.management.Query;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.edu.zzti.ass.core.dao.impl.BaseDaoImpl;
import com.edu.zzti.ass.onlinestudy.dao.IOnlineStudyDao;
import com.edu.zzti.ass.testlibrary.model.Judge;
import com.edu.zzti.ass.testlibrary.model.Singlesel;
import com.edu.zzti.ass.testlibrary.model.Subjective;


@Repository("onlineStudyDao")
public class OnlineStudyDaoImpl extends BaseDaoImpl implements IOnlineStudyDao {

	@Override
	public List<Judge> getJudgeByUnitId(Integer unitId) {
		String sql = "SELECT *FROM t_judge where sectorId = '"+unitId+"' order by id  desc limit 5";
		
		 List<Judge> list= this.findBySql(sql);
		 System.out.println(list);
		 return list;
	}

	@Override
	public List<Singlesel> getSingleselByUnitId(Integer unitId) {
		String sql = "SELECT *FROM t_singlesel where sectorId = '"+unitId+"' order by id  desc limit 5";
		
		 List<Singlesel> list2= this.findBySql(sql);
		 System.out.println(list2);
		 return list2;

	}

	@Override
	public List<Subjective> getSubjectiveByUnitId(Integer unitId) {
		String sql = "SELECT *FROM t_subjective where sectorId = '"+unitId+"' order by id  desc limit 2";
		
		 List<Subjective> list3= this.findBySql(sql);
		 System.out.println(list3);
		 return list3;
	}

}
