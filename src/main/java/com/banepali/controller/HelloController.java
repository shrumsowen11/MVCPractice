package com.banepali.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@Repository(helloController)  //we can use this but, as this is a model, we must use the "@Controller"
@Controller
public class HelloController {

	//Method  = GET
	//Action  = ful   //calling this
	@GetMapping("/ful")
	public String aclass() {
		return "index";  
		//here, you do not give the extension, 
		//otherwise that will be depended upon 
		//that specific extension, which is not good practice
		
		
	}
}
