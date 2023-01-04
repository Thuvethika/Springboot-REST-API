package net.javaguides.demo2.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import net.javaguides.demo2.exception.ResourceNotFoundException;
import net.javaguides.demo2.model.Employee;
import net.javaguides.demo2.repository.EmployeeRepository;
import net.javaguides.demo2.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
	return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> employee= employeeRepository.findById(id);
		if(employee.isPresent()) {
			return employee.get();
		} else {
		throw new ResourceNotFoundException("Employee", "Id", id);
		}
		}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		
		//check exixting or not
	Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
			() -> new ResourceNotFoundException("Employee", "Id", id));
		
	existingEmployee.setFirstName(employee.getFirstName());
	existingEmployee.setLastName(employee.getLastName());
	existingEmployee.setEmail(employee.getEmail());
	
	// save employee details
	employeeRepository.save(existingEmployee);
	
		return existingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		//existing or not
		employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee", "Id", id));
		
		employeeRepository.deleteById(id);
		
	}
	
}
