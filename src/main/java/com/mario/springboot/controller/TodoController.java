package com.mario.springboot.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mario.springboot.model.Todo;
import com.mario.springboot.security.UserDetailComponent;
import com.mario.springboot.services.TodoService;

@Controller
public class TodoController {


	@Autowired
	UserDetailComponent userDetailComponent;
	
	@Autowired
	TodoService todoServices;
	
	@RequestMapping(value="/list-todos", method = RequestMethod.GET)
	public String showTodoPage(ModelMap model){
		model.put("todos", todoServices.retrieveTodos(userDetailComponent.getLoggedInUserName()));
		return "list-todos";
	}

	
	@RequestMapping(value="/add-todo", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model){
		model.put("todo", new Todo(0, userDetailComponent.getLoggedInUserName(), null, new Date(), false));
		return "todo";
	}
	
	@RequestMapping(value="/add-todo", method = RequestMethod.POST)
	public String addTodoPage(ModelMap model, @Valid  Todo todo,  BindingResult result){
		if(result.hasErrors()){
			return "todo";
		}
		todoServices.addTodo(userDetailComponent.getLoggedInUserName(), todo.getDesc(), new Date(), false);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value="/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id){
		todoServices.deleteTodo(id);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value="/update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model){
		Todo todo = todoServices.retrieveTodo(id);
		model.put("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value="/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap map,  @Valid Todo todo, BindingResult result){
		if(result.hasErrors()){
			return "todo";
		}
		todo.setUser(userDetailComponent.getLoggedInUserName());
		todoServices.updateTodo(todo);
		return "redirect:/list-todos";
	}


	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	
}