package com.edu.zzti.ass.testlibrary.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.edu.zzti.ass.core.dao.impl.BaseDaoImpl;
import com.edu.zzti.ass.testlibrary.dao.IJudgeDao;
import com.edu.zzti.ass.testlibrary.model.Judge;
import com.edu.zzti.ass.testlibrary.model.Singlesel;
@Repository("judgeDao")
public class JudgeDaoImpl extends BaseDaoImpl<Judge> implements IJudgeDao {
	@Override
	public Serializable addJudge(Judge judge) {
		return super.save(judge);

	}

	@Override
	public List<Judge> findAllJudge() {
		String hql = "from Judge";
		return super.find(hql);
	}

	@Override
	public Long countJudge(String key, Integer sectorId,String difficult) {
		String sql = "SELECT count(*) FROM t_judge as l ";
		if ("null".equals(key)) {
			if(difficult!=null){
				sql = sql + "where l.sectorId = " + sectorId + " and l.quesDifficult="+difficult+"";
			}else{
				sql = sql + "where l.sectorId = " + sectorId + "";	
			}
		}else {
			if(difficult!=null){
				sql = sql + "where l.jkey1 like '" + key + "%' and l.sectorId = " + sectorId + " and l.quesDifficult = "+difficult+"";
			}else{
				sql = sql + "where l.jkey1 like '" + key + "%' and l.sectorId = " + sectorId + "";	
			}
			
		}
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		return ((BigInteger) q.uniqueResult()).longValue();
	}

	@Override
	public List<Judge> getListByPage(int currentPage, String key,
			Integer sectorId,String difficult) {
		if ("null".equals(key)) {
			if(difficult==null){
				return this.find("from Judge  where sectorId = "+sectorId+"", currentPage, 10);
			}else{
				return this.find("from Judge  where sectorId = "+sectorId+" and quesDifficult="+difficult+"", currentPage, 10);
			}
				
		} else {
			if(difficult==null){
				return this.find("from Judge  where sectorId = "
						+ sectorId + " and jkey1 like '" + key + "%'",
						currentPage, 10);
			}else{
				return this.find("from Judge  where sectorId = "
						+ sectorId + " and jkey1 like '" + key + "%' and quesDifficult="+difficult+"",
						currentPage, 10);
			}
				
		}
	}

	

	@Override
	public List<Judge> getListBySecIds(List<Integer> sectorIds) {
		StringBuffer buffer = new StringBuffer();
		for(Integer sectorId : sectorIds){
			buffer.append(sectorId).append(",");
			
		}
		buffer.deleteCharAt(buffer.length()-1);
		String hql = "FROM Judge as t  where t.tSector.id in ( " + buffer.toString()
		+ ") ";
			List<Judge> list = super.find(hql);
		return list;
	}
	
	@Override
	public List<Judge> getListBySecIds(List<Integer> sectorIds,String difficult) {
		StringBuffer buffer = new StringBuffer();
		for(Integer sectorId : sectorIds){
			buffer.append(sectorId).append(",");
			
		}
		buffer.deleteCharAt(buffer.length()-1);
			String hql = "FROM Judge as t  where t.tSector.id in ( " + buffer.toString()
					+ ") and t.quesDifficult="+difficult+"";
		
			List<Judge> list = super.find(hql);
		return list;
	}

	@Override
	public void updateNum(Integer id) {
		String sql = "update t_judge set useNum = useNum + 1 where id = " + id;
		super.executeSql(sql);
		
	}

	@Override
	public void cancelNum(Integer id) {
		String sql = "update t_judge set useNum = useNum - 1 where id = " + id;
		super.executeSql(sql);
	}


}
