package com.banepali.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.banepali.controller.dto.EmployeeDTO;
import com.banepali.service.EmployeeService;
import com.banepali.service.EmployeeServiceImpl;


//@Repository(helloController)  //we can use this but, as this is a model, we must use the "@Controller"
@Controller
public class IndexController {
	
	@Autowired
	private EmployeeService employeeService;
	
	// Method = GET
	// Action = login //calling this in website
	@GetMapping({ "/" })
	public String indexPage() {
		return "index";
		// here, you do not give the extension,
		// otherwise that will be depended upon
		// that specific extension, which is not good practice
		/*
		 * @PostMapping("/login") public String aclass() { return "index";
		 */
	}

	@PostMapping("/login") //these are the buttons made in the .jsp file before
	public String loginPost(@RequestParam String temail, @RequestParam String password, Model model,
			HttpSession session) {

		// public String loginPost(HttpServletRequest req) {
		// "Using HttpsServletRequest" like this in code in not a good practice, use
		// @Requestparam
		// Similarly, for setting the data and sending them, we use the Model


		EmployeeDTO optionalEmplDTO = employeeService.employeeLogin(temail, password);

		if (optionalEmplDTO != null) {
			session.setAttribute("userData", optionalEmplDTO);

			// req.getRequestDispatcher("dashboard.jsp").forward(req,resp);
			return "dashboard";

		} else {
			model.addAttribute("message", "Sorry, Incorrect Email or password.");
			return "index";

		}

	}
	
	
	@GetMapping("/register")
	public String registerUser() {
		return "register";
	}

	@PostMapping("/register")
	public String registerUserPost(@ModelAttribute EmployeeDTO employeeDTO, Model model) {
		

		/*
		 * When you want to read all the attribute from the EmployeeEntity, you will not
		 * be doing the @RequestParam String again and again, so get the whole
		 * EmployeeEntity and you may then access the attributes of the EmployeeEntity
		 * from thw itself.
		 * 
		 * 
		 * "redirect:/   "; --> this is used to send you to the another action mapping,
		 * instead of the .jsp file
		 */

		String check = "good";
		List<String> previousUserIds = employeeService.findAllUserId();

		if (previousUserIds.contains(employeeDTO.getUserId())) {
			check = "Sorry,      \"" + employeeDTO.getUserId() + "\"     is already taken.";
		}

		if (check != "good") {
			model.addAttribute("message", check);
			return "register";

		} else {
			employeeService.save(employeeDTO);
			model.addAttribute("message", "You have succcessfully registered..");
			return "redirect:/index";
		}

	}
	
	
	
	@GetMapping("/validateEmail")
	public String forgotPassword() {
		return "validateEmail";
	}
	@PostMapping("/validateEmail")
	public String forgotPasswordPost(@RequestParam String temail, Model model, HttpSession session) {
		
		
		EmployeeService employeeService = new EmployeeServiceImpl();
		Optional<EmployeeDTO> employeeDTO = employeeService.optionalEmployeeByEmail(temail);
	
			if(employeeDTO.isPresent()) {
				session.setAttribute("userData", employeeDTO.get());
				//"req.setAttribut()"//this only gives to the forwarded page
				return "getNewPasswords";
		}else {
			
			model.addAttribute("message","Sorry, Email not found. Try again");
			return "emailValidate";
		}
	}

}
