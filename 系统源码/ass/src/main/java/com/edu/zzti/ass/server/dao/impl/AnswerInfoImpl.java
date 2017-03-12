package com.edu.zzti.ass.server.dao.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edu.zzti.ass.core.dao.impl.BaseDaoImpl;
import com.edu.zzti.ass.server.dao.IAnswerInfoDao;
import com.edu.zzti.ass.server.model.AnswerInfo;


@Repository("answerInfoDao")
public class AnswerInfoImpl extends BaseDaoImpl<AnswerInfo> implements IAnswerInfoDao {

	@Override
	public boolean findBysql(String studentId, Integer paperid) {
		String sql ="select count(*) as num from t_answerinfo where studentId = '"+studentId+"' and paperId = "+paperid;
		List<Map> list =  super.findBySql(sql);
		Map map  = list.get(0);
		BigInteger num = (BigInteger) map.get("num");
		if(num.longValue()>0){
			return true;
			
		}else{
			return false;
		}

	}

	@Override
	public List<AnswerInfo> getListByHql(String id, Integer unitId) {
		String hql = "from AnswerInfo as a  where a.student.id = '"+id +"' and a.unit.id = "+unitId;
		return super.find(hql);
	}

	@Override
	public List<AnswerInfo> getAnswerlist(Integer paperid,Integer classId) {
		String hql = "from AnswerInfo as a where a.paper.id = "+paperid+" and a.student.TClass.id ="+classId ;
		return super.find(hql);
	}

}
