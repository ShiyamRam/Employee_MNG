package net.javaguides.springboot.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
//	get all employees list 
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	
//	create or add the employees rest api method
	@PostMapping("/saveemployees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	
//	get employee by id rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
		Employee employee = this.employeeRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Employee not found in id" + id));
		return ResponseEntity.ok(employee);
	}
	
//	update employee record by id
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee>  updateEmployeeById(@PathVariable Long id , @RequestBody Employee employeeDetails){
		Employee employee = this.employeeRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Employee not found in id" + id));
		employee.setFirstname(employeeDetails.getFirstname());
		employee.setLastname(employeeDetails.getLastname());
		employee.setEmailId(employeeDetails.getEmailId());
		
		  Employee updatedEmployee =   employeeRepository.save(employee);
		  return ResponseEntity.ok(updatedEmployee);
	}
	
//	delete employee by id
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee = this.employeeRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Employee not found in id" + id));
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}