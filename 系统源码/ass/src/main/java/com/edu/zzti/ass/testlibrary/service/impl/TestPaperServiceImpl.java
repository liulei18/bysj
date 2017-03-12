package com.edu.zzti.ass.testlibrary.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.zzti.ass.core.model.PageInfo;
import com.edu.zzti.ass.onlinestudy.dao.ITestPaperDao;
import com.edu.zzti.ass.onlinestudy.model.TestPaper;
import com.edu.zzti.ass.testlibrary.dao.IFillDao;
import com.edu.zzti.ass.testlibrary.dao.IJudgeDao;
import com.edu.zzti.ass.testlibrary.dao.ISingleselDao;
import com.edu.zzti.ass.testlibrary.dao.ISubjectiveDao;
import com.edu.zzti.ass.testlibrary.dao.ITFillDao;
import com.edu.zzti.ass.testlibrary.dao.ITJudgeDao;
import com.edu.zzti.ass.testlibrary.dao.ITSectorDao;
import com.edu.zzti.ass.testlibrary.dao.ITSingleselDao;
import com.edu.zzti.ass.testlibrary.dao.ITSubjectiveDao;
import com.edu.zzti.ass.testlibrary.dao.ITunitDao;
import com.edu.zzti.ass.testlibrary.model.Fill;
import com.edu.zzti.ass.testlibrary.model.Judge;
import com.edu.zzti.ass.testlibrary.model.Singlesel;
import com.edu.zzti.ass.testlibrary.model.Subjective;
import com.edu.zzti.ass.testlibrary.model.TFill;
import com.edu.zzti.ass.testlibrary.model.TJudge;
import com.edu.zzti.ass.testlibrary.model.TSinglesel;
import com.edu.zzti.ass.testlibrary.model.TSubjective;
import com.edu.zzti.ass.testlibrary.service.ITestPaperService;

@Service("testPaperService")
public class TestPaperServiceImpl implements ITestPaperService {
	@Autowired
	private ITunitDao unitDao;
	@Autowired
	private ITSectorDao sectorDao;
	@Autowired
	private ITJudgeDao tJudgeDao;
	@Autowired
	private ITSingleselDao tSingleselDao;
	@Autowired
	private ITSubjectiveDao tSubjectiveDao;
	@Autowired
	private ITFillDao tFillDao;
	@Autowired
	private IJudgeDao judgeDao;
	@Autowired
	private ISingleselDao singleselDao;
	@Autowired
	private ISubjectiveDao subjectiveDao;
	@Autowired
	private IFillDao fillDao;
	@Autowired
	private ITestPaperDao testPaperDao;

	@Override
	public Map<String, List> find() {
		Map<String, List> map = new HashMap<String, List>();
		map.put("unit", unitDao.findAllUnit());
		map.put("sector", sectorDao.findAllSector());
		map.put("single", tSingleselDao.findAllSingle());
		map.put("judge", tJudgeDao.findAllJudge());
		map.put("subjective", tSubjectiveDao.findAllSubjective());
		map.put("fill", tFillDao.findAllFill());
		return map;
	}

	@Override
	public void addTjudge(TJudge judge) {
		tJudgeDao.addJudge(judge);
	}

	@Override
	public void addTSinglesel(TSinglesel singlesel) {
		tSingleselDao.addSinglesel(singlesel);
	}

	@Override
	public void addTSubjective(TSubjective subjective) {
		tSubjectiveDao.addSubjective(subjective);
	}
	
	@Override
	public void deleteTJudge(Integer id) {
		tJudgeDao.deleteTjudge(id);
	}

	@Override
	public void deleteTSinglesel(Integer id) {
		tSingleselDao.deleteTSinglesel(id);

	}

	@Override
	public void deleteTSubjective(Integer id) {
		tSubjectiveDao.deleteTSubjective(id);

	}

