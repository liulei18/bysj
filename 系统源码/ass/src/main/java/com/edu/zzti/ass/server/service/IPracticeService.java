package com.edu.zzti.ass.server.service;

import java.util.List;

import com.edu.zzti.ass.management.model.TStudent;
import com.edu.zzti.ass.management.model.TTeacher;
import com.edu.zzti.ass.server.model.AnswerInfo;
import com.edu.zzti.ass.server.model.PracticePaper;
import com.edu.zzti.ass.server.model.Unit;

public interface IPracticeService {

	public List<Unit> getUnits();

	public PracticePaper addPra(String praName, Integer praDx, Integer praPd,
			Integer praJd, String praCutTime, String teacherId, Integer unitId,
			List<Integer> chapterIdList);
	
	public PracticePaper getPaper(Integer id);

	public int deal(Integer id, Integer type);

	public List<PracticePaper> getPaperList(String teacherid, Integer unitId);

	public String getTeacherId(String id);

	public void saveStuAnswer(AnswerInfo answerInfo);

	public boolean findStuAnswer(String studentId, Integer paperid);

	public List<AnswerInfo> getStuPaperList(String id, Integer unitId);

	public AnswerInfo getAnswer(Integer id);

	public TTeacher getTeacher(String teacherid);

	public List<AnswerInfo> getAnswerlist(Integer paperid, Integer classId);
	
}
