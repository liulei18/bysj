package com.edu.zzti.ass.server.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edu.zzti.ass.management.model.TClass;
import com.edu.zzti.ass.management.model.TStudent;
import com.edu.zzti.ass.management.model.TTeacher;
import com.edu.zzti.ass.management.service.IStudentService;
import com.edu.zzti.ass.server.model.AnswerInfo;
import com.edu.zzti.ass.server.model.PracticePaper;
import com.edu.zzti.ass.server.model.Unit;
import com.edu.zzti.ass.server.service.IPracticeService;

@Controller
@RequestMapping("/server/practice")
public class PracticeController {

	@Autowired
	private IPracticeService practiceService;
	
	@Autowired
	private IStudentService studentService;
	
	@ResponseBody  
	@RequestMapping("allUnit")
	public List<Unit> getAllUnit(HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		List<Unit> units = practiceService.getUnits();
		return units;

	} 
	@ResponseBody  
	@RequestMapping(value = "praAdd",method = {RequestMethod.POST}  )
	public Integer praAdd(HttpServletRequest request) throws Exception{
		request.setCharacterEncoding("utf-8");
		String praName = request.getParameter("praName");
		Integer praDx = Integer.valueOf(request.getParameter("praDx")) ;
		Integer praPd = Integer.valueOf(request.getParameter("praPd"));
		Integer praJd = Integer.valueOf(request.getParameter("praJd"));
		String praCutTime = request.getParameter("praCutTime");
		String teacherId = request.getParameter("teacherId");
		Integer unitId = Integer.valueOf(request.getParameter("unitId"));
		String chapterIds = request.getParameter("chapterIds");
		String [] chapterIdArray = chapterIds.split(",");
		List<Integer> chapterIdList =new  ArrayList<Integer>();
		for(String id : chapterIdArray){
			chapterIdList.add(Integer.valueOf(id));
		}
		
		PracticePaper paper = practiceService.addPra(praName,praDx,praPd,praJd,praCutTime,teacherId,unitId,chapterIdList);
		Integer paperId = paper.getId();
		return paperId;
	}
	
	@RequestMapping(value = "prePaper/{id}", method = { RequestMethod.GET })
	public ModelAndView prePaper(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView();
		PracticePaper paper = practiceService.getPaper(id);
		mav.addObject("paper", paper);
		mav.setViewName("prePractice");
		return mav;
	}
	
	@RequestMapping(value = "usePaper/{id}/{type}", method = { RequestMethod.GET })
	public ModelAndView usePaper(@PathVariable Integer id,@PathVariable Integer type) {
		ModelAndView mav = new ModelAndView();
		int i = practiceService.deal(id,type);
		if(i==0){
			mav.addObject("info", "此练习已经删除！");
			mav.setViewName("info");
		}else{
			mav.addObject("info", "此练习已经可以使用！");
			mav.setViewName("info");
		}
		return mav;
	}
	@RequestMapping(value = "intoPaper/{paperid}/{type}/{id}", method = { RequestMethod.GET })
	public ModelAndView intoPaper(@PathVariable Integer paperid,@PathVariable String type,@PathVariable String id) {
		ModelAndView mav = new ModelAndView();
		String userId = id;
		PracticePaper paper = practiceService.getPaper(paperid);
		if(type.equals("teacher")){
			String sings =  paper.getPracticeAnswer().getSingleAnswers();
			mav.addObject("sings", sings);
			String judges =  paper.getPracticeAnswer().getJudgeAnswers();
			StringBuilder sb = new StringBuilder();
			String[] juds =  judges.split(",");
			for(int i = 0 ;i<juds.length;i++){
				String val = "";
				if(juds[i].equals("0")){
					val = "√";
				}else{
					val = "×";
				}
				sb.append(i+1).append("、 ").append(val).append(" ");
			}
			mav.addObject("judges", sb.toString());
			sb.setLength(0);
			String subjs =  paper.getPracticeAnswer().getSubjectAnswers();
			String[] subs = subjs.split("////");
			for(int i = 0 ;i<subs.length;i++){
				sb.append(i+1).append("、 ").append(subs[i]).append("<br/>");
			}
			mav.addObject("subs", sb.toString());
		}
		mav.addObject("stuid", userId);
		mav.addObject("type", type);
		mav.addObject("paper", paper);
		mav.addObject("singNum", paper.getSinglesels().size());
		mav.addObject("judgeNum", paper.getJudges().size());
		mav.setViewName("intoPractice");
		return mav;
	}
	
