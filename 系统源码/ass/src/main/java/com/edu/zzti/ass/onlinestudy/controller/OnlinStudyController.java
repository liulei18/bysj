package com.edu.zzti.ass.onlinestudy.controller;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.edu.zzti.ass.management.model.TStudent;
import com.edu.zzti.ass.onlinestudy.model.TestPaper;
import com.edu.zzti.ass.onlinestudy.service.ILearningService;
import com.edu.zzti.ass.onlinestudy.service.IOnlineStudyService;
import com.edu.zzti.ass.testlibrary.model.TUnit;
import com.edu.zzti.ass.testlibrary.service.ITestPaperService;

@Controller
@RequestMapping("/onlineStudy")
public class OnlinStudyController {
	@Autowired
	private IOnlineStudyService onlineStudyService;
	@Autowired
	private ITestPaperService testPaperService;
	@Autowired
	private ILearningService learningService;
	    
	/**
	 * 随堂练习页面访问
	 * @return
	 */
	@RequestMapping(value = "class", method = { RequestMethod.GET })
	public ModelAndView  cl() {
		ModelAndView mav= new ModelAndView();
		List<TUnit> units =learningService.getAllUnits(); //获取所有单元存放List集合中
		mav.addObject("units", units); //将List存放 模型视图装载器 中
		mav.setViewName("class"); //加入要解析的视图
		return mav;
	}
	
	@RequestMapping(value = "class/{unitId}", method = { RequestMethod.GET })
	public ModelAndView  praList(@PathVariable Integer unitId,HttpSession session) {
		ModelAndView mav= new ModelAndView();
		TUnit unit =learningService.getunit(unitId); 
		TStudent student = (TStudent) session.getAttribute("student"); //获取当前登录用户
		String teacherId =	 onlineStudyService.getTeacherId(student.getId()); //通过登录的学生id 获取他的教师的id 
		List<TestPaper> papers= onlineStudyService.getPaperByUnit(teacherId,unitId); //获取自己老师发布的所选单元的练习
		List<TUnit> units =learningService.getAllUnits();
		mav.addObject("units", units);
		mav.addObject("papers", papers);
		mav.addObject("unit", unit);
		mav.setViewName("class");
		return mav;
	}

}
