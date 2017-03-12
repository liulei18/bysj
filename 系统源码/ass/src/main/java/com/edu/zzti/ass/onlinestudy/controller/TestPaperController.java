package com.edu.zzti.ass.onlinestudy.controller;



import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.edu.zzti.ass.management.model.TStudent;
import com.edu.zzti.ass.onlinestudy.model.TestAnswerInfo;
import com.edu.zzti.ass.onlinestudy.model.TestPaper;
import com.edu.zzti.ass.onlinestudy.service.ILearningService;
import com.edu.zzti.ass.onlinestudy.service.IOnlineStudyService;
import com.edu.zzti.ass.onlinestudy.service.IPaperService;
import com.edu.zzti.ass.testlibrary.model.TUnit;


@Controller
@RequestMapping("/paper")
public class TestPaperController {
	@Autowired
	private IPaperService paperService;
	@Autowired
	private ILearningService learningService;
	@Autowired
	private IOnlineStudyService onlineStudyService;
	
	/**
	 * 进入练习
	 * @param id 练习题id
	 * @return
	 */
	@RequestMapping(value = "intoPaper/{id}", method = { RequestMethod.GET })
	public ModelAndView intoPaper(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView();
		TestPaper paper = paperService.getPaper(id);
		mav.addObject("singNum", paper.getSinglesels().size());
		mav.addObject("judgeNum", paper.getJudges().size());
		mav.addObject("paper", paper);
		mav.setViewName("intoPaper");
		return mav;
	}
	
	@RequestMapping(value = "savePaper/{id}", method = { RequestMethod.POST })
	public ModelAndView savePaper(@PathVariable Integer id,HttpSession session,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		TStudent stu = (TStudent) session.getAttribute("student");
		String studentId = stu.getId();
		Integer paperid = id;
		TestPaper paper  =  paperService.getPaper(paperid);
		boolean flag = paperService.findStuAnswer(studentId,paperid);  //判断此学生是否做过此练习
		if(flag){
			mav.addObject("info", "已经提交过了！");
			mav.setViewName("webInfo");
			return mav;
		}

//		Date now = new Date();
//		if (paper.getAnswerDate().before(now)) {
//			mav.addObject("info", "已经超时不能提交了！");
//			mav.setViewName("info");
//			return mav;
//		}

		List<String> singleValues = new ArrayList<>();
		for(int i = 0 ;i<paper.getSinglesels().size();i++){
			singleValues.add(request.getParameter("sing"+i));  //循环保存页面的单选题答案
		}
		List<String> judgeValues = new ArrayList<>();
		for(int i = 0 ;i<paper.getJudges().size();i++){
			judgeValues.add(request.getParameter("judge"+i)); //循环保存页面的判断题答案
		}
		List<String> subValues = new ArrayList<>();
		for(int i = 0 ;i<paper.getSubjective().size();i++){  //循环保存页面的简单题答案
			subValues.add(request.getParameter("sub"+i));
		}
		int count  =  singleValues.size()+judgeValues.size();   //单选和判断的总数量
		String singStr =paper.getTestAnswer().getSingleAnswers(); //获得练习单选的正确答案
		String judgeStr = paper.getTestAnswer().getJudgeAnswers(); //获得判断的正确答案
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
		int info = k*100/count;   //计算单选和判断的正确率
		TestAnswerInfo answerInfo = new TestAnswerInfo();
		answerInfo.setInfo(info);
		StringBuffer sb = new StringBuffer();  //创建可变字符串
		for(String sing :singleValues){
			sb.append(sing+",");
		}
		sb.deleteCharAt(sb.length()-1);  //删除最后一个','
		answerInfo.setSingleAnswers(sb.toString());
		
		sb.setLength(0); //清空可变字符串
		
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
		paperService.saveStuAnswer(answerInfo);
		mav.addObject("info", "练习已经提交！");
		mav.setViewName("webInfo");
		return mav;
	}
	/**
	 * 查看我的练习进入单元选择
	 * @return
	 */
	@RequestMapping(value = "intoAnswer", method = { RequestMethod.GET })
	public ModelAndView intoAnswer() {
		ModelAndView mav = new ModelAndView();
		List<TUnit> units =learningService.getAllUnits();
		mav.addObject("units", units);
		mav.setViewName("answerlist");
		return mav;
		
	}
	/**
	 * 展示所选单元已经做过的练习
	 * @param unitId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "intoAnswer/{unitId}", method = { RequestMethod.GET })
	public ModelAndView intoAnswer(@PathVariable Integer unitId,HttpSession session) {
		ModelAndView mav= new ModelAndView();
		TUnit unit =learningService.getunit(unitId);
		TStudent student = (TStudent) session.getAttribute("student");
		List<TestAnswerInfo> papers= onlineStudyService.getAnswerByUnit(student.getId(),unitId);
		List<TUnit> units =learningService.getAllUnits();
		mav.addObject("units", units);
		mav.addObject("papers", papers);
		mav.addObject("unit", unit);
		mav.setViewName("answerlist");
		return mav;
		
	}
	/**
	 * 练习详细  正确答案 和 我的答案
	 * @param answerId
	 * @return
	 */
	@RequestMapping(value = "lookAnswer/{answerId}", method = { RequestMethod.GET })
	public ModelAndView intoAnswer(@PathVariable Integer answerId) {
		ModelAndView mav= new ModelAndView();
		TestAnswerInfo answer =  paperService.getAnswer(answerId);
			
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
	

		
			String sings = answer.getPaper().getTestAnswer().getSingleAnswers();
			mav.addObject("sings", sings);
			String judges = answer.getPaper().getTestAnswer().getJudgeAnswers();
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
			String subjs =   answer.getPaper().getTestAnswer().getSubjectAnswers();
			String[] subs = subjs.split("////");
			for(int i = 0 ;i<subs.length;i++){
				sb1.append(i+1).append("、 ").append(subs[i]).append("<br/>");
		}
		mav.addObject("subs", sb1.toString());
	
		

		mav.addObject("paper",  answer.getPaper());
		mav.addObject("singNum",  answer.getPaper().getSinglesels().size());
		mav.addObject("judgeNum",  answer.getPaper().getJudges().size());
		
		mav.setViewName("lookAnswer");
	
		return mav;
	}

	
}
