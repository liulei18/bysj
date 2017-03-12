package com.edu.zzti.ass.testlibrary.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
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
import com.edu.zzti.ass.server.model.Lexicon;
import com.edu.zzti.ass.testlibrary.model.Judge;
import com.edu.zzti.ass.testlibrary.model.TJudge;
import com.edu.zzti.ass.testlibrary.service.ITestPaperService;

@Controller
@RequestMapping("/manage/tJudge")
public class TJudgeController {
	@Autowired
	private ITestPaperService testPaperService;
	    
	/**
	 * 访问判断题临时表页面
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listTJudgeTemp", method = { RequestMethod.GET })
	public ModelAndView listTjudge(HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		TTeacher teacher = (TTeacher) httpSession.getAttribute("userInfo");
		PageInfo<TJudge> pageInfo;
		pageInfo = testPaperService.findAll(1, null, 1,null,null,teacher.getId());
		
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("manage/listTJudgeTemp");
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "listTJudgeTemp/{currentPage}", method = { RequestMethod.GET })
	public ModelAndView getAll(@PathVariable Integer currentPage,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		if (currentPage.equals("")) {
			currentPage = 1;
		}
		TTeacher teacher = (TTeacher) httpSession.getAttribute("userInfo");
		PageInfo<TJudge> pageInfo;

		pageInfo = testPaperService.findAll(currentPage, null, 1,null,null,teacher.getId());
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("manage/listTJudgeTemp");
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "listJudgeCheck/{currentPage}/{jkey}", method = { RequestMethod.GET })
	public ModelAndView getAllJudge(@PathVariable Integer currentPage,@PathVariable String jkey) {
		ModelAndView mav = new ModelAndView();
		/**
		 * 对jkey进行解码
		 */
		try {
			jkey = URLDecoder.decode(jkey, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (currentPage.equals("")) {
			currentPage = 1;
		} 
		PageInfo<Judge> pageInfo;

		pageInfo = testPaperService.findAll(currentPage, jkey, 4,null,null,null);
		int i = pageInfo.getTotalRecords();
		System.out.println( i);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("manage/judgeCheck");
		return mav;
	}
	/**
	 * 访问相似判断题型的页面
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "judgeCheck/{id}/{jkey}", method = { RequestMethod.GET })
	public ModelAndView check(@PathVariable Integer id,@PathVariable String jkey) {
		ModelAndView mav = new ModelAndView();
		/**
		 * 对jkey进行解码
		 */
		try {
			jkey = URLDecoder.decode(jkey, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		TJudge tJudge = testPaperService.getByIdTJudge(id);
		mav.addObject("id",id);
		mav.addObject("jkey", jkey);
		mav.addObject("tJudge", tJudge);
		PageInfo<Judge> pageInfo;
		pageInfo = testPaperService.findAll(1, jkey, 4,tJudge.gettSector().getId(),null,null);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("manage/judgeCheck");
		return mav;
	}
	
	@RequestMapping(value = "judgeCheck/{id}", method = { RequestMethod.GET })
	public ModelAndView delete(@PathVariable Integer id,HttpSession httpSession) {
		testPaperService.deleteTJudge(id);
		return listTjudge(httpSession);
	}
	/*
	 * 访问试题设置难度页面：
	 */
	@RequestMapping(value = "updateTjudge/{id}", method = { RequestMethod.GET })
	public ModelAndView insertJudge(@PathVariable Integer id) {
		TJudge tJudge = testPaperService.getByIdTJudge(id);	
		ModelAndView mav = new ModelAndView();
		mav.addObject("tJudge", tJudge);
		mav.setViewName("manage/updateTjudge");
		return mav;
	}

	
	@RequestMapping(value = "updateTjudge/{id}", method = { RequestMethod.POST })
	public ModelAndView addJudge(@PathVariable Integer id,Model model,Judge judge,HttpSession httpSession) {
		TJudge tJudge = testPaperService.getByIdTJudge(id);	
		judge.settSector(tJudge.gettSector());
		judge.setUseNum(0);
		testPaperService.deleteTJudge(id);
		model.addAttribute("tJudge", new TJudge());
		testPaperService.addJudge(judge);
		return listTjudge(httpSession);
	}
	

}
