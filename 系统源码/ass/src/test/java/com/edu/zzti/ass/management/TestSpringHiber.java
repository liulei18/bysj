package com.edu.zzti.ass.management;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.edu.zzti.ass.management.model.User;
import com.edu.zzti.ass.management.service.IUserService;


public class TestSpringHiber {
	@Test
	public void testHiber() {

		User user = new User();
		user.setPassword("ssssss");
		user.setRealName("zhang");
		user.setUsername("aaaaa");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:spring-hibernate.xml" ,"classpath:spring.xml"
						});
		IUserService userService = context.getBean("userService",IUserService.class);
		userService.save(user);

	}
	
	
	@Test
	public void testFindAlll(){
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:spring-hibernate.xml" ,"classpath:spring.xml"
						});
		IUserService userService = context.getBean("userService",IUserService.class);
		System.out.println(userService.findAll().get(0));
	}
}
