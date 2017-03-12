package com.edu.zzti.ass.server.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.zzti.ass.management.dao.IStudentDao;
import com.edu.zzti.ass.management.dao.ITeacherDao;
import com.edu.zzti.ass.management.model.TStudent;
import com.edu.zzti.ass.management.model.TTeacher;
import com.edu.zzti.ass.server.service.IInfoService;

@Service("infoService")
public class InfoServiceImpl implements IInfoService {

	@Autowired
	private IStudentDao studentDao;
	@Autowired
	private ITeacherDao teacherDao;

	@Override
	public String updateInfo(Object object, String type, String path) {

		if (type.equals("student")) {
			TStudent student = (TStudent) object;
			TStudent temp = studentDao.getOne(student.getId());
			temp.setDescs(student.getDescs());
			temp.setEmail(student.getEmail());
			if (student.getImgurl() != null) {
				String filepath = path + temp.getImgurl();
				File file = new File(filepath);
				if(file.exists()){
					file.delete();
				}
				temp.setImgurl(student.getImgurl());
			} else {
				temp.setImgurl(temp.getImgurl());
			}
			temp.setRemark(student.getRemark());
			studentDao.updateInfo(temp);
			return temp.getImgurl();
		} else {
			TTeacher teacher = (TTeacher) object;
			TTeacher temp = teacherDao.getOne(teacher.getId());
			temp.setDescs(teacher.getDescs());
			temp.setEmail(teacher.getEmail());
			if (teacher.getImgurl() != null) {
				String filepath = path + temp.getImgurl();
				File file = new File(filepath);
				if(file.exists()){
					file.delete();
				}
				temp.setImgurl(teacher.getImgurl());
			} else {
				temp.setImgurl(temp.getImgurl());
			}
			temp.setRemark(teacher.getRemark());
			teacherDao.updateInfo(temp);
			return temp.getImgurl();
		}

	}

}
