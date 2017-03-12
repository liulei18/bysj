package com.edu.zzti.ass.management.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.edu.zzti.ass.management.model.TTeacher;
import com.edu.zzti.ass.management.service.ITeacherService;

@Controller
@RequestMapping(value = "/manage")
public class ManageLoginController {

	@Autowired
	private ITeacherService teacherService;

	/**
	 * 访问login页面 添加属性
	 * @param model
	 * @return
	 */
	  @RequestMapping(value="login", method = {RequestMethod.GET})
	    public String login(Model model,HttpSession httpSession){
		  if(httpSession.getAttribute("userInfo")==null){
			  model.addAttribute("teacher", new TTeacher());
		        return "manage/login";
		  }else{
			  httpSession.removeAttribute("userInfo");
			  model.addAttribute("teacher", new TTeacher());
		        return "manage/login";
		  }
	       
	    }
	/**
	 * 进行登录界面的post请求操作！
	 * @param teacher
	 * @return
	 */
	@RequestMapping(value="login", method = {RequestMethod.POST})
	public ModelAndView findTeacher(TTeacher teacher , Model model,HttpSession httpSession){
		 ModelAndView mav = new ModelAndView();  
		 	
		 	teacher.setLastLogDate(""+new Date());
		 	TTeacher obj=	this.teacherService.findTeacher(teacher);
		  if(obj==null){
			  mav.addObject("message", "账号或者密码错误！"); 
			  mav.addObject("name", teacher.getTname()); 
		      mav.setViewName("manage/login");
			  return mav;
		  }else{
			  httpSession.setAttribute("userInfo", obj);
		      mav.setViewName("redirect:/manage/index");
			  return mav;
		  }
	  }
	
	@RequestMapping(value="index", method = {RequestMethod.GET})
	public String index(){
	 return "manage/index";
	}
}
