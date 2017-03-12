package com.edu.zzti.ass.management.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.edu.zzti.ass.core.util.UuidHelper;
import com.edu.zzti.ass.management.model.TClass;
import com.edu.zzti.ass.management.model.TStudent;
import com.edu.zzti.ass.management.model.TTeacher;
import com.edu.zzti.ass.management.service.IClassService;
import com.edu.zzti.ass.management.service.IStudentService;

@Controller
@RequestMapping("/manage/student")
public class StudentController {
	@Autowired
	private IStudentService studentService;
	@Autowired
	private IClassService classService;



}
