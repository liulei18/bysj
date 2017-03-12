package com.edu.zzti.ass.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.edu.zzti.ass.core.dao.impl.BaseDaoImpl;
import com.edu.zzti.ass.server.dao.IPracticePaperDao;
import com.edu.zzti.ass.server.model.PracticePaper;


@Repository("practicePaperDao")
public class PracticePaperImpl extends BaseDaoImpl<PracticePaper> implements IPracticePaperDao {

	@Override
	public void updateSql(Integer id) {
		String sql ="update t_practicepaper set isRelease = 1 where id = "+id;
		super.executeSql(sql);
	}

	@Override
	public void deleteByHql(Integer id) {
		String sql = "delete from t_pra_judge  where paperId = "+id;
		super.executeSql(sql);
		String sql1 = "delete from t_pra_single where paperId = "+id;
		super.executeSql(sql1);
		String sql2 = "delete from t_pra_subj  where paperId = "+id;
		super.executeSql(sql2);
		PracticePaper paper =super.getById(PracticePaper.class, id);
		 int answerid =paper.getPracticeAnswer().getId();
		String sql4 = "delete from t_practicepaper where id = "+id;
		super.executeSql(sql4);
		String sql5 = "delete from t_practiceanswer  where id = "+answerid;
		super.executeSql(sql5);
		
	}  

}
