package com.edu.zzti.ass.management.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edu.zzti.ass.core.dao.impl.BaseDaoImpl;
import com.edu.zzti.ass.management.dao.IStudentDao;
import com.edu.zzti.ass.management.model.TStudent;

@Repository("studentDao")
public class StudentDaoImpl extends BaseDaoImpl<TStudent> implements
		IStudentDao {

	@Override
	public TStudent findStudent(TStudent student) {
		String hql = "from TStudent as s where s.id = '"
				+ student.getId() + "' and s.pwd= '"
				+ student.getPwd() + "'";
		return super.getByHql(hql);
	}

	@Override
	public TStudent getOne(String id) {
		
		return super.getByHql("from TStudent as s where s.id = "+id);
	}

	@Override
	public void updateInfo(TStudent temp) {
		Map<String ,Object> map =new HashMap<String ,Object>();
		map.put("id",temp.getId() );
		map.put("descs", temp.getDescs());
		map.put("email", temp.getEmail());
		map.put("imgurl", temp.getImgurl());
		map.put("remark", temp.getRemark());
		String sql = "update t_student set descs = :descs, email "
				+ "= :email , imgurl = :imgurl, remark = :remark where id = :id";
		super.executeSql(sql, map);
	}


	@Override
	public List<TStudent> getall(Integer classId) {
		String hql ="from TStudent as t  where  t.TClass.id = "+classId;
		return super.find(hql);
	}


	@Override
	public List<TStudent> find(Integer classId) {
		String hql ="from TStudent  where classId = "+classId+"";
		return super.find(hql);
	}
}
