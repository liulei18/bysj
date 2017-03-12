package com.edu.zzti.ass.onlinestudy.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.edu.zzti.ass.management.model.TFile;
import com.edu.zzti.ass.management.model.TFileShow;
import com.edu.zzti.ass.management.model.TLearningRecord;
import com.edu.zzti.ass.management.model.TStudent;
import com.edu.zzti.ass.management.model.TTeacher;
import com.edu.zzti.ass.management.service.ILearningRecordService;
import com.edu.zzti.ass.management.service.ITeacherService;
import com.edu.zzti.ass.onlinestudy.service.ILearningService;
import com.edu.zzti.ass.testlibrary.model.TUnit;

@Controller
@RequestMapping("/")
public class QCourseController {
	@Autowired
	private ITeacherService teacherService;
	
	@Autowired
	private ILearningService learningService;
	
	@Autowired
	private ILearningRecordService learningRecordService;
 
	@RequestMapping(value = "course", method = { RequestMethod.GET })
	public String course(Model model) {
		return "course";  //返回视频页面
	}

	/**
	 * 访问在线学习界面 展示所有单元
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "learning", method = { RequestMethod.GET })
	public ModelAndView learning(Model model, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		TTeacher teacher = (TTeacher) httpSession.getAttribute("teacher");
		if (teacher == null) {
			TStudent obj = (TStudent) httpSession.getAttribute("student");
			if(obj!=null){
				mav.addObject("stuId", obj.getId());
				mav.addObject("claName", obj.getTClass().getName());
			}
		}
		List<TUnit> units = learningService.getAllUnits();
		mav.addObject("units", units);
		
		mav.setViewName("learning");  
		
		return mav;
	}



	@RequestMapping(value = "library", method = { RequestMethod.GET })
	public String library(Model model) {
		return "library";
	}

	@RequestMapping(value = "first", method = { RequestMethod.GET })
	public String first(Model model) {
		return "first";
	}

	@RequestMapping(value = "learning_content/{id}", method = { RequestMethod.GET })
	public ModelAndView learningcontent(@PathVariable Integer id,HttpSession httpSession) {
		ModelAndView mav= new ModelAndView();
		TFile ppt =  learningService.getFile(id,1);
		if(ppt!=null){
			String ppturl = ppt.getUrl();
			ppt.setUrl(ppturl);
		}
		TFile video =  learningService.getFile(id,2);
		if(video!=null){
			video.setUrl(video.getUrl());
		}
		TFile pdf =  learningService.getFile(id,3);
		if(pdf!=null){
			pdf.setUrl(pdf.getUrl());
		}
		TUnit unit =  learningService.getunit(id);
		mav.addObject("unit", unit.getUnitName());
		mav.addObject("ppt", ppt);
		mav.addObject("video", video);
		mav.addObject("pdf", pdf);
		mav.setViewName("learning_content");
		
		//学习记录
		TStudent student= (TStudent) httpSession.getAttribute("student");
		if (student!=null) {
			TLearningRecord lr=new TLearningRecord();
			lr.setNum(student.getId());
			lr.setName(student.getRealName());
			lr.setOperTimes(new Date());
			lr.setUnitId(id.toString());
			learningRecordService.save(lr);
			
		}
		
		return mav;
	}
	
	@RequestMapping(value = "info/info", method = { RequestMethod.GET })
	public ModelAndView first() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("info", "对不起，请先登录");
		mav.setViewName("webInfo");
		return mav;
	}
	
	
	//转到教学目标页面
	@RequestMapping(value = "target_content/{id}", method = { RequestMethod.GET })
	public ModelAndView targetcontent(@PathVariable Integer id) {
		ModelAndView mav= new ModelAndView();
		TFileShow target =  learningService.getFileShow(id,1);
		if(target!=null){
			String ppturl = target.getUrl().substring(9);
			target.setUrl(ppturl);
		}
		
		mav.addObject("target", target);
		mav.setViewName("target_content");
		return mav;
	}
	
	//转到教学方法页面
	@RequestMapping(value = "jxff_content/{id}", method = { RequestMethod.GET })
	public ModelAndView jxffcontent(@PathVariable Integer id) {
		ModelAndView mav= new ModelAndView();
		TFileShow target =  learningService.getFileShow(id,2);
		if(target!=null){
			String ppturl = target.getUrl().substring(9);
			target.setUrl(ppturl);
		}
		
		mav.addObject("target", target);
		mav.setViewName("jxff_content");
		return mav;
	}
	
	
	//转到教学大纲页面
	@RequestMapping(value = "jxdg_content/{id}", method = { RequestMethod.GET })
	public ModelAndView jxdgcontent(@PathVariable Integer id) {
		ModelAndView mav= new ModelAndView();
		TFileShow target =  learningService.getFileShow(id,3);
		if(target!=null){
			String ppturl = target.getUrl().substring(9);
			target.setUrl(ppturl);
		}
		
		mav.addObject("target", target);
		mav.setViewName("jxdg_content");
		return mav;
	}
	
	//转到教学日历页面
	@RequestMapping(value = "jxrl_content/{id}", method = { RequestMethod.GET })
	public ModelAndView jxrlcontent(@PathVariable Integer id) {
		ModelAndView mav= new ModelAndView();
		TFileShow target =  learningService.getFileShow(id,4);
		if(target!=null){
			String ppturl = target.getUrl().substring(9);
			target.setUrl(ppturl);
		}
		
		mav.addObject("target", target);
		mav.setViewName("jxrl_content");
		return mav;
	}
	
	
	//转到考核方式页面
	@RequestMapping(value = "khfs_content/{id}", method = { RequestMethod.GET })
	public ModelAndView khfscontent(@PathVariable Integer id) {
		ModelAndView mav= new ModelAndView();
		TFileShow target =  learningService.getFileShow(id,5);
		if(target!=null){
			String ppturl = target.getUrl().substring(9);
			target.setUrl(ppturl);
		}
		
		mav.addObject("target", target);
		mav.setViewName("khfs_content");
		return mav;
	}
	
	
	//转到教材页面
	@RequestMapping(value = "jc_content/{id}", method = { RequestMethod.GET })
	public ModelAndView jc_content(@PathVariable Integer id) {
		ModelAndView mav= new ModelAndView();
		TFileShow target =  learningService.getFileShow(id,6);
		if(target!=null){
			String ppturl = target.getUrl().substring(9);
			target.setUrl(ppturl);
		}
		
		mav.addObject("target", target);
		mav.setViewName("jc_content");
		return mav;
	}
	
	
	//转到师资力量页面
	@RequestMapping(value = "szll_content/{id}", method = { RequestMethod.GET })
	public ModelAndView szll_content(@PathVariable Integer id) {
		ModelAndView mav= new ModelAndView();
		TFileShow target =  learningService.getFileShow(id,7);
		if(target!=null){
			String ppturl = target.getUrl().substring(9);
			target.setUrl(ppturl);
		}
		
		mav.addObject("target", target);
		mav.setViewName("szll_content");
		return mav;
	}
		
		
}
