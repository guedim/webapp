package com.mario.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mario.springboot.security.UserDetailComponent;

@Controller
public class WellcomeController {

	@Autowired
	UserDetailComponent userDetailComponent;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String showWellcomesPage(ModelMap model){
		model.put("name", userDetailComponent.getLoggedInUserName());
		return "welcome";
	}
	
	
	
	
	
}