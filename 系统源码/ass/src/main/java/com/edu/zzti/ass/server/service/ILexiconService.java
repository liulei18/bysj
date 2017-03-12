package com.edu.zzti.ass.server.service;

import com.edu.zzti.ass.core.model.PageInfo;
import com.edu.zzti.ass.server.model.Lexicon;

public interface ILexiconService {

	
	public void save(Lexicon lexicon);
	
	public PageInfo<Lexicon> findAllLexicon(int currentPage,String key);
	
	public void delete(Integer id);

	public Lexicon getById(Integer id);

	public void update(Lexicon lexicon);

	public Lexicon find(String word);

	public Lexicon getRandom();
}
