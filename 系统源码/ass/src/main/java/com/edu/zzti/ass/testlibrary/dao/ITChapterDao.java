package com.edu.zzti.ass.testlibrary.dao;


import java.util.List;

import com.edu.zzti.ass.core.dao.IBaseDao;
import com.edu.zzti.ass.testlibrary.model.TChapter;

public interface ITChapterDao extends IBaseDao {
	
	public List<TChapter> getChapters(Integer unitId);
	
}
