package com.edu.zzti.ass.server.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.edu.zzti.ass.core.dao.impl.BaseDaoImpl;
import com.edu.zzti.ass.server.dao.ILexiconDao;
import com.edu.zzti.ass.server.model.Lexicon;

@Repository("lexiconDao")
public class LexiconDaoImpl extends BaseDaoImpl<Lexicon> implements
		ILexiconDao {

	@Override
	public Long countLexicon(String key) {
		String sql ="SELECT count(*) FROM t_lexicon as l ";
		if(key!=null){
			sql=sql+"where l.word like '"+key+"%'";
		}
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		return  ((BigInteger)q.uniqueResult()).longValue();
	}

	@Override
	public List<Lexicon> getListByPage(int currentPage, String key) {

		if (key == null) {
			return this.find("from Lexicon", currentPage, 10);
		} else {
			return this.find("from Lexicon as l where l.word like '"+key+"%'", currentPage, 10);
		}

	}

	@Override
	public void deleteLexicon(Integer id) {
		String sql ="DELETE FROM t_lexicon WHERE  id ="+id;
		this.executeSql(sql);
	}

	@Override
	public void updateLex(Lexicon lexicon) {
		
		String sql ="update t_lexicon set word = '"+lexicon.getWord()+"',en_Mean = '"+lexicon.getEnMean()+"',zh_Mean = '"+lexicon.getZhMean()+"' where id= "+lexicon.getId();
		this.executeSql(sql);
		
	}

	@Override
	public Lexicon findLexicon(String word) {
		String hql = "from Lexicon as l where l.word ='"+word+"'";
		return super.getByHql(hql);
	}

	@Override
	public Long getCount() {
		String sql ="SELECT count(*) FROM t_lexicon ";
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		return  ((BigInteger)q.uniqueResult()).longValue();
	}

	@Override
	public Lexicon getOne(int random) {
		
		return this.find("from Lexicon", random, 1).get(0);
	}

}
