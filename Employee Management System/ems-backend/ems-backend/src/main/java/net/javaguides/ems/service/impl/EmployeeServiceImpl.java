package net.javaguides.ems.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.mapper.EmployeeMapper;
import net.javaguides.ems.repository.EmployeeRepository;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor  // Constructor based dependency injection using Lombok
public class EmployeeServiceImpl implements EmployeeService {

private EmployeeRepository employeeRepository;  //Injecting dependency

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);   //Converted employee dto to employee entity
       Employee savedEmployee = employeeRepository.save(employee); //Storing Entity into Database. This returns th object so saving it into savedEmployee object
          return EmployeeMapper.mapToEmployeeDto(savedEmployee);                    // converting savedEmployee jpEntity into employee Dto
                               // returning savedEmployee object back to the client

    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
      Employee employee =  employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee does not exist with given id : " + employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {

      List<Employee> employees =  employeeRepository.findAll();
        return employees.stream().map((employee) -> {
            return EmployeeMapper.mapToEmployeeDto(employee);
        }).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {
     Employee employee =  employeeRepository.findById(employeeId).
             orElseThrow(() ->
                     new ResourceNotFoundException("Employee doesn't exist with the given id: " + employeeId));

                employee.setFirstName(updateEmployee.getFirstName());
                employee.setLastName(updateEmployee.getLastName());
                employee.setEmail(updateEmployee.getEmail());

                Employee updatedEmployeeObj = employeeRepository.save(employee);

                 return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        Employee employee =  employeeRepository.findById(employeeId).
                orElseThrow(() ->
                        new ResourceNotFoundException("Employee doesn't exist with the given id: " + employeeId));


        employeeRepository.deleteById(employeeId);

    }
}
