package com.edu.zzti.ass.onlinestudy.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.edu.zzti.ass.core.model.PageInfo;
import com.edu.zzti.ass.management.service.ILearningRecordService;
import com.edu.zzti.ass.management.service.IRecordService;
import com.edu.zzti.ass.server.model.Lexicon;
import com.edu.zzti.ass.server.service.ILexiconService;

@Controller
@RequestMapping("/learnrecord")
public class LearningRecordController {

	@Autowired
	private ILexiconService lexiconService;
	
	@Autowired
	private IRecordService recordService;
	
	@Autowired
	private ILearningRecordService learningRecordService;

	@RequestMapping(value = "all", method = { RequestMethod.GET })
	public ModelAndView getAll() {
		ModelAndView mav = new ModelAndView();
		PageInfo<Lexicon> pageInfo;

		pageInfo = lexiconService.findAllLexicon(1, null);
		
		
		List<Map> map= learningRecordService.findBySql("SELECT name,num,operTimes, CASE unitId WHEN 1 THEN '项目计划' WHEN 2 THEN '需求工程' WHEN 3 THEN '系统设计' WHEN 4 THEN '代码审查' ELSE '其它' END AS unitId FROM t_learning_log");
	
		mav.addObject("pageInfo", pageInfo);
		mav.addObject("mapInfo", map);
		mav.setViewName("manage/learnrecord");
		return mav;
		
		
	}

	

	

	@RequestMapping(value = "add", method = { RequestMethod.POST })
	public ModelAndView addlex(Lexicon lexicon) {
		ModelAndView mav = new ModelAndView();
		lexiconService.save(lexicon);
		mav.setViewName("redirect:/manage/lexicon/all");
		return mav;
	}
	
	@RequestMapping(value = "delete/{id}", method = { RequestMethod.GET })
	public ModelAndView delete(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView();
		System.out.println(id);
		lexiconService.delete(id);
		
		mav.setViewName("redirect:/manage/lexicon/all");
		return mav;
	}

	@RequestMapping(value = "update/{id}", method = { RequestMethod.GET })
	public ModelAndView intoUpdate(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView();
		Lexicon lexicon=lexiconService.getById(id);
		mav.addObject("lexicon", lexicon);
		mav.addObject("id",id );
		mav.setViewName("manage/lexiconAdd");
		return mav;
	}
	
	@RequestMapping(value = "update/{id}", method = { RequestMethod.POST })
	public ModelAndView update(@PathVariable Integer id,Lexicon lexicon) {
		ModelAndView mav = new ModelAndView();
		lexiconService.update(lexicon);
		mav.setViewName("redirect:/manage/lexicon/all");
		return mav;
	}
	
	@RequestMapping(value = "detail/{id}", method = { RequestMethod.GET })
	public ModelAndView Detail(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView();
		Lexicon lexicon=lexiconService.getById(id);
		mav.addObject("lexicon", lexicon);
		mav.addObject("detail",1 );
		mav.setViewName("manage/lexiconAdd");
		return mav;
	}
	
	@RequestMapping(value ="find",method={RequestMethod.POST})
	public void find(HttpServletRequest resquest ,HttpServletResponse response ) throws Exception{
		String word = resquest.getParameter("word");
		String type = resquest.getParameter("type");
		Lexicon lexicon = lexiconService.find(word);
	
		PrintWriter writer = response.getWriter();

		if(lexicon==null){
			if(type.equals("app")){
				writer.write("no");
			}else if(type.equals("server")){
				writer.write("{\"info\":\"此词条可以录入\"}");
				
			}
			
		}else{
			if(type.equals("app")){
				writer.write(JSON.toJSONString(lexicon));
			}else if(type.equals("server")){
				writer.write("{\"info\":\"此词条记录已存在\"}");
			}
		}
		System.out.println("asdasdasd");
		writer.flush();
		writer.close();
	}
	
	
}
