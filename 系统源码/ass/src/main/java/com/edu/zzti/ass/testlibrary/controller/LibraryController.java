package com.edu.zzti.ass.testlibrary.controller;


import static org.junit.Assert.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;














import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;














import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;














import com.alibaba.fastjson.JSONArray;
import com.edu.zzti.ass.core.model.PageInfo;
import com.edu.zzti.ass.management.model.TStudent;
import com.edu.zzti.ass.management.model.TTeacher;
import com.edu.zzti.ass.onlinestudy.model.TestPaper;
import com.edu.zzti.ass.testlibrary.model.Fill;
import com.edu.zzti.ass.testlibrary.model.Judge;
import com.edu.zzti.ass.testlibrary.model.Singlesel;
import com.edu.zzti.ass.testlibrary.model.Subjective;
import com.edu.zzti.ass.testlibrary.model.TChapter;
import com.edu.zzti.ass.testlibrary.model.TJudge;
import com.edu.zzti.ass.testlibrary.model.TSector;
import com.edu.zzti.ass.testlibrary.model.TUnit;
import com.edu.zzti.ass.testlibrary.service.IAutoTestService;
import com.edu.zzti.ass.testlibrary.service.ILibraryService;
import com.edu.zzti.ass.testlibrary.service.ITestPaperService;

@Controller
@RequestMapping("/front")
public class LibraryController {
	@Autowired
	private ITestPaperService testPaperService;
	@Autowired
	private ILibraryService libraryService;
	@Autowired
	private IAutoTestService autoTestService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/library", method = { RequestMethod.GET })
	public ModelAndView library(Model model,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		PageInfo<TestPaper> pageInfo;
		pageInfo = testPaperService.findAll(1, null, 7, null,
				null, null);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("library");
		return mav;
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listPageTest", method = { RequestMethod.GET })
	public ModelAndView library(HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		List<TUnit> units = testPaperService.find().get("unit");
		mav.addObject("units", units);
		mav.setViewName("listPageTest");
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
	@RequestMapping(value = "listPageTest/{sectorId}/{currentPage}", method = { RequestMethod.POST })
	public List<Object> checkTestList(@PathVariable Integer sectorId,
			HttpServletResponse response, HttpSession httpSession,
			@PathVariable Integer currentPage) {
		PageInfo<Judge> pageInfo;
		pageInfo = testPaperService.findAll(currentPage, "null", 4, sectorId,
				null, null);
		
		PageInfo<Singlesel> pageInfo1;
		pageInfo1 = testPaperService.findAll(currentPage, "null", 5, sectorId,
				null, null);

		PageInfo<Subjective> pageInfo2;
		pageInfo2 = testPaperService.findAll(currentPage, "null", 6, sectorId,
				null, null);
		PageInfo<Fill> pageInfo3;
		pageInfo3 = testPaperService.findAll(currentPage, "null", 10, sectorId,
				null, null);
		
		List<Object> jsonArray = new ArrayList<>();
		jsonArray.add(pageInfo);
		jsonArray.add(pageInfo1);
		jsonArray.add(pageInfo2);
		jsonArray.add(pageInfo3);
		return 	jsonArray;
	}
	
	@RequestMapping(value = "downLoad/{testId}", method = { RequestMethod.GET })
	public ModelAndView upload(@PathVariable Integer testId,Model model,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		TStudent student = (TStudent) httpSession.getAttribute("student");
		if (student ==null) {
			mav.addObject("info", "对不起，请先登录系统");
			mav.setViewName("webInfo");
			return mav;
		} else {
			TestPaper test = autoTestService.getPaper(testId);
			mav.addObject("test", test);
			mav.setViewName("downLoadTest");
			return mav;
		}
		
	}
	/**
	 * 试卷列表分页
	 * @param currentPage
	 * @param httpSession
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "libraryTest/{currentPage}", method = { RequestMethod.GET })
	public ModelAndView getAll(@PathVariable Integer currentPage,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		PageInfo<TestPaper> pageInfo;
		pageInfo = testPaperService.findAll(currentPage, null, 7, null,
				null, null);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("library");
		
		
		
		return mav;
	}
	
	
	
	
}
