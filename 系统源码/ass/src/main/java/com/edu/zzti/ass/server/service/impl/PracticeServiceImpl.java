package com.edu.zzti.ass.server.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.zzti.ass.management.dao.IStudentDao;
import com.edu.zzti.ass.management.dao.ITeacherDao;
import com.edu.zzti.ass.management.model.TTeacher;
import com.edu.zzti.ass.server.dao.IAnswerInfoDao;
import com.edu.zzti.ass.server.dao.IPracticeAnswerDao;
import com.edu.zzti.ass.server.dao.IPracticePaperDao;
import com.edu.zzti.ass.server.model.AnswerInfo;
import com.edu.zzti.ass.server.model.Chapter;
import com.edu.zzti.ass.server.model.PracticeAnswer;
import com.edu.zzti.ass.server.model.PracticePaper;
import com.edu.zzti.ass.server.model.Unit;
import com.edu.zzti.ass.server.service.IPracticeService;
import com.edu.zzti.ass.testlibrary.dao.IJudgeDao;
import com.edu.zzti.ass.testlibrary.dao.ISingleselDao;
import com.edu.zzti.ass.testlibrary.dao.ISubjectiveDao;
import com.edu.zzti.ass.testlibrary.dao.ITunitDao;
import com.edu.zzti.ass.testlibrary.model.Judge;
import com.edu.zzti.ass.testlibrary.model.Singlesel;
import com.edu.zzti.ass.testlibrary.model.Subjective;
import com.edu.zzti.ass.testlibrary.model.TChapter;
import com.edu.zzti.ass.testlibrary.model.TSector;
import com.edu.zzti.ass.testlibrary.model.TUnit;

@Service("practiceService")
public class PracticeServiceImpl implements IPracticeService {

	@Autowired
	private ITunitDao unitDao;

	@Autowired
	private IJudgeDao judgeDao;

	@Autowired
	private ISingleselDao singleselDao;

	@Autowired
	private ISubjectiveDao subjectiveDao;
	
	@Autowired
	private IPracticePaperDao practicePaperDao;
	
	@Autowired
	private IPracticeAnswerDao practiceAnswerDao;
	
	@Autowired
	private IStudentDao studentDao;
	
	@Autowired
	private ITeacherDao teacherDao;
	
	@Autowired
	private IAnswerInfoDao answerInfoDao;
	
	@Override
	public List<Unit> getUnits() {
		List<TUnit> list = unitDao.findAllUnit();
		List<Unit> units = new ArrayList<Unit>();
		for (TUnit tUnit : list) {
			Unit unit = new Unit();
			unit.setId(tUnit.getId());
			unit.setUnitName(tUnit.getUnitName());
			Set<Chapter> chapters = new HashSet<Chapter>();
			Iterator<TChapter> iterator = tUnit.gettChapters().iterator();
			while (iterator.hasNext()) {
				TChapter temp = (TChapter) iterator.next();
				Chapter chapter = new Chapter();
				chapter.setId(temp.getId());
				chapter.setChaName(temp.getChaName());
				chapters.add(chapter);
			}
			unit.setChapters(chapters);
			units.add(unit);
		}
		return units;
	}