	@RequestMapping(value = "saveInfo", method = { RequestMethod.POST })
	public ModelAndView saveInfo(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView();
		String studentId = request.getParameter("stuid");
		Integer paperid = Integer.valueOf(request.getParameter("paperid"));
		PracticePaper paper  =  practiceService.getPaper(paperid);
		boolean flag = practiceService.findStuAnswer(studentId,paperid);
		if(flag){
			mav.addObject("info", "已经提交过了！");
			mav.setViewName("info");
			return mav;
		}

		Date now = new Date();
		if (paper.getAnswerDate().before(now)) {
			mav.addObject("info", "已经超时不能提交了！");
			mav.setViewName("info");
			return mav;
		}

		List<String> singleValues = new ArrayList<>();
		for(int i = 0 ;i<paper.getSinglesels().size();i++){
			singleValues.add(request.getParameter("sing"+i));
		}
		List<String> judgeValues = new ArrayList<>();
		for(int i = 0 ;i<paper.getJudges().size();i++){
			judgeValues.add(request.getParameter("judge"+i));
		}
		List<String> subValues = new ArrayList<>();
		for(int i = 0 ;i<paper.getSubjective().size();i++){
			subValues.add(request.getParameter("sub"+i));
		}
		int count  =  singleValues.size()+judgeValues.size();
		String singStr =paper.getPracticeAnswer().getSingleAnswers();
		String judgeStr = paper.getPracticeAnswer().getJudgeAnswers();
		String[] sings = singStr.split(",");
		String[] judges = judgeStr.split(",");
		int k = 0;
		for(int i = 0 ;i<singleValues.size();i++){
			if(singleValues.get(i).equals(sings[i])){
				k++;
			}
		}
		for(int i = 0 ;i<judgeValues.size();i++){
			if(judgeValues.get(i).equals(judges[i])){
				k++;
			}
		}
		int info = k*100/count;
		AnswerInfo answerInfo = new AnswerInfo();
		answerInfo.setInfo(info);
		StringBuffer sb = new StringBuffer();
		for(String sing :singleValues){
			sb.append(sing+",");
		}
		sb.deleteCharAt(sb.length()-1);
		answerInfo.setSingleAnswers(sb.toString());
		sb.setLength(0);
		
		for(String judge :judgeValues){
			sb.append(judge+",");
		}
		sb.deleteCharAt(sb.length()-1);
		answerInfo.setJudgeAnswers(sb.toString());
		sb.setLength(0);
		
		for(String sub:subValues){
			sb.append(sub+"////");
		}
		sb.delete(sb.length()-4, sb.length());
		answerInfo.setSubjectAnswers(sb.toString());
		answerInfo.setCreateDate(new Date());
		answerInfo.setUnit(paper.getUnit());
		answerInfo.setPaper(paper);
		TStudent student =new TStudent();
		student.setId(studentId);
		answerInfo.setStudent(student);
		practiceService.saveStuAnswer(answerInfo);
		mav.addObject("info", "练习已经提交！");
		mav.setViewName("info");
		return mav;
	}
	@ResponseBody  
	@RequestMapping(value = "paperList/{id}/{type}/{unitId}", method = { RequestMethod.GET })
	public List<Map> getPaperList(@PathVariable String id,@PathVariable String type,@PathVariable Integer unitId) {
		String teacherid ="";
		if(type.equals("student")){

			teacherid = practiceService.getTeacherId(id);

		}else if(type.equals("teacher")){
			teacherid = id;
		}
		List<PracticePaper> papers = practiceService.getPaperList(teacherid,unitId);
		
		List<Map> list = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for(PracticePaper paper : papers){
			Map<String,Object> map = new HashMap<>();
			map.put("paperid", paper.getId());
			map.put("createdate", sdf.format(paper.getCreateDate()));
			map.put("paperName",paper.getPagerName());
			list.add(map);
		}
		return list;
	}
	
	@ResponseBody  
	@RequestMapping(value = "stuPaperList/{id}/{unitId}", method = { RequestMethod.GET })
	public List<Map> getStuPaperList(@PathVariable String id,@PathVariable Integer unitId) {

		List<AnswerInfo> papers =  practiceService.getStuPaperList(id,unitId);
		
		List<Map> list = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for(AnswerInfo paper : papers){
			Map<String,Object> map = new HashMap<>();
			map.put("paperid", paper.getId());
			map.put("createdate", sdf.format(paper.getPaper().getCreateDate()));
			map.put("paperName",paper.getPaper().getPagerName());
			list.add(map);
		}
		return list;
	}
	
