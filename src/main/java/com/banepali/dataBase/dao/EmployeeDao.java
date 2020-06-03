package com.banepali.dataBase.dao;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

import com.banepali.dataBase.dao.entity.EmployeeEntity;

public interface EmployeeDao {

	void save(EmployeeEntity entity);

    List<EmployeeEntity> findAll();
	List<String> findAllUserId();

    
    EmployeeEntity employeeById(int eID);
    EmployeeEntity employeeByEmail(String email);
    EmployeeEntity employeeByUserId(String userId);
	Optional<EmployeeEntity> optionalEmployeeByEmail(String email);

    Optional<EmployeeEntity> employeeLogin(String email, String password);
    
    void update(EmployeeEntity entity);
	String updatePassword(String email, String password);
	void updateEmployeeActiveStatus(String userId);

    
    
    void deleteById(int eID);
	void deleteByEmail(String email);

	int getIncrementedEId();

	Time getStartTime();
	Time getEndTime();

	

	




	

	



}
