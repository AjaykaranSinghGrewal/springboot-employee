package com.example.employee.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.employee.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository

public class EmployeeDAOImpl implements EmployeeDAO{
	private EntityManager entityManager;

	@Autowired
	public EmployeeDAOImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> findAll() {
		//create a query
		TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);
		
		//execute query & get result list
		List<Employee> employees = theQuery.getResultList();
		
		//return the results
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		//get employee by id
		Employee employee = entityManager.find(Employee.class, theId);
		//return the employee
		return employee;
	}

	@Override
	public Employee save(Employee theEmployee) {
		//for merge method, if id = 0 then it'll be insert statement else an update statement
		Employee employee = entityManager.merge(theEmployee);
		return employee;
	}

	@Override
	public void deleteById(int theId) {
		//find employee by id
		Employee employee = entityManager.find(Employee.class, theId);
		//delete the employee
		entityManager.remove(employee);
	}

}
