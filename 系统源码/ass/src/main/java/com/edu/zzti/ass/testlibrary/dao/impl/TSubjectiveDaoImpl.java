package com.edu.zzti.ass.testlibrary.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.edu.zzti.ass.core.dao.impl.BaseDaoImpl;
import com.edu.zzti.ass.testlibrary.dao.ITSubjectiveDao;
import com.edu.zzti.ass.testlibrary.model.TSubjective;
@Repository("tSubjectiveDao")
public class TSubjectiveDaoImpl extends BaseDaoImpl<TSubjective> implements
		ITSubjectiveDao {

	@Override
	public Serializable addSubjective(TSubjective subjective) {
		
		return super.save(subjective);
	}

	@Override
	public List<TSubjective> findAllSubjective() {
		String hql ="from TSubjective";
		return super.find(hql);
	}

	@Override
	public void deleteTSubjective(Integer id) {
		String sql ="DELETE FROM t_subjective_temp WHERE  id ="+id;
		this.executeSql(sql);
	}

	@Override
	public Long countTSubjective(String key,String  teacherId) {
		String sql ="SELECT count(*) FROM t_subjective_temp as s where s.teacherId ='"+teacherId+"'";
		if (key != null) {
			sql = sql + "and s.skey1 like '" + key + "%'";
			
		}
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		return  ((BigInteger)q.uniqueResult()).longValue();
	}

	@Override
	public List<TSubjective> getListByPage(int currentPage, String key,String teacherId) {
		if (key == null) {
				return this.find("from TSubjective  where teacherId = '"
						+ teacherId + "'", currentPage, 10);
		} else {
			return this.find("from TSubjective  where teacherId = " + teacherId
					+ " and skey1 like '" + key + "%'", currentPage, 10);
		}
	}

}
