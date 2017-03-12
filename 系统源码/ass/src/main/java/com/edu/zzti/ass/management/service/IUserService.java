package com.edu.zzti.ass.management.service;

import java.io.Serializable;
import java.util.List;

import com.edu.zzti.ass.management.model.User;

public interface IUserService {

	public Serializable save(User user);
	
	public User findUser(User user);
	
	public void update(User user);
	
	public List<User> findAll();
	
}
