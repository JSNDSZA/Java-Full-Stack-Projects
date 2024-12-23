package net.javaguides.ems.controller;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController  // Once used this class is able to handle HTTP requests
@RequestMapping("/api/employees")  // To define the base url for all the apis that we are going to build within this controller
public class EmployeeController {

    private EmployeeService employeeService;

    // Build Add Employee Rest API
    @PostMapping //  Incoming HTTP post request for the below method
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto)   // RequestBody will extract the Json from the HTTP request and it will convert JSON into employee dto java object
    {
       EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
       return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //Build Get Employee Rest API
@GetMapping("{id}") // Incoming HTTP get request for the below method
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId) // Pathvariable will bind the id mentioned in getmapping to the getEmployeeById method
    {
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }

    //Build Get All Employees Rest API
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    //Build Update Employee Rest API
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updatedEmployee )
    {
       EmployeeDto employeeDto =  employeeService.updateEmployee(employeeId, updatedEmployee);
       return ResponseEntity.ok(employeeDto);

    }

    //Build Delete Employee Rest API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId)
    {
       employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
