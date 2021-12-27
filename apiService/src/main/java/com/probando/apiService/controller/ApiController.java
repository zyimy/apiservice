package com.probando.apiService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ApiController {

	//@GetMapping
	@RequestMapping(value = "hola", method=RequestMethod.GET)
	public String hola() {
		return "Hola";
	}
}
