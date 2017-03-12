package com.edu.zzti.ass.testlibrary.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
import com.edu.zzti.ass.testlibrary.model.Subjective;
import com.edu.zzti.ass.testlibrary.model.TSubjective;
import com.edu.zzti.ass.testlibrary.service.ITestPaperService;

@Controller
@RequestMapping("/manage/tSubjective")
public class TSubjectiveController {
	@Autowired
	private ITestPaperService testPaperService;

	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listTSubjectiveTemp", method = { RequestMethod.GET })
	public ModelAndView listTSubjective(HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		TTeacher teacher = (TTeacher) httpSession.getAttribute("userInfo");
		PageInfo<TSubjective> pageInfo;
		pageInfo = testPaperService.findAll(1, null, 3,null,null,teacher.getId());
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("manage/listTSubjectiveTemp");
		return mav;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "listTSubjectiveTemp/{currentPage}", method = { RequestMethod.GET })
	public ModelAndView getAll(@PathVariable Integer currentPage,HttpSession httpSession) {
		TTeacher teacher = (TTeacher) httpSession.getAttribute("userInfo");
		ModelAndView mav = new ModelAndView();
		if (currentPage.equals("")) {
			currentPage = 1;
		} 
		PageInfo<TSubjective> pageInfo;

		pageInfo = testPaperService.findAll(currentPage, null, 3,null,null,teacher.getId());
		
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("manage/listTSubjectiveTemp");
		return mav;
	}
	
	

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "subjectiveCheck/{id}/{skey}", method = { RequestMethod.GET })
	public ModelAndView check(@PathVariable Integer id,@PathVariable String skey) {
		ModelAndView mav = new ModelAndView();
		/**
		 * 对jkey进行解码
		 */
		try {
			skey = URLDecoder.decode(skey, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		TSubjective tSubjective = testPaperService.getByIdTSubjective(id);
		mav.addObject("id",id);
		mav.addObject("skey", skey);
		mav.addObject("tSubjective", tSubjective);
		PageInfo<TSubjective> pageInfo;
		pageInfo = testPaperService.findAll(1, skey, 6,tSubjective.gettSector().getId(),null,null);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("manage/subjectiveCheck");
		return mav;
	}
	
	@RequestMapping(value = "subjectiveCheck/{id}", method = { RequestMethod.GET })
	public ModelAndView delete(@PathVariable Integer id,HttpSession httpSession) {
		testPaperService.deleteTSubjective(id);
		return listTSubjective(httpSession);
	}
	
	
	
	/*
	 * 访问简答题设置难度页面：
	 */
	@RequestMapping(value = "updateTSubjective/{id}", method = { RequestMethod.GET })
	public ModelAndView insertSubjective(@PathVariable Integer id) {
		TSubjective tSubjective = testPaperService.getByIdTSubjective(id);	
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("tSubjective", tSubjective);
		mav.setViewName("manage/updateTSubjective");
		return mav;
	}
	@RequestMapping(value = "updateTSubjective/{id}", method = { RequestMethod.POST })
	public ModelAndView addSubjective(@PathVariable Integer id,Model model,Subjective subjective,HttpSession httpSession) {
		TSubjective tSubjective = testPaperService.getByIdTSubjective(id);	
		subjective.settSector(tSubjective.gettSector());
		subjective.setUseNum(0);
		testPaperService.deleteTSubjective(id);
		model.addAttribute("tSubjective", new TSubjective());
		testPaperService.addSubjective(subjective);
		return listTSubjective(httpSession);
	}
	
}
