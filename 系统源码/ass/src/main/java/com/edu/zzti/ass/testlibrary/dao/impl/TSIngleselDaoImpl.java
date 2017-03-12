package com.edu.zzti.ass.testlibrary.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.edu.zzti.ass.core.dao.impl.BaseDaoImpl;
import com.edu.zzti.ass.testlibrary.dao.ITSingleselDao;
import com.edu.zzti.ass.testlibrary.model.TSinglesel;

@Repository("tSingleselDao")
public class TSIngleselDaoImpl extends BaseDaoImpl<TSinglesel>  implements ITSingleselDao {

	@Override
	public Serializable addSinglesel(TSinglesel singlesel) {
		
		return super.save(singlesel);
		
	}

	@Override
	public List<TSinglesel> findAllSingle() {
		String hql ="from TSinglesel";
		return super.find(hql);
	}

	@Override
	public void deleteTSinglesel(Integer id) {
		String sql ="DELETE FROM t_singlesel_temp WHERE  id ="+id;
		this.executeSql(sql);
	}

	@Override
	public Long countTSinglesel(String key,String teacherId) {
		String sql ="SELECT count(*) FROM t_singlesel_temp as s where s.teacherId ='" + teacherId + "'";
		if (key != null) {
			sql = sql + "and s.skey1 like '" + key + "%'";
		}
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		return  ((BigInteger)q.uniqueResult()).longValue();
	}

	@Override
	public List<TSinglesel> getListByPage(int currentPage, String key,String teacherId) {
		if (key == null) {
			
				return this.find("from TSinglesel where teacherId = '"
						+ teacherId + "'", currentPage, 10);
			
		} else {
			return this.find("from TSinglesel where teacherId = " + teacherId
					+ " and skey1 like '" + key + "%'", currentPage, 10);
		}
	}
}
