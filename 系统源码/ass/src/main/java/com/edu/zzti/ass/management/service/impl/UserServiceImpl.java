package com.edu.zzti.ass.management.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.zzti.ass.management.dao.IUserDao;
import com.edu.zzti.ass.management.model.User;
import com.edu.zzti.ass.management.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao userDao;




	@Override
	public Serializable save(User user) {
		
		return userDao.save(user);
	}

	@Override
	public void update(User user) {
	userDao.update(user);
		
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User findUser(User user) {
		 List<User> list =userDao.findUser(user);
		if(list!=null&&list.size()>0){
			return  list.get(0);
		}
		return null;
	}

}

