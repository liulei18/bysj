package com.edu.zzti.ass.testlibrary.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.edu.zzti.ass.onlinestudy.model.TestPaper;
import com.edu.zzti.ass.testlibrary.model.TUnit;
import com.edu.zzti.ass.testlibrary.service.IAutoTestService;
import com.edu.zzti.ass.testlibrary.service.ILibraryService;
import com.edu.zzti.ass.testlibrary.service.ITestPaperService;

@Controller
@RequestMapping("/manage/autoPaper")
public class AutoPaperController {
	@Autowired
	private ITestPaperService testPaperService;
	@Autowired
	private ILibraryService libraryService;
	@Autowired
	private IAutoTestService autoTestService;

	/**
	 * 访问自动组卷页面 
	 * 
	 * @param model
	 * @return
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "autoTest", method = { RequestMethod.GET })
	public ModelAndView autoTest(Model model) {
		ModelAndView mav = new ModelAndView();
		List<TUnit> units = testPaperService.find().get("unit");
		mav.addObject("units", units);
		mav.setViewName("manage/autoTest");
		return mav;
	}

	@RequestMapping(value = "autoTest/{unitId}", method = { RequestMethod.GET })
	@ResponseBody
	public void checkChapter(@PathVariable Integer unitId,
			HttpServletResponse response,HttpSession httpSession) {
		httpSession.setAttribute("unitId", unitId);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(this.libraryService.checkChapter(unitId));
		try {
			response.getWriter().print(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "autoTest", method = { RequestMethod.POST })
	public void addSession( HttpServletRequest request,HttpSession httpSession,HttpServletResponse response) {
		String chapterIds = request.getParameter("chapterIds");
		String paperName = request.getParameter("paperName");
		Integer jEasy = Integer.valueOf(request.getParameter("jEasy"));
		Integer jMid = Integer.valueOf(request.getParameter("jMid"));
		Integer jDif = Integer.valueOf(request.getParameter("jDif"));
		Integer siEasy = Integer.valueOf(request.getParameter("siEasy"));
		Integer siMid = Integer.valueOf(request.getParameter("siMid"));
		Integer siDif = Integer.valueOf(request.getParameter("siDif"));
		Integer suEasy = Integer.valueOf(request.getParameter("suEasy"));
		Integer suMid = Integer.valueOf(request.getParameter("suMid"));
		Integer suDif = Integer.valueOf(request.getParameter("suDif"));
		Integer fEasy = Integer.valueOf(request.getParameter("fEasy"));
		Integer fMid = Integer.valueOf(request.getParameter("fMid"));
		Integer fDif = Integer.valueOf(request.getParameter("fDif"));
		String [] chapterIdArray = chapterIds.split(",");
		List<Integer> chapterIdList =new  ArrayList<Integer>();
		for(String id : chapterIdArray){
			chapterIdList.add(Integer.valueOf(id));
		}
		TestPaper test = autoTestService.addTest(paperName, jEasy, jMid, jDif, siEasy, siMid,siDif, suEasy,suMid,suDif,fEasy, fMid, fDif,chapterIdList,httpSession);
		Integer testId = test.getId();
		JSONArray jsonArray =  new JSONArray();
		jsonArray.add(testId);
		try {
			response.getWriter().print(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@RequestMapping(value = "autoTestInfo/{testId}", method = { RequestMethod.GET })
	public ModelAndView autoTestInfo(Model model,@PathVariable Integer testId) {
		ModelAndView mav = new ModelAndView();
		TestPaper test = autoTestService.getPaper(testId);
		mav.addObject("test", test);
		mav.setViewName("manage/autoTestInfo");
		return mav;
	}
	/**
	 * 弃用试卷
	 * @param testId
	 * @return
	 */
	@RequestMapping(value = "delTest/{testId}", method = { RequestMethod.GET })
	public ModelAndView delTest(@PathVariable Integer testId) {
		ModelAndView mav = new ModelAndView();
		autoTestService.delTest(testId);
		mav.setViewName("redirect:/manage/autoPaper/autoTest");
		return mav;
	}
	
	/*@RequestMapping(value = "del/{Id}", method = { RequestMethod.GET })
	public ModelAndView del(@PathVariable Integer Id) {
		ModelAndView mav = new ModelAndView();
		autoTestService.del(Id);
		mav.setViewName("manage/autoTest");
		return mav;
	}*/
	
}
