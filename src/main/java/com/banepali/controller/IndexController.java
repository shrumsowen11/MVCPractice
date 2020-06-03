package com.banepali.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.banepali.controller.dto.EmployeeDTO;
import com.banepali.service.EmployeeService;


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
		 * 
		 * redirect:/   "; --> this is used to send you to the another action mapping,
		 * instead of the .jsp file
		 */
	}

	@PostMapping("/login") //these are the buttons made in the .jsp file before
	public String loginPost(@RequestParam String temail, @RequestParam String password, Model model,
			HttpSession session) {

		// public String loginPost(HttpServletRequest req) {
		// "Using HttpsServletRequest" like this in code in not a good practice, use
		// @Requestparam
		// Similarly, for setting the data and sending them, we use the Model


		/*
		 * EmployeeDTO optionalEmplDTO = empl oyeeService.employeeLogin(temail,
		 * password);
		 * 
		 * if (optionalEmplDTO != null) { session.setAttribute("userData",
		 * optionalEmplDTO); return "dashboard";
		 * 
		 * } else { model.addAttribute("message",
		 * "Sorry, Incorrect Email or password."); return "index";
		 * 
		 * }
		 */
		
		Optional<EmployeeDTO> optionalEmplDTO = employeeService.employeeLogin(temail, password);

		if (optionalEmplDTO.isPresent()) {
			model.addAttribute("userData", optionalEmplDTO);

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

	@PostMapping("/rregister")
	public String registerUserPost(@ModelAttribute EmployeeDTO employeeDTO, Model model) {

		String check = "good";
		List<String> previousUserIds = employeeService.findAllUserId();

		if (previousUserIds.contains(employeeDTO.getUserId())) {
			
			System.out.println(employeeDTO.getUserId());
			check = "Sorry,      \"" + employeeDTO.getUserId() + "\"     is already taken.";
		}

		if (check != "good") {
			model.addAttribute("message", check);
			return "register";

		} else {
			employeeService.save(employeeDTO);
			model.addAttribute("message", "You have succcessfully registered..");
			return "index";
		}

	}
	
	@InitBinder    
	 public void initBinder(WebDataBinder binder){
	     binder.registerCustomEditor( Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));   
	}
	
	
	@GetMapping("/forgotPassword")
	public String forgotPassword() {
		return "validateEmail";
	}
	
	}
