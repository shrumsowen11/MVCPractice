package com.banepali.controller;

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

//@Repository(helloController)  //we can use this but, as this is a model, we must use the "@Controller"
@Controller
public class LoginController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping({ "/", "index" })
	public String indexPage() {
		return "index";

		/*
		 * redirect:/ "; --> this is used to send you to the another action mapping,
		 * instead of the .jsp file
		 */
	}

	@PostMapping("/login") 
	public String loginPost(@RequestParam String temail, @RequestParam String password, Model model,
			HttpSession session) {

		EmployeeDTO optionalEmplDTO = employeeService.employeeLogin(temail, password);
		// As we do not guarantee the incoming emploeeeEntity is the actual Employee or
		// a null value,
		// we need to check.
		// --> checking if the incoming employeeEntity is null or some actual values
		// --> this actually needs to be done in the "ServiceImpl"
		if (optionalEmplDTO != null) {
			session.setAttribute("userData", optionalEmplDTO);
			// session.setAttribute("userData", optionalEmplDTO.get()); --> is done when .isPresent() is used
			return "dashboard";
		} else {
			model.addAttribute("message", "Sorry, Incorrect Email or password.");
			return "index";
		}
	}
	
	@GetMapping("/dashboard")
	public String openDashboard() {
		return "dashboard";
	}

	@PostMapping("/dashboard")
	public String openDashboardPost() {
		return "redirect:/login";
	}

	
	
	@GetMapping("/validateEmail")
	public String forgotPassword() {
		return "validateEmail";
	}

	@PostMapping("/validateEmailSearch")
	public String validateEmail(@RequestParam String temail, HttpSession session, Model model) {
		employeeService = new EmployeeServiceImpl();
		EmployeeDTO employeeDTO = employeeService.optionalEmployeeByEmail(temail);
		System.out.println("From IndexController" + temail);
		String output = null;
		try {
			if (employeeDTO != null) {
				session.setAttribute("userData", employeeDTO);
				System.out.println(temail + " found. --> From IndexController");
				output = "getNewPasswords";
			} else {
				model.addAttribute("message", "Sorry, Email not found. Try again");
				output = "validateEmail";
			}
		} catch (Exception e) {
		}
		return output;
	}

	@GetMapping("/getNewPasswords")
	public String getNewPasswords() {
		return "getNewPasswords";
	}

	@PostMapping("/updatePasswords")
	public String getNewPasswordsPost(@RequestParam String password1, @RequestParam String password2,
			@RequestParam String email, HttpSession session, Model model) {
		if (password1.equals(password2)) {
			employeeService = new EmployeeServiceImpl();
			String result = employeeService.updatePassword(email, password1);

			if (result.equals("Update Successful")) {
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
