package com.edu.zzti.ass.server.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.edu.zzti.ass.core.util.UploadUtil;
import com.edu.zzti.ass.management.model.TStudent;
import com.edu.zzti.ass.management.model.TTeacher;
import com.edu.zzti.ass.server.service.IInfoService;

@Controller
@RequestMapping("/server/info")
public class InfoManageController {
	@Autowired
	private IInfoService infoService ;
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public void saveInfo(@RequestParam("photo") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String tempPath = request.getServletContext().getRealPath("").replace("\\", "/");
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> map = new HashMap<String, String>();
		String imageurl =null;
		if (!file.isEmpty()) {
			UploadUtil uploadUtil = new UploadUtil(request);
		
			// 获得文件真实名字
			String fileName = file.getOriginalFilename();
			// 获取文件后缀名
			String endName = fileName.substring(fileName.lastIndexOf(".") + 1)
					.toLowerCase();
			
			try {
				map = uploadUtil.uploadFile1(10485760, "img", file);
				imageurl  = map.get("url");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
			String id  = request.getParameter("id");
			String desc = request.getParameter("desc");
			String email = request.getParameter("email");
			String remark = request.getParameter("remark");
			String type = request.getParameter("type");
			if(type.equals("student")){
				TStudent student =new TStudent();
				student.setId(id);
				if(imageurl!=null){
					student.setImgurl(imageurl);
				}
				student.setDescs(desc);
				student.setEmail(email);
				student.setRemark(remark);
				
				imageurl = infoService.updateInfo(student,type,tempPath);
			}else{
				TTeacher teacher = new TTeacher();
				teacher.setId(id);
				if(imageurl!=null){
					teacher.setImgurl(imageurl);
				}
				teacher.setDescs(desc);
				teacher.setEmail(email);
				teacher.setRemark(remark);
				imageurl = infoService.updateInfo(teacher,type,tempPath);
			}
	
			
			try {
				PrintWriter writer = response.getWriter();
				String info =imageurl;
				writer.write(info);
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
			
		
		
	}
	


