package com.edu.zzti.ass.management.controller;


import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edu.zzti.ass.core.util.UuidHelper;
import com.edu.zzti.ass.management.model.TClass;
import com.edu.zzti.ass.management.model.TStudent;
import com.edu.zzti.ass.management.model.TTeacher;
import com.edu.zzti.ass.management.service.IClassService;
import com.edu.zzti.ass.management.service.IStudentService;
import com.edu.zzti.ass.management.service.ITeacherService;

@Controller
@RequestMapping("/manage/teacher")
public class TeacherController {
	@Autowired
	private ITeacherService teacherService;
	@Autowired
	private IClassService classService;
	@Autowired
	private IStudentService studentService;

	@RequestMapping(value = "addTeacher", method = { RequestMethod.GET })
	public ModelAndView addTeacher(Model model, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		TTeacher teacher = (TTeacher) httpSession.getAttribute("userInfo");
		if (teacher.getIsAdmin()==null||teacher.getIsAdmin() == 0) {
			mav.addObject("info", "对不起，你不是管理员老师！");
			mav.setViewName("manage/info");
			return mav;
		} else {
			model.addAttribute("teacher", new TTeacher());
			mav.setViewName("manage/addTeacher");
			return mav;
		}
	}
	
	/**
	 * 验证用户名
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "checkName", method = { RequestMethod.POST })
	public void checkName(HttpServletRequest request , HttpServletResponse response) {
		String userName = request.getParameter("username");
		TTeacher teacher = teacherService.findTeacherByName(userName);
		if(teacher ==null){
			try {
				response.getWriter().write("{\"info\":\"可以使用\"}");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				response.getWriter().write("{\"info\":\"已存在此用户名\"}");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		
	}
	

	@RequestMapping(value = "addTeacher", method = { RequestMethod.POST })
	public ModelAndView addTeacher(Model model, TTeacher teacher) {
		teacher.setId(UuidHelper.getUuid());
		ModelAndView mav = new ModelAndView();
		teacher.setIsAdmin(0);
		teacherService.save(teacher);
		mav.addObject("info", "教师添加成功");
		mav.setViewName("manage/info");
		return mav;
	}

	/**
	 * 查看学生信息
	 */

	@RequestMapping(value = "student", method = { RequestMethod.GET })
	public ModelAndView student(Model model, HttpSession httpSession) {
		TTeacher teacher = (TTeacher) httpSession.getAttribute("userInfo");
		List<TClass> tClass = classService.find(teacher.getId());
		ModelAndView mav = new ModelAndView();
		mav.addObject("tclass", tClass);
		mav.setViewName("manage/student");
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "queryStu/{classId}", method = { RequestMethod.POST })
	public List<Object> student(HttpServletResponse response,
			@PathVariable Integer classId) {

		List<Object> list = new ArrayList<Object>();
		list.add(this.studentService.find(classId));
		return list;
	}
	/**
	 * 修改密码
	 * @param model
	 * @param httpSession
	 * @param pwd
	 */
	@ResponseBody
	 @RequestMapping(value="update/{pwd}", method = {RequestMethod.POST})
	    public String  updatePwd(HttpSession httpSession,@PathVariable String pwd){
		 TTeacher teacher = (TTeacher) httpSession.getAttribute("userInfo");
		 teacherService.updateSql(pwd, teacher.getId());
	       return pwd;
	    }
	
}
