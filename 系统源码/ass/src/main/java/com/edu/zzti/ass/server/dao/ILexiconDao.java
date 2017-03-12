package com.edu.zzti.ass.server.dao;

import java.util.List;

import com.edu.zzti.ass.core.dao.IBaseDao;
import com.edu.zzti.ass.server.model.Lexicon;

public interface ILexiconDao extends IBaseDao<Lexicon>{

	public Long countLexicon(String key);
	
	public List<Lexicon>  getListByPage(int currentPage,String key);

	public void deleteLexicon(Integer id);

	public void updateLex(Lexicon lexicon);

	public Lexicon findLexicon(String word);

	public Long getCount();

	public Lexicon getOne(int random);
}
