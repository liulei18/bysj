package com.edu.zzti.ass.testlibrary.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.zzti.ass.management.model.TTeacher;
import com.edu.zzti.ass.onlinestudy.dao.ITestAnswerDao;
import com.edu.zzti.ass.onlinestudy.dao.ITestPaperDao;
import com.edu.zzti.ass.onlinestudy.model.TestAnswer;
import com.edu.zzti.ass.onlinestudy.model.TestPaper;
import com.edu.zzti.ass.server.model.Chapter;
import com.edu.zzti.ass.server.model.Unit;
import com.edu.zzti.ass.server.service.IPracticeService;
import com.edu.zzti.ass.testlibrary.dao.IFillDao;
import com.edu.zzti.ass.testlibrary.dao.IJudgeDao;
import com.edu.zzti.ass.testlibrary.dao.ISingleselDao;
import com.edu.zzti.ass.testlibrary.dao.ISubjectiveDao;
import com.edu.zzti.ass.testlibrary.dao.ITunitDao;
import com.edu.zzti.ass.testlibrary.model.Fill;
import com.edu.zzti.ass.testlibrary.model.Judge;
import com.edu.zzti.ass.testlibrary.model.Singlesel;
import com.edu.zzti.ass.testlibrary.model.Subjective;
import com.edu.zzti.ass.testlibrary.model.TChapter;
import com.edu.zzti.ass.testlibrary.model.TSector;
import com.edu.zzti.ass.testlibrary.model.TUnit;
import com.edu.zzti.ass.testlibrary.service.IAutoTestService;

@Service("autoTestService")
public class AutoTestServiceImpl implements IAutoTestService {
	@Autowired
	private ITestAnswerDao testAnswerDao;
	
	@Autowired
	private ITestPaperDao  testPaperDao;
	
	@Autowired
	private ITunitDao unitDao;

	@Autowired
	private IJudgeDao judgeDao;

	@Autowired
	private ISingleselDao singleselDao;

	@Autowired
	private ISubjectiveDao subjectiveDao;
	
	@Autowired
	private IFillDao fillDao;
	

