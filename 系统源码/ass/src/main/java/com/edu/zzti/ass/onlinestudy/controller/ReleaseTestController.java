package com.edu.zzti.ass.onlinestudy.controller;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.edu.zzti.ass.core.model.PageInfo;
import com.edu.zzti.ass.management.model.TTeacher;
import com.edu.zzti.ass.onlinestudy.model.TestPaper;
import com.edu.zzti.ass.onlinestudy.service.IPaperService;
import com.edu.zzti.ass.testlibrary.service.IAutoTestService;
import com.edu.zzti.ass.testlibrary.service.ILibraryService;
import com.edu.zzti.ass.testlibrary.service.ITestPaperService;


@Controller
@RequestMapping("/manage/add")
public class ReleaseTestController {
	@Autowired
	private ITestPaperService testPaperService;
	@Autowired
	private ILibraryService libraryService;
	@Autowired
	private IAutoTestService autoTestService;
	@Autowired
	private IPaperService paperService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listTestPaper", method = { RequestMethod.GET })
	public ModelAndView library(Model model,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		TTeacher teacher =(TTeacher) httpSession.getAttribute("userInfo");
		PageInfo<TestPaper> pageInfo;
		pageInfo = testPaperService.findAll(1, null, 8, null,
				null, teacher.getId());
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("manage/listTestPaper");
		return mav;
	}
	/**
	 * 分页
	 * @param currentPage  页数
	 * @param httpSession 存放教师信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "listTestPaper/{currentPage}", method = { RequestMethod.GET })
	public ModelAndView getAll(@PathVariable Integer currentPage,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		TTeacher teacher =(TTeacher) httpSession.getAttribute("userInfo");
		if (currentPage.equals("")) {
			currentPage = 1;
		}
		PageInfo<TestPaper> pageInfo;
		pageInfo = testPaperService.findAll(currentPage, null, 8, null,
				null, teacher.getId());
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("manage/listTestPaper");
		return mav;
	}
	
	
	/**
	 *
	 * @param httpSession
	 * @param testId
	 * @return
	 */
	@RequestMapping(value = "release/{testId}", method = { RequestMethod.GET })
	public ModelAndView library(HttpSession httpSession,@PathVariable Integer testId) {
		//更新发布
		paperService.updateSql(testId);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/manage/add/listTestPaper");
		return mav;
	}
	

	
}
