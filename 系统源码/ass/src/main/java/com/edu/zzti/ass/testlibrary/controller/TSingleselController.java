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
import com.edu.zzti.ass.testlibrary.model.Singlesel;
import com.edu.zzti.ass.testlibrary.model.TSinglesel;
import com.edu.zzti.ass.testlibrary.service.ITestPaperService;

@Controller
@RequestMapping("/manage/tSinglesel")
public class TSingleselController {
	@Autowired
	private ITestPaperService testPaperService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listTSingleselTemp", method = { RequestMethod.GET })
	public ModelAndView listTsinglesel(HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		TTeacher teacher = (TTeacher) httpSession.getAttribute("userInfo");
		PageInfo<TSinglesel> pageInfo;
		pageInfo = testPaperService.findAll(1, null, 2,null,null,teacher.getId());
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("manage/listTSingleselTemp");
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "listTSingleselTemp/{currentPage}", method = { RequestMethod.GET })
	public ModelAndView getAll(@PathVariable Integer currentPage,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		if (currentPage.equals("")) {
			currentPage = 1;
		} 
		TTeacher teacher = (TTeacher) httpSession.getAttribute("userInfo");
		PageInfo<TSinglesel> pageInfo;

		pageInfo = testPaperService.findAll(currentPage, null, 2,null,null,teacher.getId());
		
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("manage/listTSingleselTemp");
		return mav;
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "singleselCheck/{id}/{skey}", method = { RequestMethod.GET })
	public ModelAndView check(@PathVariable Integer id,@PathVariable String skey,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		/**
		 * 对jkey进行解码
		 */
		try {
			skey = URLDecoder.decode(skey, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		TSinglesel tSinglesel = testPaperService.getByIdTSinglesel(id);
		mav.addObject("id",id);
		mav.addObject("skey", skey);
		System.out.println(id+"----"+skey);
		mav.addObject("tSinglesel", tSinglesel);
		PageInfo<Singlesel> pageInfo;
		pageInfo = testPaperService.findAll(1, skey, 5,tSinglesel.gettSector().getId(),null,null);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("manage/singleselCheck");
		return mav;
	}
	
	@RequestMapping(value = "singleselCheck/{id}", method = { RequestMethod.GET })
	public ModelAndView delete(@PathVariable Integer id,HttpSession httpSession) {
		testPaperService.deleteTSinglesel(id);
		return listTsinglesel(httpSession);
	}
	
	/*
	 * 访问单选题设置难度页面：
	 */
	@RequestMapping(value = "updateTSinglesel/{id}", method = { RequestMethod.GET })
	public ModelAndView insertSinglesel(@PathVariable Integer id) {
		TSinglesel tSinglesel = testPaperService.getByIdTSinglesel(id);	
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("tSinglesel", tSinglesel);
		mav.setViewName("manage/updateTSinglesel");
		return mav;
	}

	
	@RequestMapping(value = "updateTSinglesel/{id}", method = { RequestMethod.POST })
	public ModelAndView addSinglesel(@PathVariable Integer id,Model model,Singlesel singlesel,HttpSession httpSession) {
		TSinglesel tSinglesel = testPaperService.getByIdTSinglesel(id);	
		singlesel.settSector(tSinglesel.gettSector());
		singlesel.setUseNum(0);
		testPaperService.deleteTSinglesel(id);
		model.addAttribute("tSinglesel", new TSinglesel());
		testPaperService.addSinglesel(singlesel);
		return listTsinglesel(httpSession);
	}
	
	
	

}
