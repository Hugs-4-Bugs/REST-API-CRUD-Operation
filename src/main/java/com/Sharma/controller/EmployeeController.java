package com.Sharma.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sharma.Repository.EmployeeRepository;
import com.Sharma.model.Employee;




@RestController   // use to define the class as a controller in restful web service
@RequestMapping("/api")     // used to map a web request to a method in controller layer/class
public class EmployeeController {
	
	@Autowired    // used to inject a bean into class OR used for automatic dependency injection.
	EmployeeRepository employeeRepository;
	
	
	
	
	@PostMapping("/employees")
	public String createNewEmployee(@RequestBody Employee employee) { 
		
// here	@RequestBody indicates that whatever data we are sending in the body it's(the data we are sending) mapping should be done properly with the object
		// @RequestBody will bind the request body to a method parameter in a controller class
		
		employeeRepository.save(employee);
		
		return "Employee Created in Database";
	}
	
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		List<Employee>empList = new ArrayList<>();
		employeeRepository.findAll().forEach(empList::add);
		return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);
	}
	
	
//
//	@GetMapping("/employees {empid}")
//	public ResponseEntity<Employee> getEmployeeById(@PathVariable long empid){
//		Optional<Employee> emp = employeeRepository.findById(empid);
//		if(emp.isPresent()) {
//			return new ResponseEntity<Employee>(emp.get(), HttpStatus.FOUND);
//		} else {
//			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
//		}
//		
//	}
	
  @GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
	    Optional<Employee> employee = employeeRepository.findById(id);   
	    if (employee.isPresent()) {
	        return new ResponseEntity<>(employee.get(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	
	@PutMapping("/employees/{empid}")
	public String updateEmployeeById(@PathVariable long empid, @RequestBody Employee employee) {
		Optional<Employee> emp = employeeRepository.findById(empid);
		if(emp.isPresent()) {
			Employee existEmp = emp.get();
			existEmp.setEmp_age(employee.getEmp_age());
			existEmp.setEmp_city(employee.getEmp_city());
			existEmp.setEmp_name(employee.getEmp_name());
			existEmp.setEmp_salary(employee.getEmp_salary());
			employeeRepository.save(existEmp);
			return "Employee Details against Id" + " " + empid + " " + "updated";
			
		} else {
			return "Employee doesn't exist for empid" + " " + empid;
		}
	}
	
//	@PutMapping("/employees/{id}")
//	public ResponseEntity<String> updateEmployeeById(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
//	    Optional<Employee> existingEmployee = employeeRepository.findById(id);
//	    
//	    if (existingEmployee.isPresent()) {
//	        Employee employeeToUpdate = existingEmployee.get();
//	        
//	        // Update the employee's information with the data from the updatedEmployee object
//	        employeeToUpdate.setName(updatedEmployee.getName());
//	        employeeToUpdate.setRole(updatedEmployee.getRole());
//	        // Set other properties you want to update
//	        
//	        employeeRepository.save(employeeToUpdate);
//	        
//	        return new ResponseEntity<>("Employee Updated in Database", HttpStatus.OK);
//	    } else {
//	        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
//	    }
//	}


	
	
	
	@DeleteMapping("/employees/{empid}")
	public String deleteEmployeeByEmpId(@PathVariable Long empid) {
		employeeRepository.deleteById(empid);
		return "Employee Deleted Successfully";
	}
	
	
	
	@DeleteMapping("/employees")
	public String deleteAllEmployee() {
		employeeRepository.deleteAll();
		return "Employee Deleted Successfully...";
	}
}
