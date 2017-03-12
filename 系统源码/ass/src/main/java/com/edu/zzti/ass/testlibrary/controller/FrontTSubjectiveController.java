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
import com.edu.zzti.ass.core.model.PageInfo;
import com.edu.zzti.ass.management.model.TTeacher;
import com.edu.zzti.ass.testlibrary.model.TJudge;
import com.edu.zzti.ass.testlibrary.model.TSector;
import com.edu.zzti.ass.testlibrary.model.TSubjective;
import com.edu.zzti.ass.testlibrary.model.TUnit;
import com.edu.zzti.ass.testlibrary.service.ILibraryService;
import com.edu.zzti.ass.testlibrary.service.ITestPaperService;

@Controller
@RequestMapping("/manage/frontSubjective")
public class FrontTSubjectiveController {
	@Autowired
	private ITestPaperService testPaperService;
	@Autowired
	private ILibraryService libraryService;
	/**
	 * 访问addTSubjective页面 添加属性
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addTSubjective", method = { RequestMethod.GET })
	public ModelAndView addTSubjective(Model model) {
		model.addAttribute("TSubjective", new TSubjective());
		ModelAndView mav = new ModelAndView();
		List<TUnit> units = this.testPaperService.find().get("unit");
		mav.addObject("units", units);
		mav.setViewName("manage/addTSubjective");
		return mav;
	}
	/**
	 * 主观题页面的提交
	 * 
	 * @param judge
	 * @return
	 */
	@RequestMapping(value = "addTSubjective", method = { RequestMethod.POST })
	public ModelAndView addTSubjective(TSubjective subjective,HttpServletRequest request,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		TSector sector = new TSector();
		Integer sectorId = Integer.parseInt(request.getParameter("sectors"));
		sector.setId(sectorId);
		subjective.settSector(sector);
		TTeacher teacher = new TTeacher();
		teacher = (TTeacher) httpSession.getAttribute("userInfo");
		subjective.setTTeacher(teacher);
		testPaperService.addTSubjective(subjective);
		mav.setViewName("manage/success");
		return mav;
	}
	@RequestMapping(value = "checkChapter/{unitId}", method = { RequestMethod.GET })
	public void checkChapter(@PathVariable Integer unitId,
			HttpServletResponse response) {
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(this.libraryService.checkChapter(unitId));
		System.out.println(jsonArray);
		try {
			response.getWriter().print(jsonArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "checkSector/{chapterId}", method = { RequestMethod.GET })
	public void checkSector(@PathVariable Integer chapterId,
			HttpServletResponse response) {
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(this.libraryService.checkSector(chapterId));
		System.out.println(jsonArray);
		try {
			response.getWriter().print(jsonArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
