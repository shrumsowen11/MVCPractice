package com.banepali.service;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

import com.banepali.controller.dto.EmployeeDTO;

public interface EmployeeService {

	void save(EmployeeDTO employeeDTO);

	List<EmployeeDTO> findAll();
	List<String> findAllUserId();

	EmployeeDTO employeeById(int eID);
	EmployeeDTO employeeByEmail(String email);
	EmployeeDTO employeeByUserId(String userId);
	Optional<EmployeeDTO> optionalEmployeeByEmail(String email);

	EmployeeDTO employeeLogin(String email, String password);
    
    void update(EmployeeDTO employeeDTO);
	void updatePassword(String email, String password);
	void updateEmployeeActiveStatus(String userId);
    
    void deleteById(int eID);
	void deleteByEmail(String email);

	int getIncrementedEId();

	Time getStartTime();
	Time getEndTime();
}
