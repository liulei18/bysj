package com.edu.zzti.ass.testlibrary.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.edu.zzti.ass.core.dao.impl.BaseDaoImpl;
import com.edu.zzti.ass.testlibrary.dao.IFillDao;
import com.edu.zzti.ass.testlibrary.model.Fill;
@Repository("fillDao")
public class FillDaoImpl extends BaseDaoImpl<Fill> implements IFillDao {
	@Override
	public Serializable addFill(Fill fill) {
		return super.save(fill);

	}

	@Override
	public List<Fill> findAllFill() {
		String hql = "from Fill";
		return super.find(hql);
	}

	@Override
	public Long countFill(String key, Integer sectorId,String difficult) {
		String sql = "SELECT count(*) FROM t_fill as l ";
		if ("null".equals(key)) {
			if(difficult!=null){
				sql = sql + "where l.sectorId = " + sectorId + " and l.quesDifficult="+difficult+"";
			}else{
				sql = sql + "where l.sectorId = " + sectorId + "";	
			}
		}else {
			if(difficult!=null){
				sql = sql + "where l.fkey like '" + key + "%' and l.sectorId = " + sectorId + " and l.quesDifficult = "+difficult+"";
			}else{
				sql = sql + "where l.fkey like '" + key + "%' and l.sectorId = " + sectorId + "";	
			}
			
		}
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		return ((BigInteger) q.uniqueResult()).longValue();
	}

	@Override
	public List<Fill> getListByPage(int currentPage, String key,
			Integer sectorId,String difficult) {
		if ("null".equals(key)) {
			if(difficult==null){
				return this.find("from Fill  where sectorId = "+sectorId+"", currentPage, 10);
			}else{
				return this.find("from Fill  where sectorId = "+sectorId+" and quesDifficult="+difficult+"", currentPage, 10);
			}
				
		} else {
			if(difficult==null){
				return this.find("from Fill  where sectorId = "
						+ sectorId + " and fkey like '" + key + "%'",
						currentPage, 10);
			}else{
				return this.find("from Fill  where sectorId = "
						+ sectorId + " and fkey like '" + key + "%' and quesDifficult="+difficult+"",
						currentPage, 10);
			}
				
		}
	}

	

	@Override
	public List<Fill> getListBySecIds(List<Integer> sectorIds) {
		StringBuffer buffer = new StringBuffer();
		for(Integer sectorId : sectorIds){
			buffer.append(sectorId).append(",");
			
		}
		buffer.deleteCharAt(buffer.length()-1);
		String hql = "FROM Fill as t  where t.tSector.id in ( " + buffer.toString()
		+ ") ";
			List<Fill> list = super.find(hql);
		return list;
	}
	
	@Override
	public List<Fill> getListBySecIds(List<Integer> sectorIds,String difficult) {
		StringBuffer buffer = new StringBuffer();
		for(Integer sectorId : sectorIds){
			buffer.append(sectorId).append(",");
			
		}
		buffer.deleteCharAt(buffer.length()-1);
			String hql = "FROM Fill as t  where t.tSector.id in ( " + buffer.toString()
					+ ") and t.quesDifficult="+difficult+"";
		
			List<Fill> list = super.find(hql);
		return list;
	}

	@Override
	public void updateNum(Integer id) {
		String sql = "update t_fill set useNum = useNum + 1 where id = " + id;
		super.executeSql(sql);
		
	}

	@Override
	public void cancelNum(Integer id) {
		String sql = "update t_fill set useNum = useNum - 1 where id = " + id;
		super.executeSql(sql);
	}


}
