package com.edu.zzti.ass.management.dao;

import java.io.Serializable;
import java.util.List;

import com.edu.zzti.ass.management.model.User;


public interface IUserDao {

	public Serializable save(User user);
	
	public List<User> findUser(User user);
	
	public void update(User user);
	
	public List<User> findAll();
	
	
}
