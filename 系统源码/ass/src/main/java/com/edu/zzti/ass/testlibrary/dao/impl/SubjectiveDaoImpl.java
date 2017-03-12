package com.edu.zzti.ass.testlibrary.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.edu.zzti.ass.core.dao.impl.BaseDaoImpl;
import com.edu.zzti.ass.testlibrary.dao.ISubjectiveDao;
import com.edu.zzti.ass.testlibrary.model.Judge;
import com.edu.zzti.ass.testlibrary.model.Subjective;
@Repository("subjectiveDao")
public class SubjectiveDaoImpl extends BaseDaoImpl<Subjective> implements
		ISubjectiveDao {

	@Override
	public Serializable addSubjective(Subjective subjective) {
		
		return super.save(subjective);
	}

	@Override
	public List<Subjective> findAllSubjective() {
		String hql ="from Subjective";
		return super.find(hql);
	}

	@Override
	public Long countSubjective(String key,Integer sectorId,String difficult) {
		String sql = "SELECT count(*) FROM t_subjective as s ";
		if ("null".equals(key)) {
			if(difficult!=null){
				sql = sql + "where s.sectorId = " + sectorId + " and s.quesDifficult="+difficult+"";
			}else{
				sql = sql + "where s.sectorId = " + sectorId + "";	
			}
		}else {
			if(difficult!=null){
				sql = sql + "where s.skey1 like '" + key + "%' and s.sectorId = " + sectorId + " and s.quesDifficult = "+difficult+"";
			}else{
				sql = sql + "where s.skey1 like '" + key + "%' and s.sectorId = " + sectorId + "";	
			}
			
		}
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		return ((BigInteger) q.uniqueResult()).longValue();
	}

	@Override
	public List<Subjective> getListByPage(int currentPage, String key,Integer sectorId,String difficult) {
		System.out.println("-------------------subjective----------------------");
		if ("null".equals(key)) {
			if(difficult==null){
				return this.find("from Subjective  where sectorId = "+sectorId+"", currentPage, 10);
			}else{
				return this.find("from Subjective  where sectorId = "+sectorId+" and quesDifficult="+difficult+"", currentPage, 10);
			}
				
		} else {
			if(difficult==null){
				return this.find("from Subjective  where sectorId = "
						+ sectorId + " and skey1 like '" + key + "%'",
						currentPage, 10);
			}else{
				return this.find("from Subjective  where sectorId = "
						+ sectorId + " and skey1 like '" + key + "%' and quesDifficult="+difficult+"",
						currentPage, 10);
			}
		}
	}

	@Override
	public List<Subjective> getListBySecIds(List<Integer> sectorIds) {
		StringBuffer buffer = new StringBuffer();
		for(Integer sectorId : sectorIds){
			buffer.append(sectorId).append(",");
		}
		buffer.deleteCharAt(buffer.length()-1);
		String hql = "FROM Subjective as t  where t.tSector.id in ( " + buffer.toString()
		+ ") ";
			List<Subjective> list = super.find(hql);
		return list;
	}
	
	@Override
	public List<Subjective> getListBySecIds(List<Integer> sectorIds,String difficult) {
		StringBuffer buffer = new StringBuffer();
		for(Integer sectorId : sectorIds){
			buffer.append(sectorId).append(",");
		}
		buffer.deleteCharAt(buffer.length()-1);
		String hql = "FROM Subjective as t  where t.tSector.id in ( " + buffer.toString()
		+ ") and t.quesDifficult="+difficult+"";
			List<Subjective> list = super.find(hql);
		return list;
	}

	@Override
	public void updateNum(Integer id) {
		String sql = "update t_subjective set useNum = useNum + 1 where id = " + id;
		super.executeSql(sql);
		
	}

	@Override
	public void cancelNum(Integer id) {
		String sql = "update t_subjective set useNum = useNum - 1 where id = " + id;
		super.executeSql(sql);
		
	}
}
