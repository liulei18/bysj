package com.edu.zzti.ass.management.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.zzti.ass.management.dao.IUserDao;
import com.edu.zzti.ass.management.model.User;

@Repository("userDao")
public class UserDaoImpl implements IUserDao {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Serializable save(User user) {
		return this.getSession().save(user);
	}

	@Override
	public List<User> findUser(User user) {
		String hql = "from User as user where user.username = :username and user.password= :password";
		Query query = this.getSession().createQuery(hql);
		query.setString("username", user.getUsername());
		query.setString("password", user.getPassword());
		return query.list();
	}

	@Override
	public void update(User user) {
		this.getSession().update(user);
	}

	@Override
	public List<User> findAll() {

		return this.getSession().createQuery("from User").list();
	}

}
