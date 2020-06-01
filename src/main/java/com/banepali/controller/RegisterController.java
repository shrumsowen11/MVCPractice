package com.banepali.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.banepali.dataBase.dao.EmployeeDao;
import com.banepali.dataBase.dao.entity.EmployeeEntity;



@Controller
public class RegisterController {
	
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute EmployeeEntity employeeEntity, Model model) {
		
		String check = "good";
		List<String> previousUserIds = employeeDao.findAllUserId();

		if (previousUserIds.contains(employeeEntity.getUserId())) {
			check = "Sorry,      \"" + employeeEntity.getUserId() + "\"     is already taken.";
		}
		
		if (check != "good") {
			model.addAttribute("message", check);
			return "register";

		} else {	
			employeeDao.save(employeeEntity);
			model.addAttribute("message", "You have succcessfully registered..");
			return "redirect:/index";
		}
	}
}

