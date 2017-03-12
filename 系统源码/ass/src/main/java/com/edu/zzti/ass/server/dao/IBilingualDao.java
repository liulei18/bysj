package com.edu.zzti.ass.server.dao;

import java.util.List;

import com.edu.zzti.ass.core.dao.IBaseDao;
import com.edu.zzti.ass.server.model.Bilingual;
import com.edu.zzti.ass.server.model.Lexicon;

public interface IBilingualDao extends IBaseDao<Bilingual>{

	public Long countBilingual(String key);
	
	public List<Bilingual>  getListByPage(int currentPage,String key);

	public void deleteBilingual(Integer id);

	public void updateBil(Bilingual bil);

	public Bilingual findBilingual(String title);

	public Long getCount();

	public Bilingual getOne(int random);
}
