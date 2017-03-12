package com.edu.zzti.ass.management.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edu.zzti.ass.core.dao.impl.BaseDaoImpl;
import com.edu.zzti.ass.management.dao.ITeacherDao;
import com.edu.zzti.ass.management.model.TTeacher;

@Repository("teacherDao")
public class TeacherDaoImpl extends BaseDaoImpl<TTeacher> implements
		ITeacherDao {

	@Override
	public TTeacher findTeacher(TTeacher teacher) {
		String hql = "from TTeacher as teacher where teacher.tname = '"
				+ teacher.getTname() + "' and teacher.tpwd= '"
				+ teacher.getTpwd() + "'";
		return super.getByHql(hql);
	}

	@Override
	public Serializable save(TTeacher teacher) {
		
		return super.save(teacher);
	}

	@Override
	public TTeacher getOne(String id) {
		
		return super.getByHql("from TTeacher as t where t.id = '"+id+"'");
	}

	@Override
	public void updateInfo(TTeacher temp) {
		Map<String ,Object> map =new HashMap<String ,Object>();
		map.put("id",temp.getId() );
		map.put("descs", temp.getDescs());
		map.put("email", temp.getEmail());
		map.put("imgurl", temp.getImgurl());
		map.put("remark", temp.getRemark());
		String sql = "update t_teacher set descs = :descs, email "
				+ "= :email , imgurl = :imgurl, remark = :remark where id = :id";
		super.executeSql(sql, map);
		
	}

	@Override
	public void updateSql(String pwd,String id) {
		String sql = "update t_teacher set tpwd = "+pwd+" where id='"+id+"'";
		super.executeSql(sql);
		
	}

}
