package com.banepali.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.banepali.dataBase.dao.EmployeeDao;
import com.banepali.dataBase.dao.entity.EmployeeEntity;

//@Repository(helloController)  //we can use this but, as this is a model, we must use the "@Controller"
@Controller
public class LoginController {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	// Method = GET
	// Action = login //calling this in website
	@GetMapping({ "/" })
	public String loginPage() {
		return "index";
		// here, you do not give the extension,
		// otherwise that will be depended upon
		// that specific extension, which is not good practice
		/*
		 * @PostMapping("/login") public String aclass() { return "index";
		 */
	}

	@PostMapping("/index")
	public String loginPost(@RequestParam String temail, @RequestParam String password, Model model,
			HttpSession session) {

		// public String loginPost(HttpServletRequest req) {
		// "Using HttpsServletRequest" like this in code in not a good practice, use
		// @Requestparam
		// Similarly, for setting the data and sending them, we use the Model

		

		EmployeeEntity optionalEmplEntity = employeeDao.employeeLogin(temail, password);

		if (optionalEmplEntity != null) {
			session.setAttribute("userData", optionalEmplEntity);

			// req.getRequestDispatcher("dashboard.jsp").forward(req,resp);
			return "dashboard";

		} else {
			model.addAttribute("message", "Sorry, Incorrect Email or password.");
			return "index";

		}

	}
}
