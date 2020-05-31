package com.banepali.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.banepali.dataBase.dao.EmployeeDao;
import com.banepali.dataBase.dao.EmployeeDaoImpl;
import com.banepali.dataBase.dao.entity.EmployeeEntity;
import com.banepali.dataBase.utils.DateUtils;



@Controller
public class RegisterController {

	@GetMapping("/register")
	public String registerUser(@ModelAttribute EmployeeEntity employeeEntity,
			Model model) {
		
		String check = "good";
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		List<String> previousUserIds = employeeDao.findAllUserId();

		if (previousUserIds.contains(employeeEntity.getUserId())) {
			check = "Sorry,      \"" + employeeEntity.getUserId() + "\"     is already taken.";
		}
		
		Date dateFromStringAgain = employeeEntity.getDate();

		long mob = Long.parseLong(mobile);
	
		double sal = Double.parseDouble(salary);
		
		long sssn = Long.parseLong(ssn);
		
		if (check != "good") {
			model.addAttribute("message", check);
			return "register";

		} else {

			EmployeeEntity entity = new EmployeeEntity(employeeDao.getIncrementedEId(), userid, password, name, email,
					dateFromStringAgain, mob, sal, sssn, null, null, new EmployeeEntity().getRole(), new EmployeeEntity().getStartTime(), new EmployeeEntity().getEndTime(), new EmployeeEntity().getActive());
			employeeDao.save(entity);

			model.addAttribute("message", "You have succcessfully registered..");
			return "register";		

		}
	}
}

