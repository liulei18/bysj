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
import com.edu.zzti.ass.testlibrary.model.Fill;
import com.edu.zzti.ass.testlibrary.model.Judge;
import com.edu.zzti.ass.testlibrary.model.TFill;
import com.edu.zzti.ass.testlibrary.model.TJudge;
import com.edu.zzti.ass.testlibrary.service.ITestPaperService;

@Controller
@RequestMapping("/manage/tFill")
public class TFillController {
	@Autowired
	private ITestPaperService testPaperService;
	    
	/**
	 * 访问填空题临时表页面
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listTFillTemp", method = { RequestMethod.GET })
	public ModelAndView listTfill(HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		TTeacher teacher = (TTeacher) httpSession.getAttribute("userInfo");
		PageInfo<TFill> pageInfo;
		pageInfo = testPaperService.findAll(1, null, 9,null,null,teacher.getId());
		
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("manage/listTFillTemp");
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "listTFillTemp/{currentPage}", method = { RequestMethod.GET })
	public ModelAndView getAll(@PathVariable Integer currentPage,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		if (currentPage.equals("")) {
			currentPage = 1;
		}
		TTeacher teacher = (TTeacher) httpSession.getAttribute("userInfo");
		PageInfo<TFill> pageInfo;

		pageInfo = testPaperService.findAll(currentPage, null, 9,null,null,teacher.getId());
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("manage/listTFillTemp");
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "listFillCheck/{currentPage}/{fkey}", method = { RequestMethod.GET })
	public ModelAndView getAllFill(@PathVariable Integer currentPage,@PathVariable String fkey) {
		ModelAndView mav = new ModelAndView();
		/**
		 * 对fkey进行解码
		 */
		try {
			fkey = URLDecoder.decode(fkey, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (currentPage.equals("")) {
			currentPage = 1;
		} 
		PageInfo<Fill> pageInfo;

		pageInfo = testPaperService.findAll(currentPage, fkey, 10,null,null,null);
		int i = pageInfo.getTotalRecords();
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("manage/fillCheck");
		return mav;
	}
	/**
	 * 访问相似判断题型的页面
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "fillCheck/{id}/{fkey}", method = { RequestMethod.GET })
	public ModelAndView check(@PathVariable Integer id,@PathVariable String fkey) {
		ModelAndView mav = new ModelAndView();
		/**
		 * 对fkey进行解码
		 */
		try {
			fkey = URLDecoder.decode(fkey, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		TFill tFill = testPaperService.getByIdTFill(id);
		mav.addObject("id",id);
		mav.addObject("fkey", fkey);
		mav.addObject("tJudge", tFill);
		PageInfo<Fill> pageInfo;
		pageInfo = testPaperService.findAll(1, fkey, 10,tFill.gettSector().getId(),null,null);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("manage/fillCheck");
		return mav;
	}
	
	@RequestMapping(value = "fillCheck/{id}", method = { RequestMethod.GET })
	public ModelAndView delete(@PathVariable Integer id,HttpSession httpSession) {
		testPaperService.deleteTFill(id);
		return listTfill(httpSession);
	}
	/*
	 * 访问试题设置难度页面：
	 */
	@RequestMapping(value = "updateTfill/{id}", method = { RequestMethod.GET })
	public ModelAndView insertFill(@PathVariable Integer id) {
		TFill tFill = testPaperService.getByIdTFill(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("tFill", tFill);
		mav.setViewName("manage/updateTFill");
		return mav;
	}

	
	@RequestMapping(value = "updateTFill/{id}", method = { RequestMethod.POST })
	public ModelAndView addFill(@PathVariable Integer id,Model model,Fill fill,HttpSession httpSession) {
		TFill tFill = testPaperService.getByIdTFill(id);	
		fill.settSector(tFill.gettSector());
		fill.setUseNum(0);
		testPaperService.deleteTFill(id);
		model.addAttribute("tFill", new TFill());
		testPaperService.addFill(fill);
		return listTfill(httpSession);
	}
	

}
