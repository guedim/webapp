package com.mario.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mario.springboot.services.TodoService;

@Controller
public class TodoController {

	@Autowired
	TodoService todoServices;
	
	@RequestMapping(value="/list-todos", method = RequestMethod.GET)
	public String showTodoPage(ModelMap model){
		model.put("todos", todoServices.retrieveTodos("guedim"));
		return "list-todos";
	}
}