package com.edu.zzti.ass.testlibrary.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.edu.zzti.ass.core.dao.impl.BaseDaoImpl;
import com.edu.zzti.ass.testlibrary.dao.ITJudgeDao;
import com.edu.zzti.ass.testlibrary.model.TJudge;
@Repository("tJudgeDao")
public class TJudgeDaoImpl  extends BaseDaoImpl<TJudge> implements ITJudgeDao {
	@Override
	public Serializable addJudge(TJudge judge) {
		return super.save(judge);

	}

	@Override
	public List<TJudge> findAllJudge() {
		String hql="from TJudge";
		return super.find(hql);
	}

	@Override
	public void deleteTjudge(Integer id) {
		String sql ="DELETE FROM t_judge_temp WHERE  id ="+id;
		this.executeSql(sql);
		
	}
	
	@Override
	public Long countTJudge(String key,String teacherId) {
		String sql ="SELECT count(*) FROM t_judge_temp as l where l.teacherId = '" + teacherId + "'";
		if (key != null) {
			sql = sql + "and l.jkey1 like '" + key + "%'";
		}
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		return  ((BigInteger)q.uniqueResult()).longValue();
	}

	
	@Override
	public List<TJudge> getListByPage(int currentPage, String key,String teacherId) {
		if (key == null) {
				return this.find("from TJudge  where teacherId = '"
						+ teacherId + "'", currentPage, 10);
		} else {
			return this.find("from TJudge where teacherId = '" + teacherId
					+ "' and jkey1 like '" + key + "%'", currentPage, 10);
		}


	}

	@Override
	public void updateLex(TJudge judge) {
		String sql ="update t_judge_temp set jkey1 = '"+judge.getJkey1()+"',question = '"+judge.getQuestion()+"',zh_Mean = '"+judge.getAnswer()+"' where id= "+judge.getId();
		this.executeSql(sql);
	}

	
}
