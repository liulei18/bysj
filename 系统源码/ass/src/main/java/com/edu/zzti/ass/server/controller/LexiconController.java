package com.edu.zzti.ass.server.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.edu.zzti.ass.core.model.PageInfo;
import com.edu.zzti.ass.server.model.Lexicon;
import com.edu.zzti.ass.server.service.ILexiconService;

@Controller
@RequestMapping("/manage/lexicon")
public class LexiconController {

	@Autowired
	private ILexiconService lexiconService;

	@RequestMapping(value = "all", method = { RequestMethod.GET })
	public ModelAndView getAll() {
		ModelAndView mav = new ModelAndView();
		PageInfo<Lexicon> pageInfo;

		pageInfo = lexiconService.findAllLexicon(1, null);

		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("manage/lexiconManage");
		return mav;
	}

	@RequestMapping(value = "all", method = { RequestMethod.POST })
	public ModelAndView getAll(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Integer currentPage;
		if (request.getParameter("currentPage").equals("")) {
			currentPage = 1;
		} else {
			currentPage = Integer.valueOf(request.getParameter("currentPage"));
		}
		String key = request.getParameter("key");
		PageInfo<Lexicon> pageInfo;

		pageInfo = lexiconService.findAllLexicon(currentPage, key);
		
		mav.addObject("pageInfo", pageInfo);
		mav.addObject("key", key);
		mav.setViewName("manage/lexiconManage");
		return mav;
	}

	@RequestMapping(value = "add", method = { RequestMethod.GET })
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("lexicon", new Lexicon());

		mav.setViewName("manage/lexiconAdd");
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
