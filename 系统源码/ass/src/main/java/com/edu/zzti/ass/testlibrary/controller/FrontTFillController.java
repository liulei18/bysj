package com.edu.zzti.ass.testlibrary.controller;

import java.io.IOException;
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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.edu.zzti.ass.management.model.TTeacher;
import com.edu.zzti.ass.testlibrary.model.TFill;
import com.edu.zzti.ass.testlibrary.model.TSector;
import com.edu.zzti.ass.testlibrary.model.TUnit;
import com.edu.zzti.ass.testlibrary.service.ILibraryService;
import com.edu.zzti.ass.testlibrary.service.ITestPaperService;

@Controller
@RequestMapping("/manage/frontFill")
public class FrontTFillController {
	@Autowired
	private ITestPaperService testPaperService;
	@Autowired
	private ILibraryService libraryService;

	/**
	 * 访问addTfill页面 添加属性
	 * 
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addTfill", method = { RequestMethod.GET })
	public ModelAndView addTfill(Model model) {
		model.addAttribute("Tfill", new TFill());
		ModelAndView mav = new ModelAndView();
		List<TUnit> units = this.testPaperService.find().get("unit");
		mav.addObject("units", units);
		mav.setViewName("manage/addTfill");
		return mav;
	}

	/**
	 * 填空添加页面的提交
	 * 
	 * @param fill
	 * @return
	 */
	@RequestMapping(value = "addTfill", method = { RequestMethod.POST })
	public ModelAndView addTfill(TFill fill, HttpServletRequest request,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		TSector sector = new TSector();
		Integer sectorId = Integer.parseInt(request.getParameter("sectors"));
		sector.setId(sectorId);
		TTeacher teacher = new TTeacher();
		teacher = (TTeacher) httpSession.getAttribute("userInfo");
		fill.settSector(sector);
		fill.setTTeacher(teacher);
		testPaperService.addTFill(fill);
		mav.setViewName("manage/success");
		return mav;
	}
	/**
	 * 实现异步刷新 遍历章信息
	 * @param unitId
	 * @param response
	 */
	@RequestMapping(value = "checkChapter/{unitId}", method = { RequestMethod.GET })
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
	 * 异步刷新获得节
	 * @param chapterId
	 * @param response
	 */
	@RequestMapping(value = "checkSector/{chapterId}", method = { RequestMethod.GET })
	public void checkSector(@PathVariable Integer chapterId,
			HttpServletResponse response) {
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(this.libraryService.checkSector(chapterId));
		try {
			response.getWriter().print(jsonArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
