package com.edu.zzti.ass.testlibrary.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.zzti.ass.management.model.TTeacher;
import com.edu.zzti.ass.onlinestudy.dao.IOnlineStudyDao;
import com.edu.zzti.ass.onlinestudy.dao.ITestAnswerDao;
import com.edu.zzti.ass.onlinestudy.dao.ITestPaperDao;
import com.edu.zzti.ass.onlinestudy.model.TestAnswer;
import com.edu.zzti.ass.onlinestudy.model.TestPaper;
import com.edu.zzti.ass.testlibrary.dao.IFillDao;
import com.edu.zzti.ass.testlibrary.dao.IJudgeDao;
import com.edu.zzti.ass.testlibrary.dao.ISingleselDao;
import com.edu.zzti.ass.testlibrary.dao.ISubjectiveDao;
import com.edu.zzti.ass.testlibrary.dao.ITChapterDao;
import com.edu.zzti.ass.testlibrary.dao.ITSectorDao;
import com.edu.zzti.ass.testlibrary.dao.ITunitDao;
import com.edu.zzti.ass.testlibrary.model.Fill;
import com.edu.zzti.ass.testlibrary.model.Judge;
import com.edu.zzti.ass.testlibrary.model.Singlesel;
import com.edu.zzti.ass.testlibrary.model.Subjective;
import com.edu.zzti.ass.testlibrary.model.TChapter;
import com.edu.zzti.ass.testlibrary.model.TSector;
import com.edu.zzti.ass.testlibrary.service.ILibraryService;

@Service("libraryService")
public class LibraryServiceImpl implements ILibraryService {
	
	@Autowired
	private ITestAnswerDao testAnswerDao;
	@Autowired
	private ITestPaperDao  testPaperDao;
	@Autowired
	private IJudgeDao judgeDao;
	@Autowired
	private ISingleselDao singleselDao;
	@Autowired
	private ISubjectiveDao subjectiveDao;
	@Autowired
	private IFillDao fillDao;
	@Autowired
	private ITChapterDao chapterDao;
	@Autowired
	private ITSectorDao sectorDao;


	

	@Override
	public List<TChapter> checkChapter(Integer unitId) {
		return chapterDao.getChapters(unitId);
	}

	@Override
	public List<TSector> checkSector(Integer chapterId) {

		return sectorDao.getSectors(chapterId);
	}

	

	@Override
	public Judge getByJudgeId(Integer id) {
		return judgeDao.getById(Judge.class, id);
	}

	@Override
	public Singlesel getBySingleId(Integer id) {
		
		return singleselDao.getById(Singlesel.class, id);
	}

	@Override
	public Subjective getBySubjecId(Integer id) {
		
		return subjectiveDao.getById(Subjective.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TestPaper addTest(String paperName, HttpSession httpSession) {
		TestPaper testPaper = new TestPaper();
		testPaper.setCreateDate(new Date());
		testPaper.setPagerName(paperName);
		testPaper.setIsRelease(0);
		TTeacher teacher = new TTeacher();
		teacher = (TTeacher) httpSession.getAttribute("userInfo");
		testPaper.setTeacher(teacher);
		
		/*
		 * 填充answer试题库
		 */
		TestAnswer answer = new TestAnswer();
		List<Integer> judList = (List<Integer>) httpSession.getAttribute("judgeId");
		List<Integer> sinList = (List<Integer>) httpSession.getAttribute("singleselId");
		List<Integer> subList = (List<Integer>) httpSession.getAttribute("subjectiveId");
		List<Integer> fillList = (List<Integer>) httpSession.getAttribute("fillId");
		if (judList == null) {
			judList = new ArrayList<Integer>();
		}
		if (sinList == null) {
			sinList = new ArrayList<Integer>();
		}
		if (subList == null) {
			subList = new ArrayList<Integer>();
		}
		if (fillList == null) {
			fillList = new ArrayList<Integer>();
		}
		
		/*循环遍历ID 得到判断题对象
		 */
		StringBuffer judgeAnswers = new StringBuffer();
		List<Judge> judges = new ArrayList<Judge>();
		for (Integer judId : judList) {
			judgeDao.updateNum(judId);
			Judge judge = getByJudgeId(judId);
			judgeAnswers.append(judge.getAnswer());
			judgeAnswers.append(",");
			judges.add(judge);
		}
		judgeAnswers.deleteCharAt(judgeAnswers.length()-1);
		testPaper.setJudges(judges);
		
		/*循环遍历ID 得到单选题对象
		 */
		StringBuffer singleselAnswers = new StringBuffer();
		List<Singlesel> singlesels = new ArrayList<Singlesel>();
		for (Integer sinId : sinList) {
			singleselDao.updateNum(sinId);
			Singlesel singlesel = getBySingleId(sinId);
			singleselAnswers.append(singlesel.getAnswer());
			singleselAnswers.append(",");
			singlesels.add(singlesel);
		}
		singleselAnswers.deleteCharAt(singleselAnswers.length()-1);
		testPaper.setSinglesels(singlesels);
		
		/*循环遍历ID 得到简答题对象
		 */
		StringBuffer subjectiveAnswers = new StringBuffer();
		List<Subjective> subjectives = new ArrayList<Subjective>();
		for (Integer subId : subList) {
			subjectiveDao.updateNum(subId);
			Subjective subjective = getBySubjecId(subId);
			subjectiveAnswers.append(subjective.getAnswer());
			subjectiveAnswers.append(",");
			subjectives.add(subjective);
		}
		subjectiveAnswers.deleteCharAt(subjectiveAnswers.length()-1);
		testPaper.setSubjective(subjectives);
		
		/*循环遍历ID 得到填空对象
		 */
		StringBuffer fillAnswers = new StringBuffer();
		List<Fill> fills = new ArrayList<Fill>();
		for (Integer fillId : fillList) {
			subjectiveDao.updateNum(fillId);
			Fill fill = getByFillId(fillId);
			fillAnswers.append(fill.getAnswer());
			fillAnswers.append("|");
			fills.add(fill);
		}
		
		fillAnswers.deleteCharAt(fillAnswers.length()-1);
		testPaper.setFill(fills);
		
		answer.setJudgeAnswers(judgeAnswers.toString());
		answer.setSingleAnswers(singleselAnswers.toString());
		answer.setSubjectAnswers(subjectiveAnswers.toString());
		answer.setFillAnswers(fillAnswers.toString());
		testAnswerDao.save(answer);
		testPaper.setTestAnswer(answer);
		Integer testPaperId=(Integer) testPaperDao.save(testPaper);
		for(Integer judId: judList){
			String sql ="insert into t_page_judge(judgeId,paperId)values("+judId+","+testPaperId+")" ;
			judgeDao.executeSql(sql);
		}
		for (Integer sinId : sinList) {
			String sql ="insert into t_page_single(singleselId,paperId)values("+sinId+","+testPaperId+")" ;
			judgeDao.executeSql(sql);
		}
		for (Integer subId : subList){
			String sql ="insert into t_page_subj(subjectiveId,paperId)values("+subId+","+testPaperId+")" ;
			judgeDao.executeSql(sql);
		}
		for (Integer fillId : fillList){
			String sql ="insert into t_page_fill(fillId,paperId)values("+fillId+","+testPaperId+")" ;
			fillDao.executeSql(sql);
		}
		return testPaper;
	}

	@Override
	public Fill getByFillId(Integer id) {
		return fillDao.getById(Fill.class, id);
	}

}
