/*
 * package com.banepali.controller;
 * 
 * import org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.RequestParam;
 * 
 * import com.banepali.dataBase.dao.EmployeeDao; import
 * com.banepali.dataBase.dao.EmployeeDaoImpl;
 * 
 * 
 * 
 * @Controller public class DeleteController {
 * 
 * 
 * @GetMapping("/delete") public String deleteUser(@RequestParam String email,
 * Model model) {
 * 
 * 
 * EmployeeDao employeeDao = new EmployeeDaoImpl();
 * employeeDao.deleteByEmail(email); return"redirect:/showAllEmployeesServlet";
 * }
 * 
 * }
 */