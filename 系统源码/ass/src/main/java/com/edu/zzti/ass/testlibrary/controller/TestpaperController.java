package com.edu.zzti.ass.testlibrary.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.edu.zzti.ass.core.model.PageInfo;
import com.edu.zzti.ass.management.model.TTeacher;
import com.edu.zzti.ass.onlinestudy.model.TestPaper;
import com.edu.zzti.ass.testlibrary.model.Fill;
import com.edu.zzti.ass.testlibrary.model.Judge;
import com.edu.zzti.ass.testlibrary.model.Singlesel;
import com.edu.zzti.ass.testlibrary.model.Subjective;
import com.edu.zzti.ass.testlibrary.model.TUnit;
import com.edu.zzti.ass.testlibrary.service.ILibraryService;
import com.edu.zzti.ass.testlibrary.service.ITestPaperService;

@Controller
@RequestMapping("/manage/testPaper")
public class TestpaperController {
	@Autowired
	private ITestPaperService testPaperService;
	@Autowired
	private ILibraryService libraryService;
	
	/**
	 * 
	 * @param 访问人工组卷页面
	 * @return
	 */
	@RequestMapping(value = "/library1", method = { RequestMethod.GET })
	public ModelAndView libraryTest() {
		ModelAndView mav = new ModelAndView();
		List<TUnit> units = testPaperService.find().get("unit");
		mav.addObject("units", units);
		mav.setViewName("manage/library1");
		return mav;
	}