	@RequestMapping(value = "answer/{id}", method = { RequestMethod.GET })
	public ModelAndView getAnswer(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView();
		AnswerInfo answer =  practiceService.getAnswer(id);
			
		String mysings = answer.getSingleAnswers();
		mav.addObject("mysings", mysings);
		String myjudges = answer.getJudgeAnswers();
		StringBuilder sb = new StringBuilder();
		String[] myjuds =  myjudges.split(",");
		for(int i = 0 ;i<myjuds.length;i++){
			String val = "";
			if(myjuds[i].equals("0")){
				val = "√";
			}else{
				val = "×";
			}
			sb.append(i+1).append("、 ").append(val).append(" ");
		}
		mav.addObject("myjudges", sb.toString());
		sb.setLength(0);
		String mysubjs =   answer.getSubjectAnswers();
		String[] mysubs = mysubjs.split("////");
		for(int i = 0 ;i<mysubs.length;i++){
			sb.append(i+1).append("、 ").append(mysubs[i]).append("<br/>");
		}
		mav.addObject("mysubs", sb.toString());
	
		if(answer.getPaper().getAnswerDate().before(new Date())){
		
			String sings = answer.getPaper().getPracticeAnswer().getSingleAnswers();
			mav.addObject("sings", sings);
			String judges = answer.getPaper().getPracticeAnswer().getJudgeAnswers();
			StringBuilder sb1 = new StringBuilder();
			String[] juds =  judges.split(",");
			for(int i = 0 ;i<juds.length;i++){
				String val = "";
				if(juds[i].equals("0")){
					val = "√";
				}else{
					val = "×";
				}
				sb1.append(i+1).append("、 ").append(val).append(" ");
			}
			mav.addObject("judges", sb1.toString());
			sb1.setLength(0);
			String subjs =   answer.getPaper().getPracticeAnswer().getSubjectAnswers();
			String[] subs = subjs.split("////");
			for(int i = 0 ;i<subs.length;i++){
				sb1.append(i+1).append("、 ").append(subs[i]).append("<br/>");
		}
		mav.addObject("subs", sb1.toString());
	
		}else{
			mav.addObject("type", "overtime");
		}
		
		

		mav.addObject("paper",  answer.getPaper());
		mav.addObject("singNum",  answer.getPaper().getSinglesels().size());
		mav.addObject("judgeNum",  answer.getPaper().getJudges().size());
		
		mav.setViewName("intoAnswer");
		return mav;
	}
	@RequestMapping(value = "info/{paperid}/{teacherid}/{classid}", method = { RequestMethod.GET })
	public ModelAndView getInfo(@PathVariable Integer paperid,@PathVariable String teacherid,@PathVariable Integer classid) {
		ModelAndView mav = new ModelAndView();
		PracticePaper paper = practiceService.getPaper(paperid);
		TTeacher teacher = practiceService.getTeacher(teacherid);
		Iterator<TClass> iterator=  teacher.getTClass().iterator();
		List<TClass> classes  = new ArrayList<>();
		while(iterator.hasNext()){
			classes.add(iterator.next());
		}
		Collections.sort(classes, new Comparator<TClass>(){

			@Override
			public int compare(TClass o1, TClass o2) {
				if(o1.getId()<o2.getId()){
					return -1;
				}
				if(o1.getId()>o2.getId()){
					return 1;
				}
				return 0;
			}
		});
		Integer classId =classid;
		if(classid==-999){
			TClass tclass =	classes.get(0);
			classId=tclass.getId();
		}

		List<TStudent> students =  studentService.getall(classId);
		List<AnswerInfo> answerlist = practiceService.getAnswerlist(paperid,classId) ; 

		for(AnswerInfo answerInfo :answerlist){
			if(students.contains(answerInfo.getStudent())){
				students.remove(answerInfo.getStudent());
			}
		}
	
		mav.addObject("paper", paper);
		mav.addObject("classid", classId);
		mav.addObject("teacherid", teacherid);
		mav.addObject("classes", classes);
		mav.addObject("students", students);
		mav.addObject("answerlist", answerlist);
		mav.addObject("stuNum", students.size());
		mav.addObject("answerNum", answerlist.size());
		mav.setViewName("checkInfo");
		return mav;
	}
}
