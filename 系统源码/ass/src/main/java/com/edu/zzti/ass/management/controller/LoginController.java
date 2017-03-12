package com.edu.zzti.ass.management.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.edu.zzti.ass.management.model.TRecord;
import com.edu.zzti.ass.management.model.TStudent;
import com.edu.zzti.ass.management.model.TTeacher;
import com.edu.zzti.ass.management.service.IRecordService;
import com.edu.zzti.ass.management.service.IStudentService;
import com.edu.zzti.ass.management.service.ITeacherService;
import com.edu.zzti.ass.management.service.IUserService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private IUserService userService;
	@Autowired
	private ITeacherService teacherService;

	@Autowired
	private IStudentService studentService;
	
	@Autowired
	private IRecordService recordService;

	@RequestMapping(value = "login", method = { RequestMethod.GET })
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}

	/**
	 * 注销
	 * 
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "logout", method = { RequestMethod.GET })
	public ModelAndView logout(HttpSession httpSession) {
		httpSession.removeAttribute("student");
		httpSession.removeAttribute("teacher");
		httpSession.removeAttribute("stuId");
		httpSession.removeAttribute("claName");
		httpSession.removeAttribute("userName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/index");
		return mav;
	}

	/**
	 * 前台登录
	 * 
	 * @param httpSession
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "login", method = { RequestMethod.POST })
	public ModelAndView login(HttpSession httpSession,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String id = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		TStudent obj = this.studentService.findStudent(new TStudent(id, pwd));
		if (obj == null) {
			mav.addObject("info", "账号或者密码错误！");
			mav.setViewName("info");
			return mav;
		} else {
			httpSession.setAttribute("student", obj);
			httpSession.setAttribute("userName", obj.getRealName());
			
			TRecord record=new TRecord();
			record.setNum(obj.getId());
			record.setOperTimes(new Date());
			record.setOper("login");
			record.setRemark(null);
			recordService.save(record);
			
			mav.setViewName("redirect:/index");
			return mav;
		}

	}

}
