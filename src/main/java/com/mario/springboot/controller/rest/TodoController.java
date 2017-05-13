package com.mario.springboot.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mario.springboot.model.Todo;
import com.mario.springboot.services.TodoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController("todoRestController")
@RequestMapping("/rest/todo")
@Api(value = "Todo managment system", description = "Operations pertaining to tasks and todo's online")
public class TodoController {

	@Autowired
	TodoService todoServices;

	@ApiOperation(value = "View a list of all Todo's tasks", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Todo> list(Model model) {
		List<Todo> todoList = todoServices.retrieveAllTodos();
		return todoList;
	}

	@ApiOperation(value = "Add a task in the Todo List")
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity saveProduct(@RequestBody Todo todo) {
		todoServices.addTodo(todo);
		return new ResponseEntity("Todo task saved successfully", HttpStatus.OK);
	}

	@ApiOperation(value = "Search a Todo task with an ID", response = Todo.class)
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
	public Todo showTodo(@PathVariable Integer id, Model model) {
		Todo todo = todoServices.retrieveTodo(id);
		return todo;
	}

	@ApiOperation(value = "Delete a task form Todo List")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity delete(@PathVariable Integer id) {
		todoServices.deleteTodo(id);
		return new ResponseEntity("Todo task deleted successfully", HttpStatus.OK);
	}

	@ApiOperation(value = "Update a task in the Todo list")
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody Todo todo) {
		Todo storedTodo = todoServices.retrieveTodo(id);
		storedTodo.setDesc(todo.getDesc());
		storedTodo.setIsDone(todo.getIsDone());
		storedTodo.setTargetDate(todo.getTargetDate());
		storedTodo.setUser(todo.getUser());
		todoServices.addTodo(storedTodo);
		return new ResponseEntity("Product updated successfully", HttpStatus.OK);
	}

}
