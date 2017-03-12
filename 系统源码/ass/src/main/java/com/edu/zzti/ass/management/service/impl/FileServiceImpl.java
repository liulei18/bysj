package com.edu.zzti.ass.management.service.impl;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.zzti.ass.core.model.PageInfo;
import com.edu.zzti.ass.management.dao.IFileDao;
import com.edu.zzti.ass.management.model.TFile;
import com.edu.zzti.ass.management.service.IFileService;
import com.edu.zzti.ass.testlibrary.dao.ITunitDao;
import com.edu.zzti.ass.testlibrary.model.TUnit;

@Service("fileService")
public class FileServiceImpl implements IFileService{

	@Autowired
	private ITunitDao unitDao;
	@Autowired
	private IFileDao fileDao;
	
	@Override
	public List<TUnit> findUnit() {
		
		return unitDao.findAllUnit();
	}

	@Override
	public Serializable save(TFile file) {
		
		return fileDao.save(file);
	}

	@Override
	public List<TFile> findByUnitId(Integer unitId,Integer type) {
		
		return fileDao.findByUnitId(unitId,type);
	}

	@Override
	public PageInfo<TFile> findByPage(Integer currentPage,Integer type) {
		PageInfo<TFile> pageInfo = new PageInfo<TFile>();
		int count = fileDao.countFile(type).intValue();
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
		List<TFile> list =fileDao.getListByPage(currentPage,type);

		pageInfo.setData(list);

		return pageInfo;
	}

	@Override
	public void pptDelete(Integer id,String url) {
		TFile tfile = fileDao.getById(TFile.class, id);
		File file = new File(url+tfile.getUrl());
		if(file.exists()){
			file.delete();
			fileDao.delete(tfile);
		}
		
		
	}
	
	
	

}
