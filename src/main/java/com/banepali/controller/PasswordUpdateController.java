package com.banepali.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.banepali.controller.dto.EmployeeDTO;
import com.banepali.service.EmployeeService;
import com.banepali.service.EmployeeServiceImpl;

@Controller
public class PasswordUpdateController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/validateEmail")
	public String validateEmail(@RequestParam String email, HttpSession session, Model model) {
		employeeService = new EmployeeServiceImpl();
		Optional<EmployeeDTO> employeeDTO = employeeService.optionalEmployeeByEmail(email);
		if (employeeDTO.isPresent()) {
			session.setAttribute("userData", employeeDTO.get());
			// "req.setAttribut()"//this only gives to the forwarded page
			return "getNewPasswords";
		} else {
			model.addAttribute("message", "Sorry, Email not found. Try again");
			return "validateEmail";
		}
	}

	@GetMapping("/getNewPasswords")
	public String getNewPasswords() {
		return "getNewPasswords";
	}
	@PostMapping("/getNewPasswords")
	public String getNewPasswords(@RequestParam String password1, @RequestParam String password2,
			@RequestParam String email, HttpSession session, Model model) {
		if (password1.equals(password2)) {

			employeeService = new EmployeeServiceImpl();
			// EmployeeEntity employeeEntity = employeeDao.employeeByEmail(email);
			String result = employeeService.updatePassword(email, password1);

			if (result.equals("Update Successful")) {
				// request.setAttribute("updatedDataEmail", employeeEntity.getEmail());
				model.addAttribute("message", "Password changed successfully.");
				return "index";
			} else {
				model.addAttribute("message", "Server Connection problem. Please Try Again.");
				return "getNewPasswords";
			}

		} else {
			model.addAttribute("message", "Passwords do not match.");
			return "getNewPasswords";
		}
	}

}
