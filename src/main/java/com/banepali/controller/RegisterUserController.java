package com.banepali.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.banepali.service.EmployeeService;

@Controller
public class RegisterUserController {

	@Autowired
	private EmployeeService employeeService;

}
