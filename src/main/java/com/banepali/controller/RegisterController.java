package com.banepali.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
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

@Controller
public class RegisterController {
	
	@Autowired
	private EmployeeService employeeService;

	
	@GetMapping("/register")
	public String registerUser() {
		return "register";
	}

	@PostMapping("/rregister")
	public String registerUserPost(@ModelAttribute EmployeeDTO employeeDTO, Model model) throws IOException {

		// MultipartFile into byte[]
		byte[] bPhoto = employeeDTO.getPhoto().getBytes();
		employeeDTO.setbPhoto(bPhoto);

		String check = "good";
		List<String> previousUserIds = employeeService.findAllUserId();
		System.out.println("From IndexController \n\n\n" + previousUserIds);
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

	// <img src="image?sid =1"
	@GetMapping("/image")
	public void showImage(@RequestParam int eid, HttpServletResponse httpServletResponse) throws IOException {
		byte[] photo = employeeService.findImageById(eid);
		ServletOutputStream outputStream = httpServletResponse.getOutputStream();
		if (photo != null) {
			httpServletResponse.setContentType("image/png");
			outputStream.write(photo);
		} else {
			outputStream.write(new byte[] {});
		}
		outputStream.flush();
	}

	
	 @InitBinder
	    public void initBinder(WebDataBinder binder) {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        sdf.setLenient(true);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	    }
	

	
	
	@GetMapping("/showAllContactInfo")
	public String contactUsPost(Model model) {
		List<EmployeeDTO> employeeList = new ArrayList<EmployeeDTO>();
		employeeList = employeeService.findAll();
		model.addAttribute("employeeList", employeeList);
		return "showAllContacts";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		return "logout";
	}

	@GetMapping("/showProfile")
	public String showProfile(@RequestParam String email, HttpSession session, Model model) {

		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO = employeeService.employeeByEmail(email);
		model.addAttribute("entity", employeeDTO);
		return "showProfile";

	}

	@GetMapping("/showAllEmployees")
	public String showAllEmployees(Model model) {

		List<EmployeeDTO> employeeDTOs = new ArrayList<EmployeeDTO>();
		employeeDTOs = employeeService.findAll();
		model.addAttribute("employeeList", employeeDTOs);
		return "showAllData";

	}

	@GetMapping("/showAllEmployeesBlockTime")
	public String showAllEmployeesBlockTime(Model model) {
		List<EmployeeDTO> employeeList = new ArrayList<EmployeeDTO>();
		employeeList = employeeService.findAll();
		model.addAttribute("employeeList", employeeList);
		return "showAllDataBlockTime";

	}

	@GetMapping("/deletePerson")
	public String deleteData(@RequestParam String email) {
		employeeService.deleteByEmail(email);
		return "redirect:/showAllEmployees";
	}

	@GetMapping("/editPerson")
	public String editData(@RequestParam String userId, Model model) {
		EmployeeDTO employeeDTO = employeeService.employeeByUserId(userId);
		model.addAttribute("message", "Please edit the fields you like to update.");
		model.addAttribute("employeeEntity", employeeDTO);
		return "editEmploeeData";
	}

	@PostMapping("/updateEmployeeData")
	public String updateData(@ModelAttribute EmployeeDTO employeeDTO, Model model) {
		System.out.println("DataTableController " + employeeDTO);

		employeeService.updateEmployee(employeeDTO);
		model.addAttribute("message", "You have successfully updated.");
		return "redirect:/showAllEmployees";

	}

}
