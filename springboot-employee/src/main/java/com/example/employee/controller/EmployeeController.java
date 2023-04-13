package com.example.employee.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.entity.Employee;
import com.example.employee.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}
	
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployeeById(@PathVariable int employeeId) {
		Employee theEmployee = employeeService.findById(employeeId);
		
		if(theEmployee==null) {
			throw new RuntimeException("Employee id not found " +employeeId);
		}
		return theEmployee;
	}
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		//set id to 0...in case client pass id in json accidentally 
		theEmployee.setId(0);
		Employee employee = employeeService.save(theEmployee);
		return employee;
	}
	
	@PutMapping("/employees/{employeeId}")
	public Employee updateEmployeeById(@RequestBody Employee theEmployee) {
		Employee employee = employeeService.findById(theEmployee.getId());
		return employeeService.save(employee);
	}
	
	@PutMapping("/employees") 
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		Employee employee = employeeService.save(theEmployee);
		return employee;
	}
	
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		Employee employee = employeeService.findById(employeeId);
		
		if(employee==null) {
			throw new RuntimeException("employee does not exist "+employeeId);
		}
		employeeService.deleteById(employeeId);
		return "empoyee with "+employeeId+ " deleted";
	}
}
