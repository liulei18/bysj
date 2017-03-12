package com.edu.zzti.ass.onlinestudy.dao.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edu.zzti.ass.core.dao.impl.BaseDaoImpl;
import com.edu.zzti.ass.onlinestudy.dao.ITestAnswerInfoDao;
import com.edu.zzti.ass.onlinestudy.model.TestAnswerInfo;

@Repository("testAnswerInfoDao")
public class TestAnswerInfoDaoImpl extends BaseDaoImpl<TestAnswerInfo> implements ITestAnswerInfoDao{

	@Override
	public boolean findBysql(String studentId, Integer paperid) {
		String sql ="select count(*) as num from t_testanswerinfo where studentId = '"+studentId+"' and paperId = "+paperid;
		List<Map> list =  super.findBySql(sql);
		Map map  = list.get(0);
		BigInteger num = (BigInteger) map.get("num");
		if(num.longValue()>0){
			return true;
			
		}else{
			return false;
		}

	}

}
