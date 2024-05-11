package com.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entities.Todo;

@Controller
public class HomeController {
	
	@Autowired
	ServletContext context;
	
	@RequestMapping("/home")
	public String home(Model model) {
		String str = "home";
		model.addAttribute("page", str);
		
		List<Todo> todos = (List<Todo>) context.getAttribute("list");
		model.addAttribute("todos", todos);
		
		return "home";
	}
	
	@RequestMapping("/add")
	public String addTodo(Model model) {
		
		Todo todo = new Todo();
		model.addAttribute("page", "add");
		model.addAttribute("todo", todo);
		return "home";
	}

	@RequestMapping(value = "/saveTodo", method = RequestMethod.POST)
	public String saveTodo(@ModelAttribute("todo") Todo t, Model model) {
		System.out.println(t);
		t.setTodoDate(new Date());
		
		List<Todo> todos = (List<Todo>) context.getAttribute("list");
		todos.add(t);
		model.addAttribute("msg", "successfully added...");
		return "home";
	}
}