	@Override
	public TestPaper addTest(String paperName, Integer jEasy, Integer jMid,
			Integer jDif, Integer siEasy, Integer siMid, Integer siDif,
			Integer suEasy, Integer suMid, Integer suDif,Integer fEasy, Integer fMid,
			Integer fDif,List<Integer> chapterIdList,HttpSession httpSession) {


		TestPaper test = new TestPaper();
		TestAnswer answer = new TestAnswer();
		test.setPagerName(paperName);
		test.setCreateDate(new Date());
		test.setIsRelease(0);
		TTeacher teacher = new TTeacher();
		teacher = (TTeacher) httpSession.getAttribute("userInfo");
		test.setTeacher(teacher);
		Integer unitId =   (Integer) httpSession.getAttribute("unitId");
		test.setUnit(new TUnit(unitId));
		TUnit unit = unitDao.getById(TUnit.class, unitId);
		Set<TChapter> chapters = unit.gettChapters();
		List<Integer> sectorIds = new ArrayList<Integer>();
		Iterator<TChapter> iterator = chapters.iterator();
	/*
	 *  iterator
	 */
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
		/*
		 * 判断题三种试题难度的遍历
		 */
		
		List<Judge> judges = new ArrayList<Judge>();
		StringBuffer judgesAnswers = new StringBuffer();
		List<Judge> easyJudge = judgeDao.getListBySecIds(sectorIds,"0");
		
		for (int i = 0; i < jEasy; i++) {
			Judge judge = easyJudge.get(new Random().nextInt(easyJudge.size()));
			if (judges.contains(judge)) {
				i--;
			} else {
				judges.add(judge);
				judgeDao.updateNum(judge.getId());
				judgesAnswers.append(judge.getAnswer());
				judgesAnswers.append(",");
			}
		}
		List<Judge> midJudge = judgeDao.getListBySecIds(sectorIds,"1");
			for (int i = 0; i < jMid; i++) {
				Judge judge = midJudge.get(new Random().nextInt(midJudge.size()));
				if (judges.contains(judge)) {
					i--;
				} else {
					judges.add(judge);
					judgeDao.updateNum(judge.getId());
					judgesAnswers.append(judge.getAnswer());
					judgesAnswers.append(",");
				}
			}

		List<Judge> difJudge = judgeDao.getListBySecIds(sectorIds,"2");
		for (int i = 0; i < jDif; i++) {
			Judge judge = difJudge.get(new Random().nextInt(difJudge.size()));
			if (judges.contains(judge)) {
				i--;
			} else {
				judges.add(judge);
				judgeDao.updateNum(judge.getId());
				judgesAnswers.append(judge.getAnswer());
				judgesAnswers.append(",");
			}
		}
		judgesAnswers.deleteCharAt(judgesAnswers.length() - 1);
		answer.setJudgeAnswers(judgesAnswers.toString());

		/*
		 * 单选试题三种难度的遍历
		 */
		List<Singlesel> singlesels = new ArrayList<Singlesel>();
		StringBuffer singleAnswers = new StringBuffer();
		
		List<Singlesel> easySingle = singleselDao.getListBySecIds(sectorIds,"0");
		for (int i = 0; i < siEasy; i++) {
			Singlesel sing = easySingle.get(new Random().nextInt(easySingle.size()));
			if (singlesels.contains(sing)) {
				i--;
			} else {
				singlesels.add(sing);
				singleselDao.updateNum(sing.getId());
				singleAnswers.append(sing.getAnswer());
				singleAnswers.append(",");
			}
		}
		
		List<Singlesel> midSingle = singleselDao.getListBySecIds(sectorIds,"1");
		for (int i = 0; i < siMid; i++) {
			Singlesel sing = midSingle.get(new Random().nextInt(midSingle.size()));
			if (singlesels.contains(sing)) {
				i--;
			} else {
				singlesels.add(sing);
				singleselDao.updateNum(sing.getId());
				singleAnswers.append(sing.getAnswer());
				singleAnswers.append(",");
			}
		}
		List<Singlesel> difSingle = singleselDao.getListBySecIds(sectorIds,"2");
		for (int i = 0; i < siDif; i++) {
			Singlesel sing = difSingle.get(new Random().nextInt(difSingle.size()));
			if (singlesels.contains(sing)) {
				i--;
			} else {
				singlesels.add(sing);
				singleselDao.updateNum(sing.getId());
				singleAnswers.append(sing.getAnswer());
				singleAnswers.append(",");
			}
		}
		singleAnswers.deleteCharAt(singleAnswers.length() - 1);
		answer.setSingleAnswers(singleAnswers.toString());

		
		
		List<Subjective> subjectives = new ArrayList<Subjective>();
		StringBuffer subjectiveAnswers = new StringBuffer();
		
		List<Subjective> easySubjective = subjectiveDao.getListBySecIds(sectorIds,"0");
		for (int i = 0; i < suEasy; i++) {
			Subjective subjective = easySubjective
					.get(new Random().nextInt(easySubjective.size()));
			if (subjectives.contains(subjective)) {
				i--;
			} else {
				subjectives.add(subjective);
				subjectiveDao.updateNum(subjective.getId());
				subjectiveAnswers.append(subjective.getAnswer());
				subjectiveAnswers.append("////");
			}

		}
		if(suMid>0){
			List<Subjective> midSubjective = subjectiveDao.getListBySecIds(sectorIds,"1");
			for (int i = 0; i < suMid; i++) {
				Subjective subjective = midSubjective
						.get(new Random().nextInt(midSubjective.size()));
				if (subjectives.contains(subjective)) {
					i--;
				} else {
					subjectives.add(subjective);
					subjectiveDao.updateNum(subjective.getId());
					subjectiveAnswers.append(subjective.getAnswer());
					subjectiveAnswers.append("////");
				}

			}
		}
		
		if(suDif>0){
			List<Subjective> difSubjective = subjectiveDao.getListBySecIds(sectorIds,"2");
			for (int i = 0; i < suDif; i++) {
				Subjective subjective = difSubjective
						.get(new Random().nextInt(difSubjective.size()));
				if (subjectives.contains(subjective)) {
					i--;
				} else {
					subjectives.add(subjective);
					subjectiveDao.updateNum(subjective.getId());
					subjectiveAnswers.append(subjective.getAnswer());
					subjectiveAnswers.append("////");
				}
			}
		}
		
		subjectiveAnswers.delete(subjectiveAnswers.length() - 4,
				subjectiveAnswers.length());
		answer.setSubjectAnswers(subjectiveAnswers.toString());
		/*
		 * 填空题三种试题难度的遍历
		 */
		
		List<Fill> fills = new ArrayList<Fill>();
		StringBuffer fillsAnswers = new StringBuffer();
		List<Fill> easyFill = fillDao.getListBySecIds(sectorIds,"0");
		
		for (int i = 0; i < jEasy; i++) {
			Fill fill = easyFill.get(new Random().nextInt(easyFill.size()));
			if (fills.contains(fill)) {
				i--;
			} else {
				fills.add(fill);
				fillDao.updateNum(fill.getId());
				fillsAnswers.append(fill.getAnswer());
				fillsAnswers.append("|");
			}
		}
		List<Fill> midFill = fillDao.getListBySecIds(sectorIds,"1");
			for (int i = 0; i < jMid; i++) {
				Fill fill = midFill.get(new Random().nextInt(midFill.size()));
				if (fills.contains(fill)) {
					i--;
				} else {
					fills.add(fill);
					fillDao.updateNum(fill.getId());
					fillsAnswers.append(fill.getAnswer());
					fillsAnswers.append("|");
				}
			}

		List<Fill> difFill = fillDao.getListBySecIds(sectorIds,"2");
		for (int i = 0; i < jDif; i++) {
			Fill fill = difFill.get(new Random().nextInt(difFill.size()));
			if (fills.contains(fill)) {
				i--;
			} else {
				fills.add(fill);
				fillDao.updateNum(fill.getId());
				fillsAnswers.append(fill.getAnswer());
				fillsAnswers.append("|");
			}
		}
		fillsAnswers.deleteCharAt(fillsAnswers.length() - 1);
		answer.setFillAnswers(fillsAnswers.toString());
		
		
		
		testAnswerDao.save(answer);

		test.setSinglesels(singlesels);
	
		test.setJudges(judges);
		test.setSubjective(subjectives);
		test.setFill(fills);
		test.setTestAnswer(answer);
		
		
		Integer testId=(Integer) testPaperDao.save(test);
		for(Judge judge:judges){
			String sql ="insert into t_page_judge(judgeId,paperId)values("+judge.getId()+","+testId+")" ;
			judgeDao.executeSql(sql);
		}
		for(Singlesel singlesel:singlesels){
			String sql ="insert into t_page_single(singleselId,paperId)values("+singlesel.getId()+","+testId+")" ;
			singleselDao.executeSql(sql);
		}
		for(Subjective subjective:subjectives){
			String sql ="insert into t_page_subj(subjectiveId,paperId)values("+subjective.getId()+","+testId+")" ;
			subjectiveDao.executeSql(sql);
		}
		for(Fill fill:fills){
			String sql ="insert into t_page_fill(subjectiveId,paperId)values("+fill.getId()+","+testId+")" ;
			fillDao.executeSql(sql);
		}
		return test;
	}

	@Override
	public TestPaper getPaper(Integer id) {
		TestPaper test =  testPaperDao.getById(TestPaper.class, id);
		return test;
	}

	@Override
	public void  delTest(Integer testId) {
			testPaperDao.deleteByHql(testId);
	}

	@Override
	public void del(Integer id) {/*
		testPaperDao.delete(o);*/
	}

	
	

}