	/**
	 * 查询所有符合条件的试题
	 */
	@Override
	public PageInfo findAll(int currentPage, String key, int type,
			Integer sectorId, String difficult, String teacherId) {
		int count = 0;
		PageInfo pageInfo = new PageInfo();
		switch (type) {
		case 1:
			count = tJudgeDao.countTJudge(key, teacherId).intValue();
			break;
		case 2:
			count = tSingleselDao.countTSinglesel(key, teacherId).intValue();
			break;
		case 3:
			count = tSubjectiveDao.countTSubjective(key, teacherId).intValue();
			break;
		case 4:
			count = judgeDao.countJudge(key, sectorId, difficult).intValue();
			break;
		case 5:
			count = singleselDao.countSinglesel(key, sectorId, difficult)
					.intValue();
			break;
		case 6:
			count = subjectiveDao.countSubjective(key, sectorId, difficult)
					.intValue();
			break;
		case 7:
			count = testPaperDao.countTestPaper().intValue();
			break;
		case 8:
			count = testPaperDao.countNoRelease(teacherId).intValue();
			break;
		case 9:
			count = tFillDao.countTFill(key, teacherId)
					.intValue();
			break;
		case 10:
			count = fillDao.countFill(key, sectorId, difficult)
					.intValue();
			break;
		}
		pageInfo.setTotalRecords(count);
		int totalPages = 0;
		if (count > 9) {
			totalPages = count % 10 == 0 ? (count / 10) : count / 10 + 1;
		} else {
			totalPages = 1;
		}
		pageInfo.setTotalPages(totalPages);
		pageInfo.setCurrentPage(currentPage > totalPages ? totalPages
				: currentPage);
		switch (type) {
		case 1:
			List<TJudge> list = tJudgeDao.getListByPage(currentPage, key,
					teacherId);
			for (TJudge judge : list) {
				judge.setQuestion(judge.getQuestion().length() > 40 ? judge
						.getQuestion().substring(0, 40) + "..." : judge
						.getQuestion());
				judge.setQuestion(judge.getQuestion().length() > 30 ? judge
						.getQuestion().substring(0, 30) + "..." : judge
						.getQuestion());
			}
			pageInfo.setData(list);
			break;
		case 2:
			List<TSinglesel> list1 = tSingleselDao.getListByPage(currentPage,
					key, teacherId);
			for (TSinglesel singlesel : list1) {
				singlesel
						.setQuestion(singlesel.getQuestion().length() > 40 ? singlesel
								.getQuestion().substring(0, 40) + "..."
								: singlesel.getQuestion());
				singlesel
						.setQuestion(singlesel.getQuestion().length() > 30 ? singlesel
								.getQuestion().substring(0, 30) + "..."
								: singlesel.getQuestion());
			}
			pageInfo.setData(list1);
			break;
		case 3:
			List<TSubjective> list2 = tSubjectiveDao.getListByPage(currentPage,
					key, teacherId);
			for (TSubjective subjective : list2) {
				subjective
						.setQuestion(subjective.getQuestion().length() > 40 ? subjective
								.getQuestion().substring(0, 40) + "..."
								: subjective.getQuestion());
				subjective
						.setQuestion(subjective.getQuestion().length() > 30 ? subjective
								.getQuestion().substring(0, 30) + "..."
								: subjective.getQuestion());
			}
			pageInfo.setData(list2);
			break;
		case 4:
			List<Judge> list3 = judgeDao.getListByPage(currentPage, key,
					sectorId, difficult);
			/*
			 * for (Judge judge : list3) {
			 * judge.setQuestion(judge.getQuestion().length() > 40 ? judge
			 * .getQuestion().substring(0, 40) + "..." : judge .getQuestion());
			 * judge.setQuestion(judge.getQuestion().length() > 30 ? judge
			 * .getQuestion().substring(0, 30) + "..." : judge .getQuestion());
			 * }
			 */
			pageInfo.setData(list3);
			break;
		case 5:
			List<Singlesel> list4 = singleselDao.getListByPage(currentPage,
					key, sectorId, difficult);
			/*
			 * for (Singlesel singlesel : list4) {
			 * singlesel.setQuestion(singlesel.getQuestion().length() > 40 ?
			 * singlesel .getQuestion().substring(0, 40) + "..." : singlesel
			 * .getQuestion());
			 * singlesel.setQuestion(singlesel.getQuestion().length() > 30 ?
			 * singlesel .getQuestion().substring(0, 30) + "..." : singlesel
			 * .getQuestion()); }
			 */
			pageInfo.setData(list4);
			break;
		case 6:
			List<Subjective> list5 = subjectiveDao.getListByPage(currentPage,
					key, sectorId, difficult);
			
			pageInfo.setData(list5);
			break;
		case 7:
			List<TestPaper> list6 = testPaperDao.getListByPage(currentPage);
			
			pageInfo.setData(list6);
			break;
		case 8:
			List<TestPaper> list7 = testPaperDao.getNoReleaseByPage(currentPage,teacherId);
			
			pageInfo.setData(list7);
			break;
		case 9:
			List<TFill> list8 = tFillDao.getListByPage(currentPage, key, teacherId);	
			pageInfo.setData(list8);
			break;
		case 10:
			List<Fill> list9 = fillDao.getListByPage(currentPage, key, sectorId, difficult);
				
			pageInfo.setData(list9);
				break;

		}
		return pageInfo;
	}

	@Override
	public TJudge getByIdTJudge(Integer id) {

		return tJudgeDao.getById(TJudge.class, id);
	}

	@Override
	public TSinglesel getByIdTSinglesel(Integer id) {

		return tSingleselDao.getById(TSinglesel.class, id);
	}

	@Override
	public TSubjective getByIdTSubjective(Integer id) {

		return tSubjectiveDao.getById(TSubjective.class, id);
	}

	@Override
	public void addJudge(Judge judge) {
		judgeDao.addJudge(judge);

	}

	@Override
	public void addSinglesel(Singlesel singlesel) {
		singleselDao.addSinglesel(singlesel);

	}

	@Override
	public void addSubjective(Subjective subjective) {
		subjectiveDao.addSubjective(subjective);

	}

	@Override
	public void addTFill(TFill fill) {
		tFillDao.addFill(fill);
		
	}

	@Override
	public void addFill(Fill fill) {
		fillDao.addFill(fill);
		
	}

	@Override
	public void deleteTFill(Integer id) {
		tFillDao.deleteTFill(id);
		
	}

	@Override
	public TFill getByIdTFill(Integer id) {
		
		return tFillDao.getById(TFill.class, id);
	}

}
