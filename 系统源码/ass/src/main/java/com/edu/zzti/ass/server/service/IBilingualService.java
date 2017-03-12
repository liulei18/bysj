package com.edu.zzti.ass.server.service;

import com.edu.zzti.ass.core.model.PageInfo;
import com.edu.zzti.ass.server.model.Bilingual;
import com.edu.zzti.ass.server.model.Lexicon;

public interface IBilingualService {

	
	public void save(Bilingual bilingual);
	
	public PageInfo<Bilingual> findAllLexicon(int currentPage,String key);
	
	public void delete(Integer id);

	public Bilingual getById(Integer id);

	public void update(Bilingual lexicon);

	public Bilingual find(String title);

	public Bilingual getRandom();
}
