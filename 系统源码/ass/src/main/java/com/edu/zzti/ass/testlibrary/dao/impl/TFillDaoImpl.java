package com.edu.zzti.ass.testlibrary.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.edu.zzti.ass.core.dao.impl.BaseDaoImpl;
import com.edu.zzti.ass.testlibrary.dao.ITFillDao;
import com.edu.zzti.ass.testlibrary.dao.ITSingleselDao;
import com.edu.zzti.ass.testlibrary.model.TFill;
import com.edu.zzti.ass.testlibrary.model.TSinglesel;

@Repository("tFillDao")
public class TFillDaoImpl extends BaseDaoImpl<TFill>  implements ITFillDao {

	@Override
	public Serializable addFill(TFill fill) {
		
		return super.save(fill);
		
	}

	@Override
	public List<TFill> findAllFill() {
		String hql ="from TFill";
		return super.find(hql);
	}

	@Override
	public void deleteTFill(Integer id) {
		String sql ="DELETE FROM t_fill_temp WHERE  id ="+id;
		this.executeSql(sql);
	}

	@Override
	public Long countTFill(String key,String teacherId) {
		String sql ="SELECT count(*) FROM t_fill_temp as s where s.teacherId ='" + teacherId + "'";
		if (key != null) {
			sql = sql + "and s.fkey like '" + key + "%'";
		}
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		return  ((BigInteger)q.uniqueResult()).longValue();
	}

	@Override
	public List<TFill> getListByPage(int currentPage, String key,String teacherId) {
		if (key == null) {
			
				return this.find("from TFill where teacherId = '"
						+ teacherId + "'", currentPage, 10);
			
		} else {
			return this.find("from TFill where teacherId = '" + teacherId
					+ "' and fkey like '" + key + "%'", currentPage, 10);
		}
	}
}
