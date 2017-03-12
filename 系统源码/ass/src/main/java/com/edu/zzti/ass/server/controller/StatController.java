package com.edu.zzti.ass.server.controller;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.edu.zzti.ass.core.model.PageInfo;
import com.edu.zzti.ass.management.model.Stat;
import com.edu.zzti.ass.management.service.IRecordService;
import com.edu.zzti.ass.server.model.Lexicon;
import com.edu.zzti.ass.server.service.ILexiconService;

@Controller
@RequestMapping("/stat")
public class StatController {

	@Autowired
	private ILexiconService lexiconService;
	
	@Autowired
	private IRecordService recordService;

	@RequestMapping(value = "all", method = { RequestMethod.GET })
	public ModelAndView getAll() {
		ModelAndView mav = new ModelAndView();
		PageInfo<Lexicon> pageInfo;

		pageInfo = lexiconService.findAllLexicon(1, null);
		
		
		List<Map> map= recordService.findBySql("SELECT COUNT(*) as sl,SUBSTR(t.operTimes,1,7) ,t.operTimes as sj FROM t_student_log t  GROUP BY SUBSTR(t.operTimes,1,7)");
		System.out.println(map.get(0).get("sl"));
		System.out.println(map.get(0).get("sj"));
		mav.addObject("pageInfo", pageInfo);
		mav.addObject("mapInfo", map);
		mav.setViewName("manage/statManage");
		return mav;
		
		
	}

	

	@RequestMapping(value = "chart", method = { RequestMethod.GET })
	public ModelAndView add(HttpServletRequest request) {
		
		String title = "各大公司JEE AS市场占有率统计" ;
		DefaultPieDataset ds=new DefaultPieDataset();
		ds.setValue("2016-05", 2);
		ds.setValue("2016-06", 2);
		ds.setValue("2016-07", 3);
		
		JFreeChart chart= ChartFactory.createPieChart3D(title, ds, true, false, false);
		
		//中文 标题字体
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 25));
		//设置提示
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 18));
		
		//绘图区
		PiePlot plot= (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("宋体",Font.PLAIN,15));
		
		
		//设置分裂效果 只支持2维
		plot.setExplodePercent("IBM", 0.1f);
		plot.setExplodePercent("JBOSS", 0.2f);
		
		//设置前景色透明度
		plot.setForegroundAlpha(0.7f);
		
		//设置标签生成器
		//{0}:公司名称
		//{1}:销量
		//{2}:百分比
		//{3}:总量
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}({1}/{3}-{2})"));
		
		try {
			String savePath = "/WEB-INF/upload/";
			
			ChartUtilities.saveChartAsJPEG(new File(savePath), chart, 800, 500);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
