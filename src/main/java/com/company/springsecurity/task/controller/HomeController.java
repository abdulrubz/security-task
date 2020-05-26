package com.company.springsecurity.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String showHome() {
		return "homepage";
	}
	
	@GetMapping("/manager")
	public String showManager() {
		return "managers";
	}
	
	@GetMapping("/admin")
	public String showAdmin() {
		return "admins";
	}

}
