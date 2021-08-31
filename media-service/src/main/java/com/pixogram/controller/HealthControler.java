package com.pixogram.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("health")
public class HealthControler {
	
	@GetMapping
	public String getHealth() {
		return "Media Service is running.";
	}

}
