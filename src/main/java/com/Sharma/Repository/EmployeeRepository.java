package com.Sharma.Repository;

//import java.util.List;
//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Sharma.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	
//	 Home work
	
//	Optional<List<Employee>> findEmployeeByEmpcity(String emp_city);

}
