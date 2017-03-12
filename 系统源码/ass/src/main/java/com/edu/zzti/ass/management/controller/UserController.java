package com.edu.zzti.ass.management.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.edu.zzti.ass.management.model.TTeacher;
import com.edu.zzti.ass.management.model.User;
import com.edu.zzti.ass.management.service.IUserService;

@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private IUserService userService;
	
    @RequestMapping(value="index", method = {RequestMethod.GET})
    public ModelAndView index(HttpSession httpSession ){
        
        ModelAndView mav = new ModelAndView();  
	      Object obj =  httpSession.getAttribute("userName");
	      if(obj==null){
	    	  httpSession.setAttribute("userName", "none");
	      }
      
        mav.addObject("message", "Hello World!");  
        mav.setViewName("index");
        return mav;
    }
    
  
   
}

