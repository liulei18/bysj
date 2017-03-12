package com.edu.zzti.ass.server.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.edu.zzti.ass.management.model.TStudent;
import com.edu.zzti.ass.management.model.TTeacher;
import com.edu.zzti.ass.management.service.IStudentService;
import com.edu.zzti.ass.management.service.ITeacherService;
import com.edu.zzti.ass.server.model.Lexicon;
import com.edu.zzti.ass.server.model.Student;
import com.edu.zzti.ass.server.model.Teacher;
import com.edu.zzti.ass.server.service.ILexiconService;

@Controller
@RequestMapping("/server")
public class AppLoginController {
	@Autowired
	private ITeacherService teacherService;
	@Autowired
	private IStudentService studentService;
	@Autowired
	private ILexiconService lexiconService;

	@RequestMapping(value = "login", method = { RequestMethod.POST })
	public void login(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		response.setContentType("text/html;charset=utf-8");
		if ("student".equals(type)) {
			TStudent student = new TStudent();
			student.setId(userName);
			student.setPwd(userPwd);
			TStudent obj = studentService.findStudent(student);
			String json = "";
			if (obj != null) {
				Student stu = new Student();
				stu.setId(obj.getId());
				stu.setName(obj.getRealName());
				stu.setImgurl(obj.getImgurl());
				stu.setDescs(obj.getDescs());
				stu.setEmail(obj.getEmail());
				stu.setRemark(obj.getRemark());
				stu.setType("student");
				json = JSONObject.toJSONString(stu);
			} else {
				json = "no";

			}
			try {
				PrintWriter writer = response.getWriter();
				writer.write(json);
				writer.flush();
				writer.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		if ("teacher".equals(type)) {
			TTeacher teacher = new TTeacher();
			teacher.setTname(userName);
			teacher.setTpwd(userPwd);
			TTeacher obj = this.teacherService.findTeacher(teacher);
			String json = "";
			if (obj != null) {
				Teacher tea = new Teacher();
				tea.setId(obj.getId());
				tea.setName(obj.getRealName());
				tea.setImgurl(obj.getImgurl());
				tea.setDescs(obj.getDescs());
				tea.setEmail(obj.getEmail());
				tea.setRemark(obj.getRemark());
				tea.setType("teacher");
				json = JSONObject.toJSONString(tea);
			} else {
				json = "no";
			}
			try {
				PrintWriter writer = response.getWriter();
				writer.write(json);
				writer.flush();
				writer.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "find", method = { RequestMethod.POST })
	public void find(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String word = request.getParameter("word");
		String type = request.getParameter("type");
		Lexicon lexicon = lexiconService.find(word);

		PrintWriter writer = response.getWriter();

		if (lexicon == null) {
			if (type.equals("app")) {
				writer.write("no");
			} else if (type.equals("server")) {
				writer.write("{\"info\":\"此词条可以录入\"}");

			}

		} else {
			if (type.equals("app")) {
				writer.write(JSON.toJSONString(lexicon));
			} else if (type.equals("server")) {
				writer.write("{\"info\":\"此词条记录已存在\"}");
			}
		}

		writer.flush();
		writer.close();
	}

	@RequestMapping(value = "get", method = { RequestMethod.GET })
	public void get(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		Lexicon lexicon = lexiconService.getRandom();

		PrintWriter writer = response.getWriter();

		if (lexicon == null) {
		} else {
			writer.write(JSON.toJSONString(lexicon));
		}

		writer.flush();
		writer.close();
	}

}
