package com.edu.zzti.ass.server.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.zzti.ass.core.model.PageInfo;
import com.edu.zzti.ass.server.dao.IBilingualDao;
import com.edu.zzti.ass.server.dao.ILexiconDao;
import com.edu.zzti.ass.server.model.Bilingual;
import com.edu.zzti.ass.server.model.Lexicon;
import com.edu.zzti.ass.server.service.IBilingualService;
import com.edu.zzti.ass.server.service.ILexiconService;

@Service("bilingualService")
public class BilingualServiceImpl implements IBilingualService {

	@Autowired
	private ILexiconDao lexiconDao;
	
	@Autowired
	private IBilingualDao bilingualDao;

	@Override
	public void save(Bilingual bilingual) {
		bilingualDao.save(bilingual);

	}

	@Override
	public PageInfo<Bilingual> findAllLexicon(int currentPage, String key) {
		PageInfo<Bilingual> pageInfo = new PageInfo<Bilingual>();
		int count = bilingualDao.countBilingual(key).intValue();
		pageInfo.setTotalRecords(count);
		int totalPages = 0;
		if (count > 9) {
			totalPages=count % 10 == 0 ? (count / 10)
					: count / 10 + 1;
		} else {
			totalPages =1;
		}
		pageInfo.setTotalPages(totalPages);
		pageInfo.setCurrentPage(currentPage>totalPages?totalPages:currentPage);
		List<Bilingual> list =bilingualDao.getListByPage(currentPage, key);
		for(Bilingual  bilingual :list ){
			bilingual.setEnContent(bilingual.getEnContent().length()>40?bilingual.getEnContent().substring(0, 40)+"...":bilingual.getEnContent());
			bilingual.setZhContent(bilingual.getZhContent().length()>30?bilingual.getZhContent().substring(0,30)+"...":bilingual.getZhContent());
		}
		pageInfo.setData(list);

		return pageInfo;
	}

	@Override
	public void delete(Integer id) {
		bilingualDao.deleteBilingual(id);
	}

	@Override
	public Bilingual getById(Integer id) {
		return bilingualDao.getById(Bilingual.class, id);
	}

	@Override
	public void update(Bilingual bil) {
		bilingualDao.updateBil(bil);
	}

	@Override
	public Bilingual find(String title) {
		return bilingualDao.findBilingual(title);
	}

	@Override
	public Bilingual getRandom() {
		int num =  bilingualDao.getCount().intValue();
		int random = new Random().nextInt(num+1);
		
		return bilingualDao.getOne(random);
	}

}