	@Override
	public PracticePaper addPra(String praName, Integer praDx, Integer praPd,
			Integer praJd, String praCutTime, String teacherId, Integer unitId,
			List<Integer> chapterIdList) {


		PracticePaper paper = new PracticePaper();
		PracticeAnswer answer = new PracticeAnswer();
		paper.setPagerName(praName);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date answerDate = null;
		try {
			answerDate = sdf.parse(praCutTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		paper.setAnswerDate(answerDate);
		paper.setCreateDate(new Date());
		paper.setIsRelease(0);
		paper.setTeacher(new TTeacher(teacherId));
		paper.setUnit(new TUnit(unitId));

		TUnit unit = unitDao.getById(TUnit.class, unitId);
		Set<TChapter> chapters = unit.gettChapters();
		List<Integer> sectorIds = new ArrayList<Integer>();
		Iterator<TChapter> iterator = chapters.iterator();

		while (iterator.hasNext()) {
			TChapter chapter = iterator.next();
			if (chapterIdList.contains(chapter.getId())) {
				Iterator<TSector> iter = chapter.gettSectors().iterator();
				while (iter.hasNext()) {
					TSector sector = iter.next();
					sectorIds.add(sector.getId());
				}
			}
		}

		List<Singlesel> temp1 = singleselDao.getListBySecIds(sectorIds);
		List<Singlesel> singlesels = new ArrayList<Singlesel>();
		StringBuffer singleAnswers = new StringBuffer();

		for (int i = 0; i < praDx; i++) {
			Singlesel sing = temp1.get(new Random().nextInt(temp1.size()));
			if (singlesels.contains(sing)) {
				i--;
			} else {
				singlesels.add(sing);
				singleAnswers.append(sing.getAnswer());
				singleAnswers.append(",");
			}
		}
		singleAnswers.deleteCharAt(singleAnswers.length() - 1);

		answer.setSingleAnswers(singleAnswers.toString());

		List<Judge> temp2 = judgeDao.getListBySecIds(sectorIds);
		List<Judge> judges = new ArrayList<Judge>();
		StringBuffer judgesAnswers = new StringBuffer();

		for (int i = 0; i < praPd; i++) {
			Judge judge = temp2.get(new Random().nextInt(temp2.size()));
			if (judges.contains(judge)) {
				i--;
			} else {
				judges.add(judge);
				judgesAnswers.append(judge.getAnswer());
				judgesAnswers.append(",");
			}

		}
		judgesAnswers.deleteCharAt(judgesAnswers.length() - 1);
		answer.setJudgeAnswers(judgesAnswers.toString());

		List<Subjective> temp3 = subjectiveDao.getListBySecIds(sectorIds);
		List<Subjective> subjectives = new ArrayList<Subjective>();
		StringBuffer subjectiveAnswers = new StringBuffer();
		for (int i = 0; i < praJd; i++) {
			Subjective subjective = temp3
					.get(new Random().nextInt(temp3.size()));
			if (subjectives.contains(subjective)) {
				i--;
			} else {
				subjectives.add(subjective);
				subjectiveAnswers.append(subjective.getAnswer());
				subjectiveAnswers.append("////");
			}

		}
		subjectiveAnswers.delete(subjectiveAnswers.length() - 4,
				subjectiveAnswers.length());
		answer.setSubjectAnswers(subjectiveAnswers.toString());

		practiceAnswerDao.save(answer);

		paper.setSinglesels(singlesels);
	
		paper.setJudges(judges);
		paper.setSubjective(subjectives);
		paper.setPracticeAnswer(answer);
		System.out.println(paper.getJudges());
		
		
		Integer paerId=(Integer) practicePaperDao.save(paper);
		for(Judge judge:judges){
			String sql ="insert into t_pra_judge(judgeId,paperId)values("+judge.getId()+","+paerId+")" ;
			judgeDao.executeSql(sql);
		}
		for(Singlesel singlesel:singlesels){
			String sql ="insert into t_pra_single(singleselId,paperId)values("+singlesel.getId()+","+paerId+")" ;
			singleselDao.executeSql(sql);
		}
		for(Subjective subjective:subjectives){
			String sql ="insert into t_pra_subj(subjectiveId,paperId)values("+subjective.getId()+","+paerId+")" ;
			subjectiveDao.executeSql(sql);
		}
		return paper;
	}

	@Override
	public PracticePaper getPaper(Integer id) {
		PracticePaper paper =  practicePaperDao.getById(PracticePaper.class, id);
		return paper;
	}

	@Override
	public int  deal(Integer id, Integer type) {
		if(type==0){
			practicePaperDao.deleteByHql(id);
			return 0;
		}else{
			practicePaperDao.updateSql(id);	
			return 1;
		}
		
	}


	@Override
	public List<PracticePaper> getPaperList(String teacherid, Integer unitId) {
		String hql = "from PracticePaper as p where p.isRelease = 1 and p.teacher.id = '"+teacherid+"' and p.unit.id ="+unitId+"  order by p.id desc ";
		
		return practicePaperDao.find(hql);
	}

	@Override
	public String getTeacherId(String id) {
		String sql = "select teacher_id  from  t_class where id = (select s.classId from t_student s where s.id = "+id+")";
		List<Map> maplist =  practicePaperDao.findBySql(sql);
		Map map = maplist.get(0);
		
		return map.get("teacher_id").toString();
	}

	@Override
	public void saveStuAnswer(AnswerInfo answerInfo) {
		
		answerInfoDao.save(answerInfo);
	}

	@Override
	public boolean findStuAnswer(String studentId, Integer paperid) {
		
		return answerInfoDao.findBysql(studentId,paperid);
	}

	@Override
	public List<AnswerInfo> getStuPaperList(String id, Integer unitId) {
		
		return answerInfoDao.getListByHql(id,unitId);
	}

	@Override
	public AnswerInfo getAnswer(Integer id) {
		
		return answerInfoDao.getById(AnswerInfo.class, id);
	}

	@Override
	public TTeacher getTeacher(String teacherid) {
		
		return teacherDao.getOne(teacherid);
	}

	@Override
	public List<AnswerInfo> getAnswerlist(Integer paperid, Integer classId) {
		
		return answerInfoDao.getAnswerlist(paperid,classId);
	}

}
