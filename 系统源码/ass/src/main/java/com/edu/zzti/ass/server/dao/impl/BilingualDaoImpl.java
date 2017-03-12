package com.edu.zzti.ass.server.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.edu.zzti.ass.core.dao.impl.BaseDaoImpl;
import com.edu.zzti.ass.server.dao.IBilingualDao;
import com.edu.zzti.ass.server.dao.ILexiconDao;
import com.edu.zzti.ass.server.model.Bilingual;
import com.edu.zzti.ass.server.model.Lexicon;

@Repository("bilingualDao")
public class BilingualDaoImpl extends BaseDaoImpl<Bilingual> implements
		IBilingualDao {

	@Override
	public Long countBilingual(String key) {
		String sql ="SELECT count(*) FROM t_bilingual as b ";
		if(key!=null){
			sql=sql+"where b.title like '"+key+"%'";
		}
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		return  ((BigInteger)q.uniqueResult()).longValue();
	}

	@Override
	public List<Bilingual> getListByPage(int currentPage, String key) {
		if (key == null) {
			return this.find("from Bilingual", currentPage, 10);
		} else {
			return this.find("from Bilingual as l where l.title like '"+key+"%'", currentPage, 10);
		}
	}

	@Override
	public void deleteBilingual(Integer id) {
		String sql ="DELETE FROM t_bilingual WHERE  id ="+id;
		this.executeSql(sql);
	}

	@Override
	public void updateBil(Bilingual bil) {
		String sql ="update t_bilingual set title = '"+bil.getTitle()+"',enContent = '"+bil.getEnContent()+"',zhContent = '"+bil.getZhContent()+"' where id= "+bil.getId();
		this.executeSql(sql);
	}

	@Override
	public Bilingual findBilingual(String title) {
		String hql = "from Bilingual as l where l.title ='"+title+"'";
		return super.getByHql(hql);
	}

	@Override
	public Long getCount() {
		String sql ="SELECT count(*) FROM t_bilingual ";
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		return  ((BigInteger)q.uniqueResult()).longValue();
	}

	@Override
	public Bilingual getOne(int random) {
		return this.find("from Bilingual", random, 1).get(0);
	}


}
