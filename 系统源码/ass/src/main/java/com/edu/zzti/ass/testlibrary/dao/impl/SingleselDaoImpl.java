package com.edu.zzti.ass.testlibrary.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.edu.zzti.ass.core.dao.impl.BaseDaoImpl;
import com.edu.zzti.ass.testlibrary.dao.ISingleselDao;
import com.edu.zzti.ass.testlibrary.model.Singlesel;

@Repository("singleselDao")
public class SingleselDaoImpl extends BaseDaoImpl<Singlesel> implements ISingleselDao {

	@Override
	public Serializable addSinglesel(Singlesel singlesel) {

		return super.save(singlesel);

	}

	@Override
	public List<Singlesel> findAllSingle() {
		String hql = "from TSinglesel";
		return super.find(hql);
	}

	@Override
	public Long countSinglesel(String key, Integer sectorId,String difficult) {
		String sql = "SELECT count(*) FROM t_singlesel as s ";
		
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
	public List<Singlesel> getListByPage(int currentPage, String key,
			Integer sectorId,String difficult) {
		System.out.println("-------------------single----------------------");
		if ("null".equals(key)) {
			if(difficult==null){
				return this.find("from Singlesel  where sectorId = "+sectorId+"", currentPage, 10);
			}else{
				return this.find("from Singlesel  where sectorId = "+sectorId+" and quesDifficult="+difficult+"", currentPage, 10);
			}
				
		} else {
			if(difficult==null){
				return this.find("from Singlesel  where sectorId = "
						+ sectorId + " and skey1 like '" + key + "%'",
						currentPage, 10);
			}else{
				return this.find("from Singlesel  where sectorId = "
						+ sectorId + " and skey1 like '" + key + "%' and quesDifficult="+difficult+"",
						currentPage, 10);
			}
		}
	}



	@Override
	public List<Singlesel> getListBySecIds(List<Integer> sectorIds) {
		StringBuffer buffer = new StringBuffer();
		for(Integer sectorId : sectorIds){
			buffer.append(sectorId).append(",");
			
		}
		buffer.deleteCharAt(buffer.length()-1);
		String hql = "FROM Singlesel as t  where t.tSector.id in ( " + buffer.toString()
		+ ") ";
			List<Singlesel> list = super.find(hql);
		return list;
	}
	@Override
	public List<Singlesel> getListBySecIds(List<Integer> sectorIds,String difficult) {
		StringBuffer buffer = new StringBuffer();
		for(Integer sectorId : sectorIds){
			buffer.append(sectorId).append(",");
			
		}
		buffer.deleteCharAt(buffer.length()-1);
		String hql = "FROM Singlesel as t  where t.tSector.id in ( " + buffer.toString()
		+ ")and t.quesDifficult="+difficult+"";
			List<Singlesel> list = super.find(hql);
		return list;
	}

	@Override
	public void updateNum(Integer id) {
		String sql = "update t_singlesel set useNum = useNum + 1 where id = " + id;
		super.executeSql(sql);
		
	}

	@Override
	public void cancelNum(Integer id) {
		String sql = "update t_singlesel set useNum = useNum - 1 where id = " + id;
		super.executeSql(sql);
		
	}

}
