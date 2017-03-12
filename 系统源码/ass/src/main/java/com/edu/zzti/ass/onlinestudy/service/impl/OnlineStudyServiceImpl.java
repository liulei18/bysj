package com.edu.zzti.ass.onlinestudy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.zzti.ass.onlinestudy.dao.IOnlineStudyDao;
import com.edu.zzti.ass.onlinestudy.dao.ITestAnswerInfoDao;
import com.edu.zzti.ass.onlinestudy.dao.ITestPaperDao;
import com.edu.zzti.ass.onlinestudy.model.TestAnswerInfo;
import com.edu.zzti.ass.onlinestudy.model.TestPaper;
import com.edu.zzti.ass.onlinestudy.service.IOnlineStudyService;
@Service("onlineStudyService")
public class OnlineStudyServiceImpl implements IOnlineStudyService  {

	@Autowired
	private IOnlineStudyDao onlineStudyDao;
	@Autowired
	private  ITestPaperDao testPaperDao;
	
	@Autowired
	private  ITestAnswerInfoDao testAnswerInfoDao;
	
	@Override
	public Map<String, List> find(Integer sectorId) {
		Map<String,List> map = new HashMap<String, List>();
		map.put("judge", onlineStudyDao.getJudgeByUnitId(sectorId));
		map.put("singlesel",onlineStudyDao.getSingleselByUnitId(sectorId));
		map.put("subjective",onlineStudyDao.getSubjectiveByUnitId(sectorId));
		return map;
	}
	
	@Override
	public String getTeacherId(String id) {
		String sql = "select teacher_id  from  t_class where id = (select s.classId from t_student s where s.id = "+id+")";
		List<Map> maplist =  onlineStudyDao.findBySql(sql);
		Map map = maplist.get(0);
		
		return map.get("teacher_id").toString();
	}

	@Override
	public List<TestPaper> getPaperByUnit(String teacherId, Integer unitId) {
		String hql = "from TestPaper as t where t.teacher.id = '"+teacherId+"' and t.unit.id = "+unitId+" and isRelease = 1";
		
		return testPaperDao.find(hql);
	}

	@Override
	public List<TestAnswerInfo> getAnswerByUnit(String id, Integer unitId) {
		String hql = "from TestAnswerInfo as t where t.student.id = '"+id+"' and t.unit.id = "+unitId;

		return testAnswerInfoDao.find(hql);
	}

}
