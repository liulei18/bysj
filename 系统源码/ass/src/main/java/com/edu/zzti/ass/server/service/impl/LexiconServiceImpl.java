package com.edu.zzti.ass.server.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.zzti.ass.core.model.PageInfo;
import com.edu.zzti.ass.server.dao.ILexiconDao;
import com.edu.zzti.ass.server.model.Lexicon;
import com.edu.zzti.ass.server.service.ILexiconService;

@Service("lexiconService")
public class LexiconServiceImpl implements ILexiconService {

	@Autowired
	private ILexiconDao lexiconDao;

	@Override
	public void save(Lexicon lexicon) {
		lexiconDao.save(lexicon);

	}

	@Override
	public PageInfo<Lexicon> findAllLexicon(int currentPage, String key) {
		PageInfo<Lexicon> pageInfo = new PageInfo<Lexicon>();
		int count = lexiconDao.countLexicon(key).intValue();
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
		List<Lexicon> list =lexiconDao.getListByPage(currentPage, key);
		for(Lexicon  lexicon :list ){
			lexicon.setEnMean(lexicon.getEnMean().length()>40?lexicon.getEnMean().substring(0,40)+"...":lexicon.getEnMean());
			lexicon.setZhMean(lexicon.getZhMean().length()>30?lexicon.getZhMean().substring(0,30)+"...":lexicon.getZhMean());
		}
		pageInfo.setData(list);

		return pageInfo;
	}

	@Override
	public void delete(Integer id) {


		lexiconDao.deleteLexicon(id);
	}

	@Override
	public Lexicon getById(Integer id) {
		return lexiconDao.getById(Lexicon.class, id);
	}

	@Override
	public void update(Lexicon lexicon) {
		
		lexiconDao.updateLex(lexicon);
	}

	@Override
	public Lexicon find(String word) {
		
		
		return lexiconDao.findLexicon(word);
		
		
	}

	@Override
	public Lexicon getRandom() {
		int num =  lexiconDao.getCount().intValue();
		int random = new Random().nextInt(num+1);
		
		return lexiconDao.getOne(random);
	}

}