	@RequestMapping(value = "/removeTest", method = { RequestMethod.GET })
	public ModelAndView library(HttpSession httpSession) {

		httpSession.removeAttribute("judgeId");
		httpSession.removeAttribute("singleselId");
		httpSession.removeAttribute("subjectiveId");
		httpSession.removeAttribute("count");
		return libraryTest();
	}
	/**
	 * 简单出卷
	 * @param httpSession
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/test2", method = { RequestMethod.GET })
	public ModelAndView Basketlist(HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
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
		List<Judge> judge = new ArrayList<Judge>();
		for (Integer judId : judList) {
			judge.add(libraryService.getByJudgeId(judId));
		}
		List<Singlesel> singlesel = new ArrayList<Singlesel>();
		for (Integer sinId : sinList) {
			singlesel.add(libraryService.getBySingleId(sinId));
		}
		List<Subjective> subjective = new ArrayList<Subjective>();
		for (Integer subId : subList) {
			subjective.add(libraryService.getBySubjecId(subId));
		}
		List<Fill> fill = new ArrayList<Fill>();
		for (Integer fillId : fillList) {
			fill.add(libraryService.getByFillId(fillId));
		}
		mav.addObject("judge", judge);
		mav.addObject("singlesel", singlesel);
		mav.addObject("subjective", subjective);
		mav.addObject("fill", fill);
		mav.setViewName("test2");
		return mav;
	}

	/**
	 * ajax异步刷新 得到章
	 * 
	 * @param unitId
	 * @param response
	 */
	@RequestMapping(value = "tunits/{unitId}", method = { RequestMethod.GET })
	@ResponseBody
	public void checkChapter(@PathVariable Integer unitId,
			HttpServletResponse response) {
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(this.libraryService.checkChapter(unitId));
		try {
			response.getWriter().print(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ajax实现 异步刷新 得到节
	 * 
	 * @param chapterId
	 * @param response
	 */
	@RequestMapping(value = "tchapters/{chapterId}", method = { RequestMethod.GET })
	@ResponseBody
	public void checkSector(@PathVariable Integer chapterId,
			HttpServletResponse response) {
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(this.libraryService.checkSector(chapterId));
		try {
			response.getWriter().print(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ajax 实现异步刷新 试题页面
	 * @param sectorId
	 *            单元id
	 * @param key
	 *            知识点
	 * @param difficult
	 *            难易程度
	 * @param response
	 *            请求返回方式
	 * @param httpSession
	 *            session数据
	 * @param currentPage
	 *            页数
	 * @param flag
	 *            标识是否加入试题篮
	 *            
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "listTest/{sectorId}/{key}/{difficult}/{currentPage}", method = { RequestMethod.POST })
	public List<Object> checkTestList(@PathVariable Integer sectorId,
			@PathVariable String key, @PathVariable String difficult,
			HttpServletResponse response, HttpSession httpSession,
			@PathVariable Integer currentPage) {
		
		Integer count = (Integer) httpSession.getAttribute("count");//试题篮数量
		if(count==null){
			count=0;
			httpSession.setAttribute("count", count);
		}
		List<Integer> judgeList = (List<Integer>) httpSession
				.getAttribute("judgeId");
		if (judgeList == null) {
			judgeList = new ArrayList<Integer>();
		}
		List<Integer> singleselList = (List<Integer>) httpSession
				.getAttribute("singleselId");
		if (singleselList == null) {
			singleselList = new ArrayList<Integer>();
		}
		List<Integer> subjectiveList = (List<Integer>) httpSession
				.getAttribute("subjectiveId");
		if (subjectiveList == null) {
			subjectiveList = new ArrayList<Integer>();
		}
		List<Integer> fillList = (List<Integer>) httpSession
				.getAttribute("fillId");
		if (fillList == null) {
			fillList = new ArrayList<Integer>();
		}
		try {
			key = URLDecoder.decode(key, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpSession.getAttribute("judgeId");
		PageInfo<Judge> pageInfo;
		pageInfo = testPaperService.findAll(currentPage, key, 4, sectorId,
				difficult, null);
		for (Judge judge : pageInfo.getData()) {
			if (judgeList.contains(judge.getId())) {
				judge.setFlag(1);
			}
		}
		PageInfo<Singlesel> pageInfo1;
		pageInfo1 = testPaperService.findAll(currentPage, key, 5, sectorId,
				difficult, null);
		for (Singlesel singlesel : pageInfo1.getData()) {
			if (singleselList.contains(singlesel.getId())) {
				singlesel.setFlag(1);
			}
		}

		PageInfo<Subjective> pageInfo2;
		pageInfo2 = testPaperService.findAll(currentPage, key, 6, sectorId,
				difficult, null);
		for (Subjective subjective : pageInfo2.getData()) {
			if (subjectiveList.contains(subjective.getId())) {
				subjective.setFlag(1);
			}
		}
		PageInfo<Fill> pageInfo3;
		pageInfo3 = testPaperService.findAll(currentPage, key, 10, sectorId,
				difficult, null);
		for (Fill fill : pageInfo3.getData()) {
			if (fillList.contains(fill.getId())) {
				fill.setFlag(1);
			}
		}

		List<Object> jsonArray = new ArrayList<>();
		jsonArray.add(pageInfo);
		jsonArray.add(pageInfo1);
		jsonArray.add(pageInfo2);
		jsonArray.add(pageInfo3);
		jsonArray.add(judgeList);
		jsonArray.add(singleselList);
		jsonArray.add(subjectiveList);
		jsonArray.add(fillList);
		

		return 	jsonArray;
	}

	/**
	 * 添加试题篮
	 * 
	 * @param Id
	 *            试题Id
	 * @param type
	 *            标识试题类型
	 * @param response
	 * @param httpSession
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "addSession/{Id}/{type}", method = { RequestMethod.POST })
	public Integer addBasket(@PathVariable Integer Id, @PathVariable Integer type,
			HttpServletResponse response, HttpSession httpSession) {
			
		if (type == 0) {
			List<Integer> judgeList = (List<Integer>) httpSession
					.getAttribute("judgeId");
			if (judgeList == null) {
				judgeList = new ArrayList<Integer>();
			}
			judgeList.add(Id);
			httpSession.setAttribute("judgeId", judgeList);
		}
		if (type == 1) {
			List<Integer> singleselList = (List<Integer>) httpSession
					.getAttribute("singleselId");
			if (singleselList == null) {
				singleselList = new ArrayList<Integer>();
			}
			singleselList.add(Id);
			httpSession.setAttribute("singleselId", singleselList);
		}
		if (type == 2) {
			List<Integer> subjectiveList = (List<Integer>) httpSession
					.getAttribute("subjectiveId");
			if (subjectiveList == null) {
				subjectiveList = new ArrayList<Integer>();
			}
			subjectiveList.add(Id);
			httpSession.setAttribute("subjectiveId", subjectiveList);
		}
		if (type == 3) {
			List<Integer> fillList = (List<Integer>) httpSession
					.getAttribute("fillId");
			if (fillList == null) {
				fillList = new ArrayList<Integer>();
			}
			fillList.add(Id);
			httpSession.setAttribute("fillId", fillList);
		}
		Integer count = (Integer) httpSession.getAttribute("count");
		count++;
		httpSession.setAttribute("count", count);
		return count;
	}

	/**
	 * 移除试题篮
	 * 
	 * @param Id
	 * @param type
	 * @param response
	 * @param httpSession
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "removeSession/{Id}/{type}", method = { RequestMethod.POST })
	public Integer removeBasket(@PathVariable Integer Id,
			@PathVariable Integer type,
			HttpSession httpSession) {
		if (type == 0) {
			List<Integer> judgeList = (List<Integer>) httpSession
					.getAttribute("judgeId");
			judgeList.remove(Id);
			httpSession.setAttribute("judgeId", judgeList);
		}
		if (type == 1) {
			List<Integer> singleselList = (List<Integer>) httpSession
					.getAttribute("singleselId");
			singleselList.remove(Id);
			httpSession.setAttribute("singleselId", singleselList);
		}
		if (type == 2) {
			List<Integer> subjectiveList = (List<Integer>) httpSession
					.getAttribute("subjectiveId");
			subjectiveList.remove(Id);
			httpSession.setAttribute("subjectiveId", subjectiveList);
		}
		if (type == 3) {
			List<Integer> fillList = (List<Integer>) httpSession
					.getAttribute("fillId");
		
			fillList.remove(Id);
			httpSession.setAttribute("fillId", fillList);
		}
		Integer count = (Integer) httpSession.getAttribute("count");
		count--;
		httpSession.setAttribute("count", count);
		return count;
	}
	/**
	 * 访问试题篮页面
	 * @param httpSession
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/basket", method = { RequestMethod.GET })
	public ModelAndView removeBasket(HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
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
		List<Judge> judge = new ArrayList<Judge>();
		for (Integer judId : judList) {
			judge.add(libraryService.getByJudgeId(judId));
		}
		List<Singlesel> singlesel = new ArrayList<Singlesel>();
		for (Integer sinId : sinList) {
			singlesel.add(libraryService.getBySingleId(sinId));
		}
		List<Subjective> subjective = new ArrayList<Subjective>();
		for (Integer subId : subList) {
			subjective.add(libraryService.getBySubjecId(subId));
		}
		List<Fill> fill = new ArrayList<Fill>();
		for (Integer fillId : fillList) {
			fill.add(libraryService.getByFillId(fillId));
		}
		mav.addObject("judge", judge);
		mav.addObject("singlesel", singlesel);
		mav.addObject("subjective", subjective);
		mav.addObject("fill", fill);
		mav.setViewName("manage/basket");
		return mav;
	}
	/**
	 * 试题篮页面实现删除试题
	 * @param Id
	 * @param type
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "basket/{Id}/{type}", method = { RequestMethod.GET })
	public ModelAndView basket(@PathVariable Integer Id,@PathVariable Integer type,
			HttpSession httpSession) {
		removeBasket(Id,type,httpSession);
		return removeBasket(httpSession);
		
	}
	/**
	 * 人工生成试卷，存入数据库中
	 * @param paperName 试题名称
	 * @param httpSession 
	 * @return
	 */
	@RequestMapping(value = "createTest/{paperName}", method = { RequestMethod.GET })
	public ModelAndView createTest(@PathVariable String paperName,HttpSession httpSession) {
		try {
			paperName = URLDecoder.decode(paperName, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		libraryService.addTest(paperName, httpSession);
		return library(httpSession);
		
	}
}
