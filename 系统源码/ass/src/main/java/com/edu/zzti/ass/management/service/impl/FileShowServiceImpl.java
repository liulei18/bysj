package com.edu.zzti.ass.management.service.impl;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.zzti.ass.core.model.PageInfo;
import com.edu.zzti.ass.management.dao.IFileDao;
import com.edu.zzti.ass.management.dao.IFileShowDao;
import com.edu.zzti.ass.management.model.TFile;
import com.edu.zzti.ass.management.model.TFileShow;
import com.edu.zzti.ass.management.service.IFileService;
import com.edu.zzti.ass.management.service.IFileShowService;
import com.edu.zzti.ass.testlibrary.dao.ITunitDao;
import com.edu.zzti.ass.testlibrary.model.TUnit;

@Service("fileShowService")
public class FileShowServiceImpl implements IFileShowService{

	@Autowired
	private ITunitDao unitDao;
	@Autowired
	private IFileDao fileDao;
	
	@Autowired
	private IFileShowDao fileShowDao;
	
	@Override
	public Serializable save(TFileShow file) {
		return fileShowDao.save(file);
	}


	@Override
	public PageInfo<TFileShow> findByPage(Integer currentPage,Integer type) {
		PageInfo<TFileShow> pageInfo = new PageInfo<TFileShow>();
		int count = fileShowDao.countFile(type).intValue();
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
		List<TFileShow> list =fileShowDao.getListByPage(currentPage,type);

		pageInfo.setData(list);

		return pageInfo;
	}

	@Override
	public void targetDelete(Integer id,String url) {
		TFileShow tFileShow = fileShowDao.getById(TFileShow.class, id);
		File file = new File(url+tFileShow.getUrl());
		if(file.exists()){
			file.delete();
			fileShowDao.delete(tFileShow);
		}
		
		
	}


	@Override
	public List<TFileShow> findByFKey(Integer fkey, Integer type) {
		return fileShowDao.findByFKey(fkey,type);
	}


	



}
